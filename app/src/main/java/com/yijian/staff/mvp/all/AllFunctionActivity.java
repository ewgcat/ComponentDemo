package com.yijian.staff.mvp.all;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.MenuHelper;
import com.yijian.staff.tab.adapter.MenuHeaderRecyclerGridAdapter;
import com.yijian.staff.tab.adapter.MenuRecyclerListAdapter;
import com.yijian.staff.tab.adapter.MenuRecyclerListHeaderWrapper;
import com.yijian.staff.tab.entity.EditItem;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.listener.OnAddListener;
import com.yijian.staff.tab.listener.OnDeleteListener;
import com.yijian.staff.tab.recyclerview.BaseRecyclerItem;
import com.yijian.staff.tab.recyclerview.OnRecyclerItemLongClickListener;
import com.yijian.staff.util.ConstantUtil;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

public class AllFunctionActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    /*分组数据的缓存列表，初始化分组的时候用*/
    private List<MenuItem> frequentlyList;
    private List<MenuItem> vipmanagerList;
    private List<MenuItem> huijikefuList;
    private List<MenuItem> coachList;
    private List<MenuItem> caokeList;
    private List<MenuItem> admList;
    private List<MenuItem> audittaskList;
    private List<MenuItem> otherList;

    private List<EditItem> mEditList;

    private MenuRecyclerListAdapter mListAdapter;
    private MenuRecyclerListHeaderWrapper mListHeaderWrapper;

    private boolean hasChangedListData;
    private TextView rightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_function);
        SharePreferenceUtil.setShowEditIcon(false);
        initView();
        initEvents();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = findViewById(R.id.all_function_navigation_bar);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.hideLeftSecondIv();
        rightTv = navigationBar2.findViewById(R.id.right_tv);
        rightTv.setOnClickListener(this);
        navigationBar2.setTitle("全部功能");
        navigationBar2.setmRightTvText("编辑");
        mRecyclerView = (RecyclerView) findViewById(R.id.edit);
    }


    private void initEvents() {
        //TODO 更换数据来源
        frequentlyList = MenuHelper.getPreferFrequentlyList();
        vipmanagerList = MenuHelper.getPreferVipManageList();
        huijikefuList = MenuHelper.getPreferHuiJiKeFuList();
        coachList = MenuHelper.getPreferCoachList();
        caokeList = MenuHelper.getPreferCaoKeList();
        admList = MenuHelper.getPreferAdmList();
        audittaskList = MenuHelper.getPreferAuditTaskList();
        otherList = MenuHelper.getPreferOtherList();


        mEditList = new ArrayList<>();
        mEditList.add(new EditItem(MenuHelper.GROUP_VIP_MANAGER, "会员管理", vipmanagerList));
        mEditList.add(new EditItem(MenuHelper.GROUP_HUI_JI_KE_FU, "会籍", huijikefuList));
        mEditList.add(new EditItem(MenuHelper.GROUP_COCAH, "教练", coachList));
        mEditList.add(new EditItem(MenuHelper.GROUP_CAO_KE, "操课", caokeList));
        mEditList.add(new EditItem(MenuHelper.GROUP_ADM, "行政", admList));
        mEditList.add(new EditItem(MenuHelper.GROUP_AUDIT_TASK, "审核任务", audittaskList));
        mEditList.add(new EditItem(MenuHelper.GROUP_OTHER, "其他", otherList));

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
            }
        });
        mListHeaderWrapper.addHeader(new EditItem(MenuHelper.GROUP_FREQUENTLY, "常用管理", frequentlyList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mListHeaderWrapper);


    }

    @Override
    protected void onDestroy() {
        mListHeaderWrapper.releaseDragManager();
        if (mListHeaderWrapper.isHasDragChanged() || hasChangedListData) {
            sendBroadcast(new Intent(ConstantUtil.NOTIFY_REFRESH_MENU_LIST_DATA));
        }
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_first_left:
                String s1 = rightTv.getText().toString();
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
                }

                break;
        }
    }
}
