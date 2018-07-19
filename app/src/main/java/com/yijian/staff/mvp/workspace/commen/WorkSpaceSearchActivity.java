package com.yijian.staff.mvp.workspace.commen;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.workspace.base.BaseSpaceFragment;
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

    private final String tag1 = "com.yijian.staff.mvp.workspace.commen.SearchFragment1";
    private final String tag2 = "com.yijian.staff.mvp.workspace.commen.SearchFragment2";
    private EditActionObservable editActionObservable = new EditActionObservable();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_workspace_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        String name = et_search.getText().toString().trim();
                        editActionObservable.notifyObservers(name);
                        hideKeyBoard(et_search);
                        break;
                        default:
                }
                return true;
            }
        });
        fm = getSupportFragmentManager();
        ActivityUtils.showFragment(fm, R.id.fl_container, tag1, editActionObservable, new String[]{tag1,tag2});
    }

    @OnClick({R.id.ll_back, R.id.tv_cancel, R.id.lin_search_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back: //返回
                finish();
                break;
            case R.id.tv_cancel: //取消
                ActivityUtils.showFragment(fm, R.id.fl_container, tag1, editActionObservable, new String[]{tag1,tag2});
                ll_back.setVisibility(View.VISIBLE);
                tv_cancel.setVisibility(View.GONE);
                lin_search_et.setVisibility(View.GONE);
                lin_search_bt.setVisibility(View.VISIBLE);
                hideKeyBoard(et_search);
                et_search.setText("");
                break;
            case R.id.lin_search_bt: //搜索
                ActivityUtils.showFragment(fm, R.id.fl_container, tag2, editActionObservable, new String[]{tag1,tag2});
                ll_back.setVisibility(View.GONE);
                tv_cancel.setVisibility(View.VISIBLE);
                lin_search_et.setVisibility(View.VISIBLE);
                lin_search_bt.setVisibility(View.GONE);
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();
                showKeyBoard(et_search);
                break;
                default:
        }
    }

}
