package com.yijian.staff.mvp.main.mine.club;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jaeger.library.StatusBarUtil;
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

public class ClubActivity extends BaseWebViewActivity {

    private static final String TAG = ClubActivity.class.getSimpleName();
    @BindView(R.id.club_navigation_bar2)
    NavigationBar2 navigationBar2;
    @BindView(R.id.web_view)
    WebView webView;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_club;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        navigationBar2.setTitle("俱乐部");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        initWebView(webView,CLUB_TYPE);
    }


}
