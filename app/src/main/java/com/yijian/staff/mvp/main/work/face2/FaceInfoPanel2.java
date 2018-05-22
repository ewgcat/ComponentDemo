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

    private void setTranslucentStatus(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {//4.4 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    private ScrollView scrollview;
    private RecyclerView rv_face;

    public FaceInfoPanel2(final Context context, List<FaceDetail> faceDetails) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.pop_face_panal2, null);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setClippingEnabled(false);

        /*setTranslucentStatus((Activity)context);
        int statusBarHeight = CommonUtil.getStatusBarHeight(context);
        RelativeLayout rel_header = mMenuView.findViewById(R.id.rel_header);
        RelativeLayout.LayoutParams rel_top_lp = (RelativeLayout.LayoutParams) rel_header.getLayoutParams();
        rel_top_lp.setMargins(0, statusBarHeight, 0, 0);
        rel_header.setLayoutParams(rel_top_lp);*/

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

        this.setFocusable(true);
        this.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
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
                ImageLoader.setImageResource(faceDetail.getImgHeader(), context, iv_header);
                tv_memberName.setText(faceDetail.getMemberName());
                tv_cardName.setText(faceDetail.getCardName());
                tv_expirationDate.setText(faceDetail.getExpirationDate());
                tv_courseNameNum.setText(faceDetail.getCourseName() + faceDetail.getCourseNum());
                tv_query_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollview.setVisibility(View.VISIBLE);
                        rv_face.setVisibility(View.GONE);
                    }
                });
            }

        }

    }


}