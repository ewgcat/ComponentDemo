package com.yijian.staff.mvp.course.timetable.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private final List<String> mItems = new ArrayList<>();

    private final OnStartDragListener mDragStartListener;
    private int width;
    private int height;

    public RecyclerListAdapter(Context context, OnStartDragListener dragStartListener, int width, int height) {
        mDragStartListener = dragStartListener;
        for (int i = 0; i < 168; i++) {
            mItems.add(i + "");
        }
        this.width=width;
        this.height=height;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view,width,height);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position));

    }

    @Override
    public void onItemDismiss(int position) {

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyDataSetChanged();
        return true;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;

        public ItemViewHolder(View itemView,int width, int height) {
            super(itemView);
            itemView.setLayoutParams(new FrameLayout.LayoutParams(width,height));
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onItemSelected() {
            itemView.setScaleX(1.2f);
            itemView.setScaleY(1.2f);
        }

        @Override
        public void onItemClear() {
            itemView.setScaleX(1.0f);
            itemView.setScaleY(1.0f);
        }
    }
}
