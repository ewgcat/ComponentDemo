package com.yijian.commonlib.net.response;


import android.arch.lifecycle.Lifecycle;

import org.json.JSONObject;


public abstract class ResultStringObserver extends ResponseObserver<String> {

    public ResultStringObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    protected void initResultType() {
        dataClassType = String.class;
    }

    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getString("data"));
    }
}