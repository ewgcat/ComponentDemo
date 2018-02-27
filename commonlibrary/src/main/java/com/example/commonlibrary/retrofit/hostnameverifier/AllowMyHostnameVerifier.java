package com.example.commonlibrary.retrofit.hostnameverifier;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


public class AllowMyHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {

        return true;
    }
}
