package com.yijian.staff.mvp.reception.step1;

import android.support.annotation.NonNull;
import android.view.View;

import com.yijian.staff.mvp.reception.step1.bean.DataListBean;
import com.yijian.staff.mvp.reception.step1.bean.ItemsBean;
import com.yijian.staff.mvp.reception.step1.bean.QuestOptBean;
import com.yijian.staff.mvp.reception.step1.bean.Step1Bean;
import com.yijian.staff.mvp.reception.step1.recyclerView.ParentViewHolderGroup;

/**
 * Created by The_P on 2018/3/17.
 */

public class AbsParentViewHolder extends ParentViewHolderGroup<DataListBean, ItemsBean> {


    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public AbsParentViewHolder(@NonNull View itemView) {
        super(itemView);
    }

//    public  void bind(Step1Bean parent, int parentPosition);
}
