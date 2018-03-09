package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.tab.adapter.holder.MenuHeaderRecyclerGridHolder;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.recyclerview.BaseDraggableRecyclerAdapter;

import java.util.List;

/**
 * 描述:编辑页面的头部的子元素适配器

 *
 * @version 1.0
 */
public class MenuHeaderRecyclerGridAdapter extends BaseDraggableRecyclerAdapter<MenuHeaderRecyclerGridHolder, MenuItem> {
    private boolean showEditIcon;
    private Context context;

    private OnDeleteListener onDeleteListener;

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    /**
     * 可以拖拽的RecyclerView适配器
     *
     * @param recyclerItems 数据列表
     * @param recyclerView  与改适配器匹配的RecyclerView
     */
    public MenuHeaderRecyclerGridAdapter(List<MenuItem> recyclerItems, RecyclerView recyclerView,  Context context) {
        super(recyclerItems, recyclerView,context);
        this.context=context;
    }

    public void setShowEditIcon(boolean showEditIcon){
        this.showEditIcon=showEditIcon;
        notifyDataSetChanged();
    }
    @Override
    public MenuHeaderRecyclerGridHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new MenuHeaderRecyclerGridHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frequently_menu_grid,parent,false));
    }

    @Override
    public void bindViewHolder(final MenuHeaderRecyclerGridHolder holder, MenuItem item) {
        Log.i("TEST","MenuHeaderRecyclerGridAdapter   showEditIcon="+showEditIcon);
        final MenuItem menuItem=item;
        holder.iv_delete.setVisibility(showEditIcon? View.VISIBLE:View.GONE);
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeleteListener!=null){
                    onDeleteListener.onDeleteClick(v,menuItem,holder.getAdapterPosition());
                }
            }
        });
        holder.tv_name.setText(item.getName());
        Glide.with(context).load(item.getIcon()).into(holder.iv_icon);
    }

    @Override
    public int getItemCount() {
        return mRecyclerItems == null ? 0 : mRecyclerItems.size();
    }

    @Override
    public void onItemDragStarted(int position) {

    }

    @Override
    public void onItemDragFinished(int fromPosition, int toPosition, boolean result) {

    }


}
