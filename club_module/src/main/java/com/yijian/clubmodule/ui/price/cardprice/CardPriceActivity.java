package com.yijian.clubmodule.ui.price.cardprice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.ui.price.cardprice.adapter.CardsListAdapter;
import com.yijian.clubmodule.ui.price.cardprice.filter.OptionDialog;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.bean.CardInfo;
import com.yijian.clubmodule.net.requestbody.CardRequestBody;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.DensityUtil;
import com.yijian.commonlib.widget.EmptyView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




/**
 * 会籍（客服）产品报价
 */
@Route(path = "/test/9")
public class CardPriceActivity extends MvcBaseActivity implements HuiJiProductContract.View, View.OnClickListener {


    TextView tvZongHe;
    TextView tvPrice;
    TextView tvShaixuan;

    LinearLayout llroot;
    RecyclerView goodsRcv;

    EmptyView empty_view;
    TextView tvSearch;
    SmartRefreshLayout cardRefreshLayout;

    private CardsListAdapter goodsListAdapter;

    private CardRequestBody bodyCondition;
    private HuiJiProductPresenter presenter;
    private static final String TAG = "CardPriceActivity";



    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvZongHe = findViewById(R.id.tv_zong_he);
        tvPrice = findViewById(R.id.tv_price);
        tvShaixuan = findViewById(R.id.tv_shaixuan);
        llroot = findViewById(R.id.ll_root);
        goodsRcv = findViewById(R.id.rv);
        empty_view = findViewById(R.id.empty_view);
        tvSearch = findViewById(R.id.tv_search);
        cardRefreshLayout = findViewById(R.id.refreshLayout);
        findViewById(R.id.ll_zong_he).setOnClickListener(this);
        findViewById(R.id.ll_price).setOnClickListener(this);
        findViewById(R.id.ll_shai_xuan).setOnClickListener(this);
        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_production_list",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        presenter = new HuiJiProductPresenter(getLifecycle(),this);
        presenter.setView(this);
        bodyCondition = new CardRequestBody();

        initComponent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_goods_list;
    }


    public void initComponent() {

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardPriceActivity.this, SearchCardPriceActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        goodsListAdapter = new CardsListAdapter(this);
        goodsRcv.setAdapter(goodsListAdapter);


        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(this).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        cardRefreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        cardRefreshLayout.setRefreshFooter(footer);
        cardRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                Log.e(TAG, "onRefresh: " + bodyCondition.toString());
                presenter.resetBodyPage(bodyCondition);
                presenter.getRecptionCards(bodyCondition, true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e(TAG, "onLoadMore: " + bodyCondition.toString());
                bodyCondition.setPageNum(bodyCondition.getPageNum() + 1);
                presenter.getRecptionCards(bodyCondition, false);
            }
        });


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

    }


    //点击筛选
    private boolean priceUp = false;




    //点击筛选
    private void selectShaixuan() {
        Bundle bundle = new Bundle();
        bundle.putString("cardType", bodyCondition.getCardType());
        bundle.putString("startPrice", bodyCondition.getStartPrice());
        bundle.putString("venueId", bodyCondition.getVenueId());
        bundle.putString("cardName", bodyCondition.getCardName());

        OptionDialog optionDialog = new OptionDialog(getLifecycle());
        optionDialog.setOnDismissListener(new OptionDialog.OnDismissListener() {
            @Override
            public void onDismiss(CardRequestBody body) {


                String startPrice = body.getStartPrice();
                String endPrice = body.getEndPrice();
                String cardType = body.getCardType();
                String venueId = body.getVenueId();
                String cardName = body.getCardName();

                if (TextUtils.isEmpty(startPrice)
                        && TextUtils.isEmpty(endPrice)
                        && TextUtils.isEmpty(cardType)
                        && TextUtils.isEmpty(venueId)
                        && TextUtils.isEmpty(cardName)) {//重置
                    Drawable drawable1 = getResources().getDrawable(R.mipmap.shaixuan_black);
                    drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                    tvShaixuan.setCompoundDrawablePadding(DensityUtil.dip2px(CardPriceActivity.this, 4));
                    tvShaixuan.setCompoundDrawables(null, null, drawable1, null);
                    tvShaixuan.setTextColor(Color.parseColor("#666666"));

                    tvSearch.setText("输入产品名称，进行搜索");
                    tvSearch.setTextColor(getResources().getColor(R.color.time_bar));
                } else {
                    Drawable drawable = getResources().getDrawable(R.mipmap.shaixuan_blue);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvShaixuan.setCompoundDrawablePadding(DensityUtil.dip2px(CardPriceActivity.this, 4));
                    tvShaixuan.setCompoundDrawables(null, null, drawable, null);
                    tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
                }


                bodyCondition.setStartPrice(startPrice);
                bodyCondition.setEndPrice(endPrice);
                bodyCondition.setCardType(cardType);
                bodyCondition.setVenueId(venueId);
                bodyCondition.setCardName(cardName);
                bodyCondition.setPageSize(4);
                bodyCondition.setPageNum(1);

                Log.e(TAG, "onDismiss: " + bodyCondition.toString());
                presenter.getRecptionCards(bodyCondition, true);

            }
        });

        optionDialog.setArguments(bundle);
        optionDialog.show(getFragmentManager(), "OptionDialog");
    }

    //点击价格
    private void selectPrice() {

        resetStateNoShaixuan();
        tvPrice.setTextColor(Color.parseColor("#1997f8"));
        if (priceUp) {//（0：升序，1：降序） ,
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawablePadding(DensityUtil.dip2px(this, 4));
            tvPrice.setCompoundDrawables(null, null, drawable, null);

            bodyCondition.setIsSortByPrice(0);
        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawablePadding(DensityUtil.dip2px(this, 4));
            tvPrice.setCompoundDrawables(null, null, drawable, null);

            bodyCondition.setIsSortByPrice(1);
        }

        bodyCondition.setPageSize(4);
        bodyCondition.setPageNum(1);
        Log.e(TAG, "selectPrice: " + bodyCondition.toString());
        presenter.getRecptionCards(bodyCondition, true);
    }

    //点击综合
    private void selectZongHe() {

        resetStateNoShaixuan();
        tvZongHe.setTextColor(Color.parseColor("#1997f8"));

        bodyCondition.setPageSize(4);
        bodyCondition.setPageNum(1);
        bodyCondition.setIsSortByPrice(null);
        presenter.getRecptionCards(bodyCondition, true);

    }

    private void resetStateNoShaixuan() {
        tvZongHe.setTextColor(Color.parseColor("#666666"));

        tvPrice.setTextColor(Color.parseColor("#666666"));
        Drawable drawable1 = getResources().getDrawable(R.mipmap.jd_normal_arrow);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        tvPrice.setCompoundDrawables(null, null, drawable1, null);

//        tvShaixuan.setTextColor(Color.parseColor("#666666"));
//        Drawable drawable = getResources().getDrawable(R.mipmap.shaixuan_black);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        tvShaixuan.setCompoundDrawables(null, null, drawable, null);

    }


    @Override
    public void showCards(List<CardInfo> goodsInfos, Boolean isRefresh) {
        empty_view.setVisibility(View.GONE);

        if (isRefresh) {
            goodsListAdapter.resetData(goodsInfos);
            cardRefreshLayout.finishRefresh(1000);
        } else {
            goodsListAdapter.addDatas(goodsInfos);
            cardRefreshLayout.finishLoadMore(1000);
        }

    }


    @Override
    public void showNoCards(boolean isRefresh, boolean isSucceed) {

        if (isRefresh) {
            if (isSucceed) Toast.makeText(this, "未查询到相关数据", Toast.LENGTH_SHORT).show();
            goodsListAdapter.resetData(new ArrayList<>());
            cardRefreshLayout.finishRefresh(1000);
            empty_view.setVisibility(View.VISIBLE);
        } else {
            if (isSucceed) Toast.makeText(this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
            cardRefreshLayout.finishLoadMore(1000);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            String search = data.getStringExtra("search");
            bodyCondition.setCardName(search);
            presenter.getRecptionCards(bodyCondition, true);
            if (TextUtils.isEmpty(search)) {
                tvSearch.setText("输入产品名称，进行搜索");
                tvSearch.setTextColor(getResources().getColor(R.color.time_bar));
            } else {
                tvSearch.setText(search);
                tvSearch.setTextColor(getResources().getColor(R.color.text_black1));
            }
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_zong_he) {
            selectZongHe();

        } else if (i == R.id.ll_price) {
            if (priceUp) {
                priceUp = false;
            } else {
                priceUp = true;
            }
            selectPrice();


        } else if (i == R.id.ll_shai_xuan) {
            selectShaixuan();

        }
    }



}
