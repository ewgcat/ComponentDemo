package com.yijian.staff.mvp.reception;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;


/**
 * Created by The_P on 2018/3/28.
 */

public class ReceptionPresenter implements ReceptionContract.Presenter {

    private static final String TAG = "ReceptionPresenter";


    private Context context;
    private final User user;
    private ReceptionContract.View view;
    private final HashMap<String, String> headerParam;

    public void setView(ReceptionContract.View view) {
        this.view = view;
    }

    public ReceptionPresenter(Context context) {
        this.context = context;

        user = DBManager.getInstance().queryUser();
        headerParam = new HashMap<>();
        headerParam.put("token", user.getToken());
    }


    /**
     * 获取接待人信息
     */
    @Override
    public void getRecptionerInfo() {


        HttpManager.getHasHeaderNoParam(HttpManager.RECEPTION_INFO, headerParam, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
//                Log.e(TAG, "onSuccess: "+result.toString() );
                RecptionerInfoBean receptionInfo = new Gson().fromJson(result.toString(), RecptionerInfoBean.class);
                if (receptionInfo == null) return;
                view.showRecptionInfo(receptionInfo);

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int pageNum=1;//默认页码
    @Override
    public void getRecptionRecord(boolean isRefresh) {

        Map<String, String> params = new HashMap<>();
        params.put("pageNum",String.valueOf(pageNum));
        params.put("pageSize","10");

        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_RECORD, headerParam, params, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.e(TAG, "onSuccess: "+result.toString() );
            }

            @Override
            public void onFail(String msg) {
                Log.e(TAG, "msg: "+msg );
            }
        });
    }

}