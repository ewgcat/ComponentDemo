package com.yijian.staff.mvp.coach.outdate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yangk on 2018/3/26.
 */

public class CoachOutdateViperListAdapter extends RecyclerView.Adapter<CoachOutdateViperListAdapter.ViewHolder>  {

    private List<CoachViperBean> coachViperBeanList;
    private Context context;

    public CoachOutdateViperListAdapter(Context context, List<CoachViperBean> coachViperBeanList){
        this.context = context;
        this.coachViperBeanList = coachViperBeanList;
    }


    @Override
    public CoachOutdateViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_outdate_coach_date, parent, false);
        CoachOutdateViperListAdapter.ViewHolder holder = new CoachOutdateViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachOutdateViperListAdapter.ViewHolder holder, int position) {
        CoachViperBean coachViperBean = coachViperBeanList.get(position);
        holder.tv_name.setText(coachViperBean.getName());
        int resId;
        if (coachViperBean.getSex().equals("1")){
            resId =  R.mipmap.lg_man ;
        } else if (coachViperBean.getSex().equals("2")){
            resId =  R.mipmap.lg_women ;
        }else {
            resId =  R.mipmap.lg_man ;
        }
        holder.iv_gender.setImageResource(resId);
        holder.rv_card.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_card.setAdapter(new CoachVipCardListAdapter(coachViperBean.getCardprodsBeans()));

        holder.rel_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCardView(holder);
            }
        });
        holder.tv_history_lesson.setText(coachViperBean.getHistoryCourse());


        long deadline = coachViperBean.getDeadline();
        if (deadline!=0){
            String s = DateUtil.parseLongDateToDateString(deadline);
            holder.tv_outDate.setText(s);
        }else {
            holder.tv_outDate.setText("");
        }

        holder.tv_outDate_reason.setText(coachViperBean.getExpiryReason());


        holder.ll_chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoachViperDetailActivity.class);
                intent.putExtra("vipType",1);
                intent.putExtra("coachViperBean",coachViperBean);
                context.startActivity(intent);
            }
        });


        //回访
        Boolean isProtected = coachViperBean.getProtected();
        if (isProtected){
            holder.iv_huifang.setImageResource(R.mipmap.my_password_new);
            holder.tv_huifang.setText("保护7天");
        }else {
            holder.iv_huifang.setImageResource(R.mipmap.wt_huifang);
            holder.tv_huifang.setText("回访");
            String mobile = coachViperBean.getMobile();
            holder.lin_huifan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (!TextUtils.isEmpty(mobile)){
                        if (CommonUtil.isPhoneFormat(mobile)){
                            CommonUtil.callPhone(context, mobile);
                            HashMap<String, String> param = new HashMap<>();
                            param.put("interviewRecordId","8");
                            param.put("memberId",coachViperBean.getMemberId());
                            HttpManager.getHasHeaderHasParam(HttpManager.GET_VIP_COACH_HUI_FANG_CALL_PHONE_URL, param, new ResultJSONObjectObserver() {
                                @Override
                                public void onSuccess(JSONObject result) {

                                }

                                @Override
                                public void onFail(String msg) {

                                }
                            });
                        }else {
                            Toast.makeText(context,"返回的手机号不正确！",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context,"未录入手机号！",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }




    }

    @Override
    public int getItemCount() {
        return coachViperBeanList==null?0:coachViperBeanList.size();
    }

    public void update(List<CoachViperBean> coachViperBeanList) {
        this.coachViperBeanList=coachViperBeanList;
        notifyDataSetChanged();
    }
    private void toggleCardView(CoachOutdateViperListAdapter.ViewHolder holder) {
        int visibility = holder.rv_card.getVisibility();
        if (visibility == View.GONE) {
            holder.rv_card.setVisibility(View.VISIBLE);
            holder.tv_zhankai_status.setText("收起");
            Drawable drawable = context.getDrawable(R.mipmap.fp_shang);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        } else {
            holder.rv_card.setVisibility(View.GONE);
            holder.tv_zhankai_status.setText("展开");
            Drawable drawable = context.getDrawable(R.mipmap.lg_xiala);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            holder.tv_zhankai_status.setCompoundDrawables(null,null,drawable,null);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_chakanxiangqing;
        ImageView iv_header;
        TextView tv_name;
        ImageView iv_gender;

        TextView tv_zhankai_status;
        RelativeLayout rel_expand;
        RecyclerView rv_card;

        TextView tv_history_lesson; //历史课程
        TextView tv_outDate; //过期时间
        TextView tv_outDate_reason; //过期原因
        TextView tv_huifang;
        LinearLayout lin_huifan;
        ImageView iv_huifang;


        public ViewHolder(View view) {
            super(view);
            ll_chakanxiangqing  =     view.findViewById(R.id.ll_chakanxiangqing);
            iv_header =  view.findViewById(R.id.iv_header);
            tv_name   = view.findViewById(R.id.tv_name);
            iv_gender =  view.findViewById(R.id.iv_gender);

            rel_expand =  view.findViewById(R.id.rel_expand);
            rv_card =  view.findViewById(R.id.rv_card);
            tv_zhankai_status =  view.findViewById(R.id.tv_zhankai_status);
            iv_huifang =  view.findViewById(R.id.iv_huifang);
            tv_huifang =  view.findViewById(R.id.tv_huifang);

            tv_history_lesson =     view.findViewById(R.id.tv_history_lesson);
            tv_outDate  =     view.findViewById(R.id.tv_outDate);
            tv_outDate_reason  =     view.findViewById(R.id.tv_outDate_reason);
            lin_huifan  =     view.findViewById(R.id.lin_huifan);
        }
    }

}
