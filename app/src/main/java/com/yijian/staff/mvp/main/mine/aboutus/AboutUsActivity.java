package com.yijian.staff.mvp.main.mine.aboutus;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.mvp.webview.BaseWebViewActivity;
import com.yijian.staff.widget.NavigationBar;

import butterknife.BindView;

public class AboutUsActivity extends BaseWebViewActivity {


    @BindView(R.id.about_us_navigation_bar2)
    NavigationBar NavigationBar;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar.setTitle("关于易健");
        NavigationBar.hideLeftSecondIv();
        NavigationBar.setBackClickListener(this);


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

        webView.loadUrl(BuildConfig.WORKSPACE_H5_HOST+"#/bappabout");




    }


}
