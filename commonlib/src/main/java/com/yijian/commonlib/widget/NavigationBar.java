package com.yijian.commonlib.widget;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.commonlib.R;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/1 11:20:30
 */
public class NavigationBar extends LinearLayout {
    private Context context;
    private ImageView firstLeftIv;
    private ImageView secondLeftIv;
    private ImageView mRightIv;
    private TextView mTitleView;
    private TextView mRightTv;
    private LinearLayout navigationBar;
    private LinearLayout llRightClickArea;
    private LinearLayout backLL;
    private View bottomLine;

    public NavigationBar(Context context) {
        this(context, null);
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        navigationBar = (LinearLayout) findViewById(R.id.navigationBar);
        llRightClickArea = (LinearLayout) findViewById(R.id.ll_right_click_area);
        backLL = (LinearLayout) findViewById(R.id.ll_back);

        firstLeftIv = (ImageView) findViewById(R.id.iv_first_left);
        secondLeftIv = (ImageView) findViewById(R.id.iv_second_left);
        mTitleView = findViewById(R.id.title_tv);
        mRightIv = (ImageView) findViewById(R.id.iv_right);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        bottomLine = findViewById(R.id.bottom_line);
    }

    public void setNavigationBarBackgroudColor(int id) {
        navigationBar.setBackgroundColor(id);
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

    public void setBackLLVisiable(int i) {
        backLL.setVisibility(i);
    }

    public void hideBottomLine() {
        bottomLine.setVisibility(GONE);
    }

    public void hideLeftSecondIv() {
        secondLeftIv.setVisibility(GONE);
    }

    public void setmRightIv(int resId){
        mRightIv.setImageResource(resId);
    }

    public void setBackClickListener(Activity activity) {
        backLL.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }



    public void setRightClickListener(OnClickListener listener){
        llRightClickArea.setOnClickListener(listener);
    }

    public LinearLayout getBackLL() {
        return backLL;
    }


}
