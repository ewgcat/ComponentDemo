package com.yijian.staff.mvp.huiji.viperlist.adapter;

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
import com.yijian.staff.mvp.huiji.detail.HuiJiViperDetailActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.List;

/**
 * Created by yangk on 2018/3/6.
 */

public class HuijiViperListAdapter extends RecyclerView.Adapter<HuijiViperListAdapter.ViewHolder> {

    private List<HuiJiViperBean> viperBeanList;
    private Context context;

    public HuijiViperListAdapter(Context context, List<HuiJiViperBean> viperBeanList) {
        this.context = context;
        this.viperBeanList = viperBeanList;
    }

    @Override
    public HuijiViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_info, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huiji_vip_all_info, parent, false);
        HuijiViperListAdapter.ViewHolder holder = new HuijiViperListAdapter.ViewHolder(view);
        return holder;
    }

    public void update(List<HuiJiViperBean> viperBeanList) {
        this.viperBeanList = viperBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(HuijiViperListAdapter.ViewHolder holder, int position) {
        HuiJiViperBean viperBean = viperBeanList.get(position);
        holder.bind(context,viperBean);
    }

    @Override
    public int getItemCount() {
        return viperBeanList == null ? 0 : viperBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_header;
        private ImageView iv_gender;
        private TextView tv_name;
        private TextView tv_protect_seven;
        private ImageView iv_visit;
        private RelativeLayout rel_content;


        public ViewHolder(View view) {
            super(view);
            iv_header = view.findViewById(R.id.iv_header);
            iv_gender = view.findViewById(R.id.iv_gender);
            tv_name = view.findViewById(R.id.tv_name);
            tv_protect_seven = view.findViewById(R.id.tv_protect_seven);
            iv_visit = view.findViewById(R.id.iv_visit);
            rel_content = view.findViewById(R.id.rel_content);
        }

        public void bind(Context context, HuiJiViperBean huiJiViperBean){
            ImageLoader.setImageResource(huiJiViperBean.getHeadImg(), context, iv_header);
            iv_gender.setImageResource("1".equals(huiJiViperBean.getSex()) ? R.mipmap.lg_man : R.mipmap.lg_women);
            tv_name.setText(huiJiViperBean.getName());
            rel_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viperDetailBean
                    Intent intent = new Intent(context, HuiJiViperDetailActivity.class);
                    intent.putExtra("memberId",huiJiViperBean.getMemberId());
                    context.startActivity(intent);
                }
            });

            //回访
            Boolean isProtected = huiJiViperBean.isUnderProtected();
            tv_protect_seven.setVisibility(isProtected?View.VISIBLE:View.GONE);
            iv_visit.setVisibility(isProtected?View.GONE:View.VISIBLE);
            iv_visit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mobile = huiJiViperBean.getMobile();
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
