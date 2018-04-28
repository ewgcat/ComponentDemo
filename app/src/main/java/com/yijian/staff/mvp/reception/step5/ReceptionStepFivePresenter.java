package com.yijian.staff.mvp.reception.step5;

import android.content.Context;

import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/23.
 */

public class ReceptionStepFivePresenter implements ReceptionStepFiveContract.Presenter{
    private Context context;
    private ReceptionStepFiveContract.View view;
    public ReceptionStepFivePresenter(Context context) {
            this.context=context;
    }

    public void setView(ReceptionStepFiveContract.View view){
        this.view=view;
    }

    @Override
    public void getStatus(boolean isFirst, String memberId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STATUS,params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ReceptionStastuBean receptionStastuBean = GsonNullString.getGson().fromJson(result.toString(), ReceptionStastuBean.class);
                if (receptionStastuBean==null||receptionStastuBean.getOperatorType()==null)return;

                if (receptionStastuBean.getOperatorType()==51){
                    view.showStatus(receptionStastuBean);
                }else {
                    if (isFirst) view.needEndProcess();
                }

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void endProcess(String memberId) {
        Map<String ,String> params=new HashMap<>();
        params.put("memberId",memberId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_STEP5_END, params, new ResultNullObserver() {
            @Override
            public void onSuccess(Object result) {
                view.ShowEndProcess();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
