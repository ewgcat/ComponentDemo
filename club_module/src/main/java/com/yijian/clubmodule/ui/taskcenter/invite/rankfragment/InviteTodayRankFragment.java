package com.yijian.clubmodule.ui.taskcenter.invite.rankfragment;

import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.ui.taskcenter.invite.InviteRankListAdatper;
import com.yijian.clubmodule.ui.taskcenter.list.RankListActivity;
import com.yijian.commonlib.base.mvc.MvcBaseFragment;
import com.yijian.clubmodule.bean.RankBean;

import java.util.ArrayList;
import java.util.List;




public class InviteTodayRankFragment extends MvcBaseFragment {
    ImageView ivHeader;
    TextView tvName;
    TextView tvCompletedPrecent;
    TextView tvRankPosition;
    RecyclerView rv;

    private List<RankBean> rankBeanList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_invite_today_rank;
    }

    @Override
    public void initView() {
        rankBeanList.clear();
        ivHeader = getRootView().findViewById(R.id.iv_header);
        tvName = getRootView().findViewById(R.id.tv_name);
        tvCompletedPrecent = getRootView().findViewById(R.id.tv_completed_precent);
        tvRankPosition = getRootView().findViewById(R.id.tv_rank_position);
        rv = getRootView().findViewById(R.id.rv);
        getRootView().findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RankListActivity.class);
                intent.putExtra("RANK_TYPE",RankListActivity.MONTH_RANK_TYPE);
                startActivity(intent);

            }
        });
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
        InviteRankListAdatper adatper = new InviteRankListAdatper(getContext(), rankBeanList);
        rv.setAdapter(adatper);
    }





}
