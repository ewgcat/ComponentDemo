package com.yijian.workspace.sport;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.net.response.ResultStringObserver;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.bean.SportStepRequedtBody;
import com.yijian.workspace.commen.ShareTestActivity;
import com.yijian.workspace.net.HttpManagerWorkSpace;
import com.yijian.workspace.observe.EditActionObservable;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.widget.CommenPopupWindow;

import java.util.HashMap;
import java.util.Map;

public class SportTestActivity extends MvcBaseActivity implements View.OnClickListener {

    private final String tag1 = "com.yijian.workspace.sport.SportFragment1";
    private final String tag2 = "com.yijian.workspace.sport.SportFragment2";
    private final String tag3 = "com.yijian.workspace.sport.SportFragment3";
    private int currentIndex = 0;

    private TextView rightTv;
    Button btn_next;
    View view_mingjie_sel;
    View view_rouren_sel;
    ImageView iv_mingjie_sel;
    ImageView iv_rouren_sel;
    private EditActionObservable editActionObservable = new EditActionObservable();
    private Map<String, String> observerMap = new HashMap<>();
    public static String STEP1 = "sport_step1";
    public static String STEP2 = "sport_step2";
    public static String STEP3 = "sport_step3";
    public static String TYPE_STEP_NEXT_SUCCESS = "type_step1_success";
    private SportStepRequedtBody sportStepRequedtBody = new SportStepRequedtBody();
    private CommenPopupWindow popupWindow;

    public SportStepRequedtBody getSportStepRequedtBody() {
        return sportStepRequedtBody;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_sport_test1;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);


        btn_next = findViewById(R.id.btn_next);
        view_mingjie_sel = findViewById(R.id.view_mingjie_sel);
        view_rouren_sel = findViewById(R.id.view_rouren_sel);
        iv_mingjie_sel = findViewById(R.id.iv_mingjie_sel);
        iv_rouren_sel = findViewById(R.id.iv_rouren_sel);

        findViewById(R.id.right_tv).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        initTitle();
        initData();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("运动表现测试");
        navigationBar.hideLeftSecondIv();
        rightTv = navigationBar.getmRightTv();
        rightTv.setText("上一步");
        rightTv.setTextColor(getResources().getColor(R.color.blue));
        rightTv.setVisibility(View.GONE);
        navigationBar.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null) {

                    popupWindow = new CommenPopupWindow(SportTestActivity.this, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            if (v.getId() == R.id.tv_sure) {
                                finish();
                            }
                        }
                    }, R.layout.pop_sport_clear, new int[]{R.id.tv_sure, R.id.tv_cancel});

                }
                popupWindow.showAtBottom(getWindow().getDecorView());
            }
        });
    }

    private void initData() {
        ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag1, editActionObservable, new String[]{tag1, tag2, tag3});
    }


    /**
     * 判断是否能下一步
     */
    public void judgeNext(Object data) {
        String type = (String) data;
        if (TYPE_STEP_NEXT_SUCCESS.equals(type)) { //第一步提交成功
            if (currentIndex < 3) {
                currentIndex++;
            }
            switch (currentIndex) {
                case 1:
                    rightTv.setVisibility(View.VISIBLE);
                    view_mingjie_sel.setVisibility(View.VISIBLE);
                    iv_mingjie_sel.setVisibility(View.VISIBLE);
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag2, editActionObservable, new String[]{tag1, tag2, tag3});
                    break;
                case 2:
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag3, editActionObservable, new String[]{tag1, tag2, tag3});
                    btn_next.setText("完成");
                    rightTv.setVisibility(View.VISIBLE);
                    view_rouren_sel.setVisibility(View.VISIBLE);
                    iv_rouren_sel.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    showLoading();
                    HttpManagerWorkSpace.postSportInfo(sportStepRequedtBody, new ResultStringObserver(getLifecycle()) {

                        @Override
                        public void onSuccess(String result) {
                            hideLoading();
                            Toast.makeText(SportTestActivity.this, "提交成功", Toast.LENGTH_SHORT).show();

                            currentIndex = 0;
                            if (editActionObservable != null) {

                                BaseSpaceFragment showFragment1 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag1);
                                BaseSpaceFragment showFragment2 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag2);
                                BaseSpaceFragment showFragment3 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag3);
                                if (showFragment1 != null) {
                                    FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                                    showTransaction.remove(showFragment1);
                                    showTransaction.commit();
                                    editActionObservable.deleteObserver(showFragment1);
                                }
                                if (showFragment2 != null) {
                                    FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                                    showTransaction.remove(showFragment1);
                                    showTransaction.remove(showFragment2);
                                    editActionObservable.deleteObserver(showFragment2);
                                }
                                if (showFragment3 != null) {
                                    FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                                    showTransaction.remove(showFragment1);
                                    showTransaction.remove(showFragment3);
                                    editActionObservable.deleteObserver(showFragment3);
                                }
                            }

                            Bundle bundle = new Bundle();
                            bundle.putString("recordId", result);
                            ActivityUtils.startActivity(SportTestActivity.this, ShareTestActivity.class, bundle);
                            finish();
                        }

                        @Override
                        public void onFail(String msg) {
                            hideLoading();
                            Toast.makeText(SportTestActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                default:
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentIndex = 0;
        if (editActionObservable != null) {

            BaseSpaceFragment showFragment1 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag1);
            BaseSpaceFragment showFragment2 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag2);
            BaseSpaceFragment showFragment3 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag3);
            if (showFragment1 != null) {
                FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                showTransaction.remove(showFragment1);
                showTransaction.commit();
                editActionObservable.deleteObserver(showFragment1);
            }
            if (showFragment2 != null) {
                FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                showTransaction.remove(showFragment1);
                showTransaction.remove(showFragment2);
                editActionObservable.deleteObserver(showFragment2);
            }
            if (showFragment3 != null) {
                FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
                showTransaction.remove(showFragment1);
                showTransaction.remove(showFragment3);
                editActionObservable.deleteObserver(showFragment3);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_next) {
            switch (currentIndex) {
                case 0:
                    observerMap.put("type", STEP1);
                    break;
                case 1:
                    observerMap.put("type", STEP2);
                    break;
                case 2:
                    observerMap.put("type", STEP3);
                    break;
                default:
            }
            editActionObservable.notifyObservers(observerMap);

        } else if (i == R.id.right_tv) {
            if (currentIndex > 0) {
                currentIndex--;
            }
            if (currentIndex == 0) {
                view_mingjie_sel.setVisibility(View.GONE);
                iv_mingjie_sel.setVisibility(View.GONE);
                rightTv.setVisibility(View.GONE);
                btn_next.setText("下一步");
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag1, editActionObservable, new String[]{tag1, tag2, tag3});
            } else if (currentIndex == 1) {
                view_rouren_sel.setVisibility(View.GONE);
                iv_rouren_sel.setVisibility(View.GONE);
                rightTv.setVisibility(View.VISIBLE);
                btn_next.setText("下一步");
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_sport, tag2, editActionObservable, new String[]{tag1, tag2, tag3});
            }

        } else {
        }
    }
}
