package com.yijian.staff.mvp.reception.physical;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step2.expandablerecyclerview.ChildViewHolder;
import com.yijian.staff.mvp.reception.step2.step2Bean.ChildOptBean;
import com.yijian.staff.mvp.reception.step2.step2Bean.MultiOptBean;

import java.util.List;

/**
 * Created by The_P on 2018/3/15.
 */

public class Child_physical_dispalyMulti extends ChildViewHolder  {

    private final TextView name;
    private final RecyclerView recyclerview;
    private static final String TAG = "Child_DemoViewHolder_di";
    private final Multi_physicalAdapter multiAdapter;


    /**
     * Default constructor.
     *  @param itemView The {@link View} being hosted in this ViewHolder
     * @param
     */
    public Child_physical_dispalyMulti(@NonNull View itemView, Activity mContext) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        recyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview);

        multiAdapter = new Multi_physicalAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(multiAdapter);
    }

    public void bind(ChildOptBean child, int childPosition, int parentPosition, Activity mContext) {
        name.setText(child.getQustion());

        List<MultiOptBean> multiOptBeans = child.getMultiOptBeans();
        if (multiOptBeans==null||multiOptBeans.size()==0){
            recyclerview.setVisibility(View.GONE);
        }else {

            multiAdapter.resetData(multiOptBeans,childPosition,parentPosition);
            recyclerview.setVisibility(View.VISIBLE);

        }




    }





}
