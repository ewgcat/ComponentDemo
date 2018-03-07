package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.tab.adapter.holder.MenuHeaderRecyclerGridHolder;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.recyclerview.BaseDraggableRecyclerAdapter;

import java.util.List;

/**
 * 描述:编辑页面的头部的子元素适配器
 * <p>
 * 作者:陈俊森
 * 创建时间:2017年11月03日 17:07
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class MenuHeaderRecyclerGridAdapter extends BaseDraggableRecyclerAdapter<MenuHeaderRecyclerGridHolder, MenuItem> {
    private boolean showEditIcon;
    private OnDeleteClickListener mOnDeleteClickListener;
    private Context context;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        mOnDeleteClickListener = onDeleteClickListener;
    }

    /**
     * 可以拖拽的RecyclerView适配器
     *
     * @param recyclerItems 数据列表
     * @param recyclerView  与改适配器匹配的RecyclerView
     */
    public MenuHeaderRecyclerGridAdapter(List<MenuItem> recyclerItems, RecyclerView recyclerView, boolean showEditIcon, Context context) {
        super(recyclerItems, recyclerView,context);
        this.showEditIcon=showEditIcon;
        this.context=context;
    }

    public void setShowEditIcon(Boolean showEditIcon){
        this.showEditIcon=showEditIcon;
        notifyDataSetChanged();
    }
    @Override
    public MenuHeaderRecyclerGridHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new MenuHeaderRecyclerGridHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_grid_func,parent,false));
    }

    @Override
    public void bindViewHolder(final MenuHeaderRecyclerGridHolder holder, MenuItem item) {
        final MenuItem menuItem=item;
        holder.tv_delete.setVisibility(showEditIcon? View.VISIBLE:View.GONE);
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnDeleteClickListener!=null){
                    mOnDeleteClickListener.onDeleteClick(v,menuItem,holder.getAdapterPosition());
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

    /**
     * 描述:删除脚标单击监听器
     *
     * <br>作者: 陈俊森
     * <br>创建时间: 2017/11/6 0006 14:54
     * <br>邮箱: chenjunsen@outlook.com
     * @version 1.0
     */
    public interface OnDeleteClickListener{
        void onDeleteClick(View v, MenuItem item, int position);
    }
}
