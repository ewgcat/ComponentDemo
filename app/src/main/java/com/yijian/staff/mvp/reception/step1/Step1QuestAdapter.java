package com.yijian.staff.mvp.reception.step1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestionEntry;
import com.yijian.staff.mvp.reception.step1.bean.QuestionOption;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;
import com.yijian.staff.mvp.reception.step1.recyclerView.ExpandableRecyclerAdapterGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by The_P on 2018/3/12.
 */

public class Step1QuestAdapter extends ExpandableRecyclerAdapterGroup<Step1Bean, QuestOptBean,AbsParentViewHolder, ChildViewHolderGroup> implements QuestionSingleCheckViewHolder.SingleCheckListener, QuestionWriteViewHolder.WriteListener {
    private static final String TAG = "Step1QuestAdapter";
    private static final int SINGLECHECK = 3;
    private static final int MULTICHECK =4;
    private static final int WRITE = 5;
    public static final int CHILDNORMAL=6;
    public static final int CHILDMIX=7;

    private LayoutInflater mInflater;
    private List<Step1Bean> QuestionList;
    private Context mContext;

    private Map<Integer,Integer> singleCheck=new HashMap<>();//单选的结果集合

    private Map<Integer,HashSet<Integer>> multiCheck=new HashMap<>();//多选的结果集合

    private Map<Integer,String> write=new HashMap<>();//填空的结果集合

    public Step1QuestAdapter(@NonNull List<Step1Bean> parentList, Context context) {
        super(parentList);
        QuestionList = parentList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public AbsParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {

        switch (viewType){
            default:
            case SINGLECHECK:
            case MULTICHECK:
                View titleview = mInflater.inflate(R.layout.item_quest_title, parentViewGroup, false);
                return new QuestionViewHolder(titleview);
            case WRITE:
                View titleview1 = mInflater.inflate(R.layout.item_quest_option_write, parentViewGroup, false);
                QuestionWriteViewHolder questionWriteViewHolder = new QuestionWriteViewHolder(titleview1);

                questionWriteViewHolder.setWriteListener(this);
                return questionWriteViewHolder;
        }
    }

    @NonNull
    @Override
    public ChildViewHolderGroup onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        ChildViewHolderGroup viewHolder;
        switch (viewType){
            default:
            case CHILDNORMAL:
                View singleView = mInflater.inflate(R.layout.item_quest_option_single, childViewGroup, false);
                QuestionSingleCheckViewHolder viewHolder0 = new QuestionSingleCheckViewHolder(singleView);
                viewHolder0.setSingleCheckListener(this);
                viewHolder = viewHolder0;
                break;

            case CHILDMIX:
                View mixView = mInflater.inflate(R.layout.item_quest_option_mix, childViewGroup, false);
                QuestionOptMixViewHolder viewHolder1 = new QuestionOptMixViewHolder(mixView);
//                viewHolder1.setMixCheckListener(this);
                viewHolder = viewHolder1;
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindParentViewHolder(@NonNull AbsParentViewHolder parentViewHolder, int parentPosition, @NonNull Step1Bean parent) {
       if (parentViewHolder instanceof QuestionViewHolder){
           ((QuestionViewHolder)parentViewHolder) .bind(parent,parentPosition);
       }else if (parentViewHolder instanceof QuestionWriteViewHolder){
           ((QuestionWriteViewHolder)parentViewHolder)  .bind(parent,parentPosition);
        }

    }

    @Override
    public void onBindChildViewHolder(@NonNull ChildViewHolderGroup childViewHolder, int parentPosition, int childPosition,
                                      @NonNull QuestOptBean child, int flatPosition) {

        if (childViewHolder instanceof QuestionSingleCheckViewHolder){
            ((QuestionSingleCheckViewHolder) childViewHolder).bind(child,parentPosition,childPosition);
        }else if (childViewHolder instanceof QuestionOptMixViewHolder){
            ((QuestionOptMixViewHolder) childViewHolder).bind(child,parentPosition,childPosition);
        }

    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        int type=CHILDNORMAL;
        String childType = QuestionList.get(parentPosition).getChildList().get(childPosition).getType();

        switch (childType){
            case "normal":
                type=CHILDNORMAL;
                break;
            case "mix":
                type=CHILDMIX;
                break;
        }

        return type;
    }

    @Override
    public int getParentViewType(int parentPosition) {
        int type=WRITE;
        String parentType = QuestionList.get(parentPosition).getType();
        switch (parentType){
            case "singleCheck":
                type=SINGLECHECK;
                break;
            case "multiCheck":
                type=MULTICHECK;
                break;
            case "write":
                type=WRITE;
                break;
        }
        return type;
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType==SINGLECHECK||viewType==MULTICHECK||viewType==WRITE;
    }


    @Override
    public void onSingleClick(QuestOptBean child, int parentPosition,int childPosition) {
//        Toast.makeText(mContext,"QuestionOption=="+child.getmName()+" ,parentPosition"+parentPosition,Toast.LENGTH_SHORT).show();
        String type = QuestionList.get(parentPosition).getType();


//        Step1Bean questionEntry = QuestionList.get(parentPosition);
//        for (QuestOptBean ingredient1:questionEntry.getChildList() ) {
//            ingredient1.setSelected(false);
//        }
//        child.setSelected(true);
//        notifyDataSetChanged();

//        singleCheck.put(QuestionList.get(parentPosition).getId(),childPosition);

    }



    public Map<Integer, Integer> getSingleCheck() {
        return singleCheck;
    }

    public Map<Integer, HashSet<Integer>> getMultiCheck() {
        return multiCheck;
    }

    public Map<Integer, String> getWrite() {
        return write;
    }


    @Override
    public void onWrited(int parentPosition, Editable s) {
        Log.e(TAG, "onWrited: parentPosition=="+parentPosition+"s=="+s );
    }
}
