package com.yijian.workspace.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.yijian.commonlib.util.FileUtil;

/**
 * Created by GZLX on 2018/5/17.
 */
@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置磁盘缓存目录（和创建的缓存目录相同）
        String downloadDirectoryPath = FileUtil.getDiskCacheDir(context) + "glide";
        //设置缓存的大小为100M
        int cacheSize = 100 * 1024 * 1024;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));
    }

}
