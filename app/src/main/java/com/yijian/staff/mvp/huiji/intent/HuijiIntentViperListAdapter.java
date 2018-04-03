package com.yijian.staff.mvp.huiji.intent;

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
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.huiji.invitation.index.InvateIndexActivity;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class HuijiIntentViperListAdapter extends RecyclerView.Adapter<HuijiIntentViperListAdapter.ViewHolder> {

    private List<HuiJiViperBean> viperBeanList;
    private Context context;

    public HuijiIntentViperListAdapter(Context context, List<HuiJiViperBean> viperBeanList) {
        this.context = context;
        this.viperBeanList = viperBeanList;
    }

    @Override
    public HuijiIntentViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_intention_people_info, parent, false);
        HuijiIntentViperListAdapter.ViewHolder holder = new HuijiIntentViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HuijiIntentViperListAdapter.ViewHolder holder, int position) {
        HuiJiViperBean viperBean = viperBeanList.get(position);
        holder.tv_name.setText(viperBean.getName());
        holder.tv_birth.setText(viperBean.getBirthday());
        holder.tv_birth_type.setText(viperBean.getBirthdayType());
        holder.tv_bodybuildingHobby.setText(viperBean.getFitnessHobby());
        holder.tv_bodyStatus.setText(viperBean.getHealthStatus());
        holder.tv_interestHobby.setText(viperBean.getHobby());
        holder.iv_gender.setImageResource("1".equals(viperBean.getSex()) ? R.mipmap.lg_women : R.mipmap.lg_man);
        holder.tv_useCar.setText(viperBean.getUseCar());

        //TODO 回访 根据后台返回，保护7天是不能电话回访，不是保护七天时，可以电话回访
        holder.lin_protect_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //邀约
        holder.lin_invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, InvateIndexActivity.class));

            }
        });

        //详情
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HuijiIntentViperDetailActivity.class);
                intent.putExtra("id",viperBean.getMemberId());
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
        LinearLayout lin_invitation; //邀请
        LinearLayout ll_content; //真个Item条目
        LinearLayout lin_protect_seven; //保护7天


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_birth = view.findViewById(R.id.tv_birth);
            tv_birth_type = view.findViewById(R.id.tv_birth_type);
            tv_bodyStatus = view.findViewById(R.id.tv_bodyStatus);
            ll_content = view.findViewById(R.id.ll_content);
            tv_bodybuildingHobby = view.findViewById(R.id.tv_bodybuildingHobby);
            tv_interestHobby = view.findViewById(R.id.tv_interestHobby);
            tv_useCar = view.findViewById(R.id.tv_useCar);

            tv_huifang = view.findViewById(R.id.tv_huifang);
            lin_protect_seven = view.findViewById(R.id.lin_protect_seven);

            lin_invitation = view.findViewById(R.id.lin_invitation);

        }
    }

}
