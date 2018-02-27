package com.yijian.staff.net.observer;

import android.util.Log;

import com.example.commonlibrary.net.BaseObserver;
import com.yijian.staff.net.ResultCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import static junit.framework.Assert.fail;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/26 09:47:09
 */
public abstract class CommonObserver extends BaseObserver<JSONObject> implements ResultCallBack<JSONObject> {


    @Override
    public void onFail(String error) {
        fail(error);
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        try {
            int code = jsonObject.getInt("code");
            if (code==0){
                JSONObject data = jsonObject.getJSONObject("data");
                success(data);
            }else {
                String msg = jsonObject.getString("msg");
                fail(msg);
            }
        } catch (JSONException e) {
            Log.i("CommonObserver","JSONException "+e.getMessage());
        }
    }
}
