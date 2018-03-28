package com.yijian.staff.mvp.reception.step1;

import android.content.Context;
import android.util.Log;

import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;

/**
 * Created by The_P on 2018/3/28.
 */

public class RecptionStep1Presenter implements ReceptionStep1Contract.Presenter {
    private static final String TAG = "RecptionStep1Presenter";
    private Context context;
    private final User user;

    public RecptionStep1Presenter(Context context) {
        this.context=context;
        user = DBManager.getInstance().queryUser();
    }

    @Override
    public void getQuestion() {

        Log.e(TAG, "getQuestion: user.merchantId"+user.merchantId+"  user.shopId= "+user.shopId+" user.userId= "+user.userId );

//        HttpManager.getReceptionQuestion(user.merchantId, user.shopId, user.userId, new Observer<JSONObject>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(JSONObject jsonObject) {
////                Log.e(TAG, "onNext: jsonObject=="+jsonObject.toString() );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }
}
