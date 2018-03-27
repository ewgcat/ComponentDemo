package com.yijian.staff.mvp.experienceclass.index;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.mvp.experienceclass.step1.ExperienceClassProcess1Activity;

import java.util.List;

/**
 * Created by yangk on 2018/3/8.
 */

public class ExperienceClassListAdatper extends RecyclerView.Adapter<ExperienceClassListAdatper.ViewHolder> {

    private List<ExperienceClassBean> experienceClassBeanList;
    private Context context;

    public ExperienceClassListAdatper(Context context, List<ExperienceClassBean> experienceClassBeanList){
        this.context = context;
        this.experienceClassBeanList = experienceClassBeanList;
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

        Glide.with(context).load(experienceClassBean.getHeaderUrl()).apply(options).into(holder.iv_header);
        int sex = experienceClassBean.getSex();
        if (sex==0){
            Glide.with(context).load(R.mipmap.lg_man).apply(options).into(holder.iv_gender);
        }else {
            Glide.with(context).load(R.mipmap.lg_women).apply(options).into(holder.iv_gender);
        }

        holder.tv_name.setText(experienceClassBean.getName());
        holder.tv_card_name.setText(experienceClassBean.getCardName());
        holder.tv_card_type.setText(experienceClassBean.getCardType());
        holder.tv_experienced_count.setText(experienceClassBean.getExperiencedCuont()+"");
        holder.tv_current_operation.setText(experienceClassBean.getCurrentOperation());

        holder.experience_class_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExperienceClassProcess1Activity.class);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return experienceClassBeanList==null?0:experienceClassBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout experience_class_item_view;
        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_card_name;
        TextView tv_card_type;
        TextView tv_experienced_count;
        TextView tv_current_operation;


        public ViewHolder(View view) {
            super(view);
            experience_class_item_view =  view.findViewById(R.id.experience_class_item_view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_card_name   = view.findViewById(R.id.tv_card_name);
            tv_card_type =     view.findViewById(R.id.tv_card_type);
            tv_experienced_count =     view.findViewById(R.id.tv_experienced_count);
            tv_current_operation  =     view.findViewById(R.id.tv_current_operation);
        }
    }

}