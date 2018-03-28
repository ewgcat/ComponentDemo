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
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.prefs.MenuHelper;
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
public class WorkFragment extends Fragment {


    public static WorkFragment mWorkFragment = null;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_yeji_wanchengdu)
    TextView tvYejiWanchengdu;
    @BindView(R.id.tv_today_score)
    TextView tvTodayScore;
    @BindView(R.id.tv_month_rank)
    TextView tvMonthRank;

    private ImageView ivRotate;
    private EditText etSearch;
    private View view;

    private List<MenuItem> menuItemList = new ArrayList<>();

    private MenuRecyclerGridAdapter adapter;
    private RecyclerUpdateReceiver mRecyclerUpdateReceiver;

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
        ivRotate = view.findViewById(R.id.iv_rotate);
        etSearch.setHintTextColor(Color.parseColor("#fafbfb"));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:

                        //TODO 点击搜索键,触发搜索请求

                        break;
                }
                return true;
            }
        });

        adapter = new MenuRecyclerGridAdapter(menuItemList, getContext(), true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(adapter);

        //注册刷新数据的广播
        mRecyclerUpdateReceiver = new RecyclerUpdateReceiver();
        IntentFilter filter=new IntentFilter();
        filter.setPriority(1009);
        filter.addAction(ConstantUtil.NOTIFY_REFRESH_MENU_LIST_DATA);
        getActivity().registerReceiver(mRecyclerUpdateReceiver,filter);

        startRotateAnimation();

    }


    private void initData() {


        HashMap<String, String> map = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        map.put("token", user.getToken());
        HttpManager.getIndexMenuList(map, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                String monthRank = JsonUtil.getString(result, "monthRank");
                String todayScore = JsonUtil.getString(result, "todayScore");
                String completePercent = JsonUtil.getString(result, "completePercent");

                if (TextUtils.isEmpty(completePercent)) {
                    tvYejiWanchengdu.setText("未知");
                }else {
                    tvYejiWanchengdu.setText(completePercent.substring(0,completePercent.length()-1));
                }
                if (TextUtils.isEmpty(todayScore)) {
                    tvTodayScore.setText("未知");
                }else {
                    tvTodayScore.setText(todayScore.substring(0,todayScore.length()-1));
                }
                if (TextUtils.isEmpty(monthRank)) {
                    tvMonthRank.setText("未知");
                }else {
                    tvMonthRank.setText("第"+monthRank+"名");
                }

                JSONArray menulist = JsonUtil.getJsonArray(result, "menulist");
                MenuHelper menuHelper = new MenuHelper();
                menuHelper.parseJSONArrayToMenuList(menulist);
                List<MenuItem> preferFrequentlyList = MenuHelper.getPreferFrequentlyList();
                menuItemList.clear();
                if (preferFrequentlyList != null) {
                    menuItemList.addAll(preferFrequentlyList);
                }
                initAllFunctionMenuItem();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initAllFunctionMenuItem() {
        MenuItem menuItem = new MenuItem();
        menuItem.setCount(0);
        menuItem.setName("全部");
        menuItem.setPath("/test/all");
        Uri uri = Uri.parse("android.resource://" + getContext().getApplicationContext().getPackageName() + "/" + R.mipmap.lg_all);
        String path = uri.toString();
        menuItem.setIcon(path);
        menuItemList.add(menuItem);
    }


    /**
     * 开始动画
     */
    private void startRotateAnimation() {
        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(2000);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
        ivRotate.setAnimation(rotate);
    }

    /**
     * 移除动画
     */
    private void clearRotateAnimation() {
        ivRotate.clearAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_jiedai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_jiedai:
                startActivity(new Intent(getActivity(), ReceptionActivity.class));
                break;

        }
    }


    private void initMenu() {
        List<MenuItem> preferFrequentlyList = MenuHelper.getPreferFrequentlyList();
        menuItemList.clear();
        if (preferFrequentlyList != null) {
            menuItemList.addAll(preferFrequentlyList);
        }
        initAllFunctionMenuItem();
        adapter.notifyDataSetChanged();
    }

    /**
     * 用于执行刷新数据的广播接收器
     */
    private class RecyclerUpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            initMenu();
        }
    }

    @Override
    public void onDestroy() {
        //注销刷新数据的广播
        if(mRecyclerUpdateReceiver!=null){
            getActivity().unregisterReceiver(mRecyclerUpdateReceiver);
        }
        super.onDestroy();
    }
}










