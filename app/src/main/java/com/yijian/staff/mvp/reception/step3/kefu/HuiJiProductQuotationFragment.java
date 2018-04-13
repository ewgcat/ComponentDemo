package com.yijian.staff.mvp.reception.step3.kefu;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yijian.staff.R;
import com.yijian.staff.mvp.huiji.goodsbaojia.filter.HuiJiFilterGoodsDialog;
import com.yijian.staff.mvp.huiji.goodsbaojia.filter.HuiJiGoodsFilterBean;
import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HuiJiProductQuotationFragment extends Fragment implements HuiJiProductContract.View{

    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.tv_send_to_status)
    TextView tvSendToStatus;

    @BindView(R.id.tv_zong_he)
    TextView tvZongHe;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;

    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;

    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout cardRefreshLayout;

    private static final String TAG = "HuiJiProductQuotationFr";
    private List<CardInfo> mGoodsInfoList ;
    private CardsListAdapter goodsListAdapter;
    private HuiJiFilterGoodsDialog huiJiFilterGoodsDialog;
    private HuiJiGoodsFilterBean huiJiGoodsFilterBean;
    private CardInfo selectedGoodsInfo;

    private ConditionBody bodyCondition;
    private HuiJiProductPresenter presenter;
    private OptionDialog optionDialog;


    public HuiJiProductQuotationFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hui_ji_product_quotation, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new HuiJiProductPresenter(getContext());
        presenter.setView(this);
        bodyCondition = new ConditionBody();
        initView();

        return view;
    }

    private void initView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局
        goodsRcv.setLayoutManager(layoutmanager);
        goodsListAdapter = new CardsListAdapter(getContext());
        goodsRcv.setAdapter(goodsListAdapter);


        //设置 Header 为 BezierRadar 样式
        BezierRadarHeader header = new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true);
        header.setPrimaryColor(Color.parseColor("#1997f8"));
        cardRefreshLayout.setRefreshHeader(header);
        //设置 Footer 为 球脉冲
        BallPulseFooter footer = new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale);
        footer.setAnimatingColor(Color.parseColor("#1997f8"));
        cardRefreshLayout.setRefreshFooter(footer);
        cardRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.resetBodyPage(bodyCondition);
                presenter.getRecptionCards(cardRefreshLayout,bodyCondition,true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                bodyCondition.setPageSize(bodyCondition.getPageNum()+1);
                presenter.getRecptionCards(cardRefreshLayout,bodyCondition,false);
            }
        });

        optionDialog = new OptionDialog();
        optionDialog.setOnDismissListener(new OptionDialog.OnDismissListener() {
            @Override
            public void onDismiss(ConditionBody body) {

                bodyCondition=body;
                bodyCondition.setPageNum(1);
                bodyCondition.setPageSize(4);
                presenter.getRecptionCards(cardRefreshLayout,bodyCondition,true);
            }
        });
        goodsListAdapter.setOnItemClickListener(new CardsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, CardInfo goodsInfo) {
                selectedGoodsInfo = goodsInfo;

            }
        });
        selectZongHe();
    }




    //点击筛选
    private void selectShaixuan() {
        priceUp = false;
        resetTabColor();
        tvShaixuan.setTextColor(Color.parseColor("#1997f8"));
        optionDialog.show(getActivity().getFragmentManager(),"OptionDialog");

    }

    private boolean priceUp = false;

    //点击价格
    private void selectPrice() {
        if (mGoodsInfoList==null||mGoodsInfoList.size()==0)return;
        Log.e(TAG, "mGoodsInfoList"+mGoodsInfoList.size());
        resetTabColor();
        if (priceUp){
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);

            Collections.sort(mGoodsInfoList);
            goodsListAdapter.resetData(mGoodsInfoList);
            priceUp = false;

        }else {
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

        resetTabColor();
        tvZongHe.setTextColor(Color.parseColor("#1997f8"));

        presenter.resetBody(bodyCondition);
        presenter.getRecptionCards(cardRefreshLayout,bodyCondition,true);


    }



    @OnClick({R.id.ll_zong_he, R.id.ll_price, R.id.ll_shai_xuan, R.id.ll_to_coach})
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
            case R.id.ll_to_coach:
                if (selectedGoodsInfo != null) {



                    rlGoods.setVisibility(View.GONE);
                    tvSendToStatus.setVisibility(View.VISIBLE);
                    //TODO TO给教练的请求
                } else {
                    Toast.makeText(getContext(), "请先点击选取一个产品,再TO给教练!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void showCards(List<CardInfo> goodsInfos, Boolean isRefresh) {
        mGoodsInfoList=goodsInfos;

        if (isRefresh){
            goodsListAdapter.resetData(goodsInfos);
        }else {
            goodsListAdapter.addDatas(goodsInfos);
        }
    }

    public void resetTabColor(){
            tvZongHe.setTextColor(Color.parseColor("#666666"));
            tvPrice.setTextColor(Color.parseColor("#666666"));
            tvShaixuan.setTextColor(Color.parseColor("#666666"));
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_normal_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            Drawable drawableShaixuan = getResources().getDrawable(R.mipmap.shaixuan_black);
            drawableShaixuan.setBounds(0, 0, drawableShaixuan.getMinimumWidth(), drawableShaixuan.getMinimumHeight());
            tvShaixuan.setCompoundDrawables(null, null, drawableShaixuan, null);
    }

}
