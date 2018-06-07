package com.yijian.staff.mvp.main.work;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.mvp.all.AllFunctionActivity;
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.coach.search.CoachSearchActivity;
import com.yijian.staff.mvp.huiji.search.HuiJiSearchActivity;
import com.yijian.staff.mvp.main.work.face.FaceActivity;
import com.yijian.staff.mvp.main.work.face2.FaceDetectorActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.MenuHelper;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.tab.adapter.MenuRecyclerGridAdapter;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class WorkFragment extends MvcBaseFragment implements AllFunctionActivity.ObserveDataChange {


    public static WorkFragment mWorkFragment = null;
    @BindView(R.id.top_view)
    LinearLayout topView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;


    private List<MenuItem> menuItemList = new ArrayList<>();

    private MenuRecyclerGridAdapter adapter;

    public static WorkFragment getInstance() {
        if (mWorkFragment == null) {
            mWorkFragment = new WorkFragment();
        }
        return mWorkFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_work;
    }


    public void initView() {

        StatusBarUtil.setTranslucentForImageView(getActivity(), 0, null);

        int statusBarHeight = CommonUtil.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, statusBarHeight, 0, 0);
        topView.setLayoutParams(params);

        adapter = new MenuRecyclerGridAdapter(menuItemList, getContext(), true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
        initData();


    }


    private void initData() {

        HttpManager.getIndexMenuList(new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {

                JSONArray menulist = JsonUtil.getJsonArray(result, "menulist");
                MenuHelper menuHelper = new MenuHelper();
                menuHelper.parseJSONArrayToMenuList(menulist);
                List<MenuItem> preferFrequentlyList = MenuHelper.getPreferFrequentlyList();
                menuItemList.clear();
                if (preferFrequentlyList != null) {
                    menuItemList.addAll(preferFrequentlyList);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });


    }


    private void initMenu() {
        List<MenuItem> preferFrequentlyList = MenuHelper.getPreferFrequentlyList();
        menuItemList.clear();
        if (preferFrequentlyList != null) {
            menuItemList.addAll(preferFrequentlyList);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateChange() {
        initMenu();
    }


    @OnClick({R.id.et_search, R.id.ll_jiedai, R.id.iv_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                // 此处为得到焦点时的处理内容
                int userRole = SharePreferenceUtil.getUserRole();
                if (userRole == 1 || userRole == 3 || userRole == 6) {
                    startActivity(new Intent(getContext(), HuiJiSearchActivity.class));
                } else if (userRole == 2 || userRole == 4 || userRole == 7) {
                    startActivity(new Intent(getContext(), CoachSearchActivity.class));
                }

                break;
            case R.id.ll_jiedai:
//                Intent intent = new Intent(getActivity(), ReceptionActivity.class);
////                startActivity(intent);
//                startActivityForResult(intent, RESULT_OK_RECEPTION);
                if (receptionActivityLisenter != null) receptionActivityLisenter.startAct();

                break;
//            case R.id.iv_all_function:
//                AllFunctionActivity.startToActivity(getActivity(), this);

//                break;
            case R.id.iv_face:
//                startActivity(new Intent(getActivity(), FaceActivity.class));
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










