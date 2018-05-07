package com.yijian.staff.mvp.resourceallocation.coachleader.selectcoach;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/5/7 19:55:00
 */
public class SelectCoachPopupWindow extends PopupWindow implements View.OnClickListener {
    private List<CoachInfo> coachInfos = new ArrayList<>();

    private Activity context;
    private RecyclerView rcl;

    public SelectCoachPopupWindow(Activity context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.select_coach_popupwindow, null);
        setContentView(contentView);


        // 加上这个popupwindow中的ListView才可以接收点击事件
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        int height = CommonUtil.dp2px(context, 400);
        this.setHeight(height);
        this.setFocusable(true);

        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setAnimationStyle(R.style.PopupAnimation);
        this.setOutsideTouchable(true);
        //添加popupWindow窗口关闭监听
        this.setOnDismissListener(() -> backgroundAlpha(1f));
        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView tv_send = (TextView) contentView.findViewById(R.id.tv_send);

        tv_cancel.setOnClickListener(this);
        tv_send.setOnClickListener(this);

        rcl = contentView.findViewById(R.id.rcl);
        rcl.setLayoutManager(new LinearLayoutManager(context));
        coachInfos.add(new CoachInfo(new JSONObject()));
        coachInfos.add(new CoachInfo(new JSONObject()));
        coachInfos.add(new CoachInfo(new JSONObject()));
        rcl.setAdapter(new SelectCoachAdapter(context,coachInfos));
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    public void showAtLocation(View parent) {
        backgroundAlpha(0.3f);
        this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_send:
                break;

        }
    }
}
