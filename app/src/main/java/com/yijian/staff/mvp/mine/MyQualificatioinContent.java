package com.yijian.staff.mvp.mine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.DensityUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yangk on 2018/3/14.
 */

public class MyQualificatioinContent extends LinearLayout {

    private Context mContext;

    public MyQualificatioinContent(Context context) {
        super(context);
        this.mContext= context;
    }

    public MyQualificatioinContent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext= context;
    }

    public MyQualificatioinContent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext= context;
    }

    public void setContent(List<String> qualifyList){
        for(int i = 0; i < qualifyList.size(); i++){
            TextView tv = new TextView(mContext);
            tv.setTextSize(14);
            tv.setTextColor(Color.parseColor("#333333"));
            tv.setSingleLine(false);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            tv.setPadding(DensityUtil.dip2px(mContext,16),DensityUtil.dip2px(mContext,16),
                    DensityUtil.dip2px(mContext,16),DensityUtil.dip2px(mContext,16));
            tv.setText(qualifyList.get(i));
            View view_line = new View(mContext);
            view_line.setBackgroundColor(Color.parseColor("#f2f2f2"));
            view_line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,2));
            view_line.setPadding(DensityUtil.dip2px(mContext,16),0,DensityUtil.dip2px(mContext,16),0);
            addView(tv);
            if(i < (qualifyList.size()-1)){
                addView(view_line);
            }
        }
    }


}
