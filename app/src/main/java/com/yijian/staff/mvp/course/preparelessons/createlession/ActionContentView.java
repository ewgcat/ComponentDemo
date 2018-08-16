package com.yijian.staff.mvp.course.preparelessons.createlession;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.ActionBean;

import java.util.Map;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionContentView extends LinearLayout implements Observer {

    private Context mContext;
    private boolean isShowCheck; //是否显示选择框的标志位
    private ActionBean actionBean; //动作内容 Bean
    private TextView tv_action_title; //标题
    private TextView tv_action_degree; //难易程度
    private TextView tv_action_name; //动作名称
    private TextView tv_action_limit; //动作次数
    private TextView tv_action_qixie; //器械选择
    private CheckBox ck_group; //选中删除复选框
    //    private ImageView iv_rank; //排序
    private RelativeLayout rel_rank; //排序
    private TextView tv_rank; //排序
    private LinearLayout lin_action_content_container; //动作内容容器
    private RelativeLayout rel_action_header; //头部
    private int itemPosition; //位置标题
    private CreatePrivateLessionActivity createPrivateLessionActivity;

    public ActionContentView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ActionContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public ActionContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void initAction(ActionBean actionBean, int itemPosition, CreatePrivateLessionActivity createPrivateLessionActivity) {
        this.actionBean = actionBean;
        this.itemPosition = itemPosition;
        this.createPrivateLessionActivity = createPrivateLessionActivity;
        //添加动作内容
        initView();
        addActionContent();
    }

    private void initView() {

        LinearLayout linActionContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_action_content, null);
        rel_action_header = linActionContainer.findViewById(R.id.rel_action_header);
        tv_action_title = linActionContainer.findViewById(R.id.tv_action_title);
        tv_action_degree = linActionContainer.findViewById(R.id.tv_action_degree);
        tv_action_name = linActionContainer.findViewById(R.id.tv_action_name);
        tv_action_limit = linActionContainer.findViewById(R.id.tv_action_limit);
        tv_action_qixie = linActionContainer.findViewById(R.id.tv_action_qixie);
        rel_rank = linActionContainer.findViewById(R.id.rel_rank);
        tv_rank = linActionContainer.findViewById(R.id.tv_rank);
        ck_group = linActionContainer.findViewById(R.id.ck_group);
        ck_group.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                actionBean.setCheck(isChecked);
                createPrivateLessionActivity.setActionBeanList(itemPosition, actionBean);
            }
        });
        lin_action_content_container = linActionContainer.findViewById(R.id.lin_action_content_container);
        lin_action_content_container.setVisibility(actionBean.isShowHeader() ? View.VISIBLE : View.GONE);
        rel_action_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createPrivateLessionActivity.notifyClickHeader(itemPosition, CreatePrivateLessionActivity.CLICK_HEADER);
            }
        });
        addView(linActionContainer);
    }

    private void addActionContent() {
        tv_action_title.setText(actionBean.getMoName());
        tv_action_degree.setText(actionBean.getMoDifficultyDesc());
        tv_action_name.setText(actionBean.getMoName());
        tv_action_limit.setText(actionBean.getBuildDesc());
        tv_action_qixie.setText(actionBean.getMoApplianceName());
        tv_rank.setText((itemPosition + 1) + "");
    }

    @Override
    public void update(Object data) {

        Toast.makeText(mContext, "观察者做了 " + data + itemPosition, Toast.LENGTH_SHORT).show();
        Map<String, String> map = (Map<String, String>) data;
        int type = Integer.valueOf(map.get("type"));
        switch (type) {
            case 0: //编辑
                isShowCheck = true;
                ck_group.setVisibility(View.VISIBLE);
                rel_rank.setVisibility(View.GONE);
                break;

            case 1: //点击头部显示和隐藏
                int itemLocation = Integer.valueOf(map.get("itemPosition"));
                if (itemLocation == itemPosition) {
                    if (lin_action_content_container.getVisibility() == View.GONE) {
                        lin_action_content_container.setVisibility(View.VISIBLE);
                        actionBean.setShowHeader(true);
                    } else {
                        lin_action_content_container.setVisibility(View.GONE);
                        actionBean.setShowHeader(false);
                    }
                } else {
                    lin_action_content_container.setVisibility(View.GONE);
                    actionBean.setShowHeader(false);
                }
                createPrivateLessionActivity.setActionBeanList(itemPosition, actionBean);
                break;

            case 2: //确定
                isShowCheck = false;
                ck_group.setVisibility(View.GONE);
                rel_rank.setVisibility(View.VISIBLE);

                break;

            case 5: //删除
                isShowCheck = true;
                ck_group.setVisibility(View.VISIBLE);
                rel_rank.setVisibility(View.GONE);
                break;

        }
        addActionContent();

    }
}
