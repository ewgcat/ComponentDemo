package com.yijian.staff.mvp.coach.setclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.setclass.bean.AerobicsBean;
import com.yijian.staff.mvp.coach.setclass.bean.RecordTitleBean;

import java.util.ArrayList;
import java.util.List;

public class AerobicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> aerobicsBeans = new ArrayList<Object>();
    private RecordTitleBean recordTitleBean;
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    public AerobicsAdapter() {
        this.recordTitleBean = new RecordTitleBean("器材", "程式/级数", "时间(s)");
    }

    public void resetActionList(List<AerobicsBean> list) {
        aerobicsBeans.clear();
        aerobicsBeans.add(recordTitleBean);//添加头部
        aerobicsBeans.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case TYPE_TITLE:
                View title = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_title, parent, false);
                viewHolder = new TitleViewHolder(title);
                break;
            default:
            case TYPE_ITEM:
                View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
                viewHolder = new BodyViewHolder(item);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = aerobicsBeans.get(position);
        if (holder instanceof TitleViewHolder && o instanceof AerobicsBean) {
            ((TitleViewHolder) holder).bind((RecordTitleBean) o);
        } else if (holder instanceof BodyViewHolder && o instanceof AerobicsBean) {
            ((BodyViewHolder) holder).bind((AerobicsBean) o);
        }
    }

    @Override
    public int getItemCount() {
        return aerobicsBeans == null ? 0 : aerobicsBeans.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title1;
        private TextView tv_title2;
        private TextView tv_title3;
        private TextView tv_title4;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tv_title1 = itemView.findViewById(R.id.tv_title1);
            tv_title2 = itemView.findViewById(R.id.tv_title2);
            tv_title3 = itemView.findViewById(R.id.tv_title3);
            tv_title4 = itemView.findViewById(R.id.tv_title4);
        }

        public void bind(RecordTitleBean recordTitleBean) {
            tv_title1.setText(recordTitleBean.getTitle1());
            tv_title2.setText(recordTitleBean.getTitle2());
            tv_title3.setText(recordTitleBean.getTitle3());
            tv_title4.setVisibility(View.GONE);
        }

    }

    class BodyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_groupNum;
        private TextView tv_groupTime;

        public BodyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_groupNum = itemView.findViewById(R.id.tv_groupNum);
            tv_groupTime = itemView.findViewById(R.id.tv_groupTime);
        }

        public void bind(AerobicsBean aerobicsBean) {
            tv_name.setText(aerobicsBean.getName());
            tv_groupNum.setText(aerobicsBean.getGrade());
            tv_groupTime.setText(aerobicsBean.getTime());
        }

    }
}