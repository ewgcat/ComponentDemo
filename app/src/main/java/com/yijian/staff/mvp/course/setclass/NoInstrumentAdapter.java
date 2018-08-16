package com.yijian.staff.mvp.course.setclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.bean.NoInstrumentBean;
import com.yijian.staff.bean.RecordTitleBean;

import java.util.ArrayList;
import java.util.List;

public class NoInstrumentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> noInstrumentBeans = new ArrayList<Object>();
    private RecordTitleBean recordTitleBean;
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    public NoInstrumentAdapter() {
        this.recordTitleBean = new RecordTitleBean("测试", "组数", "次数");
    }

    public void resetActionList(List<NoInstrumentBean> list) {
        noInstrumentBeans.clear();
        noInstrumentBeans.add(recordTitleBean);//添加头部
        noInstrumentBeans.addAll(list);
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
        Object o = noInstrumentBeans.get(position);
        if (holder instanceof TitleViewHolder && o instanceof RecordTitleBean) {
            ((TitleViewHolder) holder).bind((RecordTitleBean) o);
        } else if (holder instanceof BodyViewHolder && o instanceof NoInstrumentBean) {
            ((BodyViewHolder) holder).bind((NoInstrumentBean) o);
        }
    }

    @Override
    public int getItemCount() {
        return noInstrumentBeans == null ? 0 : noInstrumentBeans.size();
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

        public void bind(NoInstrumentBean noInstrumentBean) {
            tv_name.setText(noInstrumentBean.getName());
            tv_groupNum.setText(noInstrumentBean.getGroupNum());
            tv_groupTime.setText(noInstrumentBean.getGroupTime());
        }

    }

}
