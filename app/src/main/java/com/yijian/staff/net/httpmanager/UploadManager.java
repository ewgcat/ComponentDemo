package com.yijian.staff.net.httpmanager;


import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.coach.experienceclass.step2.bean.AccessRecordBean;
import com.yijian.staff.mvp.coach.preparelessons.PrivatePrepareLessonBody;
import com.yijian.staff.mvp.huiji.bean.EditHuiJiVipBody;
import com.yijian.staff.mvp.reception.step1.bean.QuestionnaireAnswer;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.setclass.bean.PrivateShangKeBean;
import com.yijian.staff.net.api.ApiService;
import com.yijian.staff.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.staff.net.requestbody.advice.AddAdviceBody;
import com.yijian.staff.net.requestbody.authcertificate.AuthCertificateRequestBody;
import com.yijian.staff.net.requestbody.huifang.AddHuiFangResultBody;
import com.yijian.staff.net.requestbody.huijigoods.HuiJiGoodsRequestBody;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.staff.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.staff.net.requestbody.questionnaire.QuestionnaireRequestBody;
import com.yijian.staff.net.requestbody.savemenu.MenuRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;

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

public class UploadManager {


    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static String UPLOAD_URL = BuildConfig.HOST+"/file/newUploadFiles";

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
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            List<MultipartBody.Part> bodys = new ArrayList<>();
            headers.put("token", user.getToken());
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i);
                File file = new File(s);
                // 创建 RequestBody，用于封装构建RequestBody
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part  和后端约定好Key，这里的partName是用image
                MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFiles", file.getName(), requestFile);
                bodys.add(body);
            }

            Observable<JSONObject> observable = apiService.uploadFiles(UPLOAD_URL, headers, fileType, bodys);
            execute(observable, observer);
        }
    }




}
