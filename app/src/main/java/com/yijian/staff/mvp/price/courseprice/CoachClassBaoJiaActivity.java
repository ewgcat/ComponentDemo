package com.yijian.staff.mvp.price.courseprice;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.price.courseprice.adapter.ClassListAdapter;
import com.yijian.staff.bean.ClassInfo;
import com.yijian.staff.mvp.price.courseprice.filter.CoachClassFilterBean;
import com.yijian.staff.mvp.price.courseprice.filter.OptionDialog;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * （教练）产品报价
 */
@Route(path = "/test/18")
public class CoachClassBaoJiaActivity extends MvcBaseActivity {


    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;


    @BindView(R.id.rv)
    RecyclerView goodsRcv;
    @BindView(R.id.empty_view)
    EmptyView empty_view;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<ClassInfo> mClassInfoList = new ArrayList<>();
    private ClassListAdapter classListAdapter;
    private ClassInfo selectedClassInfo;
    private EditText etSearch;
    private CoachClassFilterBean coachClassFilterBean = new CoachClassFilterBean();
    private int pageNum = 1;
    private int pageSize = 4;
    private int pages;
    private OptionDialog optionDialog;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_coach_goods_bao_jia;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_course_price",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        initComponent();

        selectZongHe();
        empty_view.setButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvZongHe.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
                    empty_view.setVisibility(View.GONE);
                    selectZongHe();
                } else if (tvPrice.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
                    empty_view.setVisibility(View.GONE);
                    selectPrice();
                }
            }
        });


        optionDialog = new OptionDialog();
        optionDialog.setOnDismissListener(new OptionDialog.OnDismissListener() {
            @Override
            public void onDismiss(CoachClassFilterBean body) {


                if (body == null) {//属于重置
                    Drawable drawable = getResources().getDrawable(R.mipmap.shaixuan_black);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvShaixuan.setCompoundDrawablePadding(DensityUtil.dip2px(CoachClassBaoJiaActivity.this, 4));
                    tvShaixuan.setCompoundDrawables(null, null, drawable, null);
                    tvShaixuan.setTextColor(Color.parseColor("#666666"));
                    refresh(body);

                } else {//有条件
                    Drawable drawable = getResources().getDrawable(R.mipmap.shaixuan_blue);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvShaixuan.setCompoundDrawablePadding(DensityUtil.dip2px(CoachClassBaoJiaActivity.this, 4));
                    tvShaixuan.setCompoundDrawables(null, null, drawable, null);
                    tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
                    refresh(body);
                }


            }
        });

    }


    public void initComponent() {

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etSearch = findViewById(R.id.et_search);

        etSearch.setHintTextColor(Color.parseColor("#999999"));

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        String name = etSearch.getText().toString().trim();

                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(CoachClassBaoJiaActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                        } else {
                            refresh(coachClassFilterBean);
                        }
                        break;
                }
                return true;
            }
        });

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        classListAdapter = new ClassListAdapter(this, mClassInfoList);
        goodsRcv.setAdapter(classListAdapter);

        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                mClassInfoList.clear();
                refresh(coachClassFilterBean);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void refresh(CoachClassFilterBean coachClassFilterBean) {
        pageNum = 1;
        pageSize = 4;
        this.coachClassFilterBean = coachClassFilterBean;
        mClassInfoList.clear();

        empty_view.setVisibility(View.GONE);

        String name = etSearch.getText().toString().trim();

        CoachPrivateCourseRequestBody body = new CoachPrivateCourseRequestBody();
        body.setCourseName(name);
        body.setPageNum(pageNum);
        body.setPageSize(pageSize);
        body.setIsSortByPrice(isSortByPrice + "");

        if (coachClassFilterBean != null) {
            body.setIndate(coachClassFilterBean.getIndate());
            body.setLconsumingMinute(coachClassFilterBean.getLconsumingMinute());
            body.setRconsumingMinute(coachClassFilterBean.getRconsumingMinute());
            body.setLtotalPrice(coachClassFilterBean.getLtotalPrice());
            body.setRtotalPrice(coachClassFilterBean.getRtotalPrice());
            body.setLcourseNum(coachClassFilterBean.getLcourseNum());
            body.setRcourseNum(coachClassFilterBean.getRcourseNum());
        }

        showLoading();
        HttpManager.getCoachPrivateCourseList(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();

                mClassInfoList.clear();
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject o = (JSONObject) records.get(i);
                        ClassInfo classInfo = new ClassInfo(o);
                        mClassInfoList.add(classInfo);
                    }
                    classListAdapter.notifyDataSetChanged();
                    if (mClassInfoList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(CoachClassBaoJiaActivity.this, msg, Toast.LENGTH_SHORT).show();
                classListAdapter.notifyDataSetChanged();

                if (mClassInfoList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    private void loadMore() {
        empty_view.setVisibility(View.GONE);

        Map<String, String> header = new HashMap<>();
        String name = etSearch.getText().toString().trim();

        CoachPrivateCourseRequestBody body = new CoachPrivateCourseRequestBody();
        body.setCourseName(name);
        body.setPageNum(pageNum);
        body.setPageSize(pageSize);
        body.setIsSortByPrice(isSortByPrice + "");
        if (coachClassFilterBean != null) {
            body.setIndate(coachClassFilterBean.getIndate());
            body.setLconsumingMinute(coachClassFilterBean.getLconsumingMinute());
            body.setRconsumingMinute(coachClassFilterBean.getRconsumingMinute());
            body.setLtotalPrice(coachClassFilterBean.getLtotalPrice());
            body.setRtotalPrice(coachClassFilterBean.getRtotalPrice());
            body.setLcourseNum(coachClassFilterBean.getLcourseNum());
            body.setRcourseNum(coachClassFilterBean.getRcourseNum());
        }
        showLoading();
        HttpManager.getCoachPrivateCourseList(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                hideLoading();
                pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                pages = JsonUtil.getInt(result, "pages");

                refreshLayout.finishLoadMore(2000, true, false);//传入false表示刷新失败
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject o = (JSONObject) records.get(i);
                        ClassInfo classInfo = new ClassInfo(o);
                        mClassInfoList.add(classInfo);
                    }
                    classListAdapter.notifyDataSetChanged();

                    if (mClassInfoList.size() == 0) {
                        empty_view.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                refreshLayout.finishLoadMore(2000, false, false);//传入false表示刷新失败
                Toast.makeText(CoachClassBaoJiaActivity.this, msg, Toast.LENGTH_SHORT).show();
                classListAdapter.notifyDataSetChanged();

                if (mClassInfoList.size() == 0) {
                    empty_view.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    @OnClick({R.id.ll_zong_he, R.id.ll_price, R.id.ll_shai_xuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zong_he:
                selectZongHe();
                break;
            case R.id.ll_price:
                selectPrice();
                break;
            case R.id.ll_shai_xuan:
                selectShaixuan();
                break;

        }
    }

    //点击筛选
    private void selectShaixuan() {

        tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
        Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_blue);
        drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
        tvShaixuan.setCompoundDrawablePadding(DensityUtil.dip2px(CoachClassBaoJiaActivity.this, 4));
        tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);

        Bundle bundle = new Bundle();
        bundle.putSerializable("coachClassFilterBean", coachClassFilterBean);
        optionDialog.setArguments(bundle);
        optionDialog.show(getFragmentManager(), "OptionDialog");

    }

    private boolean priceUp = false;
    private int isSortByPrice = -1;

    //点击价格
    private void selectPrice() {
        tvPrice.setTextColor(Color.parseColor("#1997f8"));
        tvZongHe.setTextColor(Color.parseColor("#666666"));
        if (priceUp) {
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawablePadding(DensityUtil.dip2px(CoachClassBaoJiaActivity.this, 4));

            tvPrice.setCompoundDrawables(null, null, drawable, null);
            priceUp = false;
            isSortByPrice = 1;
            refresh(coachClassFilterBean);


        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawablePadding(DensityUtil.dip2px(CoachClassBaoJiaActivity.this, 4));

            tvPrice.setCompoundDrawables(null, null, drawable, null);
            priceUp = true;
            isSortByPrice = 0;
            refresh(coachClassFilterBean);


        }


    }

    private void selectZongHe() {
        if (tvZongHe.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            isSortByPrice = -1;
            refresh(coachClassFilterBean);
        } else {
            tvZongHe.setTextColor(Color.parseColor("#1997f8"));
            tvPrice.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_normal_arrow);
            tvPrice.setCompoundDrawablePadding(DensityUtil.dip2px(CoachClassBaoJiaActivity.this, 4));

            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            tvPrice.setCompoundDrawables(null, null, drawable, null);
            isSortByPrice = -1;
            refresh(coachClassFilterBean);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyBoard(etSearch);
    }
}
