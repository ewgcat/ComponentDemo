package com.yijian.staff.prefs;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.yijian.staff.util.ApplicationHolder;


public class SharePreferenceUtil {
    private static final String DEFAULT_CATEGORY = "default";
    private static final String ALL_FUNCTION_ACTIVITY_SHOW_EDIT_ICON = "all_function_activity_show_edit_icon";
    private static final String USER_ROLE = "USER_ROLE";


    public static void setShowEditIcon(boolean b){
        setBoolean(ALL_FUNCTION_ACTIVITY_SHOW_EDIT_ICON,b);
    }

    public static boolean getShowEditIcon(){
        return getBoolean(ALL_FUNCTION_ACTIVITY_SHOW_EDIT_ICON,false);
    }

    public static boolean setUserRole( int role) {
        return setInt(USER_ROLE,role);
    }

    public static int getUserRole(){
        return getInt(USER_ROLE);
    }






    private static long getLong(String category, String key) {
        long result = 0;
        Application application = ApplicationHolder.getmApplication();
        if (application == null) {
            return result;
        }
        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.getLong(key, 0);
        return result;

    }

    private static boolean setLong(String category, String key, long value) {
        boolean result = false;
        Application application = ApplicationHolder.getmApplication();
        if (application == null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.edit().putLong(key, value).commit();
        return result;
    }


    private static int getInt( String key) {
        return getInt(DEFAULT_CATEGORY, key);
    }
    private static int getInt(String category, String key) {
        int result = 0;
        Application application = ApplicationHolder.getmApplication();
        if (application == null) {
            return result;
        }
        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.getInt(key, 0);
        return result;

    }

    private static boolean setInt( String key, int value) {
        return  setInt(DEFAULT_CATEGORY, key, value);
    }

    private static boolean setInt(String category, String key, int value) {
        boolean result = false;
        Application application = ApplicationHolder.getmApplication();
        if (application == null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.edit().putInt(key, value).commit();
        return result;
    }


    /**
     * 保存 String 值
     *
     * @param key   键值
     * @param value 值
     * @return
     */
    private static boolean setString(String key, String value) {
        return setString(DEFAULT_CATEGORY, key, value);
    }

    /**
     * 保存 String 值
     *
     * @param category 分类名
     * @param key      键值
     * @param value    值
     * @return
     */
    private static boolean setString(String category, String key, String value) {
        boolean result = false;
        Application application = ApplicationHolder.getmApplication();
        if (application == null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.edit().putString(key, value).commit();
        return result;
    }


    /**
     * 读取  String 值
     *
     * @param key          键值
     * @param defaultValue 默认值
     * @return
     */
    private static String getString(String key, String defaultValue) {
        return getString(DEFAULT_CATEGORY, key, defaultValue);
    }

    /**
     * 读取 String 值
     *
     * @param category     分类名
     * @param key          键值
     * @param defaultValue 默认值
     * @return
     */
    private static String getString(String category, String key, String defaultValue) {
        String result = defaultValue;
        Application application = ApplicationHolder.getmApplication();
        if (application == null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.getString(key, defaultValue);
        return result;
    }


    /**
     * 保存  Boolean 值
     *
     * @param key   键值
     * @param value 值
     * @return
     */
    private static boolean setBoolean(String key, boolean value) {
        return setBoolean(DEFAULT_CATEGORY, key, value);
    }

    /**
     * 保存  Boolean 值
     *
     * @param category 分类名
     * @param key      键值
     * @param value    值
     * @return
     */
    private static boolean setBoolean(String category, String key, boolean value) {
        boolean result = false;
        Application application = ApplicationHolder.getmApplication();
        if (application == null)
            return result;

        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.edit().putBoolean(key, value).commit();
        return result;
    }


    /**
     * 读取 Boolean 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private static Boolean getBoolean(String key, boolean defaultValue) {
        return getBoolean(DEFAULT_CATEGORY, key, defaultValue);
    }


    /**
     * 读取 Boolean 值
     *
     * @param category
     * @param key
     * @param defaultValue
     * @return
     */
    private static Boolean getBoolean(String category, String key, boolean defaultValue) {
        Boolean result = defaultValue;
        Application application = ApplicationHolder.getmApplication();
        if (application == null)
            return result;
        android.content.SharedPreferences references = application.getSharedPreferences(category, Context.MODE_PRIVATE);
        result = references.getBoolean(key, defaultValue);
        return result;
    }



}
