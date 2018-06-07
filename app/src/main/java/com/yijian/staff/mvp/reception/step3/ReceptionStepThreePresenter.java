package com.yijian.staff.mvp.reception.step3;

import android.content.Context;

import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultNullObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/19.
 */

public class ReceptionStepThreePresenter implements ReceptionStepThreeContract.Presenter {
    private Context context;
    private ReceptionStepThreeContract.View view;

    public ReceptionStepThreePresenter(Context context) {
        this.context = context;
    }

    public void setView(ReceptionStepThreeContract.View view) {
        this.view = view;
    }

    @Override
    public void leaderToSale(String memberId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP3_LEADERTOSALE, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.leaderToSaleSecceed();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void coachToSale(String memberId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP3_COACHTOSALE, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.coachToSaleSecceed();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
