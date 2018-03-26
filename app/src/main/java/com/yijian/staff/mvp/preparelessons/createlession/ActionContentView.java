package com.yijian.staff.mvp.preparelessons.createlession;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.preparelessons.CreatePrivateLessionActivity;
import com.yijian.staff.util.DensityUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yangk on 2018/3/21.
 */

public class ActionContentView extends LinearLayout implements Observer {

    private Context mContext;
    private boolean isInit = true;
    /********************** 动作难易程度 ************************/
    private List<TextView> viewActionList = new ArrayList<TextView>(); //所有难易程度View的集合
    private TextView resCheckTxtView = null; //当前选中难以程度的View


    private Map<String, List<SubActionBean>> mapList = new HashMap<String, List<SubActionBean>>();
    private LinearLayout linHeaderContainer; // 头部布局容器
    private LinearLayout linSingleActionContain; // 个动作内容布局容器
    private LinearLayout linOprationButtonContain; // 添加动作内容操作按钮布局容器
    private TextView tv_action_title; //头部标题
    private CheckBox ck_group; //组复选框
    private int itemPosition; //位置标题
    private Map<String, List<Integer>> subMapList = new HashMap<String, List<Integer>>();
    private boolean isShowCheck;
    private CreatePrivateLessionActivity createPrivateLessionActivity;
    private ActionBean actionBean;
    private boolean isShowHeader = true;


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

    public void initAction(List<String> actionArray, ActionBean actionBean, int itemPosition, CreatePrivateLessionActivity createPrivateLessionActivity) {
        if (isInit) {
            this.itemPosition = itemPosition;
            this.actionBean = actionBean;
            this.createPrivateLessionActivity = createPrivateLessionActivity;
            addLayoutContainer(actionArray);
            for (int i = 0; i < actionArray.size(); i++) {
                List<SubActionBean> actionBeanList = new ArrayList<SubActionBean>();
                mapList.put(actionArray.get(i), actionBeanList);
                List<Integer> actionCheckList = new ArrayList<Integer>();
                subMapList.put(actionArray.get(i), actionCheckList);
            }
            List<SubActionBean> subActionBeanList = actionBean.getSubActionBeans();
            mapList.put(actionBean.getDegree(), subActionBeanList);
            setTitle();
            if (TextUtils.isEmpty(actionBean.getDegree())) {
                actionOpration(resCheckTxtView);
            } else {
                for (int i = 0; i < viewActionList.size(); i++) {
                    if ((viewActionList.get(i).getText()).equals(actionBean.getDegree())) {
                        resCheckTxtView = viewActionList.get(i);
                        actionOpration(viewActionList.get(i));
                        break;
                    }
                }
            }

            isInit = false;
        }
        addActionContentView();
        linSingleActionContain.setVisibility(View.GONE);
        linOprationButtonContain.setVisibility(View.GONE);
        setHeaderLayout();
    }

    /************************************START 动作难易程度操作 *********************************************/
    public void addHeaderView(List<String> actionArray) {
        linHeaderContainer = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_action, null);
        linHeaderContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createPrivateLessionActivity.notifyClickHeader(itemPosition);
            }
        });
        ck_group = linHeaderContainer.findViewById(R.id.ck_group);
        ck_group.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> subItemList = subMapList.get(resCheckTxtView.getText());
                if (isChecked) {
                    actionBean.setCheckGroup(true);
                    List<SubActionBean> subActionBeanList = mapList.get(resCheckTxtView.getText());
                    subItemList.clear();
                    for (int i = 0; i < subActionBeanList.size(); i++) {
                        subItemList.add(i);
                    }
                } else {
                    actionBean.setCheckGroup(false);
                    subItemList.clear();
                }
                subMapList.put(resCheckTxtView.getText().toString(), subItemList);
                createPrivateLessionActivity.setActionBeanList(itemPosition, actionBean);
            }
        });
        tv_action_title = linHeaderContainer.findViewById(R.id.tv_title);
        tv_action_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv_action_title.setTextColor(Color.parseColor("#333333"));
        LinearLayout lin_action_degree = linHeaderContainer.findViewById(R.id.lin_action_degree);
        for (int i = 0; i < actionArray.size(); i++) {
            TextView tv_degree = new TextView(mContext);
            tv_degree.setText(actionArray.get(i));
            tv_degree.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            lin_action_degree.addView(tv_degree);
            LayoutParams titleLP = (LayoutParams) tv_degree.getLayoutParams();
            titleLP.setMargins(DensityUtil.dip2px(mContext, 7), 0, 0, 0);
            tv_degree.setLayoutParams(titleLP);
            tv_degree.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionOpration((TextView) v);
                }
            });
            viewActionList.add(tv_degree);
            if (i == 0) {
                resCheckTxtView = tv_degree;
            }
        }
        addView(linHeaderContainer);
    }

    public void actionOpration(TextView txtView) {
        /********************* 设置字体颜色 **********************/
        for (int i = 0; i < viewActionList.size(); i++) {
            viewActionList.get(i).setTextColor(Color.parseColor("#666666"));
            viewActionList.get(i).setBackgroundResource(R.drawable.shape_action_half_nor);
            if (txtView == viewActionList.get(i)) {
                resCheckTxtView = txtView;
                txtView.setTextColor(Color.parseColor("#ffffff"));
                txtView.setBackgroundResource(R.drawable.shape_action_half_sel);
            }
        }
        resCheckTxtView = txtView;

        actionBean.setDegree(resCheckTxtView.getText().toString());
        createPrivateLessionActivity.setActionBeanList(itemPosition,actionBean);

        addActionContentView(); //选中难易程度之后，更新数据集合

        setTitle();


    }

    private void setTitle() {
        tv_action_title.setText(numToChinesse(itemPosition));
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

    /************************************END 动作难易程度操作 *********************************************/


    /************************************START 动作内容 *********************************************/


    public void addActionContentView() {

        if (resCheckTxtView != null) {

            List<SubActionBean> subActionBeanList = mapList.get(resCheckTxtView.getText());
            List<Integer> subCheckList = subMapList.get(resCheckTxtView.getText());

            linSingleActionContain.removeAllViews();

            for (int i = 0; i < subActionBeanList.size(); i++) {

                SubActionBean subActionBean = subActionBeanList.get(i);
                List<SubActionBean.SubChildBean> subChildBeans = subActionBean.getSubChildBeanList();
                LinearLayout subLinContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_actions, null);

                CheckBox ck_rank = subLinContain.findViewById(R.id.ck_rank);
                ck_rank.setVisibility(isShowCheck ? View.VISIBLE : View.GONE);
                if (subCheckList.contains(i)) {
                    ck_rank.setChecked(true);
                }
                ck_rank.setTag(resCheckTxtView.getText() + "_" + i);
                ck_rank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String tag = (String) buttonView.getTag();
                        String[] tagArray = tag.split("_");
                        List<Integer> subItemList = subMapList.get(tagArray[0]);
                        if (isChecked) {
                            subItemList.add(Integer.valueOf(tagArray[1]));
                        } else {
                            subItemList.remove(Integer.valueOf(tagArray[1]));
                        }
                        subMapList.put(tagArray[0], subItemList);

                        SubActionBean checkSubActionBean = actionBean.getSubActionBeans().get(Integer.valueOf(tagArray[1]));
                        checkSubActionBean.setCheckChild(isChecked);
                        createPrivateLessionActivity.setActionBeanList(itemPosition, actionBean);
                    }
                });

                TextView tv_action_rank = subLinContain.findViewById(R.id.tv_action_rank);
                tv_action_rank.setText("第" + (i + 1) + "个：");
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
                linSingleActionContain.addView(subLinContain);
            }

        }

    }

    /************************************END 动作内容 *********************************************/

    private void addLayoutContainer(List<String> actionArray) {
        //添加头部
        addHeaderView(actionArray);

        //添加动作内容
        linSingleActionContain = new LinearLayout(mContext);
        linSingleActionContain.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        linSingleActionContain.setOrientation(LinearLayout.VERTICAL);
        addView(linSingleActionContain);

        //添加操作按钮
        linOprationButtonContain = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_add_action_bottom, null);

        LinearLayout lin_add_group = linOprationButtonContain.findViewById(R.id.lin_add_group);
        lin_add_group.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createPrivateLessionActivity.addActionBeanList();
            }
        });
        LinearLayout lin_add_single = linOprationButtonContain.findViewById(R.id.lin_add_single);
        lin_add_single.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SubActionBean> subActionBeanList = mapList.get(resCheckTxtView.getText());


                SubActionBean subActionBean_1_1 = new SubActionBean();
                List<SubActionBean.SubChildBean> subChildBeanList_1_1 = new ArrayList<SubActionBean.SubChildBean>();
                subChildBeanList_1_1.add(new SubActionBean.SubChildBean("平板支撑", "2组/每组2分钟"));
                subChildBeanList_1_1.add(new SubActionBean.SubChildBean("需要器械", "无"));
                subActionBean_1_1.setSubChildBeanList(subChildBeanList_1_1);
                subActionBeanList.add(subActionBean_1_1);

                addActionContentView();
            }
        });

        addView(linOprationButtonContain);
    }


    @Override
    public void update(Object data) {  // data: 0 编辑，1  删除 ，2  确定
        Toast.makeText(mContext, "观察者做了 " + data + itemPosition, Toast.LENGTH_SHORT).show();
        Map<String, String> map = (Map<String, String>) data;
        int type = Integer.valueOf(map.get("type"));
        switch (type) {
            case 0: //编辑
                isShowCheck = true;
                ck_group.setVisibility(View.VISIBLE);
                linOprationButtonContain.setVisibility(View.GONE);
                break;

            case 1: //删除
                /*isShowCheck = true;
                List<Integer> subItemList = subMapList.get(resCheckTxtView.getText());
                List<List<SubActionBean>> subActionBeanList = mapList.get(resCheckTxtView.getText());
                List<List<SubActionBean>> tempSubActionBeanList = new ArrayList<List<SubActionBean>>();
                for (int i = 0; i < subItemList.size(); i++) {
                    tempSubActionBeanList.add(subActionBeanList.get(subItemList.get(i)));
                }

                for (int i = 0; i < subItemList.size(); i++) {
                    subActionBeanList.remove(tempSubActionBeanList.get(i));

                }

                mapList.put(resCheckTxtView.getText().toString(), subActionBeanList);
                subItemList.clear();
                subMapList.put(resCheckTxtView.getText().toString(), subItemList);
                actionBean.setDegree(resCheckTxtView.getText().toString());
                actionBean.setSubActionBeans(subActionBeanList); */
                break;

            case 2: //确定
                isShowCheck = false;
                ck_group.setVisibility(View.GONE);
                break;

            case 3: //点击头部之后分发通知给给各组 控制显示隐藏
                int itemLocation = Integer.valueOf(map.get("itemPosition"));

                if (itemLocation == itemPosition) {
                    linSingleActionContain.setVisibility(isShowHeader ? View.VISIBLE : View.GONE);
                    linOprationButtonContain.setVisibility(isShowHeader ? View.VISIBLE : View.GONE);
                    isShowHeader = !isShowHeader;
                } else {
                    linSingleActionContain.setVisibility(View.GONE);
                    linOprationButtonContain.setVisibility(View.GONE);
                }
                setHeaderLayout();

                break;
            case 4: //点击 添加组动作之后，分发通知给各组 控制显示隐藏
                int sumItemSize = Integer.valueOf(map.get("sumItemSize"));
                linSingleActionContain.setVisibility((itemPosition == (sumItemSize - 1)) ? View.VISIBLE : View.GONE);
                linOprationButtonContain.setVisibility((itemPosition == (sumItemSize - 1)) ? View.VISIBLE : View.GONE);
                setHeaderLayout();
                break;
        }
        addActionContentView();
    }

    private void setHeaderLayout() {
        for (TextView txtView : viewActionList) {
            if (linSingleActionContain.getVisibility() == View.GONE) { //通过分组内容的显示和隐藏控制头部的按钮显示布局
                txtView.setVisibility(txtView == resCheckTxtView ? View.VISIBLE : View.GONE);
            } else {
                txtView.setVisibility(View.VISIBLE);
            }
        }

    }

}
