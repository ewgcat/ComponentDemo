package com.yijian.staff.mvp.setclass;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.CreatePrivateLessionActivity;
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.coach.preparelessons.createlession.Observer;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.mvp.setclass.bean.TypeOfActionItem;
import com.yijian.staff.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/29.
 */

public class OpenLessonNewView extends LinearLayout implements Observer {

    private Context mContext;
    private int itemPosition;
    private BottomSheetDialogFragmentLesson bottomSheetDialogFragmentLesson;
    private android.support.v4.app.FragmentManager fragmentManager;
    private OpenLessonNewActivity openLessonNewActivity;
    private OpenLessonNewBean openLessonNewBean;
    private List<EditText> editList = new ArrayList<EditText>();
    private List<LinearLayout> oprationBodyList = new ArrayList<LinearLayout>();


    /*************** 头部 *******************/
    private RelativeLayout rel_header; //头部容器
    private TextView tv_group; // 头部组数标题
    private TextView tv_degree; // 头部难易程度

    /***************  Body *************************/
    private LinearLayout lin_body;  // Body的容器


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

    public void initView(OpenLessonNewBean openLessonNewBean, int itemPosition, OpenLessonNewActivity openLessonNewActivity) {
        this.openLessonNewBean = openLessonNewBean;
        this.openLessonNewActivity = openLessonNewActivity;
        this.itemPosition = itemPosition;
        this.fragmentManager = openLessonNewActivity.getSupportFragmentManager();
        //加载布局
        addGroupLayout();
        //设置头部
        setTitle(openLessonNewBean.getDegree());
        //加载数据
        addBodyData(openLessonNewBean);


    }

    /**
     * 设置标题
     */
    private void setTitle(String degree) {
        tv_group.setText(numToChinesse(itemPosition));
        tv_degree.setText(degree);
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

    /**
     * 添加布局容器
     */
    private void addGroupLayout() {
        //加载主布局
        LinearLayout linTotalContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_open_lesson, null);
        /*****************  初始化子布局 **************************/

        /*****************START  初始化头部 *********************/
        rel_header = linTotalContainer.findViewById(R.id.rel_header);
        rel_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openLessonNewActivity.notifyClickHeader(itemPosition);
            }
        });
        tv_group = linTotalContainer.findViewById(R.id.tv_group);
        tv_degree = linTotalContainer.findViewById(R.id.tv_degree);
        /*****************END  初始化头部 *********************/

        /*****************START  初始化Body *********************/
        lin_body = linTotalContainer.findViewById(R.id.lin_body);
        lin_body.setVisibility(View.GONE);
        /*****************END  初始化Body *********************/
        this.addView(linTotalContainer);
    }

    /**
     * 添加动作内容的Body
     *
     * @param openLessonNewBean
     */
    private void addBodyData(OpenLessonNewBean openLessonNewBean) {
        List<OpenLessonNewBean.SubOpenLessonNewBean> openLessonNewBeans = openLessonNewBean.getSubOpenLessonNewBeans();
        for (int i = 0; i < openLessonNewBeans.size(); i++) {

            OpenLessonNewBean.SubOpenLessonNewBean subOpenLessonNewBean = openLessonNewBeans.get(i);
            //加载个动作内容布局容器
            /********************* 设置左边的标题 ************************/
            LinearLayout subBodyContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_body_action, null);
            LinearLayout lin_body_header = subBodyContain.findViewById(R.id.lin_body_header);
            lin_body_header.setTag(i);
            lin_body_header.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tag = (Integer) v.getTag();
                    showOpration(tag);
                }
            });
            TextView tv_single_serialize = subBodyContain.findViewById(R.id.tv_single_serialize);
            tv_single_serialize.setText("第" + (i + 1) + "个:");

            /******************* 添加具体个动作内容 *********************/
            LinearLayout lin_action_content = subBodyContain.findViewById(R.id.lin_action_content);
            Map<String, String> actionMap = subOpenLessonNewBean.getActionMap();

            //遍历个动作集合
            for (Map.Entry<String, String> actionEntry : actionMap.entrySet()) {
                String key = actionEntry.getKey();
                String value = actionEntry.getValue();
                LinearLayout contentLin = new LinearLayout(mContext);
                contentLin.setOrientation(LinearLayout.HORIZONTAL);
                contentLin.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                contentLin.setPadding(0, DensityUtil.dip2px(mContext, 15), 0, 0);

                TextView textView1 = new TextView(mContext);
                LayoutParams et_lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                et_lp.weight = 1;
                textView1.setLayoutParams(et_lp);
                textView1.setText(key);
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView1.setTextColor(Color.parseColor("#666666"));
                textView1.setPadding(DensityUtil.dip2px(mContext, 15), 0, 0, 0);


                TextView textView2 = new TextView(mContext);
                textView2.setGravity(Gravity.LEFT);
                LayoutParams et_lp2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                et_lp2.weight = 1;
                textView2.setLayoutParams(et_lp2);
                textView2.setText(value);
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView2.setTextColor(Color.parseColor("#666666"));
                textView2.setGravity(Gravity.RIGHT);

                contentLin.addView(textView1);
                contentLin.addView(textView2);
                lin_action_content.addView(contentLin);
            }

            /**************  添加训练的操作项目  ****************/
            LinearLayout lin_opration_content = subBodyContain.findViewById(R.id.lin_opration_content);
            Map<String, String> actionOprationMap = subOpenLessonNewBean.getActionOprationMap();
            int oprationSize = 1;
            //遍历个动作操作项目集合
            for (Map.Entry<String, String> actionOpration : actionOprationMap.entrySet()) {
                String key = actionOpration.getKey();
                String value = actionOpration.getValue();
                LinearLayout linOpration = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_opration_content, null);
                View view_lin_opration = linOpration.findViewById(R.id.view_lin_opration);
                TextView tv_opration_label = linOpration.findViewById(R.id.tv_opration_label);
                TextView tv_opration_content = linOpration.findViewById(R.id.tv_opration_content);
                EditText et_opration_content = linOpration.findViewById(R.id.et_opration_content);
                et_opration_content.setHint("请填写时间间隔");
                et_opration_content.setTag(key + "-" + i);
                editList.add(et_opration_content);

                tv_opration_content.setTag(key + "-" + i);
                tv_opration_content.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv = (TextView) v;
                        String[] tagArray = ((String) tv.getTag()).split("-");
                        String key = tagArray[0];
                        Integer subPosition = Integer.valueOf(tagArray[1]);
                        if ("动作形态".equals(key)) {
                            List<String> actionForm = new ArrayList<String>();
                            actionForm.add("标准");
                            actionForm.add("非标准");
                            manualPickedView(actionForm, "标准", tv, subPosition, key);
                        } else if ("强度".equals(key)) {
                            List<String> actionForm = new ArrayList<String>();
                            actionForm.add("弱");
                            actionForm.add("中");
                            actionForm.add("强");
                            manualPickedView(actionForm, "中", tv, subPosition, key);
                        } else if ("时间".equals(key)) {
                            OpenLessonNewBean.SubOpenLessonNewBean subLesson = openLessonNewBean.getSubOpenLessonNewBeans().get(subPosition);
                            if (itemPosition == 0 && subPosition == 0) {
                                showClockView(tv, subPosition, key);
                            } else {
                                if (subLesson.isStartClolck()) {
                                    showClockView(tv, subPosition, key);
                                } else {
                                    Toast.makeText(mContext, "不能跨组锻炼...", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                    }
                });

                tv_opration_label.setText(key);
                tv_opration_content.setText(value);
                lin_opration_content.addView(linOpration);
                if (oprationSize >= actionOprationMap.size()) {
                    view_lin_opration.setVisibility(View.GONE);
                }
                et_opration_content.setVisibility(("间隔".equals(key)) ? View.VISIBLE : View.GONE);
                tv_opration_content.setVisibility(("间隔".equals(key)) ? View.GONE : View.VISIBLE);
                oprationSize++;

            }
            lin_opration_content.setVisibility(View.GONE);
            lin_opration_content.setTag(i);
            oprationBodyList.add(lin_opration_content);
            lin_body.addView(subBodyContain);
        }
    }

    /**
     * 强度和动作形态弹窗
     *
     * @param opts
     * @param defaultValue
     * @param name
     */
    private void manualPickedView(List<String> opts, String defaultValue, TextView name, int subItemLocation, String key) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                name.setText(opts.get(options1));
                openLessonNewBean.getSubOpenLessonNewBeans().get(subItemLocation).getActionOprationMap().put(key, opts.get(options1));
                openLessonNewActivity.setOpenLessonList(itemPosition, openLessonNewBean);
            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.show();
    }

    /**
     * 时间 计时弹窗
     */
    public void showClockView(TextView tv, int subPosition, String key) {
        if (bottomSheetDialogFragmentLesson == null) {
            bottomSheetDialogFragmentLesson = new BottomSheetDialogFragmentLesson();
        }
        bottomSheetDialogFragmentLesson.show(fragmentManager, "BottomSheetDialogFragmentLesson");
        bottomSheetDialogFragmentLesson.setResultChronometerListener(new BottomSheetDialogFragmentLesson.ResultChronometerListener() {
            @Override
            public void getTimes(long time) {
                String secondTime = "" + time / 1000.00f + "s";
                tv.setText(secondTime);
                OpenLessonNewBean.SubOpenLessonNewBean subLesson = openLessonNewBean.getSubOpenLessonNewBeans().get(subPosition);
                subLesson.setStartClolck(true);
                openLessonNewActivity.notifyAllLesson(itemPosition, subPosition, openLessonNewBean.getSubOpenLessonNewBeans().size());
                subLesson.getActionOprationMap().put(key, secondTime);
                openLessonNewActivity.setOpenLessonList(itemPosition, openLessonNewBean);
            }
        });
    }

    private void showOpration(int position){
        for(LinearLayout lin_opration_content : oprationBodyList){
            Integer itemPosition = (Integer) lin_opration_content.getTag();
            if(position == itemPosition){
                lin_opration_content.setVisibility((lin_opration_content.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
            }else{
                lin_opration_content.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void update(Object data) {
        Map<String, String> map = (Map<String, String>) data;
        int type = Integer.valueOf(map.get("type"));
        switch (type) {  //该通知是需要点击了可以计时的时候，才能触发
            case 0:
                int itemLocation = Integer.valueOf(map.get("itemPosition"));
                int subItemLocation = Integer.valueOf(map.get("subPosition"));
                int subSize = Integer.valueOf(map.get("subSize"));
                if (itemLocation == itemPosition) {
                    if (subItemLocation < (subSize - 1)) {
                        openLessonNewBean.getSubOpenLessonNewBeans().get((subItemLocation + 1)).setStartClolck(true);
                    } else {
                        //如果>= 不做任何处理
                    }
                } else if (itemLocation == (itemPosition - 1)) {
                    if (subItemLocation == (subSize - 1)) {
                        openLessonNewBean.getSubOpenLessonNewBeans().get(0).setStartClolck(true);
                    }
                }
                break;
            case 1:  // 提交时间间隔的数据到Activity
                for (EditText et : editList) {
                    String[] tagArray = ((String) et.getTag()).split("-");
                    String key = tagArray[0];
                    Integer subPosition = Integer.valueOf(tagArray[1]);
                    openLessonNewBean.getSubOpenLessonNewBeans().get(subPosition).getActionOprationMap().put(key, et.getText().toString());
                    openLessonNewActivity.setOpenLessonList(itemPosition, openLessonNewBean);
                }
                break;
            case 2: //点击头部，控制头部的显示和隐藏
                int itemClickLocation = Integer.valueOf(map.get("itemPosition"));
                if (itemClickLocation == itemPosition) {
                    lin_body.setVisibility((lin_body.getVisibility()==View.GONE)?View.VISIBLE:View.GONE);
                }else{
                    lin_body.setVisibility(View.GONE);
                }
                break;
        }
    }
}
