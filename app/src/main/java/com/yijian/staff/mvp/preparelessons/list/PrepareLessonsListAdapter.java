package com.yijian.staff.mvp.preparelessons.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.preparelessons.PrepareLessonsActivity;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/26 17:25:29
 */
public class PrepareLessonsListAdapter extends RecyclerView.Adapter<PrepareLessonsListAdapter.ViewHolder> {

    private List<PrepareLessonsBean> prepareLessonsBeanList;
    private Context context;
    public PrepareLessonsListAdapter(Context context,List<PrepareLessonsBean> prepareLessonsBeanList) {
        this.context = context;
        this.prepareLessonsBeanList = prepareLessonsBeanList;
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
        String  beikeStatus = prepareLessonsBean.getBeikeStatus();

        if (beikeStatus.equals("1")){
            holder.tv_beike_status.setText("备课");
            holder.tv_beike_status.setTextColor(Color.parseColor("#1997f8"));
            Drawable preparelessons =context.getResources().getDrawable(R.mipmap.preparelessons);
            preparelessons.setBounds(0, 0, preparelessons.getMinimumWidth(), preparelessons.getMinimumHeight());
            holder.tv_beike_status.setCompoundDrawables(preparelessons, null, null, null);
        }else {

            Drawable done =context.getResources().getDrawable(R.mipmap.done);
            done.setBounds(0, 0, done.getMinimumWidth(), done.getMinimumHeight());
            holder.tv_beike_status.setCompoundDrawables(done, null, null, null);
            holder.tv_beike_status.setText("已备课");
            holder.tv_beike_status.setTextColor(Color.parseColor("#666666"));

        }

        holder.lin_beike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beikeStatus =="1"){
                    context.startActivity(new Intent(context,PrepareLessonsActivity.class));
                }
            }
        });
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
        TextView tv_beike_status;

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
            tv_beike_status = view.findViewById(R.id.tv_beike_status);


        }
    }
}
