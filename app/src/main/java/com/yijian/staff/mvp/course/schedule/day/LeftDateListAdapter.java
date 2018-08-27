package com.yijian.staff.mvp.course.schedule.day;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.DateBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/24 11:02:44
 */
public class LeftDateListAdapter extends RecyclerView.Adapter<LeftDateListAdapter.ItemViewHolder> {
    private Context context;
    private int height=-1;
    private List<DateBean> dateBeanList = new ArrayList<>();

   

    public LeftDateListAdapter(Context context,int height, List<DateBean> dateBeanList) {
        this.context = context;
        this.height = height;
        this.dateBeanList = dateBeanList;
    }



    @NonNull
    @Override
    public LeftDateListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yue_ke_date, parent, false);
        LeftDateListAdapter.ItemViewHolder itemViewHolder = new LeftDateListAdapter.ItemViewHolder(view, height);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftDateListAdapter.ItemViewHolder holder, int position) {
        holder.bind(dateBeanList, position,i);
    }

    @Override
    public int getItemCount() {
        return dateBeanList == null ? 0 : dateBeanList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll;
        private TextView tv;
        private TextView tvWeekday;

        public ItemViewHolder(View itemView,int height) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            tv = itemView.findViewById(R.id.tv);
            tvWeekday = itemView.findViewById(R.id.tv_weekday);

            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height));
        }

        public void bind(List<DateBean> dateBeanList, int position, int i) {

            DateBean dateBean = dateBeanList.get(position);
            if (position != i) {
                ll.setBackgroundColor(Color.parseColor("#f1f2f6"));
                tv.setTextColor(Color.parseColor("#333333"));
                tvWeekday.setTextColor(Color.parseColor("#666666"));
            } else {
                ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv.setTextColor(Color.parseColor("#1997f8"));
                tvWeekday.setTextColor(Color.parseColor("#1997f8"));
            }
            if (position == 0) {
                tv.setText("今天");
                tvWeekday.setText(dateBean.getWeekDay());
            } else if (position == 1) {
                tv.setText("明天");
                tvWeekday.setText(dateBean.getWeekDay());
            } else {
                tv.setText(dateBean.getDate().substring(8, 10) + "日");
                tvWeekday.setText(dateBean.getWeekDay());
            }
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(position);
                    }
                }
            });

        }
    }

    public int i;

    public void selectDate(int i) {
        this.i = i;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private LeftDateListAdapter.OnItemClickListener mItemClickListener;

    public void setItemClickListener(LeftDateListAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

}

