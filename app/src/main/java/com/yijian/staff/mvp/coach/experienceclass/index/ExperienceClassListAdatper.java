package com.yijian.staff.mvp.coach.experienceclass.index;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.experienceclass.step1.ExperienceClassProcess1Activity;
import com.yijian.staff.mvp.coach.experienceclass.step2.ExperienceClassProcess2Activity;
import com.yijian.staff.mvp.coach.experienceclass.step3.ExperienceClassProcess3Activity;
import com.yijian.staff.mvp.coach.experienceclass.step4.ExperienceClassProcess4Activity;
import com.yijian.staff.mvp.coach.experienceclass.step5.coach.ExperienceClassProcess5Activity;
import com.yijian.staff.mvp.coach.viperlist.CoachViperListAdapter;

import java.util.List;

/**
 * Created by yangk on 2018/3/8.
 */

public class ExperienceClassListAdatper extends RecyclerView.Adapter<ExperienceClassListAdatper.ViewHolder> {

    private List<ExperienceClassBean> experienceClassBeanList;
    private Context context;

    public ExperienceClassListAdatper(Context context, List<ExperienceClassBean> experienceClassBeanList) {
        this.context = context;
        this.experienceClassBeanList = experienceClassBeanList;
    }

    public void update(List<ExperienceClassBean> experienceClassBeanList) {
        if (experienceClassBeanList != null) {
            this.experienceClassBeanList = experienceClassBeanList;
            notifyDataSetChanged();
        }
    }

    @Override
    public ExperienceClassListAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience_class, parent, false);
        ExperienceClassListAdatper.ViewHolder holder = new ExperienceClassListAdatper.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExperienceClassListAdatper.ViewHolder holder, int position) {
        ExperienceClassBean experienceClassBean = experienceClassBeanList.get(position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(context).load(experienceClassBean.getHeadPath()).apply(options).into(holder.iv_header);
        int sex = experienceClassBean.getGender();
        if (sex == 1) {
            Glide.with(context).load(R.mipmap.lg_man).apply(options).into(holder.iv_gender);
        } else {
            Glide.with(context).load(R.mipmap.lg_women).apply(options).into(holder.iv_gender);
        }

        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new ExperienceClassVipCardListAdapter(experienceClassBean.getCardprodVOs()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCardView(holder);
            }
        });


        holder.tv_name.setText(experienceClassBean.getMemberName());

        holder.tv_experienced_count.setText(experienceClassBean.getCourseNum() + "");
        holder.tv_current_operation.setText(experienceClassBean.getStatusDesc());

        holder.experience_class_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = experienceClassBean.getStatus();
                Intent intent = new Intent();
                if (status == 10) {
                    intent.setClassName(context, "com.yijian.staff.mvp.coach.experienceclass.step1.ExperienceClassProcess1Activity");
                } else if (status == 20) {
                    intent.setClassName(context, "com.yijian.staff.mvp.coach.experienceclass.step2.ExperienceClassProcess2Activity");

                } else if (status == 30) {
                    intent.setClassName(context, "com.yijian.staff.mvp.coach.experienceclass.step3.ExperienceClassProcess3Activity");
                } else if (status == 40) {
                    intent.setClassName(context, "com.yijian.staff.mvp.coach.experienceclass.step4.ExperienceClassProcess4Activity");
                } else if (status == 50) {
                    intent.setClassName(context, "com.yijian.staff.mvp.coach.experienceclass.step5.ExperienceClassProcess5Activity");

                }
                intent.putExtra("memberId", experienceClassBean.getMemberId());
                context.startActivity(intent);
            }
        });
    }


    private void toggleCardView(ExperienceClassListAdatper.ViewHolder holder) {
        int visibility = holder.rv_card.getVisibility();
        if (visibility == View.GONE) {
            holder.rv_card.setVisibility(View.VISIBLE);
            holder.tv_zhankai_status.setText("收起");
            Drawable drawable = context.getDrawable(R.mipmap.fp_shang);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null, null, drawable, null);
        } else {
            holder.rv_card.setVisibility(View.GONE);
            holder.tv_zhankai_status.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_xiala);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null, null, drawable, null);
        }

    }

    @Override
    public int getItemCount() {
        return experienceClassBeanList == null ? 0 : experienceClassBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout experience_class_item_view;
        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_zhankai_status;
        RelativeLayout rel_expand;
        RecyclerView rv_card;
        TextView tv_experienced_count;
        TextView tv_current_operation;


        public ViewHolder(View view) {
            super(view);
            experience_class_item_view = view.findViewById(R.id.experience_class_item_view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_zhankai_status = view.findViewById(R.id.tv_zhankai_status);
            rel_expand = view.findViewById(R.id.rel_expand);
            rv_card = view.findViewById(R.id.rv_card);
            tv_experienced_count = view.findViewById(R.id.tv_experienced_count);
            tv_current_operation = view.findViewById(R.id.tv_current_operation);
        }
    }

}