package com.yijian.staff.mvp.workspace.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yijian.staff.mvp.workspace.webutils.JavaScriptInterface;
import com.yijian.staff.mvp.workspace.webutils.SafeWebChromeClient;
import com.yijian.staff.mvp.workspace.webutils.SafeWebViewClient;

public class BaseWebView extends WebView {

    public BaseWebView(Context context) {
        this(context,null);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        WebSettings webSettings = getSettings();
        // webSettings.setSupportZoom(true);// 支持缩放
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // webSettings.setUseWideViewPort(true); //设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
    }

    public void addLoadListener(SafeWebViewClient.CallLoadBackListener listener){
        setWebViewClient(new SafeWebViewClient(listener));
    }

    public void addWebChromeClientListener(SafeWebChromeClient.CallWebChromeClientBackListener listener){
        setWebChromeClient(new SafeWebChromeClient(listener));
    }

    @SuppressLint("JavascriptInterface")
    public void addAppJavaScript(JavaScriptInterface.CallBackListener callBackListener){
        addJavascriptInterface(new JavaScriptInterface(callBackListener),"android_workspace");
    }

    public void loadAppUrl(String url){
        loadUrl(url);
    }



}
