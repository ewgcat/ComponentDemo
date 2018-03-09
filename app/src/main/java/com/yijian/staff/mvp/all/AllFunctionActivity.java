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
import com.yijian.staff.util.ConstantUtil;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

public class AllFunctionActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<MenuItem> mFavList;
    private List<MenuItem> mColdList;
    private List<MenuItem> mModernList;
    private List<MenuItem> mMiscList;
    private List<MenuItem> mPersonList;
    private List<MenuItem> mEqtList;

    private List<EditItem> mEditList;

    private MenuRecyclerListAdapter mListAdapter;
    private MenuRecyclerListHeaderWrapper mListHeaderWrapper;

    private boolean hasChangedListData;
    private TextView rightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_all_function);
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
        mFavList = MenuHelper.getPreferFavoriteList();
        mColdList = MenuHelper.getPreferColdWeaponList();
        mModernList = MenuHelper.getPreferModernWeaponList();


        mEditList = new ArrayList<>();
        mEditList.add(new EditItem(MenuHelper.GROUP_COLD_WEAPON, getString(R.string.cold_weapon), mColdList));
        mEditList.add(new EditItem(MenuHelper.GROUP_MODERN_WEAPON, getString(R.string.modern_weapon), mModernList));

        mListAdapter = new MenuRecyclerListAdapter(mEditList, AllFunctionActivity.this);


        mListAdapter.setOnAddListener(new OnAddListener() {
            @Override
            public void onAddClick(View v, MenuItem item, int position) {
                boolean isContain=false;
                String group = item.getGroup();
                for (int i = 0; i < mFavList.size(); i++) {
                    MenuItem favMenuItem = mFavList.get(i);
                    if (favMenuItem.getGroup().equals(group)&&favMenuItem.getName().equals(item.getName())) {
                       isContain=true;
                       break;
                    }
                }
                if (!isContain){
                    mFavList.add(item);
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

                for (int i = 0; i < mFavList.size(); i++) {
                    MenuItem favMenuItem = mFavList.get(i);
                    if (favMenuItem.getGroup().equals(group)&&favMenuItem.getName().equals(item.getName())) {
                            mFavList.remove(favMenuItem);
                            mListHeaderWrapper.notifyDataSetChanged();
                    }
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
                mFavList.remove(item);
                mListHeaderWrapper.notifyDataSetChanged();
            }
        });
        mListHeaderWrapper.addHeader(new EditItem(MenuHelper.GROUP_FAVORITE, getString(R.string.favorite), mFavList));
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
                if (s1.equals("完成")){
                    SharePreferenceUtil.setShowEditIcon(false);
                    mListAdapter.notifyDataSetChanged();
                    mListHeaderWrapper.notifyDataSetChanged();
                }
                finish();
                break;
            case R.id.right_tv:
                String s = rightTv.getText().toString();
                if (s.equals("编辑")){
                    SharePreferenceUtil.setShowEditIcon(true);
                    mListAdapter.notifyDataSetChanged();
                    mListHeaderWrapper.notifyDataSetChanged();
                    rightTv.setText("完成");
                }else if (s.equals("完成")){
                    SharePreferenceUtil.setShowEditIcon(false);
                    mListAdapter.notifyDataSetChanged();
                    mListHeaderWrapper.notifyDataSetChanged();
                    rightTv.setText("编辑");
                }

                break;
        }
    }
}
