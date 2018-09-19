package com.yijian.commonlib.umeng;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseRvViewHolder<T> extends RecyclerView.ViewHolder {
    protected View view;

    public BaseRvViewHolder(View view) {
        super(view);
        this.view = view;
    }

    protected <W extends View> W findView(int viewId) {
        return view.findViewById(viewId);
    }

    protected void bindData(T t, int position) {
    }
}
