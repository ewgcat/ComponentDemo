package com.yijian.staff.mvp.setclass;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Chronometer;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/test/15")
public class OpenLessonNewActivity extends AppCompatActivity {

    @BindView(R.id.rv_open_lesson)
    RecyclerView rv_open_lesson;
    @BindView(R.id.chronometer)
    Chronometer chronometer;

    OpenLessonNewAdapter openLessonNewAdapter;
    List<OpenLessonNewBean> openLessonNewBeans = new ArrayList<OpenLessonNewBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_lesson_new);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 添加虚拟数据
     */
    private void initData() {
        /************** 添加第一组 *****************/
        OpenLessonNewBean openLessonNewBean1 = new OpenLessonNewBean();
        openLessonNewBean1.setDegree("简单");

        OpenLessonNewBean.SubOpenLessonNewBean subOpenLessonNewBean1 = new OpenLessonNewBean.SubOpenLessonNewBean();
        Map<String,String> actionMap1 = new HashMap<String,String>();
        actionMap1.put("平板支撑","1组/每组2分钟");
        actionMap1.put("需要器械","有");
        subOpenLessonNewBean1.setActionMap(actionMap1);

        Map<String,String> actionOprationMap1 = new HashMap<String,String>();
        actionOprationMap1.put("动作形态","");
        actionOprationMap1.put("强度","");
        actionOprationMap1.put("时间","");
        actionOprationMap1.put("间隔","");
        subOpenLessonNewBean1.setActionOprationMap(actionOprationMap1);

        /************** 添加第二组 *****************/
        OpenLessonNewBean openLessonNewBean2 = new OpenLessonNewBean();
        openLessonNewBean2.setDegree("中等");

        OpenLessonNewBean.SubOpenLessonNewBean subOpenLessonNewBean2 = new OpenLessonNewBean.SubOpenLessonNewBean();
        Map<String,String> actionMap2 = new HashMap<String,String>();
        actionMap2.put("平板支撑","1组/每组2分钟");
        actionMap2.put("需要器械","有");
        subOpenLessonNewBean2.setActionMap(actionMap2);

        Map<String,String> actionOprationMap2 = new HashMap<String,String>();
        actionOprationMap2.put("动作形态","");
        actionOprationMap2.put("强度","");
        actionOprationMap2.put("时间","");
        actionOprationMap2.put("间隔","");
        subOpenLessonNewBean1.setActionOprationMap(actionOprationMap2);



        openLessonNewBeans.add(openLessonNewBean1);
        openLessonNewBeans.add(openLessonNewBean2);

        openLessonNewAdapter.notifyDataSetChanged();
    }

    private Handler handler=new Handler();
    private void initView() {
//        chronometer.setFormat("H:MM:SS");
        Calendar mCalendar= Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);

        int time= hour*60*60+minute*60+second;
        chronometer.setBase(SystemClock.elapsedRealtime()-time*1000);//计时器清零
        chronometer.start();


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        rv_open_lesson.setLayoutManager(layoutmanager);
        openLessonNewAdapter = new OpenLessonNewAdapter(openLessonNewBeans);
        rv_open_lesson.setAdapter(openLessonNewAdapter);
    }
}
