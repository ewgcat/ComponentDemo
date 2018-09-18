package com.yijian.staff.jpush;

import android.arch.lifecycle.Lifecycle;

import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijan.commonlib.net.response.ResponseObserver;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/6/28 15:26:41
 */
public class ClearRedPointUtil {


   public static void clearBusinessNotice(Lifecycle lifecycle) {
        HashMap<String,String> map=new HashMap<>();
        map.put("moduleCode","app_business_message");
        HttpManager.postHasHeaderHasParam(HttpManager.CLEAR_RED_POINT_URL,map, new ResponseObserver<JSONArray>(lifecycle) {
            @Override
            public void onSuccess(JSONArray result) {
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public static void clearYueKeNotice(Lifecycle lifecycle) {
        HashMap<String,String> map=new HashMap<>();
        map.put("moduleCode","app_course_appoint_info");
        HttpManager.postHasHeaderHasParam(HttpManager.CLEAR_RED_POINT_URL,map, new ResponseObserver<JSONArray>(lifecycle) {
            @Override
            public void onSuccess(JSONArray result) {
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    public static void clearJieDaiNotice(Lifecycle lifecycle) {
        HashMap<String,String> map=new HashMap<>();
        map.put("moduleCode","app_reception");
        HttpManager.postHasHeaderHasParam(HttpManager.CLEAR_RED_POINT_URL, map, new ResponseObserver<JSONArray>(lifecycle) {
            @Override
            public void onSuccess(JSONArray result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
