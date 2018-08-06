package com.yijian.staff.mvp.course.timetable.schedule.week;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ScheduleWeekFragment extends MvcBaseFragment implements ScrollViewListener {

    private DragViewGroup group;
    private View stub;
    private TimeLayout timeLayout;
    private WeekLayout week_layout;
    private static String TAG = ScheduleWeekFragment.class.getSimpleName();

    private List<TextView> timeViews = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedule_week;
    }

    @Override
    public void initView() {
        getScreenSize();

        stub = rootView.findViewById(R.id.stub);
        int i = ((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 8;
        timeLayout = rootView.findViewById(R.id.time_layout);
        week_layout = rootView.findViewById(R.id.week_layout);
        week_layout.setTimeItemWidthAndHeight(i, i);
        stub.setLayoutParams(new LinearLayout.LayoutParams((CommonUtil.dp2px(getContext(), 40)), i - CommonUtil.dp2px(getContext(), 10)));
        timeLayout.setTimeItemWidthAndHeight(CommonUtil.dp2px(getContext(), 40), SCREEN_HEIGHT / 8);


        group = (DragViewGroup) rootView.findViewById(R.id.mydragview);
        group.initContext(getContext());
        group.setAdapter(baseAdapter);
        group.setOnScrollViewListener(this);
        timeLayout.setOnScrollViewListener(this);

        group.setItemClickListener(new DragViewGroup.ItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                Toast.makeText(getContext(), "第" + pos + "被点击", Toast.LENGTH_SHORT).show();
            }
        });
        group.setChildPositionChangeListener(new DragViewGroup.ChildPositionChangeListener() {
            @Override
            public void onChildPositionChange(int pos1, View view1, int pos2, View view2) {
                Toast.makeText(getContext(), "第" + pos1 + "个元素和第" + pos2 + "个元素交换了位置", Toast.LENGTH_SHORT).show();
            }
        });

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

    private BaseAdapter baseAdapter = new BaseAdapter() {

        private int[] colors = new int[]{Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW};
        private Random random = new Random();

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t = new TextView(parent.getContext());
            if (position == 21) {
                t.setBackground(getContext().getDrawable(R.drawable.green_board));

            } else {

                t.setBackground(getContext().getDrawable(R.drawable.board));
            }

            t.setText(position + "");
            t.setGravity(Gravity.CENTER);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(((SCREEN_WIDTH - CommonUtil.dp2px(getContext(), 40))) / 8, SCREEN_HEIGHT / 8);
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


    @Override
    public void onScrollChanged(ViewGroup viewGroup, int x, int y, int oldx, int oldy) {
        if (viewGroup == group) {
            timeLayout.scrollTo(x, y);
        } else if (viewGroup == timeLayout) {
            group.scrollTo(x, y);
        }
    }
}

