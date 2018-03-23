package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.adapter.holder.MenuHeaderRecyclerGridHolder;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.recyclerview.BaseDraggableRecyclerAdapter;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.Logger;

import java.util.List;

/**
 * 描述:编辑页面的头部的子元素适配器
 *
 * @version 1.0
 */
public class MenuHeaderRecyclerGridAdapter extends BaseDraggableRecyclerAdapter<MenuHeaderRecyclerGridHolder, MenuItem> {

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
    public MenuHeaderRecyclerGridAdapter(List<MenuItem> recyclerItems, RecyclerView recyclerView, Context context) {
        super(recyclerItems, recyclerView, context);
        this.context = context;
    }


    @Override
    public MenuHeaderRecyclerGridHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new MenuHeaderRecyclerGridHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frequently_menu_grid, parent, false));
    }

    @Override
    public void bindViewHolder(final MenuHeaderRecyclerGridHolder holder, MenuItem item) {
        boolean showEditIcon = SharePreferenceUtil.getShowEditIcon();

        final MenuItem menuItem = item;
        if (showEditIcon) {
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.tv_count.setVisibility(View.GONE);
        }else {
            int count = menuItem.getCount();
            if (count > 0) {
                if (count < 100) {
                    holder.tv_count.setText(""+count);
                }else {
                    holder.tv_count.setText("99+");
                }
                holder.tv_count.setVisibility(View.VISIBLE);
            } else {
                holder.tv_count.setVisibility(View.GONE);
            }
        }
        holder.iv_delete.setVisibility(showEditIcon ? View.VISIBLE : View.GONE);
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteListener != null) {
                    onDeleteListener.onDeleteClick(v, menuItem, holder.getAdapterPosition());
                }
            }
        });
        holder.tv_name.setText(item.getName());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(item.getIcon()).apply(options).into(holder.iv_icon);
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
