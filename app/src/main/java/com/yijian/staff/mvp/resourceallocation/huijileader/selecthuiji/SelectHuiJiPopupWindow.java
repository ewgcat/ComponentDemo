package com.yijian.staff.mvp.resourceallocation.huijileader.selecthuiji;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
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
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijan.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SelectHuiJiPopupWindow extends PopupWindow implements View.OnClickListener {
    private List<HuiJiInfo> coachInfos = new ArrayList<>();

    private Activity context;
    private Lifecycle lifecycle;
    private RecyclerView rcl;
    private SelectHuiJiAdapter adapter;

    public SelectHuiJiPopupWindow(Lifecycle lifecycle,Activity context) {
        super(context);
        this.lifecycle = lifecycle;
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


        HttpManager.getHasHeaderNoParam(HttpManager.GET_HUIJI_LIST_RECEIVE_URL, new ResultJSONObjectObserver(lifecycle) {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {
                        JSONObject jsonObject = (JSONObject) records.get(i);
                        HuiJiInfo coachInfo = new HuiJiInfo(jsonObject);
                        coachInfos.add(coachInfo);
                    }
                    adapter = new SelectHuiJiAdapter(context, coachInfos);
                    rcl.setAdapter(adapter);
                } catch (JSONException e) {
                    Logger.i("TEST", e.getMessage());

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
                HuiJiInfo selectCoachInfo = adapter.getSelectCoachInfo();
                if (selectCoachInfo == null) {
                    Toast.makeText(context, "请先选择教练", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (selectCoachListener != null) {
                        selectCoachListener.onSelect(selectCoachInfo);
                        dismiss();
                    }
                }

                break;

        }
    }

    public interface SelectCoachListener {
        void onSelect(HuiJiInfo coachInfo);
    }

    private SelectCoachListener selectCoachListener;

    public void setSelectCoachListener(SelectCoachListener selectCoachListener) {
        this.selectCoachListener = selectCoachListener;
    }


}
