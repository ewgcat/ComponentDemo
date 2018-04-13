package com.yijian.staff.mvp.coach.preparelessons.createlession;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    @BindView(R.id.lin_add_single)
    LinearLayout lin_add_single;


    List<String> departArray;

    String selectionPart = "胸部";
    List<ActionBean> actionBeanList = new ArrayList<ActionBean>(); //装载RecyclerView的集合
    ActionViewAdapter actionViewAdapter; //装载RecyclerView的适配器Adapter
    EditActionObservable editActionObservable = new EditActionObservable();
    boolean isEdit = false; //当前状态是否处于编辑状态
    public static int CLICK_HEADER = 0; //点击头部时的分发


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
        ActionBean actionBean1 = new ActionBean(1, "简单", "平板支撑", "1组/1次", "无");
        ActionBean actionBean2 = new ActionBean(2, "中等", "平板支撑2", "2组2次", "有");
        ActionBean actionBean3 = new ActionBean(3, "困难", "平板支撑3", "3组/3次", "无");
        actionBeanList.add(actionBean1);
        actionBeanList.add(actionBean2);
        actionBeanList.add(actionBean3);


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rc_actioin.setLayoutManager(layoutmanager);
        actionViewAdapter = new ActionViewAdapter(actionBeanList, editActionObservable, this);
        rc_actioin.setAdapter(actionViewAdapter);
    }


    private void initView() {
        view_depart.addLineView(selectionPart, departArray, this);
    }


    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("创建私教课备课");
        navigationBar2.setmRightTvText("完成");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void departOpration(TextView txtView) {
    }

    @OnClick({R.id.lin_edit, R.id.lin_delete, R.id.lin_sure, R.id.lin_add_single})
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
                deleteAction();
                break;
            case R.id.lin_sure: //确定
                isEdit = false;
                setLinOprationVisibility(false);
                map.put("type", "2");
                editActionObservable.notifyObservers(map);

                break;
            case R.id.lin_add_single: //添加个动作

                List<ActionBean> selectActionBeanList1 = new ArrayList<ActionBean>();
                ActionBean actionBean1 = new ActionBean(0,"简单","俯卧撑","1组/30次","撑炳");
                ActionBean actionBean2 = new ActionBean(1,"简单","俯卧撑","1组/30次","撑炳");
                ActionBean actionBean3 = new ActionBean(2,"简单","俯卧撑","1组/30次","撑炳");
                selectActionBeanList1.add(actionBean1);
                selectActionBeanList1.add(actionBean2);
                selectActionBeanList1.add(actionBean3);

                List<ActionBean> selectActionBeanList2 = new ArrayList<ActionBean>();
                ActionBean actionBean21 = new ActionBean(0,"中等","仰卧起坐","2组/30次","撑炳");
                ActionBean actionBean22 = new ActionBean(1,"中等","仰卧起坐","2组/30次","撑炳");
                ActionBean actionBean23 = new ActionBean(2,"中等","仰卧起坐","2组/30次","撑炳");
                selectActionBeanList2.add(actionBean21);
                selectActionBeanList2.add(actionBean22);
                selectActionBeanList2.add(actionBean23);

                List<ActionBean> selectActionBeanList3 = new ArrayList<ActionBean>();
                ActionBean actionBean31 = new ActionBean(0,"困难","深蹲","2组/30次","无");
                ActionBean actionBean32 = new ActionBean(1,"困难","深蹲","2组/30次","无");
                ActionBean actionBean33 = new ActionBean(2,"困难","深蹲","2组/30次","无");
                selectActionBeanList3.add(actionBean31);
                selectActionBeanList3.add(actionBean32);
                selectActionBeanList3.add(actionBean33);

                List<List<ActionBean>> parentList = new ArrayList<>();
                parentList.add(selectActionBeanList1);
                parentList.add(selectActionBeanList2);
                parentList.add(selectActionBeanList3);

                SelectActionPopwindow selectActionPopwindow = new SelectActionPopwindow(this,parentList);
                selectActionPopwindow.showAtBottom(getWindow().getDecorView());

                break;
        }
    }

    /**
     * 删除分组
     */
    private void deleteAction() {
        editActionObservable.deleteObservers();

        List<ActionBean> tempRecyclerViewActionBean = new ArrayList<ActionBean>();
        Collections.addAll(tempRecyclerViewActionBean, new ActionBean[actionBeanList.size()]);
        Collections.copy(tempRecyclerViewActionBean, actionBeanList);

        for (ActionBean actionBean : tempRecyclerViewActionBean) {
            int index = actionBeanList.indexOf(actionBean);
            ActionBean actionBean1 = actionBeanList.get(index);
            if (actionBean.isCheck()) {
                actionBeanList.remove(actionBean);
            }
        }

        actionViewAdapter = null;
        actionViewAdapter = new ActionViewAdapter(actionBeanList, editActionObservable, this);
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
     * 设置编辑的显示和隐藏
     *
     * @param isEdit
     */
    private void setLinOprationVisibility(boolean isEdit) {
        lin_edit.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        lin_opration.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        lin_add_single.setVisibility(isEdit ? View.GONE : View.VISIBLE);

    }

    /**
     * 实时更新分组数据
     *
     * @param itemPosition
     * @param actionBean
     */
    public void setActionBeanList(int itemPosition, ActionBean actionBean) {
        actionBeanList.set(itemPosition, actionBean);
    }

    /**
     * 添加个动作
     * @param actionBean
     */
    public void addSingleAction(ActionBean actionBean){
        editActionObservable.deleteObservers();
        actionBeanList.add(actionBean);
        actionViewAdapter = null;
        actionViewAdapter = new ActionViewAdapter(actionBeanList, editActionObservable, this);
        rc_actioin.setAdapter(actionViewAdapter);

    }

    /**
     * 点击头部 监听各分组的隐藏和显示
     *
     * @param itemPosition
     */
    public void notifyClickHeader(int itemPosition, int eventType) {
        Map<String, String> map = new HashMap<String, String>();
        if (eventType == CLICK_HEADER) {
            map.put("type", "1");
        }
        map.put("itemPosition", itemPosition + "");
        editActionObservable.notifyObservers(map);
    }


}
