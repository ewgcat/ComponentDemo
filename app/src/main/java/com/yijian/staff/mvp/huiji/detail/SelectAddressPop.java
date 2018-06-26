package com.yijian.staff.mvp.huiji.detail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.yijian.staff.mvp.huiji.detail.picker.GetJsonDataUtil;
import com.yijian.staff.mvp.huiji.detail.picker.JsonBean;
import com.yijian.staff.mvp.huiji.detail.picker.OptionsPickerBuilder;
import com.yijian.staff.mvp.huiji.detail.picker.OptionsPickerView;
import com.yijian.staff.util.DensityUtil;

import org.json.JSONArray;

import java.util.ArrayList;

public class SelectAddressPop extends PopupWindow {

    private Context context;
    private RelativeLayout rel_address;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
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

    public SelectAddressPop(Context context,TextView tv_address) {
        super(context);
        this.context = context;
        this.tv_address = tv_address;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.activity_select_adress, null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout container = mMenuView.findViewById(R.id.container);
        RelativeLayout.LayoutParams containerLP = (RelativeLayout.LayoutParams) container.getLayoutParams();
        containerLP.height = DensityUtil.getScreenHeight(context)-DensityUtil.dip2px(context,60);
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
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        Toast.makeText(context, "Begin Parse Data", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    if (isLoaded) {
                        showPickerView();
                    } else {
                        Toast.makeText(context, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(context, "Parse Failed", Toast.LENGTH_SHORT).show();
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
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

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

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
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


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    public void showPickerView(){
        pvNoLinkOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(option2) +
                        options3Items.get(options1).get(option2).get(options3);
                String detail = et_detail.getText().toString();
                Log.e("Test","address==="+tx+"  detail==="+detail);
                tv_address.setText(tx + detail);
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
//        rel_address.addView(pvNoLinkOptions.rootView);
        pvNoLinkOptions.show(false);
    }

}
