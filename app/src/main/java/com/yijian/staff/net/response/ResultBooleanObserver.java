package com.yijian.staff.net.response;

import android.arch.lifecycle.Lifecycle;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/23 11:23:17
 */
public abstract class ResultBooleanObserver   extends ResponseObserver<Boolean> {

    public ResultBooleanObserver(Lifecycle lifecycle) {
        super(lifecycle);
    }

    @Override
    protected void initResultType() {
        dataClassType = Boolean.class;
    }


    @Override
    protected void responData(JSONObject jsonObject) throws Exception {
        onSuccess(jsonObject.getBoolean("data"));
    }
}