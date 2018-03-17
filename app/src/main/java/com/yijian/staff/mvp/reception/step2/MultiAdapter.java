package com.yijian.staff.mvp.reception.step2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MyViewHolder > {
    private Context context;
    private List<MultiOptBean> mMultiOptBeans=new ArrayList<>();
    private static final String TAG = "MultiAdapter";
    public MultiAdapter(Activity mContext) {
        context=mContext;
    }

    @Override
    public MultiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child_type_multi, parent, false);
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
//        layoutParams.height=300;
//        view.setLayoutParams(layoutParams);
//        Log.e(TAG, "onCreateViewHolder:layoutParamsclass===== "+layoutParams.getClass() );
        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    private int childPosition;
    private int parentPosition;
    public void resetData(List<MultiOptBean> multiOptBeans, int cPosition, int pPosition){
        mMultiOptBeans.clear();
        mMultiOptBeans.addAll(multiOptBeans);
        childPosition=   cPosition;
        parentPosition=pPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MultiAdapter.MyViewHolder holder, int position) {

        holder.bindView(mMultiOptBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mMultiOptBeans.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final CheckedTextView optName;

        public MyViewHolder(View itemView) {
            super(itemView);
            optName = (CheckedTextView) itemView.findViewById(R.id.ctv_option);
        }

        public void bindView(final MultiOptBean multiOptBean) {
            optName.setText(multiOptBean.getOptName());
            optName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null)listener.onChildMultiClick(multiOptBean,getLayoutPosition(),childPosition,parentPosition);
                }
            });
        }
    }

    public interface ChildMultiListener{
        void onChildMultiClick(MultiOptBean child_demo, int multiItemPosition, int childPosition, int parentPosition);
    }

    private ChildMultiListener listener;


    public void setChildMultiListener(ChildMultiListener childMultiListener){
        listener=childMultiListener;
    }
}
