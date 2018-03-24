package com.yijian.staff.mvp.preparelessons.createlession;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.preparelessons.SubActionBean;

import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionContentView extends LinearLayout {


    private Context mContext;

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

    public void addActionContentView(List<List<SubActionBean>> actionBeanList){
        for(int i = 0; i < actionBeanList.size(); i++){

            List<SubActionBean> actionBeans = actionBeanList.get(i);
            LinearLayout linContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_actions, null);
            TextView tv_action_rank = linContain.findViewById(R.id.tv_action_rank);
            tv_action_rank.setText("第"+(i+1)+"个");
            LinearLayout lin_action_content = linContain.findViewById(R.id.lin_action_content);
            for(int j = 0; j < actionBeans.size(); j++){
                SubActionBean actionBean = actionBeans.get(j);
                LinearLayout contentLin = new LinearLayout(mContext);
                contentLin.setOrientation(LinearLayout.HORIZONTAL);
                contentLin.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

                EditText editText = new EditText(mContext);
                editText.setBackgroundColor(Color.WHITE);
                LayoutParams et_lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                et_lp.weight = 1;
                editText.setLayoutParams(et_lp);
                editText.setText(actionBean.getKey());

                EditText editText2 = new EditText(mContext);
                editText2.setBackgroundColor(Color.WHITE);
                LayoutParams et_lp2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                et_lp2.weight = 1;
                editText2.setLayoutParams(et_lp2);
                editText2.setText(actionBean.getValue());

                contentLin.addView(editText);
                contentLin.addView(editText2);
                lin_action_content.addView(contentLin);
            }
            this.addView(linContain);
        }
    }

}
