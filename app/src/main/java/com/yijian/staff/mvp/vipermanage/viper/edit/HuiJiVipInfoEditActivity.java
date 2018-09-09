package com.yijian.staff.mvp.vipermanage.viper.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.bean.EditHuiJiVipBody;
import com.yijian.staff.bean.ViperDetailBean;
import com.yijian.staff.mvp.vipermanage.viper.detail.formatoroutdate.SelectAddressActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员信息编辑页
 */
public class HuiJiVipInfoEditActivity extends AppCompatActivity {

    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.tv_fitnessGoal)
    TextView tv_fitnessGoal;
    @BindView(R.id.tv_onceJoinedClub)
    TextView tv_onceJoinedClub;
    @BindView(R.id.et_clubBrand)
    EditText et_clubBrand;
    @BindView(R.id.tv_yearIncome)
    TextView tv_yearIncome;
    @BindView(R.id.tv_carPrice)
    TextView tv_carPrice;
    @BindView(R.id.tv_hobby)
    TextView tv_hobby;
    @BindView(R.id.tv_nationality)
    TextView tv_nationality;
    @BindView(R.id.tv_nation)
    TextView tv_nation;
    @BindView(R.id.tv_occupation)
    TextView tv_occupation;
    @BindView(R.id.tv_marriageStatus)
    TextView tv_marriageStatus;
    @BindView(R.id.tv_hasChildren)
    TextView tv_hasChildren;
    @BindView(R.id.et_wx)
    EditText et_wx;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tv_height)
    TextView tv_height;
    @BindView(R.id.tv_weight)
    TextView tv_weight;
    @BindView(R.id.tv_position)
    TextView tv_position;
    @BindView(R.id.tv_workdress)
    TextView tv_workdress;
    @BindView(R.id.tv_healthStatus)
    TextView tv_healthStatus;
    @BindView(R.id.tv_homeaddress)
    TextView tv_homeaddress;
    @BindView(R.id.et_relationname)
    EditText et_relationname;
    @BindView(R.id.et_relationmobile)
    EditText et_relationmobile;


    com.alibaba.fastjson.JSONObject detailJsonObj = new com.alibaba.fastjson.JSONObject();
    ViperDetailBean.DetailBean detailBean;
    String memberId = "";
    String resource;
    List<String> resuorceList = new ArrayList<String>(); //用户获取渠道集合
    List<String> onceJoinedClubList = new ArrayList<String>();  //是否参加过俱乐部集合
    List<String> yearIncomeList = new ArrayList<String>();  //年收入集合
    List<String> carPriceList = new ArrayList<String>();  //用车价格集合
    List<String> nationList = new ArrayList<String>(); // 名族集合
    List<String> hasChildrenList = new ArrayList<String>();  //是否有子女集合
    List<String> marriageStatusList = new ArrayList<String>();  //婚姻状况集合
    List<String> nationalityList = new ArrayList<String>();  //国籍集合
    List<String> occupationList = new ArrayList<String>();  //职业集合
    List<String> hobbyList = new ArrayList<String>();  //爱好集合
    List<String> bodybuildingList = new ArrayList<String>();  //健身目的
    List<String> healthstatusList = new ArrayList<String>();  //身体状态
    List<String> positionList = new ArrayList<String>();  //职务
    List<String> heightList = new ArrayList<String>();  //职务
    List<String> weightList = new ArrayList<String>();  //职务
    final int REQUEST_ADDRESS_HOME_CODE = 100; //修改地址请求码
    final int REQUEST_ADDRESS_COMPANY_CODE = 101; //修改地址请求码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_info_edit);
        ButterKnife.bind(this);
        initTitle();
        initData();

    }

    private void initData() {
        memberId = getIntent().getStringExtra("memberId");

        detailBean = (ViperDetailBean.DetailBean) getIntent().getSerializableExtra("detail");
        resource = getIntent().getStringExtra("source");
        detailJsonObj = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(detailBean);
        detailJsonObj.put("source", resource);
        detailJsonObj.put("memberId", memberId);
        updateUi();
    }

    private void initTitle() {
        String name = getIntent().getStringExtra("name");
        NavigationBar NavigationBar = findViewById(R.id.vip_edit_navigation_bar);
        NavigationBar.setTitle(name + "详细信息");
        NavigationBar.hideLeftSecondIv();
        TextView rightTv = NavigationBar.getmRightTv();
        rightTv.setText("保存");
        rightTv.setTextColor(getResources().getColor(R.color.blue));
        NavigationBar.setBackClickListener(this);
    }

    @OnClick({R.id.right_tv, R.id.rl_source, R.id.rl_onceJoinedClub, R.id.rl_carPrice,
            R.id.rl_yearIncome, R.id.rl_nationality, R.id.rl_nation, R.id.rl_marriageStatus,
            R.id.rl_hasChildren, R.id.rl_occupation, R.id.rl_hobby, R.id.rl_fitnessGoal,
            R.id.rl_healthStatus, R.id.rl_position, R.id.rl_height, R.id.rl_weight, R.id.rl_workdress,
            R.id.rl_homeaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_tv: //保存
                submitData();

//                startActivity(new Intent(this, SelectAdressActivity.class));

                break;
            case R.id.rl_source: //用户渠道
                manualPickedView(resuorceList, "易健平台", tv_source);
                break;
            case R.id.rl_onceJoinedClub: // 是否参加过俱乐部
                manualPickedViewClub(onceJoinedClubList, "是", tv_onceJoinedClub);
                break;
            case R.id.rl_yearIncome: // 年收入
                manualPickedView(yearIncomeList, "10万以内", tv_yearIncome);
                break;
            case R.id.rl_carPrice: // 用车价格
                manualPickedView(carPriceList, "10万以内", tv_carPrice);
                break;
            case R.id.rl_nationality: // 国籍
                manualPickedView(nationalityList, "中国", tv_nationality);
                break;
            case R.id.rl_nation: // 名族
                manualPickedView(nationList, "汉族", tv_nation);
                break;
            case R.id.rl_marriageStatus: // 婚姻状态
                manualPickedView(marriageStatusList, "未婚", tv_marriageStatus);
                break;
            case R.id.rl_hasChildren: // 是否有子女
                manualPickedView(hasChildrenList, "无", tv_hasChildren);
                break;
            case R.id.rl_occupation: // 行业
                manualPickedView(occupationList, "", tv_occupation);
                break;
            case R.id.rl_hobby: // 爱好
                manualPickedView(hobbyList, "", tv_hobby);
                break;
            case R.id.rl_fitnessGoal:  //健身目的
                manualPickedView(bodybuildingList, "", tv_fitnessGoal);
                break;
            case R.id.rl_healthStatus: //身体状态
                manualPickedView(healthstatusList, "", tv_healthStatus);
                break;
            case R.id.rl_position: //职务
                manualPickedView(positionList, "", tv_position);
                break;
            case R.id.rl_height: //身高
                manualPickedView(heightList, "", tv_height);
                break;
            case R.id.rl_weight: //体重
                manualPickedView(weightList, "", tv_weight);
                break;
            case R.id.rl_workdress: //工作地址
                   /* SelectAddressPop workAddressPop = new SelectAddressPop(this, "工作地址", tv_workdress);
                    workAddressPop.showAsDropDown(getWindow().getDecorView());*/

                Intent intent = new Intent(HuiJiVipInfoEditActivity.this, SelectAddressActivity.class);
                intent.putExtra("title", "工作地址");
                intent.putExtra("area", detailBean.getCompanyRegion());
                intent.putExtra("detail", detailBean.getNewestCompanyAddress());
                intent.putExtra("type", 0);
                startActivityForResult(intent, REQUEST_ADDRESS_COMPANY_CODE);

                break;
            case R.id.rl_homeaddress: //家庭地址
                    /*SelectAddressPop homeAddressPop = new SelectAddressPop(this, "家庭地址", tv_homeaddress);
                    homeAddressPop.showAsDropDown(getWindow().getDecorView());*/
                Intent intent2 = new Intent(HuiJiVipInfoEditActivity.this, SelectAddressActivity.class);
                intent2.putExtra("title", "家庭地址");
                intent2.putExtra("area", detailBean.getHomeRegion());
                intent2.putExtra("detail", detailBean.getNewestAddress());
                intent2.putExtra("type", 1);
                startActivityForResult(intent2, REQUEST_ADDRESS_HOME_CODE);
                break;
        }
    }


    /**
     * 提交数据
     */
    private void submitData() {
        EditHuiJiVipBody editHuiJiVipBody = new EditHuiJiVipBody();
        if (!TextUtils.isEmpty(tv_carPrice.getText().toString())) {
            editHuiJiVipBody.setCarPrice(tv_carPrice.getText().toString());
        }
        if (!TextUtils.isEmpty(tv_fitnessGoal.getText().toString())) {
            editHuiJiVipBody.setFitnessGoal(tv_fitnessGoal.getText().toString());
        }

        String anObject = tv_hasChildren.getText().toString();
        if (!TextUtils.isEmpty(anObject)) {
            editHuiJiVipBody.setHasChildren(("有".equals(anObject)));
        }
        if (!TextUtils.isEmpty(tv_yearIncome.getText().toString())) {
            editHuiJiVipBody.setYearIncome(tv_yearIncome.getText().toString());
        }
        if (!TextUtils.isEmpty(et_clubBrand.getText().toString())) {
            editHuiJiVipBody.setClubBrand(et_clubBrand.getText().toString());
        }

        if (!TextUtils.isEmpty(tv_healthStatus.getText().toString())) {
            editHuiJiVipBody.setHealthStatus(tv_healthStatus.getText().toString());
        }
        if (!TextUtils.isEmpty(tv_hobby.getText().toString())) {
            editHuiJiVipBody.setHobby(tv_hobby.getText().toString());
        }


        String anObject1 = tv_marriageStatus.getText().toString();
        if (!TextUtils.isEmpty(anObject1)) {
            editHuiJiVipBody.setMarriageStatus(("未婚".equals(anObject1)) ? 0 : 1);
        }

        editHuiJiVipBody.setMemberId(memberId);


        if (!TextUtils.isEmpty(tv_nation.getText().toString())) {
            editHuiJiVipBody.setNation(tv_nation.getText().toString());
        }


        if (!TextUtils.isEmpty(tv_nationality.getText().toString())) {
            editHuiJiVipBody.setNationality(tv_nationality.getText().toString());
        }


        if (!TextUtils.isEmpty(tv_position.getText().toString())) {
            editHuiJiVipBody.setPosition(tv_position.getText().toString());
        }


        if (!TextUtils.isEmpty(tv_occupation.getText().toString())) {
            editHuiJiVipBody.setOccupation(tv_occupation.getText().toString());
        }

        String anObject2 = tv_onceJoinedClub.getText().toString();
        if (!TextUtils.isEmpty(anObject2)) {
            editHuiJiVipBody.setOnceJoinedClub(("是".equals(anObject2)));
        }

        if (!TextUtils.isEmpty(tv_source.getText().toString())) {
            editHuiJiVipBody.setSource(tv_source.getText().toString());
        }

        ViperDetailBean.DetailBean.CompanyRegion companyRegion = detailBean.getCompanyRegion();
        if (companyRegion != null) {
            EditHuiJiVipBody.CompanyAddressIdsBean companyAddressIdsBean = new EditHuiJiVipBody.CompanyAddressIdsBean();
            companyAddressIdsBean.setProvinceId(companyRegion.getProvinceId());
            companyAddressIdsBean.setCityId(companyRegion.getCityId());
            companyAddressIdsBean.setDistrictId(companyRegion.getDistrictId());
            editHuiJiVipBody.setCompanyAddressIds(companyAddressIdsBean);
        }

        ViperDetailBean.DetailBean.HomeRegion homeRegion = detailBean.getHomeRegion();
        if (companyRegion != null) {
            EditHuiJiVipBody.HomeAddressIdsBean homeAddressIdsBean = new EditHuiJiVipBody.HomeAddressIdsBean();
            homeAddressIdsBean.setProvinceId(homeRegion.getProvinceId());
            homeAddressIdsBean.setCityId(homeRegion.getCityId());
            homeAddressIdsBean.setDistrictId(homeRegion.getDistrictId());
            editHuiJiVipBody.setHomeAddressIds(homeAddressIdsBean);
        }

        editHuiJiVipBody.setAddress(detailBean.getNewestAddress());
        editHuiJiVipBody.setCompanyAddress(detailBean.getNewestCompanyAddress());

        if (!TextUtils.isEmpty(et_relationname.getText().toString())) {
            editHuiJiVipBody.setUrgentContact(et_relationname.getText().toString());
        }
        if (!TextUtils.isEmpty(et_relationmobile.getText().toString())) {
            editHuiJiVipBody.setContactPhone(et_relationmobile.getText().toString());
        }
        if (!TextUtils.isEmpty(et_wx.getText().toString())) {
            editHuiJiVipBody.setWechatNo(et_wx.getText().toString());
        }
        if (!TextUtils.isEmpty(et_email.getText().toString())) {
            editHuiJiVipBody.setEmail(et_email.getText().toString());
        }
        if (!TextUtils.isEmpty(tv_height.getText().toString())) {
            editHuiJiVipBody.setHeight(tv_height.getText().toString());
        }
        if (!TextUtils.isEmpty(tv_weight.getText().toString())) {
            editHuiJiVipBody.setWeight(tv_weight.getText().toString());
        }


        HttpManager.postEditHuiJiVipInfo(HttpManager.GET_HUIJI_VIPER_EDIT_URL, editHuiJiVipBody, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e("Test", result.toString());
                Toast.makeText(HuiJiVipInfoEditActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                setResult(1);
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HuiJiVipInfoEditActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }


    /**
     * 更新数据
     */
    private void updateUi() {
        tv_source.setText(strEmpty(resource));
        tv_fitnessGoal.setText(strEmpty(detailBean.getFitnessGoal()));

        boolean onceJoinedClub = detailBean.isOnceJoinedClub();

        tv_onceJoinedClub.setText(onceJoinedClub ? "是" : "否");
        if (!onceJoinedClub) {
            et_clubBrand.setEnabled(false);
            et_clubBrand.setText("暂未录入");

        } else {
            et_clubBrand.setEnabled(true);
            et_clubBrand.setText(strEmpty(detailBean.getClubBrand()));
        }


        tv_yearIncome.setText(strEmpty(detailBean.getYearIncome()));
        tv_carPrice.setText(strEmpty(detailBean.getCarPrice()));
        tv_hobby.setText(strEmpty(detailBean.getHobby()));
        tv_nationality.setText(strEmpty(detailBean.getNationality()));
        tv_nation.setText(strEmpty(detailBean.getNation()));
        tv_occupation.setText(strEmpty(detailBean.getOccupation()));
        tv_position.setText(strEmpty(detailBean.getPosition()));
        tv_marriageStatus.setText(strEmpty(detailBean.getMarriageStatus()));
        tv_hasChildren.setText(strEmpty(detailBean.getChildrenStatus()));

        et_wx.setText(strEmpty(detailBean.getWechatNo()));
        et_email.setText(strEmpty(detailBean.getEmail()));
        tv_height.setText(strEmpty(detailBean.getHeight()));
        tv_weight.setText(strEmpty(detailBean.getWeight()));
        tv_healthStatus.setText(strEmpty(detailBean.getHealthStatus()));
        tv_position.setText(strEmpty(detailBean.getPosition()));
        et_relationname.setText(strEmpty(detailBean.getUrgentContact()));
        et_relationmobile.setText(strEmpty(detailBean.getContactPhone()));


        ViperDetailBean.DetailBean.HomeRegion homeRegion = detailBean.getHomeRegion();
        String homeAddress = detailBean.getNewestAddress();
        if (TextUtils.isEmpty(homeAddress) && homeRegion == null) {
            tv_homeaddress.setText("暂未录入");
        } else {
            StringBuffer hAddress = new StringBuffer();
            if (homeRegion != null) {

                if (!TextUtils.isEmpty(homeRegion.getProvinceName())) {
                    hAddress.append(homeRegion.getProvinceName());
                    hAddress.append((char) 1);
                }

                if (!TextUtils.isEmpty(homeRegion.getCityName())) {
                    hAddress.append(homeRegion.getCityName());
                    hAddress.append((char) 1);
                }
                if (!TextUtils.isEmpty(homeRegion.getDistrictName())) {
                    hAddress.append(homeRegion.getDistrictName());
                    hAddress.append((char) 1);
                }

            }
            if (!TextUtils.isEmpty(homeAddress)) {
                hAddress.append((char) 1);
                hAddress.append(homeAddress);
            }
            tv_homeaddress.setText(hAddress.toString());
        }

        ViperDetailBean.DetailBean.CompanyRegion comRegion = detailBean.getCompanyRegion();
        String comAddress = detailBean.getNewestCompanyAddress();
        if (TextUtils.isEmpty(comAddress) && comRegion == null) {
            tv_workdress.setText("暂未录入");
        } else {
            StringBuffer cAddress = new StringBuffer();
            if (comRegion != null) {
                if (!TextUtils.isEmpty(comRegion.getProvinceName())) {
                    cAddress.append(comRegion.getProvinceName());
                    cAddress.append((char) 1);
                }

                if (!TextUtils.isEmpty(comRegion.getCityName())) {
                    cAddress.append(comRegion.getCityName());
                    cAddress.append((char) 1);
                }
                if (!TextUtils.isEmpty(comRegion.getDistrictName())) {
                    cAddress.append(comRegion.getDistrictName());
                    cAddress.append((char) 1);
                }
            }
            if (!TextUtils.isEmpty(comAddress)) {
                cAddress.append((char) 1);
                cAddress.append(comAddress);
            }
            tv_workdress.setText(cAddress.toString());
        }

        downSourceFromService();
    }

    private String strEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        } else if ("暂未录入".equals(str)) {
            return "";
        } else {
            return str;

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
     * 选项弹出框选择俱乐部
     *
     * @param opts
     * @param defaultValue
     * @param tv_widget
     */
    private void manualPickedViewClub(List<String> opts, String defaultValue, TextView tv_widget) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                tv_widget.setText(opts.get(options1));
                if (options1 == 1) {
//                    et_clubBrand.setText("未录入");
                    et_clubBrand.setEnabled(false);
                    et_clubBrand.setText("");
                } else {
                    et_clubBrand.setEnabled(true);
                }
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
        onceJoinedClubList.add("是");
        onceJoinedClubList.add("否");

        hasChildrenList.add("有");
        hasChildrenList.add("无");

        marriageStatusList.add("未婚");
        marriageStatusList.add("已婚");

        for (int i = 150; i < 200; i++) {
            heightList.add(i + "");
        }

        for (int i = 35; i < 100; i++) {
            weightList.add(i + "");
        }


        HttpManager.getHasHeaderNoParam(HttpManager.GET_HUIJI_VIPER_DICT_URL, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                try {

                    //用户获取渠道
                    JSONObject yhhqqd = result.getJSONObject("YHHQQD");
                    JSONArray yhhqqdJsonArray = yhhqqd.getJSONArray("items");
                    for (int j = 0; j < yhhqqdJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) yhhqqdJsonArray.get(j);
                        resuorceList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //年收入
                    JSONObject nsr = result.getJSONObject("NSR");
                    JSONArray nsrJsonArray = nsr.getJSONArray("items");
                    for (int j = 0; j < nsrJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) nsrJsonArray.get(j);
                        yearIncomeList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //用车价格
                    JSONObject ycjz = result.getJSONObject("YCJZ");
                    JSONArray ycjzJsonArray = ycjz.getJSONArray("items");
                    for (int j = 0; j < ycjzJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) ycjzJsonArray.get(j);
                        carPriceList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //爱好
                    JSONObject xqah = result.getJSONObject("XQAH");
                    JSONArray xqahJsonArray = xqah.getJSONArray("items");
                    for (int j = 0; j < xqahJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) xqahJsonArray.get(j);
                        hobbyList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //国籍
                    JSONObject gj = result.getJSONObject("GJ");
                    JSONArray gjJsonArray = gj.getJSONArray("items");
                    for (int j = 0; j < gjJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) gjJsonArray.get(j);
                        nationalityList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //民族
                    JSONObject mz = result.getJSONObject("MZ");
                    JSONArray mzJsonArray = mz.getJSONArray("items");
                    for (int j = 0; j < mzJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) mzJsonArray.get(j);
                        nationList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //职务
                    JSONObject zw = result.getJSONObject("ZW");
                    JSONArray zwJsonArray = zw.getJSONArray("items");
                    for (int j = 0; j < zwJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) zwJsonArray.get(j);
                        positionList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //行业
                    JSONObject hy = result.getJSONObject("HY");
                    JSONArray hyJsonArray = hy.getJSONArray("items");
                    for (int j = 0; j < hyJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) hyJsonArray.get(j);
                        occupationList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //健身目的
                    JSONObject jsmd = result.getJSONObject("JSMD");
                    JSONArray jsmdJsonArray = jsmd.getJSONArray("items");
                    for (int j = 0; j < jsmdJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) jsmdJsonArray.get(j);
                        bodybuildingList.add(itemJsonObj.getString("dictItemName"));
                    }

                    //身体状态
                    JSONObject stzt = result.getJSONObject("STZT");
                    JSONArray stztJsonArray = stzt.getJSONArray("items");
                    for (int j = 0; j < stztJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) stztJsonArray.get(j);
                        healthstatusList.add(itemJsonObj.getString("dictItemName"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HuiJiVipInfoEditActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADDRESS_COMPANY_CODE && resultCode == RESULT_OK) { //工作地址
            String allAddress = data.getStringExtra("allAddress");
            String detail = data.getStringExtra("detail");
            tv_workdress.setText(allAddress);
            ViperDetailBean.DetailBean.CompanyRegion companyRegion = (ViperDetailBean.DetailBean.CompanyRegion) data.getSerializableExtra("area");
            detailBean.setCompanyRegion(companyRegion);
            detailBean.setNewestCompanyAddress(detail);
        } else if (requestCode == REQUEST_ADDRESS_HOME_CODE && resultCode == RESULT_OK) { //家庭地址
            String allAddress = data.getStringExtra("allAddress");
            String detail = data.getStringExtra("detail");
            ViperDetailBean.DetailBean.HomeRegion homeRegion = (ViperDetailBean.DetailBean.HomeRegion) data.getSerializableExtra("area");
            detailBean.setHomeRegion(homeRegion);
            detailBean.setNewestAddress(detail);
            tv_homeaddress.setText(allAddress);

        }
    }

}
