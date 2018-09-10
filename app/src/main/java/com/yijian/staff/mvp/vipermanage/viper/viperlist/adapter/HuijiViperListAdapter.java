package com.yijian.staff.mvp.vipermanage.viper.viperlist.adapter;

import android.arch.lifecycle.Lifecycle;
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

import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.HuiJiViperBean;
import com.yijian.staff.mvp.permission.PermissionUtils;
import com.yijian.staff.mvp.vipermanage.viper.detail.formatoroutdate.HuiJiViperDetailActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class HuijiViperListAdapter extends RecyclerView.Adapter<HuijiViperListAdapter.ViewHolder> {

    private List<HuiJiViperBean> viperBeanList = new ArrayList<>();
    private Context context;
    private Lifecycle lifecycle;

    public HuijiViperListAdapter(Lifecycle lifecycle,Context context, List<HuiJiViperBean> viperBeanList) {
        this.context = context;
        this.lifecycle = lifecycle;
        this.viperBeanList = viperBeanList;

    }

    @Override
    public HuijiViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_all_info, parent, false);
        HuijiViperListAdapter.ViewHolder holder = new HuijiViperListAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(HuijiViperListAdapter.ViewHolder holder, int position) {
        if (viperBeanList != null && viperBeanList.size() > position) {
            HuiJiViperBean viperBean = viperBeanList.get(position);
            holder.bind(context, viperBean);
        }

    }

    @Override
    public int getItemCount() {
        return viperBeanList == null ? 0 : viperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_header;
        private ImageView iv_gender;
        private ImageView iv_rank;
        private TextView tv_name;
        private TextView tv_protect_seven;
        private ImageView iv_visit;
        private RelativeLayout rel_content;


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            iv_rank = view.findViewById(R.id.iv_rank);
            tv_name = view.findViewById(R.id.tv_name);
            tv_protect_seven = view.findViewById(R.id.tv_protect_seven);
            iv_visit = view.findViewById(R.id.iv_visit);
            rel_content = view.findViewById(R.id.rel_content);
        }

        public void bind(Context context, HuiJiViperBean huiJiViperBean) {
            ImageLoader.setHeadImageResource(huiJiViperBean.getHeadImg(), context, iv_header);
            int medalType = huiJiViperBean.getMedalType();
            if (medalType==0){

            }else if (medalType==1){
                ImageLoader.setImageResource(R.mipmap.member_gray, context, iv_rank);
            }else if (medalType==2){
                ImageLoader.setImageResource(R.mipmap.member_gold, context, iv_rank);
            }
            iv_gender.setImageResource(1 == huiJiViperBean.getSex() ? R.mipmap.lg_man : R.mipmap.lg_women);
            tv_name.setText(huiJiViperBean.getName());
            rel_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PermissionUtils.getInstance().setMenuKey("app_formal_member");
                    Intent intent = new Intent(context, HuiJiViperDetailActivity.class);
                    intent.putExtra("memberId", huiJiViperBean.getMemberId());
                    intent.putExtra("subclassName", huiJiViperBean.getSubclassName());
                    intent.putExtra("dictItemKey", huiJiViperBean.getDictItemKey());
                    context.startActivity(intent);
                }
            });

            //回访
            Boolean isProtected = huiJiViperBean.isUnderProtected();
            tv_protect_seven.setVisibility(isProtected ? View.VISIBLE : View.GONE);
            tv_protect_seven.setText("保护"+huiJiViperBean.getProtectedDay()+"天");
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
