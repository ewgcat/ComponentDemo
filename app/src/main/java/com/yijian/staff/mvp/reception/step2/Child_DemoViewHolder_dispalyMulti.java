package com.yijian.staff.mvp.reception.step2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildObjBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;

import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

class Child_DemoViewHolder_dispalyMulti extends ChildViewHolder implements MultiAdapter.ChildMultiListener {

    private final TextView name;
    private final RecyclerView recyclerview;
    private static final String TAG = "Child_DemoViewHolder_di";
    private final MultiAdapter multiAdapter;

    /**
     * Default constructor.
     *  @param itemView The {@link View} being hosted in this ViewHolder
     * @param
     */
    public Child_DemoViewHolder_dispalyMulti(@NonNull View itemView, Activity mContext) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        recyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview);

        multiAdapter = new MultiAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(multiAdapter);
        multiAdapter.setChildMultiListener(this);
    }

    public void bind(ChildObjBean child, int childPosition, int parentPosition, Activity mContext) {
        name.setText(child.getQustion());

        List<MultiOptBean> multiOptBeans = child.getMultiOptBeans();
        Log.e(TAG, "bind:multiOptBeans ");
        if (multiOptBeans==null||multiOptBeans.size()==0){
            Log.e(TAG, "bind:multiOptBeans 111");
            recyclerview.setVisibility(View.GONE);
        }else {

            Log.e(TAG, "bind:multiOptBeans ");
            multiAdapter.resetData(multiOptBeans,childPosition,parentPosition);
            recyclerview.setVisibility(View.VISIBLE);

        }



        // 关键代码
//        //////////////////////////////////////////////////
//        ProductAdapter productAdapter = new ProductAdapter(mContext, entity.getProducts());
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        holder.productList.setLayoutManager(linearLayoutManager);
//        holder.productList.setAdapter(productAdapter);
//        holder.productList.setVisibility(View.VISIBLE);
//        /////////////////////////////////////////////////////

    }


    @Override
    public void onChildMultiClick(MultiOptBean child_demo, int multiItemPosition, int childPosition, int parentPosition) {
        if (listener!=null)listener.onItemMultiClick(child_demo,multiItemPosition,childPosition,parentPosition);
    }

    public interface ItemMultiListener{
        void onItemMultiClick(MultiOptBean child_demo, int multiItemPosition, int childPosition, int parentPosition);
    }

    private ItemMultiListener listener;


    public void setChildMultiListener(ItemMultiListener itemMultiListener){
        listener=itemMultiListener;
    }


}
