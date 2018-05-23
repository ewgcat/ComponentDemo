package com.yijian.staff.net.response;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ResultJSONIntegerObserver extends ResponseObserver<Integer> {

    public ResultJSONIntegerObserver() {
        super();
    }

    @Override
    protected void initResultType() {
        dataClassType = JSONArray.class;
    }

    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getInt("data"));
    }
}