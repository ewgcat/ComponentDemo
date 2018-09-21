package com.yijian.staff.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijian.staff.R;


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/25 11:53:36
 */
public class NotificationPopuwindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;


    public NotificationPopuwindow(Context context) {
        this(context, null);
    }

    public NotificationPopuwindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotificationPopuwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_popuwindow_view, null, false);
        setContentView(view);


        view.findViewById(R.id.cancel).setOnClickListener(this);
        view.findViewById(R.id.confirm).setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel) {
            if (onSelectLisener != null) {
                onSelectLisener.onCancel();
            }
            dismiss();

        } else if (i == R.id.confirm) {
            if (onSelectLisener != null) {
                onSelectLisener.onConfirm();
            }
            dismiss();

        }

    }


    public interface OnSelectLisener {
        void onConfirm();
        void onCancel();
    }

    private OnSelectLisener onSelectLisener;

    public void setOnSelectLisener(OnSelectLisener onSelectLisener) {
        this.onSelectLisener = onSelectLisener;
    }

    public void setBackgroundAlpha(Activity activity, float bgAlpha) {

        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(layoutParams);
    }



}
