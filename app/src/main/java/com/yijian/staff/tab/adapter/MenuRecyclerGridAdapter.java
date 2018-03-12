package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.h6ah4i.android.widget.advrecyclerview.headerfooter.AbstractHeaderFooterWrapperAdapter;
import com.yijian.staff.R;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.adapter.holder.MenuRecyclerGridHolder;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnAddListener;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.recyclerview.BaseSimpleRecyclerAdapter;
import com.yijian.staff.tab.recyclerview.OnRecyclerItemLongClickListener;

import java.util.List;


/**
 * 描述:编辑页面主体元素的子元素适配器
 */
public class MenuRecyclerGridAdapter extends BaseSimpleRecyclerAdapter<MenuRecyclerGridHolder, MenuItem> {
    private Context context;
    private OnDeleteListener onDeleteListener;
    private OnAddListener onAddListener;
    protected OnRecyclerItemLongClickListener<MenuItem> mOnRecyclerItemLongClickListener;
    public void setOnRecyclerItemLongClickListener(OnRecyclerItemLongClickListener onRecyclerItemLongClickListener) {
        mOnRecyclerItemLongClickListener = onRecyclerItemLongClickListener;
    }

    public void update(MenuItem item){
        for (int i = 0; i < mRecyclerItems.size(); i++) {
            MenuItem menuItem = mRecyclerItems.get(i);
            if (menuItem.getName().equals(item.getName())){
                menuItem.setType(item.getType());
            }
        }
        notifyDataSetChanged();

    }
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
        boolean showEditIcon = SharePreferenceUtil.getShowEditIcon();

        Glide.with(context).load(item.getIcon()).into(holder.iv_icon);
        holder.tv_name.setText(item.getName());
        int type = item.getType();
        if (showEditIcon) {
            if (type == 0) {
                holder.iv_add.setVisibility(View.GONE);
                holder.iv_delete.setVisibility(View.VISIBLE);
            } else {
                holder.iv_delete.setVisibility(View.GONE);
                holder.iv_add.setVisibility(View.VISIBLE);
            }
        } else {
            holder.iv_add.setVisibility(View.GONE);
            holder.iv_delete.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.iv_add.getVisibility() == View.VISIBLE) {
                    item.setType(0);
                    holder.iv_add.setVisibility(View.GONE);
                    holder.iv_delete.setVisibility(View.VISIBLE);
                    if (onAddListener != null) {
                        onAddListener.onAddClick(v, item, holder.getAdapterPosition());
                    }
                } else if (holder.iv_delete.getVisibility() == View.VISIBLE) {
                    item.setType(1);
                    holder.iv_delete.setVisibility(View.GONE);
                    holder.iv_add.setVisibility(View.VISIBLE);
                    if (onDeleteListener != null) {
                        onDeleteListener.onDeleteClick(v, item, holder.getAdapterPosition());
                    }
                }else {//非编辑状态
                    ARouter.getInstance().build(item.getPath()).navigation();

                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnRecyclerItemLongClickListener!=null){
                    mOnRecyclerItemLongClickListener.onItemLongClick(v,item,holder.getAdapterPosition(), AbstractHeaderFooterWrapperAdapter.SEGMENT_TYPE_NORMAL);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecyclerItems == null ? 0 : mRecyclerItems.size();
    }


}
