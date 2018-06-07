package com.yijian.staff.net.response;


import com.google.gson.JsonObject;

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
        JSONObject jsonObj = null;
        if(jsonObject.get("data") instanceof JSONObject){
            jsonObj = jsonObject.getJSONObject("data");
        }else{
            jsonObj = new JSONObject();
        }
        onSuccess(jsonObj);
    }
}
