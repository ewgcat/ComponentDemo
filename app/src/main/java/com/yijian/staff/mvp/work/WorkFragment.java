package com.yijian.staff.mvp.work;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.all.AllFunctionActivity;
import com.yijian.staff.mvp.coach.search.CoachSearchActivity;
import com.yijian.staff.mvp.huiji.search.HuiJiSearchActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.prefs.MenuHelper;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.adapter.MenuRecyclerGridAdapter;
import com.yijian.staff.tab.entity.EditItem;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ConstantUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class WorkFragment extends Fragment implements AllFunctionActivity.ObserveDataChange {


    public static WorkFragment mWorkFragment = null;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private EditText etSearch;
    private View view;

    private List<MenuItem> menuItemList = new ArrayList<>();

    private MenuRecyclerGridAdapter adapter;

    public static WorkFragment getInstance() {
        if (mWorkFragment == null) {
            mWorkFragment = new WorkFragment();
        }
        return mWorkFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageView(getActivity(), 0, null);
        view = inflater.inflate(R.layout.fragment_work, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {

        LinearLayout contentView = view.findViewById(R.id.top_view);
        int statusBarHeight = CommonUtil.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, statusBarHeight, 0, 0);
        contentView.setLayoutParams(params);

        etSearch = view.findViewById(R.id.et_search);

        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 此处为得到焦点时的处理内容
                int userRole = SharePreferenceUtil.getUserRole();
                if (userRole == 1 || userRole == 3) {
                    startActivity(new Intent(getContext(), HuiJiSearchActivity.class));
                } else if (userRole == 2 || userRole == 4) {
                    startActivity(new Intent(getContext(), CoachSearchActivity.class));
                }

            }
        });


        adapter = new MenuRecyclerGridAdapter(menuItemList, getContext(), true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);

    }


    private void initData() {


        HttpManager.getIndexMenuList(new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {


                JSONArray menulist = JsonUtil.getJsonArray(result, "menulist");
                MenuHelper menuHelper = new MenuHelper();
                menuHelper.parseJSONArrayToMenuList(menulist);
                List<MenuItem> preferFrequentlyList = MenuHelper.getPreferFrequentlyList();
                menuItemList.clear();
                if (preferFrequentlyList != null) {
                    menuItemList.addAll(preferFrequentlyList);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_jiedai, R.id.iv_all_function})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_jiedai:
                startActivity(new Intent(getActivity(), ReceptionActivity.class));
                break;
            case R.id.iv_all_function:
                AllFunctionActivity.startToActivity(getActivity(),this);
                break;

        }
    }


    private void initMenu() {
        List<MenuItem> preferFrequentlyList = MenuHelper.getPreferFrequentlyList();
        menuItemList.clear();
        if (preferFrequentlyList != null) {
            menuItemList.addAll(preferFrequentlyList);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateChange() {
        initMenu();
    }



}










