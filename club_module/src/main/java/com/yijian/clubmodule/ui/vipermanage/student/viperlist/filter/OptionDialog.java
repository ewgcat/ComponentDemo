package com.yijian.clubmodule.ui.vipermanage.student.viperlist.filter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.clubmodule.R;
import com.yijian.commonlib.util.DateUtil;

import java.util.Calendar;




/**
 * Created by The_P on 2018/4/11.
 */

public class OptionDialog extends DialogFragment implements View.OnClickListener {

    TextView tvSexMan;
    TextView tvSexWoman;
    TextView tvSijiaoClass;
    TextView tvTiyanClass;
    TextView tvTime1;
    TextView tvTime2;
    TextView tvTime3;
    TextView tvStartTime;
    TextView tvEndTime;
    TextView tvBuyTime1;
    TextView tvBuyTime2;
    TextView tvBuyTime3;
    TextView tvDay1;
    TextView tvDay2;
    TextView tvDay3;


    private Activity activity;

    private int sex = -1;//性别：【0:未知 1:男 2:女】
    private String classType = null;//课程类型：【1:私教课，1:体验课】

    private int joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
    private String startTime = null;//开始时间
    private String endTime = null;//结束时间


    private int buyClassTime = -1;//购买时间：【0:0天，7:7天，30:30天】

    private int expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】


    private static final String TAG = "OptionDialog";

    private CoachViperFilterBean coachViperFilterBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_coach_filter_viper, container, false);


        tvSexMan = view.findViewById(R.id.tv_sex_man);
        tvSexWoman = view.findViewById(R.id.tv_sex_woman);
        tvSijiaoClass = view.findViewById(R.id.tv_sijiao_class);
        tvTiyanClass = view.findViewById(R.id.tv_tiyan_class);

        tvTime1 = view.findViewById(R.id.tv_time1);
        tvTime2 = view.findViewById(R.id.tv_time2);
        tvTime3 = view.findViewById(R.id.tv_time3);
        tvStartTime = view.findViewById(R.id.tv_start_time);
        tvEndTime = view.findViewById(R.id.tv_end_time);

        tvBuyTime1 = view.findViewById(R.id.tv_buy_time1);
        tvBuyTime2 = view.findViewById(R.id.tv_buy_time2);
        tvBuyTime3 = view.findViewById(R.id.tv_buy_time3);

        tvDay1 = view.findViewById(R.id.tv_day1);
        tvDay2 = view.findViewById(R.id.tv_day2);
        tvDay3 = view.findViewById(R.id.tv_day3);

        view.findViewById(R.id.tv_reset).setOnClickListener(this);
        view.findViewById(R.id.tv_confirm).setOnClickListener(this);
        view.findViewById(R.id.empty_view).setOnClickListener(this);
        view.findViewById(R.id.tv_sex_man).setOnClickListener(this);
        view.findViewById(R.id.tv_sex_woman).setOnClickListener(this);
        view.findViewById(R.id.tv_sijiao_class).setOnClickListener(this);
        view.findViewById(R.id.tv_tiyan_class).setOnClickListener(this);
        view.findViewById(R.id.tv_time1).setOnClickListener(this);
        view.findViewById(R.id.tv_time2).setOnClickListener(this);
        view.findViewById(R.id.tv_time3).setOnClickListener(this);
        view.findViewById(R.id.tv_start_time).setOnClickListener(this);
        view.findViewById(R.id.tv_end_time).setOnClickListener(this);
        view.findViewById(R.id.tv_buy_time1).setOnClickListener(this);
        view.findViewById(R.id.tv_buy_time2).setOnClickListener(this);
        view.findViewById(R.id.tv_buy_time3).setOnClickListener(this);
        view.findViewById(R.id.tv_day1).setOnClickListener(this);
        view.findViewById(R.id.tv_day2).setOnClickListener(this);
        view.findViewById(R.id.tv_day3).setOnClickListener(this);

        initView();

        return view;
    }


    public OptionDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        coachViperFilterBean = (CoachViperFilterBean) arguments.getSerializable("coachViperFilterBean");


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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
    }


    private void initView() {
        if (coachViperFilterBean != null) {

            int sex = coachViperFilterBean.getSex();
            if (sex == 0) {
                selectSex(0);
            } else if (sex == 1) {
                selectSex(1);
            }

            String courseType = coachViperFilterBean.getCourseType();
            if (!TextUtils.isEmpty(courseType)) {
                if (courseType.equals("1")) {
                    selectClassType(1);
                } else if (courseType.equals("2")) {
                    selectClassType(2);
                }
            }


            int joinTimeType = coachViperFilterBean.getJoinTimeType();
            if (joinTimeType == -1) {
                selectRuJiTime(4);
                tvStartTime.setText(coachViperFilterBean.getStartTime());
                tvEndTime.setText(coachViperFilterBean.getEndTime());
            } else if (joinTimeType == 0) {
                selectRuJiTime(1);
            } else if (joinTimeType == 7) {
                selectRuJiTime(2);
            } else if (joinTimeType == 30) {
                selectRuJiTime(3);
            }

            int buyTime = coachViperFilterBean.getBuyTime();
            if (buyTime == 0) {
                selectBuyClassTime(1);
            } else if (buyTime == 7) {
                selectBuyClassTime(2);
            } else if (buyTime == 30) {
                selectBuyClassTime(3);
            }

            int expiringDay = coachViperFilterBean.getExpiringDay();
            if (expiringDay == 7) {
                selectExpiringDay(1);
            } else if (expiringDay == 14) {
                selectExpiringDay(2);

            } else if (expiringDay == 30) {
                selectExpiringDay(3);

            }


        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    private void resetView() {

        sex = -1;//性别：【0:未知 1:男 2:女】
        classType = null;//课程类型：【1:私教课，1:体验课】
        joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
        startTime = null;//开始时间
        endTime = null;//结束时间
        buyClassTime = -1;//购买时间：【0:0天，7:7天，30:30天】
        expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】

        tvStartTime.setText("");
        tvEndTime.setText("");
        tvSexMan.setTextColor(Color.parseColor("#666666"));
        tvSexWoman.setTextColor(Color.parseColor("#666666"));

        tvSijiaoClass.setTextColor(Color.parseColor("#666666"));
        tvTiyanClass.setTextColor(Color.parseColor("#666666"));


        //互斥
        tvTime1.setTextColor(Color.parseColor("#666666"));
        tvTime2.setTextColor(Color.parseColor("#666666"));
        tvTime3.setTextColor(Color.parseColor("#666666"));


        tvBuyTime1.setTextColor(Color.parseColor("#666666"));
        tvBuyTime2.setTextColor(Color.parseColor("#666666"));
        tvBuyTime3.setTextColor(Color.parseColor("#666666"));

        tvDay1.setTextColor(Color.parseColor("#666666"));
        tvDay2.setTextColor(Color.parseColor("#666666"));
        tvDay3.setTextColor(Color.parseColor("#666666"));


        tvSexMan.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvSexWoman.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvSijiaoClass.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTiyanClass.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvTime1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTime2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvTime3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvBuyTime1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvBuyTime2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvBuyTime3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));

        tvDay1.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvDay2.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
        tvDay3.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));


        tvSexMan.setCompoundDrawables(null, null, null, null);
        tvSexWoman.setCompoundDrawables(null, null, null, null);

        tvSijiaoClass.setCompoundDrawables(null, null, null, null);
        tvTiyanClass.setCompoundDrawables(null, null, null, null);

        tvTime1.setCompoundDrawables(null, null, null, null);
        tvTime2.setCompoundDrawables(null, null, null, null);
        tvTime3.setCompoundDrawables(null, null, null, null);
        tvBuyTime1.setCompoundDrawables(null, null, null, null);
        tvBuyTime2.setCompoundDrawables(null, null, null, null);
        tvBuyTime3.setCompoundDrawables(null, null, null, null);
        tvDay1.setCompoundDrawables(null, null, null, null);
        tvDay2.setCompoundDrawables(null, null, null, null);
        tvDay3.setCompoundDrawables(null, null, null, null);


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


    //性别
    private void selectSex(int index) {
        if (index == 0) {
            if (sex == 1) {
                sex = -1;
                tvSexMan.setTextColor(Color.parseColor("#666666"));
                tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexMan.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexMan.setCompoundDrawables(null, null, null, null);

                tvSexWoman.setTextColor(Color.parseColor("#666666"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexWoman.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexWoman.setCompoundDrawables(null, null, null, null);
            } else {
                sex = 1;
                tvSexMan.setTextColor(Color.parseColor("#1997f8"));
                tvSexMan.setBackgroundColor(Color.parseColor("#ffffff"));
                tvSexMan.setBackground(getActivity().getDrawable(R.drawable.blue_stroke_select_bg));
                Drawable jd_choose = getActivity().getResources().getDrawable(R.mipmap.jd_choose);
                jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
                tvSexMan.setCompoundDrawables(jd_choose, null, null, null);

                tvSexWoman.setTextColor(Color.parseColor("#666666"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexWoman.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexWoman.setCompoundDrawables(null, null, null, null);
            }

        } else {
            if (sex == 2) {
                sex = -1;
                tvSexMan.setTextColor(Color.parseColor("#666666"));
                tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexMan.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexMan.setCompoundDrawables(null, null, null, null);

                tvSexWoman.setTextColor(Color.parseColor("#666666"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexWoman.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexWoman.setCompoundDrawables(null, null, null, null);
            } else {
                sex = 2;
                tvSexWoman.setTextColor(Color.parseColor("#1997f8"));
                tvSexWoman.setBackgroundColor(Color.parseColor("#ffffff"));
                tvSexWoman.setBackground(getActivity().getDrawable(R.drawable.blue_stroke_select_bg));
                Drawable jd_choose = getActivity().getResources().getDrawable(R.mipmap.jd_choose);
                jd_choose.setBounds(0, 0, jd_choose.getMinimumWidth(), jd_choose.getMinimumHeight());
                tvSexWoman.setCompoundDrawables(jd_choose, null, null, null);

                tvSexMan.setTextColor(Color.parseColor("#666666"));
                tvSexMan.setBackgroundColor(Color.parseColor("#f2f2f2"));
                tvSexMan.setBackground(getActivity().getDrawable(R.drawable.gray_stroke_unselect_bg));
                tvSexMan.setCompoundDrawables(null, null, null, null);
            }

        }
    }

    //课程类型
    private void selectClassType(int index) {
        if (index == 1) {
            if ("1".equals(classType)) {
                classType = null;
                setUnSelectStyle(tvSijiaoClass);
                setUnSelectStyle(tvTiyanClass);
            } else {
                classType = "1";
                setSelectStyle(tvSijiaoClass);
                setUnSelectStyle(tvTiyanClass);
            }


        } else if (index == 2) {
            if ("2".equals(classType)) {
                classType = null;
                setUnSelectStyle(tvSijiaoClass);
                setUnSelectStyle(tvTiyanClass);
            } else {
                classType = "2";
                setSelectStyle(tvTiyanClass);
                setUnSelectStyle(tvSijiaoClass);
            }

        }

    }

    //入籍时间
    private void selectRuJiTime(int index) {
        if (index == 1) {

            if (joinTimeType == 0) {
                joinTimeType = -1;
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            } else {
                joinTimeType = 0;
                setSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            }
        } else if (index == 2) {
            if (joinTimeType == 7) {
                joinTimeType = -1;
                setUnSelectStyle(tvTime2);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            } else {
                joinTimeType = 7;
                setSelectStyle(tvTime2);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime3);
                tvStartTime.setText("");
                tvEndTime.setText("");
            }

        } else if (index == 3) {
            if (joinTimeType == 30) {
                joinTimeType = 30;
                setUnSelectStyle(tvTime3);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                tvStartTime.setText("");
                tvEndTime.setText("");
            } else {
                joinTimeType = 30;
                setSelectStyle(tvTime3);
                setUnSelectStyle(tvTime1);
                setUnSelectStyle(tvTime2);
                tvStartTime.setText("");
                tvEndTime.setText("");
            }

        } else if (index == 4) {

            joinTimeType = -1;
            setUnSelectStyle(tvTime1);
            setUnSelectStyle(tvTime2);
            setUnSelectStyle(tvTime3);

            Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                            String time = "";
                            if (month < 9 && dayOfMonth < 10) {
                                time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                            } else if (month > 9 && dayOfMonth > 10) {
                                time += year + "-" + (month + 1) + "-" + dayOfMonth;

                            } else if (month < 9 && dayOfMonth > 10) {
                                time += year + "-0" + (month + 1) + "-" + dayOfMonth;

                            } else if (month > 9 && dayOfMonth < 10) {
                                time += year + "-" + (month + 1) + "-0" + dayOfMonth;

                            }
                            tvStartTime.setText(time);


                            String endTime = tvEndTime.getText().toString();
                            if (!TextUtils.isEmpty(endTime)) {
                                endTime = endTime.replace("-", "");
                            }


                            if (!TextUtils.isEmpty(endTime)) {
                                endTime = endTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(time)) {
                                time = time.replace("-", "");
                            }
                            String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                            String s1 = "" + year + month + dayOfMonth;
                            if (Integer.parseInt(s) >= Integer.parseInt(s1)) {
                                if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(endTime)) {
                                    if (Integer.parseInt(time) > Integer.parseInt(endTime)) {
                                        tvStartTime.setText("");
                                        tvEndTime.setText("");
                                        Toast.makeText(getActivity(), "结束时间不得小于开始时间", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                tvStartTime.setText("");
                                Toast.makeText(getActivity(), "开始日期不得大于当前日期", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        } else if (index == 5) {
            joinTimeType = -1;
            setUnSelectStyle(tvTime1);
            setUnSelectStyle(tvTime2);
            setUnSelectStyle(tvTime3);
            Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                            String time = "";

                            if (month < 9 && dayOfMonth < 10) {
                                time += year + "-0" + (month + 1) + "-0" + dayOfMonth;
                            } else if (month > 9 && dayOfMonth > 10) {
                                time += year + "-" + (month + 1) + "-" + dayOfMonth;
                            } else if (month < 9 && dayOfMonth > 10) {
                                time += year + "-0" + (month + 1) + "-" + dayOfMonth;

                            } else if (month > 9 && dayOfMonth < 10) {
                                time += year + "-" + (month + 1) + "-0" + dayOfMonth;
                            }
                            tvEndTime.setText(time);


                            String startTime = tvStartTime.getText().toString();

                            if (!TextUtils.isEmpty(startTime)) {
                                startTime = startTime.replace("-", "");
                            }
                            if (!TextUtils.isEmpty(time)) {
                                time = time.replace("-", "");
                            }

                            String s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay();
                            String s1 = "" + year + month + dayOfMonth;
                            if (Integer.parseInt(s) >= Integer.parseInt(s1)) {
                                if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(startTime)) {
                                    if (Integer.parseInt(time) < Integer.parseInt(startTime)) {
                                        tvStartTime.setText("");
                                        tvEndTime.setText("");
                                        Toast.makeText(getActivity(), "结束时间不得大于开始时间", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                tvEndTime.setText("");
                                Toast.makeText(getActivity(), "结束日期不得大于当前日期", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    }

    //私教课购买情况
    private void selectBuyClassTime(int index) {

        if (index == 1) {
            if (buyClassTime == 7) {
                buyClassTime = -1;
                setUnSelectStyle(tvBuyTime1);
                setUnSelectStyle(tvBuyTime2);
                setUnSelectStyle(tvBuyTime3);
            } else {
                buyClassTime = 7;
                setSelectStyle(tvBuyTime1);
                setUnSelectStyle(tvBuyTime2);
                setUnSelectStyle(tvBuyTime3);
            }

        } else if (index == 2) {
            if (buyClassTime == 15) {
                buyClassTime = -1;
                setUnSelectStyle(tvBuyTime1);
                setUnSelectStyle(tvBuyTime2);
                setUnSelectStyle(tvBuyTime3);
            } else {
                buyClassTime = 15;
                setSelectStyle(tvBuyTime2);
                setUnSelectStyle(tvBuyTime1);
                setUnSelectStyle(tvBuyTime3);
            }

        } else if (index == 3) {
            if (buyClassTime == 30) {
                buyClassTime = -1;
                setUnSelectStyle(tvBuyTime1);
                setUnSelectStyle(tvBuyTime2);
                setUnSelectStyle(tvBuyTime3);
            } else {
                buyClassTime = 30;
                setSelectStyle(tvBuyTime3);
                setUnSelectStyle(tvBuyTime1);
                setUnSelectStyle(tvBuyTime2);
            }

        }


    }


    //快到期时间
    private void selectExpiringDay(int index) {

        if (index == 1) {

            if (expiringDay == 7) {
                expiringDay = -1;
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            } else {
                expiringDay = 7;
                setSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            }
        } else if (index == 2) {
            if (expiringDay == 15) {
                expiringDay = -1;
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            } else {
                expiringDay = 15;
                setSelectStyle(tvDay2);
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay3);
            }

        } else if (index == 3) {


            if (expiringDay == 30) {
                expiringDay = -1;
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
                setUnSelectStyle(tvDay3);
            } else {
                expiringDay = 30;
                setSelectStyle(tvDay3);
                setUnSelectStyle(tvDay1);
                setUnSelectStyle(tvDay2);
            }
        }
    }


    private void reset() {
        resetView();
        sex = -1;//性别：【0:未知 1:男 2:女】
        classType = null;//课程类型：【1:私教课，1:体验课】
        joinTimeType = -2;//入籍时间类型：【0:今日，7:最近七天，30:最近30天，-1:可编辑日期】
        startTime = null;//开始时间
        endTime = null;//结束时间
        buyClassTime = -1;//购买时间：【0:0天，7:7天，30:30天】
        expiringDay = -1;//快过期天数:【7:7天，14:14天，30:30天】
    }


    private void setResultNoSure() {
        if (onDismissListener != null) {
            onDismissListener.onDismiss(new CoachViperFilterBean());
        }
        dismiss();
    }

    private void setResultSure() {
        startTime = tvStartTime.getText().toString();
        endTime = tvEndTime.getText().toString();
        CoachViperFilterBean coachViperFilterBean = new CoachViperFilterBean();
        coachViperFilterBean.setSex(sex);
        coachViperFilterBean.setCourseType(classType);
        coachViperFilterBean.setJoinTimeType(joinTimeType);
        if (joinTimeType == -1) {
            coachViperFilterBean.setStartTime(startTime);
            coachViperFilterBean.setEndTime(endTime);
        }
        coachViperFilterBean.setBuyTime(buyClassTime);
        coachViperFilterBean.setExpiringDay(expiringDay);

        if (onDismissListener != null) {
            onDismissListener.onDismiss(coachViperFilterBean);
        }
        dismiss();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_sex_man) {
            selectSex(0);

        } else if (i == R.id.tv_sex_woman) {
            selectSex(1);


            //课程类型
        } else if (i == R.id.tv_sijiao_class) {
            selectClassType(1);

        } else if (i == R.id.tv_tiyan_class) {
            selectClassType(2);


            //入籍时间
        } else if (i == R.id.tv_time1) {
            selectRuJiTime(1);

        } else if (i == R.id.tv_time2) {
            selectRuJiTime(2);

        } else if (i == R.id.tv_time3) {
            selectRuJiTime(3);

        } else if (i == R.id.tv_start_time) {
            selectRuJiTime(4);

        } else if (i == R.id.tv_end_time) {
            selectRuJiTime(5);


            //购买时间
        } else if (i == R.id.tv_buy_time1) {
            selectBuyClassTime(1);

        } else if (i == R.id.tv_buy_time2) {
            selectBuyClassTime(2);

        } else if (i == R.id.tv_buy_time3) {
            selectBuyClassTime(3);


            //快到期时间
        } else if (i == R.id.tv_day1) {
            selectExpiringDay(1);

        } else if (i == R.id.tv_day2) {
            selectExpiringDay(2);

        } else if (i == R.id.tv_day3) {
            selectExpiringDay(3);


            //按钮
        } else if (i == R.id.tv_reset) {
            reset();

        } else if (i == R.id.tv_confirm) {
            setResultSure();
            dismiss();

        } else if (i == R.id.empty_view) {
            reset();
            setResultNoSure();

        }
    }


    public interface OnDismissListener {
        void onDismiss(CoachViperFilterBean coachViperFilterBean);
    }

    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

}

