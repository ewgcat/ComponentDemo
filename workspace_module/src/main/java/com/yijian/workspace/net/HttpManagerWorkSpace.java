package com.yijian.workspace.net;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.retrofit.RetrofitClient;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.workspace.bean.DynamicRequestBody;
import com.yijian.workspace.bean.PerfectRequestBody;
import com.yijian.workspace.bean.SportStepRequedtBody;
import com.yijian.workspace.bean.StaticRequestBody;

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
import module.LoginRequestBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HttpManagerWorkSpace {

    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);
    private static String HOST;

    //登录
    public static String LOGIN_URL = "user/login";

    public static void setWorkSpaceHost(boolean isWorkspace) {
        HOST = SharePreferenceUtil.getHostUrl();
    }

    public static String getH5Host() {
        return SharePreferenceUtil.getH5Url();
    }

    public static String getFileHost() {
        return SharePreferenceUtil.getImageUrl();
    }

    //执行请求
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    //登陆
    public static void postLogin(LoginRequestBody loginRequestBody, Observer<JSONObject> observer) {
        Observable<JSONObject> loginObservable = apiService.login(SharePreferenceUtil.getHostUrl() + LOGIN_URL, loginRequestBody);
        execute(loginObservable, observer);
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
    //保存静态评估
    public static String WORKSPACE_SAVE_STATIC_URL = "coach/side/fuzzy/saveOrUpdateTy";
    //保存动态评估
    public static String WORKSPACE_SAVE_DYNAMIC_URL = "coach/side/fuzzy/saveOrUpdateDt";


    // get无头有参
    public static void getNoHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getNoHeaderHasParam(SharePreferenceUtil.getHostUrl() + url, param);
        execute(observable, observer);
    }

    // get有头有参
    public static void getHasHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();

        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getHasHeaderHasParam(HOST + url, headers, param);
            execute(observable, observer);
        }
    }

    //保存完美围度
    public static void postPerfectInfo(PerfectRequestBody perfectRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postPerfectInfo(HOST + WORKSPACE_ADD_PERFECT__URL, headers, perfectRequestBody);
            execute(observable, observer);
        }
    }

    //保存运动表现
    public static void postSportInfo(SportStepRequedtBody sportStepRequedtBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postSportInfo(HOST + WORKSPACE_SAVE_SPORT_URL, headers, sportStepRequedtBody);
            execute(observable, observer);
        }
    }

    //保存静态评估表现
    public static void postStaticInfo(StaticRequestBody staticRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postStaticInfo(HOST + WORKSPACE_SAVE_STATIC_URL, headers, staticRequestBody);
            execute(observable, observer);
        }
    }

    //保存动态评估表现
    public static void postDynamicInfo(DynamicRequestBody dynamicRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postDynamicInfo(HOST + WORKSPACE_SAVE_DYNAMIC_URL, headers, dynamicRequestBody);
            execute(observable, observer);
        }
    }


    /**
     * 单文件上传
     * @param url
     * @param imageFilePath
     * @param fileType
     * @param observer
     */
    public static void upLoadImageHasParam(String url, String imageFilePath, Integer fileType, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());

            File file = new File(imageFilePath);
            // 创建 RequestBody，用于封装构建RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // MultipartBody.Part  和后端约定好Key，这里的partName是用image
            MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFiles", file.getName(), requestFile);

            List<MultipartBody.Part> parts = new ArrayList<>();
            parts.add(body);
            Observable<JSONObject> observable = apiService.upLoadImageHasParam(SharePreferenceUtil.getHostUrl() + url, headers, fileType, parts);
            execute(observable, observer);
        }
    }

    /**
     * 多文件上传
     * @param url
     * @param paths
     * @param fileType
     * @param observer
     */
    public static void upLoadImageListHasParam(String url, Integer fileType, Observer<JSONObject> observer, String... paths) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            List<MultipartBody.Part> parts = new ArrayList<>();
            for (String imageFilePath : paths) {
                File file = new File(imageFilePath);
                // 创建 RequestBody，用于封装构建RequestBody
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part  和后端约定好Key，这里的partName是用image
                MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFiles", file.getName(), requestFile);
                parts.add(body);
            }
            Observable<JSONObject> observable = apiService.upLoadImageHasParam(SharePreferenceUtil.getHostUrl() + url, headers, fileType, parts);
            execute(observable, observer);
        }
    }

}
