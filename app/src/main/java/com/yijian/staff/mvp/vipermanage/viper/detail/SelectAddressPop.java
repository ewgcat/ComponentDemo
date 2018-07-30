package com.yijian.staff.mvp.vipermanage.viper.detail;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.vipermanage.viper.detail.picker.GetJsonDataUtil;
import com.yijian.staff.mvp.vipermanage.viper.detail.picker.JsonBean_Service;
import com.yijian.staff.mvp.vipermanage.viper.detail.picker.OptionsPickerBuilder;
import com.yijian.staff.mvp.vipermanage.viper.detail.picker.OptionsPickerView;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.DensityUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class SelectAddressPop extends PopupWindow {

    private Context context;
    private Lifecycle lifecycle;
    private RelativeLayout rel_address;

    private ArrayList<JsonBean_Service> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;
    private OptionsPickerView pvNoLinkOptions;
    private EditText et_detail;

    private TextView tv_address;
    private TextView tv_address_title;

    public SelectAddressPop(Lifecycle lifecycle,Context context, String title, TextView tv_address) {
        super(context);
        this.lifecycle = lifecycle;
        this.context = context;
        this.tv_address = tv_address;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.activity_select_adress, null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout container = mMenuView.findViewById(R.id.container);
        RelativeLayout.LayoutParams containerLP = (RelativeLayout.LayoutParams) container.getLayoutParams();
        containerLP.height = DensityUtil.getScreenHeight(context) - DensityUtil.dip2px(context, 60);
        container.setLayoutParams(containerLP);
        this.setContentView(mMenuView);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.commen_pop_animation);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        mMenuView.findViewById(R.id.tv_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mMenuView.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvNoLinkOptions.returnData();
            }
        });
        rel_address = mMenuView.findViewById(R.id.rel_address);
        et_detail = mMenuView.findViewById(R.id.et_detail);
        tv_address_title = mMenuView.findViewById(R.id.tv_address_title);
        tv_address_title.setText(title);
        File addressFile = new File(context.getCacheDir() + "/service_province.json");
        if (!addressFile.exists()) {
            downloadService();
        } else {
            mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        }

    }

    private void downloadService() {
        HttpManager.getHasHeaderNoParam(HttpManager.QUERY_ADDRESS_URL, new ResultJSONObjectObserver(lifecycle) {

            @Override
            public void onSuccess(JSONObject result) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            String addressArray = result.getJSONArray("dataList").toString();
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                FileOutputStream fos = new FileOutputStream(new File(context.getCacheDir() + "/service_province.json"));
                                fos.write(addressArray.getBytes());
                                fos.close();
                                mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }).start();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

//                        Toast.makeText(context, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(context, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    if (isLoaded) {
                        showPickerView();
                    } else {
//                        Toast.makeText(context, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case MSG_LOAD_FAILED:
//                    Toast.makeText(context, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJsonFromSD(context, context.getCacheDir() + "/service_province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean_Service> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCitys().size(); c++) {//遍历该省份的所有城市
                JsonBean_Service.CitysBean citysBean = jsonBean.get(i).getCitys().get(c);
                String CityName = citysBean.getCityName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                if (citysBean.getDistricts() == null) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < citysBean.getDistricts().size(); d++) {
                        JsonBean_Service.CitysBean.DistrictsBean districtsBean = citysBean.getDistricts().get(d);
                        City_AreaList.add(districtsBean.getDistrictName());
                    }
                }

                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    private ArrayList<JsonBean_Service> parseData(String result) {//Gson 解析
        ArrayList<JsonBean_Service> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean_Service entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean_Service.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void showPickerView() {
        pvNoLinkOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String province = options1Items.get(options1).getPickerViewText();
                String city = options2Items.get(options1).get(option2);
                String area = options3Items.get(options1).get(option2).get(options3);
                String detail = et_detail.getText().toString();
                StringBuffer address = new StringBuffer(province);
                if (!province.equals(city)) {
                    address.append(city);
                }
                address.append(area);
                tv_address.setText(address.toString() + detail);
                dismiss();
            }
        })
                .setDecorView(rel_address)
                .setBackgroundId(Color.WHITE)
                .setOutSideCancelable(false)
                .isRestoreItem(true)
                .setSelectOptions(2, 2, 3)  //设置默认选中项
                .build();
        pvNoLinkOptions.setPicker(options1Items, options2Items, options3Items);//添加数据源
        pvNoLinkOptions.show(false);
    }

}
