package com.yijian.staff.mvp.workspace.commen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;

public class SearchAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workspace_search,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rel_item;
        TextView tv_role;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_role = itemView.findViewById(R.id.tv_role);
            rel_item = itemView.findViewById(R.id.rel_item);
        }

        public void bind(int position){
            tv_role.setText("Item"+position);
            rel_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.name = "小明";
                    ActivityUtils.startActivity(mContext, SearchOprationActivity.class);

                }
            });
        }

    }

}
