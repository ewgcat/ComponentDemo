package com.yijian.staff.mvp.coach.setclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.setclass.bean.AerobicsBean;
import com.yijian.staff.mvp.coach.setclass.bean.PowerBean;
import com.yijian.staff.mvp.coach.setclass.bean.RecordTitleBean;

import java.util.ArrayList;
import java.util.List;

public class PowerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> powerBeans = new ArrayList<Object>();
    private RecordTitleBean recordTitleBean;
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    public PowerAdapter() {
        this.recordTitleBean = new RecordTitleBean("器材", "重量", "次数", "组数");
    }

    public void resetActionList(List<PowerBean> list) {
        powerBeans.clear();
        powerBeans.add(recordTitleBean);//添加头部
        powerBeans.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case TYPE_TITLE:
                View title = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_power, parent, false);
                viewHolder = new TitleViewHolder(title);
                break;
            default:
            case TYPE_ITEM:
                View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_power_title, parent, false);
                viewHolder = new BodyViewHolder(item);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = powerBeans.get(position);
        if (holder instanceof NoInstrumentAdapter.TitleViewHolder && o instanceof AerobicsBean) {
            ((NoInstrumentAdapter.TitleViewHolder) holder).bind((RecordTitleBean) o);
        } else if (holder instanceof BodyViewHolder && o instanceof PowerBean) {
            ((BodyViewHolder) holder).bind((PowerBean) o);
        }
    }

    @Override
    public int getItemCount() {
        return powerBeans == null ? 0 : powerBeans.size();
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
            tv_title4.setText(recordTitleBean.getTitle4());
        }

    }

    class BodyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_weight;
        private TextView tv_groupNum;
        private TextView tv_groupTime;

        public BodyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_groupNum = itemView.findViewById(R.id.tv_groupNum);
            tv_groupTime = itemView.findViewById(R.id.tv_groupTime);
        }

        public void bind(PowerBean powerBean) {
            tv_name.setText(powerBean.getName());
            tv_weight.setText(powerBean.getWeight());
            tv_groupNum.setText(powerBean.getGroupNum());
            tv_groupTime.setText(powerBean.getGroupTime());
        }

    }
}