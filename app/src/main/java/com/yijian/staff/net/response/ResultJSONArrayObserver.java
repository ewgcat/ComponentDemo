package com.yijian.staff.net.response;


import android.arch.lifecycle.Lifecycle;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public abstract class ResultJSONArrayObserver extends ResponseObserver<JSONArray> {

    public ResultJSONArrayObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    protected void initResultType() {
        dataClassType = JSONArray.class;
    }

    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        JSONArray jsonArray = null;
        if(jsonObject.get("data") instanceof JSONArray){
            jsonArray = jsonObject.getJSONArray("data");
        }else{
            jsonArray = new JSONArray();
        }
        onSuccess(jsonArray);
    }
}
