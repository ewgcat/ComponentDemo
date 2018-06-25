package com.yijian.staff.mvp.coach.viperlist.adpater;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity_ycm;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.ImageLoader;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachViperListAdapter extends RecyclerView.Adapter<CoachViperListAdapter.ViewHolder> {

    private List<CoachViperBean> coachViperBeanList;
    private Context context;
    private Boolean isAllVipInfo; // true 全部会员，false  今日来访

    public CoachViperListAdapter(Context context, List<CoachViperBean> coachViperBeanList, boolean isAllVipInfo) {
        this.context = context;
        this.coachViperBeanList = coachViperBeanList;
        this.isAllVipInfo = isAllVipInfo;
    }

    @Override
    public CoachViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_vip_info, parent, false);
        CoachViperListAdapter.ViewHolder holder = new CoachViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<CoachViperBean> coachViperBeanList) {
        this.coachViperBeanList = coachViperBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(CoachViperListAdapter.ViewHolder holder, int position) {
        CoachViperBean coachViperBean = coachViperBeanList.get(position);

        holder.tv_name.setText(coachViperBean.getName());
        int resId;
        if (coachViperBean.getSex() == 1) {
            resId = R.mipmap.lg_man;
        } else if (coachViperBean.getSex() == 2) {
            resId = R.mipmap.lg_women;
        } else {
            resId = R.mipmap.lg_man;
        }
        holder.iv_gender.setImageResource(resId);

        String headImg = coachViperBean.getHeadImg();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .transform(new GlideCircleTransform())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        ImageLoader.setHeadImageResource(headImg, context, holder.iv_header);


        //详情
        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, CoachViperDetailActivity.class);
                Intent intent = new Intent(context, CoachViperDetailActivity_ycm.class);
                intent.putExtra("vipType", 0);
                intent.putExtra("memberId", coachViperBean.getMemberId());
                context.startActivity(intent);
            }
        });
        Boolean isProtected = coachViperBean.isUnderProtected();
        if (isProtected) {
            holder.iv_call.setVisibility(View.GONE);
            holder.tv_call.setVisibility(View.VISIBLE);
            holder.iv_suo.setVisibility(View.VISIBLE);

        } else {
            Glide.with(context).load(R.mipmap.dianhua).apply(options).into(holder.iv_call);
            holder.tv_call.setVisibility(View.GONE);
            holder.iv_suo.setVisibility(View.GONE);
            holder.iv_call.setVisibility(View.VISIBLE);

            String mobile = coachViperBean.getMobile();
            holder.iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(mobile)) {
                        if (CommonUtil.isPhoneFormat(mobile)) {
                            CommonUtil.callPhone(context, mobile);
                            HashMap<String, String> param = new HashMap<>();
                            param.put("interviewRecordId", "0");
                            param.put("memberId", coachViperBean.getMemberId());
                            HttpManager.getHasHeaderHasParam(HttpManager.GET_VIP_COACH_HUI_FANG_CALL_PHONE_URL, param, new ResultJSONObjectObserver() {
                                @Override
                                public void onSuccess(JSONObject result) {

                                }

                                @Override
                                public void onFail(String msg) {

                                }
                            });
                        } else {
                            Toast.makeText(context, "返回的手机号不正确！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "未录入手机号！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return coachViperBeanList == null ? 0 : coachViperBeanList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        ImageView iv_call;
        ImageView iv_suo;

        TextView tv_name;
        TextView tv_call;

        LinearLayout lin_content;

        public ViewHolder(View view) {
            super(view);
            lin_content = view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_call = view.findViewById(R.id.iv_call);
            iv_suo = view.findViewById(R.id.iv_suo);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_call = view.findViewById(R.id.tv_call);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }

}
