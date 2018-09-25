package com.yijian.clubmodule.ui.taskcenter.visit.rankfragment;

import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.ui.taskcenter.list.RankListActivity;
import com.yijian.clubmodule.ui.taskcenter.visit.VisitRankListAdatper;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.bean.RankBean;

import java.util.ArrayList;
import java.util.List;




public class VisitTodayRankFragment extends MvcBaseFragment {

    ImageView ivHeader;
    TextView tvName;
    TextView tvCompletedPrecent;
    TextView tvRankPosition;
    RecyclerView rv;
    private List<RankBean> rankBeanList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_visit_today_rank;
    }

    @Override
    public void initView() {
        ivHeader = getRootView().findViewById(R.id.visit_iv_header);
        tvName = getRootView().findViewById(R.id.visit_tv_name);
        tvCompletedPrecent = getRootView().findViewById(R.id.visit_tv_completed_precent);
        tvRankPosition = getRootView().findViewById(R.id.visit_tv_rank_position);
        rv = getRootView().findViewById(R.id.visit_rv);
        getRootView().findViewById(R.id.visit_tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RankListActivity.class);
                intent.putExtra("RANK_TYPE",RankListActivity.TODAY_RANK_TYPE);
                startActivity(intent);
            }
        });
        rankBeanList.clear();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局
        rv.setLayoutManager(layoutmanager);
        for (int i = 0; i < 3; i++) {
            RankBean rankBean = new RankBean();
            rankBean.setName("员工" + (i + 1));
            rankBean.setCompletedPrecent((100 - (30 * i)) + "");
            rankBean.setTvRankPosition((i + 1) + "");
            rankBeanList.add(rankBean);
        }
        VisitRankListAdatper adatper = new VisitRankListAdatper(getContext(), rankBeanList);
        rv.setAdapter(adatper);
    }




}
