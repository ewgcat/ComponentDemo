package com.yijian.staff.mvp.workspace.commen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.base.BaseWebView;
import com.yijian.staff.mvp.workspace.perfect.PerfectActivity;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.staff.mvp.workspace.static_assessment.StaticAssessmentActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.mvp.workspace.utils.HttpManagerWorkSpace;
import com.yijian.staff.mvp.workspace.webutils.JavaScriptInterface;
import com.yijian.staff.mvp.workspace.webutils.SafeWebChromeClient;
import com.yijian.staff.mvp.workspace.webutils.SafeWebViewClient;
import com.yijian.staff.mvp.workspace.widget.CommenPopupWindow;
import com.yijian.staff.share.umeng.SharePopupWindow;
import com.yijian.staff.widget.EmptyView;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONException;
import org.json.JSONObject;

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
    String shareWorkSpaceUrl;
    String shareWorkSpaceTitle;
    String shareWorkSpaceImgUrl;
    String shareWorkSpaceDescr;
    NavigationBar  navigationBar;
    String webUrl;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_share_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        showLoading();
        initToast();
        initData();
    }

    private void initTitle() {
        navigationBar = findViewById(R.id.navigation_bar);
//        navigationBar.setTitle(ActivityUtils.name+"的测试记录");
        navigationBar .hideLeftSecondIv();
        navigationBar.setBackLLVisiable(View.GONE);
//        NavigationBar.setmRightIv(R.mipmap.share);
        navigationBar .setBackClickListener(this);
        navigationBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
            }
        });
    }

    private void initData() {
        recordId = getIntent().getExtras().getString("recordId");
        if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)){
//            webUrl = String.format("http://192.168.2.101:8080/#/perfectgirth?memberId=%s&wdId=%s&title=%s", ActivityUtils.workSpaceVipBean.getMemberId(), recordId, ActivityUtils.workSpaceVipBean.getName() + "的测试记录");
            webUrl = String.format( HttpManagerWorkSpace.getH5Host() + "#/perfectgirth?memberId=%s&wdId=%s&title=%s", ActivityUtils.workSpaceVipBean.getMemberId(), recordId, ActivityUtils.workSpaceVipBean.getName() + "的测试记录");
        }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)){
//            webUrl = String.format("http://192.168.2.101:8080/#/sportperformance?memberId=%s&wdId=%s&title=%s", ActivityUtils.workSpaceVipBean.getMemberId(), recordId, ActivityUtils.workSpaceVipBean.getName() + "的测试记录");
            webUrl = String.format( HttpManagerWorkSpace.getH5Host() + "#/sportperformance?memberId=%s&wdId=%s&title=%s", ActivityUtils.workSpaceVipBean.getMemberId(), recordId, ActivityUtils.workSpaceVipBean.getName() + "的测试记录");
        }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_STATIC_EVALUATE)){
            webUrl = String.format("http://192.168.2.100:8080/#/summaryreport?memberId=%s&wdId=%s&title=%s", ActivityUtils.workSpaceVipBean.getMemberId(), recordId, ActivityUtils.workSpaceVipBean.getName() + "的测试记录");
        }
        emptyView.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web_view.reload();
            }
        });
        web_view.addAppJavaScript(new JavaScriptInterface.CallBackListener() {
            @Override
            public Object callBack(String msg, int type) {
                if (type == JavaScriptInterface.JS_GoWorkspaceTest) { //测试
                    if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)) {
                        getMContext().startActivity(new Intent(getMContext(), SportTestActivity.class));
                    } else if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)) {
                        getMContext().startActivity(new Intent(getMContext(), PerfectActivity.class));
                    } else if (ActivityUtils.moduleType.equals(ActivityUtils.MODULE_STATIC_EVALUATE)) {
                        getMContext().startActivity(new Intent(getMContext(), StaticAssessmentActivity.class));
                    }
                    finish();
                } else if (type == JavaScriptInterface.JS_GoWorkspaceOtherTest) { //其它测试
                    popDialog();
                } else if (type == JavaScriptInterface.JS_GetWorkspaceToaken) { //获取Toaken
                    User user = DBManager.getInstance().queryUser();
                    return user.getToken();
                } else if (type == JavaScriptInterface.JS_returnTestWdId) { //获取结果Id
                    return recordId;
                } else if (type == JavaScriptInterface.JS_returnTestMemberId) { //获取MemberId
                    return ActivityUtils.workSpaceVipBean.getMemberId();
                } else if (type == JavaScriptInterface.JS_ReturnShareUrl) { //获取分享链接
                    if (!TextUtils.isEmpty(msg)) {
                        try {
                            JSONObject jsonObject = new JSONObject(msg);
                            shareWorkSpaceUrl = jsonObject.getString("webpageShareUrl");
                            shareWorkSpaceTitle = jsonObject.getString("title");
                            shareWorkSpaceImgUrl = jsonObject.getString("imgUrl");
                            shareWorkSpaceDescr = jsonObject.getString("descr");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return "";
            }
        });
        web_view.addLoadListener(new SafeWebViewClient.CallLoadBackListener() {
            @Override
            public void onLoadFinish() {
                hideLoading();
                emptyView.setVisibility(View.GONE);
                web_view.setVisibility(View.VISIBLE);
                navigationBar.setBackLLVisiable(View.VISIBLE);
                navigationBar.setmRightIv(R.mipmap.share);

            }

            @Override
            public void onLoadError() {
                emptyView.setVisibility(View.VISIBLE);
                web_view.setVisibility(View.GONE);
                hideLoading();
            }
        });
        web_view.addWebChromeClientListener(new SafeWebChromeClient.CallWebChromeClientBackListener() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
//                navigationBar.setTitle(title);
            }

            @Override
            public void onProgressChanged(String title, int newProgress) {
                if(newProgress == 100){
                    navigationBar.setTitle(title);
                }
            }
        });

        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        User user = DBManager.getInstance().queryUser();
        cookieManager.setCookie(webUrl, "studio_token=" + user.getToken());
        CookieSyncManager.getInstance().sync();

        web_view.loadAppUrl(webUrl);
    }


    private void showShareDialog() {
        if (sharePopupWindow == null) {
            sharePopupWindow = new SharePopupWindow(this);
            sharePopupWindow.setData(shareWorkSpaceUrl, shareWorkSpaceTitle, shareWorkSpaceImgUrl, shareWorkSpaceDescr);
        }
        sharePopupWindow.show(getWindow().getDecorView());
    }

    public void popDialog() {
        if (testPopupWindow == null) {

            testPopupWindow = new CommenPopupWindow(ShareTestActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testPopupWindow.dismiss();
                    switch (v.getId()) {
                        case R.id.lin_perfect: //完美围度
                            ActivityUtils.isShareJump = true;
                            ActivityUtils.tempModule = ActivityUtils.moduleType;
                            ActivityUtils.moduleType = ActivityUtils.MODULE_PERFECT;
                            ShareTestActivity.this.startActivity(new Intent( ShareTestActivity.this, PerfectActivity.class));
                            finish();
                            break;
                        case R.id.lin_sport: // 运动表现
                            ActivityUtils.isShareJump = true;
                            ActivityUtils.tempModule = ActivityUtils.moduleType;
                            ActivityUtils.moduleType = ActivityUtils.MODULE_SPORT;
                            ShareTestActivity.this.startActivity(new Intent( ShareTestActivity.this, SportTestActivity.class));
                            finish();
                            break;
                        case R.id.lin_static: //静态评估
//                            toast.show();
                            ActivityUtils.isShareJump = true;
                            ActivityUtils.tempModule = ActivityUtils.moduleType;
                            ActivityUtils.moduleType = ActivityUtils.MODULE_STATIC_EVALUATE;
                            ShareTestActivity.this.startActivity(new Intent( ShareTestActivity.this, SportTestActivity.class));
                            finish();
                            break;
                        case R.id.lin_action: //动作评估
                            toast.show();
                            break;
                        default:
                    }
                }
            }, R.layout.pop_test_result, new int[]{R.id.lin_perfect, R.id.lin_sport, R.id.lin_static, R.id.lin_action});

        }
        if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_PERFECT)){
            testPopupWindow.getmMenuView().findViewById(R.id.lin_perfect).setVisibility(View.GONE);
        }else if(ActivityUtils.moduleType.equals(ActivityUtils.MODULE_SPORT)){
            testPopupWindow.getmMenuView().findViewById(R.id.lin_sport).setVisibility(View.GONE);
        }
        testPopupWindow.showAtBottom(getWindow().getDecorView());
    }

    public void initToast() {
        toast = new Toast(ShareTestActivity.this);
        View view = getLayoutInflater().inflate(R.layout.toast_btranspant, null);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
    }

}
