package com.yijian.clubmodule.net.httpmanager;


import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.retrofit.RetrofitClient;
import com.yijian.clubmodule.net.api.ApiService;
import com.yijian.commonlib.prefs.SharePreferenceUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadManager {


    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static String UPLOAD_URL = SharePreferenceUtil.getHostUrl() + "/file/newUploadFiles";

    //公用方法
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 文件类型(0：证书，1：品牌设置，2：品牌相册，3：门店封面，4：门店相册，5：员工头像，6：会员头像)
     */
    public static void upLoadFiles(List<String> list, String fileType, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            List<MultipartBody.Part> bodys = new ArrayList<>();
            headers.put("token", user.getToken());
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i);
                File file = new File(s);
                // 创建 RequestBody，用于封装构建RequestBody
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part  和后端约定好Key，这里的partName是用uploadFiles
                MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFiles", file.getName(), requestFile);
                bodys.add(body);
            }

            Observable<JSONObject> observable = apiService.uploadFiles(UPLOAD_URL, headers, fileType, bodys);
            execute(observable, observer);
        }
    }


}
