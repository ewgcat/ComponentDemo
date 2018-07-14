package com.yijian.staff.mvp.huifang.huiji.invitation.list;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huifang.huiji.invitation.list.record.InvitationRecordFragment;
import com.yijian.staff.mvp.huifang.huiji.invitation.list.result.InvitationResultFragment;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/test/6")
public class HuiJiInvitationListActivity extends AppCompatActivity {

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] FRAGMENT_TAG = {"InvitationRecordFragment", "InvitationResultFragment"};
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRESELECTEDINDEX = "PREV_SELECTED_INDEX";
    private int selectedIndex = 0;

    private InvitationRecordFragment invitationRecordFragment;
    private InvitationResultFragment invitationResultFragment;
    private NavigationBar2 navigationBar2;

    @BindView(R.id.lin_invitation_record)
    LinearLayout lin_invitation_record;
    @BindView(R.id.lin_invitation_result)
    LinearLayout lin_invitation_result;
    @BindView(R.id.tv_invitaion_record)
    TextView tv_invitaion_record;
    @BindView(R.id.iv_invitaion_record)
    ImageView iv_invitaion_record;
    @BindView(R.id.tv_invitaion_result)
    TextView tv_invitaion_result;
    @BindView(R.id.iv_invitaion_result)
    ImageView iv_invitaion_result;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRESELECTEDINDEX, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        ButterKnife.bind(this);
        initView();
        selectTab(0);
    }

    private void initView() {
        navigationBar2 = findViewById(R.id.vip_over_navigation_bar2);
        navigationBar2.setTitle("邀约记录");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
    }

    @OnClick({R.id.lin_invitation_record, R.id.lin_invitation_result})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lin_invitation_record: //邀约记录
                selectTab(0);
                break;
            case R.id.lin_invitation_result: //邀约结果
                selectTab(1);
                break;
        }
    }

    public void selectTab(int index) {
        selectedIndex = index;
        setBotoomStyle(index);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllIndex(transaction);
        switch (index) {
            case 0:
                popFragement(transaction, invitationRecordFragment, index);
                break;
            case 1:
                popFragement(transaction, invitationResultFragment, index);
                break;
        }
        transaction.commit();
    }

    public void popFragement(FragmentTransaction transaction, Fragment fragment, int index) {
        if (fragment == null) {
            // 如果ViperFragment为空，则创建一个并添加到界面上
            if (index == 0) {
                invitationRecordFragment = InvitationRecordFragment.getInstance();
                transaction.add(R.id.fl_invitation, invitationRecordFragment, FRAGMENT_TAG[index]);
            } else if (index == 1) {
                invitationResultFragment = InvitationResultFragment.getInstance();
                transaction.add(R.id.fl_invitation, invitationResultFragment, FRAGMENT_TAG[index]);
            }
        } else {
            // 如果ViperFragment不为空，则直接将它显示出来
            transaction.show(fragment);
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

    public void setBotoomStyle(int index) {
        navigationBar2.setTitle(index == 0 ? "邀约记录" : "邀约结果");
        tv_invitaion_record.setTextColor(index == 0 ? Color.parseColor("#1997f8") : Color.parseColor("#666666"));
        tv_invitaion_result.setTextColor(index == 0 ? Color.parseColor("#666666") : Color.parseColor("#1997f8"));

        iv_invitaion_record.setImageResource(index == 0 ? R.mipmap.wt_jilulan : R.mipmap.wt_jilu);
        iv_invitaion_result.setImageResource(index == 0 ? R.mipmap.wt_jieguo : R.mipmap.wt_jieguolan);
    }

}
