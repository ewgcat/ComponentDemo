package com.yijian.staff.mvp.questionnaire.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.questionnaire.QuestionNaireVipBean;
import com.yijian.staff.mvp.questionnaire.detail.QuestionnaireResultActivity;
import com.yijian.staff.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class QuestionareAdapter extends RecyclerView.Adapter<QuestionareAdapter.ViewHolder> {
    private List<QuestionNaireVipBean> questionNaireVipBeanList = new ArrayList<>();
    private Context context;

    public QuestionareAdapter(Context context, List<QuestionNaireVipBean> questionNaireVipBeanList) {
        this.context = context;
        this.questionNaireVipBeanList = questionNaireVipBeanList;
    }

    public void update(List<QuestionNaireVipBean> questionNaireVipBeanList) {
        this.questionNaireVipBeanList = questionNaireVipBeanList;
        notifyDataSetChanged();
    }


    @Override
    public QuestionareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_naire, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(QuestionareAdapter.ViewHolder holder, int position) {
        QuestionNaireVipBean questionNaireVipBean = questionNaireVipBeanList.get(position);
        holder.tv_name.setText(questionNaireVipBean.getMemberName());
        Long createTime = questionNaireVipBean.getCreateTime();
        if (createTime != null&&createTime!=-1) {
            holder.tv_time.setText(DateUtil.parseLongDateToTimeString(createTime));
        }
        holder.tv_seller_name.setText(questionNaireVipBean.getSellerName());
        holder.ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuestionnaireResultActivity.class);
                intent.putExtra("memberId",questionNaireVipBean.getMemberId());
                intent.putExtra("memberName",questionNaireVipBean.getMemberName());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return questionNaireVipBeanList == null ? 0 : questionNaireVipBeanList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_time;
        TextView tv_seller_name;
        LinearLayout ll_detail;


        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_time = view.findViewById(R.id.tv_time);
            tv_seller_name = view.findViewById(R.id.tv_seller_name);
            ll_detail = view.findViewById(R.id.ll_detail);
        }

    }
}
