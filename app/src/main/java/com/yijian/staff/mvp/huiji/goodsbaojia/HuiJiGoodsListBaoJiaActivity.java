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
import com.yijian.staff.mvp.huiji.goodsbaojia.adapter.CardsListAdapter;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardInfo;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardRequestBody;
import com.yijian.staff.mvp.huiji.goodsbaojia.filter.OptionDialog;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yijian.staff.tab.tools.ContextUtil.getContext;

/**
 * 会籍（客服）产品报价
 */
@Route(path = "/test/9")
public class HuiJiGoodsListBaoJiaActivity extends AppCompatActivity implements HuiJiProductContract.View {


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
    SmartRefreshLayout cardRefreshLayout;

    private List<CardInfo> mGoodsInfoList = new ArrayList<>();
    private CardsListAdapter goodsListAdapter;

    private CardRequestBody bodyCondition;
    private HuiJiProductPresenter presenter;
    private OptionDialog optionDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);

        presenter = new HuiJiProductPresenter(getContext());
        presenter.setView(this);
        bodyCondition = new CardRequestBody();
        initComponent();
    }


    public void initComponent() {

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etSearch.setHintTextColor(Color.parseColor("#999999"));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:

                        String name = etSearch.getText().toString().trim();
                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(HuiJiGoodsListBaoJiaActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                        } else {
                            bodyCondition.setCardName(name);
                            presenter.getRecptionCards(bodyCondition, true);
                        }
                        break;
                }
                return true;
            }
        });


        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
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
                presenter.resetBodyPage(bodyCondition);
                presenter.getRecptionCards(bodyCondition, true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                bodyCondition.setPageSize(bodyCondition.getPageNum() + 1);
                presenter.getRecptionCards(bodyCondition, false);
            }
        });

        optionDialog = new OptionDialog();
        optionDialog.setOnDismissListener(new OptionDialog.OnDismissListener() {
            @Override
            public void onDismiss(CardRequestBody body) {
                resetState();
                tvShaixuan.setTextColor(Color.parseColor("#1997f8"));

                bodyCondition = body;
                bodyCondition.setPageNum(1);
                bodyCondition.setPageSize(4);
                presenter.getRecptionCards(bodyCondition, true);
            }
        });

        selectZongHe();

    }


    //点击筛选
    private boolean priceUp = false;


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
//        resetState();
//        tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
        Bundle bundle = new Bundle();
        bundle.putString("cardType", bodyCondition.getCardType());
        bundle.putString("startPrice", bodyCondition.getStartPrice());
        bundle.putString("venueName", bodyCondition.getVenueName());
        optionDialog.setArguments(bundle);
        optionDialog.show(getFragmentManager(), "OptionDialog");
    }

    //点击价格
    private void selectPrice() {
        if (mGoodsInfoList == null || mGoodsInfoList.size() == 0) return;
        resetState();
        tvPrice.setTextColor(Color.parseColor("#1997f8"));
        if (priceUp) {
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Collections.sort(mGoodsInfoList);
            goodsListAdapter.resetData(mGoodsInfoList);
            priceUp = false;

        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);

            Collections.sort(mGoodsInfoList);
            Collections.reverse(mGoodsInfoList);
            goodsListAdapter.resetData(mGoodsInfoList);
            priceUp = true;
        }

    }

    //点击综合
    private void selectZongHe() {
        resetState();
        tvZongHe.setTextColor(Color.parseColor("#1997f8"));
        bodyCondition.setPageSize(4);
        bodyCondition.setPageNum(1);
        presenter.getRecptionCards(bodyCondition, true);
    }


    private void resetState(){
        tvPrice.setTextColor(Color.parseColor("#666666"));
        tvZongHe.setTextColor(Color.parseColor("#666666"));
        tvShaixuan.setTextColor(Color.parseColor("#666666"));
    }

    @Override
    public void showCards(List<CardInfo> goodsInfos, Boolean isRefresh) {
        mGoodsInfoList = goodsInfos;
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
            if (isSucceed) Toast.makeText(getContext(), "未查询到相关数据", Toast.LENGTH_SHORT).show();
            cardRefreshLayout.finishRefresh(1000);
            goodsListAdapter.resetData(new ArrayList<>());

        } else {
            if (isSucceed) Toast.makeText(getContext(), "已经是最后一页了", Toast.LENGTH_SHORT).show();
            cardRefreshLayout.finishLoadMore(1000);
        }
    }

}
