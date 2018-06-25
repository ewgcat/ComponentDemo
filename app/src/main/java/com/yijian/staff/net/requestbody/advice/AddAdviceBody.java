package com.yijian.staff.net.requestbody.advice;

import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 10:14:00
 */
public class AddAdviceBody {

    Advice feedBack;

    public AddAdviceBody(Advice advice) {
        this.feedBack = advice;
    }


}
