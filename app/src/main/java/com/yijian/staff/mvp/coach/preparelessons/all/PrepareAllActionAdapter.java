package com.yijian.staff.mvp.coach.preparelessons.all;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yijian.staff.R;
import com.yijian.staff.bean.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;

import java.util.List;

/**
 * Created by yangk on 2018/3/27.
 */

public class PrepareAllActionAdapter extends RecyclerView.Adapter<PrepareAllActionAdapter.ViewHolder> {

    private List<PrepareLessonAllBean.PrepareListBean> recyclerViewActionBean; //装载RecyclerView的集合
    private EditActionObservable editActionObservable;
    private PrepareAllLessonActivity prepareAllLessonActivity;

    public PrepareAllActionAdapter(List<PrepareLessonAllBean.PrepareListBean> recyclerViewActionBean, EditActionObservable editActionObservable, PrepareAllLessonActivity prepareAllLessonActivity) {
        this.recyclerViewActionBean = recyclerViewActionBean;
        this.editActionObservable = editActionObservable;
        this.prepareAllLessonActivity = prepareAllLessonActivity;
    }

    @Override
    public PrepareAllActionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prepareall_action, parent, false);
        PrepareAllActionAdapter.ViewHolder holder = new PrepareAllActionAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PrepareAllActionAdapter.ViewHolder holder, int position) {
        try {
            PrepareLessonAllBean.PrepareListBean prepareListBean = recyclerViewActionBean.get(position);
            holder.subActionContentView.initAction(prepareListBean, position, prepareAllLessonActivity);
            editActionObservable.addObserver(holder.subActionContentView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewActionBean == null ? 0 : recyclerViewActionBean.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private SubActionContentView subActionContentView;

        public ViewHolder(View view) {
            super(view);
            subActionContentView = view.findViewById(R.id.subActionContentView);
        }
    }


}
