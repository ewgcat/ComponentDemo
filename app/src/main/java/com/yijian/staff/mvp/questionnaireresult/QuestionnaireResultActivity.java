package com.yijian.staff.mvp.questionnaireresult;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yijian.staff.R;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

//问卷结果
public class QuestionnaireResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_questionnaire_result);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.questionnaire_result_navigation_bar);
        navigationBar.setTitle("问卷结果", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

    }
}
