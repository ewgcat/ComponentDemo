package com.yijian.staff.mvp.main.mine.calendartable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * 设置所有选项都不可编辑
 */
public class AllNoneSelectedDecorator implements DayViewDecorator {

    public AllNoneSelectedDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }
}