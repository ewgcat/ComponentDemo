package com.yijian.clubmodule.ui.price.courseprice.filter;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijian.clubmodule.R;





/**
 * Created by The_P on 2018/4/11.
 */

public class OptionDialog extends DialogFragment implements View.OnClickListener {


    TextView tvPrice1;
    TextView tvPrice2;
    TextView tvPrice3;
    TextView tvPrice4;

    TextView tvClassJieshu1;
    TextView tvClassJieshu2;
    TextView tvClassJieshu3;

    TextView tvLongtime1;
    TextView tvLongtime2;
    TextView tvLongtime3;
    TextView tvLongtime4;

    TextView tvYouxiaoqi1;
    TextView tvYouxiaoqi2;
    TextView tvYouxiaoqi3;

    View emptyView;


    private int price;
    private int classJieShu = -1;
    private int classLongTime;
    private int classYouXiaoQi;


    private static final String TAG = "OptionDialog";

    private CoachClassFilterBean coachClassFilterBean;

    public OptionDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        coachClassFilterBean = (CoachClassFilterBean) arguments.getSerializable("coachClassFilterBean");


    }


    @Override
    public void onStart() {

        //设置DialogFragment所依附的window背景透明（不设置会有一块灰色的背景）
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置dialog的位置（自定义的布局并没有显示在window的中间，没达到我想要的效果）
        getDialog().getWindow().setGravity(Gravity.RIGHT);
        //设置Window的大小，想要自定义Dialog的位置摆放正确，将Window的大小保持和自定义Dialog的大小一样
        getDialog().getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_coach_class_filter, container, false);
        emptyView = view.findViewById(R.id.empty_view);
        tvPrice1 = view.findViewById(R.id.tv_price1);
        tvPrice2 = view.findViewById(R.id.tv_price2);
        tvPrice3 = view.findViewById(R.id.tv_price3);
        tvPrice4 = view.findViewById(R.id.tv_price4);
        tvClassJieshu1 = view.findViewById(R.id.tv_class_jieshu1);
        tvClassJieshu2 = view.findViewById(R.id.tv_class_jieshu2);
        tvClassJieshu3 = view.findViewById(R.id.tv_class_jieshu3);

        tvYouxiaoqi1 = view.findViewById(R.id.tv_youxiaoqi1);
        tvYouxiaoqi2 = view.findViewById(R.id.tv_youxiaoqi2);
        tvYouxiaoqi3 = view.findViewById(R.id.tv_youxiaoqi3);

        tvLongtime1 = view.findViewById(R.id.tv_longtime1);
        tvLongtime2 = view.findViewById(R.id.tv_longtime2);
        tvLongtime3 = view.findViewById(R.id.tv_longtime3);
        tvLongtime4 = view.findViewById(R.id.tv_longtime4);

        view.findViewById(R.id.tv_reset).setOnClickListener(this);
        view.findViewById(R.id.tv_confirm).setOnClickListener(this);
        view.findViewById(R.id.tv_price1).setOnClickListener(this);
        view.findViewById(R.id.tv_price2).setOnClickListener(this);
        view.findViewById(R.id.tv_price3).setOnClickListener(this);
        view.findViewById(R.id.tv_price4).setOnClickListener(this);
        view.findViewById(R.id.empty_view).setOnClickListener(this);
        view.findViewById(R.id.tv_class_jieshu1).setOnClickListener(this);
        view.findViewById(R.id.tv_class_jieshu2).setOnClickListener(this);
        view.findViewById(R.id.tv_class_jieshu3).setOnClickListener(this);
        view.findViewById(R.id.tv_longtime1).setOnClickListener(this);
        view.findViewById(R.id.tv_longtime2).setOnClickListener(this);
        view.findViewById(R.id.tv_longtime3).setOnClickListener(this);
        view.findViewById(R.id.tv_longtime4).setOnClickListener(this);
        view.findViewById(R.id.tv_youxiaoqi1).setOnClickListener(this);
        view.findViewById(R.id.tv_youxiaoqi2).setOnClickListener(this);
        view.findViewById(R.id.tv_youxiaoqi3).setOnClickListener(this);

        initView();

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
    }


    private void initView() {
        if (coachClassFilterBean != null) {

            String lcourseNum = coachClassFilterBean.getLcourseNum();
            String rcourseNum = coachClassFilterBean.getRcourseNum();

            if (!TextUtils.isEmpty(lcourseNum)) {
                if (lcourseNum.equals("0") && rcourseNum.equals("10")) {
                    selectClassJieShu(1);
                } else if (lcourseNum.equals("10") && rcourseNum.equals("30")) {
                    selectClassJieShu(2);
                } else if (lcourseNum.equals("30")) {
                    selectClassJieShu(3);
                }
            }


            String ltotalPrice = coachClassFilterBean.getLtotalPrice();
            String rtotalPrice = coachClassFilterBean.getRtotalPrice();
            if (!TextUtils.isEmpty(ltotalPrice)) {
                if (ltotalPrice.equals("0") && rtotalPrice.equals("1000")) {
                    selectPrice(1);
                } else if (ltotalPrice.equals("1000") && rtotalPrice.equals("2000")) {
                    selectPrice(2);
                } else if (ltotalPrice.equals("2000") && rtotalPrice.equals("3000")) {
                    selectPrice(3);
                } else if (ltotalPrice.equals("3000")) {
                    selectPrice(4);
                }
            }


            String lconsumingMinute = coachClassFilterBean.getLconsumingMinute();
            String rconsumingMinute = coachClassFilterBean.getRconsumingMinute();
            if (!TextUtils.isEmpty(lconsumingMinute)) {
                if (lconsumingMinute.equals("0") && rconsumingMinute.equals("60")) {
                    selectClassLongTime(1);
                } else if (lconsumingMinute.equals("60") && rconsumingMinute.equals("120")) {
                    selectClassLongTime(2);
                } else if (lconsumingMinute.equals("120") && rconsumingMinute.equals("180")) {
                    selectClassLongTime(3);
                } else if (lconsumingMinute.equals("180")) {
                    selectClassLongTime(4);
                }
            }


            String indate = coachClassFilterBean.getIndate();
            if (!TextUtils.isEmpty(indate)) {
                if (indate.equals("90")) {
                    selectClassYouXiaoQi(1);
                } else if (indate.equals("180")) {
                    selectClassYouXiaoQi(2);
                } else if (indate.equals("365")) {
                    selectClassYouXiaoQi(3);
                }
            }
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    private void resetCourse(){
        classJieShu = -1;
        tvClassJieshu1.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu2.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu3.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvClassJieshu2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvClassJieshu3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
    }

    private void resetPrice(){
        price = -1;
        tvPrice1.setTextColor(Color.parseColor("#666666"));
        tvPrice2.setTextColor(Color.parseColor("#666666"));
        tvPrice3.setTextColor(Color.parseColor("#666666"));
        tvPrice4.setTextColor(Color.parseColor("#666666"));
        tvPrice1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice4.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
    }

    private void resetValidity(){
        classYouXiaoQi = -1;
        tvYouxiaoqi1.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi2.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi3.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouxiaoqi2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouxiaoqi3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
    }

    private void resetView() {

        classJieShu = -1;
        price = -1;
        classLongTime = -1;
        classYouXiaoQi = -1;

        tvClassJieshu1.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu2.setTextColor(Color.parseColor("#666666"));
        tvClassJieshu3.setTextColor(Color.parseColor("#666666"));

        tvYouxiaoqi1.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi2.setTextColor(Color.parseColor("#666666"));
        tvYouxiaoqi3.setTextColor(Color.parseColor("#666666"));

        tvPrice1.setTextColor(Color.parseColor("#666666"));
        tvPrice2.setTextColor(Color.parseColor("#666666"));
        tvPrice3.setTextColor(Color.parseColor("#666666"));
        tvPrice4.setTextColor(Color.parseColor("#666666"));

        tvLongtime1.setTextColor(Color.parseColor("#666666"));
        tvLongtime2.setTextColor(Color.parseColor("#666666"));
        tvLongtime3.setTextColor(Color.parseColor("#666666"));
        tvLongtime4.setTextColor(Color.parseColor("#666666"));

        tvClassJieshu1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvClassJieshu2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvClassJieshu3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvYouxiaoqi1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouxiaoqi2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvYouxiaoqi3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvPrice1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvPrice4.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvLongtime1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvLongtime2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvLongtime3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvLongtime4.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));


        tvLongtime1.setCompoundDrawables(null, null, null, null);
        tvLongtime2.setCompoundDrawables(null, null, null, null);
        tvLongtime3.setCompoundDrawables(null, null, null, null);
        tvLongtime4.setCompoundDrawables(null, null, null, null);
        tvPrice1.setCompoundDrawables(null, null, null, null);
        tvPrice2.setCompoundDrawables(null, null, null, null);
        tvPrice3.setCompoundDrawables(null, null, null, null);
        tvPrice4.setCompoundDrawables(null, null, null, null);

        tvClassJieshu1.setCompoundDrawables(null, null, null, null);
        tvClassJieshu2.setCompoundDrawables(null, null, null, null);
        tvClassJieshu3.setCompoundDrawables(null, null, null, null);
        tvYouxiaoqi1.setCompoundDrawables(null, null, null, null);
        tvYouxiaoqi2.setCompoundDrawables(null, null, null, null);
        tvYouxiaoqi3.setCompoundDrawables(null, null, null, null);
    }

    private void setSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#1997f8"));
        textView.setBackground(getActivity().getDrawable(R.drawable.blue_stroke_select_bg));
        Drawable jd_choose = getActivity().getResources().getDrawable(R.mipmap.jd_choose);
        jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
        textView.setCompoundDrawables(jd_choose, null, null, null);

    }

    private void setUnSelectStyle(TextView textView) {
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        textView.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        textView.setCompoundDrawables(null, null, null, null);
    }



    private void setResultForNoSure() {

        if (onDismissListener != null) {
            resetView();
            onDismissListener.onDismiss(null);
        }
    }


    private void setResultForSure() {
        CoachClassFilterBean coachClassFilterBean = new CoachClassFilterBean();
        if (classJieShu == 1) {
            coachClassFilterBean.setLcourseNum("0");
            coachClassFilterBean.setRcourseNum("10");
        } else if (classJieShu == 2) {
            coachClassFilterBean.setLcourseNum("10");
            coachClassFilterBean.setRcourseNum("30");
        } else if (classJieShu == 3) {
            coachClassFilterBean.setLcourseNum("30");
            coachClassFilterBean.setRcourseNum(null);
        } else {
            coachClassFilterBean.setLcourseNum(null);
            coachClassFilterBean.setRcourseNum(null);
        }
        if (price == 1) {
            coachClassFilterBean.setLtotalPrice("0");
            coachClassFilterBean.setRtotalPrice("1000");
        } else if (price == 2) {
            coachClassFilterBean.setLtotalPrice("1000");
            coachClassFilterBean.setRtotalPrice("2000");
        } else if (price == 3) {
            coachClassFilterBean.setLtotalPrice("2000");
            coachClassFilterBean.setRtotalPrice("3000");
        } else if (price == 4) {
            coachClassFilterBean.setLtotalPrice("3000");
        } else {
            coachClassFilterBean.setLtotalPrice(null);
            coachClassFilterBean.setRtotalPrice(null);
        }
        if (classLongTime == 1) {
            coachClassFilterBean.setLconsumingMinute("0");
            coachClassFilterBean.setRconsumingMinute("60");
        } else if (classLongTime == 2) {
            coachClassFilterBean.setLconsumingMinute("60");
            coachClassFilterBean.setRconsumingMinute("120");
        } else if (classLongTime == 3) {
            coachClassFilterBean.setLconsumingMinute("120");
            coachClassFilterBean.setRconsumingMinute("180");
        } else if (classLongTime == 4) {
            coachClassFilterBean.setLconsumingMinute("180");
            coachClassFilterBean.setRconsumingMinute(null);
        } else {
            coachClassFilterBean.setLconsumingMinute(null);
            coachClassFilterBean.setRconsumingMinute(null);
        }

        if (classYouXiaoQi == 1) {
            coachClassFilterBean.setIndate("90");
        } else if (classYouXiaoQi == 2) {
            coachClassFilterBean.setIndate("180");
        } else if (classYouXiaoQi == 3) {
            coachClassFilterBean.setIndate("365");
        } else {
            coachClassFilterBean.setIndate(null);
        }


        if (onDismissListener != null) {
            String indate = coachClassFilterBean.getIndate();
            String lconsumingMinute = coachClassFilterBean.getLconsumingMinute();
            String rconsumingMinute = coachClassFilterBean.getRconsumingMinute();
            String lcourseNum = coachClassFilterBean.getLcourseNum();
            String rcourseNum = coachClassFilterBean.getRcourseNum();
            String ltotalPrice = coachClassFilterBean.getLtotalPrice();
            String rtotalPrice = coachClassFilterBean.getRtotalPrice();
            if (TextUtils.isEmpty(indate)
                    && TextUtils.isEmpty(lconsumingMinute)
                    && TextUtils.isEmpty(rconsumingMinute)
                    && TextUtils.isEmpty(lcourseNum)
                    && TextUtils.isEmpty(rcourseNum)
                    && TextUtils.isEmpty(ltotalPrice)
                    && TextUtils.isEmpty(rtotalPrice)
                    ) {
                onDismissListener.onDismiss(null);
            } else {
                onDismissListener.onDismiss(coachClassFilterBean);

            }
        }
    }

    private void selectPrice(int i) {
        if (i == 1) {
            price = 1;
            setSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice3);
            setUnSelectStyle(tvPrice4);
        } else if (i == 2) {
            price = 2;

            setSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice3);
            setUnSelectStyle(tvPrice4);
        } else if (i == 3) {
            price = 3;

            setSelectStyle(tvPrice3);
            setUnSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice4);
        } else if (i == 4) {
            price = 4;

            setSelectStyle(tvPrice4);
            setUnSelectStyle(tvPrice1);
            setUnSelectStyle(tvPrice2);
            setUnSelectStyle(tvPrice3);
        }
    }

    private void selectClassJieShu(int i) {
        if (i == 1) {
            if(classJieShu == 1){
                resetCourse();
            }else{
                classJieShu = 1;
                setSelectStyle(tvClassJieshu1);
                setUnSelectStyle(tvClassJieshu2);
                setUnSelectStyle(tvClassJieshu3);
            }

        } else if (i == 2) {
            if(classJieShu == 2){
                resetPrice();
            }else{
                classJieShu = 2;

                setSelectStyle(tvClassJieshu2);
                setUnSelectStyle(tvClassJieshu1);
                setUnSelectStyle(tvClassJieshu3);
            }

        } else if (i == 3) {
            if(classJieShu == 3){
                resetValidity();
            }else{
                classJieShu = 3;

                setSelectStyle(tvClassJieshu3);
                setUnSelectStyle(tvClassJieshu1);
                setUnSelectStyle(tvClassJieshu2);
            }

        }
    }


    private void selectClassLongTime(int i) {

        if (i == 1) {
            classLongTime = 1;
            setSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime3);
            setUnSelectStyle(tvLongtime4);
        } else if (i == 2) {
            classLongTime = 2;

            setSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime3);
            setUnSelectStyle(tvLongtime4);
        } else if (i == 3) {
            classLongTime = 3;

            setSelectStyle(tvLongtime3);
            setUnSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime4);
        } else if (i == 4) {
            classLongTime = 4;

            setSelectStyle(tvLongtime4);
            setUnSelectStyle(tvLongtime1);
            setUnSelectStyle(tvLongtime2);
            setUnSelectStyle(tvLongtime3);
        }
    }

    private void selectClassYouXiaoQi(int i) {
        if (i == 1) {
            classYouXiaoQi = 1;
            setSelectStyle(tvYouxiaoqi1);
            setUnSelectStyle(tvYouxiaoqi2);
            setUnSelectStyle(tvYouxiaoqi3);
        } else if (i == 2) {
            classYouXiaoQi = 2;
            setSelectStyle(tvYouxiaoqi2);
            setUnSelectStyle(tvYouxiaoqi1);
            setUnSelectStyle(tvYouxiaoqi3);
        } else if (i == 3) {
            classYouXiaoQi = 3;
            setSelectStyle(tvYouxiaoqi3);
            setUnSelectStyle(tvYouxiaoqi1);
            setUnSelectStyle(tvYouxiaoqi2);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_class_jieshu1) {
            selectClassJieShu(1);

        } else if (i == R.id.tv_class_jieshu2) {
            selectClassJieShu(2);

        } else if (i == R.id.tv_class_jieshu3) {
            selectClassJieShu(3);


            //价格区间
        } else if (i == R.id.tv_price1) {
            selectPrice(1);

        } else if (i == R.id.tv_price2) {
            selectPrice(2);

        } else if (i == R.id.tv_price3) {
            selectPrice(3);

        } else if (i == R.id.tv_price4) {
            selectPrice(4);


            //单节时长
        } else if (i == R.id.tv_longtime1) {
            selectClassLongTime(1);

        } else if (i == R.id.tv_longtime2) {
            selectClassLongTime(2);

        } else if (i == R.id.tv_longtime3) {
            selectClassLongTime(3);

        } else if (i == R.id.tv_longtime4) {
            selectClassLongTime(4);


            //有效期
        } else if (i == R.id.tv_youxiaoqi1) {
            selectClassYouXiaoQi(1);

        } else if (i == R.id.tv_youxiaoqi2) {
            selectClassYouXiaoQi(2);

        } else if (i == R.id.tv_youxiaoqi3) {
            selectClassYouXiaoQi(3);

        } else if (i == R.id.tv_reset) {
            resetView();

        } else if (i == R.id.empty_view) {
            setResultForNoSure();
            dismiss();

        } else if (i == R.id.tv_confirm) {
            setResultForSure();
            dismiss();

        }
    }


    public interface OnDismissListener {
        void onDismiss(CoachClassFilterBean coachClassFilterBean);
    }

    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
}

