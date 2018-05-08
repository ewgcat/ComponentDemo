package com.yijian.staff.mvp.webview;

import android.os.Build;
import android.os.Bundle;
import android.transition.Scene;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;

public abstract class BaseWebViewActivity extends MvcBaseActivity {

    public static final int  ABOUT_US_TYPE=1;
    public static final int CLUB_TYPE=2;
    public static final int QR_TYPE=3;
    public static final int CONTRACT_TYPE=5;



    public void initWebView( WebView webView,int type) {
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
        HashMap<String ,String> params=new HashMap<>();
        params.put("type",""+type);
        HttpManager.postHasHeaderHasParam(HttpManager.ABOUT_US_AND_CLUB_AND_QR_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                webView.loadUrl(JsonUtil.getString(result,"url"));
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
        JSONObject jsonObject = new JSONObject();
        webView.loadUrl("http://192.168.2.209:8080/#/contract");
        String token = DBManager.getInstance().queryUser().getToken();
        try {
            jsonObject.put("token",token);
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
