package com.yijian.staff.mvp.all;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.savemenu.MenuBean;
import com.yijian.staff.net.requestbody.savemenu.MenuRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.MenuHelper;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.adapter.MenuRecyclerListAdapter;
import com.yijian.staff.tab.adapter.MenuRecyclerListHeaderWrapper;
import com.yijian.staff.tab.entity.EditItem;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnAddListener;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.recyclerview.BaseRecyclerItem;
import com.yijian.staff.tab.recyclerview.OnRecyclerItemLongClickListener;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/test/all")
public class AllFunctionActivity extends MvcBaseActivity implements View.OnClickListener {

    public static void startToActivity(Context context, ObserveDataChange observeDataChange2) {
        observeDataChange = observeDataChange2;
        context.startActivity(new Intent(context, AllFunctionActivity.class));
    }


    private RecyclerView mRecyclerView;
    /*分组数据的缓存列表，初始化分组的时候用*/
    private List<MenuItem> frequentlyList;
    private List<MenuItem> vipmanagerList;
    private List<MenuItem> huijikefuList;
    private List<MenuItem> coachList;
    private List<MenuItem> otherList;

    private List<EditItem> mEditList;

    private MenuRecyclerListAdapter mListAdapter;
    private MenuRecyclerListHeaderWrapper mListHeaderWrapper;

    private boolean hasChangedListData;
    private TextView rightTv;
    private static ObserveDataChange observeDataChange;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_all_function;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        SharePreferenceUtil.setShowEditIcon(false);
        NavigationBar2 navigationBar2 = findViewById(R.id.all_function_navigation_bar);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.hideLeftSecondIv();
        rightTv = navigationBar2.findViewById(R.id.right_tv);
        rightTv.setOnClickListener(this);
        navigationBar2.setTitle("全部功能");
        navigationBar2.setmRightTvText("编辑");
        mRecyclerView = (RecyclerView) findViewById(R.id.edit);
        initEvents();
    }


    private void initEvents() {
        frequentlyList = MenuHelper.getPreferFrequentlyList();
        vipmanagerList = MenuHelper.getPreferVipManageList();
        huijikefuList = MenuHelper.getPreferHuiJiKeFuList();
        coachList = MenuHelper.getPreferCoachList();
        otherList = MenuHelper.getPreferOtherList();
        //非常用功能
        mEditList = new ArrayList<>();
        if (vipmanagerList != null) {
            mEditList.add(new EditItem(MenuHelper.GROUP_VIP_MANAGER, vipmanagerList));
        }
        if (huijikefuList != null) {
            mEditList.add(new EditItem(MenuHelper.GROUP_HUI_JI_KE_FU, huijikefuList));
        }
        if (coachList != null) {
            mEditList.add(new EditItem(MenuHelper.GROUP_COCAH, coachList));
        }
        if (otherList != null) {
            mEditList.add(new EditItem(MenuHelper.GROUP_OTHER, otherList));
        }

        mListAdapter = new MenuRecyclerListAdapter(mEditList, AllFunctionActivity.this);

        mListAdapter.setOnAddListener(new OnAddListener() {
            @Override
            public void onAddClick(View v, MenuItem item, int position) {
                boolean isContain = false;
                String group = item.getGroup();
                for (int i = 0; i < frequentlyList.size(); i++) {
                    MenuItem favMenuItem = frequentlyList.get(i);
                    if (favMenuItem.getGroup().equals(group) && favMenuItem.getName().equals(item.getName())) {
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    frequentlyList.add(item);
                    mListHeaderWrapper.notifyDataSetChanged();
                }
                hasChangedListData = true;
            }
        });

        mListAdapter.setOnDeleteListener(new OnDeleteListener() {
            @Override
            public void onDeleteClick(View v, MenuItem item, int position) {

                //从常用中删除
                String group = item.getGroup();
                for (int i = 0; i < mEditList.size(); i++) {
                    EditItem editItem = mEditList.get(i);
                    if (editItem.getGroup().equals(group)) {
                        List<MenuItem> menuItemList = editItem.getMenuItemList();
                        for (int j = 0; j < menuItemList.size(); j++) {
                            if (menuItemList.get(j).getName().equals(item.getName())) {
                                menuItemList.get(j).setType(1);
                                mListAdapter.notifyChildDataChanged(group, menuItemList.get(j));
                            }
                        }
                    }
                }

                for (int i = 0; i < frequentlyList.size(); i++) {
                    MenuItem favMenuItem = frequentlyList.get(i);
                    if (favMenuItem.getGroup().equals(group) && favMenuItem.getName().equals(item.getName())) {
                        frequentlyList.remove(favMenuItem);
                        mListHeaderWrapper.notifyDataSetChanged();
                    }
                }
                hasChangedListData = true;
            }
        });

        mListAdapter.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, BaseRecyclerItem item, int position, int segment) {
                String s = rightTv.getText().toString();
                if (s.equals("编辑")) {
                    SharePreferenceUtil.setShowEditIcon(true);
                    mListAdapter.notifyDataSetChanged();
                    mListHeaderWrapper.notifyDataSetChanged();
                    rightTv.setText("完成");
                }
            }
        });
        mListHeaderWrapper = new MenuRecyclerListHeaderWrapper(mListAdapter);
        mListHeaderWrapper.setOnDeleteListener(new OnDeleteListener() {
            @Override
            public void onDeleteClick(View v, MenuItem item, int position) {
                //从常用中删除
                String group = item.getGroup();
                for (int i = 0; i < mEditList.size(); i++) {
                    EditItem editItem = mEditList.get(i);
                    if (editItem.getGroup().equals(group)) {
                        List<MenuItem> menuItemList = editItem.getMenuItemList();
                        for (int j = 0; j < menuItemList.size(); j++) {
                            if (menuItemList.get(j).getName().equals(item.getName())) {
                                menuItemList.get(j).setType(1);
                                mListAdapter.notifyChildDataChanged(group, menuItemList.get(j));
                            }
                        }
                    }
                }
                frequentlyList.remove(item);
                mListHeaderWrapper.notifyDataSetChanged();
                hasChangedListData = true;
            }
        });
        mListHeaderWrapper.addHeader(new EditItem(MenuHelper.GROUP_FREQUENTLY, frequentlyList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mListHeaderWrapper);


    }

    @Override
    protected void onDestroy() {
        mListHeaderWrapper.releaseDragManager();
        if (mListHeaderWrapper.isHasDragChanged() || hasChangedListData) {
            observeDataChange.updateChange();
        }
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_first_left:
                SharePreferenceUtil.setShowEditIcon(false);
                finish();
                break;
            case R.id.right_tv:
                String s = rightTv.getText().toString();
                if (s.equals("编辑")) {
                    SharePreferenceUtil.setShowEditIcon(true);
                    mListAdapter.notifyDataSetChanged();
                    mListHeaderWrapper.notifyDataSetChanged();
                    rightTv.setText("完成");
                } else if (s.equals("完成")) {
                    SharePreferenceUtil.setShowEditIcon(false);
                    mListAdapter.notifyDataSetChanged();
                    mListHeaderWrapper.notifyDataSetChanged();
                    rightTv.setText("编辑");
                    postSaveMenu();
                }
                break;
        }
    }

    private void postSaveMenu() {
        List<MenuBean> list = new ArrayList<>();
        if (frequentlyList != null) {
            for (int i = 0; i < frequentlyList.size(); i++) {
                MenuItem menuItem = frequentlyList.get(i);
                long itemId = menuItem.getItemId();
                MenuBean menuBean = new MenuBean(itemId, i);
                list.add(menuBean);
            }
        }
        showLoading();
        MenuHelper.savePreferFrequentlyList(frequentlyList);
        HttpManager.saveMenuChange(new MenuRequestBody(list), new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
                hideLoading();
            }
        });
    }

    public interface ObserveDataChange {
        void updateChange();
    }

}
