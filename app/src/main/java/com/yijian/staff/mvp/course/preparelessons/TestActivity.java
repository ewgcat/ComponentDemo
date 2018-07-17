package com.yijian.staff.mvp.course.preparelessons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.widget.NumberTabLayout;

import static com.yijian.staff.widget.NumberTabLayout.ADD_TYPE;
import static com.yijian.staff.widget.NumberTabLayout.CHE_XIAO_TYPE;


public class TestActivity extends AppCompatActivity {
    private NumberTabLayout numberTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        numberTabLayout = findViewById(R.id.my_point);
        numberTabLayout.setAddListener(new NumberTabLayout.AddListener() {
            @Override
            public void addClick(View view) {
                if (numberTabLayout.getAddType() == 1) {
                    numberTabLayout.addPoint();
                } else if (numberTabLayout.getAddType() == 2) {
                    numberTabLayout.resetPointNum();
                    numberTabLayout.changeAddView(ADD_TYPE);
                }
            }
        });
        numberTabLayout.setPointListener(new NumberTabLayout.PointListener() {
            @Override
            public void pointClick(View view) {
                TextView point = (TextView) view;
                if ("-".equals(point.getText().toString())) {
                    numberTabLayout.removePoint(view);
                } else {
                    point.setText("-");
                    point.setBackground(getDrawable(R.drawable.red_circle_background));
                    numberTabLayout.changeAddView(CHE_XIAO_TYPE);
                }
            }
        });
    }
}
