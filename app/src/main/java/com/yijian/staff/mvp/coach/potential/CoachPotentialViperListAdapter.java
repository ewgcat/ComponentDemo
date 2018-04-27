package com.yijian.staff.mvp.coach.potential;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.CoachViperBean;
import com.yijian.staff.mvp.coach.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.huiji.invitation.index.InvateIndexActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DateUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class CoachPotentialViperListAdapter extends RecyclerView.Adapter<CoachPotentialViperListAdapter.ViewHolder> {

    private List<CoachViperBean> viperBeanList;
    private Context context;

    public CoachPotentialViperListAdapter(Context context, List<CoachViperBean> viperBeanList) {
        this.context = context;
        this.viperBeanList = viperBeanList;
    }

    @Override
    public CoachPotentialViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coach_potential_vip, parent, false);
        CoachPotentialViperListAdapter.ViewHolder holder = new CoachPotentialViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CoachPotentialViperListAdapter.ViewHolder holder, int position) {
        CoachViperBean coachViperBean = viperBeanList.get(position);
        holder.tv_name.setText(coachViperBean.getName());

        long birthday = coachViperBean.getBirthday();
        if (birthday!=0){
            String s = DateUtil.parseLongDateToDateString(birthday);
            holder.tv_birth.setText(s);
        }else {
            holder.tv_birth.setText("");
        }

        holder.tv_birth_type.setText(coachViperBean.getBirthdayType());
        holder.tv_bodybuildingHobby.setText(coachViperBean.getFitnessHobby());
        holder.tv_bodyStatus.setText(coachViperBean.getHealthStatus());
        holder.tv_interestHobby.setText(coachViperBean.getHobby());
        holder.iv_gender.setImageResource("2".equals(coachViperBean.getSex()) ? R.mipmap.lg_women : R.mipmap.lg_man);
        holder.tv_useCar.setText(coachViperBean.getUseCar());

        //回访

        Boolean isProtected = coachViperBean.getProtected();
        if (isProtected){
            holder.tv_huifang.setText("保护7天");
        }else {
            holder.tv_huifang.setText("回访");
            String mobile = coachViperBean.getMobile();
            holder.lin_visit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(mobile)){
                        if (CommonUtil.isPhoneFormat(mobile)){
                            CommonUtil.callPhone(context, mobile);
                            HashMap<String, String> param = new HashMap<>();
                            param.put("interviewRecordId","4");
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

        //邀约
        holder.lin_invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvateIndexActivity.class);
                intent.putExtra("memberId",coachViperBean.getMemberId());
                intent.putExtra("memberName",coachViperBean.getName());
                context.startActivity(intent);
            }
        });


        //详情
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoachViperDetailActivity.class);
                intent.putExtra("vipType",1);
                intent.putExtra("coachViperBean",coachViperBean);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viperBeanList == null ? 0 : viperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_birth;
        TextView tv_birth_type;
        TextView tv_bodyStatus;
        TextView tv_bodybuildingHobby;
        TextView tv_interestHobby;
        TextView tv_useCar;
        TextView tv_huifang;
        LinearLayout lin_visit; //回访
        LinearLayout lin_invitation; //邀请
        LinearLayout ll_content; //真个Item条目


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_birth = view.findViewById(R.id.tv_birth);
            tv_birth_type = view.findViewById(R.id.tv_birth_type);
            tv_bodyStatus = view.findViewById(R.id.tv_bodyStatus);
            tv_bodybuildingHobby = view.findViewById(R.id.tv_bodybuildingHobby);
            tv_interestHobby = view.findViewById(R.id.tv_interestHobby);
            tv_useCar = view.findViewById(R.id.tv_useCar);
            tv_useCar = view.findViewById(R.id.tv_huifang);

            ll_content = view.findViewById(R.id.ll_content);

            lin_visit = view.findViewById(R.id.lin_visit);
            lin_invitation = view.findViewById(R.id.lin_invitation);
        }
    }

}
