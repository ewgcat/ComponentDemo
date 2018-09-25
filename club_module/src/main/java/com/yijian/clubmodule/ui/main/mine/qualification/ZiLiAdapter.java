package com.yijian.clubmodule.ui.main.mine.qualification;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.clubmodule.R;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/17 16:09:05
 */
public class ZiLiAdapter extends RecyclerView.Adapter<ZiLiAdapter.ViewHolder> {
    private List<String> authList;

    public ZiLiAdapter(List<String> authList) {
        this.authList = authList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zi_li, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(authList.get(position));
    }

    @Override
    public int getItemCount() {
        return authList == null ? 0 : authList.size();
    }

    public void update(List<String> authList) {
        this.authList = authList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
