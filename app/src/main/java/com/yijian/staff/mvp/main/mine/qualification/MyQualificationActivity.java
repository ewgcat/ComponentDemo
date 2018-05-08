package com.yijian.staff.mvp.main.mine.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.main.mine.qualification.CertificateBean;
import com.yijian.staff.mvp.main.mine.qualification.EditQualificationActivity;
import com.yijian.staff.mvp.main.mine.qualification.ZiLiAdapter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.authcertificate.AuthBean;
import com.yijian.staff.net.requestbody.authcertificate.CertBean;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.selectphoto.ChoosePhotoView;
import com.yijian.staff.widget.selectphoto.ImageBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;

public class MyQualificationActivity extends MvcBaseActivity {
    @BindView(R.id.choose_photo_view)
    ChoosePhotoView choosePhotoView;
    @BindView(R.id.rcl)
    RecyclerView rcl;
    @BindView(R.id.ll_my_zili)
    LinearLayout llMyZili;
    private List<AuthBean> authList;
    private ZiLiAdapter adapter;
    private CertificateBean certificateBean;
    private  List<ImageBean> list = new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_qualification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar2.setTitle("资格证书");
//        navigationBar2.setmRightTvText("编辑");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
//        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MyQualificationActivity.this, EditQualificationActivity.class);
//                startActivityForResult(intent, 101);
//            }
//        });

        choosePhotoView.setMode(ChoosePhotoView.MODE_ONLY_SHOW);
        rcl.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ZiLiAdapter(authList);
        rcl.setAdapter(adapter);
        initData();
    }

    private void initData() {

        User user = DBManager.getInstance().queryUser();
        HashMap<String, String> param = new HashMap<>();
        param.put("coachId", user.getUserId());
        HttpManager.postHasHeaderHasParam(HttpManager.GET_CERTIFICATE_URL, param, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                certificateBean = new CertificateBean(result);
                authList = certificateBean.getAuthList();
                adapter.update(authList);
                List<CertBean> certList = certificateBean.getCertList();
                setImageList(certList);
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });


    }



     public void setImageList(List<CertBean> certList) {
        list.clear();
        for (int i = 0; i < certList.size(); i++) {
            list.add(new ImageBean(certList.get(i).getCertificate(),1));
        }
        choosePhotoView.setmPhotoPathList(list);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == 101) {
//            initData();
//        }
//
//    }
}
