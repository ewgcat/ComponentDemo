package com.example.commonlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




public class SharePreferenceManager {
    private static final String TAG = "PreferenceManager";
    public static final String POST_TOPIC_TYPE = "POST_TOPIC_TYPE";
    private static final String DDEFAULT_PREFERENCE_NAME = "user_info";
    public static final String HEALTH_USERID="HEALTH_USERID";
    private static SharedPreferences mSharedPreferences;
    private static SharePreferenceManager mPreferencemManager;
    private static SharedPreferences.Editor editor;

    private SharePreferenceManager(Context context,String sharePreferenceName) {
        
        mSharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static synchronized void init(Context context,String sharePreferenceName) {
        if (mPreferencemManager == null) {
            mPreferencemManager = new SharePreferenceManager(context,sharePreferenceName);
        }
    }

    /**
     * 单例模式，获取instance实例
     * @param
     * @return
     */
    public synchronized static SharePreferenceManager getInstance() {
        if (mPreferencemManager == null) {
            throw new RuntimeException("please init first!");
        }
        return mPreferencemManager;
    }


    public long getUserId() {
        return mSharedPreferences.getLong("userId", 0);
    }

    public void setUserId(long userId) {
        editor.putLong("userId", userId).commit();
    }



    /**
     * 提取储存数据对象
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getObjectFromShare(@NonNull String key) {
        try {
            String payCityMapBase64 = mSharedPreferences.getString(key, "");
            if (payCityMapBase64.length() == 0) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(payCityMapBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储对象到sp公共方法,注：若要使用这个方法bean类请务必继承 Serializable接口进行数列化 add by ssy
     * 2014.09.07
     *
     * @param key
     * @param t
     * @return
     */
    public <T> boolean saveObjectToShare(@NonNull String key, @NonNull T t) {
        try {
            // 存储
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(toByte);
            oos.writeObject(t);
            // 对byte[]进行Base64编码
            String payCityMapBase64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));
            editor.putString(key, payCityMapBase64);
            return editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }




}
