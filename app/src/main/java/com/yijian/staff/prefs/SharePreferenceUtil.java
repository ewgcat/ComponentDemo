package com.yijian.staff.prefs;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.yijian.staff.util.ApplicationHolder;


public class SharePreferenceUtil {
    private static final String DEFAULT_CATEGORY = "default";
    private static final String ALL_FUNCTION_ACTIVITY_SHOW_EDIT_ICON = "all_function_activity_show_edit_icon";
    private static final String USER_ROLE = "USER_ROLE";

    public static final String KEY_JPUSH_REGISTRATION_ID = "jpush_registration_id";
    public static final String KEY_JPUSH_CAN_PUSH = "jpush_can_push";
    public static final String KEY_JPUSH_ALIAS = "jpush_alias";
    public static final String KEY_HAS_JPUSH_ALIAS = "has_jpush_alias";
    private static final String KEY_USER_ID = "user_id";
    private static String KEY_HUIJI_HAS_TO_COACH = "huiji_has_to_coach";
    private static String KEY_HAS_NEW_JIEDAI_PUSH="has_new_jiedai_push";
    private static String KEY_HAS_NEW_YUE_KE_PUSH="has_new_yue_ke_push";
    private static String KEY_HAS_NEW_SELL_BUSINESS_PUSH="has_new_sell_business_push";
    private static String KEY_HAS_NEW_COURSE_BUSINESS_PUSH="has_new_course_business_push";


    public static void setShowEditIcon(boolean b) {
        setBoolean(ALL_FUNCTION_ACTIVITY_SHOW_EDIT_ICON, b);
    }

    public static boolean getShowEditIcon() {
        return getBoolean(ALL_FUNCTION_ACTIVITY_SHOW_EDIT_ICON, false);
    }

    public static boolean setUserRole(int role) {
        return setInt(USER_ROLE, role);
    }

    public static int getUserRole() {
        return getInt(USER_ROLE);
    }


    public static void setJpushRegistionId(String jpushRegistrationId) {
        setString(SharePreferenceUtil.KEY_JPUSH_REGISTRATION_ID, jpushRegistrationId);
    }

    public static String getJpushRegistionId() {
        return getString(SharePreferenceUtil.KEY_JPUSH_REGISTRATION_ID, "");
    }

    public static boolean getCanPush() {
        return getBoolean(KEY_JPUSH_CAN_PUSH, false);
    }

    public static void setCanPush(boolean canPush) {
        setBoolean(KEY_JPUSH_CAN_PUSH, canPush);
    }

    public static void setJpushAlias(String alias) {
        setString(KEY_JPUSH_ALIAS, alias);
        setBoolean(KEY_HAS_JPUSH_ALIAS, true);
    }

    public static String getJpushAlias(String alias) {
        return getString(KEY_JPUSH_ALIAS, "");
    }

    public static boolean hasJpushAlias() {
        return getBoolean(KEY_HAS_JPUSH_ALIAS, false);
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


    private static int getInt(String key) {
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

    private static boolean setInt(String key, int value) {
        return setInt(DEFAULT_CATEGORY, key, value);
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


    public static void setUserId(String userId) {
        setString(KEY_USER_ID, userId);
    }

    public static String getUserId() {
        return getString(KEY_USER_ID, "");


    }

    public static void setHasToScan(boolean b) {
        setBoolean(KEY_HUIJI_HAS_TO_COACH, b);
    }

    public static boolean getHasToScan() {
        return getBoolean(KEY_HUIJI_HAS_TO_COACH, false);
    }

    public static void setUserName(String userName) {
        setString("username", userName);
    }

    public static String getUserName() {
        return getString("username", "");
    }


    public static boolean hasNewJiedaiPush() {
        return getBoolean(KEY_HAS_NEW_JIEDAI_PUSH, false);
    }

    public static void setHasNewJiedaiPush(boolean b) {
        setBoolean(KEY_HAS_NEW_JIEDAI_PUSH, b);
    }


    public static boolean hasNewYueKePush() {
        return getBoolean(KEY_HAS_NEW_YUE_KE_PUSH, false);
    }

    public static void setHasNewYueKePush(boolean b) {
        setBoolean(KEY_HAS_NEW_YUE_KE_PUSH, b);
    }


    public static boolean hasNewSellBusinessPush() {
        return getBoolean(KEY_HAS_NEW_SELL_BUSINESS_PUSH, false);
    }

    public static void setHasNewSellBusinessPush(boolean b) {
        setBoolean(KEY_HAS_NEW_SELL_BUSINESS_PUSH, b);
    }

    public static boolean hasNewCourseBusinessPush() {
        return getBoolean(KEY_HAS_NEW_COURSE_BUSINESS_PUSH, false);
    }

    public static void setHasNewCourseBusinessPush(boolean b) {
        setBoolean(KEY_HAS_NEW_COURSE_BUSINESS_PUSH, b);
    }


}
