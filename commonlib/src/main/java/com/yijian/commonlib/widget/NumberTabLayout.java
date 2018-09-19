package com.yijian.commonlib.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.commonlib.R;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.ImageLoader;

/**
 * Created by GZLX on 2018/7/17.
 */

public class NumberTabLayout extends FrameLayout {
    private ImageView add;
    private LinearLayout pointsLayout;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private AddListener addListener;
    private PointListener pointListener;

    public NumberTabLayout(Context context) {
        super(context);
        init(context);
    }

    public NumberTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.number_tab_layout, null);
        add = view.findViewById(R.id.add);
        pointsLayout = view.findViewById(R.id.point);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addListener != null) {

                    addListener.addClick(v);
                }
            }
        });
        addView(view);
    }

    public void addPoint() {
        int count = pointsLayout.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                pointsLayout.getChildAt(i).startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_to_left_traslate));
            }
        }
        TextView point = (TextView) layoutInflater.inflate(R.layout.view_point, null);
        int pixels = CommonUtil.dp2px(mContext, 24);

        LayoutParams layoutParams = new LayoutParams(pixels, pixels);
        layoutParams.rightMargin = CommonUtil.dp2px(mContext, 6);

        point.setLayoutParams(layoutParams);
        point.setText((count + 1) + "");
        pointsLayout.addView(point);
        point.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointListener != null) {
                    pointListener.pointClick(v);
                }
            }
        });
        point.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in_rotate));
        point.setTag(count);

        if (pointsLayout.getChildCount() == 6) {
            changeAddView(INVISIBLE_TYPE);
        }
    }

    public void removePoint(final View v) {
        int position = (int) v.getTag();
        if (position > 0) {
            for (int i = 0; i < position; i++) {
                pointsLayout.getChildAt(i).startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_to_right_traslate));
            }
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.right_out_rotate);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().post(new Runnable() {
                    public void run() {
                        pointsLayout.removeView(v);
                        resetPointNum();
                        changeAddView(ADD_TYPE);
                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(animation);
        changeAddView(ADD_TYPE);

    }

    public void resetPointNum() {
        int count = pointsLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            TextView point = (TextView) pointsLayout.getChildAt(i);
            point.setText((i + 1) + "");
            point.setTag(i + 1);
            point.setBackground(mContext.getDrawable(R.drawable.blue_circle_background));
        }
    }

    public void setAddListener(AddListener addListener) {
        this.addListener = addListener;
    }

    public void setPointListener(PointListener pointListener) {
        this.pointListener = pointListener;
    }


    public static final int ADD_TYPE = 1;
    public static final int CHE_XIAO_TYPE = 2;
    public static final int INVISIBLE_TYPE = 3;

    private int addType=1;

    public int getAddType() {
        return addType;
    }

    public void changeAddView(int i) {
        addType=i;
        switch (i) {
            case ADD_TYPE:

                if (pointsLayout.getChildCount() == 6) {
                    add.setVisibility(INVISIBLE);
                }else {
                    add.setVisibility(VISIBLE);
                    ImageLoader.setImageResource(R.mipmap.account_gray, mContext, add);
                }

                break;
            case CHE_XIAO_TYPE:
                add.setVisibility(VISIBLE);
                ImageLoader.setImageResource(R.mipmap.chexiao, mContext, add);
                break;
            case INVISIBLE_TYPE:
                add.setVisibility(INVISIBLE);
                break;
        }
    }

    public interface AddListener {
        void addClick(View view);
    }

    public interface PointListener {
        void pointClick(View view);
    }
}
