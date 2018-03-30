package com.yijian.staff.mvp.coach.preparelessons.all;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.coach.preparelessons.createlession.SubActionBean;
import com.yijian.staff.mvp.coach.preparelessons.all.PrepareAllActionAdapter;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 所有私教课备课
 */
public class PrepareAllLessonActivity extends AppCompatActivity {

    @BindView(R.id.tv_depart)
    TextView tv_depart;
    @BindView(R.id.rv_action_content)
    RecyclerView rv_action_content;

    List<ActionBean> recyclerViewActionBean; //装载RecyclerView的集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_all_lesson);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }


    private void initTitle() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigation_bar);
        navigationBar.setTitle("所有课程安排", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }


    private void initData() {
        /**************** 初始化部位训练数据 ************************/
        tv_depart.setText("背部");

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
//        ActionBean actionBean3 = new ActionBean();

        //添加到数据源中
        recyclerViewActionBean.add(actionBean);
        recyclerViewActionBean.add(actionBean2);
//        recyclerViewActionBean.add(actionBean3);

        /***********************END 添加组动作数据 *********************************/


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_action_content.setLayoutManager(layoutmanager);
        PrepareAllActionAdapter prepareAllActionAdapter = new PrepareAllActionAdapter(recyclerViewActionBean,this);
        rv_action_content.setAdapter(prepareAllActionAdapter);
    }


}
