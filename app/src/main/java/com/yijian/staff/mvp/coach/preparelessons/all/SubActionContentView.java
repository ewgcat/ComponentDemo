package com.yijian.staff.mvp.coach.preparelessons.all;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.util.DensityUtil;

import java.util.List;

/**
 * Created by yangk on 2018/3/27.
 */

public class SubActionContentView extends LinearLayout {

    private Context mContext;
    private LinearLayout lin_sub_content_cotainer;
    private boolean isShow = true;

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

    public void initSubActionContentView(ActionBean actionBean, int itemPosition){
        LinearLayout linContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_sub_action_content,null);
        LinearLayout lin_header = linContain.findViewById(R.id.lin_header);
        lin_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lin_sub_content_cotainer.setVisibility(isShow?View.VISIBLE:View.GONE);
                isShow = !isShow;
            }
        });
        lin_sub_content_cotainer = linContain.findViewById(R.id.lin_sub_content_cotainer);
        TextView tv_action_degree = linContain.findViewById(R.id.tv_action_degree);
        TextView tv_action_group_num = linContain.findViewById(R.id.tv_action_group_num);
        tv_action_degree.setText(actionBean.getDegree());
        tv_action_group_num.setText(numToChinesse(itemPosition));
        addActionContentView(actionBean.getSubActionBeans());
        this.addView(linContain);
    }

    /************************************START 动作内容 *********************************************/


    public void addActionContentView(List<SubActionBean> subActionBeanList) {

        for (int i = 0; i < subActionBeanList.size(); i++) {

            SubActionBean subActionBean = subActionBeanList.get(i);
            List<SubActionBean.SubChildBean> subChildBeans = subActionBean.getSubChildBeanList();
            LinearLayout subLinContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_actions, null);

            TextView tv_action_rank = subLinContain.findViewById(R.id.tv_action_rank);
            tv_action_rank.setText((i + 1)+"");
            tv_action_rank.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            LinearLayout lin_action_content = subLinContain.findViewById(R.id.lin_action_content);
            for (int j = 0; j < subChildBeans.size(); j++) {
                SubActionBean.SubChildBean subChildBean = subChildBeans.get(j);
                LinearLayout contentLin = new LinearLayout(mContext);
                contentLin.setOrientation(LinearLayout.HORIZONTAL);
                contentLin.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                contentLin.setPadding(0, 0, 0, DensityUtil.dip2px(mContext, 15));

                TextView textView1 = new TextView(mContext);
                LayoutParams et_lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                et_lp.weight = 1;
                textView1.setLayoutParams(et_lp);
                et_lp.setMargins( DensityUtil.dip2px(mContext, 7),0,0,0);
                textView1.setText(subChildBean.getKey());
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView1.setTextColor(Color.parseColor("#666666"));


                TextView textView2 = new TextView(mContext);
                textView2.setGravity(Gravity.LEFT);
                LayoutParams et_lp2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                et_lp2.weight = 1;
                textView2.setLayoutParams(et_lp2);
                textView2.setText(subChildBean.getValue());
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView2.setTextColor(Color.parseColor("#666666"));

                contentLin.addView(textView1);
                contentLin.addView(textView2);
                lin_action_content.addView(contentLin);
            }
            lin_sub_content_cotainer.addView(subLinContain);
        }

    }


    private String numToChinesse(int position) {
        String numStr = "";
        switch (position) {
            case 0:
                numStr = "第一组:";
                break;
            case 1:
                numStr = "第二组:";
                break;
            case 2:
                numStr = "第三组:";
                break;
            case 3:
                numStr = "第四组:";
                break;
            case 4:
                numStr = "第五组:";
                break;
            case 5:
                numStr = "第六组:";
                break;

        }
        return numStr;
    }


}
