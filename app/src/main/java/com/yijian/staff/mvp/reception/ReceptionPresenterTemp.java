package com.yijian.staff.mvp.reception;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.bean.ReceptionListBeanTemp;
import com.yijian.staff.bean.ReceptionRecordBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by The_P on 2018/5/14.
 */

public class ReceptionPresenterTemp implements ReceptionContract.Presenter {
    private static final String TAG = "ReceptionPresenterTemp";
    private Context context;
    private ReceptionContract.View view;



    private Lifecycle lifecycle;

    public ReceptionPresenterTemp( Lifecycle lifecycle,Context context) {
        this.context = context;
        this.lifecycle = lifecycle;

    }

    public void setView(ReceptionContract.View view) {
        this.view = view;
    }

    @Override
    public void getRecptionerInfo() {

    }

    private int pageNum = 1;//默认页码

    @Override
    public void getRecptionRecord(boolean isRefresh) {
        if (isRefresh) pageNum = 1;

        Map<String, Integer> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", 10);


        HttpManager.postHasHeaderHasParamOfInteger(HttpManager.RECEPTION_RECORD_TEMP, params, new ResultJSONObjectObserver(lifecycle) {
            @Override
            public void onSuccess(JSONObject result) {
                view.finishRefresh(isRefresh);
                ReceptionListBeanTemp recptionRecordListBean = new Gson().fromJson(result.toString(), ReceptionListBeanTemp.class);
                if (recptionRecordListBean == null) {
                    Toast.makeText(context, "数据异常", Toast.LENGTH_SHORT).show();

                    return;
                }

                List<ReceptionRecordBean> records = recptionRecordListBean.getRecords();
                if (records == null || records.size() == 0) {
                    if (pageNum == 1) {
                        view.showNoData();
                    } else if (pageNum != 1) Toast.makeText(context, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    return;
                }
                view.showRecptionRecordListTemp(records, isRefresh);
                pageNum++;
            }

            @Override
            public void onFail(String msg) {
                view.finishRefresh(isRefresh);
            }
        });
    }

    @Override
    public void getRecptionStatus(String id) {

    }

    @Override
    public void endRecption(String memberId) {

    }
}
