package com.yijian.staff.net.interceptor;


import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseInterceptor implements Interceptor {
    private Map<String, String> headers;

    public BaseInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request()
                .newBuilder();
        builder.addHeader("Content-Type", "application/json; charset=UTF-8").build();
        builder.addHeader("Accept-Encoding", "utf-8").build();
        builder.addHeader("Connection", "keep-alive").build();
        builder.addHeader("Accept", "*/*").build();
        builder.addHeader("Cookie", "add cookies here").build();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());

    }
}
