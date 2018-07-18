package com.yijian.staff.mvp.workspace.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

/**
 * Created by GZLX on 2018/5/7.
 */

public class UmengUtils {
    public static void init(Application application) {//内存泄漏
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(application, "5afe85d08f4a9d5101000042", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "5afe85d08f4a9d5101000042");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wxd88a5796b3d11fcf", "b6955f6092f388a922edbd197e2a005e");
        PlatformConfig.setSinaWeibo("1959171506", "1dbf4bb8ec913ea3e7703e93a748ec6e", "https://www.pgyer.com/wr0A");
    }
}
