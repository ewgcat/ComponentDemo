package com.yijian.staff.mvp.course.timetable.schedule.day;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.yijian.staff.R;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/24 19:11:04
 */
public class FlagPopuwindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;

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
        ImageView ivGreen = view.findViewById(R.id.iv_green);
        ImageView ivRed = view.findViewById(R.id.iv_red);
        ImageView ivBlue = view.findViewById(R.id.iv_blue);
        ImageView ivWhite = view.findViewById(R.id.iv_white);

        ivGreen.setOnClickListener(this);
        ivRed.setOnClickListener(this);
        ivBlue.setOnClickListener(this);
        ivWhite.setOnClickListener(this);
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
}
