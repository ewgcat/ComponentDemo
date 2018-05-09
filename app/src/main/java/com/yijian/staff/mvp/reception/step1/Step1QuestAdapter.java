package com.yijian.staff.mvp.reception.step1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.ItemsBean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ChildViewHolderGroup;
import com.yijian.staff.mvp.reception.step1.recyclerView.ExpandableRecyclerAdapterGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by The_P on 2018/3/12.
 */

public class Step1QuestAdapter extends ExpandableRecyclerAdapterGroup<DataListBean, ItemsBean,AbsParentViewHolder, ChildViewHolderGroup> implements QuestionSingleCheckViewHolder.SingleCheckListener, QuestionWriteViewHolder.WriteListener, QuestionOptMixViewHolder.MixListener {
    private static final String TAG = "Step1QuestAdapter";
    private static final int SINGLECHECK = 13;
    private static final int MULTICHECK =14;
    private static final int WRITE = 15;
    public static final int CHILDNORMAL=16;
    public static final int CHILDMIX=17;
    public static final int CHILDINPUT=18;

    private LayoutInflater mInflater;
    private List<DataListBean> QuestionList;
    private Context mContext;


    public Step1QuestAdapter(@NonNull List<DataListBean> parentList, Context context) {
        super(parentList);
        QuestionList = parentList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void   resetData(List<DataListBean> list){
        QuestionList.clear();
        QuestionList.addAll(list);
        setParentList(list);
    }

    public List<DataListBean> getQuestionList() {
        return QuestionList;
    }

    @NonNull
    @Override
    public AbsParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {

        switch (viewType){
            default:
            case SINGLECHECK:
            case MULTICHECK:
            case WRITE:
                View titleview = mInflater.inflate(R.layout.item_quest_title, parentViewGroup, false);
                return new QuestionViewHolder(titleview);
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
                viewHolder1.setMixWriteListener(this);
                viewHolder = viewHolder1;
                break;


            case CHILDINPUT:
                View titleview1 = mInflater.inflate(R.layout.item_quest_option_write, childViewGroup, false);
                QuestionWriteViewHolder questionWriteViewHolder = new QuestionWriteViewHolder(titleview1);
                questionWriteViewHolder.setWriteListener(this);
                viewHolder= questionWriteViewHolder;
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindParentViewHolder(@NonNull AbsParentViewHolder parentViewHolder, int parentPosition, @NonNull DataListBean parent) {
       if (parentViewHolder instanceof QuestionViewHolder){
           ((QuestionViewHolder)parentViewHolder) .bind(parent,parentPosition);
       }

    }

    @Override
    public void onBindChildViewHolder(@NonNull ChildViewHolderGroup childViewHolder, int parentPosition, int childPosition,
                                      @NonNull ItemsBean child, int flatPosition) {

        if (childViewHolder instanceof QuestionSingleCheckViewHolder){
            ((QuestionSingleCheckViewHolder) childViewHolder).bind(child,parentPosition,childPosition);
        }else if (childViewHolder instanceof QuestionOptMixViewHolder){
            ((QuestionOptMixViewHolder) childViewHolder).bind(child,parentPosition,childPosition);
        }else if (childViewHolder instanceof QuestionWriteViewHolder){
            ((QuestionWriteViewHolder)childViewHolder).bind(child,parentPosition,childPosition);
        }

    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        int type=CHILDNORMAL;
//        String childType = QuestionList.get(parentPosition).getChildList().get(childPosition).getType();
        int type1 = QuestionList.get(parentPosition).getChildList().get(childPosition).getType();
        switch (type1){//item_type:0选择,1输入,2选择加输入
            case 0:
                type=CHILDNORMAL;
                break;

            case 1:
                type=CHILDINPUT;
                break;
            case 2:
                type=CHILDMIX;
                break;
        }

        return type;
    }

    @Override
    public int getParentViewType(int parentPosition) {
        int type=WRITE;
        int selectType = QuestionList.get(parentPosition).getSelectType();
        switch (selectType){//select_type: 选择类型：0单选,1多选,2填空
            case 0:
                type=SINGLECHECK;
                break;
            case 1:
                type=MULTICHECK;
                break;
            case 2:
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
    public void onSingleClick(ItemsBean child, int parentPosition,int childPosition) {
        int selectType = QuestionList.get(parentPosition).getSelectType();
        switch (selectType){//select_type: 选择类型：0单选,1多选,2填空
            case 0://单选

                List<ItemsBean> childList = QuestionList.get(parentPosition).getChildList();
                for (ItemsBean optBean: childList  ) {
                    optBean.setSelect(false);
                }
                child.setSelect(true);
//                singleCheck.put(QuestionList.get(parentPosition).getDesc(),childList.indexOf(child));

                notifyDataSetChanged();
                break;
            case 1://多选

                child.setSelect(child.isSelect()?false:true);

                notifyDataSetChanged();
                break;

        }

        if (computerPercentLisenter!=null) computerPercentLisenter.computerPercent();

    }




    @Override
    public void onWrited(ItemsBean child, int parentPosition, int childPosition, CharSequence s) {
        QuestionList.get(parentPosition).getChildList().get(childPosition).setInputContent(s.toString());
//        child.setInputContent(s.toString());
        Log.e(TAG, "onWrited: "+child.toString() );
        if (s.length()>0){
            if (!child.isSelect()){
                if (computerPercentLisenter!=null) computerPercentLisenter.computerPercent();
            }
            child.setSelect(true);
        }else {
            child.setSelect(false);
            if (computerPercentLisenter!=null) computerPercentLisenter.computerPercent();
        }
    }

    @Override
    public void onMixWrited(ItemsBean child,int parentPosition, int childPosition, Editable s) {
//        Log.e(TAG, "onMixWrited: parentPosition=="+parentPosition+"s=="+s );
        child.setInputContent(s.toString());


    }

    @Override
    public void onMixClick(ItemsBean child, int parentPosition, int childPosition) {
        onSingleClick(child,parentPosition,childPosition);
        if (computerPercentLisenter!=null) computerPercentLisenter.computerPercent();

    }

  public   interface ComputerPercentLisenter{
        void computerPercent();
    }

    public ComputerPercentLisenter computerPercentLisenter;

    public void setComputerPercentLisenter(ComputerPercentLisenter computerPercentLisenter) {
        this.computerPercentLisenter = computerPercentLisenter;
    }
}
