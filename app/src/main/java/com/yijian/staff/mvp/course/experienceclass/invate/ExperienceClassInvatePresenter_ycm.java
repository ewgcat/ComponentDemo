package com.yijian.staff.mvp.course.experienceclass.invate;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.bean.InviterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by The_P on 2018/4/16.
 */

public class ExperienceClassInvatePresenter_ycm implements ExperienceClassInvateContract_ycm.Presenter {

    private Context context;
    private ExperienceClassInvateContract_ycm.View view;

    public ExperienceClassInvatePresenter_ycm(Context context) {
        this.context = context;
    }

    public void setView(ExperienceClassInvateContract_ycm.View view) {
        this.view = view;
    }

    @Override
    public void getInviterInfo(String memberId) {
        HashMap<String, String> map = new HashMap<>();
//        memberId="666";
        map.put("memberId", memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_INVITE_HISTORY_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                InviterBean inviterBean = new Gson().fromJson(result.toString(), InviterBean.class);

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
