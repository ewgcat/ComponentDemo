package com.yijian.staff.mvp.taskcenter.invite.rankfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.bean.RankBean;
import com.yijian.staff.mvp.taskcenter.invite.InviteRankListAdatper;
import com.yijian.staff.mvp.taskcenter.list.RankListActivity;
import com.yijian.staff.mvp.taskcenter.list.RankListAdatper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class InviteMonthRankFragment extends MvcBaseFragment {


    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_completed_precent)
    TextView tvCompletedPrecent;
    @BindView(R.id.tv_rank_position)
    TextView tvRankPosition;
    @BindView(R.id.rv)
    RecyclerView rv;
    private List<RankBean> rankBeanList=new ArrayList<>();



    @Override
    public int getLayoutId() {
        return R.layout.fragment_invite_month_rank;
    }

    @Override
    public void initView() {
        rankBeanList.clear();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局
        rv.setLayoutManager(layoutmanager);
        for (int i = 0; i <3; i++) {
            RankBean rankBean = new RankBean();
            rankBean.setName("会籍"+(i+1));
            rankBean.setCompletedPrecent((100-(30*i))+"");
            rankBean.setTvRankPosition((i+1)+"");
            rankBeanList.add(rankBean);
        }
        InviteRankListAdatper adatper = new InviteRankListAdatper(getContext(), rankBeanList);
        rv.setAdapter(adatper);
    }
    @OnClick(R.id.tv_more)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), RankListActivity.class);
        intent.putExtra("RANK_TYPE",RankListActivity.MONTH_RANK_TYPE);
        startActivity(intent);

    }




}
