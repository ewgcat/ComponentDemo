package com.yijian.staff.mvp.complaint.list;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yijian.staff.R;
import com.yijian.staff.mvp.complaint.handling.HandTaskAdapter;
import com.yijian.staff.mvp.complaint.handling.HandTaskInfo;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintListActivity extends AppCompatActivity implements View.OnClickListener{

    /*@BindView(R.id.fl_content)
    FrameLayout flContent;*/
    @BindView(R.id.lin_staff)
    LinearLayout linStaff; //   处理情况/处理任务
    @BindView(R.id.lin_coach)
    LinearLayout linCoach; //   分配情况/分配任务

    @BindView(R.id.tv_handling_condition)
    TextView tv_handling_condition; //处理情况
    @BindView(R.id.tv_handling_task)
    TextView tv_handling_task; //处理任务
    @BindView(R.id.tv_feipei_condition)
    TextView tv_feipei_condition; //分配情况
    @BindView(R.id.tv_feipei_task)
    TextView tv_feipei_task; //分配任务

    @BindView(R.id.iv_handling_condition)
    ImageView iv_handling_condition; //处理情况
    @BindView(R.id.iv_handling_task)
    ImageView iv_handling_task; //处理任务
    @BindView(R.id.iv_feipei_condition)
    ImageView iv_feipei_condition; //分配情况
    @BindView(R.id.iv_feipei_task)
    ImageView iv_feipei_task; //分配任务

    @BindView(R.id.rv_complaint)
    RecyclerView rv_complaint;
    private List<HandTaskInfo> handTaskInfoList = new ArrayList<>();
    private NavigationBar2 navigationBar2;

    private static final int VIDEO_CONTENT_DESC_MAX_LINE = 3;// 默认展示最大行数3行
    private static final int SHOW_CONTENT_NONE_STATE = 0;// 扩充
    private static final int SHRINK_UP_STATE = 1;// 收起状态
    private static final int SPREAD_STATE = 2;// 展开状态
    private static int mState = SHRINK_UP_STATE;//默认收起状态

    private TextView mContentText;// 展示文本内容
    private RelativeLayout mShowMore;// 展示更多
    private ImageView mImageSpread;// 展开
    private ImageView mImageShrinkUp;// 收起


    private int roleFlag = 1; // 作为虚拟角色的标志位， 0 用户身份1，1 用户身份2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);

        ButterKnife.bind(this);
        setTitle();
        initView();
        selectTab(R.id.lin_feipei_condition);
    }

    private void initHandTaskInfoList(boolean flagHand) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("status", "1");
            jsonObject.put("taskContent", "你今天扑街了么？");
            jsonObject.put("dispatchTaskTime", "2015-9-2");
            JSONArray taskImgsArray = new JSONArray();
            taskImgsArray.put("1");
            taskImgsArray.put("2");
            taskImgsArray.put("3");
            jsonObject.put("taskImgs", taskImgsArray);
            JSONArray taskCommentArray = new JSONArray();
            taskCommentArray.put("还行吧");
            taskCommentArray.put("太差了");
            taskCommentArray.put("用了之后还挺爽的");
            jsonObject.put("evaluateContentList", taskCommentArray);

            JSONObject handResultJsonObj = new JSONObject();
            handResultJsonObj.put("headerUrl","");
            handResultJsonObj.put("name","老王");
            handResultJsonObj.put("taskResult","嗯嗯");
            handResultJsonObj.put("handTaskTime","2015-09-02");
            jsonObject.put("handTaskResultInfo",handResultJsonObj);

            for (int i = 0; i < 10; i++) {
                HandTaskInfo handTaskInfo = new HandTaskInfo(jsonObject);
                handTaskInfoList.add(handTaskInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(ComplaintListActivity.this);
            //设置RecyclerView 布局
            rv_complaint.setLayoutManager(layoutmanager);
            HandTaskAdapter invitationRecordAdatper = new HandTaskAdapter(ComplaintListActivity.this, handTaskInfoList,flagHand,1);
            rv_complaint.setAdapter(invitationRecordAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }

    }

    private void setTitle() {
        navigationBar2 = findViewById(R.id.complaint_list_navigation_bar);
        navigationBar2.getSecondLeftIv().setVisibility(View.GONE);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.setmRightTvText("");
    }

    public void initView(){
        linStaff.setVisibility(roleFlag == 0 ? View.VISIBLE:View.GONE);
        linCoach.setVisibility(roleFlag == 0 ? View.GONE:View.VISIBLE);
    }

    @OnClick({R.id.lin_handling_condition, R.id.lin_handling_task,R.id.lin_feipei_condition, R.id.lin_feipei_task})
    public void onViewClicked(View view) {
        selectTab(view.getId());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.right_tv:
                break;
        }
    }

    /**
     * 这里以导航栏的控件ID作为导航的导向
     * @param resId
     */
    private void selectTab(int resId){
        setBottomStyle(resId);
        switch (resId) {
            case R.id.lin_handling_condition: //处理情况
                initHandTaskInfoList(true);
                break;
            case R.id.lin_handling_task: //处理任务
                initHandTaskInfoList(false);
                break;
            case R.id.lin_feipei_condition: // 分配情况
                initHandTaskInfoList(true);
                break;
            case R.id.lin_feipei_task: //  分配任务
                initHandTaskInfoList(false);
                break;
        }
    }

    private void setBottomStyle(int viewId){
        if(roleFlag == 0){ //用户角色1
            navigationBar2.setTitle(viewId==R.id.lin_handling_condition?"处理情况":"处理任务");
            iv_handling_condition.setImageResource(viewId==R.id.lin_handling_condition?
                    R.mipmap.ts_chulilan:R.mipmap.ts_chuli);
            tv_handling_condition.setTextColor(ContextCompat.getColor(this,viewId==R.id.lin_handling_condition?
                    R.color.blue1997f8:R.color.gray666666));

            iv_handling_task.setImageResource(viewId==R.id.lin_handling_condition?
                    R.mipmap.ts_renwu:R.mipmap.ts_renwulan);
            tv_handling_task.setTextColor(ContextCompat.getColor(this,viewId==R.id.lin_handling_condition?
                    R.color.gray666666:R.color.blue1997f8));


        }else { // 用户角色2
            navigationBar2.setTitle(viewId==R.id.lin_feipei_condition?"分配情况":"分配任务");
            iv_feipei_condition.setImageResource(viewId==R.id.lin_feipei_condition?
                    R.mipmap.ts_chulilan:R.mipmap.ts_chuli);
            tv_feipei_condition.setTextColor(ContextCompat.getColor(this,viewId==R.id.lin_feipei_condition?
                    R.color.blue1997f8:R.color.gray666666));

            iv_feipei_task.setImageResource(viewId==R.id.lin_feipei_condition?
                    R.mipmap.ts_renwu:R.mipmap.ts_renwulan);
            tv_feipei_task.setTextColor(ContextCompat.getColor(this,viewId==R.id.lin_feipei_condition?
                    R.color.gray666666:R.color.blue1997f8));
        }
    }

}
