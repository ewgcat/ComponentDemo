package com.yijian.staff.mvp.reception.step2;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.ParentQuestionBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by The_P on 2018/4/3.
 */

public class CoachReceptionStepTwoPresenter implements CoachReceptionStepTwoContract.Presenter {
    private static final String TAG = "CoachReceptionStepTwoPr";
    private Context context;
    private final User user;

    private CoachReceptionStepTwoActivity view;

    public CoachReceptionStepTwoPresenter(Context context) {
        this.context=context;
        user = DBManager.getInstance().queryUser();
    }

    public void setView(CoachReceptionStepTwoActivity view){
        this.view=view;
    }

    @Override
    public void saveTestData(PhysicalExaminationBean bean, String memberId) {

        memberId="076c3096caf04559b9abe112542a9cd0";

        HttpManager.postRecptionTest( memberId, bean, new Observer<JSONObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int code = jsonObject.getInt("code");
                    if (code==0){
                        view.showSavaSucceed();
                    }else {
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void viewTestData(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("shopId",user.getShopId());
        memberId="076c3096caf04559b9abe112542a9cd0";
        params.put("memberId", memberId);

        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_TEST_VIEW, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
//                Log.e(TAG, "onSuccess: "+result.toString() );
                PhysicalExaminationBean o = new Gson().fromJson(result.toString(), PhysicalExaminationBean.class);
                view.showUserData(o);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void saveData(String memberId,List<ParentQuestionBean> parentObj,String height, String age) {
        PhysicalExaminationBean   physicalExaminationBean = new PhysicalExaminationBean();

        //身高，年龄 (必填字段)
//        String height = tvHeight.getText().toString();
//        String age = tvAge.getText().toString();
        if ("请选择".equals(height) || "请选择".equals(age)) {
            Toast.makeText(context, "请填写完所有必填字段", Toast.LENGTH_SHORT).show();
            return;
        } else {
            physicalExaminationBean.setAge(Integer.valueOf(age));

            physicalExaminationBean.setHeight(new BigDecimal(height));
        }


        for (ParentQuestionBean parentQuestionBean : parentObj) {
            //必填字段
            if (parentQuestionBean.getTitle().equals("人体成分分析")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    if ("请选择".equals(childOptBean.getUserValue())) {
                        Toast.makeText(context, "请填写完所有必填字段", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String userValue = childOptBean.getUserValue();
                    switch (childOptBean.getQustion()) {
                        case "体重(kg)":
                            physicalExaminationBean.setWeight(new BigDecimal(userValue));
                            break;
                        case "骨骼肌(kg)":
                            physicalExaminationBean.setSkeletalMuscle(new BigDecimal(userValue));
                            break;
                        case "体脂肪(kg)":
                            physicalExaminationBean.setFat(new BigDecimal(userValue));
                            break;

                        case "身体水份含量(kg)":
                            physicalExaminationBean.setWater(new BigDecimal(userValue));
                            break;
                        case "去脂体重(kg)":
                            physicalExaminationBean.setWeightNoFat(new BigDecimal(userValue));
                            break;
                        case "身体质量指数BMI(kg/m2)":
                            physicalExaminationBean.setBmi(new BigDecimal(userValue));
                            break;
                        case "体脂百分比PBF(%)":
                            physicalExaminationBean.setPbf(new BigDecimal(userValue));
                            break;

                        case "腰臀比":
                            physicalExaminationBean.setWhr(new BigDecimal(userValue));
                            break;

                        case "基础代谢":
                            physicalExaminationBean.setBasalMetabolism(new BigDecimal(userValue));

                            break;
                        case "肌肉控制(kg)":
                            physicalExaminationBean.setMuscleController(new BigDecimal(userValue));
                            break;

                        case "脂肪控制(kg)":
                            physicalExaminationBean.setFatController(new BigDecimal(userValue));
                            break;

                    }


                }

            }

            //必填字段
            if (parentQuestionBean.getTitle().equals("基础测量数据")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    if ("请选择".equals(childOptBean.getUserValue())) {
                        Toast.makeText(context, "请填写完所有必填字段", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String userValue = childOptBean.getUserValue();
                    switch (childOptBean.getQustion()) {
                        case "腰围(cm)":
                            physicalExaminationBean.setWaist(new BigDecimal(userValue));
                            break;
                        case "臀围(cm)":
                            physicalExaminationBean.setHipline(new BigDecimal(userValue));
                            break;
                        case "皮脂厚度(cm)":
                            physicalExaminationBean.setSkinFatLand(new BigDecimal(userValue));
                            break;
                        case "左臂(cm)":
                            physicalExaminationBean.setLeftHand(new BigDecimal(userValue));
                            break;
                        case "右臂(cm)":
                            physicalExaminationBean.setRightHand(new BigDecimal(userValue));
                            break;
                        case "左大腿(cm)":
                            physicalExaminationBean.setLeftHhigh(new BigDecimal(userValue));
                            break;
                        case "右大腿(cm)":
                            physicalExaminationBean.setRightHhigh(new BigDecimal(userValue));
                            break;
                        case "左小腿(cm)":
                            physicalExaminationBean.setLeftShins(new BigDecimal(userValue));
                            break;
                        case "右小腿(cm)":
                            physicalExaminationBean.setRightShins(new BigDecimal(userValue));
                            break;
                        case "血压(收缩压)mmHg":
                            physicalExaminationBean.setShrinkBloodPressure(new BigDecimal(userValue));
                            break;
                        case "血压(舒张压)mmHg":
                            physicalExaminationBean.setDiastoleBloodPressure(new BigDecimal(userValue));
                            break;
                        case "静态心跳率(次/分钟)":
                            physicalExaminationBean.setHeartbeat(Integer.valueOf(userValue));
                            break;
                    }
                }

            }

            //必填字段
            if (parentQuestionBean.getTitle().equals("体态评估-侧面")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    if ("请选择".equals(childOptBean.getUserValue())) {
                        Toast.makeText(context, "请填写完所有必填字段", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String userValue = childOptBean.getUserValue();
                    switch (childOptBean.getQustion()) {
                        case "头":
                            physicalExaminationBean.setsHead("" + userValue);
                            break;

                        case "颈椎":
                            physicalExaminationBean.setsCervicalV("" + userValue);
                            break;
                        case "肩胛骨":
                            physicalExaminationBean.setsScapula("" + userValue);
                            break;
                        case "胸椎":
                            physicalExaminationBean.setsThoracicV("" + userValue);
                            break;
                        case "腰椎":
                            physicalExaminationBean.setsLumbarV("" + userValue);
                            break;
                        case "骨盆":
                            physicalExaminationBean.setsPelvis("" + userValue);
                            break;
                        case "髋关节":
                            physicalExaminationBean.setsHipJ("" + userValue);
                            break;
                        case "膝关节":
                            physicalExaminationBean.setsKnee("" + userValue);
                            break;
                        case "踝关节":
                            physicalExaminationBean.setsAnkle("" + userValue);
                            break;
                        case "腓骨外髁":
                            physicalExaminationBean.setsFibula("" + userValue);
                            break;
                    }
                }
            }


            //必填字段
            if (parentQuestionBean.getTitle().equals("体态评估-背面")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    if ("请选择".equals(childOptBean.getUserValue())) {
                        Toast.makeText(context, "请填写完所有必填字段", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String userValue = childOptBean.getUserValue();
                    switch (childOptBean.getQustion()) {
                        case "头":
                            physicalExaminationBean.setbHead("" + userValue);
                            break;
                        case "肩部":
                            physicalExaminationBean.setbShoulders("" + userValue);
                            break;
                        case "肩胛骨":
                            physicalExaminationBean.setbScapula("" + userValue);
                            break;
                        case "胸腰椎":
                            physicalExaminationBean.setbTLV("" + userValue);
                            break;
                        case "骨盆":
                            physicalExaminationBean.setbPelvis("" + userValue);
                            break;
                        case "髋关节":
                            physicalExaminationBean.setbHipJ("" + userValue);
                            break;
                        case "膝关节":
                            physicalExaminationBean.setbKnee("" + userValue);
                            break;
                        case "足部":
                            physicalExaminationBean.setBfoot("" + userValue);
                            break;
                    }
                }
            }

            //选填字段
            if (parentQuestionBean.getTitle().equals("健身处方")) {
                List<ChildOptBean> childList = parentQuestionBean.getChildList();
                for (ChildOptBean childOptBean : childList) {
                    String userValue = childOptBean.getUserValue();
                    String qustion = childOptBean.getQustion();
                    if ("(多选)健身目标".equals(qustion)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        String stitchingSymbol =String.format("%c", 1);//拼接符号
                        List<MultiOptBean> multiOptBeans = childOptBean.getMultiOptBeans();
                        for (int i = 0; i < multiOptBeans.size(); i++) {
                            MultiOptBean multiOptBean = multiOptBeans.get(i);
                            if ("normal".equals(multiOptBean.getType())){
                                if (multiOptBean.isIsSelected())stringBuffer.append(stitchingSymbol).append(multiOptBean.getOptName());
                            }else if ("mix".equals(multiOptBean.getType())){
                                if (!TextUtils.isEmpty(multiOptBean.getUserValue())){
                                    stringBuffer.append(stitchingSymbol).append(multiOptBean.getOptName()).append(multiOptBean.getUserValue());
                                }else if (multiOptBean.isIsSelected()){
                                    stringBuffer.append(stitchingSymbol).append(multiOptBean.getOptName());
                                }
                            }
                        }
                        if (stringBuffer.length()!=0){
                            String substring = stringBuffer.substring(1);
                            String[] split = substring.split(stitchingSymbol);

                            for (int i = 0; i < split.length; i++) {

                                Log.e(TAG, "saveData: "+split[i] );
                            }
                            physicalExaminationBean.setBodyTargetSelect(substring);
                        }else {
                            physicalExaminationBean.setBodyTargetSelect("");
                        }

                    } else if ("健身次数(每周)".equals(qustion)) {
                        if (!"请选择".equals(userValue)){
                            physicalExaminationBean.setBodyTimesSelect(Integer.valueOf(userValue));
                        }else {
                            physicalExaminationBean.setBodyTimesSelect(0);
                        }

                    } else if ("心肺运动(选填)".equals(qustion)) {
                        if (!TextUtils.isEmpty(userValue)){
                            physicalExaminationBean.setBeartLungMovement(userValue);
                        }else {
                            physicalExaminationBean.setBeartLungMovement("");
                        }

                    } else if ("伸展运动(选填)".equals(qustion)) {
                        if (!TextUtils.isEmpty(userValue)){
                            physicalExaminationBean.setExtensionalMovement(userValue);
                        }else {
                            physicalExaminationBean.setExtensionalMovement("");
                        }


                    } else if ("重量训练(选填)".equals(qustion) ) {
                        if (!TextUtils.isEmpty(userValue)){
                            physicalExaminationBean.setWeightTraining(userValue);
                        }else {
                            physicalExaminationBean.setWeightTraining("");
                        }
                    }

                }

            }


        }

//        Log.e(TAG, "saveData: "+physicalExaminationBean.toString() );
        saveTestData(physicalExaminationBean,memberId);
    }
}
