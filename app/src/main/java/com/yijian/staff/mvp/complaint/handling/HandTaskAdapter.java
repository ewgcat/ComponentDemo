package com.yijian.staff.mvp.complaint.handling;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.yijian.staff.R;

import java.util.List;

/**
 * Created by yangk on 2018/3/9.
 */

public class HandTaskAdapter extends RecyclerView.Adapter<HandTaskAdapter.ViewHolder> {

    private List<HandTaskInfo> handTaskInfoList;
    private Context context;
    private boolean flagHand; //作为是否为 处理或者分配的标志位
    private int roleFlag;  // 作为虚拟角色的标志位， 0 用户身份1，1 用户身份2

    private final int MAX_LINE_COUNT = 3;

    private final int STATE_UNKNOW = -1;

    private final int STATE_NOT_OVERFLOW = 1;//文本行数不能超过限定行数

    private final int STATE_COLLAPSED = 2;//文本行数超过限定行数，进行折叠

    private final int STATE_EXPANDED = 3;//文本超过限定行数，被点击全文展开

    private SparseArray<Integer> mTextStateList;

    public HandTaskAdapter(Context context, List<HandTaskInfo> handTaskInfoList, boolean flagHand, int roleFlag){
        this.context = context;
        this.handTaskInfoList = handTaskInfoList;
        this.flagHand = flagHand;
        this.roleFlag = roleFlag;
        mTextStateList = new SparseArray<>();
//        setHasStableIds(true);
    }

    @Override
    public HandTaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hand_task, parent, false);
        HandTaskAdapter.ViewHolder holder = new HandTaskAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HandTaskAdapter.ViewHolder holder, int position) {
        HandTaskInfo handTaskInfo = handTaskInfoList.get(position);
        holder.tv_name.setText(handTaskInfo.getName());
        holder.iv_gender.setImageResource(handTaskInfo.getGender());
        holder.tv_status.setText(handTaskInfo.getStatus());
        holder.tv_taskContent.setText(handTaskInfo.getTaskContent());
        holder.tv_dispatchTaskTime.setText(handTaskInfo.getDispatchTaskTime());
        holder.tv_result_taskResult.setText(handTaskInfo.getHandTaskResultInfo().getTaskResult());
        holder.tv_result_handTaskTime.setText(handTaskInfo.getHandTaskResultInfo().getHandTaskTime());
        holder.img_hand_task.setImgs(handTaskInfo.getTaskImgs());
        holder.fl_comment.setLayoutChild(context,handTaskInfo.getEvaluateContentList());
        holder.rb.setRating(Float.valueOf(handTaskInfo.getEvaluateGrade()));

        /******************** START 设置任务内容的展开和收起 **********************/
        int state=mTextStateList.get(position,STATE_UNKNOW);
//        如果该itme是第一次初始化，则取获取文本的行数
        if (state==STATE_UNKNOW){
            holder.tv_taskContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
//                    这个回掉会调用多次，获取玩行数后记得注销监听
                    holder.tv_taskContent.getViewTreeObserver().removeOnPreDrawListener(this);
//                    holder.content.getViewTreeObserver().addOnPreDrawListener(null);
//                    如果内容显示的行数大于限定显示行数
                    if (holder.tv_taskContent.getLineCount()>MAX_LINE_COUNT) {
                        holder.tv_taskContent.setMaxLines(MAX_LINE_COUNT);//设置最大显示行数
                        holder.expandOrCollapse.setVisibility(View.VISIBLE);//让其显示全文的文本框状态为显示
                        holder.expandOrCollapse.setText("全文");//设置其文字为全文
                        holder.iv_expand_or_collapse.setImageResource(R.mipmap.ts_quanwen);
                        mTextStateList.put(position, STATE_COLLAPSED);
                    }else{
                        holder.expandOrCollapse.setVisibility(View.GONE);//显示全文隐藏
                        mTextStateList.put(position,STATE_NOT_OVERFLOW);//让其不能超过限定的行数
                    }
                    return true;
                }
            });

            holder.tv_taskContent.setMaxLines(Integer.MAX_VALUE);//设置文本的最大行数，为整数的最大数值
            holder.tv_taskContent.setText("胜多负少发射点发射点犯得上发射点犯得上士大夫士大夫大师傅" +
                    "士大夫大师傅大师傅就士大夫大师傅大师傅士大夫了几块几十块的是飞机轮是几点开饭了巨大" +
                    "撒发射点犯得上犯得上犯得上犯得上犯得上士大夫首发式地方开始就流口水肌肤抵抗力的空洞是" +
                    "士大夫十分的时间了圣诞节分厘卡是否健康死了打发手动阀手动阀手动阀士大夫随风倒十分士大夫" +
                    "士大夫大师傅士大夫士大夫士大夫胜多负少发生否似的犯得上犯得上犯得上犯得上地方就开了");//用Util中的getContent方法获取内容
        }else{
//            如果之前已经初始化过了，则使用保存的状态，无需在获取一次
            switch (state){
                case STATE_NOT_OVERFLOW:
                    holder.expandOrCollapse.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    holder.tv_taskContent.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrCollapse.setVisibility(View.VISIBLE);
                    holder.expandOrCollapse.setText("全文");
                    holder.iv_expand_or_collapse.setImageResource(R.mipmap.ts_quanwen);
                    break;
                case STATE_EXPANDED:
                    holder.tv_taskContent.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrCollapse.setVisibility(View.VISIBLE);
                    holder.iv_expand_or_collapse.setImageResource(R.mipmap.ts_shouqi);
                    holder.expandOrCollapse.setText("收起");
                    break;
            }
            holder.tv_taskContent.setText("胜多负少发射点发射点犯得上发射点犯得上士大夫士大夫大师傅" +
                    "士大夫大师傅大师傅就士大夫大师傅大师傅士大夫了几块几十块的是飞机轮是几点开饭了巨大" +
                    "撒发射点犯得上犯得上犯得上犯得上犯得上士大夫首发式地方开始就流口水肌肤抵抗力的空洞是" +
                    "士大夫十分的时间了圣诞节分厘卡是否健康死了打发手动阀手动阀手动阀士大夫随风倒十分士大夫" +
                    "士大夫大师傅士大夫士大夫士大夫胜多负少发生否似的犯得上犯得上犯得上犯得上地方就开了");
        }


//        设置显示和收起的点击事件
        holder.lin_expand_or_collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state=mTextStateList.get(position,STATE_UNKNOW);
                if (state==STATE_COLLAPSED){
                    holder.tv_taskContent.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrCollapse.setText("收起");
                    holder.iv_expand_or_collapse.setImageResource(R.mipmap.ts_shouqi);
                    mTextStateList.put(position,STATE_EXPANDED);
                }else if (state==STATE_EXPANDED){
                    holder.tv_taskContent.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrCollapse.setText("全文");
                    holder.iv_expand_or_collapse.setImageResource(R.mipmap.ts_quanwen);
                    mTextStateList.put(position,STATE_COLLAPSED);
                }
            }
        });
        /******************** END 设置任务内容的展开和收起 **********************/



        if(roleFlag == 0){ //用户身份1
            holder.lin_task_hand.setVisibility(flagHand?View.GONE:View.VISIBLE);
            holder.lin_immediately_hand.setVisibility(flagHand?View.GONE:View.VISIBLE);
            holder.lin_immediately_fenpei.setVisibility(View.GONE);
            holder.tv_status.setVisibility(flagHand?View.VISIBLE:View.GONE);
            holder.view_line.setVisibility(flagHand?View.GONE:View.VISIBLE);
            //隐藏评价和评分
            holder.lin_evaluate.setVisibility(View.GONE);
            holder.lin_score.setVisibility(View.GONE);
        }else { //用户身份2
            holder.lin_task_hand.setVisibility(flagHand?View.GONE:View.VISIBLE);
            holder.lin_immediately_fenpei.setVisibility(flagHand?View.GONE:View.VISIBLE);
            holder.lin_immediately_hand.setVisibility(View.GONE);
            holder.tv_status.setVisibility(flagHand?View.VISIBLE:View.GONE);
            holder.view_line.setVisibility(flagHand?View.GONE:View.VISIBLE);
            //判断是否隐藏评价和评分
            holder.lin_evaluate.setVisibility(flagHand?View.VISIBLE:View.GONE);
            holder.lin_score.setVisibility(flagHand?View.VISIBLE:View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        return handTaskInfoList == null ? 0 : handTaskInfoList.size();
    }

  /*  @Override
    public long getItemId(int position) {
        return position;
    }*/

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_header; //任务分配者的头像
        ImageView iv_gender; //任务分配者的性别
        TextView tv_name; //任务分配者的姓名
        TextView tv_status; //处理状态
        TextView tv_taskContent; //任务内容
        HandImgViewGroup img_hand_task; //任务多配图
        TextView tv_dispatchTaskTime; //分配时间
        ImageView iv_result_header; // 处理者头像
        TextView tv_result_taskResult; // 处理内容
        TextView tv_result_handTaskTime; // 处理时间
        LinearLayout lin_task_hand; //任务处理按钮布局
        LinearLayout lin_immediately_hand; //处理点击按钮
        LinearLayout lin_immediately_fenpei; //分配点击按钮
        View view_line; //分割线
        LinearLayout lin_evaluate; //客户评价
        LinearLayout lin_score; //客户评分
        CommentFlowLayout fl_comment; //客户评价内容
        RatingBar rb; // 星级评分
        TextView expandOrCollapse;
        LinearLayout lin_expand_or_collapse;
        ImageView iv_expand_or_collapse;


        public ViewHolder(View view) {
            super(view);
            iv_header =  view.findViewById(R.id.iv_header);
            iv_gender =  view.findViewById(R.id.iv_gender);
            tv_name   = view.findViewById(R.id.tv_name);
            tv_status   = view.findViewById(R.id.tv_status);
            tv_taskContent =     view.findViewById(R.id.tv_taskContent);
            img_hand_task = view.findViewById(R.id.img_hand_task);
            tv_dispatchTaskTime =     view.findViewById(R.id.tv_dispatchTaskTime);
            iv_result_header  =     view.findViewById(R.id.iv_result_header);
            tv_result_taskResult  =     view.findViewById(R.id.tv_result_taskResult);
            tv_result_handTaskTime  =     view.findViewById(R.id.tv_result_handTaskTime);
            lin_task_hand  =     view.findViewById(R.id.lin_task_hand);
            lin_immediately_hand  =     view.findViewById(R.id.lin_immediately_hand);
            lin_immediately_fenpei  =     view.findViewById(R.id.lin_immediately_fenpei);
            lin_evaluate  =     view.findViewById(R.id.lin_evaluate);
            lin_score  =     view.findViewById(R.id.lin_score);
            view_line  =     view.findViewById(R.id.view_line);
            fl_comment  =     view.findViewById(R.id.fl_comment);
            rb  =     view.findViewById(R.id.rb);
            expandOrCollapse = view.findViewById(R.id.tv_expand_or_collapse);
            lin_expand_or_collapse = view.findViewById(R.id.lin_expand_or_collapse);
            iv_expand_or_collapse = view.findViewById(R.id.iv_expand_or_collapse);
        }
    }

}
