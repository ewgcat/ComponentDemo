package com.yijian.commonlib.net.response;


import androidx.lifecycle.Lifecycle;

import org.json.JSONArray;
import org.json.JSONObject;


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
