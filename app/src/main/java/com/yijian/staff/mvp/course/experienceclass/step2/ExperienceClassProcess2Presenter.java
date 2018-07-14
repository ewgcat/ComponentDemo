package com.yijian.staff.mvp.course.experienceclass.step2;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yijian.staff.mvp.course.experienceclass.step2.bean.AccessRecordBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.GsonNullString;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by The_P on 2018/4/16.
 */

public class ExperienceClassProcess2Presenter implements ExperienceClassProcess2Contract.Presenter {

    private Context context;
    private ExperienceClassProcess2Contract.View view;

    public ExperienceClassProcess2Presenter(Context context) {
        this.context = context;
    }

    public void setView(ExperienceClassProcess2Contract.View view) {
        this.view = view;
    }

    @Override
    public void getAccessRecord(String processId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("processId", processId);
        HttpManager.getHasHeaderHasParam(HttpManager.GET_EXPERICECE_HUI_FANG_URL, map, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {


//                AccessRecordBean accessRecordBean = new Gson().fromJson(result.toString(), AccessRecordBean.class);
                AccessRecordBean accessRecordBean = GsonNullString.getGson().fromJson(result.toString(), AccessRecordBean.class);
                if (accessRecordBean == null) return;

                view.showAccessRecord(accessRecordBean);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void postCoachAccessRecord(AccessRecordBean bean) {
        HttpManager.postExperienceAccessRecord(HttpManager.POST_EXPERICECE_HUI_FANG_URL_SAVE, bean, new Observer<JSONObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                try {
                    int code = jsonObject.getInt("code");
                    if (code == 0) {
                        view.showSavaSucceed();
                    } else {
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
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
