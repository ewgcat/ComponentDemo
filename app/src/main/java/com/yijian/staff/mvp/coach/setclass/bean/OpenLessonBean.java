package com.yijian.staff.mvp.coach.setclass.bean;

import java.util.List;

/**
 * Created by The_P on 2018/3/23.
 */

public class OpenLessonBean {
    String name;
    List<TypeOfActionTitle> actionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypeOfActionTitle> getActionList() {
        return actionList;
    }

    public void setActionList(List<TypeOfActionTitle> actionList) {
        this.actionList = actionList;
    }
}
