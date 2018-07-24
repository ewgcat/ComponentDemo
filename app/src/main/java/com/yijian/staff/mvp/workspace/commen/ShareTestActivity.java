package com.yijian.staff.mvp.workspace.commen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.base.BaseWebView;
import com.yijian.staff.mvp.workspace.perfect.PerfectActivity;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.staff.mvp.workspace.umeng.SharePopupWindow;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.mvp.workspace.webutils.JavaScriptInterface;
import com.yijian.staff.mvp.workspace.webutils.SafeWebViewClient;
import com.yijian.staff.mvp.workspace.widget.CommenPopupWindow;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;

public class ShareTestActivity extends MvcBaseActivity {

    private SharePopupWindow sharePopupWindow;
    private CommenPopupWindow testPopupWindow;
    @BindView(R.id.web_view)
    BaseWebView web_view;
    @BindView(R.id.emptyView)
    EmptyView emptyView;
    Toast toast;
    String recordId;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_share_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        showLoading();
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
        recordId = getIntent().getExtras().getString("recordId");
        emptyView.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web_view.loadUrl("http://192.168.2.32:8080/#/sport");
            }
        });
//        showShareDialog();
        web_view.addAppJavaScript(new JavaScriptInterface.CallBackListener() {
            @Override
            public Object callBack(String msg, int type) {
                if(type == JavaScriptInterface.JS_GoWorkspaceTest){ //测试
                    if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)) {
                        mContext.startActivity(new Intent(mContext, SportTestActivity.class));
                    }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)){
                        mContext.startActivity(new Intent(mContext, PerfectActivity.class));
                    }
                    finish();
                }else  if(type == JavaScriptInterface.JS_GoWorkspaceOtherTest){ //其它测试
                    popDialog();
                }else if(type == JavaScriptInterface.JS_GetWorkspaceToaken){ //获取Toaken
                    User user = DBManager.getInstance().queryUser();
                    return user.getToken();
                }else if(type == JavaScriptInterface.JS_returnTestWdId){ //获取结果Id
                    return recordId;
                }
                return "";
            }
        });
        web_view.addLoadListener(new SafeWebViewClient.CallLoadBackListener() {
            @Override
            public void onLoadFinish() {
                hideLoading();
                emptyView.setVisibility(View.GONE);
            }

            @Override
            public void onLoadError() {
                emptyView.setVisibility(View.VISIBLE);
                hideLoading();
            }
        });
        web_view.addLoadListener(new SafeWebViewClient.CallLoadBackListener() {
            @Override
            public void onLoadFinish() {
                hideLoading();
            }

            @Override
            public void onLoadError() {
                hideLoading();
            }
        });
//        web_view.loadAppUrl("http://192.168.2.69:8080/fs-vank/");
        web_view.loadAppUrl("http://192.168.2.32:8080/#/sport");
    }



    private void showShareDialog() {
        if (sharePopupWindow == null) {
            sharePopupWindow = new SharePopupWindow(this);
            sharePopupWindow.setData("https://developer.umeng.com/sdk/android", "测试记录", "", "测试记录");
        }
        sharePopupWindow.show(getWindow().getDecorView());
    }

    public void popDialog(){
        if(testPopupWindow == null){

            testPopupWindow = new CommenPopupWindow(ShareTestActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testPopupWindow.dismiss();
                    switch (v.getId()){
                        case R.id.lin_perfect: //完美围度
                            mContext.startActivity(new Intent(mContext, PerfectActivity.class));
                            finish();
                            break;
                        case R.id.lin_sport: // 运动表现
                            mContext.startActivity(new Intent(mContext, SportTestActivity.class));
                            finish();
                            break;
                        case R.id.lin_static: //静态评估
                            toast.show();
                            break;
                        case R.id.lin_action: //动作评估
                            toast.show();
                            break;
                            default:
                    }
                }
            },R.layout.pop_test_result,new int[]{R.id.lin_perfect,R.id.lin_sport,R.id.lin_static,R.id.lin_action});

        }
        testPopupWindow.showAtBottom(getWindow().getDecorView());
    }

    public void initToast(){
        toast = new Toast(ShareTestActivity.this);
        View view = getLayoutInflater().inflate(R.layout.toast_btranspant,null);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER , 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
    }

}
