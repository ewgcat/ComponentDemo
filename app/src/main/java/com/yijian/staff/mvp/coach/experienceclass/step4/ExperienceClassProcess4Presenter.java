package com.yijian.staff.mvp.coach.experienceclass.step4;

import android.content.Context;
import android.widget.Toast;

import com.yijian.staff.mvp.coach.experienceclass.step4.bean.InvitationAgainBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/25.
 */

public class ExperienceClassProcess4Presenter implements ExperienceClassProcess4Contract.Presenter {

    private Context context;
    private ExperienceClassProcess4Contract.View view;

    public ExperienceClassProcess4Presenter(Context context) {
        this.context=context;
    }

    public void setView(ExperienceClassProcess4Contract.View view){
        this.view=view;
    }

    @Override
    public void getClassRecordList(String processId) {
        Map<String,String> params=new HashMap<>();
        params.put("processId",processId);

        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_INVITE_AGAIN_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                InvitationAgainBean invitationAgainBean = GsonNullString.getGson().fromJson(result.toString(), InvitationAgainBean.class);
                if (invitationAgainBean==null)return;
                view.showClassRecordList(invitationAgainBean);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
