package com.yijian.staff.mvp.reception.step3.leader;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
 * Created by The_P on 2018/4/19.
 */

public class LeaderProductPresenter implements LeaderProductContract.Presenter {
    private Context context;
    private LeaderProductContract.View view;

    public LeaderProductPresenter(Context context) {
        this.context = context;
    }

    public void setView(LeaderProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getUserInfo(String memberId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);

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
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void getProductDetail(String memberId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STEP3_PRODUCT_DETAIL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ProductDetail productDetail = GsonNullString.getGson().fromJson(result.toString(), ProductDetail.class);
                if (productDetail == null) return;
                view.showProductDetail(productDetail);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT);
            }
        });
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
}
