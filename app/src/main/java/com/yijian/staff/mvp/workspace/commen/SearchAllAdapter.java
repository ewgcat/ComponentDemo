package com.yijian.staff.mvp.workspace.commen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.workspace.bean.WorkSpaceVipBean;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class SearchAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<WorkSpaceVipBean> dataList = new ArrayList<>();

    public SearchAllAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void resetDataList(List<WorkSpaceVipBean> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workspace_search, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rel_item;
        TextView tv_role;
        private ImageView iv_header;
        private ImageView iv_gender;
        private TextView tv_name;
        private TextView tv_age;

        public ViewHolder(View itemView) {
            super(itemView);
            rel_item = itemView.findViewById(R.id.rel_item);
            tv_role = itemView.findViewById(R.id.tv_role);
            iv_header = itemView.findViewById(R.id.iv_header);
            iv_gender = itemView.findViewById(R.id.iv_gender);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_age = itemView.findViewById(R.id.tv_age);
        }

        public void bind(WorkSpaceVipBean workSpaceVipBean) {
            rel_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.workSpaceVipBean = workSpaceVipBean;
                    ActivityUtils.name = workSpaceVipBean.getName();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("workSpaceVipBean", workSpaceVipBean);
                    ActivityUtils.startActivity(mContext, SearchOprationActivity.class, bundle);

                }
            });
            ImageLoader.setHeadImageResource(         SharePreferenceUtil.getImageUrl()
                    + workSpaceVipBean.getHeadPath(), mContext, iv_header);
            iv_gender.setImageResource("1".equals(workSpaceVipBean.getGender()) ? R.mipmap.lg_man : R.mipmap.lg_women);
            tv_name.setText(workSpaceVipBean.getName());
            tv_age.setText(String.valueOf(workSpaceVipBean.getAge()));
            tv_role.setText(workSpaceVipBean.getTypeName());
            /*String subclassName = workSpaceVipBean.getSubclassName();
            switch (subclassName) {
                case "CustomerInfoVO":
                    tv_role.setText("正式会员");
                    break;
                case "PotentialVO":
                    tv_role.setText("潜在会员");
                    break;
                case "CustomerIntentionVO":
                    tv_role.setText("意向会员");
                    break;
                case "CustomerExpireVO":
                    tv_role.setText("过期会员");
                    break;
                case "CustomerTodayVisitVO":
                    tv_role.setText("今日来访会员");
                    break;
                case "CoachInfoVO":
                    tv_role.setText("正式学员");
                    break;
                case "CoachIntentionVO":
                    tv_role.setText("意向学员");
                    break;
                case "CoachExpireVO":
                    tv_role.setText("过期学员");
                    break;
                case "CoachPotentialStudentVO":
                    tv_role.setText("潜在学员");
                    break;
                case "CoachTodayVisitVO":
                    tv_role.setText("今日来访学员");
                    break;
                default:
            }*/

        }

    }

}
