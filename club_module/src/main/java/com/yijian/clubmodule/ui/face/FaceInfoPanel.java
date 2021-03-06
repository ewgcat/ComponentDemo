package com.yijian.clubmodule.ui.face;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yijian.clubmodule.R;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FaceInfoPanel extends PopupWindow {

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
    private TextView tv_detail_expe_num; //体验课次数
    private RelativeLayout rel_coach; //教练
    private RelativeLayout rel_course_progress; //课程进度
    private RelativeLayout rel_record_build_time; //上次健身时间


    public FaceInfoPanel(final Context context, List<FaceDetail> faceDetails) {
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

    private void initView() {
        mMenuView.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv_face.getVisibility() == View.VISIBLE) {
                    dismiss();
                } else {
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
        tv_detail_expe_num = mMenuView.findViewById(R.id.tv_detail_expe_num);
        rel_coach = mMenuView.findViewById(R.id.rel_coach);
        rel_course_progress = mMenuView.findViewById(R.id.rel_course_progress);
        rel_record_build_time = mMenuView.findViewById(R.id.rel_record_build_time);
    }

    private void updateUi(FaceDetail faceDetail) {
        ImageLoader.setHeadImageResource(SharePreferenceUtil.getImageUrl() + faceDetail.getHeadPath(), context, iv_detail_header);
        tv_detail_name.setText(emptyNull(faceDetail.getMemberName(),""));
        tv_detail_cardname.setText(emptyNull(faceDetail.getCardName(),""));
        tv_detail_birthday.setText(emptyNull(faceDetail.getBirthDate(),""));
        tv_detail_age.setText(emptyNull(faceDetail.getAge()+"","岁"));
        tv_detail_huiji.setText(emptyNull(faceDetail.getSellerName(),""));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tv_detail_kayouxiaoqi.setText(faceDetail.getExpirationDate()==null?"未知":simpleDateFormat.format(simpleDateFormat.parse(faceDetail.getExpirationDate())));
            if (("无").equals(faceDetail.getBEntranceRecord()) || TextUtils.isEmpty(faceDetail.getBEntranceRecord())) {
                tv_detail_biuld_time.setText("无");
            } else {
//                tv_detail_biuld_time.setText(simpleDateFormat.format(simpleDateFormat.parse(faceDetail.getBEntranceRecord())));
                tv_detail_biuld_time.setText(faceDetail.getBEntranceRecord());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_detail_coach.setText(emptyNull(faceDetail.getCoachName(),""));
        String courseName = faceDetail.getCourseName();
        int courseNum = faceDetail.getCourseNum();
       /* if (TextUtils.isEmpty(courseName)||courseNum==0){
            tv_detail_progress.setText("无");
        }else {
            tv_detail_progress.setText(courseName + "第" + courseNum + "节");
        }*/
        tv_detail_progress.setText(faceDetail.getCourseValue());
        /*try{
            tv_detail_has_child.setText(Integer.valueOf(faceDetail.getChildrenNum()) > 0 ? "有" : "无");
        }catch (Exception e){
            tv_detail_has_child.setText("暂无录入");
        }*/
        tv_detail_has_child.setText(TextUtils.isEmpty(faceDetail.getHasChildren()) ? "暂无数据" : faceDetail.getHasChildren());
        tv_detail_build_num.setText(emptyNull(faceDetail.getBuildCount()+"","次"));
        tv_detail_expe_num.setText(emptyNull(faceDetail.getExperienceCourseCount()+"","节"));
    }

    private String emptyNull(String str,String appendStr){
        return (TextUtils.isEmpty(str) ? "无" : str) + (TextUtils.isEmpty(appendStr) ? "" : appendStr);
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
            private RelativeLayout rel_course_progress; //课程进度容器布局


            public ViewHolder(View itemView) {
                super(itemView);
                iv_header = itemView.findViewById(R.id.iv_header);
                tv_memberName = itemView.findViewById(R.id.tv_memberName);
                tv_query_detail = itemView.findViewById(R.id.tv_query_detail);
                tv_cardName = itemView.findViewById(R.id.tv_cardName);
                tv_expirationDate = itemView.findViewById(R.id.tv_expirationDate);
                tv_courseNameNum = itemView.findViewById(R.id.tv_courseNameNum);
                rel_course_progress = itemView.findViewById(R.id.rel_course_progress);

            }

            public void bind(FaceDetail faceDetail, Context context) {
                ImageLoader.setHeadImageResource(SharePreferenceUtil.getImageUrl() + faceDetail.getHeadPath(), context, iv_header);
                tv_memberName.setText(TextUtils.isEmpty(faceDetail.getMemberName())?"暂未录入":faceDetail.getMemberName());
                tv_cardName.setText(TextUtils.isEmpty(faceDetail.getCardName())?"暂未录入" : faceDetail.getCardName());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    tv_expirationDate.setText(faceDetail.getExpirationDate()==null?"未知":simpleDateFormat.format(simpleDateFormat.parse(faceDetail.getExpirationDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String courseName = faceDetail.getCourseName();
                int courseNum = faceDetail.getCourseNum();
                if (TextUtils.isEmpty(courseName)||courseNum==0){
                    tv_courseNameNum.setText("未知");
                }else {
                    tv_courseNameNum.setText(courseName + "第" + courseNum + "节");
                }
//                rel_course_progress.setVisibility(faceDetail.getCoachName() == null ? View.GONE : View.VISIBLE);
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