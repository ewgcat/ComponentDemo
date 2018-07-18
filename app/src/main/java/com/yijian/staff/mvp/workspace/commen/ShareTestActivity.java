package com.yijian.staff.mvp.workspace.commen;

import android.os.Bundle;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.umeng.SharePopupWindow;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

public class ShareTestActivity extends MvcBaseActivity {

    private SharePopupWindow sharePopupWindow;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_share_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle(ActivityUtils.name+"的测试记录");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setmRightIv(R.mipmap.share);
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
            }
        });
    }

    private void initData(){

    }

    private void showShareDialog() {
        if (sharePopupWindow == null) {
            sharePopupWindow = new SharePopupWindow(this);
            sharePopupWindow.setData("https://developer.umeng.com/sdk/android", "测试记录", "", "测试记录");
        }
        sharePopupWindow.show(getWindow().getDecorView());
    }

}
