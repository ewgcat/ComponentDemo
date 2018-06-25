package com.yijian.staff.mvp.questionnaire.detail;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by The_P on 2018/5/2.
 */

public class AllNoneSelectedDecorator implements DayViewDecorator {

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }
}
