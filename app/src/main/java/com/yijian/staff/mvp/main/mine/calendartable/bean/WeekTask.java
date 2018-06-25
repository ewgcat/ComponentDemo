package com.yijian.staff.mvp.main.mine.calendartable.bean;

import com.yijian.staff.mvp.main.mine.calendartable.bean.DayTask;

import java.util.List;

/**
 * Created by The_P on 2018/3/22.
 */

public class WeekTask {
    private List<DayTask> dataTaskList;
    private String name;

    public List<DayTask> getDataTaskList() {
        return dataTaskList;
    }

    public void setDataTaskList(List<DayTask> dataTaskList) {
        this.dataTaskList = dataTaskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
