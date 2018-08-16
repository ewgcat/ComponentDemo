package com.yijian.staff.mvp.course.experienceclass.step5.coach;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.widget.Toast;

import com.yijian.staff.mvp.course.experienceclass.step5.bean.ConsultationConclusionBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/5/4.
 */

public class ExperienceClassStep5CoachPresenter implements ExperienceClassStep5CoachContract.Presenter {

    private Context context;
    private Lifecycle lifecycle;
    private ExperienceClassStep5CoachContract.View view;

    public ExperienceClassStep5CoachPresenter(Lifecycle lifecycle,Context context) {
        this.context = context;
        this.lifecycle = lifecycle;
    }

    public void setView(ExperienceClassStep5CoachContract.View view) {
        this.view = view;
    }


    @Override
    public void getConsultationConclusion(String processId) {
        Map<String, String> params = new HashMap<>();
        params.put("processId", processId);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_HUI_SHANG_RESULT_URL, params, new ResultJSONObjectObserver(lifecycle) {
            @Override
            public void onSuccess(JSONObject result) {
                ConsultationConclusionBean bean = GsonNullString.getGson().fromJson(result.toString(), ConsultationConclusionBean.class);
                if (bean == null) return;
                view.showConclusion(bean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
