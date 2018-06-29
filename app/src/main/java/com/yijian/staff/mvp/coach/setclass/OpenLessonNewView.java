package com.yijian.staff.mvp.coach.setclass;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.Observer;
import com.yijian.staff.mvp.coach.setclass.bean.PrivateLessonRecordBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/29.
 */

public class OpenLessonNewView extends LinearLayout implements Observer {

    private Context mContext;
    private OpenLessonNewActivity openLessonNewActivity;
    private PrivateLessonRecordBean privateLessonRecordBean;
    private int itemPosition;

    private TextView tv_action_title; //标题
    private TextView tv_action_record_finish; //标题  记录完成的
    private TextView tv_action_record_request; //标题  需要记录的
    private TextView tv_action_degree; //难易程度
    private TextView tv_action_name; //动作名称
    private TextView tv_action_limit; //动作次数
    private TextView tv_action_qixie; //器械选择
    private TextView tv_rank; //排序
    private RelativeLayout rel_action_header; //头部
    private LinearLayout lin_body_container; //动作内容容器
    private LinearLayout lin_body_header;  // Body的头部
    private LinearLayout lin_record_table;  // Body的表格

    private TextView tv_actionForm;  // 动作形态
    private TextView tv_actionStrength;  // 强度
    private TextView tv_time;  //  时间
    private TextView tv_time_lable; //时间标签
    private LinearLayout lin_time; //时间标签
    private EditText et_interval; // 间隔

    private final String ACTION_FORM_KEY = "actionFrom"; // 动作形态的Key
    private final String ACTION_STRENGTH_KEY = "actionStrength"; // 动作强度的Key
    private List<String> actionFormList = new ArrayList<>(); // 动作形态
    private List<String> actionStrengthList = new ArrayList<>(); // 动作强度
    private BottomSheetDialogFragmentLesson bottomSheetDialogFragmentLesson;
    private android.support.v4.app.FragmentManager fragmentManager;

    public OpenLessonNewView(Context context) {
        super(context);
        this.mContext = context;
    }

    public OpenLessonNewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public OpenLessonNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void initActionRecord(PrivateLessonRecordBean privateLessonRecordBean, int itemPosition, OpenLessonNewActivity openLessonNewActivity) {
        this.itemPosition = itemPosition;
        this.openLessonNewActivity = openLessonNewActivity;
        this.privateLessonRecordBean = privateLessonRecordBean;
        this.fragmentManager = openLessonNewActivity.getSupportFragmentManager();

        actionFormList.add("标准");
        actionFormList.add("非标准");

        actionStrengthList.add("弱");
        actionStrengthList.add("中");
        actionStrengthList.add("强");

        initView();
        addActionContent();
        addBodyData();
    }


    private void initView() {

        LinearLayout linActionContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_open_lesson, null);
        rel_action_header = linActionContainer.findViewById(R.id.rel_action_header);
        lin_body_header = linActionContainer.findViewById(R.id.lin_body_header);
        lin_record_table = linActionContainer.findViewById(R.id.lin_record_table);
        lin_body_container = linActionContainer.findViewById(R.id.lin_body_container);
        lin_body_container.setVisibility(View.GONE);

        tv_action_title = linActionContainer.findViewById(R.id.tv_action_title);
        tv_action_record_finish = linActionContainer.findViewById(R.id.tv_action_record_finish);
        tv_action_record_request = linActionContainer.findViewById(R.id.tv_action_record_request);
        tv_action_degree = linActionContainer.findViewById(R.id.tv_action_degree);
        tv_action_name = linActionContainer.findViewById(R.id.tv_action_name);
        tv_action_limit = linActionContainer.findViewById(R.id.tv_action_limit);
        tv_action_qixie = linActionContainer.findViewById(R.id.tv_action_qixie);
        tv_rank = linActionContainer.findViewById(R.id.tv_rank);

        tv_actionForm = linActionContainer.findViewById(R.id.tv_actionForm);
        tv_actionStrength = linActionContainer.findViewById(R.id.tv_actionStrength);
        tv_time = linActionContainer.findViewById(R.id.tv_time);
        tv_time_lable = linActionContainer.findViewById(R.id.tv_time_lable);
        lin_time = linActionContainer.findViewById(R.id.lin_time);
        et_interval = linActionContainer.findViewById(R.id.et_interval);

        rel_action_header.setOnClickListener(new OnClickListener() {  //头部点击
            @Override
            public void onClick(View v) {
                openLessonNewActivity.notifyClickHeader(itemPosition);
            }
        });
        lin_body_header.setOnClickListener(new OnClickListener() { //计时头部点击
            @Override
            public void onClick(View v) {
                lin_record_table.setVisibility((lin_record_table.getVisibility() == VISIBLE) ? View.GONE : View.VISIBLE);
            }
        });
        tv_actionForm.setOnClickListener(new OnClickListener() { // 动作形态点击
            @Override
            public void onClick(View v) {

                int punchStatus = openLessonNewActivity.getPunchStatus();
                if (punchStatus == 0) { //0:未打卡 1:正在上课 2:下课已打卡
                    Toast.makeText(openLessonNewActivity, "未打卡不能操作", Toast.LENGTH_SHORT).show();
                } else if (punchStatus == 1) {

                    if (itemPosition == 0) {
                        manualPickedView(actionFormList, "标准", tv_actionForm, ACTION_FORM_KEY);
                    } else {
                        if (privateLessonRecordBean.isStartClock()) {
                            manualPickedView(actionFormList, "标准", tv_actionForm, ACTION_FORM_KEY);
                        } else {
                            Toast.makeText(mContext, "不能跨组锻炼...", Toast.LENGTH_SHORT).show();
                        }
                    }


                } else if (punchStatus == 2) {
                    Toast.makeText(openLessonNewActivity, "已完成不能操作", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv_actionStrength.setOnClickListener(new OnClickListener() { //动作强度点击
            @Override
            public void onClick(View v) {
                int punchStatus = openLessonNewActivity.getPunchStatus();
                if (punchStatus == 0) { //0:未打卡 1:正在上课 2:下课已打卡
                    Toast.makeText(openLessonNewActivity, "未打卡不能操作", Toast.LENGTH_SHORT).show();
                } else if (punchStatus == 1) {

                    if (itemPosition == 0) {
                        manualPickedView(actionStrengthList, "中", tv_actionStrength, ACTION_STRENGTH_KEY);
                    } else {
                        if (privateLessonRecordBean.isStartClock()) {
                            manualPickedView(actionStrengthList, "中", tv_actionStrength, ACTION_STRENGTH_KEY);
                        } else {
                            Toast.makeText(mContext, "不能跨组锻炼...", Toast.LENGTH_SHORT).show();
                        }
                    }


                } else if (punchStatus == 2) {
                    Toast.makeText(openLessonNewActivity, "已完成不能操作", Toast.LENGTH_SHORT).show();
                }
//                manualPickedView(actionStrengthList, "中", tv_actionStrength, ACTION_STRENGTH_KEY);
            }
        });
        lin_time.setOnClickListener(new OnClickListener() { // 计时点击
            @Override
            public void onClick(View v) {

                int punchStatus = openLessonNewActivity.getPunchStatus();
                if (punchStatus == 0) { //0:未打卡 1:正在上课 2:下课已打卡
                    Toast.makeText(openLessonNewActivity, "未打卡不能操作", Toast.LENGTH_SHORT).show();
                } else if (punchStatus == 1) {
//                    Toast.makeText(openLessonNewActivity,"未打卡不能操作",Toast.LENGTH_SHORT).show();

                    if (itemPosition == 0) {
                        showClockView(tv_time);
                    } else {
                        if (privateLessonRecordBean.isStartClock()) {
                            showClockView(tv_time);
                        } else {
                            Toast.makeText(mContext, "不能跨组锻炼...", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (punchStatus == 2) {
                    Toast.makeText(openLessonNewActivity, "已完成不能操作", Toast.LENGTH_SHORT).show();
                }

            }
        });

        addView(linActionContainer);

    }

    /**
     * 添加Item条目头部和动作内容
     */
    private void addActionContent() {
        tv_action_title.setText(privateLessonRecordBean.getMoName());
        tv_action_degree.setText(privateLessonRecordBean.getMoDifficulty());
        tv_action_name.setText(privateLessonRecordBean.getMoName());
        tv_action_limit.setText(privateLessonRecordBean.getBuildTime());
        tv_action_qixie.setText(privateLessonRecordBean.getMoApplianceName());
        tv_rank.setText((itemPosition + 1) + "");
    }


    /**
     * 添加Body记录表
     */
    private void addBodyData() {
//        tv_actionForm.setText(actionFormList.get(privateLessonRecordBean.getActionForm()));

        tv_actionForm.setText((privateLessonRecordBean.getActionForm() == null) ? "" : actionFormList.get(privateLessonRecordBean.getActionForm() - 1));
//        tv_actionStrength.setText(actionStrengthList.get(privateLessonRecordBean.getActionStrength()));
        tv_actionStrength.setText((privateLessonRecordBean.getActionStrength() == null) ? "" : actionStrengthList.get(privateLessonRecordBean.getActionStrength() - 1));
        et_interval.setText((privateLessonRecordBean.getInterval() == null) ? "" : privateLessonRecordBean.getInterval() + "");
        tv_time.setText(privateLessonRecordBean.getTime());

        setTimeVisible(!TextUtils.isEmpty(privateLessonRecordBean.getId()));

    }


    /**
     * 强度和动作形态弹窗
     *
     * @param opts
     * @param defaultValue
     * @param tv_name
     */
    private void manualPickedView(List<String> opts, String defaultValue, TextView tv_name, String key) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String value = opts.get(options1);
                tv_name.setText(value);
                if (key.equals(ACTION_FORM_KEY)) {
                    privateLessonRecordBean.setActionForm(options1 + 1);
                } else if (key.equals(ACTION_STRENGTH_KEY)) {
                    privateLessonRecordBean.setActionStrength(options1 + 1);
                }
                openLessonNewActivity.setOpenLessonList(itemPosition, privateLessonRecordBean);
            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.show();
    }

    /**
     * 时间 计时弹窗
     */
    public void showClockView(TextView tv) {
        if (bottomSheetDialogFragmentLesson == null) {
            bottomSheetDialogFragmentLesson = new BottomSheetDialogFragmentLesson();
        }
        bottomSheetDialogFragmentLesson.show(fragmentManager, "BottomSheetDialogFragmentLesson");
        bottomSheetDialogFragmentLesson.setResultChronometerListener(new BottomSheetDialogFragmentLesson.ResultChronometerListener() {
            @Override
            public void getTimes(long time) {
                String secondTime = "" + time / 1000.00f + "s";
                tv.setText(secondTime);
                setTimeVisible(true);
                privateLessonRecordBean.setStartClock(true);
                privateLessonRecordBean.setTime(secondTime + "");
                openLessonNewActivity.notifyAllLesson(itemPosition);
                openLessonNewActivity.setOpenLessonList(itemPosition, privateLessonRecordBean);
            }
        });
    }

    private void setTimeVisible(boolean showFlag) {
        tv_time.setVisibility(showFlag ? View.VISIBLE : View.GONE);
        tv_time_lable.setVisibility(showFlag ? View.GONE : View.VISIBLE);
        tv_action_record_finish.setVisibility(showFlag ? View.VISIBLE : View.GONE);
        tv_action_record_request.setVisibility(showFlag ? View.GONE : View.VISIBLE);
    }

    @Override
    public void update(Object data) {

        Map<String, String> map = (Map<String, String>) data;
        int type = Integer.valueOf(map.get("type"));
        switch (type) {  //该通知是需要点击了可以计时的时候，才能触发

            case 7: //点击头部，控制头部的显示和隐藏
                int itemClickLocation = Integer.valueOf(map.get("itemPosition"));
                if (itemClickLocation == itemPosition) {
                    lin_body_container.setVisibility((lin_body_container.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
                } else {
                    lin_body_container.setVisibility(View.GONE);
                }
                break;
            case 8: // 计时通知所有的Item
                int locationItemPosition = Integer.valueOf(map.get("itemPosition"));
                if (itemPosition == (locationItemPosition + 1)) {
                    privateLessonRecordBean.setStartClock(true);
                } else {
                    privateLessonRecordBean.setStartClock(false);
                }
                break;
        }

    }
}
