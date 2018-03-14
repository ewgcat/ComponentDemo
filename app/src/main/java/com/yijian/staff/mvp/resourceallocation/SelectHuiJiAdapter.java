package com.yijian.staff.mvp.resourceallocation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.bean.HuijiInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/12.
 * 选择会籍适配器
 */

public class SelectHuiJiAdapter extends RecyclerView.Adapter<SelectHuiJiAdapter.ViewHolder> {

    private List<HuijiInfo> huijiInfoList = new ArrayList<>();
    private Context context;
    private Map<Integer, Boolean> checkMap;


    public SelectHuiJiAdapter(Context context, List<HuijiInfo> huijiInfoList) {
        this.context = context;
        this.huijiInfoList = huijiInfoList;
        checkMap = new HashMap<Integer, Boolean>();
    }

    @Override
    public SelectHuiJiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_huiji, parent, false);
        SelectHuiJiAdapter.ViewHolder holder = new SelectHuiJiAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectHuiJiAdapter.ViewHolder holder, int position) {
        HuijiInfo huijiInfo = huijiInfoList.get(position);
        holder.tv_name.setText(huijiInfo.getName());
        holder.iv_gender.setImageResource(huijiInfo.getGender());
        holder.tv_comment_grade.setText(huijiInfo.getCommentGrade() + "分");
        holder.rb_grate.setClickable(false);//设置可否点击
        holder.rb_grate.setStar(Float.valueOf(huijiInfo.getCommentGrade()));//设置显示的星星个数
        holder.rb_grate.setStepSize(RatingBar.StepSize.Half);//设置每次点击增加一颗星还是半颗星

        holder.tv_businessProgress.setText(huijiInfo.getBusinessProgress());
        holder.tv_vipServiceNum.setText(huijiInfo.getVipServiceNum() + "人");
        holder.tv_actionTargetProgress.setText(huijiInfo.getActionTargetProgress());
        holder.tv_potentialServiceNum.setText(huijiInfo.getPotentialServiceNum() + "人");

        holder.ck_select_huiji.setTag(position);
        if (checkMap.containsKey(position)){
            holder.ck_select_huiji.setChecked(checkMap.get(position));
        }else {
            holder.ck_select_huiji.setChecked(false);//true or false 都可以，看实际需求
        }
        holder.ck_select_huiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.ck_select_huiji.isChecked()){
                    checkMap.put(position,true);
                }else {
                    checkMap.put(position,false);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return huijiInfoList == null ? 0 : huijiInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        com.yijian.staff.mvp.resourceallocation.RatingBar rb_grate; //评分等级星星
        TextView tv_comment_grade; //评分等级
        TextView tv_businessProgress; //业务完成度
        TextView tv_vipServiceNum; //会员服务数
        TextView tv_actionTargetProgress; //行为指标完成度
        TextView tv_potentialServiceNum; //潜在服务数
        CheckBox ck_select_huiji; //是否选择会籍的复选框
        View view_lin;//分割线


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_comment_grade = view.findViewById(R.id.tv_comment_grade);
            rb_grate = view.findViewById(R.id.rb_grate);
            tv_businessProgress = view.findViewById(R.id.tv_businessProgress);
            tv_vipServiceNum = view.findViewById(R.id.tv_vipServiceNum);
            tv_actionTargetProgress = view.findViewById(R.id.tv_actionTargetProgress);
            tv_potentialServiceNum = view.findViewById(R.id.tv_potentialServiceNum);
            ck_select_huiji = view.findViewById(R.id.ck_select_huiji);
            view_lin = view.findViewById(R.id.view_lin);
        }
    }


}
