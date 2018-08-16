package com.yijian.staff.mvp.course.preparelessons.createlession;

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
import com.yijian.staff.bean.DepartBean;
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
    private TextView tv_depart_title; //头部标题
    private TextView tv_depart_title_check; //选中训练部位
    private List<DepartBean> selectedDepartList = new ArrayList<>(); //选中部位的集合
    private List<DepartBean> departArray = new ArrayList<>(); //训练部位的集合

    public List<DepartBean> getSelectedDepartList() {
        return selectedDepartList;
    }

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

    public void addLineView(List<DepartBean> departArray, OnDepartOprationListener onDepartOprationListener) {
        this.departArray = departArray;
        //添加头部
        linHeaderDepartContain = new LinearLayout(mContext);
        linHeaderDepartContain.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        linHeaderDepartContain.setOrientation(LinearLayout.HORIZONTAL);
        linHeaderDepartContain.setGravity(Gravity.CENTER_VERTICAL);
        linHeaderDepartContain.setPadding(DensityUtil.dip2px(mContext, 15), DensityUtil.dip2px(mContext, 15),
                DensityUtil.dip2px(mContext, 15), DensityUtil.dip2px(mContext, 15));
        tv_depart_title = new TextView(mContext);
        tv_depart_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        LayoutParams titleLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tv_depart_title.setLayoutParams(titleLP);
        tv_depart_title.setText("训练的部位");
        tv_depart_title.setTextColor(Color.parseColor("#333333"));

        tv_depart_title_check = new TextView(mContext);
        tv_depart_title_check.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        LayoutParams titleCheckLP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_depart_title_check.setGravity(Gravity.RIGHT);
        tv_depart_title_check.setLayoutParams(titleCheckLP);
        tv_depart_title_check.setTextColor(Color.parseColor("#1997f8"));

        linHeaderDepartContain.addView(tv_depart_title);
        linHeaderDepartContain.addView(tv_depart_title_check);
        addView(linHeaderDepartContain);
        linHeaderDepartContain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linContentContain.setVisibility((linContentContain.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            }
        });

        //添加头部分割线
        View lineView = new View(mContext);
        lineView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        LayoutParams lineLP = new LayoutParams(LayoutParams.MATCH_PARENT, 2);
        lineLP.setMargins(DensityUtil.dip2px(mContext, 16), 0, DensityUtil.dip2px(mContext, 16), 0);
        lineView.setLayoutParams(lineLP);
        addView(lineView);

        //添加部位内容 和 按钮布局
        linContentContain = new LinearLayout(mContext);
        linContentContain.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        linContentContain.setGravity(Gravity.CENTER_VERTICAL);
        linContentContain.setOrientation(LinearLayout.VERTICAL);
        linContentContain.setPadding(0, 0, 0, DensityUtil.dip2px(mContext, 15));


        for (int i = 0; i < departArray.size(); i++) {
            LinearLayout linContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_depart, null);
            View view_line = linContain.findViewById(R.id.view_line);
            TextView tv_depart_1 = (TextView) linContain.findViewById(R.id.tv_depart_1);
            tv_depart_1.setText(departArray.get(i).getPartName());
            tv_depart_1.setTag(i);
            tv_depart_1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) v.getTag();
                    onDepartOprationListener.departOpration(departArray.get(tag).getId());
                    departOpration((TextView) v);
                }
            });
            viewDepartList.add(tv_depart_1);
            if ((i + 1) <= (departArray.size() - 1)) {
                TextView tv_depart_2 = (TextView) linContain.findViewById(R.id.tv_depart_2);
                tv_depart_2.setText(departArray.get(i + 1).getPartName());
                tv_depart_2.setTag(i + 1);
                tv_depart_2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        int tag = (int) v.getTag();
//                        onDepartOprationListener.departOpration(departArray.get(tag).getId());
                        departOpration((TextView) v);
                    }
                });
                viewDepartList.add(tv_depart_2);
            }

            i = i + 1;
            view_line.setVisibility((i == (departArray.size() - 1)) ? View.GONE : View.VISIBLE);
            linContentContain.addView(linContain);
        }


        addView(linContentContain);

    }

    public void departOpration(TextView txtView) {
        /********************* 设置字体颜色 **********************/
        /*for (int i = 0; i < viewDepartList.size(); i++) {
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
        resCheckTxtView = txtView;*/

        int tag = (int) txtView.getTag();
        DepartBean departBean = departArray.get(tag);
        if (selectedDepartList.contains(departBean)) {
            selectedDepartList.remove(departBean);
            txtView.setTextColor(Color.parseColor("#666666"));
        } else {
            selectedDepartList.add(departBean);
            txtView.setTextColor(Color.parseColor("#1997f8"));
        }

        setTitle();
    }

    private void setTitle() {
//        tv_depart_title_check.setText((isCheckResId ? resCheckTxtView.getText() : ""));
        StringBuffer sb = new StringBuffer();
        for (DepartBean departBean : selectedDepartList) {
            sb.append(departBean.getPartName() + "、");
        }
        if (selectedDepartList.size() > 0) {
            tv_depart_title_check.setText(sb.toString().substring(0, sb.toString().lastIndexOf("、")));
        } else {
            tv_depart_title_check.setText("");
        }
    }

    public interface OnDepartOprationListener {
        void departOpration(String id);
    }

}
