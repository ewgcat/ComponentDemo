package com.yijian.staff.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * desc:
 *
 * @author:nickming date:2016/4/1
 * time: 10:32
 * e-mail：962570483@qq.com
 */

public class PictureUtil {

    private final static String TAG = "PictureUtil";
    private static final String PATH_SEPERATOR = "/";

    //读取图片的最大大小
    private static final int MAX_DECODE_PICTURE_SIZE = 1440 * 1440;

    public static File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/baolimanager");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }


    /**
     * 给予图片的目标的大小，若为n，则宽高不会大于4/3n
     *
     * @param fromPath
     * @param size
     * @return
     */
    public static Bitmap readBitmapTargetSize(String fromPath, int size) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        // 获取这个图片的宽和高
        BitmapFactory.decodeFile(fromPath, options); // 此时返回bm为空
        // 计算缩放比
        int inSampleSize = 1;
        int calulateSize = size * 4 / 3;
        while ((options.outWidth / inSampleSize) > calulateSize || (options.outHeight / inSampleSize) > calulateSize) {
            inSampleSize *= 2;
        }
        return readBitmap(fromPath, inSampleSize);
    }


    /**
     * 读取路径中的图片，然后将其转化为缩放后的bitmap
     * 如果根据inSampleSize读取的图片大小还是超过最大值，会自动缩小
     *
     * @param fromPath     路径
     * @param inSampleSize 缩小比例,如果为4,则图片长宽各缩小至四分之一
     */
    public static Bitmap readBitmap(String fromPath, int inSampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = inSampleSize > 0 ? inSampleSize : 1;
        // 获取这个图片的宽和高
        BitmapFactory.decodeFile(fromPath, options);
        int sampleSize = 1;
        while ((options.outHeight / sampleSize) * (options.outWidth / sampleSize) > MAX_DECODE_PICTURE_SIZE) {
            sampleSize *= 2;
        }
        if (inSampleSize < sampleSize) {
            options.inSampleSize = sampleSize;
        }
        options.inJustDecodeBounds = false;
        Bitmap bitmap = null;
        try {
            // 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
            bitmap = BitmapFactory.decodeFile(fromPath, options);
            if (bitmap != null) {
                Logger.i(TAG, "readBitmap bitmap decoded size=" + bitmap.getWidth() + "x" + bitmap.getHeight() + ", inSampleSize=" + options.inSampleSize);
            } else {
                Logger.i(TAG, "readBitmap decodeFile error, bitmap = null, path = " + fromPath);
            }
        } catch (final OutOfMemoryError e) {
            Logger.i(TAG, "readBitmap failed, file=" + fromPath + ", inSampleSize=" + options.inSampleSize + "error=" + e.getMessage());
            try {
                options.inSampleSize = options.inSampleSize * 2;
                bitmap = BitmapFactory.decodeFile(fromPath, options);
                if (bitmap != null) {
                    Logger.i(TAG, "readBitmap 缩小图片尝试成功, file=" + fromPath + ", inSampleSize=" + options.inSampleSize);
                } else {
                    Logger.i(TAG, "readBitmap 缩小图片尝试失败, bitmap = null, path = " + fromPath);
                }
            } catch (final OutOfMemoryError e1) {
                Logger.i(TAG, "readBitmap 缩小图片尝试仍然失败, file=" + fromPath + ", inSampleSize=" + options.inSampleSize + "error=" + e1.getMessage());
            }
            options = null;
        }
        return bitmap;
    }

    /**
     * 自动纠正被旋转图片，会释放原图片
     *
     * @param photoPath
     * @param bitmap
     * @return
     */
    public static Bitmap correctBitmapIfRotated(String photoPath, Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        Bitmap resultBitmap = null;
        int degree = readImageDegree(photoPath);
        if (degree != 0) {
            //旋转图片
            Bitmap rotateBitmap = rotateBitmap(degree, bitmap);
            if (bitmap != rotateBitmap && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            resultBitmap = rotateBitmap;
        } else {
            resultBitmap = bitmap;
        }
        return resultBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readImageDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            //Logger.e(TAG, "TCLogger exception", e);
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = bitmap;
        try {
            resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Throwable e) {
        }
        return resizedBitmap;
    }

    /**
     * 保存图片
     *
     * @param bitmap
     * @param targetPath 质量100
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String targetPath) {
        return saveBitmap(bitmap, targetPath, 100);
    }

    /**
     * 保存图片
     *
     * @param bitmap
     * @param targetPath
     * @param quality    保存质量,100最大
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String targetPath, int quality) {
        boolean result = false;

        if (bitmap == null || bitmap.isRecycled() || targetPath == null) {
            return result;
        }
        if (quality > 100 || quality <= 0) {
            quality = 100;
        }

        String dirPath = getFileDir(targetPath);
        ensureDir(dirPath);

        File file = new File(targetPath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
                out.flush();
                out.close();
                result = true;
            }
        } catch (FileNotFoundException e) {
            //Logger.e(TAG, String.format("save bitmap to %s fail, file not found", targetPath), e);
        } catch (IOException e) {
            //Logger.e(TAG, String.format("save bitmap to %s fail, IOException", targetPath), e);
        }
        return result;
    }

    /**
     * 获得文件路径
     *
     * @param filePath
     * @return
     */
    public static String getFileDir(String filePath) {
        String result = null;
        if (TextUtils.isEmpty(filePath)) {
            return result;
        }
        int lastSeperatorIndex = filePath.lastIndexOf(PATH_SEPERATOR);
        if (lastSeperatorIndex > 0) {
            result = filePath.substring(0, lastSeperatorIndex);
        }
        return result;
    }

    public static boolean ensureDir(String path) {
        if (null == path) {
            return false;
        }

        boolean ret = false;

        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            try {
                ret = file.mkdirs();
//			    TCLogger.d(TAG, String.format("create dir(%s)", path));
            } catch (SecurityException se) {
                //Logger.e(TAG, String.format("create dir(%s) fail", path));
            }
        }
        return ret;
    }


    /**
     * 把bitmap转换成String
     *
     * @param filePath
     * @return
     */
    public static String bitmapToString(String filePath) {

        Bitmap bm = getSmallBitmap(filePath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();

        return android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);

    }


    /**
     * 从SD卡中获取图片并且比例压缩
     *
     * @param path    路径
     * @param mHeight 自定义高度
     * @param mWidth  自定义宽度
     * @return
     */
    public static Bitmap getBitmapFromSDCard(String path, int mHeight, int mWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        //计算比例值
        options.inSampleSize = calculateInSampleSize(options, mHeight, mWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 计算压缩比例值inSampleSize
     *
     * @param options 压缩的参数设置
     * @param mHeight 想要的高度
     * @param mWidth  想要的宽度
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int mHeight, int mWidth) {
        //原尺寸大小
        int yHeight = options.outHeight;
        int yWidth = options.outWidth;

        int inSampleSize = 1;
        //如果宽度大的话根据宽度固定大小缩放
        if (yWidth > yHeight && yWidth > mWidth) {
            inSampleSize = (int) (yWidth / mWidth);
        } else if (yWidth < yHeight && yHeight > mHeight) {
            //如果高度高的话根据宽度固定大小缩放
            inSampleSize = (int) (yHeight / mHeight);
        }
        if (inSampleSize <= 0)
            inSampleSize = 1;
        return inSampleSize;
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {

        File file = new File(filePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int height = options.outHeight;
        int width = options.outWidth;
        //double scale = Math.sqrt((float)file.length()/(40*1024));

        double scale = Math.sqrt(file.length() / (80 * 1024));

        options.outHeight = (int) (height / scale);
        options.outWidth = (int) (width / scale);
        options.inSampleSize = (int) (scale + 0.5);
        options.inJustDecodeBounds = false;

//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(filePath, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, 480, 800);
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 根据路径删除图片
     *
     * @param path
     */
    public static void deleteTempFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 添加到图库
     */
    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * 获取保存图片的目录
     *
     * @return
     */
    public static File getAlbumDir() {
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/baolimanager");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 获取保存 隐患检查的图片文件夹名称
     *
     * @return
     */
    public static String getAlbumName() {
        return "sheguantong";
    }


    public static Bitmap compressBitmapMax(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, output);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到output中
        int options = 100;
        while (output.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于(maxkb)30kb,大于继续压缩
            output.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            bmp.compress(Bitmap.CompressFormat.JPEG, options, output);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
        return bitmap;
    }

    /**
     * Save image to the SD card
     *
     * @param photoBitmap
     * @param photoName
     * @param path
     */
    public static void savePhotoToSDCard(Bitmap photoBitmap, String path, String photoName) {
        if (checkSDCardAvailable()) {
            File photoFile = new File(path, photoName);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                }
            } catch (Exception e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存文件到sd上面
     *
     * @param photoBitmap 需要保存的图片
     * @param path        保存的路径
     */
    public static boolean savePhotoToCach(Bitmap photoBitmap, String path) {
        boolean isSaveSuccessed = false;
        if (checkSDCardAvailable()) {
            Logger.i(TAG, "path=" + path);
            File photoFile = new File(path);
            if (!photoFile.exists()) {
                photoFile.mkdirs();
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null && fileOutputStream != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                        fileOutputStream.flush();
                        isSaveSuccessed = true;

                    }
                }
            } catch (Exception e) {
                if (photoFile != null) {
                    photoFile.delete();
                }
                Logger.i(TAG, e.toString());
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Exception e) {
                    Logger.i(TAG, e.toString());
                }
            }
        }
        return isSaveSuccessed;
    }

    /**
     * Check the SD card
     *
     * @return
     */
    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }


    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }


    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            if (path.endsWith(".jpg") || path.endsWith(".Jpeg") || path.endsWith(".JPEG")) {
                ExifInterface exifInterface = new ExifInterface(path);
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }

        } catch (IOException e) {
            Logger.i(TAG, e.toString());
        }
        return degree;
    }


    /**
     * 修正图片显示
     *
     * @param path
     * @return
     */
    public static Bitmap modifyImageDisplay(String path) {
        Bitmap bitmap = null;
        Bitmap cbitmap = null;
        /**
         * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
         */
        int degree = readPictureDegree(path);

        BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上


        opts.inSampleSize = 1;
        cbitmap = BitmapFactory.decodeFile(path, opts);


        /**
         * 把图片旋转为正的方向
         */
        if (cbitmap != null) {
            bitmap = rotaingImageView(degree, cbitmap);
        }

        return bitmap;
    }

}
