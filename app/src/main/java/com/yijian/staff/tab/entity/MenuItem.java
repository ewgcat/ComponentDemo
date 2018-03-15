package com.yijian.staff.tab.entity;

import com.yijian.staff.tab.recyclerview.BaseRecyclerItem;

import java.io.Serializable;


/**
 * 描述:列表子item实体类
 * <p>
 * 作者:cjs
 * 创建时间:2017年11月03日 11:51
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class MenuItem implements BaseRecyclerItem,Serializable{

    /**
     * name : 匕首
     * icon : ic_cold_1
     * desc : 传说的匕首,杀人于无形
     * title : cold_weapon
     */

    private String name;
    private String icon;
    private String path;
    private String title;
    private int count;
    private int  type;//0 已添加在常用列表，1未添加在常用列表

    private int viewType;
    private int itemId;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    @Override
    public long getItemId() {
        return itemId;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
