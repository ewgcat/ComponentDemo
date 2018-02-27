package com.example.commonlibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commonlibrary.R;


public class NavigationBarItemFactory {

    /**
     * 枚举出可以添加的类型，这里可以添加进来，并且自己创造方法来实现
     */
    public enum NavigationItemType {
        BACK_WHITE,
        BACK_BLACK,
        TEXT,
        SCAN_IMG,
        BACK_RED,
        SEARCH_RED,
        DOWNLOAD,
        ORDER_SAVE,
        SUCCESS,
        FAIL,
        HUOMIAN
    }

    public static View createNavigationItemImageView(Context context, NavigationItemType type) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_navigation_btn, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.navigation_btn_image);
        imageView.setVisibility(View.VISIBLE);
        switch (type) {
            case BACK_WHITE:
                imageView.setImageResource(R.mipmap.white_arrow_back);
                break;
            case BACK_BLACK:
                imageView.setImageResource(R.mipmap.black_arrow_back);
                break;


        }
        return view;
    }

    public static View createNavigationItemTextview(Context context, NavigationItemType type, int textcolor, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_navigation_tv, null);
        TextView textView = (TextView) view.findViewById(R.id.navigation_btn_tv);
        textView.setVisibility(View.VISIBLE);
        textView.setTextColor(textcolor);
        switch (type) {
            case TEXT:
                textView.setText(text);
                break;
        }
        return view;
    }

    public static View.OnClickListener createBackClickListener(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        };
    }
}
