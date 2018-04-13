package com.yijian.staff.mvp.reception.step2;

import android.content.Context;
import android.widget.Toast;

import com.yijian.staff.net.httpmanager.HttpManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by The_P on 2018/4/4.
 */

public class KeFuReceptionStepTwoPresenter implements KeFuReceptionStepTwoContract.Presenter {

    private static final String TAG = "KeFuReceptionStepTwoPre";
    private Context context;
    private KeFuReceptionStepTwoContract.View view;

    public KeFuReceptionStepTwoPresenter(Context context) {
        this.context=context;
    }

    public void setView(KeFuReceptionStepTwoContract.View view){
        this.view=view;
    }

    @Override
    public void jumpBodyCheck(String memberId) {
        Map<String,String> params=new HashMap<>();
        memberId="076c3096caf04559b9abe112542a9cd0";
        params.put("memberId", memberId);

        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP2_JUMP, params, new Observer<JSONObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int code = jsonObject.getInt("code");

                    if (code==0){
                        view.showJumpBodyCheck();
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

    @Override
    public void coachBodyCheck(String memberId) {
        Map<String,String> params=new HashMap<>();
        memberId="076c3096caf04559b9abe112542a9cd0";
        params.put("memberId", memberId);

        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP2_TOCOACH, params, new Observer<JSONObject>(){

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int code = jsonObject.getInt("code");

                    if (code==0){
                        view.showCoachBodyCheck();
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
