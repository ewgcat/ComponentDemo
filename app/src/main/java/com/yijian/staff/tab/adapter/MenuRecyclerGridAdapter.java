package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.tab.adapter.holder.MenuRecyclerGridHolder;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.recyclerview.BaseSimpleRecyclerAdapter;

import java.util.List;


/**
 * 描述:编辑页面主体元素的子元素适配器
 * <p>
 * 作者:陈俊森
 * 创建时间:2017年11月03日 17:37
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class MenuRecyclerGridAdapter extends BaseSimpleRecyclerAdapter<MenuRecyclerGridHolder, MenuItem> {
    private Context context;

    public MenuRecyclerGridAdapter(List<MenuItem> recyclerItems,Context context) {
        super(recyclerItems,context);
        this.context=context;

    }

    @Override
    public MenuRecyclerGridHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new MenuRecyclerGridHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_grid, parent, false));
    }

    @Override
    public void bindViewHolder(MenuRecyclerGridHolder holder, MenuItem item) {

        Glide.with(context).load(item.getIcon()).into(holder.iv_icon);
        holder.tv_name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mRecyclerItems == null ? 0 : mRecyclerItems.size();
    }
}
