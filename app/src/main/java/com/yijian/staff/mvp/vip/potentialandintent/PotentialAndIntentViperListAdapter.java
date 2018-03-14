package com.yijian.staff.mvp.vip.potentialandintent;

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
import com.yijian.staff.mvp.vip.model.VipPeopleInfo;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class PotentialAndIntentViperListAdapter extends RecyclerView.Adapter<PotentialAndIntentViperListAdapter.ViewHolder> {

    private List<VipPeopleInfo> vipPeopleInfoList;
    private Context context;

    public PotentialAndIntentViperListAdapter(Context context, List<VipPeopleInfo> vipPeopleInfoList){
        this.context = context;
        this.vipPeopleInfoList = vipPeopleInfoList;
    }

    @Override
    public PotentialAndIntentViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_intention_people_info, parent, false);
        PotentialAndIntentViperListAdapter.ViewHolder holder = new PotentialAndIntentViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PotentialAndIntentViperListAdapter.ViewHolder holder, int position) {
        VipPeopleInfo vipPeopleInfo = vipPeopleInfoList.get(position);
        holder.tv_name.setText(vipPeopleInfo.getName());
        holder.tv_birth.setText(vipPeopleInfo.getBirth());
        holder.tv_birth_type.setText(vipPeopleInfo.getBirthType());
        holder.tv_bodybuildingHobby.setText(vipPeopleInfo.getBodybuildingHobby());
        holder.tv_bodyStatus.setText(vipPeopleInfo.getBodyStatus());
        holder.tv_interestHobby.setText(vipPeopleInfo.getInterestHobby());
        holder.iv_gender.setImageResource("0".equals(vipPeopleInfo.getGender())?R.mipmap.lg_women:R.mipmap.lg_man);
        holder.tv_useCar.setText(vipPeopleInfo.getUseCar());
        holder.lin_input_questionnaire.setVisibility("0".equals(vipPeopleInfo.getIsIntentVip())?View.GONE:View.VISIBLE);  // 0 意向会员  ，1  潜在会员
        holder.lin_protect_seven.setVisibility("0".equals(vipPeopleInfo.getIsIntentVip())?View.VISIBLE:View.GONE);
        holder.lin_visit.setVisibility("0".equals(vipPeopleInfo.getIsIntentVip())?View.GONE:View.VISIBLE);



        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,PotentialAndIntentViperDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return vipPeopleInfoList==null?0:vipPeopleInfoList.size();
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
        LinearLayout lin_input_questionnaire; //录入问卷
        LinearLayout lin_visit; //回访
        LinearLayout lin_invitation; //邀请
        LinearLayout ll_content; //真个Item条目
        LinearLayout lin_protect_seven; //保护7天


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_birth  =     view.findViewById(R.id.tv_birth);
            tv_birth_type =     view.findViewById(R.id.tv_birth_type);
            tv_bodyStatus =     view.findViewById(R.id.tv_bodyStatus);
            tv_bodybuildingHobby =     view.findViewById(R.id.tv_bodybuildingHobby);
            tv_interestHobby =     view.findViewById(R.id.tv_interestHobby);
            tv_useCar  =     view.findViewById(R.id.tv_useCar);
            lin_input_questionnaire  =     view.findViewById(R.id.lin_input_questionnaire);
            lin_visit =     view.findViewById(R.id.lin_visit);
            lin_invitation =     view.findViewById(R.id.lin_invitation);
            ll_content =     view.findViewById(R.id.ll_content);
            lin_protect_seven =     view.findViewById(R.id.lin_protect_seven);
        }
    }

}
