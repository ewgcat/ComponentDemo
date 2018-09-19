package com.yijian.commonlib.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 10:22:03
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int mHeight = 1; //分割线高度
    private Paint mPaint ; //分割线高度
    public MyDividerItemDecoration() {
        mPaint=new Paint();
        mPaint.setColor(Color.parseColor("#eaeaea"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1.0f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            //第一个item预留空间
            outRect.top = mHeight;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getLeft();
        final int right = parent.getRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            final int bottom = childView.getTop();
            final int top = bottom - mHeight;
            c.drawLine(left, top, right, top, mPaint);
        }


    }
}
