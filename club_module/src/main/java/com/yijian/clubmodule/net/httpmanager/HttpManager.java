package com.yijian.clubmodule.net.httpmanager;


import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.yijian.clubmodule.net.requestbody.login.LoginRequestBody;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.retrofit.RetrofitClient;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.clubmodule.net.api.ApiService;
import com.yijian.clubmodule.net.httpmanager.url.CourseUrls;
import com.yijian.clubmodule.net.httpmanager.url.HuiFangUrls;
import com.yijian.clubmodule.net.requestbody.AbortFuFangBody;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.net.requestbody.CardRequestBody;
import com.yijian.clubmodule.net.requestbody.EditHuiJiVipBody;
import com.yijian.clubmodule.net.requestbody.HuiFangTypeRequestBody;
import com.yijian.clubmodule.net.requestbody.HuiJiInviteListRequestBody;
import com.yijian.clubmodule.net.requestbody.HuifangRecordRequestBody;
import com.yijian.clubmodule.net.requestbody.PrivateCoursePingJiaRequestBody;
import com.yijian.clubmodule.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.clubmodule.net.requestbody.advice.AddAdviceBody;
import com.yijian.clubmodule.net.requestbody.course.SaveCourseRequestBody;
import com.yijian.clubmodule.net.requestbody.huifang.AddHuiFangResultBody;
import com.yijian.clubmodule.net.requestbody.huifang.HuifangTaskRequestBody;
import com.yijian.clubmodule.net.requestbody.invite.SaveInviteBody;
import com.yijian.clubmodule.net.requestbody.message.BusinessMessageRequestBody;
import com.yijian.clubmodule.net.requestbody.privatecourse.CoachPrivateCourseRequestBody;
import com.yijian.clubmodule.net.requestbody.questionnaire.QuestionnaireRequestBody;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpManager {


    public static final String QUERY_ENTRANCE_QR = "user/getEntranceParam";
    private static ApiService apiService = RetrofitClient.mRetrofit.create(ApiService.class);



    /*************************会籍************************/


    //会籍（客服） 获取全部会员列表
    public static String GET_HUIJI_ALL_VIPER_LIST_URL = "customer-service/member/list";

    //会籍 会员信息 今日来访列表
    public static String GET_HUIJI_TODAY_VIPER_LIST_URL = "customer-service/member/today/visit/list";

    //会籍  过期向会员列表
    public static String GET_HUIJI_OUTDATE_VIPER_LIST_URL = "customer-service/member/expire/list";

    //会籍  意向会员列表
    public static String GET_HUIJI_INTENT_VIPER_LIST_URL = "customer-service/member/intention/list";

    //会籍  潜在会员列表
    public static String GET_HUIJI_POTENTIAL_VIPER_LIST_URL = "customer-service/member/potential/list";

    //所有会员的详情入口
    public static String GET_VIPER_DETAIL_URL = "member/detail";

    //所有会员的字典入口
    public static String GET_HUIJI_VIPER_DICT_URL = "dict/member/dict";

    //所有会员的字典入口
    public static String GET_HUIJI_VIPER_EDIT_URL = "member/edit";


    //会籍卡产品查询
    public static String HUI_JI_CARD_GOODS_LIST_URL = "card/cards-info";

    //首页搜索 会籍
    public static String INDEX_HUI_JI_QUERY_URL = "customer-service/member/fuzzy/query/list";

    //会籍保存邀约
    public static String INDEX_HUI_JI_INVITATION_SAVE_URL = "invitation/save";
    //单个邀约记录
    public static String INDEX_HUI_JI_INVITATION_GET_URL = "invitation/get";

    //会籍邀约记录
    public static String INDEX_HUI_JI_INVITATION_RECORD_URL = "invitation/select";





    /*************************教练************************/

    //教练 获取全部会员列表
    public static String GET_COACH_ALL_VIPER_LIST_URL = "coach/member/list";

    //教练 会员信息 今日来访列表
    public static String GET_COACH_TODAY_VIPER_LIST_URL = "coach/member/today/visit/list";

    //教练  过期向会员列表
    public static String GET_COACH_OUTDATE_VIPER_LIST_URL = "coach/member/expire/list";

    //教练  意向会员列表
    public static String GET_COACH_INTENT_VIPER_LIST_URL = "coach/member/intention/list";

    //教练  潜在学员列表
    public static String GET_COACH_POTENTIAL_VIPER_LIST_URL = "coach/member/potentialStudent/list";

    //首页搜索 教练
    public static String INDEX_COACH_QUERY_URL = "coach/member/fuzzy/query/list";



    //私教课查询
    public static String COACH_PRIVATE_COURSE_LIST_URL = "privatecourse/getPrivateCourseList";

    //私教课的存课列表
    public static String COACH_PRIVATE_COURSE_STOCK_PRIVATE_LIST_URL = "privatecourse/stock-private/page-list";

    //私教课的上课记录基本信息
    public static String COACH_PRIVATE_COURSE_STOCK_BASE_INFO_URL = "privatecourse/getMemberCourseRecordInfo";

    //接待记录
    public static final String RECEPTION_RECORD_TEMP = "reception/record/temp";

    
    //场馆信息列表
    public static final String RECEPTION_STEP3_VENUES = "venue/listByShopId";


    //获取私教课上课记录表详情
    public static String COACH_PRIVATE_COURSE_STOCK_RECORD_URL = "privatecourse/getPrivateCourseRecordDetail";

  
   


    //工作台 首页图标
    public static String GET_WORK_NEW_MENU_URL = "homepage/new/data";



    //登录
    public static String LOGIN_URL = "user/login";


    //获取验证码
    public static String GET_CODE_URL = "user/verificationCode/send";

    //重置密码
    public static String RESET_PASSWORD_URL = "user/password/reset";
    public static String EDIT_PASSWORD_URL = "user/password/modify";

    //添加潜在
    public static String ADD_POTENTIAL_URL = "member/potential/add";

    public static String GET_USER_INFO_URL = "user/abstract-infomation";


    //查询业务消息
    public static String GET_BUSINESS_MESSAGE_URL = "message/businessMessageQuery";

   
    //添加意见反馈
    public static String ADD_FEEDBACK_URL = "feedBack/addfeedBack";

    //type 1. 关于我们 2.俱乐部 3.教练信息url 后面?coach_id=coach_id 然后生成二维码
    public static String ABOUT_US_AND_CLUB_AND_QR_URL = "webPage/getWebPage";
    

    //添加职业证书
    public static String GET_CERTIFICATE_URL = "certificate/getCertificate";

    //获取调查问卷列表
    public static String GET_QUESTION_NIAR_LIST_URL = "qs/getQsList";





    public static String GET_COACH_HUI_FANG_REASON_LIST_URL = "dict/review-reason/dict-items";

    //教练  会员管理界面：打电话回访,通知后台
    public static String GET_VIP_COACH_HUI_FANG_CALL_PHONE_URL = "coach/add-record/call-for-interview";

    public static String POST_ACCESS_STATISTICS_URL = "syslog/addAccessLog";


   


    // 用户获取人脸登陆session
    public static String GET_FACE_LOGIN_SESSION = "member/faceLogin";

    //获取所有id获取人员信息
    public static String GET_FACE_MENBERSHOWINFO = "member/menberShowInfo";

    //消除app模块小红点
    public static String CLEAR_RED_POINT_URL = "message/eliminateAppModuleFlag";

    //获取app模块小红点
    public static String QUERY_RED_POINT_URL = "message/findAppModuleFlagByAlias";

    //地区列表
    public static String QUERY_ADDRESS_URL = "province";

   
    public static String CLUB_DETAIL_URL = "brand";



    //登陆
    public static void postLogin(LoginRequestBody loginRequestBody, Observer<JSONObject> observer) {
        Observable<JSONObject> loginObservable = apiService.login(SharePreferenceUtil.getHostUrl() + LOGIN_URL, loginRequestBody);
        execute(loginObservable, observer);
    }

    //访问
    public static void postAccessStatistics(AccessStatisticsRequestBody accessStatisticsRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            List<AccessStatisticsRequestBody> list = new ArrayList<>();
            list.add(accessStatisticsRequestBody);
            Observable<JSONObject> observable = apiService.postAccessStatistics(SharePreferenceUtil.getHostUrl() + POST_ACCESS_STATISTICS_URL, headers, list);
            execute(observable, observer);
        }
    }

    //私课评价
    public static void postPrivateCoursePingJia(PrivateCoursePingJiaRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");

            Observable<JSONObject> observable = apiService.postPrivateCoursePingJia(SharePreferenceUtil.getHostUrl() + CourseUrls.PRIVATE_COURSE_PINGJIA_URL, headers, body);
            execute(observable, observer);
        }
    }

    //保存排课计划
    public static void postSaveCourse(SaveCourseRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.postSaveCourse(SharePreferenceUtil.getHostUrl() + CourseUrls.SAVE_PRIVATE_COURSE_PLAN_URL, headers, body);
            execute(observable, observer);
        }

    }
    public static void postLockTime(SaveCourseRequestBody.PrivateCoachCAPDTOsBean body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.postLockTime(SharePreferenceUtil.getHostUrl() + CourseUrls.PRIVATE_COURSE_PLAN_LOCK_URL, headers, body);
            execute(observable, observer);
        }
    }


    //保存会籍回访结果
    public static void postHuiFangResult(AddHuiFangResultBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.postAddHuiFangResult(SharePreferenceUtil.getHostUrl() + HuiFangUrls.POST_HUI_FANG_RESULT_URL, headers, body);
            execute(observable, observer);
        }

    }



    //生成复访
    public static void postAbortFuFang(AbortFuFangBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.postAbortFuFang(SharePreferenceUtil.getHostUrl() + HuiFangUrls.POST_ABORT_FU_FANG_URL, headers, body);
            execute(observable, observer);
        }

    }


    //获取调查问卷列表
    public static void getQuestionnaireList(int pageNum, int pageSize, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            QuestionnaireRequestBody body = new QuestionnaireRequestBody(pageNum, pageSize);
            Observable<JSONObject> observable = apiService.getQuestionnaireList(SharePreferenceUtil.getHostUrl() + GET_QUESTION_NIAR_LIST_URL, headers, body);
            execute(observable, observer);
        }
    }

    //获取教练正式会员上课记录列表
    public static void getCoachVipCourseListList(String memberId, int pageNum, int pageSize, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            HashMap<String, String> params = new HashMap<>();
            params.put("memberId", memberId);
            params.put("pageNum", pageNum + "");
            params.put("pageSize", pageSize + "");
            Observable<JSONObject> observable = apiService.getHasHeaderHasParam(COACH_PRIVATE_COURSE_STOCK_BASE_INFO_URL, headers, params);
            execute(observable, observer);
        }
    }


    //添加潜在
    public static void postAddPotential(AddPotentialRequestBody addPotentialRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.postAddPotential(SharePreferenceUtil.getHostUrl() + ADD_POTENTIAL_URL, headers, addPotentialRequestBody);
            execute(observable, observer);
        }
    }

    //添加潜在
    public static void getHuiJiInviteRecord(HuiJiInviteListRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.getHuiJiInviteRecord(SharePreferenceUtil.getHostUrl() + INDEX_HUI_JI_INVITATION_RECORD_URL, headers, body);
            execute(observable, observer);
        }
    }

   
    //获取验证码
    public static void getCode(String username, String telephone, Observer<JSONObject> observer) {
        Observable<JSONObject> getCodeObservable = apiService.getCode(SharePreferenceUtil.getHostUrl() + GET_CODE_URL, username, telephone);
        execute(getCodeObservable, observer);
    }


    //重置密码
    public static void resetPassword(String username, String telephone, String verificationCode, String newPwd, String confirmPwd,Observer observer) {
        Observable<JSONObject> getCodeObservable = apiService.resetPassword(SharePreferenceUtil.getHostUrl() + RESET_PASSWORD_URL, username, telephone, verificationCode, newPwd, confirmPwd);
        execute(getCodeObservable, observer);
    }


    //会籍（客服） 获取全部会员列表
    public static void getHuiJiAllViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(SharePreferenceUtil.getHostUrl() + GET_HUIJI_ALL_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //会籍 会员信息 今日来访列表
    public static void getHuiJiTodayViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(SharePreferenceUtil.getHostUrl() + GET_HUIJI_TODAY_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //教练 获取全部会员列表
    public static void getCoachAllViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(SharePreferenceUtil.getHostUrl() + GET_COACH_ALL_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

    //教练 会员信息 今日来访列表
    public static void getCoachTodayViperList(Map<String, String> headers, Map<String, Object> params, Observer<JSONObject> observer) {
        Observable<JSONObject> observable = apiService.getDataList(SharePreferenceUtil.getHostUrl() + GET_COACH_TODAY_VIPER_LIST_URL, headers, params);
        execute(observable, observer);
    }

   

    // 首页图标
    public static void getNewIndexMenuList(Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.getIndexMenuList(SharePreferenceUtil.getHostUrl() + GET_WORK_NEW_MENU_URL, headers);
            execute(observable, observer);
        }
    }




    //私教课查询列表
    public static void getCoachPrivateCourseList(CoachPrivateCourseRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.getCoachPrivateCourseList(SharePreferenceUtil.getHostUrl() + COACH_PRIVATE_COURSE_LIST_URL, headers, body);
            execute(observable, observer);
        }
    }



    //会籍卡产品查询列表
    public static void getHuiJiCardGoodsList(CardRequestBody body, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.getHuiJiCardGoodsList(SharePreferenceUtil.getHostUrl() + HUI_JI_CARD_GOODS_LIST_URL, headers, body);
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
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");
            Observable<JSONObject> observable = apiService.editHuiJiVipDetail(SharePreferenceUtil.getHostUrl() + url, headers, editHuiJiVipBody);
            execute(observable, observer);
        }
    }


    //提交下课打卡数据
    public static void postAddAdvice(String url, AddAdviceBody addAdviceBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version","1.3");

            Observable<JSONObject> observable = apiService.postAddAdvice(SharePreferenceUtil.getHostUrl() + url, headers, addAdviceBody);
            execute(observable, observer);
        }
    }




    //业务消息
    public static void getBusinessMessage(BusinessMessageRequestBody businessMessageRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.getBusinessMessage(SharePreferenceUtil.getHostUrl() + GET_BUSINESS_MESSAGE_URL, headers, businessMessageRequestBody);
            execute(observable, observer);
        }
    }



    public static void postInvateContent(String indexHuiJiInvitationSaveUrl, SaveInviteBody saveInviteBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postInvateContent(SharePreferenceUtil.getHostUrl() + indexHuiJiInvitationSaveUrl, headers, saveInviteBody);
            execute(observable, observer);
        }
    }


    public static void postHuiFangType(HuiFangTypeRequestBody huifangTaskRequestBody, Observer observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHuiFangType(SharePreferenceUtil.getHostUrl() +     HuiFangUrls.GET_HUI_FANG_TYPE_LIST_URL, headers, huifangTaskRequestBody);
            execute(observable, observer);
        }
    }
    public static void postHuiFangTask(String getHuiJiHuiFangTaskUrl, HuifangTaskRequestBody huifangTaskRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHuiFangTask(SharePreferenceUtil.getHostUrl() + getHuiJiHuiFangTaskUrl, headers, huifangTaskRequestBody);
            execute(observable, observer);
        }
    }


    public static void postHuiFangRecord(HuifangRecordRequestBody huifangRecordRequestBody, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHuiFangRecord(SharePreferenceUtil.getHostUrl() + HuiFangUrls.HUI_FANG_RECORD_URL, headers, huifangRecordRequestBody);
            execute(observable, observer);
        }
    }

    
    public static void postAbortAppointCourseTable(String url, HashMap<String, String> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHasHeaderHasParam(SharePreferenceUtil.getHostUrl() + url, headers, param);
            execute(observable, observer);
        }
    }

    //公共
 

    // post有头无参
    public static void postHasHeaderNoParam(String url, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHasHeaderNoParam(SharePreferenceUtil.getHostUrl() + url, headers);
            execute(observable, observer);
        }
    }


    // post有头有参
    public static void postHasHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHasHeaderHasParam(SharePreferenceUtil.getHostUrl() + url, headers, param);
            execute(observable, observer);
        }
    }

    
    // post有头有参
    public static void postHasHeaderHasParamOfInteger(String url, Map<String, Integer> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.postHasHeaderHasParamOfInteger(SharePreferenceUtil.getHostUrl() + url, headers, param);
            execute(observable, observer);
        }

    }

    
    // get有头无参
    public static void getHasHeaderNoParam(String url, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.getHasHeaderNoParam(SharePreferenceUtil.getHostUrl() + url, headers);
            execute(observable, observer);
        }
    }



    // get有头有参
    public static void getHasHeaderHasParam(String url, Map<String, String> param, Observer<JSONObject> observer) {
        HashMap<String, String> headers = new HashMap<>();
        User user = DBManager.getInstance().queryUser();
        if (user == null || TextUtils.isEmpty(user.getToken())) {
            ARouter.getInstance().build("/app/login").navigation();
        } else {
            headers.put("token", user.getToken());
            headers.put("version", "1.3");
            Observable<JSONObject> observable = apiService.getHasHeaderHasParam(SharePreferenceUtil.getHostUrl() + url, headers, param);
            execute(observable, observer);
        }
    }


    //公用方法
    private static <T> void execute(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

   
}
