package com.yijian.staff.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;


public class NavigationBar extends RelativeLayout {

    protected RelativeLayout mLeftRelativeLayout = null;
    protected RelativeLayout mRightRelativeLayout = null;
    protected RelativeLayout mLeftClickAreaLayout = null;
    protected RelativeLayout mRightClickAreaLayout = null;
    protected TextView mTitleView = null;
    protected TextView mMessageView = null;
    protected TextView mRevokeView = null;
    protected RelativeLayout mContentRl;
    protected String message;


    /**
     * @param context
     */
    public NavigationBar(Context context) {
        super(context);
    }


    /**
     * @param context
     * @param attrs
     */
    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public NavigationBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /*
     * (non-Javadoc)
     * @see android.view.View#onFinishInflate()
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftRelativeLayout = (RelativeLayout) findViewById(R.id.navigationbar_leftbtn_layout);
        mRightRelativeLayout = (RelativeLayout) findViewById(R.id.navigationbar_rightbtn_layout);
        mLeftClickAreaLayout = (RelativeLayout) findViewById(R.id.navigationbar_leftbtn_clickarea_layout);
        mRightClickAreaLayout = (RelativeLayout) findViewById(R.id.navigationbar_rightbtn_clickarea_layout);
        mTitleView = (TextView) findViewById(R.id.navigationbar_title_text);
        mMessageView = (TextView) findViewById(R.id.navigationbar_message_show);
        mContentRl = (RelativeLayout) findViewById(R.id.navigationbar);
        mRevokeView = (TextView) findViewById(R.id.navigationbar_revoke);
    }



    /*
     * @see android.view.View#getBottomFadingEdgeStrength()
     */
    @Override
    protected float getBottomFadingEdgeStrength() {
        return 1.0f;
    }


    public void setNavigationBarBackgroudColor(int id) {
        mContentRl.setBackgroundColor(id);
    }


    public void setLeftButtonVisible(int viewVisiblity) {
        mLeftRelativeLayout.setVisibility(viewVisiblity);
    }


    public void setRightButtonVisible(int viewVisiblity) {
        mRightRelativeLayout.setVisibility(viewVisiblity);
    }


    public void setRightButtonEnable(boolean enable) {
        mRightClickAreaLayout.setEnabled(enable);
    }


    public void setLeftButtonView(View view) {
        if (view == null) {
            return;
        }
        mLeftRelativeLayout.removeAllViews();
        view.setDuplicateParentStateEnabled(true);
        mLeftRelativeLayout.addView(view);
    }


    public RelativeLayout getmRightRelativeLayout() {
        return mRightRelativeLayout;
    }


    public void setRightButtonView(View view) {
        if (view == null) {
            return;
        }
        mRightRelativeLayout.removeAllViews();
        view.setDuplicateParentStateEnabled(true);
        mRightRelativeLayout.addView(view);
    }


    public void setLeftButtonClickListener(OnClickListener listener) {
        mLeftClickAreaLayout.setOnClickListener(listener);
    }


    public void setRightButtonClickListener(OnClickListener listener) {
        mRightClickAreaLayout.setOnClickListener(listener);
    }


    public void setTitle(String title,String textColor) {
        if (title == null) {
            return;
        }
        mTitleView.setText(title);
        mTitleView.setTextColor(Color.parseColor(textColor));
    }


    public void setTitle(int resid) {
        mTitleView.setText(resid);
    }


    public void setMessage(String msg) {
        if (msg == null) {
            return;
        }
        mMessageView.setText(msg);
    }


    public TextView getTitleTextView() {
        return mTitleView;
    }


    public TextView getmMessageView() {
        return mMessageView;
    }


    public RelativeLayout getmRightClickAreaLayout() {
        return mRightClickAreaLayout;
    }


    /**
     * 获取撤销TextView
     *
     * @return
     */
    public TextView getmRevokeView() {
        return mRevokeView;
    }

}
