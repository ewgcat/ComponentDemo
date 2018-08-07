package com.yijian.staff.mvp.main.work;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.bean.IndexDataInfo;
import com.yijian.staff.jpush.ClearRedPointUtil;
import com.yijian.staff.mvp.permission.PermissionUtils;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.PermissionUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/6/25 17:31:31
 */
public class IndexMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> menuModelList = new ArrayList<>();

    private Context context;
    private Lifecycle lifecycle;


    public IndexMenuAdapter(Lifecycle lifecycle, Context context, List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean> menuModelList) {
        this.lifecycle = lifecycle;
        this.context = context;
        this.menuModelList = menuModelList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_menu_item, parent, false);
        IndexMenuAdapter.ViewHolder viewHolder = new IndexMenuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IndexDataInfo.MenuModelListBean.SubMeneModelListBean menuModel = menuModelList.get(position);
        ((IndexMenuAdapter.ViewHolder) holder).bind(context, menuModel);
    }

    @Override
    public int getItemCount() {
        return menuModelList != null ? menuModelList.size() : 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView menu_icon;
        private TextView menu_title;
        private View view_notice;

        private LinearLayout item_grid;

        public ViewHolder(View itemView) {
            super(itemView);
            menu_title = itemView.findViewById(R.id.menu_title);
            menu_icon = itemView.findViewById(R.id.menu_icon);
            view_notice = itemView.findViewById(R.id.view_notice);
            item_grid = itemView.findViewById(R.id.item_grid);

        }

        public void bind(Context context, IndexDataInfo.MenuModelListBean.SubMeneModelListBean menuModel) {
            ImageLoader.setImageResource(SharePreferenceUtil.getImageUrl() + menuModel.getIcon(), context, menu_icon);
            menu_title.setText(menuModel.getTitle());
            item_grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuModel.getCount() > 0) {
                        menuModel.setCount(0);
                        view_notice.setVisibility(View.INVISIBLE);
                        ClearRedPointUtil.clearYueKeNotice(lifecycle);
                    }

                    String path = menuModel.getPath();
                    if (TextUtils.isEmpty(path)) {
                        Toast.makeText(context,"此版本不能提供该服务,请更新最新版本！",Toast.LENGTH_SHORT).show();
                    } else {
                        List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean.MenuActionListBean2> menuActionList = menuModel.getMenuActionList();
                        PermissionUtils.getInstance().setMenuKey(menuModel.getMenuKey());
                        ARouter.getInstance().build(path).navigation();
                    }

                }
            });

            if (menuModel.getCount() > 0) {
                view_notice.setVisibility(View.VISIBLE);
            } else {
                view_notice.setVisibility(View.INVISIBLE);
            }
        }

    }
}
