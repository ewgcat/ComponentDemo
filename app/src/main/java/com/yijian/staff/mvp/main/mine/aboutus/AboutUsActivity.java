package com.yijian.staff.mvp.mine.aboutus;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends MvcBaseActivity {


    @BindView(R.id.about_us_navigation_bar2)
    NavigationBar2 navigationBar2;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        navigationBar2.setTitle("关于我们");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);


        initWebView(webView);


    }

    private void initWebView( WebView webView) {
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
        params.put("type","1");
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
    }


}
