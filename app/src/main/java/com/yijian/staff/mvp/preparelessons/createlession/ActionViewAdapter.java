package com.yijian.staff.mvp.preparelessons.createlession;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.preparelessons.SubActionBean;

import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionViewAdapter extends RecyclerView.Adapter<ActionViewAdapter.ViewHolder> {

    private List<ActionBean> recyclerViewActionBean; //装载RecyclerView的集合
    private List<String> actionArray; //转载 动作内容简易程度集合

    public void setRecyclerViewActionBean(List<ActionBean> recyclerViewActionBean) {
        this.recyclerViewActionBean = recyclerViewActionBean;
    }

    public ActionViewAdapter(List<ActionBean> recyclerViewActionBean,List<String> actionArray){
        this.recyclerViewActionBean = recyclerViewActionBean;
        this.actionArray = actionArray;
    }

    @Override
    public ActionViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action_view, parent, false);
        ActionViewAdapter.ViewHolder holder = new ActionViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ActionViewAdapter.ViewHolder holder, int position) {
        ActionBean actionBean = recyclerViewActionBean.get(position);
        if(position == 0){ //如果是第一组
            holder.iv_action_switch.setImageResource(actionBean.getSubActionBeans().size()==0?R.mipmap.bk_2:R.mipmap.bk_gouxuan);
        }else{
            holder.iv_action_switch.setImageResource(actionBean.getSubActionBeans().size()==0?R.mipmap.bk_jiadongzuo:R.mipmap.bk_gouxuan);
        }

        holder.tv_action_title.setText(numToChinesse(position)+actionBean.getDegree());
        holder.view_action.addLineView(actionArray);
//        holder.view_action.setVisibility(actionBean.getSubActionBeans().size()==0?View.GONE:View.VISIBLE);
        holder.view_action_content.addActionContentView(actionBean.getSubActionBeans());
        holder.lin_action_opration.setVisibility(actionBean.getSubActionBeans().size()==0?View.GONE:View.VISIBLE);
        holder.btn_opration_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                actionViewAdapter.setRecyclerViewActionBean(recyclerViewActionBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerViewActionBean==null?0:recyclerViewActionBean.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_action_switch; //添加图标
        private TextView tv_action_title; //组标题
        private MyActionView view_action; //组难度程度
        private ActionContentView view_action_content; //组内容
        private LinearLayout lin_action_opration; //添加个动作按钮容器
        private Button btn_opration_group; //添加组动作按钮
        private Button btn_opration_single; //添加个动作按钮

        public ViewHolder(View view) {
            super(view);
            iv_action_switch = view.findViewById(R.id.iv_action_switch);
            tv_action_title = view.findViewById(R.id.tv_action_title);
            view_action = view.findViewById(R.id.view_action);
            view_action_content = view.findViewById(R.id.view_action_content);
            lin_action_opration = view.findViewById(R.id.lin_action_opration);
            btn_opration_group = view.findViewById(R.id.btn_opration_group);
            btn_opration_single = view.findViewById(R.id.btn_opration_single);
        }
    }

    private String numToChinesse(int position){
        String numStr = "";
        switch (position){
            case 0:
                numStr = "动作内容一组";
                break;
            case 1:
                numStr = "动作内容二组";
                break;
            case 2:
                numStr = "动作内容三组";
                break;
        }
        return numStr;
    }

}
