package com.yijian.staff.share;

import com.hengte.sharelib.dialog.ShareDialog;
import com.yijian.staff.viewmodel.base.SimpleActivity;



/**
 * Created by lishuaihua on 2018/2/1.
 */

public class ShareActivity extends SimpleActivity {



    private void share(String url, String title, String text, String imgurl) {

        ShareDialog.getInstance().setArgs(url, title, text, imgurl);

        ShareDialog.getInstance().show(getSupportFragmentManager(), "share");

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }
}
