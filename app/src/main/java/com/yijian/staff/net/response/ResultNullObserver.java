package com.yijian.staff.net.response;


import org.json.JSONException;
import org.json.JSONObject;


public abstract class ResultNullObserver extends ResponseObserver<Object> {

    public ResultNullObserver() {
        super();
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