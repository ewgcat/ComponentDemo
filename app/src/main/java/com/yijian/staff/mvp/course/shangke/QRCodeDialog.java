package com.yijian.staff.mvp.course.shangke;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jwsd.libzxing.QRCodeManager;
import com.yijian.staff.R;
import com.yijian.staff.util.CommonUtil;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/15 09:42:18
 */
public class QRCodeDialog extends Dialog {

    private Context context;
    private ImageView iv;

    public QRCodeDialog(@NonNull Context context) {
        this(context, 0);
    }

    public QRCodeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();

    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.qrcode_dialog, null);
        setContentView(view);
        iv = view.findViewById(R.id.iv);
    }

    public void setQR(String qr){

        Bitmap qrCode = QRCodeManager.getInstance().createQRCode(qr, CommonUtil.dp2px(context,200), CommonUtil.dp2px(context,200));
        iv.setImageBitmap(qrCode);
    }



}
