package com.yijian.staff.mvp.coach.experienceclass.invate;

import android.content.Context;
import android.widget.Toast;

import com.yijian.staff.mvp.coach.experienceclass.invate.bean.InvateBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultNullObserver;

/**
 * Created by The_P on 2018/4/16.
 */

public class ExperienceClassInvateAgainPresenter_ycm implements ExperienceClassInvateAgainContract_ycm.Presenter {

    private Context context;
    private ExperienceClassInvateAgainContract_ycm.View view;

    public ExperienceClassInvateAgainPresenter_ycm(Context context) {
        this.context = context;
    }

    public void setView(ExperienceClassInvateAgainContract_ycm.View view) {
        this.view = view;
    }


    @Override
    public void saveAndSendInvite(InvateBean bean) {
        HttpManager.postInvateAgain(bean, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.showSendSucceed();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
