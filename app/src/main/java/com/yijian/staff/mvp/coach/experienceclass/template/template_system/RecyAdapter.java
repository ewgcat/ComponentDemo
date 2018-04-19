package com.yijian.staff.mvp.coach.experienceclass.template.template_system;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.AerobicsListBean;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.NoInstrumentListBean;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.PowerListBean;
import com.yijian.staff.mvp.coach.experienceclass.template.template_system.bean.TitleTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/13.
 */

public class RecyAdapter extends RecyclerView.Adapter<TypeViewHolder>{
    private Context context;
    private List<Object> objects=new ArrayList<>();
    private static final String TAG = "RecyAdapter";
    public static final int TYPE_TITLE=0;
//    public static final int TYPE_HEADNOINSTRUMENT=1;
//    public static final int TYPE_HEADAEROBICS=2;
//    public static final int TYPE_HEADPOWER=3;

    public static final int TYPE_ITEM_NOINSTRUMENT=4;
    public static final int TYPE_ITEM_AEROBICS=5;
    public static final int TYPE_ITEM_POWER=6;


    public RecyAdapter(Context context) {
        this.context=context;
    }

    public void resetData(List<Object> objects){
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }


    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TypeViewHolder typeViewHolder;
        switch (viewType){
            default:
            case TYPE_TITLE:
                View view = LayoutInflater.from(context).inflate(R.layout.item_experience_class_template_title, parent, false);
                typeViewHolder=new TypeTitleViewHolder(view);
                break;

            case TYPE_ITEM_NOINSTRUMENT:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_experience_class_template_noinstrument, parent, false);
                typeViewHolder=new TypeItemNoInstrumentViewHolder(view1);
                break;
            case TYPE_ITEM_AEROBICS:
                View view2 = LayoutInflater.from(context).inflate(R.layout.item_experience_class_template_aerobics, parent, false);
                typeViewHolder=new TypeItemAerobicsViewHolder(view2);
                break;

            case TYPE_ITEM_POWER:
                View view3 = LayoutInflater.from(context).inflate(R.layout.item_experience_class_template_power, parent, false);
                typeViewHolder=new TypeItemPowerViewHolder(view3);

                break;

        }

            return typeViewHolder;


    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
//        Log.e(TAG, "onCreateViewHolder: "+position );
        holder.bindView(position,objects);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type=-1;
        Object o = objects.get(position);


        if (o instanceof TitleTemplate){
            type=TYPE_TITLE;
        }else if(o instanceof NoInstrumentListBean){
            type=TYPE_ITEM_NOINSTRUMENT;
        }else if (o instanceof AerobicsListBean){
            type=TYPE_ITEM_AEROBICS;
        }else if (o instanceof PowerListBean){
            type=TYPE_ITEM_POWER;
        }

        return type;
    }
}
