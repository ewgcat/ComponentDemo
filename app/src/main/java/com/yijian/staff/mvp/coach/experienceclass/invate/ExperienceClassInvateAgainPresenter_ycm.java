package com.yijian.staff.mvp.coach.experienceclass.invate;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.mvp.coach.experienceclass.invate.bean.InvateBean;
import com.yijian.staff.mvp.coach.experienceclass.step1.bean.InviterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by The_P on 2018/4/16.
 */

public class ExperienceClassInvateAgainPresenter_ycm implements ExperienceClassInvateAgainContract_ycm.Presenter{

    private Context context;
    private ExperienceClassInvateAgainContract_ycm.View view;
    public ExperienceClassInvateAgainPresenter_ycm(Context context) {
        this.context=context;
    }

    public void setView(ExperienceClassInvateAgainContract_ycm.View view){
        this.view=view;
    }


    @Override
    public void saveAndSendInvite(InvateBean bean) {

    }
}
