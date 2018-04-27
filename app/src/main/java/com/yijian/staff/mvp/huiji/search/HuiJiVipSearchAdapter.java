package com.yijian.staff.mvp.huiji.search;

import android.content.Context;
import android.content.Intent;
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
import com.yijian.staff.mvp.contract.ContractActivity;
import com.yijian.staff.mvp.huiji.bean.HuiJiVipeCardAdapter;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.mvp.questionnaireresult.QuestionnaireResultActivity;
import com.yijian.staff.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class HuiJiVipSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HuiJiViperBean> dataList = new ArrayList<HuiJiViperBean>();
    private int TYPE_VIP_CEREMONIAL_INFO = 0; //正式会员
    private int TYPE_VIP_OUTDATE_INFO = 1;  //过期会员
    private int TYPE_VIP_POTENTIAL_INFO = 2;  //潜在会员
    private int TYPE_VIP_INTENT_INFO = 3;  //意向会员

    public HuiJiVipSearchAdapter(Context context, List<HuiJiViperBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void update(List<HuiJiViperBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_VIP_CEREMONIAL_INFO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_info, parent, false);
            viewHolder = new CeremonialViewHolder(view);
        } else if (viewType == TYPE_VIP_OUTDATE_INFO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oute_huiji_date, parent, false);
            viewHolder = new OutDateViewHolder(view);
        } else if (viewType == TYPE_VIP_INTENT_INFO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_intention_people_info, parent, false);
            viewHolder = new IntentViewHolder(view);
        } else if (viewType == TYPE_VIP_POTENTIAL_INFO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_potential_people_info, parent, false);
            viewHolder = new PotentialViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HuiJiViperBean huiJiSearchViperBean = dataList.get(position);
        int viewType = getItemViewType(position);
        if (viewType == TYPE_VIP_CEREMONIAL_INFO) { //普通会员
            ((CeremonialViewHolder) holder).bind(huiJiSearchViperBean);
        } else if (viewType == TYPE_VIP_OUTDATE_INFO) { //过期会员
            ((OutDateViewHolder) holder).bind(huiJiSearchViperBean);
        } else if (viewType == TYPE_VIP_INTENT_INFO) { //意向会员
            ((IntentViewHolder) holder).bind(huiJiSearchViperBean);
        } else if (viewType == TYPE_VIP_POTENTIAL_INFO) { //潜在会员
            ((PotentialViewHolder) holder).bind(huiJiSearchViperBean);
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        HuiJiViperBean huiJiSearchViperBean = dataList.get(position);
        String subclassName = huiJiSearchViperBean.getSubclassName();
        if (subclassName.equals("CustomerInfoVO")) { //正式会员
            return TYPE_VIP_CEREMONIAL_INFO;
        } else if (subclassName.equals("PotentialVO")) { // 潜在会员
            return TYPE_VIP_POTENTIAL_INFO;
        } else if (subclassName.equals("CustomerIntentionVO")) { // 意向会员
            return TYPE_VIP_INTENT_INFO;
        } else if (subclassName.equals("CustomerExpireVO")) {  // 过期会员
            return TYPE_VIP_OUTDATE_INFO;
        }

        return TYPE_VIP_CEREMONIAL_INFO;
    }

    class CeremonialViewHolder extends RecyclerView.ViewHolder { // 普通会员

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_role;

        RelativeLayout rel_expand;
        TextView tv_opration_label;
        ImageView iv_opration_arrow;
        RecyclerView rv_card;

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

        List<HuiJiViperBean.CardprodsBean> cardprodsBeanList = new ArrayList<>();
        HuiJiVipeCardAdapter huiJiVipeCardAdapter;

        public CeremonialViewHolder(View view) {
            super(view);
            lin_content = view.findViewById(R.id.lin_content);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_role = view.findViewById(R.id.tv_role);
            tv_role.setVisibility(View.VISIBLE);
            rel_expand = view.findViewById(R.id.rel_expand);
            tv_opration_label = view.findViewById(R.id.tv_opration_label);
            rv_card = view.findViewById(R.id.rv_card);
            LinearLayoutManager layoutmanager = new LinearLayoutManager(context);
            rv_card.setLayoutManager(layoutmanager);
            huiJiVipeCardAdapter = new HuiJiVipeCardAdapter(cardprodsBeanList);
            rv_card.setAdapter(huiJiVipeCardAdapter);
            iv_opration_arrow = view.findViewById(R.id.iv_opration_arrow);

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

        public void bind(HuiJiViperBean huiJiSearchViperBean) {
            tv_role.setVisibility(View.VISIBLE);
            tv_role.setText(huiJiSearchViperBean.getViperRole());
            tv_name.setText(huiJiSearchViperBean.getName());
            huiJiVipeCardAdapter.setCardprodsBeans(huiJiSearchViperBean.getCardprodsBeans());


            rel_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tv_opration_label.setText((rv_card.getVisibility() == View.GONE) ? "收起" : "展开");
                    rv_card.setVisibility((rv_card.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
                    iv_opration_arrow.setImageResource((rv_card.getVisibility() == View.GONE) ? R.mipmap.fp_xia : R.mipmap.fp_shang);

                }
            });

            tv_private_coach.setText(huiJiSearchViperBean.getPrivateCoach());
            tv_like_lesson.setText(huiJiSearchViperBean.getFavorCourse());
            tv_like_teacher.setText(huiJiSearchViperBean.getFavorTeacher());
            tv_regist_time.setText(huiJiSearchViperBean.getRegisterTime());
           /* tv_contract_overTime.setText(huiJiSearchViperBean.getContractDeadline()); //合同到期日
            tv_contract_balance.setText(huiJiSearchViperBean.getContractBalance());  //合同余额
            */
            tv_buy_count.setText(huiJiSearchViperBean.getPurchaseCount());
            tv_buy_count.setText(huiJiSearchViperBean.getPurchaseCount());
            lin_query_contract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ContractActivity.class);
                    intent.putExtra("memberId",huiJiSearchViperBean.getMemberId());
                    intent.putExtra("memberName",huiJiSearchViperBean.getName());
                    context.startActivity(intent);
                }
            });

            lin_query_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, QuestionnaireResultActivity.class);
                    intent.putExtra("memberId",huiJiSearchViperBean.getMemberId());
                    intent.putExtra("memberName",huiJiSearchViperBean.getName());
                    context.startActivity(intent);

                }
            });
        }

    }


    class OutDateViewHolder extends RecyclerView.ViewHolder {  // 过期会员

        ImageView iv_header;
        TextView tv_name;
        TextView tv_role;
        ImageView iv_gender;
        RelativeLayout rel_expand;
        TextView tv_opration_label;
        ImageView iv_opration_arrow;
        RecyclerView rv_card;
        TextView tv_privateCoach;
        TextView tv_likeLesson;
        TextView tv_likeTeacher;
        TextView tv_registTime;
        TextView tv_contractOutDate;
        TextView tv_outDateDay;
        LinearLayout lin_quey_contract;
        LinearLayout lin_quey_question;

        List<HuiJiViperBean.CardprodsBean> cardprodsBeanList = new ArrayList<>();
        HuiJiVipeCardAdapter huiJiVipeCardAdapter;


        public OutDateViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            tv_name = view.findViewById(R.id.tv_name);
            tv_role = view.findViewById(R.id.tv_role);
            iv_gender = view.findViewById(R.id.iv_gender);
            rel_expand = view.findViewById(R.id.rel_expand);
            tv_opration_label = view.findViewById(R.id.tv_opration_label);
            iv_opration_arrow = view.findViewById(R.id.iv_opration_arrow);
            rv_card = view.findViewById(R.id.rv_card);
            LinearLayoutManager layoutmanager = new LinearLayoutManager(context);
            rv_card.setLayoutManager(layoutmanager);
            huiJiVipeCardAdapter = new HuiJiVipeCardAdapter(cardprodsBeanList);
            rv_card.setAdapter(huiJiVipeCardAdapter);
            tv_privateCoach = view.findViewById(R.id.tv_privateCoach);
            tv_likeLesson = view.findViewById(R.id.tv_likeLesson);
            tv_likeTeacher = view.findViewById(R.id.tv_likeTeacher);
            tv_registTime = view.findViewById(R.id.tv_registTime);
            tv_contractOutDate = view.findViewById(R.id.tv_contractOutDate);
            tv_outDateDay = view.findViewById(R.id.tv_outDateDay);
            lin_quey_contract = view.findViewById(R.id.lin_quey_contract);
            lin_quey_question = view.findViewById(R.id.lin_quey_question);

        }

        public void bind(HuiJiViperBean huiJiSearchViperBean) {
            tv_role.setText(huiJiSearchViperBean.getViperRole());
            tv_role.setVisibility(View.VISIBLE);
            tv_name.setText(huiJiSearchViperBean.getName());
            huiJiVipeCardAdapter.setCardprodsBeans(huiJiSearchViperBean.getCardprodsBeans());
            rel_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tv_opration_label.setText((rv_card.getVisibility() == View.GONE) ? "收起" : "展开");
                    rv_card.setVisibility((rv_card.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
                    iv_opration_arrow.setImageResource((rv_card.getVisibility() == View.GONE) ? R.mipmap.fp_xia : R.mipmap.fp_shang);

                }


            });

            tv_privateCoach.setText(huiJiSearchViperBean.getPrivateCoach());
            tv_likeLesson.setText(huiJiSearchViperBean.getFavorCourse());
            tv_likeTeacher.setText(huiJiSearchViperBean.getFavorTeacher());
            tv_registTime.setText(huiJiSearchViperBean.getRegisterTime());
            /*tv_contractOutDate.setText(huiJiSearchViperBean.getPrivateCourse()); //过期时间
            tv_outDateDay.setText(huiJiSearchViperBean.); //过期天数
            */
            lin_quey_contract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ContractActivity.class);
                    intent.putExtra("memberId",huiJiSearchViperBean.getMemberId());
                    intent.putExtra("memberName",huiJiSearchViperBean.getName());
                    context.startActivity(intent);
                }
            });

            lin_quey_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, QuestionnaireResultActivity.class);
                    intent.putExtra("memberId",huiJiSearchViperBean.getMemberId());
                    intent.putExtra("memberName",huiJiSearchViperBean.getName());
                    context.startActivity(intent);

                }
            });
        }

    }


    class IntentViewHolder extends RecyclerView.ViewHolder {  // 意向会员

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_role;
        TextView tv_birth;
        TextView tv_birth_type;
        TextView tv_bodyStatus;
        TextView tv_bodybuildingHobby;
        TextView tv_interestHobby;
        TextView tv_useCar;
        LinearLayout ll_content; //真个Item条目
        LinearLayout lin_huifan;
        LinearLayout lin_yaoyue;
        ImageView iv_huifang;
        TextView tv_huifang;


        public IntentViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_role = view.findViewById(R.id.tv_role);
            tv_birth = view.findViewById(R.id.tv_birth);
            tv_birth_type = view.findViewById(R.id.tv_birth_type);
            tv_bodyStatus = view.findViewById(R.id.tv_bodyStatus);
            ll_content = view.findViewById(R.id.ll_content);
            tv_bodybuildingHobby = view.findViewById(R.id.tv_bodybuildingHobby);
            tv_interestHobby = view.findViewById(R.id.tv_interestHobby);
            tv_useCar = view.findViewById(R.id.tv_useCar);

            tv_huifang = view.findViewById(R.id.tv_huifang);
            lin_huifan  =     view.findViewById(R.id.lin_huifan);
            lin_yaoyue  =     view.findViewById(R.id.lin_yaoyue);
            iv_huifang =  view.findViewById(R.id.iv_huifang);
            tv_huifang =  view.findViewById(R.id.tv_huifang);

        }

        public void bind(HuiJiViperBean huiJiSearchViperBean) {
            tv_role.setText(huiJiSearchViperBean.getViperRole());
            tv_role.setVisibility(View.VISIBLE);
            tv_name.setText(huiJiSearchViperBean.getName());
            tv_birth.setText(huiJiSearchViperBean.getBirthday());
            tv_birth_type.setText(huiJiSearchViperBean.getBirthdayType());
            tv_bodyStatus.setText(huiJiSearchViperBean.getHealthStatus());
            tv_bodybuildingHobby.setText(huiJiSearchViperBean.getFitnessHobby());
            tv_interestHobby.setText(huiJiSearchViperBean.getHobby());
            tv_useCar.setText(huiJiSearchViperBean.getUseCar());
            //回访
            Boolean isProtected = huiJiSearchViperBean.getProtected();
            if (isProtected){
                iv_huifang.setImageResource(R.mipmap.my_password_new);
                tv_huifang.setText("保护7天");
            }else {
                iv_huifang.setImageResource(R.mipmap.wt_huifang);
                tv_huifang.setText("回访");
                String mobile = huiJiSearchViperBean.getMobile();
                lin_huifan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(mobile)){
                            CommonUtil.callPhone(context,mobile);
                        } else {
                            Toast.makeText(context,"未录入手机号,无法进行电话回访",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            ll_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }


    class PotentialViewHolder extends RecyclerView.ViewHolder {  // 潜在会员

        ImageView iv_header;
        ImageView iv_gender;
        TextView tv_name;
        TextView tv_role;
        TextView tv_birth;
        TextView tv_birth_type;
        TextView tv_bodyStatus;
        TextView tv_bodybuildingHobby;
        TextView tv_interestHobby;
        TextView tv_useCar;
        LinearLayout ll_content; //真个Item条目
        LinearLayout lin_huifan;
        LinearLayout lin_yaoyue;
        ImageView iv_huifang;
        TextView tv_huifang;


        public PotentialViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_role = view.findViewById(R.id.tv_role);
            tv_birth = view.findViewById(R.id.tv_birth);
            tv_birth_type = view.findViewById(R.id.tv_birth_type);
            tv_bodyStatus = view.findViewById(R.id.tv_bodyStatus);
            tv_bodybuildingHobby = view.findViewById(R.id.tv_bodybuildingHobby);
            tv_interestHobby = view.findViewById(R.id.tv_interestHobby);
            tv_useCar = view.findViewById(R.id.tv_useCar);

            ll_content = view.findViewById(R.id.ll_content);

            lin_huifan  =     view.findViewById(R.id.lin_huifan);
            lin_yaoyue  =     view.findViewById(R.id.lin_yaoyue);
            iv_huifang =  view.findViewById(R.id.iv_huifang);
            tv_huifang =  view.findViewById(R.id.tv_huifang);
        }

        public void bind(HuiJiViperBean huiJiSearchViperBean) {
            tv_role.setText(huiJiSearchViperBean.getViperRole());
            tv_role.setVisibility(View.VISIBLE);
            tv_name.setText(huiJiSearchViperBean.getName());
            tv_birth.setText(huiJiSearchViperBean.getBirthday());
            tv_birth_type.setText(huiJiSearchViperBean.getBirthdayType());
            tv_bodyStatus.setText(huiJiSearchViperBean.getHealthStatus());
            tv_bodybuildingHobby.setText(huiJiSearchViperBean.getFitnessHobby());
            tv_interestHobby.setText(huiJiSearchViperBean.getHobby());
            tv_useCar.setText(huiJiSearchViperBean.getUseCar());

            //回访
            Boolean isProtected = huiJiSearchViperBean.getProtected();
            if (isProtected){
                iv_huifang.setImageResource(R.mipmap.my_password_new);
                tv_huifang.setText("保护7天");
            }else {
                iv_huifang.setImageResource(R.mipmap.wt_huifang);
                tv_huifang.setText("回访");
                String mobile = huiJiSearchViperBean.getMobile();
                lin_huifan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(mobile)){
                            CommonUtil.callPhone(context,mobile);
                        } else {
                            Toast.makeText(context,"未录入手机号,无法进行电话回访",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        }

    }

}
