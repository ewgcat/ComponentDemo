package com.yijian.staff.mvp.coach.search;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.bean.CoachSearchViperBean;
import com.yijian.staff.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.card.CoachVipCardListAdapter;
import com.yijian.staff.mvp.coach.classbaojia.NoSearchBarCoachClassBaojiaActivity;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.coach.recordchart.RecordChartActivity;
import com.yijian.staff.mvp.coach.viperlist.adpater.CoachViperListAdapter;
import com.yijian.staff.mvp.huiji.invitation.index.InvateIndexActivity;
import com.yijian.staff.mvp.reception.physical.PhysicalReportActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.ImageLoader;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class CoachSearchViperListAdapter extends RecyclerView.Adapter<CoachSearchViperListAdapter.ViewHolder> {

    private static final String TAG = CoachSearchViperListAdapter.class.getSimpleName();
    private List<CoachSearchViperBean> coachViperBeanList;
    private Context context;

    public CoachSearchViperListAdapter(Context context, List<CoachSearchViperBean> viperBeanList) {
        this.context = context;
        this.coachViperBeanList = viperBeanList;
    }

    @Override
    public CoachSearchViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_search_vip_info, parent, false);
        CoachSearchViperListAdapter.ViewHolder holder = new CoachSearchViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<CoachSearchViperBean> viperBeanList) {
        this.coachViperBeanList = viperBeanList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CoachSearchViperListAdapter.ViewHolder holder, int position) {
        CoachSearchViperBean coachViperBean = coachViperBeanList.get(position);

        holder.tv_name.setText(coachViperBean.getName());
        holder.tv_role.setText(coachViperBean.getViperRole());
        int resId;
        if (coachViperBean.getSex().equals("1")) {
            resId = R.mipmap.lg_man;
        } else if (coachViperBean.getSex().equals("2")) {
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

        Glide.with(context).load(headImg).apply(options).into( holder.iv_header);

        String subclassName = coachViperBean.getSubclassName();
        Intent intent = new Intent(context, CoachViperDetailActivity.class);

        if (!TextUtils.isEmpty(subclassName)){
            if (subclassName.equals("CoachInfoVO")){
                intent.putExtra("vipType", 0);
            }else   if (subclassName.equals("CoachIntentionVO")){
                intent.putExtra("vipType", 1);
            }else   if (subclassName.equals("CoachIntentionVO")){
                intent.putExtra("PotentialVO", 2);
            }else   if (subclassName.equals("CoachExpireVO")){
                intent.putExtra("vipType", 3);
            }

        }
        //详情
        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("CoachSearchViperBean", coachViperBean);
                context.startActivity(intent);
            }
        });

        Boolean isProtected = coachViperBean.getProtected();
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
                            param.put("interviewRecordId", "4");
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
        TextView tv_role;
        TextView tv_call;

        LinearLayout lin_content;

        public ViewHolder(View view) {
            super(view);
            lin_content = view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            iv_call = view.findViewById(R.id.iv_call);
            iv_suo = view.findViewById(R.id.iv_suo);
            tv_call = view.findViewById(R.id.tv_call);
            tv_name = view.findViewById(R.id.tv_name);
            tv_role = view.findViewById(R.id.tv_role);
        }
    }

}
