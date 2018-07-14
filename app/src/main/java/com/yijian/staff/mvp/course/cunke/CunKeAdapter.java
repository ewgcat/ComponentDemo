package com.yijian.staff.mvp.course.cunke;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.TypeOfCunKeBody;
import com.yijian.staff.bean.TypeOfCunKeTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class CunKeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> dataList = new ArrayList<Object>();
    TypeOfCunKeTitle typeOfCunKeTitle;
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    public CunKeAdapter() {
        this.dataList = dataList;
        this.typeOfCunKeTitle = new TypeOfCunKeTitle("姓名", "课程名称", "总课程", "存课");
    }

    public void resetDataList(List<Object> dataList) {
        this.dataList.clear();
        this.dataList.add(typeOfCunKeTitle);
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_TITLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cunke_title, parent, false);
            viewHolder = new TitleViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cunke_body, parent, false);
            viewHolder = new BodyViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TITLE) {
            ((TitleViewHolder) holder).bind((TypeOfCunKeTitle) dataList.get(position));
        } else {
            ((BodyViewHolder) holder).bind((TypeOfCunKeBody) dataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = dataList.get(position);
        if (obj instanceof TypeOfCunKeTitle) {
            return TYPE_TITLE;
        } else if (obj instanceof TypeOfCunKeBody) {
            return TYPE_ITEM;
        }
        return TYPE_TITLE;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_user_name; //用户姓名
        private TextView tv_lesson_name; //课程名称
        private TextView tv_total_lesson_num; //总课程
        private TextView tv_remain_lesson_num; //存课

        public TitleViewHolder(View itemView) {
            super(itemView);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_lesson_name = itemView.findViewById(R.id.tv_lesson_name);
            tv_total_lesson_num = itemView.findViewById(R.id.tv_total_lesson_num);
            tv_remain_lesson_num = itemView.findViewById(R.id.tv_remain_lesson_num);
        }

        public void bind(TypeOfCunKeTitle typeOfCunKeTitle) {
            this.tv_user_name.setText(typeOfCunKeTitle.getUserName());
            this.tv_lesson_name.setText(typeOfCunKeTitle.getLessonName());
            this.tv_total_lesson_num.setText(typeOfCunKeTitle.getTotalLessonNum());
            this.tv_remain_lesson_num.setText(typeOfCunKeTitle.getRemainLessonNum());
        }

    }

    class BodyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_user_name; //用户姓名
        private TextView tv_lesson_name; //课程名称
        private TextView tv_total_lesson_num; //总课程
        private TextView tv_remain_lesson_num; //存课

        public BodyViewHolder(View itemView) {
            super(itemView);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_lesson_name = itemView.findViewById(R.id.tv_lesson_name);
            tv_total_lesson_num = itemView.findViewById(R.id.tv_total_lesson_num);
            tv_remain_lesson_num = itemView.findViewById(R.id.tv_remain_lesson_num);
        }

        public void bind(TypeOfCunKeBody typeOfCunKeBody) {
            this.tv_user_name.setText(typeOfCunKeBody.getMemberName());
            this.tv_lesson_name.setText(typeOfCunKeBody.getCourseName());
            this.tv_total_lesson_num.setText(typeOfCunKeBody.getTotalCourseCount());
            this.tv_remain_lesson_num.setText(typeOfCunKeBody.getStockCourseCount());
        }
    }

}
