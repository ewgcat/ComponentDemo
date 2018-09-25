package com.yijian.commonlib.net.response;


import androidx.lifecycle.Lifecycle;

import org.json.JSONObject;


public abstract class ResultNullObserver extends ResponseObserver<Object> {

    public ResultNullObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    protected void initResultType() {
        dataClassType = Object.class;
    }

    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.get("data"));
    }
}