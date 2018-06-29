package com.yijian.staff.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class ImageLoader {


    public static void load(Activity activity, String url, ImageView iv) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        if (!activity.isDestroyed()) {
            RequestOptions options = centerCropTransform()
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .transform(new GlideCircleTransform());
            Glide.with(activity).load(url).apply(options).into(iv);
        }


    }

    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        Glide.with(context).load(url).into(iv);
    }

    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        if (!activity.isDestroyed()) {
            RequestOptions options = centerCropTransform()
                    .priority(Priority.HIGH).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(new GlideCircleTransform());
            Glide.with(activity).load(url).apply(options).into(iv);
        }
    }


    public static void setHeadImageResource(String path, Context context, ImageView imageView) {
        if (TextUtils.isEmpty(path)) {
            path = "";
        }


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.head_placeholder)
                .error(R.mipmap.head_placeholder)
                .transform(new GlideCircleTransform())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    public static void setImageResource(String path, Context context, ImageView imageView) {
        if (TextUtils.isEmpty(path)) {
            path = "";
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

}
