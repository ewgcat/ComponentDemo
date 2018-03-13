package com.yijian.staff.mvp.resourceallocation;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.invitation.InvitationRecordFragment;
import com.yijian.staff.mvp.invitation.InvitationResultFragment;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 历史资源分配 (总监角色)
 */
public class HistoryResourceAllocationActivity extends AppCompatActivity {

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"ResourceAllocationFragment", "HistoryAllocationFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private int selectedIndex = 0;

    private ResourceAllocationFragment resourceAllocationFragment;
    private HistoryAllocationFragment historyAllocationFragment;
    private NavigationBar navigationBar;

    @BindView(R.id.lin_resource_allowcation)
    LinearLayout lin_resource_allowcation;
    @BindView(R.id.lin_history_allowcation)
    LinearLayout lin_history_allowcation;
    @BindView(R.id.tv_resource_allowcation)
    TextView tv_resource_allowcation;
    @BindView(R.id.iv_resource_allowcation)
    ImageView iv_resource_allowcation;
    @BindView(R.id.tv_history_allowcation)
    TextView tv_history_allowcation;
    @BindView(R.id.iv_history_allowcation)
    ImageView iv_history_allowcation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_resource_allocation);
        ButterKnife.bind(this);
        initView();
        selectTab(0);
    }

    private void initView() {
        navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("资源分配","#ffffff");
        navigationBar.hideBottomLine();
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }

    @OnClick({R.id.lin_resource_allowcation,R.id.lin_history_allowcation})
    public void click(View v){
        switch(v.getId()){
            case R.id.lin_resource_allowcation: //资源分配
                selectTab(0);
                break;
            case R.id.lin_history_allowcation: //历史分配
                selectTab(1);
                break;
        }
    }

    public void selectTab(int index) {
        selectedIndex = index;
        setBotoomStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        hideAllIndex(transaction);
        switch (index) {
            case 0:
                popFragement(transaction, resourceAllocationFragment, index);
                break;
            case 1:
                popFragement(transaction, historyAllocationFragment, index);
                break;
        }
        transaction.commit();
    }

    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
        if (fragment == null) {
            // 如果ViperFragment为空，则创建一个并添加到界面上
            if (index == 0) {
                resourceAllocationFragment = ResourceAllocationFragment.getInstance();
                transaction.add(R.id.fl_invitation, resourceAllocationFragment, FRAGMENT_TAG[index]);
            } else if (index == 1) {
                historyAllocationFragment = HistoryAllocationFragment.getInstance();
                transaction.add(R.id.fl_invitation, historyAllocationFragment, FRAGMENT_TAG[index]);
            }
        } else {
            // 如果ViperFragment不为空，则直接将它显示出来
            if(index == 0){
//                resourceAllocationFragment.historyResourceAllocationAdatper.setFlag_type(HistoryResourceAllocationAdatper.RESOURCE_TYPE);
//                transaction.show(resourceAllocationFragment);
                transaction.replace(R.id.fl_invitation,resourceAllocationFragment);
            }else if(index == 1){
//                historyAllocationFragment.historyResourceAllocationAdatper.setFlag_type(HistoryResourceAllocationAdatper.RESOURCE_TYPE);
//                transaction.show(historyAllocationFragment);
                transaction.replace(R.id.fl_invitation,historyAllocationFragment);
            }

        }
    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {
        Fragment fragment = InvitationRecordFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragment = InvitationResultFragment.getInstance();
        if (fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
    }

    public void setBotoomStyle(int index){
        navigationBar.setTitle(index == 0?"资源分配":"历史分配","#ffffff");
        tv_resource_allowcation.setTextColor(index == 0? Color.parseColor("#1997f8"):Color.parseColor("#666666"));
        tv_history_allowcation.setTextColor(index == 0? Color.parseColor("#666666"):Color.parseColor("#1997f8"));

        iv_resource_allowcation.setImageResource(index == 0?R.mipmap.fp_ziyuanlan:R.mipmap.fp_ziyuan);
        iv_history_allowcation.setImageResource(index == 0?R.mipmap.fp_lishi:R.mipmap.fp_lishilan);
    }


}
