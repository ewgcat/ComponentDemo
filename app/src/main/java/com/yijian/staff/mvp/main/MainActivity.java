package com.yijian.staff.mvp.main;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.message.MessageFragment;
import com.yijian.staff.mvp.mine.MineFragment;
import com.yijian.staff.mvp.report.ReportingFragment;
import com.yijian.staff.mvp.work.WorkFragment;
import com.yijian.staff.util.system.StatusBarUtil;
import com.yijian.staff.viewmodel.base.BaseActivity;
import com.yijian.staff.mvp.main.contract.MainContract;
import com.yijian.staff.mvp.main.presenter.MainPresenter;
import com.yijian.staff.widget.Bottombar;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View  , Bottombar.OnClickBottomButtonListener {


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
        StatusBarUtil.setLightStatusBar(this, Color.parseColor("#3699FC"));

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


}
