package com.yijian.staff.tab.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;


/**
 * 描述:编辑页面的主体item的holder
 * <p>
 * 作者:陈俊森
 * 创建时间:2017年11月03日 17:14
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class MenuRecyclerGridHolder extends RecyclerView.ViewHolder {
    public TextView tv_name;
    public ImageView iv_icon;
    public ImageView iv_delete;
    public ImageView iv_add;
    public TextView tv_count;
    public FrameLayout fl_item_view;

    public MenuRecyclerGridHolder(View itemView) {
        super(itemView);
        fl_item_view = itemView.findViewById(R.id.fl_item_view);
        tv_count = (TextView) itemView.findViewById(R.id.tv_count);
        tv_name = (TextView) itemView.findViewById(R.id.desc);
        iv_icon = (ImageView) itemView.findViewById(R.id.pic);
        iv_delete = (ImageView) itemView.findViewById(R.id.delete);
        iv_add = (ImageView) itemView.findViewById(R.id.add);
    }
}
