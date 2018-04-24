package com.yijian.staff.net.httpmanager;


import android.content.Context;
import android.util.Log;

import com.hengte.retrofit.net.converterfactory.JSONObjectConverterFactory;
import com.yijian.staff.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * create by tdd
 * date 2016/8/5 0005
 * des 对网络请求进行初始化
 */
public class RetrofitClient {
    public static Retrofit mRetrofit;
    public static Retrofit mRetrofitForUpload;

    public static String zs_1 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIFjTCCBHWgAwIBAgIQCgbj+EGDph0S68xH/vUZvTANBgkqhkiG9w0BAQsFADBu\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMS0wKwYDVQQDEyRFbmNyeXB0aW9uIEV2ZXJ5d2hlcmUg\n" +
            "RFYgVExTIENBIC0gRzEwHhcNMTgwNDEyMDAwMDAwWhcNMTkwNDEyMTIwMDAwWjAc\n" +
            "MRowGAYDVQQDExFoNS5kZXYuZWpveXN0LmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\n" +
            "ggEPADCCAQoCggEBAJjHMq3q3CzOigfjwrUspKjmdXfI6mtREMzXoOo3QqEpdgGO\n" +
            "jRTN/3K4tpvEK/6GFdEGHUxVP0nMfGYo3wp6oVxgMJk4sagcV/Gq6kzaucxoOqGc\n" +
            "j5WbwzTbwjHwHNdySP+6OjJeMMDVZryoe3h08G1RWqqmy8eUF13+qFJCpwAtkLNG\n" +
            "uc37cQL+0cMB6vHcY3R0OgNNNAen1UrvXSmn9BcIjd/gOcL24Ho5ayq6rmgbaNuc\n" +
            "88JxwBM/Eqzr0Ric1KNZxERBPALb1hSiC/St/mMlp/WfmuwFimE8kWlRQhlXcdHz\n" +
            "k9Nh5dEhv8Q9fFPMg8L/YP0z9/JwNHJnVqJ2HpMCAwEAAaOCAncwggJzMB8GA1Ud\n" +
            "IwQYMBaAFFV0T7JyT/VgulDR1+ZRXJoBhxrXMB0GA1UdDgQWBBTYa/wU34NtOs/A\n" +
            "lYTmFKGcQP4hyDAcBgNVHREEFTATghFoNS5kZXYuZWpveXN0LmNvbTAOBgNVHQ8B\n" +
            "Af8EBAMCBaAwHQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMEwGA1UdIARF\n" +
            "MEMwNwYJYIZIAYb9bAECMCowKAYIKwYBBQUHAgEWHGh0dHBzOi8vd3d3LmRpZ2lj\n" +
            "ZXJ0LmNvbS9DUFMwCAYGZ4EMAQIBMIGBBggrBgEFBQcBAQR1MHMwJQYIKwYBBQUH\n" +
            "MAGGGWh0dHA6Ly9vY3NwMi5kaWdpY2VydC5jb20wSgYIKwYBBQUHMAKGPmh0dHA6\n" +
            "Ly9jYWNlcnRzLmRpZ2ljZXJ0LmNvbS9FbmNyeXB0aW9uRXZlcnl3aGVyZURWVExT\n" +
            "Q0EtRzEuY3J0MAkGA1UdEwQCMAAwggEFBgorBgEEAdZ5AgQCBIH2BIHzAPEAdwCk\n" +
            "uQmQtBhYFIe7E6LMZ3AKPDWYBPkb37jjd80OyA3cEAAAAWK5bFGkAAAEAwBIMEYC\n" +
            "IQCeAF/kRzKw6pLhl+NX1LdY0uMYGmzqxbLEKGVi4Mf91QIhAN5nXX6LIEqkC/qm\n" +
            "fLhRq/3d59Roeo5yQQr4rFoTI9UnAHYAb1N2rDHwMRnYmQCkURX/dxUcEdkCwQAp\n" +
            "Bo2yCJo32RMAAAFiuWxS+QAABAMARzBFAiA4YuwGt4rnOVPzrPSjuJ4mOaaFZ4y6\n" +
            "wJCdOpL+sB2slgIhAKE9VmLxHSvNlmX0x3vbv/F0ya3L+OlLIyfMoDOk06NfMA0G\n" +
            "CSqGSIb3DQEBCwUAA4IBAQA/22lEdeLcAVO+gsVYM0UV6bF304yZc5hPcHGGBTtF\n" +
            "gkMWst6FwunHmy8gKjHzty03x13i78MENjpMdtLkh92JK4H7T6fwuVpySpGsHwi+\n" +
            "YC6IEv7+MwctyZP36CMahEWdkPXR5Bcqdp1RIdMRfhThiR3DPyPJwtL/vYcI7ic2\n" +
            "zpe0mBIWgukaaahQL06Xnc4JTH5IhLcfv2t5QLg/LwFua2r2FQVm8GBULg+t9gK8\n" +
            "/Q21YjgPVOt1LQc0KIk+QVwdAyJA1v83YWIpmq/EialP1Wb6/5gBnAaOhfMkcQm4\n" +
            "BRpidpDD5kYQtWOoteDzBIzOwEc/yzijtC2+KACtz1Os\n" +
            "-----END CERTIFICATE-----";

    public static String zs_2 = "";

    public static String zs_3 = "";


    //初始化一般请求客户端
    public static Retrofit init(Context context) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("http", message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            builder.sslSocketFactory(getSSLSocketFactory(context,
                    new Buffer().writeUtf8(zs_1).inputStream()
//                    , new Buffer().writeUtf8(zs_2).inputStream(),new Buffer().writeUtf8(zs_3).inputStream()

                    )
            );
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();

            //组装retrofit
            mRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(JSONObjectConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://bwebapp.dev.ejoyst.com/")
                    .build();
        }
        return mRetrofit;
    }


    //初始化上传客户端
    public static Retrofit initUpload(Context context) {
        if (mRetrofitForUpload == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("RetrofitClient", message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            builder.sslSocketFactory(getSSLSocketFactory(context,
                    new Buffer().writeUtf8(zs_1).inputStream(),
                    new Buffer().writeUtf8(zs_2).inputStream(),
                    new Buffer().writeUtf8(zs_3).inputStream()));
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            builder.connectTimeout(5, TimeUnit.MINUTES);
            builder.readTimeout(5, TimeUnit.MINUTES);
            builder.writeTimeout(5, TimeUnit.MINUTES);
            OkHttpClient okHttpClient = builder.build();

            mRetrofitForUpload = new Retrofit.Builder()
                    .baseUrl("http://poly.hengtech.com.cn/pmsSrv/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofitForUpload;
    }


    //注册证书
    protected static SSLSocketFactory getSSLSocketFactory(Context context, InputStream... certificates) {


        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.i("dd", "e->" + e.toString());
            e.printStackTrace();
        }
        return null;
    }


}
