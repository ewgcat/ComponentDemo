package com.yijian.staff.mvp.coach.preparelessons.createlession;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.TempBean;

import java.util.ArrayList;
import java.util.List;

public class PrepareLessonDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TempBean.TemplateContextListBean> templateContextListBeans = new ArrayList<>();

    public void resetList(List<TempBean.TemplateContextListBean> list) {
        this.templateContextListBeans.clear();
        this.templateContextListBeans = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prepare_lesson_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(templateContextListBeans.get(position), position);
    }

    @Override
    public int getItemCount() {
        return templateContextListBeans == null ? 0 : templateContextListBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_sort;
        private TextView tv_moName;
        private TextView tv_groupTime;
        private TextView tv_moDifficulty;
        private TextView tv_moParts;
        private TextView tv_moApplianceName;
        private View view_line;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_sort = itemView.findViewById(R.id.tv_sort);
            tv_moName = itemView.findViewById(R.id.tv_moName);
            tv_groupTime = itemView.findViewById(R.id.tv_groupTime);
            tv_moDifficulty = itemView.findViewById(R.id.tv_moDifficulty);
            tv_moParts = itemView.findViewById(R.id.tv_moParts);
            tv_moApplianceName = itemView.findViewById(R.id.tv_moApplianceName);
            view_line = itemView.findViewById(R.id.view_line);
        }

        public void bind(TempBean.TemplateContextListBean templateContextListBean, int position) {
            tv_sort.setText(templateContextListBean.getSort() + "");
            tv_moName.setText(templateContextListBean.getMoName());
            tv_groupTime.setText(templateContextListBean.getGroupNum() + "x" + templateContextListBean.getGroupTime());
            tv_moDifficulty.setText(templateContextListBean.getMoDifficultyDesc());
            tv_moParts.setText(templateContextListBean.getMoPartsDesc());
            tv_moApplianceName.setText(templateContextListBean.getMoApplianceName());
            if (position >= (templateContextListBeans.size() - 1)) {
                view_line.setVisibility(View.GONE);
            }
        }

    }

}
