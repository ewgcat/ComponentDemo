package com.yijian.staff.tab.entity;

import com.yijian.staff.tab.recyclerview.BaseRecyclerItem;

import java.io.Serializable;
import java.util.List;


/**
 * 描述:列表条目的实体类
 * <p>
 * 作者:cjs
 * 创建时间:2017年11月03日 15:03
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class EditItem implements BaseRecyclerItem, Serializable {
    private String mGroup;
    private List<MenuItem> mMenuItemList;

    public EditItem() {
    }

    public EditItem(String group, List<MenuItem> menuItemList) {
        mGroup = group;
        mMenuItemList = menuItemList;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String group) {
        mGroup = group;
    }


    public List<MenuItem> getMenuItemList() {
        return mMenuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        mMenuItemList = menuItemList;
    }

    private int viewType;
    private int itemId;

    @Override
    public int getViewType() {
        return viewType;
    }

    @Override
    public long getItemId() {
        return itemId;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
