package com.yijian.staff.mvp.course.preparelessons.createlession;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.bean.ActionBean;

import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionViewAdapter extends RecyclerView.Adapter<ActionViewAdapter.ViewHolder> {

    private List<ActionBean> recyclerViewActionBean; //装载RecyclerView的集合
    private EditActionObservable editActionObservable;
    private CreatePrivateLessionActivity createPrivateLessionActivity;


    public ActionViewAdapter(List<ActionBean> recyclerViewActionBean, EditActionObservable editActionObservable, CreatePrivateLessionActivity createPrivateLessionActivity) {
        this.recyclerViewActionBean = recyclerViewActionBean;
        this.editActionObservable = editActionObservable;
        this.createPrivateLessionActivity = createPrivateLessionActivity;
    }

    @Override
    public ActionViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action_view, parent, false);
        ActionViewAdapter.ViewHolder holder = new ActionViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ActionViewAdapter.ViewHolder holder, int position) {
        try {
            ActionBean actionBean = recyclerViewActionBean.get(position);
            holder.view_action_content.initAction(actionBean, position, createPrivateLessionActivity);
            editActionObservable.addObserver(holder.view_action_content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewActionBean == null ? 0 : recyclerViewActionBean.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ActionContentView view_action_content; //组内容

        public ViewHolder(View view) {
            super(view);
            view_action_content = view.findViewById(R.id.view_action_content);
        }
    }

}
