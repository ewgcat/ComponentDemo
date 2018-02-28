package com.yijian.staff.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.yijian.staff.constant.Constants;
import com.yijian.staff.application.CustomApplication;

import javax.inject.Inject;

public class ImplPreferencesHelper implements PreferencesHelper {



    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = CustomApplication.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }




}
