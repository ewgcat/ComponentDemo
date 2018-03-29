package com.yijian.staff.mvp.huiji.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuiJiSearchActivity extends AppCompatActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rcl)
    RecyclerView rcl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_ji_search);
        ButterKnife.bind(this);
        etSearch.setHintTextColor(Color.parseColor("#ffffff"));

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

    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }
}
