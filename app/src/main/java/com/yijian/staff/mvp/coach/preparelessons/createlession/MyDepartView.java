package com.yijian.staff.mvp.coach.preparelessons.createlession;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yangk on 2018/3/19.
 */

public class MyDepartView extends LinearLayout {

    private Context mContext;
    private List<TextView> viewDepartList;  //所有部位集合
    private TextView resCheckTxtView = null; //当前选中难以程度的View
    private boolean isCheckResId = false; //当前是否选中难以程度的标志位
    private LinearLayout linHeaderDepartContain; //头部容器布局
    private LinearLayout linContentContain; //部位容器布局
    private ImageView iv_depart_switch; //头部小图标
    private TextView tv_depart_title; //头部标题
    private TextView tv_depart_title_check; //选中训练部位


    public MyDepartView(Context context) {
        super(context);
        this.mContext = context;
        viewDepartList = new ArrayList<TextView>();
    }

    public MyDepartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        viewDepartList = new ArrayList<TextView>();
    }

    public MyDepartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        viewDepartList = new ArrayList<TextView>();
    }

    public void addLineView(String selectionPart, List<String> departArray, OnDepartOprationListener onDepartOprationListener) {
        //添加头部
        linHeaderDepartContain = new LinearLayout(mContext);
        linHeaderDepartContain.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        linHeaderDepartContain.setOrientation(LinearLayout.HORIZONTAL);
        linHeaderDepartContain.setGravity(Gravity.CENTER_VERTICAL);
        linHeaderDepartContain.setPadding(DensityUtil.dip2px(mContext,15),DensityUtil.dip2px(mContext,15),
                DensityUtil.dip2px(mContext,15),DensityUtil.dip2px(mContext,15));
        iv_depart_switch = new ImageView(mContext);
        iv_depart_switch.setLayoutParams(new LayoutParams(DensityUtil.dip2px(mContext,18),DensityUtil.dip2px(mContext,18)));
        tv_depart_title = new TextView(mContext);
        tv_depart_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        LayoutParams titleLP = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        titleLP.setMargins(DensityUtil.dip2px(mContext,7),0,0,0);
        tv_depart_title.setLayoutParams(titleLP);
        tv_depart_title.setText("训练的部位");

        tv_depart_title_check = new TextView(mContext);
        tv_depart_title_check.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        LayoutParams titleCheckLP = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        tv_depart_title_check.setGravity(Gravity.RIGHT);
        tv_depart_title_check.setLayoutParams(titleCheckLP);
        tv_depart_title_check.setTextColor(Color.parseColor("#1997f8"));

        linHeaderDepartContain.addView(iv_depart_switch);
        linHeaderDepartContain.addView(tv_depart_title);
        linHeaderDepartContain.addView(tv_depart_title_check);
        addView(linHeaderDepartContain);
        linHeaderDepartContain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linContentContain.setVisibility((linContentContain.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            }
        });



        //添加部位内容 和 按钮布局
        linContentContain = new LinearLayout(mContext);
        linContentContain.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        linContentContain.setGravity(Gravity.CENTER_VERTICAL);
        linContentContain.setOrientation(LinearLayout.VERTICAL);
        linContentContain.setPadding(0,0,0,DensityUtil.dip2px(mContext,15));

        //添加分割线
        View linView = new View(mContext);
        linView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,2));
        linView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        linContentContain.addView(linView);

        for (int i = 0; i < departArray.size(); i++) {
            LinearLayout linContain  = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_depart, null);
            View view_line = linContain.findViewById(R.id.view_line);
            TextView tv_depart_1 = (TextView) linContain.findViewById(R.id.tv_depart_1);
            tv_depart_1.setText(departArray.get(i));
            TextView tv_depart_2 = (TextView) linContain.findViewById(R.id.tv_depart_2);
            tv_depart_2.setText(departArray.get(i + 1));
            tv_depart_1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    onDepartOprationListener.departOpration((TextView) v);
                    departOpration((TextView) v);
                }
            });
            tv_depart_2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    departOpration((TextView) v);
                }
            });
            viewDepartList.add(tv_depart_1);
            viewDepartList.add(tv_depart_2);
            i = i + 1;
            view_line.setVisibility((i == (departArray.size()-1))?View.GONE:View.VISIBLE);
            linContentContain.addView(linContain);
        }


        addView(linContentContain);

        setTitle();
        for(int i = 0; i < viewDepartList.size(); i++){
            if((viewDepartList.get(i).getText()).equals(selectionPart)){
                resCheckTxtView = viewDepartList.get(i);
                departOpration(viewDepartList.get(i));
                break;
            }
        }

    }

    public void departOpration(TextView txtView) {
        /********************* 设置字体颜色 **********************/
        for (int i = 0; i < viewDepartList.size(); i++) {
            viewDepartList.get(i).setTextColor(Color.parseColor("#666666"));
        }
        if (resCheckTxtView == txtView) {
            if (!isCheckResId) {
                txtView.setTextColor(Color.parseColor("#1997f8"));
                isCheckResId = true;
            } else {
                txtView.setTextColor(Color.parseColor("#666666"));
                isCheckResId = false;
            }
        } else {
            txtView.setTextColor(Color.parseColor("#1997f8"));
            isCheckResId = true;
        }
        resCheckTxtView = txtView;
        setTitle();
    }

    private void setTitle(){
        iv_depart_switch.setImageResource(isCheckResId ? R.mipmap.bk_gouxuan : R.mipmap.bk_1);
        tv_depart_title_check.setText((isCheckResId ? resCheckTxtView.getText() : ""));
    }

    public interface OnDepartOprationListener {
        void departOpration(TextView txtView);
    }

}
