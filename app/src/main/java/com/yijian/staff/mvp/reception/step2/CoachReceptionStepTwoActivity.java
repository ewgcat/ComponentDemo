package com.yijian.staff.mvp.reception.step2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.step1.ReceptionStepOneActivity;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.OptionItemData;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.QustionBean;
import com.yijian.staff.mvp.reception.step3.ReceptionStepThreeActivity;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import org.jsoup.helper.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/3/19.
 */

public class CoachReceptionStepTwoActivity extends AppCompatActivity implements View.OnClickListener ,CoachReceptionStepTwoContract.View, CancelPhysicalDialog.CancelLisenter {

    private OptionItemData optionItemData;
    private static final String TAG = "DemoActivity";
    private List<ParentQuestionBean> parentObj;
    private ArrayList<String> symbol;
    private ArrayList<String> decimal;
    private ReceptionStep2Adapter demoAdapter;
    private RecyclerView recyclerView;
    private TextView tvName;
    private TextView tvHeight;
    private TextView tvAge;
    public static final int TYPE_0 = 0;//选项是数字，有符号，有小数
    public static final int TYPE_1 = 1;//选项是数字，有小数
    public static final int TYPE_2 = 2;//选项是数字
    public static final int TYPE_3 = 3;//选项是StringArray
    private CoachReceptionStepTwoPresenter presenter;
    private RecptionerInfoBean consumerBean;
    private CancelPhysicalDialog cancelPhysicalDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_reception_step_two);


        initView();
        Intent intent = getIntent();
        if (intent.hasExtra(ReceptionActivity.CONSUMER)){
            consumerBean = intent.getParcelableExtra(ReceptionActivity.CONSUMER);
        }else {
            Toast.makeText(CoachReceptionStepTwoActivity.this,"获取客户信息失败,请重新进入接待流程", Toast.LENGTH_SHORT).show();
            return;
        }


        presenter = new CoachReceptionStepTwoPresenter(this);
        presenter.setView(this);
        initData();

        cancelPhysicalDialog = new CancelPhysicalDialog();
        cancelPhysicalDialog.setCancelLisenter(this);
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.step_two_navigation_bar2);

        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setTitle("体测录入(2/5)");
        navigationBar2.setmRightTvText("下一步");


        TimeBar timeBar = findViewById(R.id.step_two_timebar);
        timeBar.showTimeBar(2);

        RelativeLayout rlName = findViewById(R.id.rl_name);
        RelativeLayout rlHeight = findViewById(R.id.rl_height);
        RelativeLayout rlAge = findViewById(R.id.rl_age);
        RelativeLayout rlSave = findViewById(R.id.rl_save);
        TextView tvCancel = findViewById(R.id.tv_cancel);

        tvName = findViewById(R.id.tv_name);
        if (consumerBean!=null)tvName.setText(""+consumerBean.getName());

        tvHeight = findViewById(R.id.tv_height);
        tvAge = findViewById(R.id.tv_age);

        rlName.setOnClickListener(this);
        rlHeight.setOnClickListener(this);
        rlAge.setOnClickListener(this);
        rlSave.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);


    }

    private void initData() {
        QustionBean bean = new Gson().fromJson(JsonStringData.metaData, QustionBean.class);
        parentObj = bean.getParentObj();
        optionItemData = new OptionItemData();
        symbol = optionItemData.getSymbol();
        decimal = optionItemData.getDecimal();


        demoAdapter = new ReceptionStep2Adapter(parentObj, this);
        recyclerView.setAdapter(demoAdapter);

     if (consumerBean!=null)  presenter.viewTestData(consumerBean.getId());
    }

    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, CoachReceptionStepTwoActivity.class);
    }


    public void showBottomView(int childPosition, int parentPosition) {
        ChildOptBean childObjBean = parentObj.get(parentPosition).getChildList().get(childPosition);
        String qusType = childObjBean.getQusType();
        if (!"normal".equals(qusType)) return;
        boolean valueIsNum = childObjBean.isValueIsNum();
        boolean hasSymbol = childObjBean.isHasSymbol();
        boolean hasDecimal = childObjBean.isHasDecimal();
        if (valueIsNum && hasSymbol && hasDecimal) {//选项是数字，有符号，有小数
            showPickerView(TYPE_0, childObjBean, childPosition, parentPosition);
        } else if (valueIsNum && hasDecimal) {////选项是数字，有小数
            showPickerView(TYPE_1, childObjBean, childPosition, parentPosition);
        } else if (valueIsNum) {//选项是数字
            showPickerView(TYPE_2, childObjBean, childPosition, parentPosition);
        } else {//选项是StringArray
            showPickerView(TYPE_3, childObjBean, childPosition, parentPosition);
        }


    }

    private void showPickerView(int type, ChildOptBean childObjBean, int childPosition, int parentPosition) {

        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = "";
                switch (type) {
                    case TYPE_0:
                        str = "symbol:" + symbol.get(options1) + "integerRange:" + getOptRange(childObjBean).get(options2) + "decimal:" + decimal.get(options3);
                        childObjBean.setUserValue("" + symbol.get(options1) + getOptRange(childObjBean).get(options2) + decimal.get(options3));
                        break;
                    case TYPE_1:
                        str = "integerRange:" + getOptRange(childObjBean).get(options1) + "decimal:" + decimal.get(options2);
                        childObjBean.setUserValue("" + getOptRange(childObjBean).get(options1) + decimal.get(options2));
                        break;
                    case TYPE_2:
                    case TYPE_3:
                        str = "integerRange:" + getOptRange(childObjBean).get(options1);
                        childObjBean.setUserValue("" + getOptRange(childObjBean).get(options1));
                        break;

                }
                //体重(kg)//体脂肪(kg)//腰围(cm)//臀围(cm)
                String qustion = childObjBean.getQustion();
                if ("体重(kg)".equals(qustion) || "体脂肪(kg)".equals(qustion) || "腰围(cm)".equals(qustion) || "臀围(cm)".equals(qustion))
                    computerQualityAndBodyFatAndWaistToHip();//计算身体质量指数/体脂百分比/腰臀比

                Toast.makeText(CoachReceptionStepTwoActivity.this, str, Toast.LENGTH_SHORT).show();
                demoAdapter.notifyChildChanged(parentPosition, childPosition);
            }
        }).build();


        switch (type) {
            case TYPE_0:
                pvNoLinkOptions.setNPicker(symbol, getOptRange(childObjBean), decimal);
                int i = getOptRange(childObjBean).indexOf(childObjBean.getDefaultValue());
                if (i < 0) i = 0;
                pvNoLinkOptions.setSelectOptions(0, i, 0);
                break;
            case TYPE_1:
                pvNoLinkOptions.setNPicker(getOptRange(childObjBean), decimal, null);
                int i1 = getOptRange(childObjBean).indexOf(childObjBean.getDefaultValue());
                if (i1 < 0) i1 = 0;
                pvNoLinkOptions.setSelectOptions(i1, 0);
                break;
            case TYPE_2:
            case TYPE_3:
                pvNoLinkOptions.setNPicker(getOptRange(childObjBean), null, null);
                int i2 = getOptRange(childObjBean).indexOf(childObjBean.getDefaultValue());
                if (i2 < 0) i2 = 0;
                pvNoLinkOptions.setSelectOptions(i2);
                break;
        }
        pvNoLinkOptions.show();
    }


    @Nullable
    private ArrayList<String> getOptRange(ChildOptBean childObjBean) {

        if (childObjBean.isValueIsNum()) {
            ArrayList<String> integerRange = optionItemData.getOptionDataFactory().initIntegerRange(Integer.valueOf(childObjBean.getMinValue()), Integer.valueOf(childObjBean.getMaxValue()));
            return integerRange;
        } else {
            ArrayList<String> optRange = new ArrayList<>();
            List<String> valueList = childObjBean.getValueArray();
            optRange.addAll(valueList);
            return optRange;
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                Intent i = new Intent(this, ReceptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.right_tv:
                Intent intent = new Intent(CoachReceptionStepTwoActivity.this, ReceptionStepThreeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;


            case R.id.rl_height:
                ArrayList<String> heightRange = optionItemData.getOptionDataFactory().initIntegerRange(120, 300);
                manualPickedView(heightRange, "170", tvHeight);
                break;

            case R.id.rl_age:
                ArrayList<String> ageRange = optionItemData.getOptionDataFactory().initIntegerRange(18, 100);
                manualPickedView(ageRange, "20", tvAge);
                break;

            case R.id.rl_save:
                if (consumerBean!=null){
                    String height = tvHeight.getText().toString();
                     String age = tvAge.getText().toString();
                   presenter.saveData(consumerBean.getId(),parentObj,height,age);
                }else {
                    Toast.makeText(CoachReceptionStepTwoActivity.this,"获取客户信息失败,请重新进入接待流程", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.tv_cancel:
                cancelPhysicalDialog.show(getFragmentManager(),"cancelPhysicalDialog");
                break;
        }
    }




    private void manualPickedView(ArrayList<String> opts, String defaultValue, TextView name) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                name.setText(opts.get(options1));
                if ("身高(cm)".equals(name)) {
                    computerQualityAndBodyFatAndWaistToHip();
                }

            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.show();
    }

    //计算身体质量指数/体脂百分比/腰臀比
    private void computerQualityAndBodyFatAndWaistToHip() {

        //计算身体质量指数
        String userHeight = this.tvHeight.getText().toString();
        String userWeight = "请选择";
        String userFat = "请选择";
        String userWaist = "请选择";
        String userHipline = "请选择";
//体重(kg)//体脂肪(kg)//腰围(cm)//臀围(cm)
        for (ParentQuestionBean parentQuestionBean : parentObj) {
            List<ChildOptBean> childList = parentQuestionBean.getChildList();
            for (ChildOptBean childOptBean : childList) {
                if ("体重(kg)".equals(childOptBean.getQustion())) {
                    userWeight = childOptBean.getUserValue();
                } else if ("体脂肪(kg)".equals(childOptBean.getQustion())) {
                    userFat = childOptBean.getUserValue();
                } else if ("腰围(cm)".equals(childOptBean.getQustion())) {
                    userWaist = childOptBean.getUserValue();
                } else if ("臀围(cm)".equals(childOptBean.getQustion())) {
                    userHipline = childOptBean.getUserValue();
                }
            }
        }

        for (ParentQuestionBean parentQuestionBean : parentObj) {
            List<ChildOptBean> childList = parentQuestionBean.getChildList();
            int parentPosition = parentObj.indexOf(parentQuestionBean);

            for (ChildOptBean childOptBean : childList) {
                int childPosition = childList.indexOf(childOptBean);

                if ("身体质量指数BMI(kg/m2)".equals(childOptBean.getQustion())) {
                    if (!"请选择".equals(userHeight) && !"请选择".equals(userWeight)) {
                        Double weight = Double.valueOf(userWeight);
                        Double height = Double.valueOf(userHeight);
                        double i = weight / (height * 0.01d);
                        childOptBean.setUserValue(String.format("%.2f", i));
                        Log.e(TAG, "computerQualityAndBodyFatAndWaistToHip: " + i);
                        demoAdapter.notifyChildChanged(parentPosition, childPosition);
                    }

                } else if ("体脂百分比PBF(%)".equals(childOptBean.getQustion())) {
                    if (!"请选择".equals(userWeight) && !"请选择".equals(userFat)) {
                        Double weight = Double.valueOf(userWeight);
                        Double fat = Double.valueOf(userFat);
                        double i = weight / (fat * 1.00d);
                        childOptBean.setUserValue(String.format("%.2f", i) );
                        demoAdapter.notifyChildChanged(parentPosition, childPosition);
                    }
                } else if ("腰臀比".equals(childOptBean.getQustion())) {
                    if (!"请选择".equals(userWaist) && !"请选择".equals(userHipline)) {
                        Double waist = Double.valueOf(userWaist);
                        Double hipline = Double.valueOf(userHipline);
                        double i = waist / (hipline * 1.00d);
                        childOptBean.setUserValue(String.format("%.2f", i));
                        demoAdapter.notifyChildChanged(parentPosition, childPosition);
                    }
                }
            }
        }
    }

    @Override
    public void showSavaSucceed() {
        Log.e(TAG, "showSavaSucceed: ");
    }

    @Override
    public void showUserData(PhysicalExaminationBean bean) {
        tvAge.setText(bean.getAge().toString());
        tvHeight.setText(bean.getHeight().toString());


        for (ParentQuestionBean parentQuestionBean : parentObj) {
            //必填字段
            if (parentQuestionBean.getTitle().equals("人体成分分析")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    switch (childOptBean.getQustion()) {
                        case "体重(kg)":

                            BigDecimal weight = bean.getWeight();
                            if (weight!=null&&!TextUtils.isEmpty(weight.toString()))
                            childOptBean.setUserValue(weight.toString());
                            break;
                        case "骨骼肌(kg)":
//                            physicalExaminationBean.setSkeletalMuscle(new BigDecimal(userValue));
                            BigDecimal skeletalMuscle = bean.getSkeletalMuscle();
                            if (skeletalMuscle!=null&&!TextUtils.isEmpty(skeletalMuscle.toString()))
                                childOptBean.setUserValue(skeletalMuscle.toString());
                            break;
                        case "体脂肪(kg)":
//                            physicalExaminationBean.setFat(new BigDecimal(userValue));
                            BigDecimal fat = bean.getFat();
                            if (fat!=null&&!TextUtils.isEmpty(fat.toString()))
                                childOptBean.setUserValue(fat.toString());
                            break;

                        case "身体水份含量(kg)":
//                            physicalExaminationBean.setWater(new BigDecimal(userValue));
                            BigDecimal water = bean.getWater();
                            if (water!=null&&!TextUtils.isEmpty(water.toString()))
                                childOptBean.setUserValue(water.toString());
                            break;
                        case "去脂体重(kg)":
//                            physicalExaminationBean.setWeightNoFat(new BigDecimal(userValue));
                            BigDecimal weightNoFat = bean.getWeightNoFat();
                            if (weightNoFat!=null&&!TextUtils.isEmpty(weightNoFat.toString()))
                                childOptBean.setUserValue(weightNoFat.toString());
                            break;
                        case "身体质量指数BMI(kg/m2)":
//                            physicalExaminationBean.setBmi(new BigDecimal(userValue));
                            BigDecimal bmi = bean.getBmi();
                            if (bmi!=null&&!TextUtils.isEmpty(bmi.toString()))
                                childOptBean.setUserValue(bmi.toString());
                            break;
                        case "体脂百分比PBF(%)":
//                            physicalExaminationBean.setPbf(new BigDecimal(userValue));
                            BigDecimal pbf = bean.getPbf();
                            if (pbf!=null&&!TextUtils.isEmpty(pbf.toString()))
                                childOptBean.setUserValue(pbf.toString());
                            break;

                        case "腰臀比":
//                            physicalExaminationBean.setWhr(new BigDecimal(userValue));
                            BigDecimal whr = bean.getWhr();
                            if (whr!=null&&!TextUtils.isEmpty(whr.toString()))
                                childOptBean.setUserValue(whr.toString());
                            break;

                        case "基础代谢":
//                            physicalExaminationBean.setBasalMetabolism(new BigDecimal(userValue));
                            BigDecimal basalMetabolism = bean.getBasalMetabolism();
                            if (basalMetabolism!=null&&!TextUtils.isEmpty(basalMetabolism.toString()))
                                childOptBean.setUserValue(basalMetabolism.toString());
                            break;
                        case "肌肉控制(kg)":
//                            physicalExaminationBean.setMuscleController(new BigDecimal(userValue));
                            BigDecimal muscleController = bean.getMuscleController();
                            if (muscleController!=null&&!TextUtils.isEmpty(muscleController.toString()))
                                childOptBean.setUserValue(muscleController.toString());
                            break;

                        case "脂肪控制(kg)":
//                            physicalExaminationBean.setFatController(new BigDecimal(userValue));
                            BigDecimal fatController = bean.getFatController();
                            if (fatController!=null&&!TextUtils.isEmpty(fatController.toString()))
                                childOptBean.setUserValue(fatController.toString());
                            break;

                    }


                }

            }

            //必填字段
            if (parentQuestionBean.getTitle().equals("基础测量数据")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    switch (childOptBean.getQustion()) {
                        case "腰围(cm)":
//                            physicalExaminationBean.setWaist(new BigDecimal(userValue));
                            BigDecimal waist = bean.getWaist();
                            if (waist!=null&&!TextUtils.isEmpty(waist.toString()))
                                childOptBean.setUserValue(waist.toString());

                            break;
                        case "臀围(cm)":
//                            physicalExaminationBean.setHipline(new BigDecimal(userValue));
                            BigDecimal hipline = bean.getHipline();
                            if (hipline!=null&&!TextUtils.isEmpty(hipline.toString()))
                                childOptBean.setUserValue(hipline.toString());
                            break;
                        case "皮脂厚度(cm)":
//                            physicalExaminationBean.setSkinFatLand(new BigDecimal(userValue));
                            BigDecimal skinFatLand = bean.getSkinFatLand();
                            if (skinFatLand!=null&&!TextUtils.isEmpty(skinFatLand.toString()))
                                childOptBean.setUserValue(skinFatLand.toString());
                            break;
                        case "左臂(cm)":
//                            physicalExaminationBean.setLeftHand(new BigDecimal(userValue));
                            BigDecimal leftHand = bean.getLeftHand();
                            if (leftHand!=null&&!TextUtils.isEmpty(leftHand.toString()))
                                childOptBean.setUserValue(leftHand.toString());
                            break;
                        case "右臂(cm)":
//                            physicalExaminationBean.setRightHand(new BigDecimal(userValue));
                            BigDecimal rightHand = bean.getRightHand();
                            if (rightHand!=null&&!TextUtils.isEmpty(rightHand.toString()))
                                childOptBean.setUserValue(rightHand.toString());
                            break;
                        case "左大腿(cm)":
//                            physicalExaminationBean.setLeftHhigh(new BigDecimal(userValue));
                            BigDecimal leftHhigh = bean.getLeftHhigh();
                            if (leftHhigh!=null&&!TextUtils.isEmpty(leftHhigh.toString()))
                                childOptBean.setUserValue(leftHhigh.toString());
                            break;
                        case "右大腿(cm)":
//                            physicalExaminationBean.setRightHhigh(new BigDecimal(userValue));
                            BigDecimal rightHhigh = bean.getRightHhigh();
                            if (rightHhigh!=null&&!TextUtils.isEmpty(rightHhigh.toString()))
                                childOptBean.setUserValue(rightHhigh.toString());
                            break;
                        case "左小腿(cm)":
//                            physicalExaminationBean.setLeftShins(new BigDecimal(userValue));
                            BigDecimal leftShins = bean.getLeftShins();
                            if (leftShins!=null&&!TextUtils.isEmpty(leftShins.toString()))
                                childOptBean.setUserValue(leftShins.toString());
                            break;
                        case "右小腿(cm)":
//                            physicalExaminationBean.setRightShins(new BigDecimal(userValue));
                            BigDecimal rightShins = bean.getRightShins();
                            if (rightShins!=null&&!TextUtils.isEmpty(rightShins.toString()))
                                childOptBean.setUserValue(rightShins.toString());
                            break;
                        case "血压(收缩压)mmHg":
//                            physicalExaminationBean.setShrinkBloodPressure(new BigDecimal(userValue));
                            BigDecimal shrinkBloodPressure = bean.getShrinkBloodPressure();
                            if (shrinkBloodPressure!=null&&!TextUtils.isEmpty(shrinkBloodPressure.toString()))
                                childOptBean.setUserValue(shrinkBloodPressure.toString());
                            break;
                        case "血压(舒张压)mmHg":
//                            physicalExaminationBean.setDiastoleBloodPressure(new BigDecimal(userValue));
                            BigDecimal diastoleBloodPressure = bean.getDiastoleBloodPressure();
                            if (diastoleBloodPressure!=null&&!TextUtils.isEmpty(diastoleBloodPressure.toString()))
                                childOptBean.setUserValue(diastoleBloodPressure.toString());
                            break;
                        case "静态心跳率(次/分钟)":
//                            physicalExaminationBean.setHeartbeat(Integer.valueOf(userValue));
                            Integer heartbeat = bean.getHeartbeat();
                            if (heartbeat!=null&&!TextUtils.isEmpty(heartbeat.toString()))
                                childOptBean.setUserValue(heartbeat.toString());
                            break;
                    }
                }

            }

            //必填字段
            if (parentQuestionBean.getTitle().equals("体态评估-侧面")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    switch (childOptBean.getQustion()) {
                        case "头":
//                            physicalExaminationBean.setsHead("" + userValue);
                            childOptBean.setUserValue(bean.getsHead());
                            break;

                        case "颈椎":
//                            physicalExaminationBean.setsSpine("" + userValue);
                            childOptBean.setUserValue(bean.getsCervicalV());
                            break;
                        case "肩胛骨":
//                            physicalExaminationBean.setsScapula("" + userValue);
                            childOptBean.setUserValue(bean.getsScapula());
                            break;
                        case "胸椎":
//                            physicalExaminationBean.setsThoracicV("" + userValue);
                            childOptBean.setUserValue(bean.getsThoracicV());
                            break;
                        case "腰椎":
//                            physicalExaminationBean.setsLumbarV("" + userValue);
                            childOptBean.setUserValue(bean.getsLumbarV());
                            break;
                        case "骨盆":
//                            physicalExaminationBean.setsPelvis("" + userValue);
                            childOptBean.setUserValue(bean.getsPelvis());
                            break;
                        case "髋关节":
//                            physicalExaminationBean.setsHipJ("" + userValue);
                            childOptBean.setUserValue(bean.getsHipJ());
                            break;
                        case "膝关节":
//                            physicalExaminationBean.setsKnee("" + userValue);
                            childOptBean.setUserValue(bean.getsKnee());
                            break;
                        case "踝关节":
//                            physicalExaminationBean.setsAnkle("" + userValue);
                            childOptBean.setUserValue(bean.getsAnkle());
                            break;
                        case "腓骨外髁":
//                            physicalExaminationBean.setsFibula("" + userValue);
                            childOptBean.setUserValue(bean.getsFibula());
                            break;
                    }
                }
            }


            //必填字段
            if (parentQuestionBean.getTitle().equals("体态评估-背面")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    String userValue = childOptBean.getUserValue();
                    switch (childOptBean.getQustion()) {
                        case "头":
//                            physicalExaminationBean.setbHead("" + userValue);
                            childOptBean.setUserValue(bean.getbHead());
                            break;
                        case "肩部":
//                            physicalExaminationBean.setbShoulders("" + userValue);
                            childOptBean.setUserValue(bean.getbShoulders());
                            break;
                        case "肩胛骨":
//                            physicalExaminationBean.setbScapula("" + userValue);
                            childOptBean.setUserValue(bean.getbScapula());
                            break;
                        case "胸腰椎":
//                            physicalExaminationBean.setbTLV("" + userValue);
                            childOptBean.setUserValue(bean.getbTLV());
                            break;
                        case "骨盆":
//                            physicalExaminationBean.setbPelvis("" + userValue);
                            childOptBean.setUserValue(bean.getbPelvis());
                            break;
                        case "髋关节":
//                            physicalExaminationBean.setbHipJ("" + userValue);
                            childOptBean.setUserValue(bean.getbHipJ());
                            break;
                        case "膝关节":
//                            physicalExaminationBean.setbKnee("" + userValue);
                            childOptBean.setUserValue(bean.getbKnee());
                            break;
                        case "足部":
//                            physicalExaminationBean.setBfoot("" + userValue);
                            childOptBean.setUserValue(bean.getBfoot());
                            break;
                    }
                }
            }

            //选填字段
            if (parentQuestionBean.getTitle().equals("健身处方")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    String qustion = childOptBean.getQustion();
                    if ("(多选)健身目标".equals(qustion)) {
                        List<MultiOptBean> multiOptBeans = childOptBean.getMultiOptBeans();

                        String stitchingSymbol =String.format("%c", 1);//拼接符号
                        String bodyTargetSelect = bean.getBodyTargetSelect();
                        String[] split1 = bodyTargetSelect.split(stitchingSymbol);
                        if (split1!=null&&split1.length!=0){
                            for (int i = 0; i < split1.length; i++) {
                                if ("减肥".equals(split1[i])){

                                    for (int j = 0; j <multiOptBeans.size() ; j++) {
                                        if ("减肥".equals(multiOptBeans.get(j).getOptName())){
                                            multiOptBeans.get(j).setIsSelected(true);
                                        }
                                    }

                                }else if ("增加肌肉".equals(split1[i])){
                                    for (int j = 0; j <multiOptBeans.size() ; j++) {
                                        if ("增加肌肉".equals(multiOptBeans.get(j).getOptName())){
                                            multiOptBeans.get(j).setIsSelected(true);
                                        }
                                    }
                                }else if ("改善线条".equals(split1[i])){
                                    for (int j = 0; j <multiOptBeans.size() ; j++) {
                                        if ("改善线条".equals(multiOptBeans.get(j).getOptName())){
                                            multiOptBeans.get(j).setIsSelected(true);
                                        }
                                    }
                                }else {
                                    for (int j = 0; j <multiOptBeans.size() ; j++) {
//                                        if ("其他".equals(multiOptBeans.get(j).getOptName())){
//                                            multiOptBeans.get(j).setIsSelected(true);
//                                        }
                                        multiOptBeans.get(3).setIsSelected(true);
                                        multiOptBeans.get(3).setUserValue(split1[i]);

                                    }
                                }
                            }

                        }


                    } else if ("健身次数(每周)".equals(qustion)) {
                        Integer bodyTimesSelect = bean.getBodyTimesSelect();
                        if (bodyTimesSelect!=null&&bodyTimesSelect!=0){
                            childOptBean.setUserValue(bodyTimesSelect.toString());
                        }

                    } else if ("心肺运动(选填)".equals(qustion)) {
                        if (!TextUtils.isEmpty(bean.getBeartLungMovement()))
                        childOptBean.setUserValue(bean.getBeartLungMovement());


                    } else if ("伸展运动(选填)".equals(qustion)) {
                        if (!TextUtils.isEmpty(bean.getExtensionalMovement()))
                            childOptBean.setUserValue(bean.getExtensionalMovement());

                    } else if ("重量训练(选填)".equals(qustion) ) {

                        if (!TextUtils.isEmpty(bean.getWeightTraining()))
                            childOptBean.setUserValue(bean.getWeightTraining());
                    }

                }

            }
        }
        demoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRejected() {

    }

    @Override
    public void showCompletePercent(double persent) {

    }

    @Override
    public void cancelReason(String reason) {
        Log.e(TAG, "cancelReason: "+reason );
    }

}

