package com.yijian.staff.mvp.main.work;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.bean.IndexDataInfo;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.OthermodelVo;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.jpush.bean.PushInfoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.face.FaceDetectorActivity;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.vipermanage.search.HuiJiSearchActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResponseObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class WorkFragment extends MvcBaseFragment {
    private static final String TAG = WorkFragment.class.getSimpleName();

    public static WorkFragment mWorkFragment = null;
    @BindView(R.id.top_view)
    LinearLayout topView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    boolean hasNewJiedaiPush;
    boolean hasNewYueKePush;
    @BindView(R.id.iv_face)
    ImageView ivFace;
    @BindView(R.id.ll_jiedai_container)
    LinearLayout llJiedaiContainer;
    @BindView(R.id.ll_jiedai)
    LinearLayout llJiedai;
    @BindView(R.id.ll_jie_dai_container)
    LinearLayout llJieDaiContainer;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private IndexMenuAdapter indexMenuAdapter;
    private List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> menuList = new ArrayList<>();
    private boolean faceRecognition;
    private boolean reception;
    private Lifecycle lifecycle;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_work;
    }


    @Override
    public void initView() {
        lifecycle = this.getLifecycle();


        indexMenuAdapter = new IndexMenuAdapter(lifecycle, getContext(), menuList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(indexMenuAdapter);

        OthermodelVo othermodelVo = DBManager.getInstance().queryOthermodelVo();
        Logger.i(TAG, othermodelVo.toString());
        faceRecognition = othermodelVo.getFaceRecognition();
        if (faceRecognition) {
            ivFace.setVisibility(View.VISIBLE);
        } else {
            ivFace.setVisibility(View.GONE);
        }
        reception = othermodelVo.getReception();

        hasNewJiedaiPush = SharePreferenceUtil.hasNewJiedaiPush();
        hasNewYueKePush = SharePreferenceUtil.hasNewYueKePush();
        initData();


    }

    public void showJieDaiView(int i) {
        if (isAdded()) {
            if (i == 0) {//没有接待权限
                llJieDaiContainer.setBackground(getResources().getDrawable(R.mipmap.home_no_jd));
                llJiedaiContainer.setVisibility(View.GONE);
                llJiedai.setVisibility(View.GONE);
            } else if (i == 1) {//有接待权限，没有新消息
                llJieDaiContainer.setBackground(getResources().getDrawable(R.mipmap.home_no_new_jd));
                llJiedaiContainer.setVisibility(View.VISIBLE);
                llJiedai.setVisibility(View.VISIBLE);
            } else if (i == 2) {//有接待权限，有新消息
                llJieDaiContainer.setBackground(getResources().getDrawable(R.mipmap.home_new_jd));
                llJiedaiContainer.setVisibility(View.VISIBLE);
                llJiedai.setVisibility(View.VISIBLE);
            }
        }
    }


    public void observe(PushInfoBean pushInfoBean) {

        hasNewJiedaiPush = pushInfoBean.getHasNewJiedaiPush();
        hasNewYueKePush = pushInfoBean.getHasNewYueKePush();

        setRedPoint();

    }

    private void setRedPoint() {
        if (reception) {
            if (hasNewJiedaiPush) {
                showJieDaiView(2);
            } else {
                showJieDaiView(1);
            }
        } else {
            showJieDaiView(0);
        }
        if (hasNewYueKePush) {
            Logger.i(TAG, "有排课信息推送");

            for (int i = 0; i < menuList.size(); i++) {
                if (menuList.get(i).getMenuKey().equals("app_course_appoint_info")) {
                    menuList.get(i).setCount(1);
                }
            }
        }
        indexMenuAdapter.notifyDataSetChanged();

    }


    private void initData() {


        HttpManager.getNewIndexMenuList(new ResponseObserver<IndexDataInfo>(getLifecycle()) {
            @Override
            public void onSuccess(IndexDataInfo result) {
                IndexDataInfo.OthermodelVoBean othermodelVoBean = result.getOthermodelVo();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("faceRecognition", othermodelVoBean.isFaceRecognition());
                    jsonObject.put("reception", othermodelVoBean.isReception());
                    jsonObject.put("coachSchedule", othermodelVoBean.isCoachSchedule());
                    jsonObject.put("sellerSchedule", othermodelVoBean.isSellerSchedule());
                    DBManager.getInstance().insertOrReplaceOthermodelVo(new OthermodelVo(jsonObject));
                    reception = othermodelVoBean.isReception();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //TODO 首页 接待 人脸识别权限

                menuList.clear();
                List<IndexDataInfo.MenuModelListBean> menuModelList = result.getMenuModelList();
                SharePreferenceUtil.setAppSellerBuiness(false);
                SharePreferenceUtil.setAppCourseBuiness(false);
                for (int i = 0; i < menuModelList.size(); i++) {
                    IndexDataInfo.MenuModelListBean menuModelListBean = menuModelList.get(i);
                    if (menuModelListBean != null && menuModelListBean.getMenuKey().equals("app_workbench")) {
                        List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> subMeneModelList = menuModelListBean.getSubMeneModelList();
                        for (int j = 0; j < subMeneModelList.size(); j++) {
                            IndexDataInfo.MenuModelListBean.SubMeneModelListBean subMeneModelListBean = subMeneModelList.get(j);
                            menuList.add(subMeneModelListBean);
                        }
                    } else if (menuModelListBean != null && menuModelListBean.getMenuKey().equals("app_business_message")) {
                        List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> subMeneModelList = menuModelListBean.getSubMeneModelList();
                        for (int j = 0; j < subMeneModelList.size(); j++) {
                            IndexDataInfo.MenuModelListBean.SubMeneModelListBean subMeneModelListBean = subMeneModelList.get(j);
                            if (subMeneModelListBean.getMenuKey().equals("app_seller_business")) {
                                SharePreferenceUtil.setAppSellerBuiness(true);
                            } else if (subMeneModelListBean.getMenuKey().equals("app_course_business")) {
                                SharePreferenceUtil.setAppCourseBuiness(true);
                            }
                        }
                    }
                }
                setRedPoint();


            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });


    }


    @OnClick({R.id.et_search, R.id.ll_jiedai, R.id.iv_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                // 此处为得到焦点时的处理内容
                startActivity(new Intent(getContext(), HuiJiSearchActivity.class));
                break;
            case R.id.ll_jiedai:
                ClearRedPointUtil.clearJieDaiNotice(lifecycle);
                showJieDaiView(1);
                SharePreferenceUtil.setHasNewJiedaiPush(false);
                startActivity(new Intent(getActivity(), ReceptionActivity.class));
                break;
            case R.id.iv_face:
                startActivity(new Intent(getActivity(), FaceDetectorActivity.class));
                break;
        }
    }

}


