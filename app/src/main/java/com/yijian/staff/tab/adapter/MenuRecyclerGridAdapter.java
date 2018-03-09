package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.tab.adapter.holder.MenuRecyclerGridHolder;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnAddListener;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.recyclerview.BaseSimpleRecyclerAdapter;

import java.util.List;


/**
 * 描述:编辑页面主体元素的子元素适配器
 */
public class MenuRecyclerGridAdapter extends BaseSimpleRecyclerAdapter<MenuRecyclerGridHolder, MenuItem> {
    private Context context;
    private boolean showEditIcon = false;
    private OnDeleteListener onDeleteListener;
    private OnAddListener onAddListener;

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }


    public MenuRecyclerGridAdapter(List<MenuItem> recyclerItems, Context context) {
        super(recyclerItems, context);
        this.context = context;

    }

    @Override
    public MenuRecyclerGridHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new MenuRecyclerGridHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_grid, parent, false));
    }

    @Override
    public void bindViewHolder(MenuRecyclerGridHolder holder, MenuItem item) {
        Log.i("TEST","MenuRecyclerGridAdapter  showEditIcon ="+showEditIcon);

        Glide.with(context).load(item.getIcon()).into(holder.iv_icon);
        holder.tv_name.setText(item.getName());
        int type = item.getType();
        if (showEditIcon) {
            if (type == 0) {
                holder.iv_add.setVisibility(View.GONE);
                holder.iv_delete.setVisibility(View.VISIBLE);
                holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setType(1);
                        holder.iv_delete.setVisibility(View.GONE);
                        holder.iv_add.setVisibility(View.VISIBLE);
                        if (onDeleteListener != null) {
                            onDeleteListener.onDeleteClick(v, item, holder.getAdapterPosition());
                        }
                    }
                });
            } else {
                holder.iv_delete.setVisibility(View.GONE);
                holder.iv_add.setVisibility(View.VISIBLE);
                holder.iv_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setType(0);
                        holder.iv_add.setVisibility(View.GONE);
                        holder.iv_delete.setVisibility(View.VISIBLE);
                        if (onAddListener != null) {
                            onAddListener.onAddClick(v, item, holder.getAdapterPosition());
                        }
                    }
                });
            }
        } else {
            holder.iv_add.setVisibility(View.GONE);
            holder.iv_delete.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mRecyclerItems == null ? 0 : mRecyclerItems.size();
    }

    public void setShowEditIcon(boolean showEditIcon){
        this.showEditIcon=showEditIcon;
        notifyDataSetChanged();
    }




}
