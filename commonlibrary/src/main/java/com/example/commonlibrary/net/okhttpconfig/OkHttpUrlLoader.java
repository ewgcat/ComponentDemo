package com.example.commonlibrary.net.okhttpconfig;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class OkHttpUrlLoader
        implements ModelLoader<GlideUrl, InputStream>
{


    private final OkHttpClient client;


    public OkHttpUrlLoader(OkHttpClient client) {
        this.client = client;
    }


    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(GlideUrl model, int width, int height, Options options) {
        return new LoadData<InputStream>(model, new OkHttpStreamFetcher(this.client, model));
    }


    @Override
    public boolean handles(GlideUrl glideUrl) {
        return true;
    }


    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        private static volatile OkHttpClient internalClient;
        private OkHttpClient client;

        private static OkHttpClient getInternalClient() {
            if (internalClient == null) {
                Class var0 = OkHttpUrlLoader.Factory.class;
                synchronized (OkHttpUrlLoader.Factory.class) {
                    if (internalClient == null) {
                        internalClient = new OkHttpClient();
                    }
                }
            }

            return internalClient;
        }

        public Factory() {
            this(getInternalClient());
        }

        public Factory(OkHttpClient client) {
            this.client = client;
        }

        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new OkHttpUrlLoader(this.client);
        }

        public void teardown() {
        }
    }
}