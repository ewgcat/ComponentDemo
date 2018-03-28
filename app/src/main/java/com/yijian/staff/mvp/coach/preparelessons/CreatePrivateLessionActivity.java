package com.yijian.staff.mvp.coach.preparelessons;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionViewAdapter;
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.coach.preparelessons.createlession.MyDepartView;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePrivateLessionActivity extends AppCompatActivity implements MyDepartView.OnDepartOprationListener {

    @BindView(R.id.view_depart)
    MyDepartView view_depart;
    @BindView(R.id.rc_actioin)
    RecyclerView rc_actioin;
    @BindView(R.id.lin_opration)
    LinearLayout lin_opration;
    @BindView(R.id.lin_edit)
    LinearLayout lin_edit;


    List<String> departArray;
    List<String> actionArray;

    String selectionPart = "胸部";
    List<ActionBean> recyclerViewActionBean; //装载RecyclerView的集合
    ActionViewAdapter actionViewAdapter; //装载RecyclerView的适配器Adapter
    EditActionObservable editActionObservable = new EditActionObservable();
    boolean isEdit = false; //当前状态是否处于编辑状态
    public static int CLICK_HEADER = 0; //点击头部时的分发
    public static int CLICK_SURE = 1;  //点击确认时的分发


    public boolean isEdit() {
        return isEdit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_private_lession);
        ButterKnife.bind(this);
        initTitle();
        initData();
        initView();
    }

    private void initData() {
        /**************** 初始化部位训练数据 ************************/
        departArray = new ArrayList<String>();
        departArray.add("背部");
        departArray.add("胸部");
        departArray.add("腰部");
        departArray.add("腹部");

        /****************** 初始化动作内容选项数据 **************************/
        actionArray = new ArrayList<String>();
        actionArray.add("简单");
        actionArray.add("中等");
        actionArray.add("困难");

        /***********************START 添加组动作数据 *********************************/
        recyclerViewActionBean = new ArrayList<ActionBean>();

        //第一组
        ActionBean actionBean = new ActionBean();
        actionBean.setDegree("简单");
        List<SubActionBean> actionBeanList_1 = new ArrayList<SubActionBean>();
        SubActionBean subActionBean_1_1 = new SubActionBean();
        SubActionBean subActionBean_1_2 = new SubActionBean();
        SubActionBean subActionBean_1_3 = new SubActionBean();

        List<SubActionBean.SubChildBean> subChildBeanList_1_1 = new ArrayList<SubActionBean.SubChildBean>();
        subChildBeanList_1_1.add(new SubActionBean.SubChildBean("平板支撑", "2组/每组2分钟"));
        subChildBeanList_1_1.add(new SubActionBean.SubChildBean("需要器械", "无"));

        List<SubActionBean.SubChildBean> subChildBeanList_1_2 = new ArrayList<SubActionBean.SubChildBean>();
        subChildBeanList_1_2.add(new SubActionBean.SubChildBean("仰卧起坐", "2组/每组3分钟"));
        subChildBeanList_1_2.add(new SubActionBean.SubChildBean("需要器械", "无"));

        List<SubActionBean.SubChildBean> subChildBeanList_1_3 = new ArrayList<SubActionBean.SubChildBean>();
        subChildBeanList_1_3.add(new SubActionBean.SubChildBean("平板支撑", "2组/每组4分钟"));
        subChildBeanList_1_3.add(new SubActionBean.SubChildBean("需要器械", "无"));

        subActionBean_1_1.setSubChildBeanList(subChildBeanList_1_1);
        subActionBean_1_2.setSubChildBeanList(subChildBeanList_1_2);
        subActionBean_1_3.setSubChildBeanList(subChildBeanList_1_3);

        actionBeanList_1.add(subActionBean_1_1);
        actionBeanList_1.add(subActionBean_1_2);
        actionBeanList_1.add(subActionBean_1_3);


        actionBean.setSubActionBeans(actionBeanList_1);

        //第二组
        ActionBean actionBean2 = new ActionBean();
        actionBean2.setDegree("中等");
        List<SubActionBean> actionBeanList_2 = new ArrayList<SubActionBean>();
        SubActionBean subActionBean_2_1 = new SubActionBean();
        SubActionBean subActionBean_2_2 = new SubActionBean();

        List<SubActionBean.SubChildBean> subChildBeanList_2_1 = new ArrayList<SubActionBean.SubChildBean>();
        subChildBeanList_2_1.add(new SubActionBean.SubChildBean("平板支撑", "2组/每组2分钟"));
        subChildBeanList_2_1.add(new SubActionBean.SubChildBean("需要器械", "无"));

        List<SubActionBean.SubChildBean> subChildBeanList_2_2 = new ArrayList<SubActionBean.SubChildBean>();
        subChildBeanList_2_2.add(new SubActionBean.SubChildBean("仰卧起坐", "2组/每组3分钟"));
        subChildBeanList_2_2.add(new SubActionBean.SubChildBean("需要器械", "无"));

        subActionBean_2_1.setSubChildBeanList(subChildBeanList_2_1);
        subActionBean_2_2.setSubChildBeanList(subChildBeanList_2_2);

        actionBeanList_2.add(subActionBean_2_1);
        actionBeanList_2.add(subActionBean_2_2);


        actionBean2.setSubActionBeans(actionBeanList_2);


        //第三组
        ActionBean actionBean3 = new ActionBean();

        //添加到数据源中
        recyclerViewActionBean.add(actionBean);
        recyclerViewActionBean.add(actionBean2);
        recyclerViewActionBean.add(actionBean3);

        /***********************END 添加组动作数据 *********************************/


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rc_actioin.setLayoutManager(layoutmanager);
        actionViewAdapter = new ActionViewAdapter(recyclerViewActionBean, actionArray, editActionObservable, this);
        rc_actioin.setAdapter(actionViewAdapter);
    }


    private void initView() {
        view_depart.addLineView(selectionPart, departArray, this);
    }


    private void initTitle() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        navigationBar.setTitle("创建私教课备课", "#ffffff");
        navigationBar.getmRightTextView().setText("完成");
        navigationBar.hideBottomLine();
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        navigationBar.setRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void departOpration(TextView txtView) {
    }

    @OnClick({R.id.lin_edit, R.id.lin_delete, R.id.lin_sure})
    public void click(View v) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "0");
        switch (v.getId()) {
            case R.id.lin_edit: //编辑
                isEdit = true;
                setLinOprationVisibility(true);
                map.put("type", "0");
                editActionObservable.notifyObservers(map);

                break;
            case R.id.lin_delete: //删除
                setLinOprationVisibility(true);
                deleteActionList();

                break;
            case R.id.lin_sure: //确定
                isEdit = false;
                setLinOprationVisibility(false);
                map.put("type", "2");
                editActionObservable.notifyObservers(map);

                break;
        }
    }

    /**
     * 设置编辑的显示和隐藏
     *
     * @param isEdit
     */
    private void setLinOprationVisibility(boolean isEdit) {
        lin_edit.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        lin_opration.setVisibility(isEdit ? View.VISIBLE : View.GONE);

    }

    /**
     * 实时更新分组数据
     *
     * @param itemPosition
     * @param actionBean
     */
    public void setActionBeanList(int itemPosition, ActionBean actionBean) {
        recyclerViewActionBean.set(itemPosition, actionBean);
    }

    /**
     * 添加分组
     */
    public void addActionBeanList() {
        //第三组
        ActionBean actionBean4 = new ActionBean();

        //添加到数据源中
        recyclerViewActionBean.add(actionBean4);
        actionViewAdapter = null;
        actionViewAdapter = new ActionViewAdapter(recyclerViewActionBean, actionArray, editActionObservable, this);
        rc_actioin.setAdapter(actionViewAdapter);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "4");
                map.put("sumItemSize", recyclerViewActionBean.size() + "");
                editActionObservable.notifyObservers(map);
            }
        });
    }

    /**
     * 删除分组和个动作
     */
    public void deleteActionList() {
        editActionObservable.deleteObservers();

        List<ActionBean> tempRecyclerViewActionBean = new ArrayList<ActionBean>();
        Collections.addAll(tempRecyclerViewActionBean, new ActionBean[recyclerViewActionBean.size()]);
        Collections.copy(tempRecyclerViewActionBean, recyclerViewActionBean);

        for (ActionBean actionBean : tempRecyclerViewActionBean) {
            int index = recyclerViewActionBean.indexOf(actionBean);
            ActionBean actionBean1 = recyclerViewActionBean.get(index);
            if (actionBean.isCheckGroup()) {
                recyclerViewActionBean.remove(actionBean);
            } else {
                List<SubActionBean> subActionBeanList = actionBean.getSubActionBeans();

                List<SubActionBean> tempSubActionBeanList = new ArrayList<SubActionBean>();
                for (int i = 0; i < subActionBeanList.size(); i++) {
                    SubActionBean subActionBean = subActionBeanList.get(i);
                    if (subActionBean.isCheckChild()) {
                        tempSubActionBeanList.add(subActionBean);
                    }
                }

                List<SubActionBean> rvActionBeanList = actionBean1.getSubActionBeans();
                for (SubActionBean tempSubActionBean : tempSubActionBeanList) {
                    rvActionBeanList.remove(tempSubActionBean);
                }
                recyclerViewActionBean.set(index, actionBean1);
            }
        }

        actionViewAdapter = null;
        actionViewAdapter = new ActionViewAdapter(recyclerViewActionBean, actionArray, editActionObservable, this);
        rc_actioin.setAdapter(actionViewAdapter);
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                Map<String, String> map = new HashMap<String, String>();
                map.put("type", "5");
                editActionObservable.notifyObservers(map);
            }
        });

    }

    /**
     * 点击头部 监听各分组的隐藏和显示
     *
     * @param itemPosition
     */
    public void notifyClickHeader(int itemPosition, int eventType) {
        Map<String, String> map = new HashMap<String, String>();
        if (eventType == CLICK_HEADER) {
            map.put("type", "3");
        } else {
            map.put("type", "6");
        }
        map.put("itemPosition", itemPosition + "");
        editActionObservable.notifyObservers(map);
    }

}
