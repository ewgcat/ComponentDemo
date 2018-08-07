package com.yijian.staff.mvp.workspace.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.workspace.bean.PerfectRequestBody;
import com.yijian.staff.mvp.workspace.bean.SportStepRequedtBody;
import com.yijian.staff.net.api.ApiService;
import com.yijian.staff.net.httpmanager.RetrofitClient;
import com.yijian.staff.prefs.SharePreferenceUtil;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HttpManagerWorkSpace {

    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);
    private static String HOST;

    public static void setWorkSpaceHost(boolean isWorkspace){
        SharePreferenceUtil.setWorkSpaceVersion(isWorkspace);
        HOST = SharePreferenceUtil.getHostUrl();
    }

    public static String getH5Host(){
        return SharePreferenceUtil.getH5Url();
    }

    public static String getFileHost(){
        return SharePreferenceUtil.getImageUrl();
    }

    //执行请求
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /************* 工作室 ****************/
    //首页会员名称模糊搜索会员信息列表
    public static String WORKSPACE_QUERY_SEARCH__URL = "coach/side/fuzzy/query/list";
    //保存完美围度添加
    public static String WORKSPACE_ADD_PERFECT__URL = "coach/side/fuzzy/saveOrUpdateWD";
    //查看结果列表
    public static String WORKSPACE_QUERY_RESULT_LIST__URL = "coach/side/fuzzy/getTestList";
    //上传单个或多个文件
    public static String WORKSPACE_UPLOAD_FILE__URL = "file/newUploadFiles";
    //保存运动表现
    public static String WORKSPACE_SAVE_SPORT_URL = "coach/side/fuzzy/saveOrUpdateYD";


    // get无头有参
    public static void getNoHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getNoHeaderHasParam(SharePreferenceUtil.getHostUrl()+url, param);
        execute(observable, observer);
    }

    // get有头有参
    public static void getHasHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();

        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getHasHeaderHasParam(HOST + url, headers, param);
            execute(observable, observer);
        }
    }

    //保存完美围度
    public static void postPerfectInfo(PerfectRequestBody perfectRequestBody, Observer<JSONObject> observer){
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postPerfectInfo(HOST + WORKSPACE_ADD_PERFECT__URL, headers, perfectRequestBody);
            execute(observable, observer);
        }
    }

    //保存运动表现
    public static void postSportInfo(SportStepRequedtBody sportStepRequedtBody, Observer<JSONObject> observer){
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postSportInfo(HOST + WORKSPACE_SAVE_SPORT_URL, headers, sportStepRequedtBody);
            execute(observable, observer);
        }
    }

    public static void upLoadImageHasParam(String url, String imageFilePath, Integer fileType, Observer<JSONObject> observer){
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());

            File file = new File(imageFilePath);
            // 创建 RequestBody，用于封装构建RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // MultipartBody.Part  和后端约定好Key，这里的partName是用image
            MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFiles", file.getName(), requestFile);

            List<MultipartBody.Part> parts = new ArrayList<>();
            parts.add(body);
            Observable<JSONObject> observable = apiService.upLoadImageHasParam(SharePreferenceUtil.getHostUrl() + url,headers, fileType, parts);
            execute(observable, observer);
        }
    }

}
