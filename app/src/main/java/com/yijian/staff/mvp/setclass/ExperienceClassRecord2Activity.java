package com.yijian.staff.mvp.setclass;

import android.media.Image;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.yijian.staff.R;
import com.yijian.staff.mvp.coach.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.huiji.bean.HuiJiViperBean;
import com.yijian.staff.mvp.setclass.bean.PrivateLessonRecordBean;
import com.yijian.staff.mvp.setclass.bean.PrivateShangKeBean;
import com.yijian.staff.mvp.setclass.orderclass.SaveDataDialog;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExperienceClassRecord2Activity extends OpenLessonNewActivity {

    @BindView(R.id.rv_open_lesson)
    RecyclerView rv_open_lesson;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_lesson_new2);
        ButterKnife.bind(this);
        initView();
        loadData();
    }




    private void initView() {

    }


}

