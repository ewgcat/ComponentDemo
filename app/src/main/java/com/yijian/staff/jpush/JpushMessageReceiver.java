package com.yijian.staff.jpush;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hengte.retrofit.net.subsrciber.BaseObserver;
import com.yijian.staff.jpush.bean.Messager;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.GsonNullString;
import com.yijian.staff.util.JsonUtil;
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

        private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext=context;
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String bundleString = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Logger.i(TAG, "接收到推送下来的自定义消息: " +bundleString);
            try {
//                JSONObject jsonObject = new JSONObject(bundleString);
//                JSONObject data = JsonUtil.getJsonObject(jsonObject, "data");
//                int smallStatus = JsonUtil.getInt(data, "smallStatus");
                Messager messager = GsonNullString.getGson().fromJson(bundleString, Messager.class);
                RecptionerInfoBean recptionerInfoBean = new RecptionerInfoBean();
                recptionerInfoBean.setId(messager.getId());
                recptionerInfoBean.setStatus(messager.getOperatorType());
                recptionerInfoBean.setMobile(messager.getMobile());
                recptionerInfoBean.setName(messager.getName());
                Integer sex = messager.getSex();
                if (sex==0){
                    recptionerInfoBean.setSex("未知");
                }else if (sex==1){
                    recptionerInfoBean.setSex("男");
                }else if (sex==2){
                    recptionerInfoBean.setSex("女");
                }
                List<Integer> historyNode = recptionerInfoBean.getHistoryNode();
                List<Integer> nodes=new ArrayList<>();
                if (historyNode!=null&&!historyNode.isEmpty()){
                    for (Integer integer :  historyNode) {
                        nodes.add(integer);
                    }
                }
                recptionerInfoBean.setHistoryNode(nodes);
                Log.e(TAG, "onReceive: "+recptionerInfoBean.toString());
                Intent intent1 = new Intent(context,ReceptionStepActivity.class);
                intent1.putExtra(ReceptionActivity.CONSUMER,recptionerInfoBean);
                context.startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {//接收到推送下来的通知
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Logger.i(TAG, "接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {//用户点击打开了通知
            Logger.i(TAG, "用户点击打开了通知");
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {//回调
            Logger.i(TAG, "用户收到到富媒体推送: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //todo 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {//连接状态
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        } else {
        }
    }


    /**
     * 打印所有的 intent extra 数据
     */
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    //Logger.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.i(TAG, "Get message extra JSON error!");
                }
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    protected ActivityManager mActivityManager;



    public ActivityManager.RunningTaskInfo getTopTask() {
        mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = mActivityManager.getRunningTasks(1);
        if (tasks != null && !tasks.isEmpty()) {
            return tasks.get(0);
        }

        return null;
    }

    public boolean isTopActivity(
            ActivityManager.RunningTaskInfo topTask,
            String packageName,
            String activityName) {
        if (topTask != null) {
            ComponentName topActivity = topTask.topActivity;

            if (topActivity.getPackageName().equals(packageName) &&
                    topActivity.getClassName().equals(activityName)) {
                return true;
            }
        }

        return false;
    }





}
