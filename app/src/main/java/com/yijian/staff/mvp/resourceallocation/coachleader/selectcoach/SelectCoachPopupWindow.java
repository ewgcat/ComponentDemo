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
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.resourceallocation.selecthuiji.SelectHuiJiActivity;
import com.yijian.staff.mvp.resourceallocation.coachleader.bean.ResourceInfo;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
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
    private SelectCoachAdapter adapter;

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


        HttpManager.getHasHeaderNoParam(HttpManager.GET_COACH_LIST_RECEIVE_URL, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        CoachInfo coachInfo = new CoachInfo(jsonObject);
                        coachInfos.add(coachInfo);
                    }
                    adapter = new SelectCoachAdapter(context, coachInfos);
                    rcl.setAdapter(adapter);
                } catch (JSONException e) {
                    Logger.i("TEST",e.getMessage());

                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

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
                CoachInfo selectCoachInfo = adapter.getSelectCoachInfo();
                if (selectCoachInfo == null) {
                    Toast.makeText(context, "请先选择教练", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (selectCoachListener!=null){
                        selectCoachListener.onSelect(selectCoachInfo);
                        dismiss();
                    }
                }

                break;

        }
    }

    public interface SelectCoachListener {
        void onSelect(CoachInfo coachInfo);
    }

  private   SelectCoachListener selectCoachListener;

    public void setSelectCoachListener(SelectCoachListener selectCoachListener) {
        this.selectCoachListener = selectCoachListener;
    }


}
