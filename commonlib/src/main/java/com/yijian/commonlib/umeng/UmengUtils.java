package com.yijian.commonlib.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by GZLX on 2018/5/7.
 */

public class UmengUtils {
    public static void init(Application application) {//内存泄漏
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(application, "5b56954df43e48214c000049", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "5b56954df43e48214c000049");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wxa43603c935a858ec", "b6955f6092f388a922edbd197e2a005e");
        PlatformConfig.setSinaWeibo("4169776103", "866dee2aeed8ddf8160a2ac4737c4d24", "http://a.app.qq.com/o/simple.jsp?pkgname=com.yijian.commonlib");
    }
}
