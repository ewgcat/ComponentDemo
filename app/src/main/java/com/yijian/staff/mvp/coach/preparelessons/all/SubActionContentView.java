package com.yijian.staff.mvp.coach.preparelessons.all;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.Observer;

import java.util.Map;

/**
 * Created by yangk on 2018/3/27.
 */

public class SubActionContentView extends LinearLayout implements Observer {

    private Context mContext;
    private PrepareLessonAllBean.PrepareListBean prepareListBean; //动作内容 Bean
    private TextView tv_action_name; //动作名称
    private TextView tv_action_buildDesc; //动作描述
    private TextView tv_action_degree; //难易程度
    private TextView tv_action_moPartsDesc; //训练部位
    private TextView tv_action_qixie; //器械选择
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

    public void initAction(PrepareLessonAllBean.PrepareListBean prepareListBean, int itemPosition, PrepareAllLessonActivity prepareAllLessonActivity) {
        this.prepareListBean = prepareListBean;
        this.itemPosition = itemPosition;
        this.prepareAllLessonActivity = prepareAllLessonActivity;
        //添加动作内容
        initView();
        addActionContent();
    }

    private void initView() {

        LinearLayout linActionContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_sub_action_content, null);
        rel_action_header = linActionContainer.findViewById(R.id.rel_action_header);
        tv_action_buildDesc = linActionContainer.findViewById(R.id.tv_action_buildDesc);
        tv_action_degree = linActionContainer.findViewById(R.id.tv_action_degree);
        tv_action_name = linActionContainer.findViewById(R.id.tv_action_name);
        tv_action_degree = linActionContainer.findViewById(R.id.tv_action_degree);
        tv_action_qixie = linActionContainer.findViewById(R.id.tv_action_qixie);
        tv_rank = linActionContainer.findViewById(R.id.tv_rank);

        lin_action_content_container = linActionContainer.findViewById(R.id.lin_action_content_container);
        lin_action_content_container.setVisibility(prepareListBean.isShowHeader() ? View.VISIBLE : View.GONE);
        rel_action_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAllLessonActivity.notifyClickHeader(itemPosition, CreatePrivateLessionActivity.CLICK_HEADER);
            }
        });
        addView(linActionContainer);
    }

    private void addActionContent() {
        tv_action_name.setText(prepareListBean.getMoName());
        tv_action_buildDesc.setText(prepareListBean.getBuildDesc());
        tv_action_degree.setText(prepareListBean.getMoDifficultyDesc());
        tv_action_moPartsDesc.setText(prepareListBean.getMoPartsDesc());
        tv_action_qixie.setText(prepareListBean.getMoApplianceName());
        tv_rank.setText((itemPosition + 1) + "");
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
                        prepareListBean.setShowHeader(true);
                    } else {
                        lin_action_content_container.setVisibility(View.GONE);
                        prepareListBean.setShowHeader(false);
                    }
                } else {
                    lin_action_content_container.setVisibility(View.GONE);
                    prepareListBean.setShowHeader(false);
                }
                break;


        }
        addActionContent();

    }


}
