package com.yijian.staff.mvp.vipermanage.viper.potential;

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
import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.LastInputEditText;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = "/test/7")
public class AddPotentialActivity extends MvcBaseActivity {

    @BindView(R.id.et_name)
    LastInputEditText etName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_phone)
    LastInputEditText etPhone;
    @BindView(R.id.tv_body_status)
    TextView tvBodyStatus;
    @BindView(R.id.tv_fitness_goal)
    TextView tvFitnessGoal;
    @BindView(R.id.tv_fithobby)
    TextView tvFithobby;
    @BindView(R.id.tv_hobby)
    TextView tvHobby;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    @BindView(R.id.sv)
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
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.add_potential_activity_navigation_bar);
        navigationBar2.setTitle("添加潜在");
        navigationBar2.setmRightTvText("确认");
        textView = navigationBar2.getmRightTv();
        TextView textView = navigationBar2.getmRightTv();
        textView.setTextColor(getResources().getColor(R.color.blue));
        navigationBar2.getmRightTv().setTextColor(getResources().getColor(R.color.blue));
        navigationBar2.hideLeftSecondIv();
        navigationBar2.getFirstLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
                finish();
            }
        });
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
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
                Toast.makeText(AddPotentialActivity.this, "名字只能是数字,英文字母和中文!", Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.tv_body_status, R.id.tv_fithobby, R.id.tv_hobby, R.id.tv_sex, R.id.tv_fitness_goal, R.id.tv_car_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_body_status:
                manualPickedView(healthStatusList, "", tvBodyStatus);
                break;
            case R.id.tv_fithobby:
                manualPickedView(fitnessHobbyList, "", tvFithobby);
                break;
            case R.id.tv_hobby:
                manualPickedView(hobbyList, "", tvHobby);
                break;
            case R.id.tv_sex:
                hideKeyBoard(view);
                optionsPickerView.show();
                break;
            case R.id.tv_fitness_goal:
                manualPickedView(fitnessGoalList, "", tvFitnessGoal);
                break;
            case R.id.tv_car_name:
                manualPickedView(userCarList, "", tvCarName);
                break;
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

}
