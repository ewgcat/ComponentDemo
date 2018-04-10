package com.yijian.staff.mvp.questionnaireresult;

import android.content.Context;
import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import com.yijian.staff.R;

import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;

import io.reactivex.Observer;

/**
 * Decorate several days with a dot
 */
public class EventDecorator implements DayViewDecorator {

    private HashSet<CalendarDay> dates;
    private Context context;

    public EventDecorator(QuestionnaireResultActivity context, Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
        this.context=context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(context.getDrawable(R.mipmap.bk_buttonbg));
    }
}
