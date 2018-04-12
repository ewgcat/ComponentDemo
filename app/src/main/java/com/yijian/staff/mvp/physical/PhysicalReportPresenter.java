package com.yijian.staff.mvp.physical;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/4.
 */

public class PhysicalReportPresenter implements PhysicalReportConstract.Presenter{
    private final User user;
//    private final HashMap<String, String> head;
    private Context context;
    private PhysicalReportConstract.View view;

    public PhysicalReportPresenter(Context context) {
        this.context=context;

        user = DBManager.getInstance().queryUser();
//        head = new HashMap<>();
//        head.put("token", user.getToken());
    }

    public void setView(PhysicalReportConstract.View activity){
        this.view=activity;
    }

    @Override
    public void loadData(String memberId) {
        Map<String,String> params=new HashMap<>();
        params.put("shopId",user.getShopId());
        memberId="076c3096caf04559b9abe112542a9cd0";
        params.put("memberId", memberId);

        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_TEST_VIEW, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
//                Log.e(TAG, "onSuccess: "+result.toString() );
                PhysicalExaminationBean o = new Gson().fromJson(result.toString(), PhysicalExaminationBean.class);
                view.showUserData(o);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
