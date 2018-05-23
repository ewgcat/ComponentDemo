package com.yijian.staff.net.response;


import org.json.JSONObject;


public abstract class ResultJSONObjectObserver extends ResponseObserver<JSONObject> {

    public ResultJSONObjectObserver() {
        super();
    }

    @Override
    protected void initResultType() {
        dataClassType = JSONObject.class;
    }

    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getJSONObject("data"));
    }
}
