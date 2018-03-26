package com.yijian.staff.mvp.coach.recordchart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class RecordChartAdapter extends RecyclerView.Adapter<RecordChartAdapter.ViewHolder> {

    private List<ClassRecordBean> classRecordBeanList;

    public RecordChartAdapter( List<ClassRecordBean> classRecordBeanList) {
        this.classRecordBeanList = classRecordBeanList;
    }

    @Override
    public RecordChartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_record, parent, false);
        RecordChartAdapter.ViewHolder holder = new RecordChartAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecordChartAdapter.ViewHolder holder, int position) {
        ClassRecordBean classRecordBean = classRecordBeanList.get(position);
        holder.tv_name.setText(classRecordBean.getClassName());
        holder.tv_class_time.setText(classRecordBean.getClassTime());
        holder.tv_class_jieshu.setText(classRecordBean.getClassJieShu());
        holder.tv_has_shang_class_count.setText(classRecordBean.getHasShangClassCount());


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
