package com.yijian.staff.widget.tab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huifang.tianxieresult.TianXieHuiFangResultActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/7 10:43:45
 */
public class TabView extends LinearLayout {

    private Context context;
    private ImageView ivTab;
    private TextView tvTab;
    private Intent i;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ivTab =  findViewById(R.id.iv_tab);
        tvTab = findViewById(R.id.tv_tab);

    }

    public void setTabIcon(int resId){
        Glide.with(context).load(resId).into(ivTab);
    }

    public void setTabText(String tabText){
        tvTab.setText(tabText);
    }




}
