package com.yijian.staff.mvp.preparelessons.createlession;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangk on 2018/3/21.
 */

public class MyActionView extends LinearLayout {

    private Context mContext;
    private List<TextView> viewActionList = new ArrayList<TextView>();
    private TextView resCheckTxtView = null; //当前选中难以程度的ID
    private boolean isCheckResId = false; //当前是否选中难以程度的标志位

    public MyActionView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyActionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void addLineView(List<String> actionArray){
        for(int i = 0;i < actionArray.size(); i++){
            LinearLayout linContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_action, null);
            TextView tv_depart_1 = (TextView) linContain.findViewById(R.id.tv_depart_1);
            tv_depart_1.setText(actionArray.get(i));
            tv_depart_1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionOpration((TextView) v);
                }
            });
            viewActionList.add(tv_depart_1);
            this.addView(linContain);
        }
    }

    public void actionOpration(TextView txtView) {
        /********************* 设置字体颜色 **********************/
        for (int i = 0; i < viewActionList.size(); i++) {
            viewActionList.get(i).setTextColor(Color.parseColor("#666666"));
        }
        if (resCheckTxtView == txtView) {
            if (!isCheckResId) {
                txtView.setTextColor(Color.parseColor("#1997f8"));
            } else {
                txtView.setTextColor(Color.parseColor("#666666"));
            }
        } else {
            txtView.setTextColor(Color.parseColor("#1997f8"));
        }
        resCheckTxtView = txtView;
        isCheckResId = !isCheckResId;
    }

}
