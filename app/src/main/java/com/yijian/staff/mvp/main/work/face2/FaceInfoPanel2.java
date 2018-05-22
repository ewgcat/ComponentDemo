package com.yijian.staff.mvp.main.work.face2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.main.work.face.BitmapUtils;
import com.yijian.staff.mvp.main.work.face.FaceDetail;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class FaceInfoPanel2 extends PopupWindow {

    private View mMenuView;
    private ScrollView scrollview;
    private RecyclerView rv_face;
    private Context context;
    private List<FaceDetail> faceDetails;

    private ImageView iv_detail_header; //头像
    private TextView tv_detail_name; //会员名称
    private TextView tv_detail_cardname; //卡名称
    private TextView tv_detail_birthday; //生日
    private TextView tv_detail_age; //年龄
    private TextView tv_detail_huiji; //会籍
    private TextView tv_detail_kayouxiaoqi; //卡有效期
    private TextView tv_detail_coach; //教练
    private TextView tv_detail_progress; //课程进度
    private TextView tv_detail_biuld_time; //上一次健身的时间
    private TextView tv_detail_has_child; //有无小孩
    private TextView tv_detail_build_num; //健身次数
    private RelativeLayout rel_coach; //教练
    private RelativeLayout rel_course_progress; //课程进度
    private RelativeLayout rel_record_build_time; //上次健身时间



    public FaceInfoPanel2(final Context context, List<FaceDetail> faceDetails) {
        super(context);
        this.context = context;
        this.faceDetails = faceDetails;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_face_panal2, null);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setClippingEnabled(false);
        initView();
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
    }

    private void initView(){
        mMenuView.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rv_face.getVisibility() == View.VISIBLE){
                    dismiss();
                }else{
                    rv_face.setVisibility(View.VISIBLE);
                    scrollview.setVisibility(View.GONE);
                }
            }
        });

        rv_face = mMenuView.findViewById(R.id.rv_face);
        rv_face.setLayoutManager(new LinearLayoutManager(context));
        rv_face.setAdapter(new FaceAdapter(faceDetails));

        scrollview = mMenuView.findViewById(R.id.scrollview);
        iv_detail_header = mMenuView.findViewById(R.id.iv_detail_header);
        tv_detail_name = mMenuView.findViewById(R.id.tv_detail_name);
        tv_detail_cardname = mMenuView.findViewById(R.id.tv_detail_cardname);
        tv_detail_birthday = mMenuView.findViewById(R.id.tv_detail_birthday);
        tv_detail_age = mMenuView.findViewById(R.id.tv_detail_age);
        tv_detail_huiji = mMenuView.findViewById(R.id.tv_detail_huiji);
        tv_detail_kayouxiaoqi = mMenuView.findViewById(R.id.tv_detail_kayouxiaoqi);
        tv_detail_coach = mMenuView.findViewById(R.id.tv_detail_coach);
        tv_detail_progress = mMenuView.findViewById(R.id.tv_detail_progress);
        tv_detail_biuld_time = mMenuView.findViewById(R.id.tv_detail_biuld_time);
        tv_detail_has_child = mMenuView.findViewById(R.id.tv_detail_has_child);
        tv_detail_build_num = mMenuView.findViewById(R.id.tv_detail_build_num);
        rel_coach = mMenuView.findViewById(R.id.rel_coach);
        rel_course_progress = mMenuView.findViewById(R.id.rel_course_progress);
        rel_record_build_time = mMenuView.findViewById(R.id.rel_record_build_time);
    }

    private void updateUi(FaceDetail faceDetail){
        ImageLoader.setImageResource(faceDetail.getHeadPath(), context, iv_detail_header);
        tv_detail_name.setText(faceDetail.getMemberName());
        tv_detail_cardname.setText(faceDetail.getCardName());
        tv_detail_birthday.setText(faceDetail.getBirthDate());
        tv_detail_age.setText(faceDetail.getAge());
        tv_detail_huiji.setText(faceDetail.getSellerName());
        tv_detail_kayouxiaoqi.setText(faceDetail.getExpirationDate());
        tv_detail_coach.setText(faceDetail.getCoachName());

        tv_detail_progress.setText(faceDetail.getCourseName()+faceDetail.getCourseNum());
        tv_detail_biuld_time.setText(faceDetail.getBEntranceRecord());
        tv_detail_has_child.setText(Integer.valueOf(faceDetail.getChildrenNum())>0?"有":"无");
        tv_detail_build_num.setText(Integer.valueOf(faceDetail.getBuildCount()+"次"));
        rel_coach.setVisibility(faceDetail.getCoachName()==null?View.GONE:View.VISIBLE);
        rel_course_progress.setVisibility(faceDetail.getCoachName()==null?View.GONE:View.VISIBLE);
        rel_record_build_time.setVisibility(faceDetail.getBEntranceRecord()==null?View.GONE:View.VISIBLE);

    }

    class FaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<FaceDetail> faceDetailList = new ArrayList<>();
        private Context context;

        public FaceAdapter(List<FaceDetail> faceDetailList) {
            this.faceDetailList = faceDetailList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_face, parent, false);
            context = parent.getContext();
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder) holder).bind(faceDetailList.get(position), context);
        }

        @Override
        public int getItemCount() {
            return faceDetailList != null ? faceDetailList.size() : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView iv_header;
            private TextView tv_memberName;
            private TextView tv_query_detail; //点击详情
            private TextView tv_cardName; //卡名称
            private TextView tv_expirationDate; //有效期
            private TextView tv_courseNameNum; //课程进度

            public ViewHolder(View itemView) {
                super(itemView);
                iv_header = itemView.findViewById(R.id.iv_header);
                tv_memberName = itemView.findViewById(R.id.tv_memberName);
                tv_query_detail = itemView.findViewById(R.id.tv_query_detail);
                tv_cardName = itemView.findViewById(R.id.tv_cardName);
                tv_expirationDate = itemView.findViewById(R.id.tv_expirationDate);
                tv_courseNameNum = itemView.findViewById(R.id.tv_courseNameNum);

            }

            public void bind(FaceDetail faceDetail, Context context) {
                ImageLoader.setImageResource(faceDetail.getHeadPath(), context, iv_header);
                tv_memberName.setText(faceDetail.getMemberName());
                tv_cardName.setText(faceDetail.getCardName());
                tv_expirationDate.setText(faceDetail.getExpirationDate());
                tv_courseNameNum.setText(faceDetail.getCourseName()+faceDetail.getCourseNum());
                tv_query_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollview.setVisibility(View.VISIBLE);
                        rv_face.setVisibility(View.GONE);
                        updateUi(faceDetail);
                    }
                });
            }

        }

    }


}