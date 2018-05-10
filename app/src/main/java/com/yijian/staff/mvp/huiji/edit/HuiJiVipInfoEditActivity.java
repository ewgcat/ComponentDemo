package com.yijian.staff.mvp.huiji.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.bean.EditHuiJiVipBody;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

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
    @BindView(R.id.et_address)
    EditText et_address;

    com.alibaba.fastjson.JSONObject detailJsonObj = new com.alibaba.fastjson.JSONObject();
    VipDetailBean.DetailBean detailBean;
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


    List<String> resuorceIdList = new ArrayList<>(); //用户获取渠道集合
    List<String> yearIncomeIdList = new ArrayList<>();  //年收入集合
    List<String> carPriceIdList = new ArrayList();  //用车价格集合
    List<String> nationIdList = new ArrayList<>(); // 名族集合
    List<String> nationalityIdList = new ArrayList<>();  //国籍集合
    List<String> occupationIdList = new ArrayList<>();  //职业集合
    List<String> hobbyIdList = new ArrayList<>();  //爱好集合
    List<String> bodybuildingIdList = new ArrayList<String>();  //健身目的

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

        detailBean = (VipDetailBean.DetailBean) getIntent().getSerializableExtra("detail");
        resource = getIntent().getStringExtra("source");
        detailJsonObj = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(detailBean);
        detailJsonObj.put("source", resource);
        detailJsonObj.put("memberId", memberId);
        updateUi();
    }

    private void initTitle() {
        String name = getIntent().getStringExtra("name");
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_edit_navigation_bar);
        navigationBar2.setTitle(name+"详细信息");
        navigationBar2.hideLeftSecondIv();
        TextView rightTv = navigationBar2.getmRightTv();
        rightTv.setText("保存");
        navigationBar2.setBackClickListener(this);
    }

    @OnClick({R.id.right_tv, R.id.tv_source, R.id.tv_onceJoinedClub, R.id.tv_carPrice,
            R.id.tv_yearIncome, R.id.tv_nationality, R.id.tv_nation, R.id.tv_marriageStatus,
            R.id.tv_hasChildren, R.id.tv_occupation, R.id.tv_hobby, R.id.tv_fitnessGoal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_tv: //保存
                submitData();
                break;
            case R.id.tv_source: //用户渠道
                manualPickedView(resuorceList, "易健平台", tv_source);
                break;
            case R.id.tv_onceJoinedClub: // 是否参加过俱乐部
                manualPickedView(onceJoinedClubList, "是", tv_onceJoinedClub);
                break;
            case R.id.tv_yearIncome: // 年收入
                manualPickedView(yearIncomeList, "10万以内", tv_yearIncome);
                break;
            case R.id.tv_carPrice: // 用车价格
                manualPickedView(carPriceList, "10万以内", tv_carPrice);
                break;
            case R.id.tv_nationality: // 国籍
                manualPickedView(nationalityList, "中国", tv_nationality);
                break;
            case R.id.tv_nation: // 名族
                manualPickedView(nationList, "汉族", tv_nation);
                break;
            case R.id.tv_marriageStatus: // 婚姻状态
                manualPickedView(marriageStatusList, "未婚", tv_marriageStatus);
                break;
            case R.id.tv_hasChildren: // 是否有子女
                manualPickedView(hasChildrenList, "无", tv_hasChildren);
                break;
            case R.id.tv_occupation: // 职业
                manualPickedView(occupationList, "", tv_occupation);
                break;
            case R.id.tv_hobby: // 爱好
                manualPickedView(hobbyList, "", tv_hobby);
                break;
            case R.id.tv_fitnessGoal:  //健身目的
                manualPickedView(bodybuildingList, "", tv_fitnessGoal);
                break;
        }
    }


    /**
     * 提交数据
     */
    private void submitData() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("address", et_address.getText().toString());
        String o = tv_carPrice.getText().toString();
        if (!TextUtils.isEmpty(o)) {
            paramMap.put("carPrice", o);
        }
        paramMap.put("clubBrand", et_clubBrand.getText().toString());
        paramMap.put("fitnessGoal", tv_fitnessGoal.getText().toString());
        String anObject = tv_hasChildren.getText().toString();
        if (!TextUtils.isEmpty(anObject)) {
            paramMap.put("hasChildren", ("有".equals(anObject)));
        }
        String o6 = tv_hobby.getText().toString();
        if (!TextUtils.isEmpty(o6)) {
            paramMap.put("hobby", o6);
        }
        String anObject1 = tv_marriageStatus.getText().toString();
        if (!TextUtils.isEmpty(anObject1)) {
            paramMap.put("marriageStatus", ("未婚".equals(anObject1)) ? 0 : 1);
        }
        paramMap.put("memberId", memberId);

        String o1 = tv_nation.getText().toString();
        if (!TextUtils.isEmpty(o1)) {
            paramMap.put("nation", o1);
        }
        String o2 = tv_nationality.getText().toString();
        if (!TextUtils.isEmpty(o2)) {
            paramMap.put("nationality", o2);
        }
        String o3 = tv_occupation.getText().toString();
        if (!TextUtils.isEmpty(o3)) {
            paramMap.put("position", o3);
        }


        String anObject2 = tv_onceJoinedClub.getText().toString();
        if (!TextUtils.isEmpty(anObject2)) {
            paramMap.put("onceJoinedClub", ("是".equals(anObject2)));
        }
        String o4 = tv_source.getText().toString();
        if (!TextUtils.isEmpty(o4)) {
            paramMap.put("source", o4);
        }

        String o5 = tv_yearIncome.getText().toString();
        if (!TextUtils.isEmpty(o5)) {
            paramMap.put("yearIncome", o5);
        }


        EditHuiJiVipBody editHuiJiVipBody = new EditHuiJiVipBody(paramMap);

        HttpManager.postEditHuiJiVipInfo(HttpManager.GET_HUIJI_VIPER_EDIT_URL, editHuiJiVipBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e("Test", result.toString());
                Toast.makeText(HuiJiVipInfoEditActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
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
        tv_source.setText(resource);
        tv_fitnessGoal.setText(detailBean.getFitnessGoal());
        tv_onceJoinedClub.setText((detailBean.isOnceJoinedClub()) ? "是" : "否");
        et_clubBrand.setText(detailBean.getClubBrand());
        tv_yearIncome.setText(detailBean.getYearIncome());
        tv_carPrice.setText(detailBean.getCarPrice());
        tv_hobby.setText(detailBean.getHobby());
        tv_nationality.setText(detailBean.getNationality());
        tv_nation.setText(detailBean.getNation());
        tv_occupation.setText(detailBean.getPosition());
        tv_marriageStatus.setText(detailBean.getMarriageStatus());
        tv_hasChildren.setText(detailBean.getChildrenStatus());
        et_address.setText(detailBean.getAddress());
        downSourceFromService();
    }

    /**
     * 选项弹出框
     *
     * @param opts
     * @param defaultValue
     * @param tv_widget
     */
    private void manualPickedView(List<String> opts, String defaultValue, TextView tv_widget) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
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
        onceJoinedClubList.add("是");
        onceJoinedClubList.add("否");

        hasChildrenList.add("有");
        hasChildrenList.add("无");

        marriageStatusList.add("未婚");
        marriageStatusList.add("已婚");


        HttpManager.getHasHeaderNoParam(HttpManager.GET_HUIJI_VIPER_DICT_URL, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {

                    //用户获取渠道
                    JSONObject yhhqqd = result.getJSONObject("YHHQQD");
                    JSONArray yhhqqdJsonArray = yhhqqd.getJSONArray("items");
                    for (int j = 0; j < yhhqqdJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) yhhqqdJsonArray.get(j);
                        resuorceList.add(itemJsonObj.getString("dictItemName"));
                        resuorceIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //年收入
                    JSONObject nsr = result.getJSONObject("NSR");
                    JSONArray nsrJsonArray = nsr.getJSONArray("items");
                    for (int j = 0; j < nsrJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) nsrJsonArray.get(j);
                        yearIncomeList.add(itemJsonObj.getString("dictItemName"));
                        yearIncomeIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //用车价格
                    JSONObject ycjz = result.getJSONObject("YCJZ");
                    JSONArray ycjzJsonArray = ycjz.getJSONArray("items");
                    for (int j = 0; j < ycjzJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) ycjzJsonArray.get(j);
                        carPriceList.add(itemJsonObj.getString("dictItemName"));
                        carPriceIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //爱好
                    JSONObject xqah = result.getJSONObject("XQAH");
                    JSONArray xqahJsonArray = xqah.getJSONArray("items");
                    for (int j = 0; j < xqahJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) xqahJsonArray.get(j);
                        hobbyList.add(itemJsonObj.getString("dictItemName"));
                        hobbyIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //国籍
                    JSONObject gj = result.getJSONObject("GJ");
                    JSONArray gjJsonArray = gj.getJSONArray("items");
                    for (int j = 0; j < gjJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) gjJsonArray.get(j);
                        nationalityList.add(itemJsonObj.getString("dictItemName"));
                        nationalityIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //民族
                    JSONObject mz = result.getJSONObject("MZ");
                    JSONArray mzJsonArray = mz.getJSONArray("items");
                    for (int j = 0; j < mzJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) mzJsonArray.get(j);
                        nationList.add(itemJsonObj.getString("dictItemName"));
                        nationIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //职务
                    JSONObject zw = result.getJSONObject("ZW");
                    JSONArray zwJsonArray = zw.getJSONArray("items");
                    for (int j = 0; j < zwJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) zwJsonArray.get(j);
                        occupationList.add(itemJsonObj.getString("dictItemName"));
                        occupationIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    //健身目的
                    JSONObject jsmd = result.getJSONObject("JSMD");
                    JSONArray jsmdJsonArray = jsmd.getJSONArray("items");
                    for (int j = 0; j < jsmdJsonArray.length(); j++) {
                        JSONObject itemJsonObj = (JSONObject) jsmdJsonArray.get(j);
                        bodybuildingList.add(itemJsonObj.getString("dictItemName"));
                        bodybuildingIdList.add(itemJsonObj.getString("dictItemId"));
                    }

                    /*if(TextUtils.isEmpty(tv_source.getText().toString())){
                        tv_source.setText(resuorceList.get(0));
                    }

                    if(TextUtils.isEmpty(tv_fitnessGoal.getText().toString())){
                        tv_fitnessGoal.setText(bodybuildingList.get(0));
                    }

                    if(TextUtils.isEmpty(tv_onceJoinedClub.getText().toString())){
                        tv_onceJoinedClub.setText(onceJoinedClubList.get(0));
                    }

                    if(TextUtils.isEmpty(tv_yearIncome.getText().toString())){
                        tv_yearIncome.setText(yearIncomeList.get(0));
                    }

                    if(TextUtils.isEmpty(tv_carPrice.getText().toString())){
                        tv_carPrice.setText(carPriceList.get(0));
                    }

                     if(TextUtils.isEmpty(tv_hobby.getText().toString())){
                         tv_hobby.setText(hobbyList.get(0));
                    }

                    if(TextUtils.isEmpty(tv_nationality.getText().toString())){
                        tv_nationality.setText(nationalityList.get(0));
                    }

                     if(TextUtils.isEmpty(tv_nation.getText().toString())){
                         tv_nation.setText(nationList.get(0));
                    }

                     if(TextUtils.isEmpty(tv_occupation.getText().toString())){
                         tv_occupation.setText(occupationList.get(0));
                    }

                     if(TextUtils.isEmpty(tv_marriageStatus.getText().toString())){
                         tv_marriageStatus.setText(marriageStatusList.get(0));
                    }

                    if(TextUtils.isEmpty(tv_hasChildren.getText().toString())){
                        tv_hasChildren.setText(hasChildrenList.get(0));
                    }*/


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
}
