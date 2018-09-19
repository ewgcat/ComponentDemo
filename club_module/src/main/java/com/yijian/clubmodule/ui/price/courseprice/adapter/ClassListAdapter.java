package com.yijian.clubmodule.ui.price.courseprice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.ClassInfo;
import com.yijian.commonlib.util.Logger;
import com.yijian.commonlib.widget.AlwaysMarqueeTextView;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/3 17:08:17
 */
public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private List<ClassInfo> mClassInfoList;
    private Context context;
    private int clickIndex = -1;

    public ClassListAdapter(Context context, List<ClassInfo> mClassInfoList) {
        this.mClassInfoList = mClassInfoList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Logger.i("ClassListAdapter", "position: " + position);
//        if (clickIndex==position){
//            holder.item_view.setBackgroundResource(R.drawable.goods_blue_stroke_bg);
//
//        }else {
        holder.item_view.setBackgroundResource(R.drawable.white_bg);
//        }

        ClassInfo classInfo = mClassInfoList.get(position);


        Glide.with(context).load(classInfo.getImg()).into(holder.iv_img);
        holder.tv_class_name.setText(classInfo.getName());
        holder.tv_class_long_time.setText(classInfo.getLessonTime());
        holder.tv_class_num.setText(classInfo.getCleassNum());
        holder.tv_price.setText(classInfo.getBaseTotalPrice());
        holder.item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, classInfo);
                }
                clickIndex = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClassInfoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        AlwaysMarqueeTextView tv_class_name;
        TextView tv_price;
        ImageView iv_img;
        TextView tv_class_long_time;
        TextView tv_class_num;
        LinearLayout item_view;


        public ViewHolder(View view) {
            super(view);
            tv_class_name = view.findViewById(R.id.tv_class_name);
            iv_img = view.findViewById(R.id.iv_img);
            tv_class_long_time = view.findViewById(R.id.tv_class_long_time);
            tv_price = view.findViewById(R.id.tv_price);
            tv_class_num = view.findViewById(R.id.tv_class_num);
            item_view = view.findViewById(R.id.item_view);

        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onItemClick(View v, ClassInfo classInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
