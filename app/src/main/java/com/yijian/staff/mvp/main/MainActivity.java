package com.yijian.staff.mvp.main;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.jpush.JPushTagAliasOperatorHelper;
import com.yijian.staff.mvp.message.MessageFragment;
import com.yijian.staff.mvp.mine.MineFragment;
import com.yijian.staff.mvp.report.ReportingFragment;
import com.yijian.staff.mvp.work.WorkFragment;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.service.NetworkService;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.mvp.base.BaseActivity;
import com.yijian.staff.mvp.main.contract.MainContract;
import com.yijian.staff.mvp.main.presenter.MainPresenter;
import com.yijian.staff.widget.Bottombar;


import cn.jpush.android.api.JPushInterface;

import static com.yijian.staff.jpush.JPushTagAliasOperatorHelper.sequence;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, Bottombar.OnClickBottomButtonListener {

    protected boolean mNetworkStateLogin = false;


    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"WorkFragment", "ReportingFragment", "ViperFragemnt", "MimeFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private int selectedIndex = 0;
    protected long mExitTime;


    private WorkFragment workFragment;
    private ReportingFragment reportingFragment;
    private MessageFragment viperFragment;
    private MineFragment mineFragment;
    private Bottombar mBottombar;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRESELECTEDINDEX, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mBottombar = findViewById(R.id.bottom_bar);
        mBottombar.setmListener(this);

        if (savedInstanceState == null) {
            // 默认选中0
            selectTab(0);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            workFragment = (WorkFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            reportingFragment = (ReportingFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            viperFragment = (MessageFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[3]);


            //读取上一次界面Save的时候tab选中的状态
            int lastSelectedIndex = savedInstanceState.getInt(PRESELECTEDINDEX, selectedIndex);

            // 选择上一次保存的Fragment界面
            selectTab(lastSelectedIndex);
        }
    }


    private void init() {

        initJPush();

//        if (LogicService.selectManager().loadmSelectAreaInfoList().size() > 0) {
        mNetworkStateLogin = true;
//        } else {
//            mNetworkStateLogin = false;
//        }

        startupNetworkCheckService();
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
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        return true;
    }


    @Override
    public void selectTab(int index) {
        selectedIndex = index;
        mBottombar.setBottomBarStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllIndex(transaction);
        switch (index) {
            case 0:
                if (workFragment == null) {
                    // 如果WorkFragment为空，则创建一个并添加到界面上
                    workFragment = WorkFragment.getInstance();
                    transaction.add(R.id.fl_home, workFragment, FRAGMENT_TAG[index]);
                } else {
                    // 如果WorkFragment不为空，则直接将它显示出来
                    transaction.show(workFragment);
                }
                break;
            case 1:

                if (reportingFragment == null) {
                    // 如果ReportingFragment为空，则创建一个并添加到界面上
                    reportingFragment = ReportingFragment.getInstance();
                    transaction.add(R.id.fl_home, reportingFragment, FRAGMENT_TAG[index]);
                } else {
                    // 如果ReportingFragment不为空，则直接将它显示出来
                    transaction.show(reportingFragment);
                }

                break;
            case 2:
                if (viperFragment == null) {
                    // 如果ViperFragment为空，则创建一个并添加到界面上
                    viperFragment = MessageFragment.getInstance();
                    transaction.add(R.id.fl_home, viperFragment, FRAGMENT_TAG[index]);
                } else {
                    // 如果ViperFragment不为空，则直接将它显示出来
                    transaction.show(viperFragment);
                }
                break;
            case 3:
                if (mineFragment == null) {
                    // 如果MimeFragment为空，则创建一个并添加到界面上
                    mineFragment = MineFragment.getInstance();
                    transaction.add(R.id.fl_home, mineFragment, FRAGMENT_TAG[index]);
                } else {
                    // 如果MimeFragment不为空，则直接将它显示出来
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }


    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        Fragment fragment = WorkFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragment = ReportingFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragment = MessageFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);

        }
        fragment = MineFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }

    protected void initInject() {
        getActivityComponent().inject(this);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NetworkService.NetBind bind = (NetworkService.NetBind) iBinder;
            NetworkService service = bind.getNetwrokService();
            service.setOnGetConnectState(isConnected -> {
                if (isConnected) {
                    if (!mNetworkStateLogin) {
                        mNetworkStateLogin = true;

                        //TODO 无网到有网
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };


    private void startupNetworkCheckService() {
        Intent intent = new Intent(this, NetworkService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
        }
    }

}