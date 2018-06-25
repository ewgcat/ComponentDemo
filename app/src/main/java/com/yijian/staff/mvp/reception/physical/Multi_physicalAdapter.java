package com.yijian.staff.mvp.reception.physical;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public class Multi_physicalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MultiOptBean> mMultiOptBeans = new ArrayList<>();
    private static final String TAG = "MultiAdapter";
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_MIX = 1;

    public Multi_physicalAdapter(Activity mContext) {
        context = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
            case TYPE_NORMAL:
                View view = LayoutInflater.from(context).inflate(R.layout.item_child_type_multi, parent, false);
                MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
            case TYPE_MIX:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_physical_option_mix, parent, false);
                MyMixViewHolder mixViewHolder = new MyMixViewHolder(view1);
                return mixViewHolder;
        }


    }

    private int childPosition;
    private int parentPosition;

    public void resetData(List<MultiOptBean> multiOptBeans, int cPosition, int pPosition) {
        mMultiOptBeans.clear();
        mMultiOptBeans.addAll(multiOptBeans);
        childPosition = cPosition;
        parentPosition = pPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).bindView(mMultiOptBeans.get(position));
        } else {
            ((MyMixViewHolder) holder).bindView(mMultiOptBeans.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mMultiOptBeans.size();
    }


    /**
     * 默认单选
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final CheckedTextView optName;
        private final RelativeLayout rlOption;

        public MyViewHolder(View itemView) {
            super(itemView);
            optName = (CheckedTextView) itemView.findViewById(R.id.ctv_option);
            rlOption = itemView.findViewById(R.id.rl_option);
        }

        public void bindView(final MultiOptBean multiOptBean) {
            optName.setText(multiOptBean.getOptName());
            optName.setChecked(multiOptBean.isIsSelected());

        }
    }


    /**
     * 混合型——单选及填空
     */
    public class MyMixViewHolder extends RecyclerView.ViewHolder {


        private final CheckedTextView optName;
        private final TextView tvMix;

        public MyMixViewHolder(View itemView) {
            super(itemView);
            optName = (CheckedTextView) itemView.findViewById(R.id.ctv_mix);
            tvMix = itemView.findViewById(R.id.tv_mix);


        }

        public void bindView(final MultiOptBean multiOptBean) {
            optName.setText(multiOptBean.getOptName());
            optName.setChecked(multiOptBean.isIsSelected());
            tvMix.setText(multiOptBean.getUserValue());

        }
    }


    @Override
    public int getItemViewType(int position) {
        String typename = mMultiOptBeans.get(position).getType();
        if ("mix".equals(typename)) {//有填空及单选
            return TYPE_MIX;
        } else {//单选
            return TYPE_NORMAL;
        }
    }

}
