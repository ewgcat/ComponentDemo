package com.yijian.staff.mvp.huiji.goodsbaojia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.system.KeyBroadUtil;

/**
 * Created by The_P on 2018/5/18.
 */

public class ActivityHuiijiBaoJiaSeach extends AppCompatActivity {
    private static final String TAG = "ActivityHuiijiBaoJiaSea";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huijibaojia_search);

        initView();

    }

    private void initView() {
        EditText etSearch = findViewById(R.id.et_search);
        TextView tvComplete = findViewById(R.id.tv_complete);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:

                        String name = etSearch.getText().toString().trim();
                        Intent intent = new Intent();
                        intent.putExtra("search",name);
                        KeyBroadUtil.hideKeyBroad(ActivityHuiijiBaoJiaSeach.this,etSearch);
                        setResult(1,intent);
                        finish();
                        break;
                }
                return true;
            }
        });

        tvComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable text = etSearch.getText();
                String s = text.toString().trim();
                Log.e(TAG, "onClick: "+s.length());
                    Intent intent = new Intent();
                    intent.putExtra("search",s);
                    setResult(1,intent);
                     KeyBroadUtil.hideKeyBroad(ActivityHuiijiBaoJiaSeach.this,etSearch);
                    finish();
            }
        });


    }
}
