package com.yijian.commonlib.util;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2017/1/6.
 * 获取通知栏权限是否开启
 */
public class NotificationsUtil {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            int modeAllowed = AppOpsManager.MODE_ALLOWED;
            int invoke = (Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg);
            Logger.i("NotificationsUtil","modeAllowed="+modeAllowed);
            Logger.i("NotificationsUtil","invoke="+invoke);

            return invoke == modeAllowed;

        } catch (ClassNotFoundException e) {
            Logger.i("NotificationsUtil",e.getMessage());
        } catch (NoSuchMethodException e) {
            Logger.i("NotificationsUtil",e.getMessage());
        } catch (NoSuchFieldException e) {
            Logger.i("NotificationsUtil",e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.i("NotificationsUtil",e.getMessage());
        } catch (IllegalAccessException e) {
            Logger.i("NotificationsUtil",e.getMessage());
        }
        return false;
    }
}