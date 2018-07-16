package com.yijian.staff.mvp.reception.reception_step_ycm.step3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.physical.PhysicalReportActivity;
import com.yijian.staff.mvp.questionnaire.detail.QuestionnaireResultActivity;
import com.yijian.staff.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step3.coach.CoachProductContract;
import com.yijian.staff.mvp.reception.step3.coach.CoachProductPresenter;
import com.yijian.staff.mvp.reception.step3.coach.ProductDetailActivity;
import com.yijian.staff.mvp.reception.step3.coach.TOLeadersDialog;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.mvp.reception.step3.coach.bean.ReceptionUserInfo;
import com.yijian.staff.widget.NavigationBar2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/13 19:25:37
 */
public class Step3Fragment_Coach extends Fragment implements CoachProductContract.View, TOLeadersDialog.ToLeaderLisenter {
    @BindView(R.id.rl_coach_goods)
    RelativeLayout rlCoachGoods;
    @BindView(R.id.tv_send_to_status)
    TextView tvSendToStatus;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_viper_phone)
    TextView tvViperPhone;
    @BindView(R.id.tv_jiedai_name)
    TextView tvJiedaiName;
    @BindView(R.id.tv_coach_name)
    TextView tvCoachName;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_jianshenplace)
    TextView tvJianshenplace;
    @BindView(R.id.tv_yu_er)
    TextView tvYuEr;
    @BindView(R.id.tv_yu_er_tip)
    TextView tvYuErTip;

    @BindView(R.id.tv_chuzhiyouhui)
    TextView tvChuzhiyouhui;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.item_view)
    LinearLayout itemView;
    Unbinder unbinder;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.ll_to_leader)
    LinearLayout llToLeader;

    private View view;
    private String memberId;
    private String memberName = "";

    private ProductDetail productDetail;

    private CoachProductPresenter presenter;
    private RecptionerInfoBean consumerBean;
    private TOLeadersDialog toLeadersDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle arguments = getArguments();
//        memberId = arguments.getString("memberId");

        Bundle arguments = getArguments();
        consumerBean = arguments.getParcelable("recptionerInfoBean");
        if (consumerBean == null) return;
        memberId = consumerBean.getId();

        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();

        navigationBar2.setmRightTvText("完成");
        navigationBar2.getmRightTv().setVisibility(View.VISIBLE);
//        navigationBar2.setmRightTvColor(R.color.white);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null && productDetail != null)
                    presenter.coachToSale(memberId, productDetail.getCardId());
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coach_product_quotation, container, false);

        unbinder = ButterKnife.bind(this, view);
        presenter = new CoachProductPresenter(getContext());
        presenter.setView(this);
        presenter.getUserInfo(memberId);
        presenter.getProductDetail(memberId);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_wenjuan, R.id.tv_ticebaogao, R.id.ll_to_leader, R.id.item_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wenjuan:
                Intent intent = new Intent(getContext(), QuestionnaireResultActivity.class);
                intent.putExtra("memberId", memberId);
                startActivity(intent);
                break;
            case R.id.tv_ticebaogao:
                Intent intent1 = new Intent(getContext(), PhysicalReportActivity.class);
                intent1.putExtra("memberId", memberId);
                intent1.putExtra("memberName", memberName);
                startActivity(intent1);
                break;

            case R.id.item_view:

                if (productDetail != null) {
                    Intent intent2 = new Intent(getContext(), ProductDetailActivity.class);
                    intent2.putExtra("productDetail", productDetail);
                    startActivity(intent2);

                }
                break;

            case R.id.ll_to_leader:
                toLeadersDialog = new TOLeadersDialog();
                toLeadersDialog.setLisenter(this);
                toLeadersDialog.show(getActivity().getFragmentManager(), "TOLeadersDialog");
                break;


        }
    }


    @Override
    public void showUserInfo(ReceptionUserInfo receptionUserInfo) {
        memberName = receptionUserInfo.getMemberName();
        tvName.setText("" + memberName);
        tvViperPhone.setText("" + receptionUserInfo.getMemberMobile());
        tvJiedaiName.setText("" + receptionUserInfo.getSaleName());
        tvCoachName.setText("" + receptionUserInfo.getCoachName());
        String sex = receptionUserInfo.getSex();
        if ("女".equals(sex)) {
            ivSex.setImageResource(R.mipmap.lg_women);
        }
    }

    @Override
    public void showProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;

        tvGoodsName.setText("" + productDetail.getCardTypeName());
        List<String> venueNames = productDetail.getVenueNames();
        if (venueNames != null && venueNames.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < venueNames.size(); i++) {
                stringBuilder.append("、").append(venueNames.get(i));
            }
            String substring = stringBuilder.substring(1);
            tvJianshenplace.setText(substring);
        }


//        Integer validDay = productDetail.getValidDay();
//        if (validDay != null) tvYuEr.setText("" + validDay + "天");
//
//        Integer validTime = productDetail.getValidTime();
//        if (validTime != null) tvYuEr.setText("" + validTime + "次");
//
//        BigDecimal rechargeGivePercent = productDetail.getRechargeGivePercent();
//        if (rechargeGivePercent != null) {
//            NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
//            String format = percent.format(rechargeGivePercent);
//            tvChuzhiyouhui.setText("赠送" + format);
//        }
//        BigDecimal salePrice = productDetail.getSalePrice();
//        if (salePrice != null) {
//            tvPrice.setText("" + salePrice.doubleValue());
//        }

        String strRestKey = productDetail.getStrRestKey();
        String strRestVal = productDetail.getStrRestVal();
        if (!TextUtils.isEmpty(strRestKey) && !TextUtils.isEmpty(strRestVal)) {
            tvYuErTip.setText(strRestKey);
            tvYuEr.setText(strRestVal);
        }

        String rechargeGivePercent = productDetail.getRechargeGivePercent();
        if (!TextUtils.isEmpty(rechargeGivePercent)) tvChuzhiyouhui.setText("赠送" + rechargeGivePercent + "%");

        String salePrice = productDetail.getSalePrice();
        if (!TextUtils.isEmpty(salePrice)) tvPrice.setText("" + salePrice);

    }

    @Override
    public void showToLeaderSucceed() {

        if (toLeadersDialog != null) toLeadersDialog.dismiss();
        Toast.makeText(getContext(), "发送给领导成功", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void coachToSaleSecceed() {
        Toast.makeText(getContext(), "发送给会籍成功", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    //TO给领导
    @Override
    public void onConfirm(Integer postid, String content) {
        if (TextUtils.isEmpty(memberId)) return;
        presenter.postToLeader(memberId, content, postid);
    }


    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }

}
