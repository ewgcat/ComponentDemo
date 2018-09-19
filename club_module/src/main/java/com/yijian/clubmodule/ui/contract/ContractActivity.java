package com.yijian.clubmodule.ui.contract;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.clubmodule.ui.webview.BaseWebViewActivity;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.clubmodule.BuildConfig;
import com.yijian.clubmodule.R;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;




public class ContractActivity extends BaseWebViewActivity {

    WebView webView;
    private String memberId;

    private ArrayList<String> contractIds;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        webView=findViewById(R.id.web_view);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.contract_navigation_bar);
        navigationBar.setTitle("合同");
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
        contractIds = getIntent().getStringArrayListExtra("contractIds");
        memberId = getIntent().getStringExtra("memberId");
        initWebView(webView);
    }

    private void initWebView(WebView webView) {
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
        HashMap<String, String> params = new HashMap<>();
        params.put("type", "" + BaseWebViewActivity.CONTRACT_TYPE);
        HttpManager.postHasHeaderHasParam(HttpManager.ABOUT_US_AND_CLUB_AND_QR_URL, params, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
//                webView.loadUrl(JsonUtil.getString(result,"url"));
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
            jsonObject.put("token", token);
            jsonObject.put("memberId", memberId);
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
