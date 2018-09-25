package com.yijian.workspace.commen;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.R;
import com.yijian.workspace.observe.EditActionObservable;

import androidx.fragment.app.FragmentManager;

public class WorkSpaceSearchActivity extends MvcBaseActivity implements View.OnClickListener {

    FragmentManager fm;
    TextView tv_cancel;
    EditText et_search;
    LinearLayout ll_back;
    LinearLayout lin_search_et;
    LinearLayout lin_search_bt;

    private final String tag1 = "com.yijian.workspace.commen.SearchFragment1";
    private final String tag2 = "com.yijian.workspace.commen.SearchFragment2";
    private EditActionObservable editActionObservable = new EditActionObservable();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_workspace_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tv_cancel = findViewById(R.id.tv_cancel);
        et_search = findViewById(R.id.et_search);
        ll_back = findViewById(R.id.ll_back);
        lin_search_et = findViewById(R.id.lin_search_et);
        lin_search_bt = findViewById(R.id.lin_search_bt);
        findViewById(R.id.ll_back).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.lin_search_bt).setOnClickListener(this);
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
        ActivityUtils.showFragment(fm, R.id.fl_container, tag1, editActionObservable, new String[]{tag1, tag2});
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_back) {
            finish();

        } else if (i == R.id.tv_cancel) {
            ActivityUtils.showFragment(fm, R.id.fl_container, tag1, editActionObservable, new String[]{tag1, tag2});
            ll_back.setVisibility(View.VISIBLE);
            tv_cancel.setVisibility(View.GONE);
            lin_search_et.setVisibility(View.GONE);
            lin_search_bt.setVisibility(View.VISIBLE);
            hideKeyBoard(et_search);
            et_search.setText("");

        } else if (i == R.id.lin_search_bt) {
            ActivityUtils.showFragment(fm, R.id.fl_container, tag2, editActionObservable, new String[]{tag1, tag2});
            ll_back.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.VISIBLE);
            lin_search_et.setVisibility(View.VISIBLE);
            lin_search_bt.setVisibility(View.GONE);
            et_search.setFocusable(true);
            et_search.setFocusableInTouchMode(true);
            et_search.requestFocus();
            showKeyBoard(et_search);

        } else {
        }

    }
}
