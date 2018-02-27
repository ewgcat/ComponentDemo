

package com.example.commonlibrary.net.okhttpconfig;

import android.support.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class OkHttpStreamFetcher implements DataFetcher<InputStream> {



    private static final String TAG = "OkHttpFetcher";
    private final OkHttpClient client;
    private final GlideUrl url;
    private volatile Call call;

    @SuppressWarnings("WeakerAccess")
    InputStream stream;
    @SuppressWarnings("WeakerAccess")
    ResponseBody responseBody;

    // Public API.
    @SuppressWarnings("WeakerAccess")
    public OkHttpStreamFetcher(OkHttpClient client, GlideUrl url) {
        this.client = client;
        this.url = url;
    }



    @Override
    public void cleanup() {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            // Ignored
        }
        if (responseBody != null) {
            responseBody.close();
        }
    }



    @Override
    public void cancel() {
        Call local = call;
        if (local != null) {
            local.cancel();
        }
    }


    @Override
    public void loadData(@NonNull Priority priority,
                         @NonNull final DataCallback<? super InputStream> callback) {
        Request.Builder requestBuilder = new Request.Builder().url(url.toStringUrl());
        for (Map.Entry<String, String> headerEntry : url.getHeaders().entrySet()) {
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key, headerEntry.getValue());
        }
        Request request = requestBuilder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onLoadFailed(e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseBody = response.body();
                if (response.isSuccessful()) {
                    long contentLength = responseBody.contentLength();
                    stream = ContentLengthInputStream.obtain(responseBody.byteStream(), contentLength);
                    callback.onDataReady(stream);
                } else {
                    callback.onLoadFailed(new HttpException(response.message(), response.code()));
                }
            }



        });
    }





    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }
}
