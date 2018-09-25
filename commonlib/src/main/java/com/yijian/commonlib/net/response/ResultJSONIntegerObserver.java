package com.yijian.commonlib.net.response;

import androidx.lifecycle.Lifecycle;

import org.json.JSONObject;

public abstract class ResultJSONIntegerObserver extends ResponseObserver<Integer> {

    public ResultJSONIntegerObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    protected void initResultType() {
        dataClassType = Integer.class;
    }


    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getInt("data"));
    }
}