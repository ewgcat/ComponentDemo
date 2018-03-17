package com.yijian.staff.mvp.reception.step1.bean;

import com.yijian.staff.mvp.reception.step1.recyclerView.ParentImp;

import java.util.List;

/**
 * Created by The_P on 2018/3/16.
 */

public  class Step1Bean implements ParentImp<QuestOptBean> {
    /**
     * questName : 1.信息来源(单选)
     * questOpt : [{"name":"报纸","type":"normal","selected":false},{"name":"电视TV","type":"normal","selected":false},{"name":"广播","type":"normal","selected":false},{"name":"广播","type":"mix","selected":false}]
     * type : singleCheck
     */

    private String questName;
    private String type;
    private List<QuestOptBean> questOpt;

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QuestOptBean> getQuestOpt() {
        return questOpt;
    }

    public void setQuestOpt(List<QuestOptBean> questOpt) {
        this.questOpt = questOpt;
    }


    @Override
    public List<QuestOptBean> getChildList() {
        return questOpt;
    }
}