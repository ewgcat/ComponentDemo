package com.yijian.staff.mvp.reception.step3.bean;

/**
 * Created by The_P on 2018/4/11.
 */

public class VenueBean extends SelectedBean{
//    id (string, optional): id ,
//    name (string, optional): 名称

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
