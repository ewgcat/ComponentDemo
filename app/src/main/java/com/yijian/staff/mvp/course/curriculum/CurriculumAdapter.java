package com.yijian.staff.mvp.course.curriculum;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;


public class CurriculumAdapter extends RecyclerView.Adapter<CurriculumAdapter.ItemViewHolder> {

    private final List<String> mItems = new ArrayList<>();

    private int width;
    private int height;

    public CurriculumAdapter(Context context, int width, int height) {

        this.width = width;
        this.height = height;
        for (int i = 0; i < 24; i++) {
            mItems.add(""+i);
        }

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, width, height);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.bindView(mItems.get(position));
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_header;
        public TextView tv_name;
        public TextView tv_course_name;
        public TextView tv_course_status;
        public LinearLayout llCourseContent;
        public LinearLayout ll_content;
        public View line2;

        public ItemViewHolder(View itemView, int width, int height) {
            super(itemView);
            itemView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
            iv_header = itemView.findViewById(R.id.iv_header);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_course_name = itemView.findViewById(R.id.tv_course_name);
            tv_course_status = itemView.findViewById(R.id.tv_course_status);
            llCourseContent = itemView.findViewById(R.id.ll_course_content);
            ll_content = itemView.findViewById(R.id.ll_content);
            line2 = itemView.findViewById(R.id.line2);
        }


        public void bindView(String s) {
            if (s.equals("10")){
                tv_course_name.setText(s);
                ll_content.setBackgroundColor(Color.parseColor("#f5f5f5"));
                line2.setVisibility(View.GONE);
                tv_course_status.setVisibility(View.VISIBLE);
            }else {
                tv_course_name.setText("");
                ll_content.setBackgroundColor(Color.WHITE);
                line2.setVisibility(View.VISIBLE);
                tv_course_status.setVisibility(View.GONE);


            }
        }
    }
}
