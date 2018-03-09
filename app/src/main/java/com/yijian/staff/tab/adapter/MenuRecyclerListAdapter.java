package com.yijian.staff.tab.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.tab.adapter.holder.MenuEditRecyclerListHolder;
import com.yijian.staff.tab.entity.EditItem;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnAddListener;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.listener.OnShowEditIconListener;
import com.yijian.staff.tab.recyclerview.BaseSimpleRecyclerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:编辑页面的列表适配器
 * <p>
 * 作者:陈俊森
 * 创建时间:2017年11月03日 17:47
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class MenuRecyclerListAdapter extends BaseSimpleRecyclerAdapter<MenuEditRecyclerListHolder, EditItem> {
    private Map<String, MenuRecyclerGridAdapter> mAdapterMap = new HashMap<>();//用于存放子列表的adapter的map
    private Context context;


    private OnDeleteListener onDeleteListener;
    private OnAddListener onAddListener;

    private OnShowEditIconListener onShowEditIconListener;

    public void setOnShowEditIconListener(OnShowEditIconListener onShowEditIconListener) {
        this.onShowEditIconListener = onShowEditIconListener;
    }
    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }



    public MenuRecyclerListAdapter(List<EditItem> recyclerItems, Context context) {
        super(recyclerItems, context);
        this.context=context;
    }

    @Override
    public MenuEditRecyclerListHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new MenuEditRecyclerListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_edit, parent, false));
    }

    @Override
    public void bindViewHolder(MenuEditRecyclerListHolder holder, EditItem item) {
        holder.tv_group_name.setText(item.getGroupTitle());
        MenuRecyclerGridAdapter adapter = new MenuRecyclerGridAdapter(item.getMenuItemList(),context);
        adapter.setOnAddListener(onAddListener);
        adapter.setOnDeleteListener(onDeleteListener);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(holder.recyclerView.getContext(), 4));
        holder.recyclerView.setAdapter(adapter);
        mAdapterMap.put(item.getGroup(), adapter);
    }

    @Override
    public int getItemCount() {
        return mRecyclerItems == null ? 0 : mRecyclerItems.size();
    }

    public void notifyChildDataChanged(String group, MenuItem item) {
        MenuRecyclerGridAdapter adapter = mAdapterMap.get(group);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void notifyChildDataAdded(String group, MenuItem item) {
        MenuRecyclerGridAdapter adapter = mAdapterMap.get(group);
        if (adapter != null) {
            if (!adapter.getRecyclerItems().contains(item)) {
                adapter.getRecyclerItems().add(item);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void notifyChildDataRemoved(String group, MenuItem item) {
        MenuRecyclerGridAdapter adapter = mAdapterMap.get(group);
        if (adapter != null) {
            adapter.getRecyclerItems().remove(item);
            //TODO
            adapter.notifyDataSetChanged();
        }
    }


}
