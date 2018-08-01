package com.yijian.staff.mvp.taskcenter.visit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.RankBean;
import com.yijian.staff.util.ImageLoader;

import java.util.List;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/21 15:04:28
 */
public class VisitRankListAdatper extends RecyclerView.Adapter<VisitRankListAdatper.ViewHolder> {
    private List<RankBean> rankBeanList;
    private Context context;

    public VisitRankListAdatper(Context context, List<RankBean> rankBeanList) {
        this.context = context;
        this.rankBeanList = rankBeanList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(context, rankBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return rankBeanList == null ? 0 : rankBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_header;
        private TextView tv_name;
        private TextView tv_completed_precent;
        private TextView tv_rank_position;


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            tv_name = view.findViewById(R.id.tv_name);
            tv_completed_precent = view.findViewById(R.id.tv_completed_precent);
            tv_rank_position = view.findViewById(R.id.tv_rank_position);
        }

        public void bind(Context context, RankBean rankBean) {
            ImageLoader.setHeadImageResource(rankBean.getHeadImg(), context, iv_header);
            tv_name.setText(rankBean.getName());
            tv_completed_precent.setText("完成率: "+rankBean.getCompletedPrecent()+"%");
            tv_rank_position.setText(rankBean.getTvRankPosition());


        }


    }
}
