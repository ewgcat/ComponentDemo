package com.yijian.staff.net.response;

import android.arch.lifecycle.Lifecycle;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ResultJSONIntegerObserver extends ResponseObserver<Integer> {

    public ResultJSONIntegerObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }



    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getInt("data"));
    }
}