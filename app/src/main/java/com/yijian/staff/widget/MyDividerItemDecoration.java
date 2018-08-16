package com.yijian.staff.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/7/31 10:22:03
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {


    public MyDividerItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

//        //如果不是第一个，则设置top的值。
        if (parent.getChildAdapterPosition(view) != 0){
            //这里直接硬编码为1px
            outRect.top = 1;
        }
    }
}
