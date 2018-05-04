package com.yijian.staff.mvp.reception.physical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.step3.bean.ExperienceClassProcess3Bean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.QustionBean;
import com.yijian.staff.widget.NavigationBar2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

//体测报告
public class PhysicalReportActivity extends AppCompatActivity implements PhysicalReportConstract.View {
    private static final String TAG = "PhysicalReportActivity";
    private List<ParentQuestionBean> parentObj;
    private RecyclerView recyclerView;
    private TextView tvName;
    private TextView tvHeight;
    private TextView tvAge;
    private PhysicalReportAdapter demoAdapter;
    private List<ParentQuestionBean> list;
    private String memberId;
    private String memberName;
    private ExperienceClassProcess3Bean.BodyCheckBean bodyCheckBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_report);
        ButterKnife.bind(this);

        initView();

//        //从体验课会商方案传过来的
//        RxBus.getDefault().toDefaultFlowable(ExperienceClassProcess3Bean.BodyCheckBean.class, new Consumer<ExperienceClassProcess3Bean.BodyCheckBean>() {
//            @Override
//            public void accept(ExperienceClassProcess3Bean.BodyCheckBean bodyCheckBean) throws Exception {
//                setBodyCheckBean(bodyCheckBean);
//            }
//        });

        Intent intent = getIntent();

        if (intent.hasExtra("memberId") || intent.hasExtra("memberName")) {
            memberId = intent.getStringExtra("memberId");
            memberName = intent.getStringExtra("memberName");
            tvName.setText("" + memberName);
        } else {
            Toast.makeText(PhysicalReportActivity.this, "用户信息获取失败", Toast.LENGTH_SHORT).show();
            return;
        }
        PhysicalReportPresenter physicalReportPresenter = new PhysicalReportPresenter(this);
        physicalReportPresenter.setView(this);
        physicalReportPresenter.loadData(memberId);
        initData();
    }

//    private void setBodyCheckBean(ExperienceClassProcess3Bean.BodyCheckBean bodyCheckBean) {
//        this.bodyCheckBean = bodyCheckBean;
//    }


    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.physical_report_navigation_bar);
        navigationBar2.setTitle("查看体测数据");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        RelativeLayout rlName = findViewById(R.id.rl_name);
        RelativeLayout rlHeight = findViewById(R.id.rl_height);
        RelativeLayout rlAge = findViewById(R.id.rl_age);
        tvName = findViewById(R.id.tv_name);


        tvHeight = findViewById(R.id.tv_height);
        tvAge = findViewById(R.id.tv_age);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        demoAdapter = new PhysicalReportAdapter(list, this);
        recyclerView.setAdapter(demoAdapter);

    }

    private void initData() {
        QustionBean bean = new Gson().fromJson(JsonStringData.metaData, QustionBean.class);
        parentObj = bean.getParentObj();
        demoAdapter.resetData(parentObj);
    }


    @Override
    public void showUserData(PhysicalExaminationBean bean) {
        Integer age = bean.getAge();
        if (age!=null&&age>0){
            tvAge.setText(String.valueOf(age));
        }
        BigDecimal height = bean.getHeight();
        if (height!=null&&height.intValue()>0){
            tvHeight.setText(height.intValue()+"cm");
        }

//        Log.e(TAG, "showUserData: "+bean.toString());

        for (ParentQuestionBean parentQuestionBean : parentObj) {
            //必填字段
            if (parentQuestionBean.getTitle().equals("人体成分分析")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    switch (childOptBean.getQustion()) {
                        case "体重(kg)":

                            BigDecimal weight = bean.getWeight();
                            if (weight != null && !TextUtils.isEmpty(weight.toString()))
                                childOptBean.setUserValue(weight.toString());
                            break;
                        case "骨骼肌(kg)":
//                            physicalExaminationBean.setSkeletalMuscle(new BigDecimal(userValue));
                            BigDecimal skeletalMuscle = bean.getSkeletalMuscle();
                            if (skeletalMuscle != null && !TextUtils.isEmpty(skeletalMuscle.toString()))
                                childOptBean.setUserValue(skeletalMuscle.toString());
                            break;
                        case "体脂肪(kg)":
//                            physicalExaminationBean.setFat(new BigDecimal(userValue));
                            BigDecimal fat = bean.getFat();
                            if (fat != null && !TextUtils.isEmpty(fat.toString()))
                                childOptBean.setUserValue(fat.toString());
                            break;

                        case "身体水份含量(kg)":
//                            physicalExaminationBean.setWater(new BigDecimal(userValue));
                            BigDecimal water = bean.getWater();
                            if (water != null && !TextUtils.isEmpty(water.toString()))
                                childOptBean.setUserValue(water.toString());
                            break;
                        case "去脂体重(kg)":
//                            physicalExaminationBean.setWeightNoFat(new BigDecimal(userValue));
                            BigDecimal weightNoFat = bean.getWeightNoFat();
                            if (weightNoFat != null && !TextUtils.isEmpty(weightNoFat.toString()))
                                childOptBean.setUserValue(weightNoFat.toString());
                            break;
                        case "身体质量指数BMI(kg/m2)":
//                            physicalExaminationBean.setBmi(new BigDecimal(userValue));
                            BigDecimal bmi = bean.getBmi();
                            if (bmi != null && !TextUtils.isEmpty(bmi.toString()))
                                childOptBean.setUserValue(bmi.toString());
                            break;
                        case "体脂百分比PBF(%)":
//                            physicalExaminationBean.setPbf(new BigDecimal(userValue));
                            BigDecimal pbf = bean.getPbf();
                            if (pbf != null && !TextUtils.isEmpty(pbf.toString()))
                                childOptBean.setUserValue(pbf.toString());
                            break;

                        case "腰臀比":
//                            physicalExaminationBean.setWhr(new BigDecimal(userValue));
                            BigDecimal whr = bean.getWhr();
                            if (whr != null && !TextUtils.isEmpty(whr.toString()))
                                childOptBean.setUserValue(whr.toString());
                            break;

                        case "基础代谢":
//                            physicalExaminationBean.setBasalMetabolism(new BigDecimal(userValue));
                            BigDecimal basalMetabolism = bean.getBasalMetabolism();
                            if (basalMetabolism != null && !TextUtils.isEmpty(basalMetabolism.toString()))
                                childOptBean.setUserValue(basalMetabolism.toString());
                            break;
                        case "肌肉控制(kg)":
//                            physicalExaminationBean.setMuscleController(new BigDecimal(userValue));
                            BigDecimal muscleController = bean.getMuscleController();
                            if (muscleController != null && !TextUtils.isEmpty(muscleController.toString()))
                                childOptBean.setUserValue(muscleController.toString());
                            break;

                        case "脂肪控制(kg)":
//                            physicalExaminationBean.setFatController(new BigDecimal(userValue));
                            BigDecimal fatController = bean.getFatController();
                            if (fatController != null && !TextUtils.isEmpty(fatController.toString()))
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
                            if (waist != null && !TextUtils.isEmpty(waist.toString()))
                                childOptBean.setUserValue(waist.toString());

                            break;
                        case "臀围(cm)":
//                            physicalExaminationBean.setHipline(new BigDecimal(userValue));
                            BigDecimal hipline = bean.getHipline();
                            if (hipline != null && !TextUtils.isEmpty(hipline.toString()))
                                childOptBean.setUserValue(hipline.toString());
                            break;
                        case "皮脂厚度(cm)":
//                            physicalExaminationBean.setSkinFatLand(new BigDecimal(userValue));
                            BigDecimal skinFatLand = bean.getSkinFatLand();
                            if (skinFatLand != null && !TextUtils.isEmpty(skinFatLand.toString()))
                                childOptBean.setUserValue(skinFatLand.toString());
                            break;
                        case "左臂(cm)":
//                            physicalExaminationBean.setLeftHand(new BigDecimal(userValue));
                            BigDecimal leftHand = bean.getLeftHand();
                            if (leftHand != null && !TextUtils.isEmpty(leftHand.toString()))
                                childOptBean.setUserValue(leftHand.toString());
                            break;
                        case "右臂(cm)":
//                            physicalExaminationBean.setRightHand(new BigDecimal(userValue));
                            BigDecimal rightHand = bean.getRightHand();
                            if (rightHand != null && !TextUtils.isEmpty(rightHand.toString()))
                                childOptBean.setUserValue(rightHand.toString());
                            break;
                        case "左大腿(cm)":
//                            physicalExaminationBean.setLeftHhigh(new BigDecimal(userValue));
                            BigDecimal leftHhigh = bean.getLeftHhigh();
                            if (leftHhigh != null && !TextUtils.isEmpty(leftHhigh.toString()))
                                childOptBean.setUserValue(leftHhigh.toString());
                            break;
                        case "右大腿(cm)":
//                            physicalExaminationBean.setRightHhigh(new BigDecimal(userValue));
                            BigDecimal rightHhigh = bean.getRightHhigh();
                            if (rightHhigh != null && !TextUtils.isEmpty(rightHhigh.toString()))
                                childOptBean.setUserValue(rightHhigh.toString());
                            break;
                        case "左小腿(cm)":
//                            physicalExaminationBean.setLeftShins(new BigDecimal(userValue));
                            BigDecimal leftShins = bean.getLeftShins();
                            if (leftShins != null && !TextUtils.isEmpty(leftShins.toString()))
                                childOptBean.setUserValue(leftShins.toString());
                            break;
                        case "右小腿(cm)":
//                            physicalExaminationBean.setRightShins(new BigDecimal(userValue));
                            BigDecimal rightShins = bean.getRightShins();
                            if (rightShins != null && !TextUtils.isEmpty(rightShins.toString()))
                                childOptBean.setUserValue(rightShins.toString());
                            break;
                        case "血压(收缩压)mmHg":
//                            physicalExaminationBean.setShrinkBloodPressure(new BigDecimal(userValue));
                            BigDecimal shrinkBloodPressure = bean.getShrinkBloodPressure();
                            if (shrinkBloodPressure != null && !TextUtils.isEmpty(shrinkBloodPressure.toString()))
                                childOptBean.setUserValue(shrinkBloodPressure.toString());
                            break;
                        case "血压(舒张压)mmHg":
//                            physicalExaminationBean.setDiastoleBloodPressure(new BigDecimal(userValue));
                            BigDecimal diastoleBloodPressure = bean.getDiastoleBloodPressure();
                            if (diastoleBloodPressure != null && !TextUtils.isEmpty(diastoleBloodPressure.toString()))
                                childOptBean.setUserValue(diastoleBloodPressure.toString());
                            break;
                        case "静态心跳率(次/分钟)":
//                            physicalExaminationBean.setHeartbeat(Integer.valueOf(userValue));
                            Integer heartbeat = bean.getHeartbeat();
                            if (heartbeat != null && !TextUtils.isEmpty(heartbeat.toString()))
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

                        String stitchingSymbol = String.format("%c", 1);//拼接符号
                        String bodyTargetSelect = bean.getBodyTargetSelect();
                        if (TextUtils.isEmpty(bodyTargetSelect)) {

                            return;
                        }
                        String[] split1 = bodyTargetSelect.split(stitchingSymbol);
                        if (split1 != null && split1.length != 0) {
                            for (int i = 0; i < split1.length; i++) {
                                if ("减肥".equals(split1[i])) {

                                    for (int j = 0; j < multiOptBeans.size(); j++) {
                                        if ("减肥".equals(multiOptBeans.get(j).getOptName())) {
                                            multiOptBeans.get(j).setIsSelected(true);
                                        }
                                    }

                                } else if ("增加肌肉".equals(split1[i])) {
                                    for (int j = 0; j < multiOptBeans.size(); j++) {
                                        if ("增加肌肉".equals(multiOptBeans.get(j).getOptName())) {
                                            multiOptBeans.get(j).setIsSelected(true);
                                        }
                                    }
                                } else if ("改善线条".equals(split1[i])) {
                                    for (int j = 0; j < multiOptBeans.size(); j++) {
                                        if ("改善线条".equals(multiOptBeans.get(j).getOptName())) {
                                            multiOptBeans.get(j).setIsSelected(true);
                                        }
                                    }
                                } else {
                                    for (int j = 0; j < multiOptBeans.size(); j++) {
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
                        if (bodyTimesSelect != null && bodyTimesSelect != 0) {
                            childOptBean.setUserValue(bodyTimesSelect.toString());
                        }

                    } else if ("心肺运动(选填)".equals(qustion)) {
                        if (!TextUtils.isEmpty(bean.getBeartLungMovement()))
                            childOptBean.setUserValue(bean.getBeartLungMovement());


                    } else if ("伸展运动(选填)".equals(qustion)) {
                        if (!TextUtils.isEmpty(bean.getExtensionalMovement()))
                            childOptBean.setUserValue(bean.getExtensionalMovement());

                    } else if ("重量训练(选填)".equals(qustion)) {

                        if (!TextUtils.isEmpty(bean.getWeightTraining()))
                            childOptBean.setUserValue(bean.getWeightTraining());
                    }

                }

            }
        }
        demoAdapter.notifyDataSetChanged();
    }
}


