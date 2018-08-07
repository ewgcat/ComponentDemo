package com.yijian.staff.mvp.workspace.umeng;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yijian.staff.R;
import com.yijian.staff.util.DensityUtil;
import java.util.ArrayList;
import java.util.List;


public class SharePopupWindow {
    private PopupWindow window;
    private RecyclerView recyclerView;
    private TextView cancel_tv;
    private SharePopAdapter sharePopAdapter;
    private Activity activity;
    private String url, title, thumbnaiUrl, describe;
    private boolean isPopShow = false;//下载图片会回调多次，加这个tag来避免

    public SharePopupWindow(Activity activity) {
        this.activity = activity;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.view_share_popuwindow, null);
        recyclerView = contentView.findViewById(R.id.rv);
        cancel_tv = contentView.findViewById(R.id.cancel_tv);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        recyclerView.setAdapter(sharePopAdapter = new SharePopAdapter());
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setAnimationStyle(R.style.bottom_show);
        window.setBackgroundDrawable(null);
        window.setElevation(DensityUtil.dip2px(activity, 8));
        addItemData();
        initListener();
    }

    public void setData(String url, String title, String thumbnaiUrl, String describe) {
        this.url = url;
        this.title = title;
        this.thumbnaiUrl = thumbnaiUrl;
        this.describe = describe;
    }

    private void addItemData() {
        List<ShareBean> list = new ArrayList<>();
        list.add(new ShareBean(ShareBean.TYPE_WEIXIN, R.mipmap.weixin, "微信"));
        list.add(new ShareBean(ShareBean.TYPE_WEIXIN_CIRCLE, R.mipmap.pengyouquan, "朋友圈"));
        list.add(new ShareBean(ShareBean.TYPE_SINA, R.mipmap.weibo, "新浪微博"));
        list.add(new ShareBean(ShareBean.TYPE_BROWER, R.mipmap.chrome, "浏览器"));
        sharePopAdapter.replaceAllData(list);
    }

    private void initListener() {
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disMiss();
            }
        });
        sharePopAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, Object o, int position) {
                ShareBean shareBean = (ShareBean) o;
                switch (shareBean.getType()) {
                    case ShareBean.TYPE_BROWER:
                        openUrlWithBrower();
                        break;
                    case ShareBean.TYPE_SINA:
                        loadShareImg(SHARE_MEDIA.SINA);
                        break;
                    case ShareBean.TYPE_WEIXIN:
                        loadShareImg(SHARE_MEDIA.WEIXIN);
                        break;
                    case ShareBean.TYPE_WEIXIN_CIRCLE:
                        loadShareImg(SHARE_MEDIA.WEIXIN_CIRCLE);
                        break;
                }
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpha(1);
            }
        });
    }

    public void show(View paramView) {
        window.showAtLocation(paramView, Gravity.BOTTOM, 0, 0);
        setWindowAlpha(0.2f);
    }

    private void openUrlWithBrower() {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

    private void loadShareImg(SHARE_MEDIA media) {
        isPopShow = false;
        Glide.with(activity).asBitmap().load(thumbnaiUrl)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        share(null, media);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        share(resource, media);
                        return true;
                    }
                }).preload();
    }


    private void share(Bitmap resource, SHARE_MEDIA media) {
        if (isPopShow) return;
        UMWeb web = new UMWeb(url);
        web.setTitle(title);
        web.setDescription(describe);
        if (resource != null) {
            web.setThumb(new UMImage(activity, resource));  //缩略图
        }
        new ShareAction(activity)
                .setPlatform(media)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(activity, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).share();
        isPopShow = true;
    }

    public void disMiss() {
        window.dismiss();
    }

    private void setWindowAlpha(float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }


    class SharePopAdapter extends BaseRvAdapter<ShareBean> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ShareViewHolder(getRootView(parent, R.layout.item_share));
        }

        class ShareViewHolder extends BaseRvViewHolder<ShareBean> {
            private TextView tv;

            public ShareViewHolder(View view) {
                super(view);
                tv = findView(R.id.tv);
            }

            @Override
            protected void bindData(ShareBean shareBean, int position) {
                tv.setCompoundDrawablesWithIntrinsicBounds(0, shareBean.getResID(), 0, 0);
                tv.setText(shareBean.getName());
            }
        }
    }
}
