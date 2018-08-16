package com.yijian.staff.mvp.course.timetable.schedule.week;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.course.timetable.helper.MyScollView;
import com.yijian.staff.mvp.course.timetable.helper.NoScrollRecycleView;
import com.yijian.staff.mvp.course.timetable.helper.OnStartDragListener;
import com.yijian.staff.mvp.course.timetable.helper.RecyclerListAdapter;
import com.yijian.staff.mvp.course.timetable.helper.SimpleItemTouchHelperCallback;
import com.yijian.staff.mvp.course.timetable.schedule.week.edit.EditCourseTableActivity;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.ScrollViewListener;
import com.yijian.staff.widget.TimeLayout;
import com.yijian.staff.widget.WeekLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;


public class ScheduleWeekFragment extends MvcBaseFragment implements ScrollViewListener,OnStartDragListener {

    private View stub;
    private MyScollView scoll_view;
    private TimeLayout timeLayout;
    private WeekLayout week_layout;
    private ItemTouchHelper mItemTouchHelper;
    private NoScrollRecycleView recyclerView;
    private static String TAG = ScheduleWeekFragment.class.getSimpleName();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_week;
    }

    @Override
    public void initView() {
        getScreenSize();

        scoll_view = rootView.findViewById(R.id.scoll_view);
        stub = rootView.findViewById(R.id.stub);
        int width = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 7;
        timeLayout = rootView.findViewById(R.id.time_layout);
        week_layout = rootView.findViewById(R.id.week_layout);
        week_layout.setTimeItemWidthAndHeight(width, width);
        stub.setLayoutParams(new LinearLayout.LayoutParams((CommonUtil.dp2px(getContext(), 40)), width - CommonUtil.dp2px(getContext(), 10)));
        int height = SCREEN_HEIGHT / 9;
        timeLayout.setTimeItemWidthAndHeight(CommonUtil.dp2px(getContext(), 40), height);


         RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(), this,width,height);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        final int spanCount = 7;
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        scoll_view.setOnScrollViewListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
        timeLayout.setOnScrollViewListener(this);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
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
        if (viewGroup == scoll_view) {
            timeLayout.scrollTo(x, y);
        } else if (viewGroup == timeLayout) {
            scoll_view.scrollTo(x, y);

        }
    }






    @OnClick(R.id.ll_edit)
    public void onViewClicked() {
        startActivity(new Intent(getContext(),EditCourseTableActivity.class));
    }
}

