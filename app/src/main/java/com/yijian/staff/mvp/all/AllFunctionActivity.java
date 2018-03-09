package com.yijian.staff.mvp.all;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yijian.staff.R;
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

public class AllFunctionActivity extends AppCompatActivity implements OnDeleteListener, View.OnClickListener {

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
        navigationBar2.findViewById(R.id.iv_second_left).setVisibility(View.GONE);
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        navigationBar2.setTitle("全部功能");
        navigationBar2.setmRightTvText("完成");
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
                mFavList.add(item);
                mListHeaderWrapper.notifyDataSetChanged();

            }
        });
        mListAdapter.setOnDeleteListener(new OnDeleteListener() {
            @Override
            public void onDeleteClick(View v, MenuItem item, int position) {
                mFavList.remove(item);
                mListHeaderWrapper.notifyDataSetChanged();
            }
        });
        mListHeaderWrapper = new MenuRecyclerListHeaderWrapper(mListAdapter);
        mListHeaderWrapper.setOnDeleteListener(this);
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
    public void onDeleteClick(View v, MenuItem item, int position) {
        Toast.makeText(AllFunctionActivity.this, "从最爱里面移除" + item.getName(), Toast.LENGTH_SHORT).show();
        mFavList.remove(item);
        mListHeaderWrapper.notifyDataSetChanged();
        //TODO 删除
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                break;
            case R.id.right_tv:
                mListHeaderWrapper.setShowEditIcon(true);

                break;
        }
    }
}
