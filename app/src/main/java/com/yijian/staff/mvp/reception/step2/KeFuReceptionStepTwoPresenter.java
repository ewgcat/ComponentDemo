package com.yijian.staff.mvp.reception.step2;

import android.content.Context;
import android.util.Log;

import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/4.
 */

public class KeFuReceptionStepTwoPresenter implements KeFuReceptionStepTwoContract.Presenter {

    private static final String TAG = "KeFuReceptionStepTwoPre";
    private final User user;
    private final HashMap<String, String> head;
    private Context context;
    private KeFuReceptionStepTwoContract.View view;

    public KeFuReceptionStepTwoPresenter(Context context) {
        this.context=context;
        user = DBManager.getInstance().queryUser();
        head = new HashMap<>();
        head.put("token", user.getToken());
    }

    public void setView(KeFuReceptionStepTwoContract.View view){
        this.view=view;
    }

    @Override
    public void jumpBodyCheck() {
        Map<String,String> params=new HashMap<>();
        String userId = user.getUserId();
        userId="076c3096caf04559b9abe112542a9cd0";
        params.put("memberId", userId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP2_JUMP, head, params, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e(TAG, "onSuccess: "+result.toString());
                view.showJumpBodyCheck();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void coachBodyCheck() {
        Map<String,String> params=new HashMap<>();
        String userId = user.getUserId();
        userId="076c3096caf04559b9abe112542a9cd0";
        params.put("memberId", userId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP2_TOCOACH, head, params, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e(TAG, "onSuccess: "+result.toString());
                view.showCoachBodyCheck();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
