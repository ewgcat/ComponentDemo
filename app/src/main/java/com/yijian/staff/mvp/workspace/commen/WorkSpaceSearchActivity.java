package com.yijian.staff.mvp.workspace.commen;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkSpaceSearchActivity extends MvcBaseActivity {

    android.support.v4.app.FragmentManager fm;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.lin_search_et)
    LinearLayout lin_search_et;
    @BindView(R.id.lin_search_bt)
    LinearLayout lin_search_bt;

    private Fragment searchFragment1;
    private Fragment searchFragment2;
    private final String tag1 = "search1";
    private final String tag2 = "search2";
    private String moduleType;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_workspace_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        moduleType = getIntent().getStringExtra("moduleType");
        fm = getSupportFragmentManager();
        searchFragment1 = new SearchFragment1();
        ActivityUtils.addFragment(fm, R.id.fl_container, searchFragment1, tag1);
    }

    @OnClick({R.id.ll_back, R.id.tv_cancel, R.id.lin_search_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back: //返回
                finish();
                break;
            case R.id.tv_cancel: //取消
                searchFragment1 = fm.findFragmentByTag(tag1);
                searchFragment2 = fm.findFragmentByTag(tag2);
                if(searchFragment2 != null){
                    ActivityUtils.hideFragment(fm,searchFragment2);
                }
                if(searchFragment1 == null){
                    searchFragment1 = new SearchFragment1();
                    ActivityUtils.addFragment(fm,R.id.fl_container,searchFragment1,tag1);
                }else{
                    ActivityUtils.showFragment(fm,searchFragment1);
                }
                ll_back.setVisibility(View.VISIBLE);
                tv_cancel.setVisibility(View.GONE);
                lin_search_et.setVisibility(View.GONE);
                lin_search_bt.setVisibility(View.VISIBLE);
                hideKeyBoard(et_search);
                break;
            case R.id.lin_search_bt: //搜索
                searchFragment1 = fm.findFragmentByTag(tag1);
                searchFragment2 = fm.findFragmentByTag(tag2);
                if(searchFragment1 != null){
                    ActivityUtils.hideFragment(fm,searchFragment1);
                }
                if(searchFragment2 == null){
                    searchFragment2 = new SearchFragment2();
                    ActivityUtils.addFragment(fm,R.id.fl_container,searchFragment2,tag2);
                }else{
                    ActivityUtils.showFragment(fm,searchFragment2);
                }
                ll_back.setVisibility(View.GONE);
                tv_cancel.setVisibility(View.VISIBLE);
                lin_search_et.setVisibility(View.VISIBLE);
                lin_search_bt.setVisibility(View.GONE);
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();
                showKeyBoard(et_search);
                break;
        }
    }

}
