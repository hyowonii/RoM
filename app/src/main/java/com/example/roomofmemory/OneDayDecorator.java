package com.example.roomofmemory;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class OneDayDecorator implements DayViewDecorator {

    private Drawable drawable;
    private HashSet<CalendarDay> dates;

    public OneDayDecorator(Collection<CalendarDay> dates, Activity context){
        this.dates = new HashSet<>(dates);
        drawable = context.getResources().getDrawable(R.drawable.date_selector);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
