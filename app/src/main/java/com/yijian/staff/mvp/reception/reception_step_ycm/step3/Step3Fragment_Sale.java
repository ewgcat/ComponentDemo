package com.yijian.staff.mvp.reception.reception_step_ycm.step3;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.reception.step3.coach.TOLeadersDialog;
import com.yijian.staff.mvp.reception.step3.kefu.CardsListAdapter;
import com.yijian.staff.mvp.reception.step3.kefu.HuiJiProductContract;
import com.yijian.staff.mvp.reception.step3.kefu.HuiJiProductPresenter;
import com.yijian.staff.mvp.reception.step3.kefu.OptionDialog;
import com.yijian.staff.widget.NavigationBar2;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Step3Fragment_Sale extends Fragment implements HuiJiProductContract.View{

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

    @BindView(R.id.ll_to_coach)
    LinearLayout llToCoach;

    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout cardRefreshLayout;

    private static final String TAG = "HuiJiProductQuotationFr";
    private CardsListAdapter goodsListAdapter;
    private CardInfo selectedGoodsInfo;

    private ConditionBody bodyCondition;
    private HuiJiProductPresenter presenter;
    private OptionDialog optionDialog;
    private String memberId;
    private RecptionerInfoBean consumerBean;


    public Step3Fragment_Sale() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        consumerBean = arguments.getParcelable("recptionerInfoBean");
        if (consumerBean==null)return;
        memberId=consumerBean.getId();
        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();

        navigationBar2.setmRightTvText("下一步");
        navigationBar2.getmRightTv().setVisibility(View.VISIBLE);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (presenter!=null)presenter.getStatus(true,memberId);
//                TOLeadersDialog  toLeadersDialog = new TOLeadersDialog();
//                toLeadersDialog.show(getActivity().getFragmentManager(),"TOLeadersDialog");
            }
        });

        navigationBar2.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep3Back();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hui_ji_product_quotation, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new HuiJiProductPresenter(getContext());
        presenter.setView(this);
        bodyCondition = new ConditionBody();
        bodyCondition.setPageSize(10);
        bodyCondition.setPageNum(1);
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
                presenter.getRecptionCards(bodyCondition,true);

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                bodyCondition.setPageNum(bodyCondition.getPageNum()+1);
                presenter.getRecptionCards(bodyCondition,false);
            }
        });

        optionDialog = new OptionDialog();

        optionDialog.setOnDismissListener(new OptionDialog.OnDismissListener() {
            @Override
            public void onDismiss(ConditionBody body) {
//                Log.e(TAG, "onDismiss: " );
                bodyCondition=body;
                bodyCondition.setPageNum(1);
                bodyCondition.setPageSize(10);
                presenter.getRecptionCards(bodyCondition,true);


            }
        });
        goodsListAdapter.setOnItemClickListener(new CardsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, CardInfo goodsInfo) {
                selectedGoodsInfo = goodsInfo;

            }
        });
        selectZongHe();

        Integer status = consumerBean.getStatus();
        if (status==null)return;

//            case 32://  MEMBERREJECT(32, "会员拒绝录入数据发送回会籍"),————手动点击确认，进入
//            case 30:// SALEJUMPCOACH(30, "会籍跳过教练"),
//            case 33://SALETOCOACH(33, "会员没购买意愿，会籍TO教练"),
//            case 34:// COACHTOSALE(34, "教练接待会员，会员同意购买,TO回会籍"),
//            case 35:// COACHTOLEADER(35, "教练接待会员，会员不同意购买,TO领导 "),
//            case 36:// LEADERTOSALE(36, "领导接待会员,TO回会籍 "),

        if (status ==32||status==30||status==31){
            llToCoach.setVisibility(View.VISIBLE);
        }else {
            llToCoach.setVisibility(View.GONE);
        }
    }




    //点击筛选
    private void selectShaixuan() {
//        priceUp = false;
        resetTabColor();
        tvShaixuan.setTextColor(Color.parseColor("#1997f8"));

        Bundle bundle = new Bundle();
//        bundle.set("bodyCondition",bodyCondition);
        bundle.putString("cardType",bodyCondition.getCardType());
        bundle.putString("startPrice",bodyCondition.getStartPrice());
        bundle.putString("venueName",bodyCondition.getVenueName());


        optionDialog.setArguments(bundle);

        optionDialog.show(getActivity().getFragmentManager(),"OptionDialog");

    }

    private boolean priceUp = false;

    //点击价格
    private void selectPrice() {
        List<CardInfo>   mGoodsInfoList=goodsListAdapter.getmGoodsInfoList();


        if (mGoodsInfoList==null||mGoodsInfoList.size()==0)return;
//        Log.e(TAG, "mGoodsInfoList"+mGoodsInfoList.size());
        resetTabColor();

        if (priceUp){
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_down_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            tvPrice.setTextColor(getResources().getColor(R.color.blue));
            Collections.sort(mGoodsInfoList);
            goodsListAdapter.notifyDataSetChanged();
            priceUp = false;
            bodyCondition.setIsSortByPrice(1);
        }else {
            Drawable drawable = getResources().getDrawable(R.mipmap.jd_up_arrow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvPrice.setCompoundDrawables(null, null, drawable, null);
            tvPrice.setTextColor(getResources().getColor(R.color.blue));
            Collections.sort(mGoodsInfoList);
            Collections.reverse(mGoodsInfoList);
            goodsListAdapter.notifyDataSetChanged();
            priceUp = true;
            bodyCondition.setIsSortByPrice(0);
        }

    }

    //点击综合
    private void selectZongHe() {
        resetTabColor();
        tvZongHe.setTextColor(Color.parseColor("#1997f8"));
        presenter.resetBody(bodyCondition);
        presenter.getRecptionCards(bodyCondition,true);
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
                    presenter.toCoach(memberId,selectedGoodsInfo.getCardprodbaseId());
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

        if (isRefresh){
            goodsListAdapter.resetData(goodsInfos);
            cardRefreshLayout.finishRefresh(1000);
        }else {
            goodsListAdapter.addDatas(goodsInfos);
            cardRefreshLayout.finishLoadMore(1000);
        }

    }

    @Override
    public void showToCoachSucceed() {
        rlGoods.setVisibility(View.GONE);
        tvSendToStatus.setVisibility(View.VISIBLE);

    }

    @Override
    public void showNoCards(boolean isRefresh,boolean isSucceed) {

        if (isRefresh){
           if (isSucceed)Toast.makeText(getContext(),"未查询到相关数据",Toast.LENGTH_SHORT).show();
            cardRefreshLayout.finishRefresh(1000);
        }else {
            if (isSucceed)  Toast.makeText(getContext(),"已经是最后一页了",Toast.LENGTH_SHORT).show();

            cardRefreshLayout.finishLoadMore(1000);
        }

    }

    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {

        if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep3SaleToStep4( receptionStastuBean.getOperatorType());
    }

    @Override
    public void showCardToOrder() {
        presenter.getStatus(false, memberId);
    }

    @Override
    public void shouldCardToOrder() {
        if (selectedGoodsInfo==null){
            Toast.makeText(getContext(),"请选择一个产品，再进行下一步",Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.cardToOrder(memberId,selectedGoodsInfo.getCardprodbaseId());

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

    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }
}
