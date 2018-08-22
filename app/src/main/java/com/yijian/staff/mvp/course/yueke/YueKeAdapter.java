package com.yijian.staff.mvp.course.yueke;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.course.punch.CoursePunchActivity;

import java.util.ArrayList;
import java.util.List;


public class YueKeAdapter extends RecyclerView.Adapter<YueKeAdapter.ItemViewHolder> {

    private final List<String> mItems = new ArrayList<>();

    private int width;
    private int height;
    private Context context;

    public YueKeAdapter(Context context, int width, int height) {
        this.context=context;
        this.width = width;
        this.height = height;
        for (int i = 0; i < 24; i++) {
            mItems.add(""+i);
        }

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appoint_course_item_view, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, width, height);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.bindView(mItems.get(position),context);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public View line2;

        public ItemViewHolder(View itemView, int width, int height) {
            super(itemView);
            itemView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
         ;
        }


        public void bindView(String s,Context context) {

        }
    }
}
