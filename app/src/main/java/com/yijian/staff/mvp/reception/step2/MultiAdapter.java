package com.yijian.staff.mvp.reception.step2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MultiOptBean> mMultiOptBeans = new ArrayList<>();
    private static final String TAG = "MultiAdapter";
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_MIX = 1;

    public MultiAdapter(Activity mContext) {
        context = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
//        layoutParams.height=300;
//        view.setLayoutParams(layoutParams);
//        Log.e(TAG, "onCreateViewHolder:layoutParamsclass===== "+layoutParams.getClass() );


        switch (viewType) {
            default:
            case TYPE_NORMAL:
                View view = LayoutInflater.from(context).inflate(R.layout.item_child_type_multi, parent, false);
                MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
            case TYPE_MIX:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_quest_step2_option_mix, parent, false);
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
        } else if (holder instanceof MyMixViewHolder) {
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

            rlOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onChildMultiClick(multiOptBean, getLayoutPosition(), childPosition, parentPosition);
                }
            });
        }
    }


    /**
     * 混合型——单选及填空
     */
    public class MyMixViewHolder extends RecyclerView.ViewHolder {


        private final CheckedTextView optName;
        private final EditText etMix;

        public MyMixViewHolder(View itemView) {
            super(itemView);
            optName = (CheckedTextView) itemView.findViewById(R.id.ctv_mix);
            etMix = itemView.findViewById(R.id.et_mix);


        }

        public void bindView(final MultiOptBean multiOptBean) {
            optName.setText(multiOptBean.getOptName());
            optName.setChecked(multiOptBean.isIsSelected());
            etMix.setText(multiOptBean.getUserValue());
            optName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onChildMultiClick(multiOptBean, getLayoutPosition(), childPosition, parentPosition);
                }
            });

            etMix.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (listener != null)
                        listener.onMixWrited(multiOptBean, getLayoutPosition(), childPosition, parentPosition, s);
                }
            });
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

    public interface ChildMultiListener {
        void onChildMultiClick(MultiOptBean child_demo, int multiItemPosition, int childPosition, int parentPosition);

        void onMixWrited(MultiOptBean multiOptBean, int layoutPosition, int childPosition, int parentPosition, Editable s);
    }

    private ChildMultiListener listener;


    public void setChildMultiListener(ChildMultiListener childMultiListener) {
        listener = childMultiListener;
    }
}
