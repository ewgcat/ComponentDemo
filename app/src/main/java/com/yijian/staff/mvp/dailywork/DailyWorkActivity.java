package com.yijian.staff.mvp.dailywork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 考勤
 */
@Route(path = "/test/29")
public class DailyWorkActivity extends AppCompatActivity {

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"ApprovalFragment", "DailyWorkStatusFragment", "MyDailyWorkFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private int selectedIndex = 0;

    private ApprovalFragment approvalFragment;
    private DailyWorkStatusFragment dailyWorkStatusFragment;
    private MyDailyWorkFragment myDailyWorkFragment;

    @BindView(R.id.vg_dailywork)
    RadioGroup vg_dailywork;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRESELECTEDINDEX, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_work);
        ButterKnife.bind(this);

        vg_dailywork.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_approval: //审批
                        selectTab(0);
                        break;
                    case R.id.rb_dailyworkstatus: //考勤情况
                        selectTab(1);
                        break;
                    case R.id.rb_mydailywork: //我的考勤
                        selectTab(2);
                        break;
                }
            }
        });

        if (savedInstanceState == null) {
            // 默认选中0
            selectTab(0);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            approvalFragment = (ApprovalFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            dailyWorkStatusFragment = (DailyWorkStatusFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            myDailyWorkFragment = (MyDailyWorkFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);


            //读取上一次界面Save的时候tab选中的状态
            int lastSelectedIndex = savedInstanceState.getInt(PRESELECTEDINDEX, selectedIndex);

            // 选择上一次保存的Fragment界面
            selectTab(lastSelectedIndex);
        }

    }

    public void selectTab(int index) {
        selectedIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllIndex(transaction);
        switch (index) {
            case 0:
                popFragement(transaction, approvalFragment, index);
                break;
            case 1:
                popFragement(transaction, dailyWorkStatusFragment, index);
                break;
            case 2:
                popFragement(transaction, myDailyWorkFragment, index);
                break;
        }
        transaction.commit();
    }

    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
        if (fragment == null) {
            // 如果ViperFragment为空，则创建一个并添加到界面上
            if (index == 0) {
                approvalFragment = ApprovalFragment.getInstance();
                transaction.add(R.id.fl_dailywork, approvalFragment, FRAGMENT_TAG[index]);
            } else if (index == 1) {
                dailyWorkStatusFragment = DailyWorkStatusFragment.getInstance();
                transaction.add(R.id.fl_dailywork, dailyWorkStatusFragment, FRAGMENT_TAG[index]);
            } else if (index == 2) {
                myDailyWorkFragment = MyDailyWorkFragment.getInstance();
                transaction.add(R.id.fl_dailywork, myDailyWorkFragment, FRAGMENT_TAG[index]);
            }
        } else {
            // 如果ViperFragment不为空，则直接将它显示出来
            transaction.show(fragment);
        }
    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        Fragment fragment = ApprovalFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragment = DailyWorkStatusFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragment = MyDailyWorkFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);

        }
    }


}
