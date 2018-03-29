package com.yijian.staff.mvp.huiji.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.ViperBean;
import com.yijian.staff.mvp.vip.bean.VipOutdateInfo;
import com.yijian.staff.mvp.vip.potential.PotentialViperListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class HuiJiVipSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> dataList = new ArrayList<Object>();
    private int TYPE_VIP_CEREMONIAL_INFO = 0; //正式会员
    private int TYPE_VIP_OUTDATE_INFO = 1;  //过期会员
    private int TYPE_VIP_POTENTIAL_INFO = 2;  //潜在会员
    private int TYPE_VIP_INTENT_INFO = 3;  //意向会员

    public void resetList(List<Object> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType == TYPE_VIP_CEREMONIAL_INFO){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_info, parent, false);
            viewHolder = new CeremonialViewHolder(view);
        }else if(viewType == TYPE_VIP_OUTDATE_INFO){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oute_huiji_date, parent, false);
            viewHolder = new OutDateViewHolder(view);
        }else if(viewType == TYPE_VIP_INTENT_INFO){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_intention_people_info, parent, false);
            viewHolder = new IntentViewHolder(view);
        }else if(viewType == TYPE_VIP_POTENTIAL_INFO){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_potential_people_info, parent, false);
            viewHolder = new PotentialViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object dataObj = dataList.get(position);
        if (dataObj instanceof ViperBean) {  //正式会员
            return TYPE_VIP_CEREMONIAL_INFO;
        }else if(dataObj instanceof VipOutdateInfo){  //过期会员
            return TYPE_VIP_OUTDATE_INFO;
        }else if(dataObj instanceof ViperBean){  // 意向会员
            return TYPE_VIP_INTENT_INFO;
        }else if(dataObj instanceof ViperBean){  // 潜在会员
            return TYPE_VIP_POTENTIAL_INFO;
        }
        return TYPE_VIP_CEREMONIAL_INFO;
    }

    class CeremonialViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_cardName;
        TextView tv_card_type;
        TextView tv_private_coach;
        TextView tv_like_lesson;
        TextView tv_like_teacher;
        TextView tv_regist_time;
        TextView tv_contract_overTime;
        TextView tv_contract_balance;
        TextView tv_buy_count;
        TextView tv_be_present_time;
        TextView tv_be_departure_time;
        RelativeLayout rel_be_present_time;
        RelativeLayout rel_be_departure_time;
        LinearLayout lin_query_contract;
        LinearLayout lin_query_question;
        LinearLayout lin_content;

        public CeremonialViewHolder(View view) {
            super(view);
            lin_content = view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_cardName = view.findViewById(R.id.tv_cardName);
            tv_card_type = view.findViewById(R.id.tv_card_type);
            tv_private_coach = view.findViewById(R.id.tv_private_coach);
            tv_like_lesson = view.findViewById(R.id.tv_like_lesson);
            tv_like_teacher = view.findViewById(R.id.tv_like_teacher);
            tv_regist_time = view.findViewById(R.id.tv_regist_time);
            tv_contract_overTime = view.findViewById(R.id.tv_contract_overTime);
            tv_contract_balance = view.findViewById(R.id.tv_contract_balance);
            tv_buy_count = view.findViewById(R.id.tv_buy_count);
            tv_be_present_time = view.findViewById(R.id.tv_be_present_time);
            tv_be_departure_time = view.findViewById(R.id.tv_be_departure_time);
            rel_be_present_time = view.findViewById(R.id.rel_be_present_time);
            rel_be_departure_time = view.findViewById(R.id.rel_be_departure_time);
            lin_query_contract = view.findViewById(R.id.lin_query_contract);
            lin_query_question = view.findViewById(R.id.lin_query_question);
        }

        public void bind(ViperBean viperBean) {
            tv_name.setText(viperBean.getName());
            tv_cardName.setText(viperBean.getCardName());
            tv_card_type.setText(viperBean.getCardType());
            tv_private_coach.setText(viperBean.getPrivateCoach());
            tv_like_lesson.setText(viperBean.getFavorCourse());
            tv_like_teacher.setText(viperBean.getFavorTeacher());
            tv_regist_time.setText(viperBean.getRegisterTime());
            tv_contract_overTime.setText(viperBean.getContractDeadline());
            tv_contract_balance.setText(viperBean.getContractBalance());
            tv_buy_count.setText(viperBean.getPurchaseCount());
            tv_buy_count.setText(viperBean.getPurchaseCount());
        }

    }


    class OutDateViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        TextView tv_name;
        ImageView iv_gender;
        TextView tv_cardName;
        TextView tv_cardType;
        TextView tv_privateCoach;
        TextView tv_likeLesson;
        TextView tv_likeTeacher;
        TextView tv_registTime;
        TextView tv_contractOutDate;
        TextView tv_outDateDay;
        LinearLayout lin_quey_contract;
        LinearLayout lin_quey_question;



        public OutDateViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            tv_name   = view.findViewById(R.id.tv_name);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_cardName   = view.findViewById(R.id.tv_cardName);
            tv_cardType =     view.findViewById(R.id.tv_cardType);
            tv_privateCoach =     view.findViewById(R.id.tv_privateCoach);
            tv_likeLesson  =     view.findViewById(R.id.tv_likeLesson);
            tv_likeTeacher  =     view.findViewById(R.id.tv_likeTeacher);
            tv_registTime  =     view.findViewById(R.id.tv_registTime);
            tv_contractOutDate  =     view.findViewById(R.id.tv_contractOutDate);
            tv_outDateDay  =     view.findViewById(R.id.tv_outDateDay);
            lin_quey_contract  =     view.findViewById(R.id.lin_quey_contract);
            lin_quey_question  =     view.findViewById(R.id.lin_quey_question);
        }

        public void bind(VipOutdateInfo vipOutdateInfo){
            tv_name.setText(vipOutdateInfo.getName());
            tv_cardName.setText(vipOutdateInfo.getCardName());
            tv_cardType.setText(vipOutdateInfo.getCardType());
            tv_privateCoach.setText(vipOutdateInfo.getPrivateCoach());
            tv_likeLesson.setText(vipOutdateInfo.getLikeLesson());
            tv_likeTeacher.setText(vipOutdateInfo.getLikeTeacher());
            tv_registTime.setText(vipOutdateInfo.getRegistTime());
            tv_contractOutDate.setText(vipOutdateInfo.getContractOutDate());
            tv_outDateDay.setText(vipOutdateInfo.getOutDateDay());
        }

    }


    class IntentViewHolder extends RecyclerView.ViewHolder {

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


        public IntentViewHolder(View view) {
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


    class PotentialViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_birth;
        TextView tv_birth_type;
        TextView tv_bodyStatus;
        TextView tv_bodybuildingHobby;
        TextView tv_interestHobby;
        TextView tv_useCar;
        LinearLayout lin_visit; //回访
        LinearLayout lin_invitation; //邀请
        LinearLayout ll_content; //真个Item条目


        public PotentialViewHolder(View view) {
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

            ll_content = view.findViewById(R.id.ll_content);

            lin_visit = view.findViewById(R.id.lin_visit);
            lin_invitation = view.findViewById(R.id.lin_invitation);
        }
    }

}
