package com.yijian.staff.mvp.reception.step2.step2Bean;


import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.Parent;

import java.util.List;

/**
 * Created by The_P on 2018/3/13.
 */

public class Parent_Demo implements Parent<Child_Demo> {
private String name;
private List<Child_Demo> child_demos;
private int id;


    public Parent_Demo(String name, List<Child_Demo> child_demos, int id) {
            this.name=name;
            this.child_demos=child_demos;
            this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Child_Demo> getChildList() {
        return child_demos;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
