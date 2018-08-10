package com.yijian.staff.mvp.taskcenter.invite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.invate.InvateListActivity;
import com.yijian.staff.mvp.taskcenter.invite.rankfragment.InviteMonthRankFragment;
import com.yijian.staff.mvp.taskcenter.invite.rankfragment.InviteTodayRankFragment;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.TaskCircleProgressBar;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:39:24
 */
@SuppressLint("ValidFragment")
public class InviteTaskCenterFragment extends Fragment implements View.OnClickListener {


    TaskCircleProgressBar taskProgress;
    TextView tvTaskContent;
    ProgressBar progressBar;
    TextView tvTaskStatus;
    TextView tvTodayRank;
    TextView tvMonthRank;
    private InviteTodayRankFragment inviteTodayRankFragment;
    private InviteMonthRankFragment inviteMonthRankFragment;








    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.invite_task_center_fragment, container, false);
        initView(view);
        return view;
    }




    public void initView(View view) {

        taskProgress=  view.findViewById(R.id.task_progress);
        tvTaskContent=  view.findViewById(R.id.tv_task_content);
        tvTaskStatus=  view.findViewById(R.id.tv_task_status);
        tvTodayRank=  view.findViewById(R.id.tv_today_rank);
        tvMonthRank=  view.findViewById(R.id.tv_month_rank);
        progressBar=  view.findViewById(R.id.progress_bar);

        tvTaskStatus.setOnClickListener(this);
        tvTodayRank.setOnClickListener(this);
        tvMonthRank.setOnClickListener(this);

        taskProgress.setProgress(60);
        tvTaskContent.setText("今日需邀约会员10个");
        tvTaskStatus.setText("未完成");
        progressBar.setProgress(100);
        changeFragment(0);
    }


    private void changeFragment(int status) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllIndex(fragmentTransaction);
        if (status == 0) {
            tvTodayRank.setTextColor(Color.parseColor("#333333"));
            tvMonthRank.setTextColor(Color.parseColor("#999999"));
            if (inviteTodayRankFragment == null) {
                inviteTodayRankFragment = new InviteTodayRankFragment();
                fragmentTransaction.add(R.id.fl_container, inviteTodayRankFragment);
            } else {
                fragmentTransaction.show(inviteTodayRankFragment);
            }
        } else {
            tvTodayRank.setTextColor(Color.parseColor("#999999"));
            tvMonthRank.setTextColor(Color.parseColor("#333333"));
            if (inviteMonthRankFragment == null) {
                inviteMonthRankFragment = new InviteMonthRankFragment();
                fragmentTransaction.add(R.id.fl_container, inviteMonthRankFragment);
            } else {
                fragmentTransaction.show(inviteMonthRankFragment);
            }
        }
        fragmentTransaction.commit();
    }

    //隐藏所有的Fragment
    public void hideAllIndex(FragmentTransaction fragmentTransaction) {

        if (inviteTodayRankFragment != null && inviteTodayRankFragment.isAdded()) {
            fragmentTransaction.hide(inviteTodayRankFragment);
        }

        if (inviteMonthRankFragment != null && inviteMonthRankFragment.isAdded()) {
            fragmentTransaction.hide(inviteMonthRankFragment);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_task_status:
                startActivity(new Intent(getContext(),InvateListActivity.class));
                break;
            case R.id.tv_today_rank:
                Logger.i("TEST", "点击了日排名");
                changeFragment(0);
                break;
            case R.id.tv_month_rank:
                Logger.i("TEST", "点击了月排名");
                changeFragment(1);
                break;
        }
    }
}
