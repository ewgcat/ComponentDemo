package com.yijian.staff.mvp.coach.preparelessons.all;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.Observer;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.util.DensityUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/27.
 */

public class SubActionContentView extends LinearLayout implements Observer {

    private Context mContext;
    private ActionBean actionBean; //动作内容 Bean
    private TextView tv_action_title; //标题
    private TextView tv_action_degree; //难易程度
    private TextView tv_action_name; //动作名称
    private TextView tv_action_limit; //动作次数
    private TextView tv_action_qixie; //器械选择
    private RelativeLayout rel_rank; //排序
    private TextView tv_rank; //排序
    private LinearLayout lin_action_content_container; //动作内容容器
    private RelativeLayout rel_action_header; //头部
    private int itemPosition; //位置标题
    private PrepareAllLessonActivity prepareAllLessonActivity;

    public SubActionContentView(Context context) {
        super(context);
        this.mContext = context;
    }

    public SubActionContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public SubActionContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void initAction(ActionBean actionBean, int itemPosition, PrepareAllLessonActivity prepareAllLessonActivity) {
        this.actionBean = actionBean;
        this.itemPosition = itemPosition;
        this.prepareAllLessonActivity = prepareAllLessonActivity;
        //添加动作内容
        initView();
        addActionContent();
    }

    private void initView() {

        LinearLayout linActionContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_sub_action_content, null);
        rel_action_header = linActionContainer.findViewById(R.id.rel_action_header);
        tv_action_title = linActionContainer.findViewById(R.id.tv_action_title);
        tv_action_degree = linActionContainer.findViewById(R.id.tv_action_degree);
        tv_action_name = linActionContainer.findViewById(R.id.tv_action_name);
        tv_action_limit = linActionContainer.findViewById(R.id.tv_action_limit);
        tv_action_qixie = linActionContainer.findViewById(R.id.tv_action_qixie);
        rel_rank = linActionContainer.findViewById(R.id.rel_rank);
        tv_rank = linActionContainer.findViewById(R.id.tv_rank);

        lin_action_content_container = linActionContainer.findViewById(R.id.lin_action_content_container);
        lin_action_content_container.setVisibility(actionBean.isShowHeader() ? View.VISIBLE : View.GONE);
        rel_action_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAllLessonActivity.notifyClickHeader(itemPosition, CreatePrivateLessionActivity.CLICK_HEADER);
            }
        });
        addView(linActionContainer);
    }

    private void addActionContent() {
        tv_action_title.setText(actionBean.getMoName());
        tv_action_degree.setText(actionBean.getMoDifficulty());
        tv_action_name.setText(actionBean.getMoName());
        tv_action_limit.setText(actionBean.getBuildTime());
        tv_action_qixie.setText(actionBean.getMoApplianceName());
        tv_rank.setText((itemPosition+1)+"");
    }

    @Override
    public void update(Object data) {

        Toast.makeText(mContext, "观察者做了 " + data + itemPosition, Toast.LENGTH_SHORT).show();
        Map<String, String> map = (Map<String, String>) data;
        int type = Integer.valueOf(map.get("type"));
        switch (type) {

            case 6: //点击头部显示和隐藏
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
                break;



        }
        addActionContent();

    }


}
