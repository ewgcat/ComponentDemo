package com.yijian.staff.mvp.reception.step3.coach;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.mvp.reception.step3.coach.bean.ReceptionUserInfo;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/12.
 */


public class CoachProductPresenter implements  CoachProductContract.Presenter{
    private static final String TAG = "CoachProductPresenter";
    private Context context;
    private CoachProductContract.View view;
    public CoachProductPresenter(Context context) {
        this.context=context;
    }

    public void setView(CoachProductContract.View view){
        this.view=view;
    }

    @Override
    public void getUserInfo(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("memberId",memberId);

        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STEP3_COACH_USERDATA, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ReceptionUserInfo receptionUserInfo = null;
                try {
                    receptionUserInfo = new Gson().fromJson(result.toString(), ReceptionUserInfo.class);

                    view.showUserInfo(receptionUserInfo);


                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
//                Log.e(TAG, "onSuccess: "+receptionUserInfo.toString() );


            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void getProductDetail(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STEP3_PRODUCT_DETAIL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ProductDetail productDetail = GsonNullString.getGson().fromJson(result.toString(), ProductDetail.class);
                if (productDetail==null)return;
                view.showProductDetail(productDetail);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void postToLeader(String memberId, String reason, Integer postId) {
        Map<String,Object> params=new HashMap<>();
        params.put("memberId",memberId);
        params.put("reason",reason);
        params.put("postId",postId);

        HttpManager.postHasHeaderHasParamOfObject(HttpManager.RECEPTION_STEP3_TO_LEADERS, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                Toast.makeText(context,"T.O给领导成功",Toast.LENGTH_SHORT).show();
                view.showToLeaderSucceed();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void coachToSale(String memberId, String cardId) {
        Map<String,String> params=new HashMap<>();
        params.put("memberId",memberId);
        params.put("cardId",cardId);
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
