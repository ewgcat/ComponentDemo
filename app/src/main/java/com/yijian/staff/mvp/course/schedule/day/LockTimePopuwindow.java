package com.yijian.staff.mvp.course.schedule.day;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.List;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/25 11:53:36
 */
public class LockTimePopuwindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private TextView start_hours;
    private WheelView start_minutes;
    private WheelView wheelView1;
    private WheelView wheelView2;
    private String selectedEndHours;
    private String selectedEndMinutes;
    private String selectedStartMinutes;
    private String selectedStartHours;

    public LockTimePopuwindow(Context context) {
        this(context, null);
    }

    public LockTimePopuwindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockTimePopuwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lock_time_popuwindow_view, null, false);
        setContentView(view);

        start_hours = view.findViewById(R.id.start_hours);
        start_minutes = view.findViewById(R.id.start_minutes);
        wheelView1 = view.findViewById(R.id.wheelview1);
        wheelView2 = view.findViewById(R.id.wheelview2);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        view.findViewById(R.id.confirm).setOnClickListener(this);
    }


    public void setStartTime(int hour, int minute) {

        String hourString = "";
        if (hour < 10) {
            hourString = hourString + "0" + hour;
        } else {
            hourString = hourString + hour;
        }
        start_hours.setText(hourString);
        selectedStartHours = hourString;
        final List<String> mOptionsItems1 = new ArrayList<>();
        for (int i = hour + 1; i <= 24; i++) {
            if (i < 10) {
                mOptionsItems1.add("0" + i);
            } else if (10 <= i && i < 24) {
                mOptionsItems1.add("" + i);
            }
        }

        if (mOptionsItems1.size() > 0) {
            selectedEndHours = mOptionsItems1.get(0);

            final List<String> mOptionsItems2 = new ArrayList<>();
            mOptionsItems2.add("00");
            mOptionsItems2.add("30");
            wheelView2.setCyclic(false);
            wheelView2.setAdapter(new ArrayWheelAdapter(mOptionsItems2));
            wheelView2.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedEndMinutes = mOptionsItems2.get(index);
                }
            });
            wheelView2.setCurrentItem(0);
            selectedEndMinutes = mOptionsItems2.get(0);

            start_minutes.setCyclic(false);
            start_minutes.setAdapter(new ArrayWheelAdapter(mOptionsItems2));
            start_minutes.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedStartMinutes = mOptionsItems2.get(index);
                }
            });
            if (minute < 30) {
                start_minutes.setCurrentItem(0);
                selectedStartMinutes = mOptionsItems2.get(0);
            } else {
                start_minutes.setCurrentItem(1);
                selectedStartMinutes = mOptionsItems2.get(1);
            }

            wheelView1.setCyclic(false);
            wheelView1.setAdapter(new ArrayWheelAdapter(mOptionsItems1));
            wheelView1.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedEndHours = mOptionsItems1.get(index);
                }
            });
            wheelView1.setCurrentItem(0);

        } else {
            mOptionsItems1.add("23");
            selectedEndHours = mOptionsItems1.get(0);


            final List<String> mOptionsItems21 = new ArrayList<>();
            mOptionsItems21.add("30");
            wheelView2.setCyclic(false);
            wheelView2.setAdapter(new ArrayWheelAdapter(mOptionsItems21));
            wheelView2.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedEndMinutes = mOptionsItems21.get(index);
                }
            });
            wheelView2.setCurrentItem(0);
            selectedEndMinutes = mOptionsItems21.get(0);

            final List<String> mOptionsItems20 = new ArrayList<>();
            mOptionsItems20.add("00");


            start_minutes.setCyclic(false);
            start_minutes.setAdapter(new ArrayWheelAdapter(mOptionsItems20));
            start_minutes.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedStartMinutes = mOptionsItems20.get(index);
                }
            });
            start_minutes.setCurrentItem(0);
            selectedStartMinutes = mOptionsItems20.get(0);

            wheelView1.setCyclic(false);
            wheelView1.setAdapter(new ArrayWheelAdapter(mOptionsItems1));
            wheelView1.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedEndHours = mOptionsItems1.get(index);
                }
            });
            wheelView1.setCurrentItem(0);


        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.confirm:
                if (onSelectLockTimeListener != null) {
                    onSelectLockTimeListener.onSelectLockTime(selectedStartHours + ":" + selectedStartMinutes, selectedEndHours + ":" + selectedEndMinutes);
                }
                dismiss();
                break;
        }
    }


    public interface OnSelectLockTimeListener {
        void onSelectLockTime(String startTime, String endTime);
    }

    private OnSelectLockTimeListener onSelectLockTimeListener;

    public void setOnSelectLockTimeListener(OnSelectLockTimeListener onSelectLockTimeListener) {
        this.onSelectLockTimeListener = onSelectLockTimeListener;
    }

    public void setBackgroundAlpha(Activity activity, float bgAlpha) {
        this.activity = activity;

        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(layoutParams);
    }

    private Activity activity;


}
