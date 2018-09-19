package com.yijian.staff.mvp.webview;

import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.staff.BuildConfig;
import com.yijan.commonlib.db.ClubDBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseWebViewActivity extends MvcBaseActivity {

    public static final int ABOUT_US_TYPE = 1;
    public static final int CLUB_TYPE = 2;
    public static final int QR_TYPE = 3;
    public static final int CONTRACT_TYPE = 5;


    public void initWebView(WebView webView, int type) {
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

        JSONObject jsonObject = new JSONObject();
        webView.loadUrl("http://192.168.2.165:8080/#/bappclub");
        String token = ClubDBManager.getInstance().queryUser().getToken();
        try {
            jsonObject.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:GetUserInfo('" + jsonObject.toString() + "')");
            }
        });


    }


}
