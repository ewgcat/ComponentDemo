package com.yijian.staff.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by COOTEK on 2017/7/31.
 */

public class FileUtil {
    private final static String TAG = "FileUtil";


    public static File getDefaultCacheFile(Context context) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File file = null;
//            file = context.getExternalCacheDir();//获取系统管理的sd卡缓存文件
//            if (file == null) {//如果获取的文件为空,就使用自己定义的缓存文件夹做缓存路径
//                file = new File(getCacheFilePath(context));
//                makeDirs(file);
//            }
//            return file;
//        } else {
//            return context.getCacheDir();
//        }
    }

    private static String getCacheFilePath(Context context) {
        String packageName = context.getPackageName();
        return "/mnt/sdcard/" + packageName;
    }


    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                size = fis.available();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 创建未存在的文件夹
     *
     * @param file
     * @return
     */
    public static File makeDirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static String clipFileName(String path) {
        int index = path.lastIndexOf("/");
        if (index != -1) {
            String expendName = path.substring(index + 1);
            if (expendName.contains("?")) {
                return expendName.substring(0, expendName.indexOf("?"));
            }
        }
        return null;
    }


    /**
     * 读取 assets 文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readData(Context context, String fileName) {
        InputStream inStream = null;
        String data = null;
        try {
            inStream = context.getAssets().open(fileName);     //打开assets目录中的文本文件
            byte[] bytes = new byte[inStream.available()];  //inStream.available()为文件中的总byte数
            inStream.read(bytes);
            inStream.close();
            data = new String(bytes, "utf-8");        //将bytes转为utf-8字符串
        } catch (IOException e) {
//            Logger.e(e.toString());
            e.printStackTrace();
        }
        return data;
    }


    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static File getLocalFile(String path) {
        return new File(path);
    }

    public static void writeToFile(String path, String content) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File newFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 获取随机存取文件
     *
     * @param path       文件路径
     * @param loadBytes  文件已下载大小
     * @param totalBytes 文件总大小
     * @return 文件
     * @throws IOException
     */
    public static RandomAccessFile getRandomAccessFile(String path, int loadBytes, int totalBytes) throws IOException {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("found invalid internal destination path, empty");
        }

        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            throw new RuntimeException(
                    formatString("found invalid internal destination path[%s]," +
                            " & path is directory[%B]", path, file.isDirectory()));
        }

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException(formatString("create new file error %s",
                        file.getAbsolutePath()));
            }
        }

        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        if (totalBytes > 0) {
            final long breakpointBytes = accessFile.length();
            final long requiredSpaceBytes = totalBytes - breakpointBytes;

            final long freeSpaceBytes = getFreeSpaceBytes(path);

            if (freeSpaceBytes < requiredSpaceBytes) {
                accessFile.close();
                // throw a out of space exception.
                throw new RuntimeException(
                        formatString("The file is too large to store, breakpoint in bytes: " +
                                " %d, required space in bytes: %d, but free space in bytes: " +
                                "%d", breakpointBytes, requiredSpaceBytes, freeSpaceBytes));
            } else {
                // pre allocate.
                accessFile.setLength(totalBytes);
            }
        }

        if (loadBytes > 0) {
            accessFile.seek(loadBytes);
        }
        return accessFile;
    }

    /**
     * 格式化字符串
     *
     * @param msg  格式数据
     * @param args 参数
     * @return 格式化字符串
     */
    public static String formatString(final String msg, Object... args) {
        return String.format(Locale.ENGLISH, msg, args);
    }

    /**
     * 获取空闲的空间大小
     *
     * @param path 文件路径
     * @return 空间大小
     */
    public static long getFreeSpaceBytes(final String path) {
        long freeSpaceBytes;
        final StatFs statFs = new StatFs(path);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            freeSpaceBytes = statFs.getAvailableBytes();
        } else {
            //noinspection deprecation
            freeSpaceBytes = statFs.getAvailableBlocks() * (long) statFs.getBlockSize();
        }

        return freeSpaceBytes;
    }

    public interface CompressCallback {
        void onSuccess(File file);
    }

    public static String readTextFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String temp = "";
            StringBuilder result = new StringBuilder();
            while ((temp = reader.readLine()) != null) {
                result.append(temp);
            }
            return result.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTextFile(File file, String data) {
        if (!file.exists())
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(data, 0, data.length() - 1);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            }
        }
        if (cachePath == null) {
            cachePath = context.getCacheDir().getPath();
        }

        return cachePath + File.separator;
    }

    public static File createImageFile(@NonNull Context context) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File filedir = new File(context.getExternalCacheDir().getAbsolutePath() + "/baolimanager");
        if (!filedir.exists()) {
            filedir.mkdirs();
        }
        try {
            File image = File.createTempFile(imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    filedir      /* directory */);
            return image;
        } catch (IOException e) {
            //do noting
            return null;
        }
    }

    public static void deleteFile(String uri) {
        File file = new File(uri);
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
                Logger.i("FileUtil", "deleteFile :" + file.getPath());
            } else if (file.isDirectory()) { // 否则如果它是一个目录

                File[] filelist = file.listFiles();// 声明目录下所有的文件 files[];
                if (filelist == null) {
                    return;
                }
                for (int i = 0; i < filelist.length; i++) { // 遍历目录下所有的文件
                    FileUtil.deleteFile(filelist[i].getAbsolutePath()); // 把每个文件 用这个方法进行迭代
                }
                file.delete();
            }
        } else {
            Logger.i("FileUtil", "文件不存在！" + "\n");
        }
    }

    /**
     * 删除一个目录下所有文件
     *
     * @param dir
     */
    public static void deleteDir(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

    /**
     * 删除图片缩略图，和原图
     *
     * @param files
     */
    public static void deleteFiles(List<File> files) {
        if (files != null) {
            try {
                for (File file : files) {
                    if (file != null && file.exists()) {
                        file.delete();
                        String smallPath = file.getAbsolutePath();
                        String bigPath = smallPath.replace("baolimanager/small_", "baolimanager/");
                        File bigFile = new File(bigPath);
                        if (bigFile.exists()) {
                            bigFile.delete();
                        }
                    }
                }
            } catch (Exception e) {
                //Logger.d(TAG,e.toString());
            }
        }
    }


    public static void scal(Uri fileUri, Uri desUri) {
        File sourceFile = new File(fileUri.getPath());
        String path = sourceFile.getPath();
        long fileSize = sourceFile.length();
        File outputFile = new File(desUri.getPath());
        final long fileMaxSize = 20 * 1024;
        if (fileSize >= fileMaxSize) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int height = options.outHeight;
            int width = options.outWidth;

            double scale = Math.sqrt((float) fileSize / fileMaxSize);
            options.outHeight = (int) (height / scale);
            options.outWidth = (int) (width / scale);
            options.inSampleSize = (int) (scale + 0.5);
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
//            outputFile = new File(PhotoUtil.createImageFile().getPath());
            outputFile = new File(desUri.getPath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            } else {
                File tempFile = outputFile;
                outputFile = new File(desUri.getPath());
                FileUtil.copyFileUsingFileChannels(tempFile, outputFile);
            }

        }

    }

    public static void scal(final Uri fileUri, final CompressCallback compressCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = fileUri.getPath();
                File outputFile = new File(path);
                long fileSize = outputFile.length();
                final long fileMaxSize = 20 * 1024;
                if (fileSize >= fileMaxSize) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(path, options);
                    int height = options.outHeight;
                    int width = options.outWidth;

                    double scale = Math.sqrt((float) fileSize / fileMaxSize);
                    options.outHeight = (int) (height / scale);
                    options.outWidth = (int) (width / scale);
                    options.inSampleSize = (int) (scale + 0.5);
                    options.inJustDecodeBounds = false;

                    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
//            outputFile = new File(PhotoUtil.createImageFile().getPath());
                    outputFile = PictureUtil.createImageFile();
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(outputFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (!bitmap.isRecycled()) {
                        bitmap.recycle();
                    } else {
                        File tempFile = outputFile;
                        outputFile = PictureUtil.createImageFile();
                        FileUtil.copyFileUsingFileChannels(tempFile, outputFile);
                    }

                }
                compressCallback.onSuccess(outputFile);
            }
        }).start();


    }

    public static void copyFileUsingFileChannels(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static boolean createHeadIconFile(String imageDir, String name) {

        //判断目录是否存在，不存在则创建该目录
        File dirFile = new File(imageDir);
        if (!dirFile.exists()) {
            boolean mkdirs = dirFile.mkdirs();
        }
        //文件是否创建成功
        boolean isFileCreateSuccess = false;

        String imagePath = imageDir + "/" + name;


        //判断文件是否存在，不存在则创建该文件
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            try {
                isFileCreateSuccess = imageFile.createNewFile();//创建文件
                Logger.i(TAG, "imageFile 不存在，isFileCreateSuccess=" + isFileCreateSuccess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isFileCreateSuccess = true;
        }

        return isFileCreateSuccess;

    }


    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        long size = 0;
        if (file.exists()) {
            try {
                java.io.File[] fileList = file.listFiles();
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            } catch (Exception e) {
                Logger.i(TAG, e.toString());
            }
        }

        return size;
    }


}

