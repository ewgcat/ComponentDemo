package com.yijian.staff.mvp.vipermanage.viper.detail.formatoroutdate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.vipermanage.viper.detail.picker.GetJsonDataUtil;
import com.yijian.staff.mvp.vipermanage.viper.detail.picker.JsonBean_Service;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectAddressActivity extends MvcBaseActivity {

    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.et_detail)
    EditText et_detail;
    @BindView(R.id.lin_container)
    LinearLayout lin_container;

    String title;
    String address;
    private OptionsPickerView pvNoLinkOptions;
    private ArrayList<JsonBean_Service> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private int selectOption1 = 0;
    private int selectOption2 = 0;
    private int selectOption3 = 0;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private Thread thread;
    private String fileName;
    String province;
    String city;
    String area;
    String detail;
    StringBuffer resultAddress = new StringBuffer(); //省市区详细地址数据
    char split;
    JSONObject addressIds=new JSONObject();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        showLoading();
        title = getIntent().getStringExtra("title");
        address = getIntent().getStringExtra("address");
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightTvText("确定");
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle(title);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail = et_detail.getText().toString();
                resultAddress.append(province);
                resultAddress.append((char) 1);
                resultAddress.append(city);
                resultAddress.append((char) 1);
                resultAddress.append(area);
                resultAddress.append((char) 1);
                resultAddress.append(detail);
                Intent intent = getIntent();
                intent.putExtra("resultAddress", resultAddress.toString());
                intent.putExtra("addressIds", addressIds.toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initData() {
        split = (char) 1;
        fileName = getCacheDir() + "/service_province.json";
        char split = (char) 1;
        if (!TextUtils.isEmpty(address)) {
            String[] addressArray = address.split(split + "");
            int length = addressArray.length;
            if (length > 2) {
                province = addressArray[0];
                city = addressArray[1];
                area = addressArray[2];
                tv_address.setText(province + split + city + split + area);
                if (length>3){
                    detail = addressArray[3];
                    et_detail.setText(detail);
                }

            }
        }
        File addressFile = new File(fileName);
        if (!addressFile.exists()) {
            downloadService();
        } else {
            mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        }
    }

    @OnClick({R.id.rl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_address: //选择地址
                showPickerView();
                break;
        }
    }

    private void downloadService() {
        HttpManager.getHasHeaderNoParam(HttpManager.QUERY_ADDRESS_URL, new ResultJSONObjectObserver(getLifecycle()) {

            @Override
            public void onSuccess(JSONObject result) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            String addressArray = result.getJSONArray("dataList").toString();
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                FileOutputStream fos = new FileOutputStream(new File(fileName));
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
                Toast.makeText(SelectAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        hideLoading();
                        lin_container.setVisibility(View.VISIBLE);
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
        String JsonData = new GetJsonDataUtil().getJsonFromSD(this, fileName);//获取assets目录下的json文件数据

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
                if ((!TextUtils.isEmpty(city)) && city.equals(CityName)) {
                    selectOption2 = c;
                }
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                if (citysBean.getDistricts() == null) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < citysBean.getDistricts().size(); d++) {
                        JsonBean_Service.CitysBean.DistrictsBean districtsBean = citysBean.getDistricts().get(d);
                        City_AreaList.add(districtsBean.getDistrictName());
                        if ((!TextUtils.isEmpty(area)) && area.equals(districtsBean.getDistrictName())) {
                            selectOption3 = d;
                        }
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
                if ((!TextUtils.isEmpty(province)) && province.equals(entity.getProvinceName())) {
                    selectOption1 = i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void showPickerView() {
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                province = options1Items.get(options1).getPickerViewText();
                city = options2Items.get(options1).get(option2);
                area = options3Items.get(options1).get(option2).get(options3);
                StringBuffer address = new StringBuffer(province);
                if (!province.equals(city)) {
                    address.append(split);
                    address.append(city);
                }
                address.append(split);
                address.append(area);
                tv_address.setText(address.toString());

                try {
                    addressIds.put("provinceId",options1Items.get(options1).getId());
                    addressIds.put("cityId",options1Items.get(options1).getCitys().get(option2).getId());
                    addressIds.put("districtId",options1Items.get(options1).getCitys().get(option2).getDistricts().get(options3).getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        })
                .setSelectOptions(selectOption1, selectOption2, selectOption3)  //设置默认选中项
                .build();
        pvNoLinkOptions.setPicker(options1Items, options2Items, options3Items);//添加数据源
        pvNoLinkOptions.show(false);
    }

}
