package com.yijian.staff.tab.adapter.holder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.draggable.annotation.DraggableItemStateFlags;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.yijian.staff.R;


/**
 * 描述:菜单编辑列表适配器的头部的ViewHolder<br>
 *     注意，一定要继承{@link AbstractDraggableItemViewHolder}或者照着这个类实现{@link com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder}
 *     才能有拖拽效果
 * <p>
 * 作者:陈俊森
 * 创建时间:2017年11月03日 17:07
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
 */
public class MenuHeaderRecyclerGridHolder extends MenuRecyclerGridHolder implements DraggableItemViewHolder {
    public ImageView iv_delete;
    public TextView tv_count;
    public FrameLayout fl_item_view;

    @DraggableItemStateFlags
    private int mDragStateFlags;

    public MenuHeaderRecyclerGridHolder(View itemView) {
        super(itemView);
        iv_delete= (ImageView) itemView.findViewById(R.id.delete);
        tv_count= (TextView) itemView.findViewById(R.id.tv_head_count);
        fl_item_view= (FrameLayout) itemView.findViewById(R.id.fl_item_view);

    }

    @Override
    public void setDragStateFlags(@DraggableItemStateFlags int flags) {
        mDragStateFlags=flags;
    }

    @Override
    @DraggableItemStateFlags
    public int getDragStateFlags() {
        return mDragStateFlags;
    }
}
