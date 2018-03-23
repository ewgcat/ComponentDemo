package com.yijian.staff.mvp.preparelessons;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.preparelessons.createlession.ActionBean;
import com.yijian.staff.mvp.preparelessons.createlession.ActionContentView;
import com.yijian.staff.mvp.preparelessons.createlession.ActionViewAdapter;
import com.yijian.staff.mvp.preparelessons.createlession.MyActionView;
import com.yijian.staff.mvp.preparelessons.createlession.MyDepartView;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePrivateLessionActivity extends AppCompatActivity implements MyDepartView.OnDepartOprationListener{

    @BindView(R.id.view_depart)
    MyDepartView view_depart;
    /*@BindView(R.id.view_action_content)
    ActionContentView view_action_content;
    @BindView(R.id.view_action)
    MyActionView view_action;*/
    @BindView(R.id.lin_depart_opration)
    LinearLayout lin_depart_opration;
    @BindView(R.id.iv_depart_switch)
    ImageView iv_depart_switch;
    /*@BindView(R.id.iv_action_switch)
    ImageView iv_action_switch;*/
    @BindView(R.id.rc_actioin)
    RecyclerView rc_actioin;
    List<String> departArray;
    List<String> actionArray;
    List<TextView> selectionViews; //动作内容选中的项目

    List<List<SubActionBean>> actionBeanList; //动作内容集合
    List<ActionBean> recyclerViewActionBean; //装载RecyclerView的集合
    private ActionViewAdapter actionViewAdapter; //装载RecyclerView的适配器Adapter

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

        /****************** 初始化部位内容选中集合 ********************/
        selectionViews = new ArrayList<TextView>();
        iv_depart_switch.setImageResource(selectionViews.size() == 0 ? R.mipmap.bk_1 : R.mipmap.bk_gouxuan);


        /*********************  添加动作模板数据（简单 中等  困难） ***************************/
        actionBeanList = new ArrayList<List<SubActionBean>>();
        List<SubActionBean> subActionBean = new ArrayList<SubActionBean>();
        subActionBean.add(new SubActionBean("平板支撑", "2组/每组2分钟"));
        subActionBean.add(new SubActionBean("需要器械", "无"));

        List<SubActionBean> subActionBean2 = new ArrayList<SubActionBean>();
        subActionBean2.add(new SubActionBean("仰卧起坐", "2组/每组2分钟"));
        subActionBean2.add(new SubActionBean("需要器械", "有"));
        actionBeanList.add(subActionBean);
        actionBeanList.add(subActionBean2);


        /***********************START 添加组动作数据 *********************************/
        recyclerViewActionBean = new ArrayList<ActionBean>();

        ActionBean actionBean = new ActionBean();
        actionBean.setDegree("简单");
        List<List<SubActionBean>> actionBeanList_1 = new ArrayList<List<SubActionBean>>();
        List<SubActionBean> subActionBean_1 = new ArrayList<SubActionBean>();
        subActionBean_1.add(new SubActionBean("平板支撑", "2组/每组2分钟"));
        subActionBean_1.add(new SubActionBean("需要器械", "无"));
        List<SubActionBean> subActionBean_2 = new ArrayList<SubActionBean>();
        subActionBean_2.add(new SubActionBean("仰卧起坐", "2组/每组2分钟"));
        subActionBean_2.add(new SubActionBean("需要器械", "有"));
        actionBeanList_1.add(subActionBean_1);
        actionBeanList_1.add(subActionBean_2);
        actionBean.setSubActionBeans(actionBeanList_1);

        ActionBean actionBean2 = new ActionBean();
        actionBean2.setDegree("中等");
        List<List<SubActionBean>> actionBeanList_2 = new ArrayList<List<SubActionBean>>();
        List<SubActionBean> subActionBean_2_1 = new ArrayList<SubActionBean>();
        subActionBean_2_1.add(new SubActionBean("平板支撑", "2组/每组2分钟"));
        subActionBean_2_1.add(new SubActionBean("需要器械", "无"));
        List<SubActionBean> subActionBean_2_2 = new ArrayList<SubActionBean>();
        subActionBean_2_2.add(new SubActionBean("仰卧起坐", "2组/每组2分钟"));
        subActionBean_2_2.add(new SubActionBean("需要器械", "有"));
        actionBeanList_2.add(subActionBean_2_1);
        actionBeanList_2.add(subActionBean_2_2);
        actionBean2.setSubActionBeans(actionBeanList_2);

        ActionBean actionBean3 = new ActionBean();


        recyclerViewActionBean.add(actionBean);
        recyclerViewActionBean.add(actionBean2);
        recyclerViewActionBean.add(actionBean3);

        /***********************END 添加组动作数据 *********************************/



        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rc_actioin.setLayoutManager(layoutmanager);
        actionViewAdapter = new ActionViewAdapter(recyclerViewActionBean,actionArray);
        rc_actioin.setAdapter(actionViewAdapter);
    }


    private void initView() {
        view_depart.addLineView(departArray, this);
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

    @OnClick({R.id.btn_depart_sure, R.id.lin_depart_header})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_depart_sure:
            case R.id.lin_depart_header:
                lin_depart_opration.setVisibility((lin_depart_opration.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
                break;
        }
    }

    @Override
    public void departOpration(TextView txtView) {
        txtView.setTextColor(selectionViews.contains(txtView) ? Color.parseColor("#666666") : Color.parseColor("#1997f8"));
        if (selectionViews.contains(txtView)) {
            selectionViews.remove(txtView);
        } else {
            selectionViews.add(txtView);
        }
        iv_depart_switch.setImageResource(selectionViews.size() == 0 ? R.mipmap.bk_1 : R.mipmap.bk_gouxuan);
    }

}
