package com.yijian.clubmodule.ui.taskcenter.add.rankfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.clubmodule.ui.taskcenter.add.AddViperRankListAdatper;
import com.yijian.clubmodule.ui.taskcenter.list.RankListActivity;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.RankBean;


import java.util.ArrayList;
import java.util.List;


public class AddViperMonthRankFragment extends MvcBaseFragment {


    ImageView ivHeader;
    TextView tvName;
    TextView tvCompletedPrecent;
    TextView tvRankPosition;
    RecyclerView rv;
    private List<RankBean> rankBeanList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_viper_month_rank;
    }

    @Override
    public void initView() {

        ivHeader = findView(R.id.iv_header);
        tvName = findView(R.id.tv_name);
        tvCompletedPrecent = findView(R.id.tv_completed_precent);
        tvRankPosition = findView(R.id.tv_rank_position);
        rv = findView(R.id.rv);
        findView(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RankListActivity.class);
                intent.putExtra("RANK_TYPE", RankListActivity.MONTH_RANK_TYPE);
                startActivity(intent);
            }
        });
        rankBeanList.clear();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局
        rv.setLayoutManager(layoutmanager);
        for (int i = 0; i < 3; i++) {
            RankBean rankBean = new RankBean();
            rankBean.setName("会籍" + (i + 1));
            rankBean.setCompletedPrecent((100 - (30 * i)) + "");
            rankBean.setTvRankPosition((i + 1) + "");
            rankBeanList.add(rankBean);
        }
        AddViperRankListAdatper adatper = new AddViperRankListAdatper(getContext(), rankBeanList);
        rv.setAdapter(adatper);
    }


}
