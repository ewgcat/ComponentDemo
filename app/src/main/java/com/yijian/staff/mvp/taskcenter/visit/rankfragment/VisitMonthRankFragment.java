package com.yijian.staff.mvp.taskcenter.visit.rankfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.bean.RankBean;
import com.yijian.staff.mvp.taskcenter.list.RankListActivity;
import com.yijian.staff.mvp.taskcenter.list.RankListAdatper;
import com.yijian.staff.mvp.taskcenter.visit.VisitRankListAdatper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class VisitMonthRankFragment extends MvcBaseFragment {


    @BindView(R.id.visit_iv_header)
    ImageView ivHeader;
    @BindView(R.id.visit_tv_name)
    TextView tvName;
    @BindView(R.id.visit_tv_completed_precent)
    TextView tvCompletedPrecent;
    @BindView(R.id.visit_tv_rank_position)
    TextView tvRankPosition;
    @BindView(R.id.visit_rv)
    RecyclerView rv;
    private List<RankBean> rankBeanList=new ArrayList<>();



    @Override
    public int getLayoutId() {
        return R.layout.fragment_visit_month_rank;
    }

    @Override
    public void initView() {
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
        VisitRankListAdatper adatper = new VisitRankListAdatper(getContext(), rankBeanList);
        rv.setAdapter(adatper);
    }
    @OnClick(R.id.visit_tv_more)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), RankListActivity.class);
        intent.putExtra("RANK_TYPE",RankListActivity.MONTH_RANK_TYPE);
        startActivity(intent);

    }




}
