package com.yijian.staff.jpush;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;

import com.yijian.staff.jpush.bean.Messager;
import com.yijian.staff.mvp.coach.setclass.orderclass.OrderClassActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.ReceptionActivityTemp;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.util.GsonNullString;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JpushMessageReceiver extends BroadcastReceiver {
    private static final String TAG = "Jpush";

    public static boolean shouldToReception = false;
    public static String bundleString = "";
    public static int notifactionId = -1;
    public static int type = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        bundleString = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Logger.i(TAG, "接收到推送下来的自定义消息: " + bundleString);


        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Logger.i(TAG, "接收到推送下来的自定义消息");
            Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
            if (vibrator.hasVibrator()) {
                long[] patter = {600, 200, 600, 200};
                vibrator.vibrate(patter, -1);
            }
            try {
                JSONObject jsonObject = new JSONObject(bundleString);
                String data = jsonObject.getString("data");
                JSONObject jsonObject1 = new JSONObject(data);
                if (jsonObject1.has("type")){
                    type = jsonObject1.getInt("type");
                    Logger.i(TAG, "type="+type);
                    boolean background = isBackground(context);
                    if (type == 0 && !background) {// //属于接待消息&&属于前台
                        ReceptionActivityTemp.toReceptionActivityTemp(context);
//                        JPushInterface.clearNotificationById(context, notifactionId);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {//接收到推送下来的通知

            Logger.i(TAG, "接收到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {//用户点击打开了通知
            Logger.i(TAG, "用户点击打开了通知");
            try {
                JSONObject jsonObject = new JSONObject(bundleString);
                String data = jsonObject.getString("data");
                JSONObject jsonObject1 = new JSONObject(data);
                if (jsonObject1.has("type")){
                    int type = jsonObject1.getInt("type");
                    if (type == 0) {// //属于接待消息
                        ReceptionActivityTemp.toReceptionActivityTemp(context);
                    }else   if (type == 1||type == 2){
                        String date= jsonObject1.getString("data");
                        Intent intent1 = new Intent(context, OrderClassActivity.class);
                        intent1.putExtra("date",date);
                        context.startActivity(intent1);
                    }else   if (type>2&&type<18){
                        Intent intent1 = new Intent(context, MainActivity.class);
                        intent1.putExtra("push_message",2);
                        context.startActivity(intent1);
                    }
                }

//                约课 OrderClassActivity
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {//回调
            Logger.i(TAG, "用户收到到富媒体推送: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //todo 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {//连接状态
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        } else {
        }
    }






    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                Log.e(TAG, "isBackground: " + appProcess.processName);
                Log.e(TAG, "importance: " + appProcess.importance);

                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                Log.i(TAG, "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(TAG, "处于前台"
                            + appProcess.processName);
                    shouldToReception = false;
                    return false;
                } else if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_TOP_SLEEPING) {
                    Log.i(TAG, "处于后台,屏幕熄频"
                            + appProcess.processName);
                    shouldToReception = true;
                    return true;
                } else {
                    Log.i(TAG, "处于后台台"
                            + appProcess.processName);
                    shouldToReception = false;
                    return true;
                }
            }
        }
        return false;
    }


}
