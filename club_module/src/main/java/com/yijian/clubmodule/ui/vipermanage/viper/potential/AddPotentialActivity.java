package com.yijian.clubmodule.ui.vipermanage.viper.potential;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.widget.EmptyView;
import com.yijian.commonlib.widget.LastInputEditText;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.CommonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



@Route(path = "/test/7")
public class AddPotentialActivity extends MvcBaseActivity implements View.OnClickListener {
    LastInputEditText etName;
    TextView tvSex;
    LastInputEditText etPhone;
    TextView tvBodyStatus;
    TextView tvFitnessGoal;
    TextView tvFithobby;
    TextView tvHobby;
    TextView tvCarName;
    EmptyView empty_view;
    ScrollView sv;


    private OptionsPickerView optionsPickerView;

    private int sex = 1;//1 男  2女
    private List<String> healthStatusList = new ArrayList<>(); //身体状态
    private List<String> fitnessGoalList = new ArrayList<>(); //健身目的
    private List<String> fitnessHobbyList = new ArrayList<>(); //健身爱好
    private List<String> hobbyList = new ArrayList<>(); //兴趣爱好
    private List<String> userCarList = new ArrayList<>(); //使用车辆
    private TextView textView;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_potential;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


        etName = findViewById(R.id.et_name);
        tvSex = findViewById(R.id.tv_sex);
        etPhone = findViewById(R.id.et_phone);
        tvBodyStatus = findViewById(R.id.tv_body_status);
        tvFitnessGoal = findViewById(R.id.tv_fitness_goal);
        tvFithobby = findViewById(R.id.tv_fithobby);
        tvHobby = findViewById(R.id.tv_hobby);
        tvCarName = findViewById(R.id.tv_car_name);
        empty_view = findViewById(R.id.empty_view);
        sv = findViewById(R.id.sv);


        findViewById(R.id.tv_body_status).setOnClickListener(this);
        findViewById(R.id.tv_fithobby).setOnClickListener(this);
        findViewById(R.id.tv_hobby).setOnClickListener(this);
        findViewById(R.id.tv_sex).setOnClickListener(this);
        findViewById(R.id.tv_fitness_goal).setOnClickListener(this);
        findViewById(R.id.tv_car_name).setOnClickListener(this);

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_add_potential",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.add_potential_activity_navigation_bar);
        navigationBar.setTitle("添加潜在");
        navigationBar.setmRightTvText("确认");
        textView =  navigationBar.getmRightTv();
        TextView textView =  navigationBar.getmRightTv();
        textView.setTextColor(getResources().getColor(R.color.blue));
         navigationBar.getmRightTv().setTextColor(getResources().getColor(R.color.blue));
        navigationBar .hideLeftSecondIv();
        navigationBar.getFirstLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
                finish();
            }
        });
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequest();
            }
        });

        downSourceFromService();

        ArrayList<String> sexDescList = new ArrayList<>();
        sexDescList.add("男");
        sexDescList.add("女");

        optionsPickerView = new OptionsPickerBuilder(AddPotentialActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvSex.setText(sexDescList.get(options1));
                sex = options1 + 1;
            }
        }).build();
        optionsPickerView.setPicker(sexDescList);
        optionsPickerView.setSelectOptions(0);
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downSourceFromService();
            }
        });
    }


    private void sendRequest() {

        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String healthStatus = tvBodyStatus.getText().toString().trim();
        String fitnessGoal = tvFitnessGoal.getText().toString().trim();
        String fitnessHobby = tvFithobby.getText().toString().trim();
        String useCar = tvCarName.getText().toString().trim();
        String hobby = tvHobby.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(AddPotentialActivity.this, "名字不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            boolean b = CommonUtil.isMatchName(name);
            if (!b){
                Toast.makeText(AddPotentialActivity.this, "名字只能是英文字母和中文!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(AddPotentialActivity.this, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (CommonUtil.isPhoneFormat(phone)) {
            showLoading();
            AddPotentialRequestBody addPotentialRequestBody = new AddPotentialRequestBody(phone, name, healthStatus, fitnessHobby, hobby, useCar, fitnessGoal, sex);
            HttpManager.postAddPotential(addPotentialRequestBody, new ResultJSONObjectObserver(getLifecycle()) {
                @Override
                public void onSuccess(JSONObject result) {
                    hideLoading();
                    hideKeyBoard(etPhone);
                    finish();
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    showToast(msg);
                }
            });
        } else {
            Toast.makeText(AddPotentialActivity.this, "手机号码不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

    }


    /**
     * 隐藏键盘
     */
    public void hideKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 显示键盘
     */
    public void showKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, 0);

        }
    }



    /**
     * 选项弹出框
     *
     * @param opts
     * @param defaultValue
     * @param tv_widget
     */
    private void manualPickedView(List<String> opts, String defaultValue, TextView tv_widget) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_widget.setText(opts.get(options1));
            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.show();
    }


    /**
     * 从服务器上拉去字典数据
     */
    public void downSourceFromService() {
        empty_view.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        showLoading();
        HttpManager.getHasHeaderNoParam(HttpManager.GET_HUIJI_VIPER_DICT_URL, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                try {
                    sv.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);


                    //用车价格
                    JSONObject ycpp = result.getJSONObject("YCPP");
                    JSONArray ycppJsonArray = ycpp.getJSONArray("items");
                    for (int j = 0; j < ycppJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) ycppJsonArray.get(j);
                        userCarList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //爱好
                    JSONObject xqah = result.getJSONObject("XQAH");
                    JSONArray xqahJsonArray = xqah.getJSONArray("items");
                    for (int j = 0; j < xqahJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) xqahJsonArray.get(j);
                        hobbyList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //健身目的
                    JSONObject jsmd = result.getJSONObject("JSMD");
                    JSONArray jsmdJsonArray = jsmd.getJSONArray("items");
                    for (int j = 0; j < jsmdJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) jsmdJsonArray.get(j);
                        fitnessGoalList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //健身爱好
                    JSONObject ydah = result.getJSONObject("YDAH");
                    JSONArray ydahJsonArray = ydah.getJSONArray("items");
                    for (int j = 0; j < ydahJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) ydahJsonArray.get(j);
                        fitnessHobbyList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //身体状态
                    JSONObject stzt = result.getJSONObject("STZT");
                    JSONArray stztJsonArray = stzt.getJSONArray("items");
                    for (int j = 0; j < stztJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) stztJsonArray.get(j);
                        healthStatusList.add(itemJsonObj.getString("dictItemName"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(AddPotentialActivity.this, msg, Toast.LENGTH_SHORT).show();
                empty_view.setVisibility(View.VISIBLE);
                sv.setVisibility(View.GONE);
                hideLoading();

            }
        });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_body_status) {
            manualPickedView(healthStatusList, "", tvBodyStatus);

        } else if (i == R.id.tv_fithobby) {
            manualPickedView(fitnessHobbyList, "", tvFithobby);

        } else if (i == R.id.tv_hobby) {
            manualPickedView(hobbyList, "", tvHobby);

        } else if (i == R.id.tv_sex) {
            hideKeyBoard(view);
            optionsPickerView.show();

        } else if (i == R.id.tv_fitness_goal) {
            manualPickedView(fitnessGoalList, "", tvFitnessGoal);

        } else if (i == R.id.tv_car_name) {
            manualPickedView(userCarList, "", tvCarName);

        }
    }
}
