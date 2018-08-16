package com.yijian.staff.mvp.course.preparelessons.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.PrepareLessonsBean;
import com.yijian.staff.mvp.course.preparelessons.index.PrepareLessonsActivity;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 17:25:29
 */
public class PrepareLessonsListAdapter extends RecyclerView.Adapter<PrepareLessonsListAdapter.ViewHolder> {

    private List<PrepareLessonsBean> prepareLessonsBeanList;
    private Context context;

    public PrepareLessonsListAdapter(Context context, List<PrepareLessonsBean> prepareLessonsBeanList) {
        this.context = context;
        this.prepareLessonsBeanList = prepareLessonsBeanList;
    }

    public void resetList(List<PrepareLessonsBean> prepareLessonsBeans) {
        this.prepareLessonsBeanList = prepareLessonsBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prepare_lessons, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PrepareLessonsBean prepareLessonsBean = prepareLessonsBeanList.get(position);

        holder.lin_beike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PrepareLessonsActivity.class);
                intent.putExtra("parepareLessonBean", prepareLessonsBean);
                context.startActivity(intent);
            }
        });
        ImageLoader.setHeadImageResource(prepareLessonsBean.getHeadPath(), context, holder.iv_header);
        holder.iv_gender.setImageResource((prepareLessonsBean.getGender() == 1) ? R.mipmap.wt_man : R.mipmap.wt_women);
        holder.tv_name.setText(prepareLessonsBean.getMemberName());
        holder.tv_class_name.setText(prepareLessonsBean.getLessonName());
        holder.tv_class_time.setText(prepareLessonsBean.getStartDate() + " " + prepareLessonsBean.getStartDatetime());
        holder.tv_class_num.setText(prepareLessonsBean.getCourseNum() + "");
        holder.tv_has_shang_class_count.setText(prepareLessonsBean.getCurrentNum() + "");

    }

    @Override
    public int getItemCount() {
        return prepareLessonsBeanList == null ? 0 : prepareLessonsBeanList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_content;
        LinearLayout lin_beike;

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;

        TextView tv_class_type;
        TextView tv_class_name;
        TextView tv_class_time;
        TextView tv_class_num;
        TextView tv_has_shang_class_count;

        public ViewHolder(View view) {
            super(view);
            ll_content = view.findViewById(R.id.ll_content);
            lin_beike = view.findViewById(R.id.lin_beike);

            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);

            tv_class_type = view.findViewById(R.id.tv_class_type);
            tv_class_name = view.findViewById(R.id.tv_class_name);
            tv_class_time = view.findViewById(R.id.tv_class_time);
            tv_class_num = view.findViewById(R.id.tv_class_num);
            tv_has_shang_class_count = view.findViewById(R.id.tv_has_shang_class_count);


        }
    }
}
