package com.yijian.staff.mvp.reception.contract;

import android.os.Bundle;
import android.webkit.WebView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.webview.BaseWebViewActivity;

import com.yijian.staff.widget.NavigationBar2;



import butterknife.BindView;

public class ContractActivity extends BaseWebViewActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contract;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.contract_navigation_bar2);
        navigationBar2.setTitle("合同");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        initWebView(webView,BaseWebViewActivity.CONTRACT_TYPE);
    }

}
