package com.yijian.staff.mvp.reception.step1;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.TemplateBean;

import java.util.List;

/**
 * Created by The_P on 2018/3/28.
 */

public interface ReceptionStep1Contract {
    interface View{
        void showQuestion(TemplateBean templateBean);
        void saveSucceed();
    }

    interface Presenter{
      void  getQuestion(String memberId);
      void  uploadQusetion(List<DataListBean> questionList, RecptionerInfoBean consumerBean, List<CalendarDay> selectedDates);


        String computerPercent(List<DataListBean> questionList, List<CalendarDay> selectedDates);
    }
}
