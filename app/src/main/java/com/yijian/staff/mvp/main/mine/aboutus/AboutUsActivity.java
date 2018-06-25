package com.yijian.staff.mvp.main.mine.aboutus;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.webview.BaseWebViewActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseWebViewActivity {


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


        initWebView(webView, ABOUT_US_TYPE);


    }


}
