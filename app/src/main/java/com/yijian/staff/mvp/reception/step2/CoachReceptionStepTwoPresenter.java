package com.yijian.staff.mvp.reception.step2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.reception.step2.step2Bean.JsonStringData;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/3.
 */

public class CoachReceptionStepTwoPresenter implements CoachReceptionStepTwoContract.Presenter {
    private static final String TAG = "CoachReceptionStepTwoPr";
    private Context context;
    private final User user;

    private CoachReceptionStepTwoActivity view;
    private final Map<String, String> head;

    public CoachReceptionStepTwoPresenter(Context context) {
        this.context=context;
        user = DBManager.getInstance().queryUser();
        head = new HashMap<>();
        head.put("token",user.getToken());
    }

    public void setView(CoachReceptionStepTwoActivity view){
        this.view=view;
    }

    @Override
    public void saveTestData(PhysicalExaminationBean bean) {

        HttpManager.postRecptionTest(head, user.getUserId(), bean, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    String msg = result.getString("msg");
                    if ("success".equals(msg)){
                        view.showSavaSucceed();
                    }else {
                        Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void viewTestData() {
//        Map<String,String> params=new HashMap<>();
//        params.put("shopId",user.getShopId());
//        params.put("memberId",user.getUserId());
//
//        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_TEST_VIEW, head, params, new ResultObserver() {
//            @Override
//            public void onSuccess(JSONObject result) {
//                Log.e(TAG, "onSuccess: "+result.toString() );
//            }
//
//            @Override
//            public void onFail(String msg) {
//
//            }
//        });

        PhysicalExaminationBean o = new Gson().fromJson(JsonStringData.MockDada, PhysicalExaminationBean.class);
        view.showUserData(o);
    }
}
