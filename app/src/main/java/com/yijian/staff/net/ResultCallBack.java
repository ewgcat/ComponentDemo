package com.yijian.staff.net;

import org.json.JSONObject;

/**
 * desc:
 *
 * date:2016/11/22
 * time:9:26
 */

public interface ResultCallBack<T> {
    void fail(String error);
    void success(JSONObject jsonObject);
}
