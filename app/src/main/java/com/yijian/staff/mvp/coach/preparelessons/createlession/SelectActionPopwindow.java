package com.yijian.staff.mvp.coach.preparelessons.createlession;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.ActionBean;
import com.yijian.staff.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectActionPopwindow extends PopupWindow {

    private CreatePrivateLessionActivity activity;
    private View mMenuView;
    private RecyclerView rv_actioin_select;
    private TextView tv_easy;
    private TextView tv_middle;
    private TextView tv_difficulty;

    private View line_easy;
    private View line_middle;
    private View line_difficulty;

    private TextView tv_submit;

    private List<List<ActionBean>> parentList = new ArrayList<>();
    private List<ActionBean> subActionList = new ArrayList<>();
    private ActionSelectAdapter actionSelectAdapter;
    private int defaultPosition = 0;
    private int cussorIndex = 0;

    public SelectActionPopwindow(CreatePrivateLessionActivity context, List<List<ActionBean>> parentList) {
        super(context);
        this.activity = context;
        this.parentList = parentList;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_select_action, null);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout lin_container = mMenuView.findViewById(R.id.lin_container);
        LinearLayout.LayoutParams lp_lin_container = (LinearLayout.LayoutParams) lin_container.getLayoutParams();
        lp_lin_container.height = DensityUtil.getScreenHeight(context) * 2 / 3;
        lin_container.setLayoutParams(lp_lin_container);

        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (!(v instanceof Button)) {
                    dismiss();
                }
                return true;
            }
        });
        rv_actioin_select = mMenuView.findViewById(R.id.rv_actioin_select);
        rv_actioin_select.setLayoutManager(new LinearLayoutManager(context));
        actionSelectAdapter = new ActionSelectAdapter();
        rv_actioin_select.setAdapter(actionSelectAdapter);
        initTabBar();
        selectTab(cussorIndex);
    }

    public void initTabBar() {
        RelativeLayout rel_easy = mMenuView.findViewById(R.id.rel_easy);
        RelativeLayout rel_middle = mMenuView.findViewById(R.id.rel_middle);
        RelativeLayout rel_difficulty = mMenuView.findViewById(R.id.rel_difficulty);
        tv_easy = mMenuView.findViewById(R.id.tv_easy);
        tv_middle = mMenuView.findViewById(R.id.tv_middle);
        tv_difficulty = mMenuView.findViewById(R.id.tv_difficulty);
        line_easy = mMenuView.findViewById(R.id.line_easy);
        line_middle = mMenuView.findViewById(R.id.line_middle);
        line_difficulty = mMenuView.findViewById(R.id.line_difficulty);
        tv_submit = mMenuView.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Test", "parentP===" + cussorIndex + "  subP===" + defaultPosition);
                if (parentList.get(cussorIndex).size() > 0) {
                    ActionBean actionBean = parentList.get(cussorIndex).get(defaultPosition);
                    activity.addSingleAction(actionBean);
                }
                dismiss();
            }
        });


        rel_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(0);
            }
        });
        rel_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(1);
            }
        });
        rel_difficulty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectTab(2);
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    public void selectTab(int index) {
        switch (index) {
            case 0:
                tv_easy.setTextColor(R.color.blue);
                tv_middle.setTextColor(R.color.textColor999999);
                tv_difficulty.setTextColor(R.color.textColor999999);
                line_easy.setVisibility(View.VISIBLE);
                line_middle.setVisibility(View.GONE);
                line_difficulty.setVisibility(View.GONE);
                break;
            case 1:
                tv_easy.setTextColor(R.color.textColor999999);
                tv_middle.setTextColor(R.color.blue);
                tv_difficulty.setTextColor(R.color.textColor999999);
                line_easy.setVisibility(View.GONE);
                line_middle.setVisibility(View.VISIBLE);
                line_difficulty.setVisibility(View.GONE);
                break;
            case 2:
                tv_easy.setTextColor(R.color.textColor999999);
                tv_middle.setTextColor(R.color.textColor999999);
                tv_difficulty.setTextColor(R.color.blue);
                line_easy.setVisibility(View.GONE);
                line_middle.setVisibility(View.GONE);
                line_difficulty.setVisibility(View.VISIBLE);
                break;
        }
        changeListCheck(index);
        subActionList = parentList.get(index);
        actionSelectAdapter.notifyDataSetChanged();
        cussorIndex = index;

    }

    private void changeListCheck(int index) {
        for (List<ActionBean> subActionBeanList : parentList) {
            for (ActionBean actionBean : subActionBeanList) {
                actionBean.setCheck(false);
            }
        }
        if (parentList.get(index).size() > 0) {
            parentList.get(index).get(0).setCheck(true);
        }
        defaultPosition = 0;
    }

    public void showAtBottom(View v) {
        this.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @SuppressLint("ResourceAsColor")
    public void setSubmitEnnable(boolean isEnnable) {
        tv_submit.setEnabled(isEnnable);
        tv_submit.setTextColor(isEnnable ? R.color.blue : R.color.textColor999999);
    }


    class ActionSelectAdapter extends RecyclerView.Adapter<ActionSelectAdapter.ViewHolder> {


        @Override
        public ActionSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_action, parent, false);
            ActionSelectAdapter.ViewHolder viewHolder = new ActionSelectAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ActionSelectAdapter.ViewHolder holder, int position) {
            ActionBean actionBean = subActionList.get(position);
            holder.action_name.setText(actionBean.getMoName());
            holder.action_limit.setText(actionBean.getBuildDesc());
            holder.action_qixie_name.setText(actionBean.getMoApplianceName());
            holder.lin_select.setBackgroundColor(actionBean.isCheck() ? Color.RED : Color.WHITE);
            holder.lin_select.setTag(position);
            holder.lin_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) v.getTag();
                    if (defaultPosition == tag) {
                        ActionBean tempActionBean = subActionList.get(tag);
                        if (tempActionBean.isCheck()) {
                            tempActionBean.setCheck(false);
                            setSubmitEnnable(false);
                        } else {
                            tempActionBean.setCheck(true);
                            setSubmitEnnable(true);
                        }
                    } else {
                        for (int i = 0; i < subActionList.size(); i++) {
                            ActionBean tempActionBean2 = subActionList.get(i);
                            tempActionBean2.setCheck(false);
                            if (tag == i) {
                                tempActionBean2.setCheck(true);
                                setSubmitEnnable(true);
                            }
                        }
                    }
                    defaultPosition = tag;
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return subActionList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout lin_select;
            private TextView action_name;
            private TextView action_limit;
            private TextView action_qixie_name;

            public ViewHolder(View itemView) {
                super(itemView);
                lin_select = itemView.findViewById(R.id.lin_select);
                action_name = itemView.findViewById(R.id.action_name);
                action_limit = itemView.findViewById(R.id.action_limit);
                action_qixie_name = itemView.findViewById(R.id.action_qixie_name);
            }

        }

    }


}
