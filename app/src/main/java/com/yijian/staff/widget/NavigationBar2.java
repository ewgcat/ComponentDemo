package com.yijian.staff.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/1 11:20:30
 */
public class NavigationBar2 extends LinearLayout {
    private Context context;
    private ImageView firstLeftIv;
    private ImageView secondLeftIv;
    private ImageView mRightIv;
    private TextView mTitleView;
    private TextView mRightTv;
    private LinearLayout navigationbar2;

    public NavigationBar2(Context context) {
        this(context, null);
    }

    public NavigationBar2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        navigationbar2 = (LinearLayout) findViewById(R.id.navigationbar2);

        firstLeftIv = (ImageView) findViewById(R.id.iv_first_left);
        secondLeftIv = (ImageView) findViewById(R.id.iv_second_left);
        mTitleView = findViewById(R.id.title_tv);
        mRightIv = (ImageView) findViewById(R.id.iv_right);
        mRightTv = (TextView) findViewById(R.id.right_tv);
    }

    public void setNavigationBarBackgroudColor(int id) {
        navigationbar2.setBackgroundColor(id);
    }
    /*
    * @see android.view.View#getBottomFadingEdgeStrength()
    */
    @Override
    protected float getBottomFadingEdgeStrength() {
        return 1.0f;
    }

    public ImageView getFirstLeftIv() {
        return firstLeftIv;
    }

    public ImageView getSecondLeftIv() {
        return secondLeftIv;
    }

    public ImageView getmRightIv() {
        return mRightIv;
    }

    public TextView getmTitleView() {
        return mTitleView;
    }

    public TextView getmRightTv() {
        return mRightTv;
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public void setTitleColor(int color) {
        mTitleView.setTextColor(color);
    }

    public void setmRightTvText(String text) {
        mRightTv.setText(text);
    }

    public void setmRightTvColor(int color) {
        mRightTv.setTextColor(color);
    }

    public void setSecondLeftIvVisiable(int i) {
        secondLeftIv.setVisibility(i);
    }


}
