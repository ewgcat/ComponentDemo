package com.yijian.staff.mvp.reception.step1;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.ItemQuestionnaire;
import com.yijian.staff.mvp.reception.step1.bean.ItemsBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestionnaireAnswer;
import com.yijian.staff.mvp.reception.step1.bean.TemplateBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by The_P on 2018/3/28.
 */

public class RecptionStep1Presenter implements ReceptionStep1Contract.Presenter {
    private static final String TAG = "RecptionStep1Presenter";
    private Context context;
    private ReceptionStep1Contract.View view;

    public RecptionStep1Presenter(Context context) {
        this.context=context;
    }

    public void setView(ReceptionStep1Contract.View view){
        this.view=view;
    }

    @Override
    public void getQuestion() {
        HttpManager.getHasHeaderNoParam(HttpManager.RECEPTION_QUESTION, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                TemplateBean templateBean = new Gson().fromJson(result.toString(), TemplateBean.class);
                view.showQuestion(templateBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    //上传问卷答案
    @Override
    public void uploadQusetion(List<DataListBean> questionList, RecptionerInfoBean consumerBean, List<CalendarDay> selectedDates) {

        if (selectedDates==null||selectedDates.size()==0){
            Toast.makeText(context,"请填写计划健身时间",Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < questionList.size(); i++) {//0单选,1多选,2填空
            DataListBean dataListBean = questionList.get(i);
            if (dataListBean.getSelectType()==2){
                List<ItemsBean> childList = dataListBean.getChildList();
                for (int j = 0; j <childList.size() ; j++) {
                    String inputContent = childList.get(j).getInputContent();
                    if (TextUtils.isEmpty(inputContent)){
                        Toast.makeText(context,"请填写完所有问卷问题",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }

            if (dataListBean.getSelectType()!=2){
                boolean hasSelected=false;
                List<ItemsBean> childList = dataListBean.getChildList();
                for (int j = 0; j <childList.size() ; j++) {
                    boolean select = childList.get(j).isSelect();
                    if (select)hasSelected=true;
                }
                if (!hasSelected){
                    Toast.makeText(context,"请填写完所有问卷问题",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }


        List<QuestionnaireAnswer> questionnaireAnswerList=new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            DataListBean dataListBean = questionList.get(i);
            QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
            questionnaireAnswer.setId(""+dataListBean.getId());
            List<ItemQuestionnaire> list=new ArrayList<>();

            //填空
            if (dataListBean.getSelectType()==2){
                List<ItemsBean> childList = dataListBean.getChildList();
                for (int j = 0; j <childList.size() ; j++) {
                    ItemQuestionnaire itemQuestionnaire = new ItemQuestionnaire();
                    String inputContent = childList.get(j).getInputContent();
                    itemQuestionnaire.setInputContent(""+inputContent);
                    itemQuestionnaire.setId(""+childList.get(j).getId());
                    list.add(itemQuestionnaire);
                }
            }

            //选择（多选及单选）
            if (dataListBean.getSelectType()!=2){
                List<ItemsBean> childList = dataListBean.getChildList();
                for (int j = 0; j <childList.size() ; j++) {
                    boolean select = childList.get(j).isSelect();

                   if (select){
                       ItemQuestionnaire itemQuestionnaire = new ItemQuestionnaire();
                       itemQuestionnaire.setId(""+childList.get(j).getId());
                       itemQuestionnaire.setInputContent("");
                       list.add(itemQuestionnaire);
                   }

                }
            }

            questionnaireAnswer.setList(list);
            questionnaireAnswerList.add(questionnaireAnswer);
        }


        String id = consumerBean.getId();
        id="1";

        HttpManager.postRecptionRequstion(id, questionnaireAnswerList, new Observer<JSONObject>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int code = jsonObject.getInt("code");
                    if (code==0){
                        upLoadFitnessTime(consumerBean,selectedDates);
                        view.saveSucceed();
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



    private void upLoadFitnessTime(RecptionerInfoBean consumerBean, List<CalendarDay> selectedDates){
       StringBuilder builder=new StringBuilder();
        for (int i = 0; i < selectedDates.size(); i++) {
            Date date = selectedDates.get(i).getDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String str=sdf.format(date);
            builder.append(",").append(str);
        }

        Map<String,String> params=new HashMap<>();
        String id = consumerBean.getId();
        id="3";
        params.put("memberId",""+ id);
        String substring = builder.substring(1);
//        Log.e(TAG, "upLoadFitnessTime: "+substring );
        params.put("bodyBuildTimes",""+ substring);

        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_QUESTION_FITNESSTIME, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                view.saveSucceed();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
