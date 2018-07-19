package com.yijian.staff.mvp.reception;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.bean.ReceptionStastuBean;
import com.yijian.staff.bean.RecptionRecordListBean;
import com.yijian.staff.bean.RecptionerInfoBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.util.GsonNullString;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


        HttpManager.getHasHeaderNoParam(HttpManager.RECEPTION_INFO, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
//                Log.e(TAG, "onSuccess: "+result.toString() );
                RecptionerInfoBean receptionInfo = GsonNullString.getGson().fromJson(result.toString(), RecptionerInfoBean.class);
                if (receptionInfo == null) return;
                view.showRecptionInfo(receptionInfo);
                //

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int pageNum = 1;//默认页码

    @Override
    public void getRecptionRecord(boolean isRefresh) {

        if (isRefresh) pageNum = 1;

        Map<String, Integer> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", 10);


        HttpManager.postHasHeaderHasParamOfInteger(HttpManager.RECEPTION_RECORD, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
//                Log.e(TAG, "onSuccess: "+result.toString() );
                view.finishRefresh(isRefresh);
                RecptionRecordListBean recptionRecordListBean = new Gson().fromJson(result.toString(), RecptionRecordListBean.class);
                if (recptionRecordListBean == null) {
                    Toast.makeText(context, "数据异常", Toast.LENGTH_SHORT).show();

                    return;
                }

                List<RecptionRecordListBean.RecordsBean> records = recptionRecordListBean.getRecords();
                if (records == null || records.size() == 0) {
                    if (pageNum != 1) Toast.makeText(context, "已经是最后一页了", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e(TAG, "onSuccess: " + records.size());
                view.showRecptionRecordList(records, isRefresh);
                pageNum++;
            }

            @Override
            public void onFail(String msg) {
//                Log.e(TAG, "msg: "+msg );
                view.finishRefresh(isRefresh);
            }
        });
    }

    @Override
    public void getRecptionStatus(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", id);

        HttpManager.getHasHeaderHasParam(HttpManager.RECEPTION_STATUS, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                ReceptionStastuBean receptionStastuBean = GsonNullString.getGson().fromJson(result.toString(), ReceptionStastuBean.class);
                if (receptionStastuBean == null) return;
                view.showStatus(receptionStastuBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void endRecption(String memberId) {
        Map<String, String> params = new HashMap<>();
        params.put("memberId", memberId);
        HttpManager.postHasHeaderHasParam(HttpManager.RECEPTION_END, params, new ResultNullObserver() {

            @Override
            public void onSuccess(Object result) {
                view.showEndRecption();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


}