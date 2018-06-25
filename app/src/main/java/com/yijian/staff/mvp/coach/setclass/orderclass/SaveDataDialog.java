package com.yijian.staff.mvp.coach.setclass.orderclass;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.setclass.OpenLessonNewActivity;
import com.yijian.staff.util.DensityUtil;

public class SaveDataDialog extends PopupWindow {

    private OpenLessonNewActivity activity;

    public SaveDataDialog(OpenLessonNewActivity context) {
        super(context);
        this.activity = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.pop_save_data, null);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout lin_container = mMenuView.findViewById(R.id.lin_container);
        LinearLayout.LayoutParams lp_lin_container = (LinearLayout.LayoutParams) lin_container.getLayoutParams();
        lp_lin_container.width = DensityUtil.getScreenWidth(context) * 2 / 3;
        lin_container.setLayoutParams(lp_lin_container);

        mMenuView.findViewById(R.id.tv_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                context.finish();
            }
        });
        mMenuView.findViewById(R.id.tv_pos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finishSaveData();
            }
        });

        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (!(v instanceof Button)) {
                    dismiss();
                }
                return true;
            }
        });
    }

    public void showSaveDialog() {
        showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }


}
