package com.yijian.staff.mvp.coach.experienceclass.step3;

import android.content.Context;
import android.widget.Toast;

import com.yijian.staff.mvp.coach.experienceclass.step3.bean.ConsultationProgrammeBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by The_P on 2018/4/16.
 */

public class ExperienceClassProcess3Presenter implements ExperienceClassProcess3Contract.Presenter {
    private Context context;
    private ExperienceClassProcess3Contract.View view;
    public ExperienceClassProcess3Presenter(Context context) {
            this.context=context;
    }

    public void setView( ExperienceClassProcess3Contract.View  view){
        this.view=view;
    }

    @Override
    public void getConsultationProgramme(String processId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("processId", processId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_HUI_SHANG_FANG_AN_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ConsultationProgrammeBean consultationProgrammeBean = GsonNullString.getGson().fromJson(result.toString(), ConsultationProgrammeBean.class);
                view.showConsultationProgramme(consultationProgrammeBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void postConsultationProgramme(String processId, String programmeContext) {
        HashMap<String, String> map = new HashMap<>();
        map.put("processId", processId);
        map.put("programmeContext", programmeContext);
//        GET_EXPERICECE_HUI_SHANG_FANG_AN_URL_SAVE
        HttpManager.postHasHeaderHasParam(HttpManager.GET_EXPERICECE_HUI_SHANG_FANG_AN_URL_SAVE, map, new Observer<JSONObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int   code = jsonObject.getInt("code");
                    if (code==0){
                            view.showSaveSecceed();
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


}
