package com.yijian.clubmodule.ui.main.mine.aboutus;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.clubmodule.ui.webview.BaseWebViewActivity;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.BuildConfig;
import com.yijian.clubmodule.R;


public class AboutUsActivity extends BaseWebViewActivity {


    NavigationBar navigationBar;
    WebView webView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        navigationBar = findViewById(R.id.about_us_navigation_bar);
        webView = findViewById(R.id.web_view);
        navigationBar.setTitle("关于易健");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);


        initWebView(webView);


    }


    public void initWebView(WebView webView) {
        WebSettings webviewSettings = webView.getSettings();
        webviewSettings.setJavaScriptEnabled(true); // 开启Javascript支持
        webviewSettings.setAllowContentAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webviewSettings.setAllowFileAccess(true);// 可以读取文件缓存(manifest生效)
        webviewSettings.setPluginState(WebSettings.PluginState.ON);
        webviewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webviewSettings.setRenderPriority(WebSettings.RenderPriority.HIGH); // 提高渲染的优先级
        webviewSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(SharePreferenceUtil.getH5Url() + "#/bappabout");


    }


}
