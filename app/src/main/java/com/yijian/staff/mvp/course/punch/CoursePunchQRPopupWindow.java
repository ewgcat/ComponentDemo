package com.yijian.staff.mvp.course.punch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.jwsd.libzxing.QRCodeManager;
import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/15 09:42:18
 */
public class CoursePunchQRPopupWindow extends PopupWindow {

    private Context context;
    private ImageView iv;


    public CoursePunchQRPopupWindow(Context context) {
        this(context,null);
    }

    public CoursePunchQRPopupWindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CoursePunchQRPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.qrcode_dialog, null);
        setContentView(view);
      setAnimationStyle(R.style.locktime_popwin_anim_style);
      setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        iv = view.findViewById(R.id.iv);
    }

    public void setQR(String qr){

        Bitmap qrCode = QRCodeManager.getInstance().createQRCode(qr, CommonUtil.dp2px(context,200), CommonUtil.dp2px(context,200));
        iv.setImageBitmap(qrCode);
    }


              ;
                
    public void setBackgroundAlpha(Activity activity, float bgAlpha){
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(layoutParams);
    }


}
