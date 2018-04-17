package com.yijian.staff.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by The_P on 2018/4/16.
 */

public class GsonNullString {
    private static Gson instance = null;
    private GsonNullString() {

    }

    public static Gson getGson(){

        if (null == instance){
            instance  = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
        }
        return instance;
    }


}
