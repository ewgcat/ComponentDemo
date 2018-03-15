package com.yijian.staff.mvp.mine.qualification;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.constant.BundleKeyConstant;
import com.yijian.staff.mvp.seepic.SeePicActivity;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;

public class EditQualificationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.gv_qualification)
    ConflictGridView gv_qualification;
    private Dialog dialog;
    QualificationImgAdapter qualificationImgAdapter;
    private ArrayList<String> qualifacatioinList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_qualification);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    private void initData() {
        initDialog();
        qualificationImgAdapter = new QualificationImgAdapter(this, qualifacatioinList);
        gv_qualification.setOnItemClickListener(this);
        gv_qualification.setAdapter(qualificationImgAdapter);
        qualificationImgAdapter.setList(qualifacatioinList);
    }

    private void initTitle() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar.setTitle("资格证书", "#ffffff");
        navigationBar.getmRightTextView().setText("完成");
        navigationBar.hideBottomLine();
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        navigationBar.setRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(101);
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == qualifacatioinList.size()) {//点击“+”号位置添加图片
            dialog.show();
        }else{
            Intent intent = new Intent(this, SeePicActivity.class);

            intent.putExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, qualifacatioinList.get(position));
            startActivity(intent);
        }
    }

    class QualificationImgAdapter extends BaseAdapter {

        private Context mContext;
        private List<String> list = new ArrayList<String>();

        public QualificationImgAdapter() {
            super();
        }
        /**
         * 获取列表数据
         * @param list
         */
        public void setList(List<String> list){
            this.list = list;
            this.notifyDataSetChanged();
        }

        public QualificationImgAdapter(Context mContext,List<String> list) {
            super();
            this.mContext = mContext;
            this.list = list;
        }

        @Override
        public int getCount() {
            if(list==null){
                return 1;
            }else if(list.size()==6){
                return 6;
            }else{
                return list.size()+1;
            }
        }

        @Override
        public Object getItem(int position) {
            if (list != null
                    && list.size() == 6)
            {
                return list.get(position);
            }

            else if (list == null || position - 1 < 0
                    || position > list.size())
            {
                return null;
            }
            else
            {
                return list.get(position - 1);
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView==null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_qualification_image, null);
                holder = new ViewHolder();
                holder.iv = (ImageView) convertView.findViewById(R.id.item_grid_image);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.iv.getLayoutParams();
                lp.width = CustomApplication.SCREEN_WIDTH/3-20;
                lp.height = CustomApplication.SCREEN_WIDTH/3-20;
                holder.iv.setLayoutParams(lp);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            if (isShowAddItem(position))
            {
                holder.iv.setImageResource(R.mipmap.ic_launcher);
            }
            else
            {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(list.get(position)).apply(options).into(holder.iv);
            }
            return convertView;
        }

        private boolean isShowAddItem(int position)
        {
            int size = list == null ? 0 : list.size();
            return position == size;
        }

        class ViewHolder{
            ImageView iv;
        }

    }

    /*****************************  选择相册 ********************************/

    private void initDialog() {
        final View view = LayoutInflater.from(this).inflate(R.layout.view_add_pic_dialog, null);
        dialog = new Dialog(this, R.style.custom_dialog);

        dialog.setOwnerActivity(this);
        dialog.setContentView(view);
        Button cameraBtn = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button albumBtn = (Button) view.findViewById(R.id.item_popupwindows_photo);
        Button cancelBtn = (Button) view.findViewById(R.id.item_popupwindows_cancel);

        //拍照
        cameraBtn.setOnClickListener(view1 -> {
            dialog.dismiss();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    //没有权限，提示设置权限
                    RxPermissions rxPermissions = new RxPermissions(this);

                    rxPermissions
                            .request(Manifest.permission.CAMERA)
                            .subscribe(granted -> {
                                if (granted) {
                                    capturePhoto();
                                } else {
                                    Toast.makeText(this, "请到手机设置里给应用分配相机权限,否则无法使用手机拍照功能", Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    //有权限，调用相机拍照
                    capturePhoto();

                }
            } else {
                capturePhoto();
            }
        });

        //相册
        albumBtn.setOnClickListener(view2 -> {
            dialog.dismiss();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //没有权限
                    RxPermissions rxPermissions = new RxPermissions(this);

                    rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) {
                                    selectNewAlbum();
                                } else {
                                    Toast.makeText(this, "请到手机设置里给应用分配读写权限,否则应用无法正常使用", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    //有权限
                    selectNewAlbum();
                }
            } else {
                selectNewAlbum();
            }
        });

        //取消
        cancelBtn.setOnClickListener(view3 -> dialog.dismiss());

    }


    //拍照
    public void capturePhoto() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .isCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }


    //相册
    public void selectNewAlbum() {
        PhotoPicker.builder()
                .setPhotoCount(3)
                .isCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE && data != null) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            if (photos != null && photos.size() > 0) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                qualifacatioinList.addAll(photos);
                qualificationImgAdapter.setList(qualifacatioinList);
            }
        }

    }

}
