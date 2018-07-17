package com.yijian.staff.mvp.workspace.commen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;

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

        TextView tv_role;
        Button btn_start;
        Button btn_record;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_role = itemView.findViewById(R.id.tv_role);
            btn_start = itemView.findViewById(R.id.btn_start);
            btn_record = itemView.findViewById(R.id.btn_record);
        }

        public void bind(int position){
            tv_role.setText("Item"+position);
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"item 开始测试==="+position,Toast.LENGTH_SHORT).show();
                }
            });
            btn_record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"item 测评记录==="+position,Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext,WorkSpaceRecordActivity.class));
                }
            });
        }

    }

}
