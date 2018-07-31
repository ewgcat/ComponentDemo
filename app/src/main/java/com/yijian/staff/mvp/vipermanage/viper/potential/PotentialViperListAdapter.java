package com.yijian.staff.mvp.vipermanage.viper.potential;

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
import com.yijian.staff.mvp.vipermanage.viper.intent.HuijiIntentViperDetailActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.PermissionUtil;

import java.util.List;

/**
 * Created by yangk on 2018/3/5.
 */

public class PotentialViperListAdapter extends RecyclerView.Adapter<PotentialViperListAdapter.ViewHolder> {

    private List<HuiJiViperBean> viperBeanList;
    private Context context;

    public PotentialViperListAdapter(Context context, List<HuiJiViperBean> viperBeanList) {
        this.context = context;
        this.viperBeanList = viperBeanList;
    }

    @Override
    public PotentialViperListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_potential_people_info, parent, false);
        PotentialViperListAdapter.ViewHolder holder = new PotentialViperListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PotentialViperListAdapter.ViewHolder holder, int position) {
        HuiJiViperBean viperBean = viperBeanList.get(position);
        holder.bind(context, viperBean);
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

        public void bind(Context context, HuiJiViperBean huiJiViperBean) {
            ImageLoader.setHeadImageResource(huiJiViperBean.getHeadImg(), (Activity) context, iv_header);
            iv_gender.setImageResource(1 == huiJiViperBean.getSex() ? R.mipmap.lg_man : R.mipmap.lg_women);
            tv_name.setText(huiJiViperBean.getName());
            rel_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viperDetailBean
//                    Intent intent = new Intent(context, HuijiIntentViperDetailActivity.class);
                    PermissionUtils.getInstance().setMenuKey("app_potential_member");
                    Intent intent = new Intent(context, HuijiIntentViperDetailActivity.class);
                    intent.putExtra("id", huiJiViperBean.getMemberId());
//                    intent.putExtra("memberName",huiJiViperBean.getName());
                    context.startActivity(intent);
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
