package com.example.commonlibrary.imageloader.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.commonlibrary.imageloader.base.BaseImageLoaderStrategy;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;


public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageLoaderConfig> {
    @Override
    public void loadImage(Context context, GlideImageLoaderConfig config) {
        if (config == null || context == null) {
            return;
        }
        RequestOptions options = centerCropTransform().priority(Priority.HIGH);
        switch (config.getCacheStrategy()) {
            case GlideImageLoaderConfig.CACHE_ALL:
                options.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case GlideImageLoaderConfig.CACHE_NONE:
                options.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case GlideImageLoaderConfig.CACHE_SOURCE:
                options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case GlideImageLoaderConfig.CACHE_RESULT:
                options.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
        }
        if (config.isCenterInside()) {
            options.fitCenter();
        }else {
            options.centerCrop();
        }
        if (config.getBitmapTransformation() != null) {
            options.bitmapTransform(config.getBitmapTransformation());
        }
        if (config.getErrorResId() != 0) {
            options.error(config.getErrorResId());
        }
        if (config.getPlaceHolderResId() != 0) {
            options.placeholder(config.getPlaceHolderResId());
        }
       Glide.with(context).load(config.getUrl()).apply(options).into(config.getImageView());

    }
}
