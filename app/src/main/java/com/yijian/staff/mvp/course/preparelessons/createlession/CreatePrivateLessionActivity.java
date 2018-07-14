package com.yijian.staff.mvp.course.preparelessons.createlession;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.bean.ActionBean;
import com.yijian.staff.bean.DepartBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.preparelessons.PrivatePrepareLessonBody;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CreatePrivateLessionActivity extends MvcBaseActivity implements MyDepartView.OnDepartOprationListener {

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

    List<ActionBean> actionBeanList = new ArrayList<ActionBean>(); //装载RecyclerView的集合
    ActionViewAdapter actionViewAdapter; //装载RecyclerView的适配器Adapter
    EditActionObservable editActionObservable = new EditActionObservable();
    boolean isEdit = false; //当前状态是否处于编辑状态
    public static int CLICK_HEADER = 0; //点击头部时的分发
    private String privateApplyId; //约课ID


    public boolean isEdit() {
        return isEdit;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_create_private_lession;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitle();
        initData();
    }

    private void initData() {
        privateApplyId = getIntent().getStringExtra("privateApplyId");
        /**************** 初始化部位训练数据 ************************/
        loadDepartData();

        /****************** 初始化动作内容选项数据 **************************/
        /*ActionBean actionBean1 = new ActionBean("1", "简单", "部位", "平板支撑", "1组/1次", "无");
        ActionBean actionBean2 = new ActionBean("2", "中等", "部位","平板支撑", "2组2次", "有");
        ActionBean actionBean3 = new ActionBean("3", "困难", "部位","平板支撑", "3组/3次", "无");
        actionBeanList.add(actionBean1);
        actionBeanList.add(actionBean2);
        actionBeanList.add(actionBean3);*/


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rc_actioin.setLayoutManager(layoutmanager);
        actionViewAdapter = new ActionViewAdapter(actionBeanList, editActionObservable, this);
        rc_actioin.setAdapter(actionViewAdapter);
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
                savePrepareLesson();
            }
        });
    }

    /**
     * 保存私教课备课内容
     */
    private void savePrepareLesson() {
        PrivatePrepareLessonBody privatePrepareLessonBody = new PrivatePrepareLessonBody();
        privatePrepareLessonBody.setPrivateApplyId(privateApplyId);
        List<PrivatePrepareLessonBody.ContentListBean> contentListBeans = new ArrayList<>();
        for (ActionBean actionBean : actionBeanList) {
            PrivatePrepareLessonBody.ContentListBean contentListBean = new PrivatePrepareLessonBody.ContentListBean();
            contentListBean.setBuildDesc(actionBean.getBuildDesc());
            contentListBean.setMoApplianceName(actionBean.getMoApplianceName());
            contentListBean.setMoDifficulty(actionBean.getMoDifficulty());
            contentListBean.setMoName(actionBean.getMoName());
            contentListBean.setMoParts(actionBean.getMoParts());
            contentListBeans.add(contentListBean);
        }
        privatePrepareLessonBody.setContentList(contentListBeans);
        showLoading();
        HttpManager.savePrivatePrepareLesson(HttpManager.COACH_PRIVATE_COURSE_STOCK_SAVE_PREPARE_URL, privatePrepareLessonBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                showToast("创建备课成功");
                finish();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                showToast("创建备课失败");
            }
        });
    }

    /**
     * 获取训练部位
     */
    public void loadDepartData() {
        HttpManager.getHasHeaderNoParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_BODYPART_URL, new ResultJSONArrayObserver() {
            @Override
            public void onSuccess(JSONArray result) {
                List<DepartBean> departArray = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), DepartBean.class);
                view_depart.addLineView(departArray, CreatePrivateLessionActivity.this);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CreatePrivateLessionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取动作内容
     */
    public void loadActionData() {
        Map<String, String> map = new HashMap<>();
        List<String> departIdList = new ArrayList<>();


        for (DepartBean departBean : view_depart.getSelectedDepartList()) {
            departIdList.add(departBean.getId());
        }

        map.put("bodyPartIds", departIdList.toString().substring(1, departIdList.toString().length() - 1));
        HttpManager.postHasHeaderHasParam(HttpManager.COACH_PRIVATE_COURSE_STOCK_ACTIONCONTENT_URL, map, new ResultJSONObjectObserver() {

            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray easyArray = result.getJSONArray("easyList");
                    JSONArray secondaryArray = result.getJSONArray("secondaryList");
                    JSONArray hardArray = result.getJSONArray("hardList");

                    List<ActionBean> easyList = com.alibaba.fastjson.JSONArray.parseArray(easyArray.toString(), ActionBean.class);
                    List<ActionBean> secondaryList = com.alibaba.fastjson.JSONArray.parseArray(secondaryArray.toString(), ActionBean.class);
                    List<ActionBean> hardList = com.alibaba.fastjson.JSONArray.parseArray(hardArray.toString(), ActionBean.class);
                    List<List<ActionBean>> parentList = new ArrayList<>();
                    parentList.add(easyList);
                    parentList.add(secondaryList);
                    parentList.add(hardList);
                    SelectActionPopwindow selectActionPopwindow = new SelectActionPopwindow(CreatePrivateLessionActivity.this, parentList);
                    selectActionPopwindow.showAtBottom(getWindow().getDecorView());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                Log.e("Test", msg);
            }
        });
    }

    /**
     * 部位选择回掉
     *
     * @param id
     */
    @Override
    public void departOpration(String id) {

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

                if (view_depart.getSelectedDepartList() == null || view_depart.getSelectedDepartList().size() <= 0) {
                    Toast.makeText(this, "请选择训练部位", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadActionData();

                /*List<ActionBean> selectActionBeanList1 = new ArrayList<ActionBean>();
                ActionBean actionBean1 = new ActionBean("0", "简单", "部位", "平板支撑", "1组/1次", "无");
                ActionBean actionBean2 = new ActionBean("1","简单","部位","俯卧撑","1组/30次","撑炳");
                ActionBean actionBean3 = new ActionBean("2","简单","部位","俯卧撑","1组/30次","撑炳");
                selectActionBeanList1.add(actionBean1);
                selectActionBeanList1.add(actionBean2);
                selectActionBeanList1.add(actionBean3);

                List<ActionBean> selectActionBeanList2 = new ArrayList<ActionBean>();
                ActionBean actionBean21 = new ActionBean("0","中等", "部位","仰卧起坐","2组/30次","撑炳");
                ActionBean actionBean22 = new ActionBean("1","中等", "部位","仰卧起坐","2组/30次","撑炳");
                ActionBean actionBean23 = new ActionBean("2","中等", "部位","仰卧起坐","2组/30次","撑炳");
                selectActionBeanList2.add(actionBean21);
                selectActionBeanList2.add(actionBean22);
                selectActionBeanList2.add(actionBean23);

                List<ActionBean> selectActionBeanList3 = new ArrayList<ActionBean>();
                ActionBean actionBean31 = new ActionBean("0","困难", "部位","深蹲","2组/30次","无");
                ActionBean actionBean32 = new ActionBean("1","困难", "部位","深蹲","2组/30次","无");
                ActionBean actionBean33 = new ActionBean("2","困难", "部位","深蹲","2组/30次","无");
                selectActionBeanList3.add(actionBean31);
                selectActionBeanList3.add(actionBean32);
                selectActionBeanList3.add(actionBean33);

                List<List<ActionBean>> parentList = new ArrayList<>();
                parentList.add(selectActionBeanList1);
                parentList.add(selectActionBeanList2);
                parentList.add(selectActionBeanList3);

                SelectActionPopwindow selectActionPopwindow = new SelectActionPopwindow(this,parentList);
                selectActionPopwindow.showAtBottom(getWindow().getDecorView());*/

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
     *
     * @param actionBean
     */
    public void addSingleAction(ActionBean actionBean) {
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
