package com.yijian.staff.net.httpmanager;


import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.huiji.bean.EditHuiJiVipBody;
import com.yijian.staff.mvp.reception.step1.bean.QuestionnaireAnswer;
import com.yijian.staff.mvp.reception.step2.step2Bean.PhysicalExaminationBean;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.setclass.bean.PrivateShangKeBean;
import com.yijian.staff.net.api.ApiService;
import com.yijian.staff.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.staff.net.requestbody.huijigoods.HuiJiGoodsRequestBody;
import com.yijian.staff.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.staff.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.staff.net.requestbody.savemenu.MenuRequestBody;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import org.json.JSONObject;
import java.io.File;
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

public class HttpManager {


    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }


    /*************************会籍************************/


    //会籍（客服） 获取全部会员列表
    public static String GET_HUIJI_ALL_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/list";

    //会籍 会员信息 今日来访列表
    public static String GET_HUIJI_TODAY_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/today/visit/list";

    //会籍  过期向会员列表
    public static String GET_HUIJI_OUTDATE_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/expire/list";

    //会籍  意向会员列表
    public static String GET_HUIJI_INTENT_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/intention/list";

    //会籍  潜在会员列表
    public static String GET_HUIJI_POTENTIAL_VIPER_LIST_URL = BuildConfig.HOST + "customer-service/member/potential/list";

    //所有会员的详情入口
    public static String GET_HUIJI_VIPER_DETAIL_URL = BuildConfig.HOST + "member/detail";

    //所有会员的字典入口
    public static String GET_HUIJI_VIPER_DICT_URL = BuildConfig.HOST + "dict/member/dict";

    //所有会员的字典入口
    public static String GET_HUIJI_VIPER_EDIT_URL = BuildConfig.HOST + "member/edit";


    //会籍卡产品查询
    public static String HUI_JI_CARD_GOODS_LIST_URL = BuildConfig.HOST + "card/cards-info";

    //首页搜索 会籍
    public static String INDEX_HUI_JI_QUERY_URL = BuildConfig.HOST + "customer-service/member/fuzzy/query/list";

    //会籍保存邀约
    public static String INDEX_HUI_JI_INVITATION_SAVE_URL = BuildConfig.HOST + "invitation/save";


    /*************************教练************************/

    //教练 获取全部会员列表
    public static String GET_COACH_ALL_VIPER_LIST_URL = BuildConfig.HOST + "coach/member/list";

    //教练 会员信息 今日来访列表
    public static String GET_COACH_TODAY_VIPER_LIST_URL = BuildConfig.HOST + "coach/member/today/visit/list";

    //教练  过期向会员列表
    public static String GET_COACH_OUTDATE_VIPER_LIST_URL = BuildConfig.HOST + "coach/member/expire/list";

    //教练  意向会员列表
    public static String GET_COACH_INTENT_VIPER_LIST_URL = BuildConfig.HOST + "coach/member/intention/list";

    //教练  潜在会员列表
    public static String GET_COACH_POTENTIAL_VIPER_LIST_URL = BuildConfig.HOST + "coach/member/potential/list";

    //首页搜索 教练
    public static String INDEX_COACH_QUERY_URL = BuildConfig.HOST + "coach/member/fuzzy/query/list";

    //私教课查询
    public static String COACH_PRIVATE_COURSE_LIST_URL = BuildConfig.HOST + "privatecourse/getPrivateCourseList";

    //私教课的存课列表
    public static String COACH_PRIVATE_COURSE_STOCK_PRIVATE_LIST_URL = BuildConfig.HOST + "privatecourse/stock-private/page-list";

    //私教课的上课记录基本信息
    public static String COACH_PRIVATE_COURSE_STOCK_BASE_INFO_URL = BuildConfig.HOST + "privatecourse/getMemberCourseRecordInfo";


    //查看教练的约课日程表
    public static String COACH_PRIVATE_COURSE_STOCK_ORDER_URL = BuildConfig.HOST + "privatecourse/getPrivateCourseByDay";

    //上课打卡
    public static String COACH_PRIVATE_COURSE_STOCK_RECORD_SHANGKE_URL = BuildConfig.HOST + "privatecourse/appoint/attendCoursePunchCard";

    //下课打卡
    public static String COACH_PRIVATE_COURSE_STOCK_RECORD_XIAKE_URL = BuildConfig.HOST + "privatecourse/appoint/finishCoursePunchCardAndSaveRecord";


    //获取私教课上课记录表详情
    public static String COACH_PRIVATE_COURSE_STOCK_RECORD_URL = BuildConfig.HOST + "privatecourse/getPrivateCourseRecordDetail";

    //获取体验课上课记录详情
    public static String COACH_PRIVATE_COURSE_STOCK_EXPERIENCE_RECORD_URL = BuildConfig.HOST + "experienceCourse/getExperienceRecord";

    //根据教练ID获取私教课备课模板列表
    public static String COACH_PRIVATE_COURSE_STOCK_TEMPLE_URL = BuildConfig.HOST + "privatecourse/getPrepareTemplateList";


    //工作台 首页图标
    public static String GET_WORK_INDEX_URL = BuildConfig.HOST + "homepage/data";

    //保存 图标位置
    public static String SAVE_MENU_CHANGE_URL = BuildConfig.HOST + "menu/common/item/save";

    //登录
    public static String LOGIN_URL = BuildConfig.HOST + "user/login";

    //获取验证码
    public static String GET_CODE_URL = BuildConfig.HOST + "user/verificationCode/send";

    //重置密码
    public static String RESET_PASSWORD_URL = BuildConfig.HOST + "user/password/reset";

    //添加潜在
    public static String ADD_POTENTIAL_URL = BuildConfig.HOST + "member/potential/add";


    //查询业务消息
    public static String GET_BUSINESS_MESSAGE_URL = BuildConfig.HOST + "message/businessMessageQuery";

    //获取体验课流程会员列表
    public static String GET_EXPERICECE_CLASS_URL = BuildConfig.HOST + "experienceProcess/getList";


    //体验课 邀约节点
    public static String GET_EXPERICECE_INVITE_HISTORY_URL = BuildConfig.HOST + "experienceProcess/toInvite";

    //体验课 回访节点
    public static String GET_EXPERICECE_HUI_FANG_URL = BuildConfig.HOST + "experienceProcess/toVisit";

    //体验课 会商方案
    public static String GET_EXPERICECE_HUI_SHANG_FANG_AN_URL = BuildConfig.HOST + "experienceProcess/toConsultationProgramme";

    //体验课 二次邀约
    public static String GET_EXPERICECE_INVITE_AGAIN_URL = BuildConfig.HOST + "experienceProcess/toInviteAgain";

    //体验课 会商结论
    public static String GET_EXPERICECE_HUI_SHANG_RESULT_URL = BuildConfig.HOST + "experienceProcess/toConsultationConclusion";


    //发出邀约并保存邀约相关信息
    public static String SEND_EXPERICECE_INVITE_HISTORY_URL = BuildConfig.HOST + "experienceProcess/saveInvite";


    //公用方法
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //登陆
    public static void postLogin(LoginRequestBody loginRequestBody, Observer<JSONObject> observer) {
        Observable<JSONObject> loginObservable = apiService.login(LOGIN_URL, loginRequestBody);
        execute(loginObservable, observer);
    }

    //登陆
    public static void postAddPotential(AddPotentialRequestBody addPotentialRequestBody, Observer<JSONObject> observer) {

        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> loginObservable = apiService.postAddPotential(ADD_POTENTIAL_URL, headers, addPotentialRequestBody);

            execute(loginObservable, observer);
        }
    }

    //获取验证码
    public static void getCode(String username, String telephone, Observer<JSONObject> observer) {
        Observable<JSONObject> getCodeObservable = apiService.getCode(GET_CODE_URL, username, telephone);
        execute(getCodeObservable, observer);
    }


    //重置密码
    public static void resetPassword(String username, String telephone, String verificationCode, String newPwd, String confirmPwd, ResultJSONObjectObserver observer) {
        Observable<JSONObject> getCodeObservable = apiService.resetPassword(RESET_PASSWORD_URL, username, telephone, verificationCode, newPwd, confirmPwd);
        execute(getCodeObservable, observer);
    }


    //会籍（客服） 获取全部会员列表
    public static void getHuiJiAllViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(GET_HUIJI_ALL_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //会籍 会员信息 今日来访列表
    public static void getHuiJiTodayViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(GET_HUIJI_TODAY_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //教练 获取全部会员列表
    public static void getCoachAllViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(GET_COACH_ALL_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //教练 会员信息 今日来访列表
    public static void getCoachTodayViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(GET_COACH_TODAY_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    // 首页图标
    public static void getIndexMenuList(Observer<JSONObject> observer) {

        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getIndexMenuList(GET_WORK_INDEX_URL, headers);
            execute(observable, observer);
        }
    }

    //体测录入
    public static void postRecptionTest(String memberId,
                                        PhysicalExaminationBean physicalExaminationBeanBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        String token = user.getToken();
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjQzOTExNDU2NzksInBheWxvYWQiOiJ7XCJpZFwiOlwiMVwiLFwidXNlcklkXCI6XCIxXCIsXCJtZXJjaGFudElkXCI6XCIzMzNcIixcInNob3BJZFwiOlwiMTFcIn0ifQ.9j6x14rFYJ8tuAGu2wUyFCyz12JnCfhT1NUU6kFs4ww";
        headers.put("token", token);

        Observable<JSONObject> receptionTestObservable = apiService.saveReceptionTest(RECEPTION_TEST_SAVE, headers, memberId, physicalExaminationBeanBody);
        execute(receptionTestObservable, observer);
    }

    //问卷调查_保存
    public static void postRecptionRequstion(String memberId,
                                             List<QuestionnaireAnswer> requestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        String token = user.getToken();
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjQzOTExNDU2NzksInBheWxvYWQiOiJ7XCJpZFwiOlwiMVwiLFwidXNlcklkXCI6XCIxXCIsXCJtZXJjaGFudElkXCI6XCIzMzNcIixcInNob3BJZFwiOlwiMTFcIn0ifQ.9j6x14rFYJ8tuAGu2wUyFCyz12JnCfhT1NUU6kFs4ww";
        headers.put("token", token);

        Observable<JSONObject> receptionTestObservable = apiService.postObj(RECEPTION_QUESTION_SAVE, headers, memberId, requestBody);
        execute(receptionTestObservable, observer);
    }

    //保存menu编辑状态
    public static void saveMenuChange(MenuRequestBody menuRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.saveMenuChange(SAVE_MENU_CHANGE_URL, headers, menuRequestBody);
            execute(observable, observer);
        }
    }

    //私教课查询列表
    public static void getCoachPrivateCourseList(CoachPrivateCourseRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getCoachPrivateCourseList(COACH_PRIVATE_COURSE_LIST_URL, headers, body);
            execute(observable, observer);
        }
    }

    //会籍卡产品查询列表
    public static void getHuiJiCardGoodsList(HuiJiGoodsRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getHuiJiCardGoodsList(HUI_JI_CARD_GOODS_LIST_URL, headers, body);
            execute(observable, observer);
        }
    }


    //会籍卡产品查询列表_ycm
    public static void getHuiJiCardGoodsList_ycm(ConditionBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());

            Observable<JSONObject> observable = apiService.getHuiJiCardGoodsList_ycm(HUI_JI_CARD_GOODS_LIST_URL, headers, body);
            execute(observable, observer);
        }
    }


    //教练模糊搜索会员
    public static void searchViperByCoach(Map<String, String> params, Observer<JSONObject> observer) {

        getHasHeaderHasParam(INDEX_COACH_QUERY_URL, params, observer);

    }


    //会籍模糊搜索会员
    public static void searchViperByHuiJi(Map<String, String> params, Observer<JSONObject> observer) {

        getHasHeaderHasParam(INDEX_HUI_JI_QUERY_URL, params, observer);

    }

    //会籍会员详情修改
    public static void postEditHuiJiVipInfo(String url, EditHuiJiVipBody editHuiJiVipBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();

        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.editHuiJiVipDetail(url, headers, editHuiJiVipBody);
            execute(observable, observer);
        }
    }

    //提交下课打卡数据
    public static void postXiaKeRecord(String url, PrivateShangKeBean privateShangKeBean, String state, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();

        headers.put("token", user.getToken());
        Observable<JSONObject> observable = apiService.saveXiaKeRecord(url, headers, privateShangKeBean, state);
        execute(observable, observer);
    }


    //公共
    // post没请求头没有参数
    public static void postNoHeaderNoParam(String url, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postNoHeaderNoParam(url);
        execute(observable, observer);
    }

    // post有头无参
    public static void postHasHeaderNoParam(String url, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();

        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());

            Observable<JSONObject> observable = apiService.postHasHeaderNoParam(url, headers);
            execute(observable, observer);
        }
    }

    // post无头有参
    public static void postNoHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postNoHeaderHasParam(url, param);
        execute(observable, observer);
    }

    // post有头有参
    public static void postHasHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();

        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.postHasHeaderHasParam(url, headers, param);
            execute(observable, observer);
        }
    }


    // post有头有参
    public static void postHasHeaderHasParamOfInteger(String url, Map<String, String> header, Map<String, Integer> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.postHasHeaderHasParamOfInteger(url, header, param);
        execute(observable, observer);
    }

    // get没请求头没有参数
    public static void getNoHeaderNoParam(String url, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getNoHeaderNoParam(url);
        execute(observable, observer);
    }

    // get有头无参
    public static void getHasHeaderNoParam(String url, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getHasHeaderNoParam(url, headers);
            execute(observable, observer);
        }
    }

    // get无头有参
    public static void getNoHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getNoHeaderHasParam(url, param);
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
            Observable<JSONObject> observable = apiService.getHasHeaderHasParam(url, headers, param);
            execute(observable, observer);
        }
    }

    //上传图片
    public static void upLoadImage(String url, String imageFilePath, Observer<JSONObject> observer) {
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
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);


            Observable<JSONObject> observable = apiService.upLoadImage(url, headers, body);
            execute(observable, observer);
        }
    }

    //登陆
    public static void getBusinessMessage(BusinessMessageRequestBody businessMessageRequestBody, Observer<JSONObject> observer) {

        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/test/login").navigation();
        } else {
            headers.put("token", user.getToken());
            Observable<JSONObject> observable = apiService.getBusinessMessage(GET_BUSINESS_MESSAGE_URL, headers, businessMessageRequestBody);
            execute(observable, observer);
        }
    }


    //接待人的信息
    public static final String RECEPTION_INFO = BuildConfig.HOST + "reception/person";


    //接待记录
    public static final String RECEPTION_RECORD = BuildConfig.HOST + "reception/record";

    ///qs/member/getBodyBuildTimesByMemberId 健身时间


    // //接待---问卷调查
    public static final String RECEPTION_QUESTION = BuildConfig.HOST + "qs/template";

    //接待---问卷调查--保存
    public static final String RECEPTION_QUESTION_SAVE = BuildConfig.HOST + "qs/save";

    // //接待---问卷调查结果查看
    public static final String RECEPTION_QUESTION_RESULT = BuildConfig.HOST + "qs/edit";

    // //接待---问卷调查结果查看_健身时间
    public static final String RECEPTION_QUESTION_RESULT_FITNESSTIME = BuildConfig.HOST + "qs/member/getBodyBuildTimesByMemberId";

    //接待---问卷调查--健身时间保存
    public static final String RECEPTION_QUESTION_FITNESSTIME = BuildConfig.HOST + "qs/member/saveBodyBuildTimes";


    //接待 ---体测录入--保存
    public static final String RECEPTION_TEST_SAVE = BuildConfig.HOST + "bodycheck/save";

    //接待--体测录入--查看
    public static final String RECEPTION_TEST_VIEW = BuildConfig.HOST + "bodycheck/view";


    //接待--会籍--step2-跳过
    public static final String RECEPTION_STEP2_JUMP = BuildConfig.HOST + "reception/sale-jump-body-check";

    //接待--会籍--step2-TO教练
    public static final String RECEPTION_STEP2_TOCOACH = BuildConfig.HOST + "reception/sale-to-coach-body-check";

    //接待--会籍--step3-场馆信息列表
    public static final String RECEPTION_STEP3_VENUES = BuildConfig.HOST + "venue/list";

    //接待--会籍--step3-会员不愿意购买,会籍To给教练
    public static final String RECEPTION_STEP3_TO_COACH = BuildConfig.HOST + "reception/sale-to-coach";

    //接待--会籍--step3-会员接待详细信息,用于会员不愿意购买,教练和领导接受TO界面数据
    public static final String RECEPTION_STEP3_COACH_USERDATA = BuildConfig.HOST + "reception/person/detail";

}
