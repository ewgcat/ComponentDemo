package com.yijian.staff.mvp.work;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.mvp.all.AllFunctionActivity;
import com.yijian.staff.mvp.dailywork.DailyWorkActivity;
import com.yijian.staff.mvp.huifang.task.HuiFangTaskActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.tab.MenuHelper;
import com.yijian.staff.tab.adapter.MenuRecyclerGridAdapter;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.recyclerview.OnRecyclerItemClickListener;
import com.yijian.staff.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class WorkFragment extends Fragment  implements OnRecyclerItemClickListener<MenuItem> {


    public static WorkFragment mWorkFragment = null;
    Unbinder unbinder;
    private ImageView ivRotate;
    private EditText etSearch;
    private View view;

    private RecyclerView mRecyclerView;
    private List<MenuItem> mFavList;
    private MenuRecyclerGridAdapter mAdapter;
    private int ID_ALL_ITEM=-1;

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
    }


    private void initData(){

        startRotateAnimation();

        if(mFavList!=null){
            mFavList.clear();
        }else{
            mFavList=new ArrayList<>();
        }
        MenuItem add=new MenuItem();
        add.setName("编辑");
        add.setIcon("add");
        add.setItemId(ID_ALL_ITEM);
        mFavList.add(add);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_display);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        mAdapter=new MenuRecyclerGridAdapter(mFavList,getContext());
        mAdapter.setOnRecyclerItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View v, MenuItem item, int position, int segment) {
        if(item.getItemId()==ID_ALL_ITEM){
            Intent i=new Intent(getContext(),AllFunctionActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getContext(),item.toString(), Toast.LENGTH_SHORT).show();
        }
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

    @OnClick({R.id.rl_jiedai,R.id.ll_work_hui_ji_jie_dai,
            R.id.ll_work_yao_yue, R.id.ll_work_tian_jia_qian_zai,
            R.id.ll_work_tou_su_chu_li, R.id.ll_work_hui_yuan_xin_xi,
            R.id.ll_main_yi_xiang_hui_yuan, R.id.ll_work_kao_qin,
            R.id.ll_work_all_gong_neng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_jiedai:
                Intent i1 = new Intent(getActivity(), ReceptionActivity.class);
                startActivity(i1);
                break;
           case R.id.ll_work_hui_ji_jie_dai:
               Intent i2 = new Intent(getActivity(), HuiFangTaskActivity.class);
               startActivity(i2);
                break;
            case R.id.ll_work_yao_yue:
                break;
            case R.id.ll_work_tian_jia_qian_zai:
                break;
            case R.id.ll_work_tou_su_chu_li:
                break;
            case R.id.ll_work_hui_yuan_xin_xi:
                break;
            case R.id.ll_main_yi_xiang_hui_yuan:
                break;
            case R.id.ll_work_kao_qin:
                startActivity(new Intent(getActivity(), DailyWorkActivity.class));
                break;
            case R.id.ll_work_all_gong_neng:
                break;
        }
    }
}










