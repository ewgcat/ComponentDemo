package com.yijian.clubmodule.ui.price.cardprice.filter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.clubmodule.bean.VenueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.Holder> {
    private Context context;
    private List<VenueBean> list = new ArrayList<>();

    public OptionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reception_step3_venue, parent, false);
        return new Holder(view);
    }

    public void resetData(List<VenueBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        public Holder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        public void bindView(int position) {
            VenueBean venueBean = list.get(position);
            tvName.setText(venueBean.getOption());
            if (venueBean.isSelect) {
                setSelectStyle(tvName);
            } else {
                setUnSelectStyle(tvName);
            }

            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetSelectState();
                    venueBean.setSelect(true);
                    if (lisenter != null) lisenter.onVenueClick(position, venueBean);

                    notifyDataSetChanged();
                }
            });
        }

        private void resetSelectState() {
            for (int i = 0; i < list.size(); i++) {
                VenueBean venueBean1 = list.get(i);
                venueBean1.setSelect(false);

            }
        }
    }


    private void setSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#1997f8"));
        textView.setBackground(context.getDrawable(R.drawable.blue_stroke_select_bg));
        Drawable jd_choose = context.getResources().getDrawable(R.mipmap.jd_choose);
        jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
        textView.setCompoundDrawables(jd_choose, null, null, null);

    }

    private void setUnSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        textView.setBackground(context.getDrawable(R.drawable.gray_stroke_unselect_bg));
        textView.setCompoundDrawables(null, null, null, null);
    }

    public interface OptionLisenter {
        void onVenueClick(int position, VenueBean venueBean);
    }

    public List<VenueBean> getList() {
        return list;
    }

    private OptionLisenter lisenter;

    public OptionLisenter getLisenter() {
        return lisenter;
    }

    public void setLisenter(OptionLisenter lisenter) {
        this.lisenter = lisenter;
    }
}
