package com.yijian.commonlib.util.system;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

/**
 * desc: 震动工具类
 * <p>
 * date:2017/2/22 0022
 * time:上午 9:25
 */

public class VibratorHelper {

    public static void Vibrate(final Activity activity, long milliseconds) {
        Vibrator vibrator = (Vibrator) activity
                .getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

    public static void Vibrate(final Activity activity, long[] pattern,
                               boolean isRepeat) {
        Vibrator vibrator = (Vibrator) activity
                .getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, isRepeat ? 1 : -1);
    }
}
