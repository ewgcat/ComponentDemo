package com.yijian.clubmodule.ui.vipermanage.viper.detail.formatoroutdate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.clubmodule.R;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/6 16:24:27
 */
public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.ViewHolder> {
    List<String> privateCourses;

    public StringListAdapter(List<String> privateCourses) {
        this.privateCourses = privateCourses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        ViewHolder itemViewHolder = new ViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_sijiao_class.setText(privateCourses.get(position));
    }

    @Override
    public int getItemCount() {
        return privateCourses == null ? 0 : privateCourses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sijiao_class;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_sijiao_class=   itemView.findViewById(R.id.tv_sijiao_class);
        }

    }
}
