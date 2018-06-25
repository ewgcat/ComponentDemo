package com.yijian.staff.mvp.complaint.handling;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.yijian.staff.R;

import java.util.List;

/**
 * Created by yangk on 2018/3/9.
 */

public class HandImgViewGroup extends ViewGroup {

    private Context mContext;
    private int margin = 5;

    public HandImgViewGroup(Context context) {
        super(context);
        this.mContext = context;
    }

    public HandImgViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public HandImgViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void setImgs(List<String> imgs) {
        for (int i = 0; i < imgs.size(); i++) {
            ImageView ivTask = (ImageView) LayoutInflater.from(mContext).inflate(R.layout.item_task_img, null);
            ivTask.setImageResource(R.mipmap.lg_vip);
            this.addView(ivTask);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpc = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpc = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = this.getChildCount();
        int parentWidth = 0;
        int parentHeight = 0;

//        measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams lp = new LayoutParams((widthSpc - margin * 2) / 3, (widthSpc - margin * 2) / 3);
            childView.setLayoutParams(lp);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            parentWidth += childView.getMeasuredWidth();
            parentHeight = childView.getMeasuredHeight();
        }
        parentHeight += margin * 2;
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY ? widthSpc : parentWidth), (heightMode == MeasureSpec.EXACTLY ? heightSpc : parentHeight));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 1;
        int top = margin;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                right = left + childView.getMeasuredWidth();
                top = (getHeight() - childView.getMeasuredHeight()) / 2;
                bottom = top + childView.getMeasuredHeight();
                childView.layout(left, top, right, bottom);
                left += childView.getMeasuredWidth() + margin;
            }
        }
    }
}
