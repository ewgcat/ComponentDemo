package com.yijian.staff.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class CameraUtil {

    /**
     * 调用系统摄像头
     * @param activity
     * @param TAKE_PHOTO_WITH_DATA 请求code
     * @param imageUri 图片路径
     */
    public static void takePhoto(Activity activity, int TAKE_PHOTO_WITH_DATA, Uri imageUri ){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent,TAKE_PHOTO_WITH_DATA);
    }

    /**
     * 调用系统裁剪
     * @param activity
     * @param uri 图片路径
     * @param outputX 图片宽
     * @param outputY 高
     * @param requestCode 请求code
     */
    public static void cropImageUri(Activity activity, Uri uri, int outputX, int outputY, int requestCode){

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);

        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", outputX);

        intent.putExtra("outputY", outputY);

        intent.putExtra("scale", true);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        intent.putExtra("return-data", false);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("noFaceDetection", true); // no face detection

        activity.startActivityForResult(intent, requestCode);

    }




}
