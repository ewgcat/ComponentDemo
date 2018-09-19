package com.yijian.clubmodule.ui.course.recordchart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ClassRecordBean;
import com.yijian.clubmodule.ui.course.recordchart.detail.ClassRecordDetailActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class RecordChartAdapter extends RecyclerView.Adapter<RecordChartAdapter.ViewHolder> {

    private List<ClassRecordBean> classRecordBeanList;

    private Context context;

    public RecordChartAdapter(Context context, List<ClassRecordBean> classRecordBeanList) {
        this.classRecordBeanList = classRecordBeanList;
        this.context = context;
    }

    public void update(List<ClassRecordBean> classRecordBeanList) {
        this.classRecordBeanList = classRecordBeanList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_record, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassRecordBean classRecordBean = classRecordBeanList.get(position);
        holder.tv_name.setText(classRecordBean.getCourseName());
        holder.tv_class_time.setText(classRecordBean.getStartDate());
        holder.tv_class_jieshu.setText(classRecordBean.getCourseNum());
        holder.tv_has_shang_class_count.setText(classRecordBean.getCurrentNum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassRecordDetailActivity.class);
                intent.putExtra("id", classRecordBean.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return classRecordBeanList == null ? 0 : classRecordBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_class_time;
        TextView tv_class_jieshu;
        TextView tv_has_shang_class_count;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_class_time = view.findViewById(R.id.tv_class_time);
            tv_class_jieshu = view.findViewById(R.id.tv_class_jieshu);
            tv_has_shang_class_count = view.findViewById(R.id.tv_has_shang_class_count);


        }
    }

}
