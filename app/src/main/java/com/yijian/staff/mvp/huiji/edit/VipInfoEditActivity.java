package com.yijian.staff.mvp.huiji.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.huiji.bean.EditHuiJiVipBody;
import com.yijian.staff.mvp.huiji.bean.VipDetailBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
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
public class VipInfoEditActivity extends AppCompatActivity {

    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.et_fitnessGoal)
    EditText et_fitnessGoal;
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

    List<String> resuorceIdList = new ArrayList<>(); //用户获取渠道集合
    List<String> onceJoinedClubIdList = new ArrayList<>();  //是否参加过俱乐部集合
    List<String> yearIncomeIdList = new ArrayList<>();  //年收入集合
    List<String> carPriceIdList = new ArrayList();  //用车价格集合
    List<String> nationIdList = new ArrayList<>(); // 名族集合
    List<String> hasChildrenIdList = new ArrayList<>();  //是否有子女集合
    List<String> marriageStatusIdList = new ArrayList<>();  //婚姻状况集合
    List<String> nationalityIdList = new ArrayList<>();  //国籍集合
    List<String> occupationIdList = new ArrayList<>();  //职业集合
    List<String> hobbyIdList = new ArrayList<>();  //爱好集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_info_edit);
        ButterKnife.bind(this);
        initTitle();
        initData();

    }

    private void initData() {
        downSourceFromService();
        memberId = getIntent().getStringExtra("memberId");
        detailBean = (VipDetailBean.DetailBean) getIntent().getSerializableExtra("detail");
        resource = getIntent().getStringExtra("source");
        detailJsonObj = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(detailBean);
        detailJsonObj.put("source", resource);
        detailJsonObj.put("memberId", memberId);
        updateUi();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_edit_navigation_bar);
        navigationBar2.setTitle("张三详细信息");
        navigationBar2.hideLeftSecondIv();
        TextView rightTv = navigationBar2.getmRightTv();
        rightTv.setText("保存");
        navigationBar2.setBackClickListener(this);
    }

    @OnClick({R.id.right_tv, R.id.tv_source, R.id.tv_onceJoinedClub, R.id.tv_carPrice,
            R.id.tv_yearIncome, R.id.tv_nationality, R.id.tv_nation, R.id.tv_marriageStatus, R.id.tv_hasChildren, R.id.tv_occupation,R.id.tv_hobby})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_tv: //保存
                submitData();
                break;
            case R.id.tv_source: //用户渠道
                manualPickedView(resuorceList, "易健平台", tv_source);
                break;
            case R.id.tv_onceJoinedClub: // 是否参加过俱乐部
                manualPickedView(onceJoinedClubList, "易健平台", tv_onceJoinedClub);
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
        }
    }


    /**
     * 提交数据
     */
    private void submitData() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("address", et_address.getText().toString());
        paramMap.put("carPrice", carPriceIdList.get(carPriceList.indexOf(tv_carPrice.getText().toString())));
        paramMap.put("clubBrand", et_clubBrand.getText().toString());
        paramMap.put("fitnessGoal", et_fitnessGoal.getText().toString());
        paramMap.put("hasChildren", ("有".equals(tv_hasChildren.getText().toString())));
        paramMap.put("hobby", hobbyIdList.get(hobbyList.indexOf(tv_hobby.getText().toString())));
        paramMap.put("marriageStatus", ("未婚".equals(tv_marriageStatus.getText().toString())) ? 0 : 1);
        paramMap.put("memberId", memberId);
        paramMap.put("nation", nationIdList.get(nationList.indexOf(tv_nation.getText().toString())));//tv_nation.getText().toString()
        paramMap.put("nationality",nationalityIdList.get(nationalityList.indexOf(tv_nationality.getText().toString())) ); //tv_nationality.getText().toString()
        paramMap.put("occupation", occupationIdList.get(occupationList.indexOf(tv_occupation.getText().toString()))); //tv_occupation.getText().toString()
        paramMap.put("onceJoinedClub", ("有".equals(tv_onceJoinedClub.getText().toString())));
        paramMap.put("source", resuorceIdList.get(resuorceList.indexOf(tv_source.getText().toString()))); //tv_source.getText().toString()
        paramMap.put("yearIncome", yearIncomeIdList.get(yearIncomeList.indexOf(tv_yearIncome.getText().toString()))); //tv_yearIncome.getText().toString()



        EditHuiJiVipBody editHuiJiVipBody = new EditHuiJiVipBody(paramMap);

        HttpManager.postEditHuiJiVipInfo(HttpManager.GET_HUIJI_VIPER_EDIT_URL,  editHuiJiVipBody, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e("Test", result.toString());
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(VipInfoEditActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 更新数据
     */
    private void updateUi() {
        tv_source.setText(resource);
        et_fitnessGoal.setText(detailBean.getFitnessGoal());
        tv_onceJoinedClub.setText((detailBean.isOnceJoinedClub()) ? "是" : "否");
        et_clubBrand.setText(detailBean.getClubBrand());
        tv_yearIncome.setText(detailBean.getYearIncome());
        tv_carPrice.setText(detailBean.getCarPrice());
        tv_hobby.setText(detailBean.getHobby());
        tv_nationality.setText(detailBean.getNationality());
        tv_nation.setText(detailBean.getNation());
        tv_occupation.setText(detailBean.getOccupation());
        tv_marriageStatus.setText(detailBean.getMarriageStatus());
        tv_hasChildren.setText(detailBean.getChildrenStatus());
        et_address.setText(detailBean.getAddress());

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


        HttpManager.getHasHeaderNoParam(HttpManager.GET_HUIJI_VIPER_DICT_URL,  new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray jsonArray = result.getJSONArray("dicts");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        Integer dictId = jsonObject.getInt("dictId");
                        JSONArray itemJsonArray = jsonObject.getJSONArray("items");
                        switch (dictId) {
                            case 5: // 装载用户获取渠道集合
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    resuorceList.add(itemJsonObj.getString("dictItemName"));
                                    resuorceIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                            case 14: // 名族集合
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    nationList.add(itemJsonObj.getString("dictItemName"));
                                    nationIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                            case 17: // 年收入集合
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    yearIncomeList.add(itemJsonObj.getString("dictItemName"));
                                    yearIncomeIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                            case 4: // 国籍集合
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    nationalityList.add(itemJsonObj.getString("dictItemName"));
                                    nationalityIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                            case 13: // 用车价值
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    carPriceList.add(itemJsonObj.getString("dictItemName"));
                                    carPriceIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                            case 15: // 职业
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    occupationList.add(itemJsonObj.getString("dictItemName"));
                                    occupationIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                            case 8: // 爱好
                                for (int j = 0; j < itemJsonArray.length(); j++) {
                                    JSONObject itemJsonObj = (JSONObject) itemJsonArray.get(j);
                                    hobbyList.add(itemJsonObj.getString("dictItemName"));
                                    hobbyIdList.add(itemJsonObj.getString("dictItemId"));
                                }
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(VipInfoEditActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
