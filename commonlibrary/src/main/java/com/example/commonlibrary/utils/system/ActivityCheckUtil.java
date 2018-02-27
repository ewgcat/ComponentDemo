package com.example.commonlibrary.utils.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;

import java.util.List;

/**
 * desc: 检查Activity是否存在
 *
 * date:2017/4/17 0017
 * time:下午 3:01
 */

public class ActivityCheckUtil {

    /**
     * 判断栈中是否存在该Act
     *
     * @param context
     * @param cls
     * @return
     */
    public static boolean isExistMain(Activity context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (componentName != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfos = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo info : taskInfos) {
                if (info.baseActivity.equals(componentName)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

}
