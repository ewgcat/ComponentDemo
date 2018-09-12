package com.yijian.staff.mvp.main;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.jpush.JPushTagAliasOperatorHelper;
import com.yijian.staff.jpush.bean.PushInfoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.main.message.MessageFragment;
import com.yijian.staff.mvp.main.mine.MineFragment;
import com.yijian.staff.mvp.main.work.WorkFragment;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.Bottombar;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.yijian.staff.jpush.JPushTagAliasOperatorHelper.sequence;

public class MainActivity extends MvcBaseActivity implements Bottombar.OnClickBottomButtonListener {

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"WorkFragment", "ReportingFragment", "MessageFragment", "MimeFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    public static final int RESULT_OK_RECEPTION = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private int selectedIndex = 0;
    protected long mExitTime;


    private WorkFragment workFragment;
    private MessageFragment mesageFragment;
    private MineFragment mineFragment;
    private Bottombar mBottombar;
    private Lifecycle lifecycle;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRESELECTEDINDEX, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {


        initJPush();
        mBottombar = findViewById(R.id.bottom_bar);
        lifecycle = getLifecycle();
        mBottombar.setLifecycle(lifecycle);
        mBottombar.setmListener(this);


        if (savedInstanceState == null) {
            workFragment = new WorkFragment();
            mesageFragment = new MessageFragment();
            mineFragment = new MineFragment();
            // 默认选中0

            //
            selectTab(0);

        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            workFragment = (WorkFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            mesageFragment = (MessageFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[3]);


            //读取上一次界面Save的时候tab选中的状态
            int lastSelectedIndex = savedInstanceState.getInt(PRESELECTEDINDEX, selectedIndex);

            // 选择上一次保存的Fragment界面
            selectTab(lastSelectedIndex);


        }

        hasNotice();
        Disposable disposable = RxBus.getDefault().toDefaultFlowable(PushInfoBean.class, new Consumer<PushInfoBean>() {
            @Override
            public void accept(PushInfoBean pushInfoBean) throws Exception {
                if (workFragment != null && workFragment.isAdded()) {
                    workFragment.observe(pushInfoBean);
                }

                boolean hasNewSellBusinessPush = pushInfoBean.getHasNewSellBusinessPush();
                Boolean hasNewCourseBusinessPush = pushInfoBean.getHasNewCourseBusinessPush();
                if (hasNewSellBusinessPush || hasNewCourseBusinessPush) {
                    mBottombar.showRedPointNotice(View.VISIBLE);
                } else {
                    mBottombar.showRedPointNotice(View.INVISIBLE);
                }
                if (mesageFragment != null && mesageFragment.isVisible()) {
                    mesageFragment.refresh();
                    mBottombar.showRedPointNotice(View.INVISIBLE);

                }
            }
        });

    }


    public void hasNotice() {
        HttpManager.postHasHeaderNoParam(HttpManager.QUERY_RED_POINT_URL, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = JsonUtil.getJsonObject(result, i);
                    String moduleCode = JsonUtil.getString(jsonObject, "moduleCode");
                    if (!TextUtils.isEmpty(moduleCode)) {
                        if (moduleCode.equals("app_business_message")) {
                            SharePreferenceUtil.setHasNewSellBusinessPush(true);
                            mBottombar.showRedPointNotice(View.VISIBLE);
                        } else {
                            SharePreferenceUtil.setHasNewSellBusinessPush(false);
                            mBottombar.showRedPointNotice(View.INVISIBLE);
                        }
                        if (moduleCode.equals("app_course_appoint_info")) {
                            Logger.i(TAG, "有排课信息推送");
                            SharePreferenceUtil.setHasNewYueKePush(true);
                        } else {
                            SharePreferenceUtil.setHasNewYueKePush(false);
                            Logger.i(TAG, "无排课信息推送");
                        }
                        if (moduleCode.equals("app_reception")) {
                            SharePreferenceUtil.setHasNewJiedaiPush(true);
                        } else {
                            SharePreferenceUtil.setHasNewJiedaiPush(false);

                        }
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initJPush() {
        String userId = SharePreferenceUtil.getUserId();
        //设置别名和分组
        JPushInterface.init(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());
        setAlias(userId + "");
    }


    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        if (TextUtils.isEmpty(alias)) {
            return;
        }
        if (!CommonUtil.isValidTagAndAlias(alias)) {
            return;
        }
        JPushTagAliasOperatorHelper.TagAliasBean tagAliasBean = new JPushTagAliasOperatorHelper.TagAliasBean(JPushTagAliasOperatorHelper.ACTION_SET, alias, true);

        JPushTagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);
        JPushInterface.resumePush(getApplicationContext());
    }

    /**
     * 监听按键的点击
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if ((secondTime - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回键退出程序!", Toast.LENGTH_SHORT).show();
                mExitTime = secondTime;
                return true;
            } else {
                CustomApplication.instance.exitApp();
            }
        }
        return true;
    }


    @Override
    public void selectTab(int index) {
        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);


        selectedIndex = index;
        mBottombar.setBottomBarStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllIndex(transaction);
        switch (index) {
            case 0:
                if (!workFragment.isAdded()) {
                    // 如果WorkFragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.fl_home, workFragment, FRAGMENT_TAG[index]);
                } else {

                    transaction.show(workFragment);
                }
                // 如果WorkFragment不为空，则直接将它显示出来
                AccessStatisticsRequestBody body1 = new AccessStatisticsRequestBody("app_workbench", version);
                HttpManager.postAccessStatistics(body1, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
                break;
            case 1:
                if (!mesageFragment.isAdded()) {
                    // 如果mesageFragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.fl_home, mesageFragment, FRAGMENT_TAG[index]);
                    transaction.show(mesageFragment);
                }
                // 如果mesageFragment不为空，则直接将它显示出来
                transaction.show(mesageFragment);
                AccessStatisticsRequestBody body2 = new AccessStatisticsRequestBody("app_business_message", version);
                HttpManager.postAccessStatistics(body2, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
                break;
            case 2:
                if (!mineFragment.isAdded()) {
                    // 如果MimeFragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.fl_home, mineFragment, FRAGMENT_TAG[index]);
                    transaction.show(mineFragment);

                } else {
                    // 如果MimeFragment不为空，则直接将它显示出来
                    transaction.show(mineFragment);
                }
                AccessStatisticsRequestBody body3 = new AccessStatisticsRequestBody("app_my", version);
                HttpManager.postAccessStatistics(body3, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
                break;
        }
        transaction.commit();
    }


    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        if (workFragment != null && workFragment.isAdded()) {
            fragmentTransaction.hide(workFragment);
        }
        if (mesageFragment != null && mesageFragment.isAdded()) {
            fragmentTransaction.hide(mesageFragment);
        }
        if (mineFragment != null && mineFragment.isAdded()) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        if (resultCode == 1234 && mineFragment != null) {
            mineFragment.onActivityResult(requestCode, resultCode, data);
        } else {

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int push_message = intent.getIntExtra("push_message", 0);
        if (push_message == 2) {
            Logger.i(TAG, "push_message");
            selectTab(push_message);
            mBottombar.showRedPointNotice(View.INVISIBLE);
            ClearRedPointUtil.clearBusinessNotice(getLifecycle());


        }
    }
}