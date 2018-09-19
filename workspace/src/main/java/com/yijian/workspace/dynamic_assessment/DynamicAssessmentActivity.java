package com.yijian.workspace.dynamic_assessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.commonlib.util.DensityUtil;
import com.yijian.staff.R;
import com.yijian.staff.mvp.course.preparelessons.createlession.EditActionObservable;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.bean.DynamicRequestBody;
import com.yijian.workspace.commen.ShareTestActivity;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.utils.GlideApp;
import com.yijian.workspace.widget.CommenPopupWindow;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultStringObserver;
import com.yijian.commonlib.widget.NavigationBar2;
import com.yijian.workspace.R;
import com.yijian.workspace.observe.EditActionObservable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DynamicAssessmentActivity extends MvcBaseActivity {

    private final String tag1 = "com.yijian.workspace.dynamic_assessment.DynamicFragment1";
    private final String tag2 = "com.yijian.workspace.dynamic_assessment.DynamicFragment2";
    private final String tag3 = "com.yijian.workspace.dynamic_assessment.DynamicFragment3";
    private final String tag4 = "com.yijian.workspace.dynamic_assessment.DynamicFragment4";
    private final String tag5 = "com.yijian.workspace.dynamic_assessment.DynamicFragment5";
    private final String tag6 = "com.yijian.workspace.dynamic_assessment.DynamicFragment6";
    private final String tag7 = "com.yijian.workspace.dynamic_assessment.DynamicFragment7";
    private TextView rightTv;
    private CommenPopupWindow popupWindow;
    private EditActionObservable editActionObservable = new EditActionObservable();
    private int currentIndex = 0;
    private Map<String, String> observerMap = new HashMap<>();
    public static String STEP1 = "dynamic_step1";
    public static String STEP2 = "dynamic_step2";
    public static String STEP3 = "dynamic_step3";
    public static String STEP4 = "dynamic_step4";
    public static String STEP5 = "dynamic_step5";
    public static String STEP6 = "dynamic_step6";
    public static String STEP7 = "dynamic_step7";
    public static String STEP7_IMG = "dynamic_step7_img";
    private String[] tags = new String[]{tag1, tag2, tag3, tag4, tag5, tag6, tag7};
    private DynamicRequestBody dynamicRequestBody;

    @BindView(R. id.btn_next)
    Button btn_next;
    @BindView(R. id.lin_next)
    LinearLayout lin_next;
    @BindView(R. id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R. id.line_progress)
    LinearLayout line_progress;
    @BindView(R. id.iv_1)
    ImageView iv_1;
    @BindView(R. id.iv_2)
    ImageView iv_2;
    @BindView(R. id.iv_3)
    ImageView iv_3;
    @BindView(R. id.iv_4)
    ImageView iv_4;
    @BindView(R. id.iv_5)
    ImageView iv_5;
    @BindView(R. id.iv_6)
    ImageView iv_6;
    @BindView(R. id.iv_7)
    ImageView iv_7;
    @BindView(R. id.lin_step1)
    LinearLayout lin_step1;
    @BindView(R. id.lin_step2)
    LinearLayout lin_step2;
    @BindView(R. id.lin_step3)
    LinearLayout lin_step3;
    @BindView(R. id.lin_step4)
    LinearLayout lin_step4;
    @BindView(R. id.lin_step5)
    LinearLayout lin_step5;
    @BindView(R. id.lin_step6)
    LinearLayout lin_step6;
    @BindView(R. id.lin_step7)
    LinearLayout lin_step7;
    @BindView(R. id.dynamic_top_step)
    HorizontalScrollView dynamic_top_step;
    private int progresBase;
    private int ivWidth;
    private int sWidth;

    public DynamicRequestBody getDynamicRequestBody() {
        if (dynamicRequestBody != null) {
            return dynamicRequestBody;
        }
        dynamicRequestBody = new DynamicRequestBody();
        return dynamicRequestBody;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dynamic_assessment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
        initUi();
        initData();
    }

    private void initUi() {
        dynamic_top_step.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        sWidth = DensityUtil.getScreenWidth(this);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) progress_bar.getLayoutParams();
        lp.width = (sWidth / 5) * 7;
        progress_bar.setPadding((lp.width / 5) / 4, 0, (lp.width / 5) / 4, 0);
        progress_bar.setLayoutParams(lp);
        progress_bar.setMax(((sWidth / 5) * 7) - ((lp.width / 5) / 4) * 2);
        iv_1.post(new Runnable() {
            @Override
            public void run() {
                ivWidth = iv_1.getWidth();
                progresBase = sWidth / 5 - ivWidth;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) progress_bar.getLayoutParams();
                lp.topMargin = ivWidth / 2;
                progress_bar.setLayoutParams(lp);
            }
        });

        LinearLayout.LayoutParams lp_step = (LinearLayout.LayoutParams) lin_step1.getLayoutParams();
        lp_step.width = sWidth / 5;
        lin_step1.setLayoutParams(lp_step);
        lin_step2.setLayoutParams(lp_step);
        lin_step3.setLayoutParams(lp_step);
        lin_step4.setLayoutParams(lp_step);
        lin_step5.setLayoutParams(lp_step);
        lin_step6.setLayoutParams(lp_step);
        lin_step7.setLayoutParams(lp_step);

        LinearLayout.LayoutParams lp_iv_1 = (LinearLayout.LayoutParams) iv_1.getLayoutParams();
        lp_iv_1.leftMargin = (lp.width / 5) / 4;
        iv_1.setLayoutParams(lp_iv_1);

        LinearLayout.LayoutParams lp_iv_2 = (LinearLayout.LayoutParams) iv_2.getLayoutParams();
        lp_iv_2.leftMargin = (lp.width / 5) / 4;
        iv_2.setLayoutParams(lp_iv_2);

        LinearLayout.LayoutParams lp_iv_3 = (LinearLayout.LayoutParams) iv_3.getLayoutParams();
        lp_iv_3.leftMargin = (lp.width / 5) / 4;
        iv_3.setLayoutParams(lp_iv_3);

        LinearLayout.LayoutParams lp_iv_4 = (LinearLayout.LayoutParams) iv_4.getLayoutParams();
        lp_iv_4.leftMargin = (lp.width / 5) / 4;
        iv_4.setLayoutParams(lp_iv_4);

        LinearLayout.LayoutParams lp_iv_5 = (LinearLayout.LayoutParams) iv_5.getLayoutParams();
        lp_iv_5.leftMargin = (lp.width / 5) / 4;
        iv_5.setLayoutParams(lp_iv_5);

        LinearLayout.LayoutParams lp_iv_6 = (LinearLayout.LayoutParams) iv_6.getLayoutParams();
        lp_iv_6.leftMargin = (lp.width / 5) / 4;
        iv_6.setLayoutParams(lp_iv_6);

        LinearLayout.LayoutParams lp_iv_7 = (LinearLayout.LayoutParams) iv_7.getLayoutParams();
        lp_iv_7.leftMargin = (lp.width / 5) / 4;
        iv_7.setLayoutParams(lp_iv_7);

    }

    private void setStep(int pro, boolean isNext) {
        if (isNext) { //下一步
            progress_bar.setProgress(pro * progresBase + (pro * iv_1.getWidth()));
            switch (pro) {
                case 1:
                    iv_2.setImageResource(R.mipmap.progress2);
                    break;
                case 2:
                    iv_3.setImageResource(R.mipmap.progress3);
                    break;
                case 3:
                    iv_4.setImageResource(R.mipmap.progress4);
                    break;
                case 4:
                    iv_5.setImageResource(R.mipmap.progress5);
                    break;
                case 5:
                    iv_6.setImageResource(R.mipmap.progress6);
                    dynamic_top_step.smoothScrollBy(2 * sWidth / 5, 0);
                    break;
                case 6:
                    iv_7.setImageResource(R.mipmap.progress7);
                    break;
                default:
            }
        } else { //上一步
            switch (pro) {
                case 0:
                    iv_2.setImageResource(R.mipmap.progress_normal2);
                    break;
                case 1:
                    iv_3.setImageResource(R.mipmap.progress_normal3);
                    break;
                case 2:
                    iv_4.setImageResource(R.mipmap.progress_normal4);
                    break;
                case 3:
                    iv_5.setImageResource(R.mipmap.progress_normal5);
                    break;
                case 4:
                    iv_6.setImageResource(R.mipmap.progress_normal6);
                    dynamic_top_step.smoothScrollBy(-(2 * sWidth / 5), 0);
                    break;
                case 5:
                    iv_7.setImageResource(R.mipmap.progress_normal7);
                    break;
                default:
            }
            progress_bar.setProgress(pro * progresBase + (pro * iv_1.getWidth()));
        }

    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("动作评估");
        navigationBar2.hideLeftSecondIv();
        rightTv = navigationBar2.getmRightTv();
        rightTv.setText("上一步");
        rightTv.setTextColor(getResources().getColor(R.color.blue));
        rightTv.setVisibility(View.GONE);
        navigationBar2.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null) {

                    popupWindow = new CommenPopupWindow(DynamicAssessmentActivity.this, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            if (v.getId() == R.id.tv_sure) {
                                unbindObserver();
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
        ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag1, editActionObservable, tags);
    }


    @OnClick({R.id.right_tv, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next: //下一步
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
                    case 3:
                        observerMap.put("type", STEP4);
                        break;
                    case 4:
                        observerMap.put("type", STEP5);
                        break;
                    case 5:
                        observerMap.put("type", STEP6);
                        break;
                    case 6:
                        observerMap.put("type", STEP7);
                        break;
                    default:
                }
                editActionObservable.notifyObservers(observerMap);
                break;

            case R.id.right_tv: //上一步
                if (currentIndex > 0) {
                    currentIndex--;
                }
                if (currentIndex == 0) {
                    rightTv.setVisibility(View.GONE);
                    lin_next.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag1, editActionObservable, tags);
                } else if (currentIndex == 1) {
                    rightTv.setVisibility(View.VISIBLE);
                    lin_next.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag2, editActionObservable, tags);
                } else if (currentIndex == 2) {
                    rightTv.setVisibility(View.VISIBLE);
                    lin_next.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag3, editActionObservable, tags);
                } else if (currentIndex == 3) {
                    rightTv.setVisibility(View.VISIBLE);
                    lin_next.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag4, editActionObservable, tags);
                } else if (currentIndex == 4) {
                    rightTv.setVisibility(View.VISIBLE);
                    lin_next.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag5, editActionObservable, tags);

                } else if (currentIndex == 5) {
                    rightTv.setVisibility(View.VISIBLE);
                    lin_next.setVisibility(View.VISIBLE);
                    btn_next.setText("下一步");
                    ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag6, editActionObservable, tags);
                }
                setStep(currentIndex, false);
                break;
            default:

        }
    }


    /**
     * 判断是否能下一步
     */
    public void judgeNext() {
        Log.e("Test", "currentIndex ===" + currentIndex);
        if (currentIndex < 7) {
            currentIndex++;
        }
        switch (currentIndex) {
            case 1:
                rightTv.setVisibility(View.VISIBLE);
                lin_next.setVisibility(View.VISIBLE);
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag2, editActionObservable, tags);
                setStep(1, true);
                break;
            case 2:
                rightTv.setVisibility(View.VISIBLE);
                lin_next.setVisibility(View.VISIBLE);
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag3, editActionObservable, tags);
                setStep(2, true);
                break;
            case 3:
                rightTv.setVisibility(View.VISIBLE);
                lin_next.setVisibility(View.VISIBLE);
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag4, editActionObservable, tags);
                setStep(3, true);
                break;
            case 4:
                rightTv.setVisibility(View.VISIBLE);
                lin_next.setVisibility(View.VISIBLE);
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag5, editActionObservable, tags);
                setStep(4, true);
                break;
            case 5:
                rightTv.setVisibility(View.VISIBLE);
                lin_next.setVisibility(View.VISIBLE);
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag6, editActionObservable, tags);
                setStep(5, true);
                break;
            case 6:
                ActivityUtils.showFragment(getSupportFragmentManager(), R.id.fl_dynamic, tag7, editActionObservable, tags);
                rightTv.setVisibility(View.VISIBLE);
                lin_next.setVisibility(View.GONE);
                setStep(6, true);
                break;
            case 7:
//                subData();
                pushData();
                break;
            default:
        }
    }

    private void subData() {

        File dynamicFile = new File(getCacheDir() + "/img_dynamic.jpg");
        if (dynamicFile.exists()) {
            showLoading();
            HttpManagerWorkSpace.upLoadImageHasParam(HttpManagerWorkSpace.WORKSPACE_UPLOAD_FILE__URL, getCacheDir() + "/img_dynamic.jpg", 10,
                    new ResultJSONObjectObserver(getLifecycle()) {
                        @Override
                        public void onSuccess(JSONObject result) {
                            try {
                                JSONArray jsonArray = result.getJSONArray("dataList");
                                String imgUrl1 = jsonArray.getString(0);
                                dynamicRequestBody.setUrl1(imgUrl1);
                                pushData();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            hideLoading();
                            Toast.makeText(DynamicAssessmentActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(DynamicAssessmentActivity.this, "请上传盆骨照", Toast.LENGTH_SHORT).show();
        }
    }

    private void pushData() {
        showLoading();
        HttpManagerWorkSpace.postDynamicInfo(dynamicRequestBody, new ResultStringObserver(getLifecycle()) {

            @Override
            public void onSuccess(String result) {
                hideLoading();
                Toast.makeText(DynamicAssessmentActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("recordId", result);
                ActivityUtils.startActivity(DynamicAssessmentActivity.this, ShareTestActivity.class, bundle);
                unbindObserver();
                finish();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                Toast.makeText(DynamicAssessmentActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void unbindObserver() {
        currentIndex = 0;
        if (editActionObservable != null) {

            BaseSpaceFragment showFragment1 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag1);
            BaseSpaceFragment showFragment2 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag2);
            BaseSpaceFragment showFragment3 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag3);
            BaseSpaceFragment showFragment4 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag4);
            BaseSpaceFragment showFragment5 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag5);
            BaseSpaceFragment showFragment6 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag6);
            BaseSpaceFragment showFragment7 = (BaseSpaceFragment) getSupportFragmentManager().findFragmentByTag(tag7);
            FragmentTransaction showTransaction = getSupportFragmentManager().beginTransaction();
            if (showFragment1 != null) {
                showTransaction.remove(showFragment1);
                editActionObservable.deleteObserver(showFragment1);
            }
            if (showFragment2 != null) {
                showTransaction.remove(showFragment2);
                editActionObservable.deleteObserver(showFragment2);
            }
            if (showFragment3 != null) {
                showTransaction.remove(showFragment3);
                editActionObservable.deleteObserver(showFragment3);
            }
            if (showFragment4 != null) {
                showTransaction.remove(showFragment4);
                editActionObservable.deleteObserver(showFragment4);
            }
            if (showFragment5 != null) {
                showTransaction.remove(showFragment5);
                editActionObservable.deleteObserver(showFragment5);
            }
            if (showFragment6 != null) {
                showTransaction.remove(showFragment6);
                editActionObservable.deleteObserver(showFragment6);
            }
            if (showFragment7 != null) {
                showTransaction.remove(showFragment7);
                editActionObservable.deleteObserver(showFragment7);
            }
            showTransaction.commit();
            editActionObservable = null;
        }
    }

    @Override
    public void onBackPressed() {

        if (popupWindow == null) {

            popupWindow = new CommenPopupWindow(DynamicAssessmentActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    if (v.getId() == R.id.tv_sure) {
                        unbindObserver();
                        finish();
                        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                    }
                }
            }, R.layout.pop_sport_clear, new int[]{R.id.tv_sure, R.id.tv_cancel});

        }
        popupWindow.showAtBottom(getWindow().getDecorView());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (dynamicRequestBody != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String area = bundle.getString("area");
                dynamicRequestBody.setHpMj(area);
            }
        }
        observerMap.put("type", STEP7_IMG);
        editActionObservable.notifyObservers(observerMap);
    }

}
