package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionContract;
import com.yijian.staff.util.ImageLoader;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/24 19:11:04
 */
public class FlagPopuwindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private LinearLayout llFlag;
    private ImageView ivGreen;
    private ImageView ivRed;
    private ImageView ivBlue;
    private ImageView ivWhite;
    private View line;

    public FlagPopuwindow(Context context) {
        this(context, null);
    }

    public FlagPopuwindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlagPopuwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.flag_view, null, false);
        setContentView(view);

        llFlag = view.findViewById(R.id.ll_flag);
        ivGreen = view.findViewById(R.id.iv_green);
        ivRed = view.findViewById(R.id.iv_red);
        ivBlue = view.findViewById(R.id.iv_blue);
        line = view.findViewById(R.id.line);
        ivWhite = view.findViewById(R.id.iv_white);

        ivGreen.setOnClickListener(this);
        ivRed.setOnClickListener(this);
        ivBlue.setOnClickListener(this);
        ivWhite.setOnClickListener(this);
    }

    public void setFlagColor(int position) {
        llFlag.removeAllViews();
        switch (position) {
            case GREEN_FLAG:
                llFlag.addView(ivRed, 0);
                llFlag.addView(ivBlue, 1);
                llFlag.addView(ivWhite, 2);
                llFlag.addView(line, 3);
                llFlag.addView(ivGreen, 4);
                break;
            case RED_FLAG:
                llFlag.addView(ivGreen, 0);
                llFlag.addView(ivBlue, 1);
                llFlag.addView(ivWhite, 2);
                llFlag.addView(line, 3);
                llFlag.addView(ivRed, 4);
                break;
            case BLUE_FLAG:
                llFlag.addView(ivGreen, 0);
                llFlag.addView(ivRed, 1);
                llFlag.addView(ivWhite, 2);
                llFlag.addView(line, 3);
                llFlag.addView(ivBlue, 4);
                break;
            case WHITE_FLAG:
                llFlag.addView(ivGreen, 0);
                llFlag.addView(ivRed, 1);
                llFlag.addView(ivBlue, 2);
                llFlag.addView(line, 3);
                llFlag.addView(ivWhite, 4);
                break;
        }
    }


    public final static int GREEN_FLAG = 0;
    public final static int RED_FLAG = 1;
    public final static int BLUE_FLAG = 2;
    public final static int WHITE_FLAG = 3;
    private OnSelectFlagListener onSelectFlagListener;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_green:
                if (onSelectFlagListener != null) {
                    onSelectFlagListener.OnSelectFlag(GREEN_FLAG);
                }
                break;
            case R.id.iv_red:
                if (onSelectFlagListener != null) {
                    onSelectFlagListener.OnSelectFlag(RED_FLAG);
                }
                break;
            case R.id.iv_blue:
                if (onSelectFlagListener != null) {
                    onSelectFlagListener.OnSelectFlag(BLUE_FLAG);
                }
                break;
            case R.id.iv_white:
                if (onSelectFlagListener != null) {
                    onSelectFlagListener.OnSelectFlag(WHITE_FLAG);
                }
                break;

        }
    }

    public interface OnSelectFlagListener {
        void OnSelectFlag(int position);
    }

    public void setOnSelectFlagListener(OnSelectFlagListener onSelectFlagListener) {
        this.onSelectFlagListener = onSelectFlagListener;
    }

    public void show(View view) {
        this.showAsDropDown(view);
    }
    public void showUp(View view) {

    }
}
