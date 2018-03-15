package com.yijian.staff.mvp.work;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.tab.MenuHelper;
import com.yijian.staff.tab.adapter.MenuRecyclerGridAdapter;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.util.CommonUtil;

import java.util.ArrayList;
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

    private ImageView ivRotate;
    private EditText etSearch;
    private View view;

    private List<MenuItem> menuItemList=new ArrayList<>();

    private MenuRecyclerGridAdapter mAdapter;

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
        menuItemList.addAll(MenuHelper.getPreferFrequentlyList());
        MenuItem menuItem = new MenuItem();
        menuItem.setCount(0);
        menuItem.setName("全部");
        menuItem.setPath("/test/all");
        Uri uri = Uri.parse("android.resource://" + getContext().getApplicationContext().getPackageName() + "/" + R.mipmap.lg_all);
        String path = uri.toString();
        menuItem.setIcon(path);
        menuItemList.add(menuItem);
        MenuRecyclerGridAdapter adapter = new MenuRecyclerGridAdapter(menuItemList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


    }


    private void initData() {

        startRotateAnimation();


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


}










