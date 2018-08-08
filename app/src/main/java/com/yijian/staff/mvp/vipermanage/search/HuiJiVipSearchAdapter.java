package com.yijian.staff.mvp.vipermanage.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.mvp.permission.PermissionUtils;
import com.yijian.staff.mvp.vipermanage.student.detail.CoachViperDetailActivity;
import com.yijian.staff.mvp.vipermanage.viper.detail.formatoroutdate.HuiJiViperDetailActivity;
import com.yijian.staff.mvp.vipermanage.viper.detail.protentialorintent.ProtentialOrIntentViperDetailActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/29.
 */

public class HuiJiVipSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HuiJiViperBean> dataList = new ArrayList<HuiJiViperBean>();

    public HuiJiVipSearchAdapter(Context context, List<HuiJiViperBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_search, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HuiJiViperBean viperBean = dataList.get(position);
        ((ViewHolder) holder).bind(context, viperBean);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_header;
        private ImageView iv_gender;
        private TextView tv_name;
        private TextView tv_protect_seven;
        private ImageView iv_visit;
        private RelativeLayout rel_content;
        private TextView tv_role;


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_protect_seven = view.findViewById(R.id.tv_protect_seven);
            iv_visit = view.findViewById(R.id.iv_visit);
            rel_content = view.findViewById(R.id.rel_content);
            tv_role = view.findViewById(R.id.tv_role);
        }

        public void bind(Context context, HuiJiViperBean huiJiViperBean) {
            ImageLoader.setHeadImageResource(huiJiViperBean.getHeadImg(), (Activity) context, iv_header);
            iv_gender.setImageResource(1 == huiJiViperBean.getSex() ? R.mipmap.lg_man : R.mipmap.lg_women);
            tv_name.setText(huiJiViperBean.getName());

            String subclassName = huiJiViperBean.getSubclassName();

            if (subclassName.equals("CustomerInfoVO")) { //正式会员
                tv_role.setText("正式会员");
            } else if (subclassName.equals("PotentialVO")) {//潜在会员
                tv_role.setText("潜在会员");
            } else if (subclassName.equals("CustomerIntentionVO")) {//意向会员
                tv_role.setText("意向会员");
            } else if (subclassName.equals("CustomerExpireVO")) {//过期会员
                tv_role.setText("过期会员");
            } else if (subclassName.equals("CoachInfoVO")) {//正式学员
                tv_role.setText("正式学员");
            } else if (subclassName.equals("CoachIntentionVO")) {//意向学员
                tv_role.setText("意向学员");
            } else if (subclassName.equals("CoachExpireVO")) {//过期学员
                tv_role.setText("过期学员");
            } else if (subclassName.equals("CoachPotentialStudentVO")) {//潜在学员
                tv_role.setText("潜在学员");
            }

            rel_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viperDetailBean
                    String subclassName = huiJiViperBean.getSubclassName();

                    if (subclassName.equals("CustomerInfoVO")) { //正式会员
                        PermissionUtils.getInstance().setMenuKey("app_formal_member");
                        Intent intent = new Intent(context, HuiJiViperDetailActivity.class);
                        intent.putExtra("memberId", huiJiViperBean.getMemberId());
                        intent.putExtra("subclassName", subclassName);
                        context.startActivity(intent);
                    } else if (subclassName.equals("PotentialVO")) {//潜在会员
                        PermissionUtils.getInstance().setMenuKey("app_potential_member");
                        Intent intent = new Intent(context, ProtentialOrIntentViperDetailActivity.class);
                        intent.putExtra("memberId", huiJiViperBean.getMemberId());
                        intent.putExtra("subclassName", subclassName);
                        intent.putExtra("dictItemKey", huiJiViperBean.getDictItemKey());
                        context.startActivity(intent);
                    } else if (subclassName.equals("CustomerIntentionVO")) {//意向会员
                        PermissionUtils.getInstance().setMenuKey("app_intention_member");
                        Intent intent = new Intent(context, ProtentialOrIntentViperDetailActivity.class);
                        intent.putExtra("memberId", huiJiViperBean.getMemberId());
                        intent.putExtra("subclassName", subclassName);
                        intent.putExtra("dictItemKey", huiJiViperBean.getDictItemKey());
                        context.startActivity(intent);
                    } else if (subclassName.equals("CustomerExpireVO")) {//过期会员
                        PermissionUtils.getInstance().setMenuKey("app_expire_member");
                        Intent intent = new Intent(context, HuiJiViperDetailActivity.class);
                        intent.putExtra("id", huiJiViperBean.getMemberId());
                        intent.putExtra("subclassName", subclassName);
                        intent.putExtra("dictItemKey", huiJiViperBean.getDictItemKey());
                        context.startActivity(intent);
                    } else {
                        //0 正式学员 （有会籍信息）3、 过期学员;、1、意向学员  2、 潜在学员（无会籍信息）
                        Intent intent = new Intent(context, CoachViperDetailActivity.class);
                        if (subclassName.equals("CoachInfoVO")) {//正式学员
                            PermissionUtils.getInstance().setMenuKey("app_formal_student");
                            intent.putExtra("vipType", 0);
                            intent.putExtra("memberId", huiJiViperBean.getMemberId());
                            context.startActivity(intent);
                        } else if (subclassName.equals("CoachIntentionVO")) {//意向学员
                            PermissionUtils.getInstance().setMenuKey("app_intention_student");
                            intent.putExtra("vipType", 1);
                            intent.putExtra("memberId", huiJiViperBean.getMemberId());
                            context.startActivity(intent);
                        } else if (subclassName.equals("CoachExpireVO")) {//过期学员
                            PermissionUtils.getInstance().setMenuKey("app_expire_student");
                            intent.putExtra("vipType", 3);
                            intent.putExtra("memberId", huiJiViperBean.getMemberId());
                            context.startActivity(intent);
                        } else if (subclassName.equals("CoachPotentialStudentVO")) {//潜在学员
                            PermissionUtils.getInstance().setMenuKey("app_potential_student");
                            intent.putExtra("vipType", 2);
                            intent.putExtra("memberId", huiJiViperBean.getMemberId());
                            context.startActivity(intent);
                        }
                    }

                }
            });


            //回访
            Boolean isProtected = huiJiViperBean.isUnderProtected();
            tv_protect_seven.setVisibility(isProtected ? View.VISIBLE : View.GONE);
            iv_visit.setVisibility(isProtected ? View.GONE : View.VISIBLE);
            iv_visit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mobile = huiJiViperBean.getMobile();
                    if (!TextUtils.isEmpty(mobile)) {
                        CommonUtil.callPhone(context, mobile);
                    } else {
                        Toast.makeText(context, "未录入手机号,无法进行电话回访", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }


}
