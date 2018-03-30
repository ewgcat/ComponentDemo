package com.yijian.staff.mvp.huiji.goodsbaojia;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.coach.classbaojia.CoachClassBaoJiaActivity;
import com.yijian.staff.mvp.coach.classbaojia.adapter.ClassListAdapter;
import com.yijian.staff.mvp.coach.classbaojia.bean.ClassInfo;
import com.yijian.staff.mvp.huiji.goodsbaojia.adapter.GoodsListAdapter;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.GoodsInfo;
import com.yijian.staff.mvp.huiji.goodsbaojia.filter.HuiJiFilterGoodsDialog;
import com.yijian.staff.mvp.huiji.goodsbaojia.filter.HuiJiGoodsFilterBean;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.huijigoods.HuiJiGoodsRequestBody;
import com.yijian.staff.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.JsonUtil;

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

/**
 * 会籍（客服）产品报价
 */
@Route(path = "/test/9")
public class HuiJiGoodsListBaoJiaActivity extends AppCompatActivity {


    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<GoodsInfo> mGoodsInfoList = new ArrayList<>();
    private GoodsListAdapter goodsListAdapter;
    private HuiJiFilterGoodsDialog huiJiFilterGoodsDialog;
    private GoodsInfo selectedGoodsInfo;
    private HuiJiGoodsFilterBean huiJiGoodsFilterBean;

    private int pageNum = 1;
    private int pageSize = 4;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {



        initComponent();


        selectZongHe();

    }

    public void initComponent() {

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etSearch.setHintTextColor(Color.parseColor("#ffffff"));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        refresh(huiJiGoodsFilterBean);
                        break;
                }
                return true;
            }
        });

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        goodsListAdapter = new GoodsListAdapter(this, mGoodsInfoList);
        goodsRcv.setAdapter(goodsListAdapter);

        huiJiFilterGoodsDialog = new HuiJiFilterGoodsDialog(this);
        huiJiFilterGoodsDialog.setOnDismissListener(new HuiJiFilterGoodsDialog.OnDismissListener() {
            @Override
            public void onDismiss(HuiJiGoodsFilterBean huiJiGoodsFilterBean) {
                refresh(huiJiGoodsFilterBean);
            }
        });

        goodsListAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, GoodsInfo goodsInfo) {
                selectedGoodsInfo = goodsInfo;
                //TODO 跳转到商品详情
            }
        });


        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#FFFFFF"));
        refreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                mGoodsInfoList.clear();
                refresh(huiJiGoodsFilterBean);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void refresh(HuiJiGoodsFilterBean huiJiGoodsFilterBean) {
        pageNum = 1;
        pageSize = 4;
        this.huiJiGoodsFilterBean = huiJiGoodsFilterBean;
        Map<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        String token = user.getToken();
        header.put("token", token);

        String name = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        } else {
            HuiJiGoodsRequestBody body = new HuiJiGoodsRequestBody();
            body.setCardName(name);
            body.setPageNum(pageNum);
            body.setPageSize(pageSize);
            body.setIsSortByPrice(isSortByPrice );

            if (huiJiGoodsFilterBean != null) {
                body.setCardType(huiJiGoodsFilterBean.getCardType());
                body.setStartPrice(huiJiGoodsFilterBean.getStartPrice());
                body.setEndPrice(huiJiGoodsFilterBean.getEndPrice());
                body.setVenueName(huiJiGoodsFilterBean.getVenueName());
            }

            HttpManager.getHuiJiCardGoodsList(header, body, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    mGoodsInfoList.clear();
                    refreshLayout.finishRefresh(2000, true);

                    pageNum = JsonUtil.getInt(result, "current") + 1;
                    pages = JsonUtil.getInt(result, "pages");
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    try {
                        for (int i = 0; i < records.length(); i++) {

                            JSONObject o = (JSONObject) records.get(i);
                            GoodsInfo goodsInfo = new GoodsInfo(o);
                            mGoodsInfoList.add(goodsInfo);
                        }
                        goodsListAdapter.update(mGoodsInfoList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String msg) {
                    refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                    Toast.makeText(HuiJiGoodsListBaoJiaActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void loadMore() {
        Map<String, String> header = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        String token = user.getToken();
        header.put("token", token);

        String name = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        } else {

            HuiJiGoodsRequestBody body = new HuiJiGoodsRequestBody();
            body.setCardName(name);
            body.setPageNum(pageNum);
            body.setPageSize(pageSize);
            body.setIsSortByPrice(isSortByPrice );

            if (huiJiGoodsFilterBean != null) {
                body.setCardType(huiJiGoodsFilterBean.getCardType());
                body.setStartPrice(huiJiGoodsFilterBean.getStartPrice());
                body.setEndPrice(huiJiGoodsFilterBean.getEndPrice());
                body.setVenueName(huiJiGoodsFilterBean.getVenueName());
            }

            HttpManager.getHuiJiCardGoodsList(header, body, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    pageNum = JsonUtil.getInt(result, "pageNum") + 1;
                    pages = JsonUtil.getInt(result, "pages");

                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
                    JSONArray records = JsonUtil.getJsonArray(result, "records");
                    try {
                            for (int i = 0; i < records.length(); i++) {

                                JSONObject o = (JSONObject) records.get(i);
                                GoodsInfo goodsInfo = new GoodsInfo(o);
                                mGoodsInfoList.add(goodsInfo);
                            }
                            goodsListAdapter.update(mGoodsInfoList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFail(String msg) {
                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, false, hasMore);//传入false表示刷新失败
                    Toast.makeText(HuiJiGoodsListBaoJiaActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    //点击筛选
    private void selectShaixuan() {
        if (tvShaixuan.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            showFilterDialog();
            isSortByPrice=-1;
            priceUp = false;
            huiJiGoodsFilterBean = null;
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
            showFilterDialog();
            isSortByPrice=-1;
            priceUp = false;
            huiJiGoodsFilterBean = null;
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
                huiJiGoodsFilterBean = null;
            } else {
                Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvPrice.setCompoundDrawables(null, null, drawable, null);
                priceUp = true;
                isSortByPrice = 0;
                huiJiGoodsFilterBean = null;

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
            huiJiGoodsFilterBean = null;
        }
    }

    private void selectZongHe() {
        if (tvZongHe.getTextColors().getDefaultColor() == Color.parseColor("#1997f8")) {
            isSortByPrice = -1;
            huiJiGoodsFilterBean = null;
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
            huiJiGoodsFilterBean = null;
        }

    }


    private void showFilterDialog() {
        huiJiFilterGoodsDialog.showFilterDialog();
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

}
