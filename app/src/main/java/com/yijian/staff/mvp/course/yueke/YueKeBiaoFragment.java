package com.yijian.staff.mvp.course.yueke;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.timetable.helper.MyScollView;
import com.yijian.staff.mvp.course.timetable.helper.NoScrollRecycleView;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.url.CourseUrls;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.ScrollViewListener;
import com.yijian.staff.widget.TimeLayout;
import com.yijian.staff.util.CommonUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;


public class YueKeBiaoFragment extends MvcBaseFragment implements ScrollViewListener {
    private static String TAG = YueKeBiaoFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    NoScrollRecycleView recyclerView;
    @BindView(R.id.scoll_view)
    MyScollView scollView;
    @BindView(R.id.time_layout)
    TimeLayout timeLayout;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_yue_ke_biao;
    }

    @Override
    public void initView() {

        getScreenSize();

        int width = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 160)));
        int height = SCREEN_HEIGHT / 9;
        timeLayout.setTimeItemWidthAndHeight(CommonUtil.dp2px(getContext(), 60), height);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        YueKeAdapter yueKeAdapter = new YueKeAdapter(getContext(), width, height);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(yueKeAdapter);
        scollView.setOnScrollViewListener(this);
        timeLayout.setOnScrollViewListener(this);

        SimpleDateFormat df = new SimpleDateFormat("HH");//设置日期格式
        String format = df.format(new Date());
        int i = Integer.parseInt(format);


        if (i>8){
            int i1 = (i -4)  * height;
            scoll(i1);
        }

    }

    public void initData(){

        HashMap<String,String> map=new HashMap<>();
        map.put("mmddmmdd","");
        HttpManager.postHasHeaderHasParam(CourseUrls.PRIVATE_COURSE_DAY_TABLE_URL, map, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }





    private void scoll(int i){
        scollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scollView.smoothScrollTo(0,i);
            }
        },100);
    }
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
        Log.i(TAG, "SCREEN_WIDTH=" + SCREEN_WIDTH);
        Log.i(TAG, "SCREEN_HEIGHT=" + SCREEN_HEIGHT);
    }

    @Override
    public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
        if (viewGroup == scollView) {
            timeLayout.scrollTo(x, y);
        } else if (viewGroup == timeLayout) {
            scollView.scrollTo(x, y);
        }
    }


}
