package com.yijian.staff.mvp.main.work.face;

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
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


public class FaceInfoPanel extends PopupWindow {

    /**
     * 图片模糊
     */
    private Bitmap blur(Bitmap bitmap, float radius, Context context) {
        Bitmap output = Bitmap.createBitmap(bitmap); // 创建输出图片
        RenderScript rs = RenderScript.create(context); // 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap); // 创建用于输入的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 创建用于输出的脚本类型
        gaussianBlue.setRadius(radius); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setInput(allIn); // 设置输入脚本类型
        gaussianBlue.forEach(allOut); // 执行高斯模糊算法，并将结果填入输出脚本类型中
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        return output;
    }

    /**
     * 图片等比例放大
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

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


    public FaceInfoPanel(final Context context, Bitmap resultBitmap, List<FaceDetail> faceDetails) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.pop_face_panal, null);
        this.setContentView(mMenuView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setClippingEnabled(false);

        setTranslucentStatus((Activity)context);

        ImageView iv_bg = mMenuView.findViewById(R.id.iv_bg);
        Bitmap newBitmap = zoomImg(resultBitmap, DensityUtil.getScreenWidth(context), DensityUtil.getScreenHeight(context));
        iv_bg.setImageBitmap(blur(newBitmap, 20, context));
        RelativeLayout rel_container = mMenuView.findViewById(R.id.rel_container);
        ViewTreeObserver vto = iv_bg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                // 保证只调用一次
                iv_bg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 组件生成cache（组件显示内容）
                iv_bg.buildDrawingCache();
                // 得到组件显示内容
                Bitmap bitmap = iv_bg.getDrawingCache();
                // 局部模糊处理
                BitmapUtils.blur(context, bitmap, rel_container, 25);
            }
        });

        mMenuView.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        RecyclerView rv_face = mMenuView.findViewById(R.id.rv_face);
        rv_face.setLayoutManager(new LinearLayoutManager(context));
        rv_face.setAdapter(new FaceAdapter(faceDetails));

        this.setFocusable(true);
        this.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (!(v instanceof Button)) {
                    dismiss();
                }
                return true;
            }
        });
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

                    }
                });
            }

        }

    }


}