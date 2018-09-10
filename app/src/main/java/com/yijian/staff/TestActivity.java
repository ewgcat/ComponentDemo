package com.yijian.staff;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yijian.staff.util.CommonUtil;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yijian.staff.application.CustomApplication.SCREEN_HEIGHT;
import static com.yijian.staff.application.CustomApplication.SCREEN_WIDTH;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.mydragview)
    DragViewGroup mydragview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mydragview.initContext(this);
        mydragview.setAdapter(baseAdapter);

    }

    private BaseAdapter baseAdapter = new BaseAdapter() {

        private int[] colors = new int[]{Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW};
        private Random random = new Random();

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t = new TextView(parent.getContext());


            if (position % 2 == 0) {
                t.setBackgroundColor(Color.BLUE);
            }else {
                t.setBackgroundColor(Color.GREEN);
            }

            t.setText(position + "");
            t.setGravity(Gravity.CENTER);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(((SCREEN_WIDTH - CommonUtil.dp2px(getBaseContext(), 40))) / 8, SCREEN_HEIGHT / 8);
            params.setMargins(0, 0, 0, 0);
            t.setLayoutParams(params);
            return t;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 168;
        }
    };

}