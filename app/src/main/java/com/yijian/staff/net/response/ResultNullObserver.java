package com.yijian.staff.net.response;


import android.arch.lifecycle.Lifecycle;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class ResultNullObserver extends ResponseObserver<Object> {

    public ResultNullObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }


    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.get("data"));
    }
}