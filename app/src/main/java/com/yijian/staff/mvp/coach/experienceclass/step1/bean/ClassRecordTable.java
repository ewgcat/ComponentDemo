package com.yijian.staff.mvp.coach.experienceclass.step1.bean;

import java.util.List;

/**
 * Created by The_P on 2018/4/25.
 */

public class ClassRecordTable {

    public List<ClassActionDetails> definedRecordContentList;// (Array[上课记录表具体动作详细内容], optional)

    public List<ClassActionDetails> getDefinedRecordContentList() {
        return definedRecordContentList;
    }

    public void setDefinedRecordContentList(List<ClassActionDetails> definedRecordContentList) {
        this.definedRecordContentList = definedRecordContentList;
    }
}
