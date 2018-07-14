package com.yijian.staff.mvp.course.experienceclass.step1;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.mvp.course.experienceclass.step1.bean.InviterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by The_P on 2018/4/14.
 */

public class ExperienceClassStep1Presenter implements ExperienceClassStep1Contract.Presenter {

    private Context context;
    private ExperienceClassStep1Contract.View view;

    public ExperienceClassStep1Presenter(Context context) {
        this.context = context;
    }

    public void setView(ExperienceClassStep1Contract.View view) {
        this.view = view;
    }

    @Override
    public void getInviterInfo(String processId) {
        HashMap<String, String> map = new HashMap<>();
//        processId="1";
        map.put("processId", processId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_INVITE_HISTORY_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Gson gson = GsonNullString.getGson();
                InviterBean inviterBean = gson.fromJson(result.toString(), InviterBean.class);

                if (inviterBean == null) return;
                view.showInviterInfo(inviterBean);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
