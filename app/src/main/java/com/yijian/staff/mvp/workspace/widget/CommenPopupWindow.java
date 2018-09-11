package com.yijian.staff.mvp.workspace.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.yijian.staff.R;

public class CommenPopupWindow extends PopupWindow {

    private View mMenuView;

    /**
     * @param context
     * @param itemsOnClick
     * @param res_layout   PopuWindow需要加载
     * @param clickResId   需要设置点击事件的资源ID
     */
    public CommenPopupWindow(Activity context, OnClickListener itemsOnClick, int res_layout, int[] clickResId) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(res_layout, null);
        for (int i = 0; i < clickResId.length; i++) {
            mMenuView.findViewById(clickResId[i]).setOnClickListener(itemsOnClick);
        }
        mMenuView.findViewById(R.id.lin_sub_container).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mMenuView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return false;
            }
        });

        this.setContentView(mMenuView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
    }

    public void showAtBottom(View v) {
        this.showAtLocation(v, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public View getmMenuView() {
        return mMenuView;
    }
}
