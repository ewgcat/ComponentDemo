package com.yijian.staff.mvp.main.work;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.bean.IndexDataInfo;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.OthermodelVo;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.jpush.bean.PushInfoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.coach.search.CoachSearchActivity;
import com.yijian.staff.mvp.huiji.search.HuiJiSearchActivity;
import com.yijian.staff.mvp.main.work.face2.FaceDetectorActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResponseObserver;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class WorkFragment extends MvcBaseFragment {


    public static WorkFragment mWorkFragment = null;
    @BindView(R.id.top_view)
    LinearLayout topView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private static final String TAG = WorkFragment.class.getSimpleName();
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

    private IndexMenuAdapter indexMenuAdapter;
    private List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> menuList = new ArrayList<>();
    private boolean faceRecognition;
    private boolean reception;
    private Lifecycle lifecycle;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_work;
    }


    public void initView() {
        lifecycle = this.getLifecycle();
        StatusBarUtil.setTranslucentForImageView(getActivity(), 0, null);

        int statusBarHeight = CommonUtil.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, statusBarHeight, 0, 0);
        topView.setLayoutParams(params);

        indexMenuAdapter = new IndexMenuAdapter(lifecycle,getContext(), menuList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(indexMenuAdapter);

        OthermodelVo othermodelVo = DBManager.getInstance().queryOthermodelVo();
        Logger.i(TAG,othermodelVo.toString());
        faceRecognition = othermodelVo.getFaceRecognition();
        if (faceRecognition){
            ivFace.setVisibility(View.VISIBLE);
        }else {
            ivFace.setVisibility(View.GONE);
        }
        reception = othermodelVo.getReception();

        hasNewJiedaiPush = SharePreferenceUtil.hasNewJiedaiPush();
        hasNewYueKePush = SharePreferenceUtil.hasNewYueKePush();
        initData();

    }

    public void showJieDaiView(int i) {

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


        HttpManager.getNewIndexMenuList(new ResponseObserver<IndexDataInfo>() {
            @Override
            public void onSuccess(IndexDataInfo result) {
                IndexDataInfo.OthermodelVoBean othermodelVoBean = result.getOthermodelVo();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("faceRecognition", othermodelVoBean.isFaceRecognition());
                    jsonObject.put("schedule", othermodelVoBean.isReception());
                    jsonObject.put("reception", othermodelVoBean.isSchedule());
                    DBManager.getInstance().insertOrReplaceOthermodelVo(new OthermodelVo(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //TODO 首页 接待 人脸识别权限

                menuList.clear();
                List<IndexDataInfo.MenuModelListBean> menuModelList = result.getMenuModelList();
                for (int i = 0; i < menuModelList.size(); i++) {
                    IndexDataInfo.MenuModelListBean menuModelListBean = menuModelList.get(i);
                    if (menuModelListBean != null) {
                        List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> subMeneModelList = menuModelListBean.getSubMeneModelList();
                        for (int j = 0; j < subMeneModelList.size(); j++) {
                            IndexDataInfo.MenuModelListBean.SubMeneModelListBean subMeneModelListBean = subMeneModelList.get(j);
                            menuList.add(subMeneModelListBean);
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
                //TODO 隐藏接待小红点
                if (receptionActivityLisenter != null) receptionActivityLisenter.startAct();
                break;
            case R.id.iv_face:
                startActivity(new Intent(getActivity(), FaceDetectorActivity.class));
                break;
        }
    }


    public interface ReceptionActivityLisenter {
        void startAct();
    }

    private ReceptionActivityLisenter receptionActivityLisenter;

    public void setReceptionActivityLisenter(ReceptionActivityLisenter receptionActivityLisenter) {
        this.receptionActivityLisenter = receptionActivityLisenter;
    }


}










