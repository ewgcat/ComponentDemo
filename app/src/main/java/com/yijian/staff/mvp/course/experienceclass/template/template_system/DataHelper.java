package com.yijian.staff.mvp.course.experienceclass.template.template_system;


import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.AerobicsListBean;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.NoInstrumentListBean;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.PowerListBean;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.TemplateListBean;
import com.yijian.staff.mvp.course.experienceclass.template.template_system.bean.TitleTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public class DataHelper {
    public List<Object> mapData(TemplateListBean bean) {
        List<Object> list = new ArrayList<>();
        List<AerobicsListBean> aerobicsList = bean.getAerobicsList();
        List<NoInstrumentListBean> noInstrumentList = bean.getNoInstrumentList();
        List<PowerListBean> powerList = bean.getPowerList();


        if (noInstrumentList != null && noInstrumentList.size() != 0) {
            list.add(new TitleTemplate("无器械"));
//        list.add(new HeadNoInstrument());
            NoInstrumentListBean head = new NoInstrumentListBean("1", 1, 1, "a", 1, 1);
            head.setHead(true);
            head.setName("head");
            list.add(head);
            noInstrumentList.get(noInstrumentList.size() - 1).setLastItem(true);
            list.addAll(noInstrumentList);
        }


        if (aerobicsList != null && aerobicsList.size() != 0) {
            list.add(new TitleTemplate("有氧器械"));
//        list.add(new HeadAerobics());
            AerobicsListBean head1 = new AerobicsListBean("1", 1, 1, "a", "a", 1);
            head1.setHead(true);
            list.add(head1);
            aerobicsList.get(aerobicsList.size() - 1).setLastItem(true);
            list.addAll(aerobicsList);
        }


        if (powerList != null && powerList.size() != 0) {
            list.add(new TitleTemplate("教练器械指导"));
//        list.add(new HeadPower());
            PowerListBean head2 = new PowerListBean("head", 1, 1, "head", 1, 1, 1);
            head2.setHead(true);
            list.add(head2);
            powerList.get(powerList.size() - 1).setLastItem(true);
            list.addAll(powerList);
        }


        return list;

    }
}
