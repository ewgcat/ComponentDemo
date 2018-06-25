package com.yijian.staff.mvp.reception.step1.bean;

import com.yijian.staff.mvp.reception.step1.recyclerView.ParentImp;

import java.util.List;

/**
 * Created by The_P on 2018/3/12.
 */

public class QuestionEntry implements ParentImp<QuestionOption> {
    private String name;
    private int id;


    private List<QuestionOption> list;

    public QuestionEntry(String name, int id, List<QuestionOption> list) {
        this.name = name;
        this.id = id;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionOption> getList() {
        return list;
    }

    public QuestionOption getQuestionOption(int position) {
        return list.get(position);
    }


    public void setList(List<QuestionOption> list) {
        this.list = list;
    }


    @Override
    public List<QuestionOption> getChildList() {
        return list;
    }
}
