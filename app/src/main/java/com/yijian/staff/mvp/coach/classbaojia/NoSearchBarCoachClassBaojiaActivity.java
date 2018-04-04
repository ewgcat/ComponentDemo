package com.yijian.staff.mvp.coach.classbaojia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.coach.classbaojia.adapter.ClassListAdapter;
import com.yijian.staff.mvp.coach.classbaojia.bean.ClassInfo;
import com.yijian.staff.mvp.coach.classbaojia.filter.CoachClassFilterBean;
import com.yijian.staff.mvp.coach.classbaojia.filter.CoachClassFilterDialog;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoSearchBarCoachClassBaojiaActivity extends AppCompatActivity {


    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<ClassInfo> mClassInfoList = new ArrayList<>();
    private ClassListAdapter classListAdapter;
    private CoachClassFilterDialog coachClassFilterDialog;
    private ClassInfo selectedClassInfo;

    private CoachClassFilterBean coachClassFilterBean;
    private int pageNum = 1;
    private int pageSize = 4;
    private int pages;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_search_bar_coach_class_baojia);

        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("请稍后...");
        initComponent();

    }




    public void initComponent() {

        NavigationBar2 navigationBar2 = findViewById(R.id.no_search_bar_navigationbar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("产品报价");

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        classListAdapter = new ClassListAdapter(this, mClassInfoList);
        goodsRcv.setAdapter(classListAdapter);


        classListAdapter.setOnItemClickListener(new ClassListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, ClassInfo classInfo) {
                selectedClassInfo = classInfo;
            }
        });
        coachClassFilterDialog = new CoachClassFilterDialog(this);
        coachClassFilterDialog.setOnDismissListener(new CoachClassFilterDialog.OnDismissListener() {
            @Override
            public void onDismiss(CoachClassFilterBean coachClassFilterBean) {
                refresh(coachClassFilterBean);
            }
        });

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

        selectZongHe();

    }


    private void refresh(CoachClassFilterBean coachClassFilterBean) {
        this.coachClassFilterBean = coachClassFilterBean;

        pageNum = 1;
        pageSize = 4;
        CoachPrivateCourseRequestBody body = new CoachPrivateCourseRequestBody();
        body.setPageNum(pageNum);
        body.setPageSize(pageSize);
        body.setIsSortByPrice(isSortByPrice+"");
        if (coachClassFilterBean != null) {
            body.setIndate(coachClassFilterBean.getIndate());
            body.setLconsumingMinute(coachClassFilterBean.getLconsumingMinute());
            body.setRconsumingMinute(coachClassFilterBean.getRconsumingMinute());
            body.setLtotalPrice(coachClassFilterBean.getLtotalPrice());
            body.setRtotalPrice(coachClassFilterBean.getRtotalPrice());
            body.setLcourseNum(coachClassFilterBean.getLcourseNum());
            body.setRcourseNum(coachClassFilterBean.getRcourseNum());
        }
        loadingDialog.show();
        HttpManager.getCoachPrivateCourseList( body, new ResultObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                mClassInfoList.clear();
                refreshLayout.finishRefresh(2000, true);

                pageNum = JsonUtil.getInt(result, "current") + 1;
                pages = JsonUtil.getInt(result, "pages");
                JSONArray records = JsonUtil.getJsonArray(result, "records");
                try {
                    for (int i = 0; i < records.length(); i++) {

                        JSONObject o = (JSONObject) records.get(i);
                        ClassInfo classInfo = new ClassInfo(o);
                        mClassInfoList.add(classInfo);
                    }
                    classListAdapter.update(mClassInfoList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                loadingDialog.hide();

            }
            @Override
            public void onFail(String msg) {
                loadingDialog.hide();

                refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                Toast.makeText(NoSearchBarCoachClassBaojiaActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadMore() {


        CoachPrivateCourseRequestBody body = new CoachPrivateCourseRequestBody();
        body.setPageNum(pageNum);
        body.setPageSize(pageSize);
        body.setIsSortByPrice(isSortByPrice+"");
        if (coachClassFilterBean != null) {
            body.setIndate(coachClassFilterBean.getIndate());
            body.setLconsumingMinute(coachClassFilterBean.getLconsumingMinute());
            body.setRconsumingMinute(coachClassFilterBean.getRconsumingMinute());
            body.setLtotalPrice(coachClassFilterBean.getLtotalPrice());
            body.setRtotalPrice(coachClassFilterBean.getRtotalPrice());
            body.setLcourseNum(coachClassFilterBean.getLcourseNum());
            body.setRcourseNum(coachClassFilterBean.getRcourseNum());
        }

        loadingDialog.show();
            HttpManager.getCoachPrivateCourseList( body, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    pageNum = JsonUtil.getInt(result, "current") + 1;
                    pages = JsonUtil.getInt(result, "pages");
                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    try {
                        for (int i = 0; i < records.length(); i++) {

                            JSONObject o = (JSONObject) records.get(i);
                            ClassInfo classInfo = new ClassInfo(o);
                            mClassInfoList.add(classInfo);
                        }
                        classListAdapter.update(mClassInfoList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loadingDialog.hide();
                }
                @Override
                public void onFail(String msg) {
                    loadingDialog.hide();

                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                    Toast.makeText(NoSearchBarCoachClassBaojiaActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
    }


    //点击筛选
    private void selectShaixuan() {
        if (tvShaixuan.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            isSortByPrice = -1;
            showFilterDialog();
        } else {
            tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
            tvZongHe.setTextColor(Color.parseColor("#666666"));
            tvPrice.setTextColor(Color.parseColor("#666666"));
            Drawable drawablePrice = getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawablePrice.setBounds(0, 0, drawablePrice.getMinimumWidth(), drawablePrice.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawablePrice, null);
            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_blue);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            isSortByPrice = -1;
            showFilterDialog();
        }
    }

    private boolean priceUp = false;
    private int isSortByPrice = -1;

    //点击价格
    private void selectPrice() {
        if (tvPrice.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            if (priceUp) {
                Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = false;
                isSortByPrice = 1;
                refresh(coachClassFilterBean);
            } else {
                Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = true;
                isSortByPrice = 0;
                coachClassFilterBean = null;
                refresh(coachClassFilterBean);
            }
        } else {
            tvPrice.setTextColor(Color.parseColor("#1997f8"));
            tvZongHe.setTextColor(Color.parseColor("#666666"));
            tvShaixuan.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_black);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            isSortByPrice = 0;
            coachClassFilterBean = null;
            refresh(coachClassFilterBean);
        }
    }

    private void selectZongHe() {
        if (tvZongHe.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            isSortByPrice = -1;
            coachClassFilterBean = null;
            refresh(coachClassFilterBean);
        } else {
            tvZongHe.setTextColor(Color.parseColor("#1997f8"));
            tvPrice.setTextColor(Color.parseColor("#666666"));
            tvShaixuan.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_black);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
            isSortByPrice = -1;
            coachClassFilterBean = null;
            refresh(coachClassFilterBean);
        }
    }


    private void showFilterDialog() {
        coachClassFilterDialog.showFilterDialog();
    }

    @OnClick({R.id.ll_zong_he, R.id.ll_price, R.id.ll_shai_xuan, R.id.ll_post})
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
            case R.id.ll_post:
                startActivity(new Intent(NoSearchBarCoachClassBaojiaActivity.this, CoachClassBaoJiaCompleteActivity.class));
                break;

        }
    }
}