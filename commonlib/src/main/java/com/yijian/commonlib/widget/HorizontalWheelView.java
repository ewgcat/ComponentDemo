package com.yijian.commonlib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yijian.commonlib.R;
import com.yijian.commonlib.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;


public class HorizontalWheelView extends View {

    private Context context;
    private List<String> strings = new ArrayList<String>();//数据源字符串数组

    private int seeSize = 5;//可见个数

    private int itemWidth;//每个字母所占的大小；
    private TextPaint textPaint;
    private boolean firstVisible = true;
    private int width;//控件宽度
    private int height;//控件高度
    private Paint selectedPaint;//被选中文字的画笔
    private int n;
    private float downX;
    private float anOffset;
    private int selectedTextSize;
    private int selectedColor;
    private int textSize;
    private int textColor;
    private Rect rect = new Rect();
    ;

    private int textWidth = 0;
    private int textHeight = 0;
    private int centerTextHeight = 0;


    public HorizontalWheelView(Context context) {
        this(context, null);
    }

    public HorizontalWheelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalWheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setWillNotDraw(false);
        setClickable(true);
        initAttrs(attrs);//初始化属性
        initPaint();//初始化画笔
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

        
        selectedPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setColor(selectedColor);
        selectedPaint.setTextSize(selectedTextSize);
    }


    /**
     * 初始化属性
     * @param attrs
     */
    @SuppressLint("RestrictedApi")
    private void initAttrs(AttributeSet attrs) {
        TintTypedArray tta = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.HorizontalWheelView);
        //两种字体颜色和字体大小
        seeSize = tta.getInteger(R.styleable.HorizontalWheelView_HorizontalWheelViewSeesize, 5);
        selectedTextSize = CommonUtil.dp2px(context, tta.getFloat(R.styleable.HorizontalWheelView_HorizontalWheelViewSelectedTextSize, 50));
        selectedColor = tta.getColor(R.styleable.HorizontalWheelView_HorizontalWheelViewSelectedTextColor, context.getResources().getColor(android.R.color.black));
        textSize =CommonUtil.dp2px(context, tta.getFloat(R.styleable.HorizontalWheelView_HorizontalWheelViewTextSize, 40));
        textColor = tta.getColor(R.styleable.HorizontalWheelView_HorizontalWheelViewTextColor, context.getResources().getColor(android.R.color.darker_gray));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("action", "onTouchEvent: " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();//获得点下去的x坐标
                break;
            case MotionEvent.ACTION_MOVE://复杂的是移动时的判断
                float scrollX = event.getX();

                if (n != 0 && n != strings.size() - 1)
                    anOffset = scrollX - downX;//滑动时的偏移量，用于计算每个是数据源文字的坐标值
                else {
                    anOffset = (float) ((scrollX - downX) / 1.5);//当滑到两端的时候添加一点阻力
                }

                if (scrollX > downX) {
                    //向右滑动，当滑动距离大于每个单元的长度时，则改变被选中的文字。
                    if (scrollX - downX >= itemWidth) {
                        if (n > 0) {
                            anOffset = 0;
                            n = n - 1;
                            downX = scrollX;
                        }
                    }
                } else {

                    //向左滑动，当滑动距离大于每个单元的长度时，则改变被选中的文字。
                    if (downX - scrollX >= itemWidth) {

                        if (n < strings.size() - 1) {
                            anOffset = 0;
                            n = n + 1;
                            downX = scrollX;
                        }
                    }
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                //抬起手指时，偏移量归零，相当于回弹。
                anOffset = 0;
                invalidate();

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (firstVisible) {//第一次绘制的时候得到控件 宽高；
            width = getWidth();
            height = getHeight();
            itemWidth = width / seeSize;
            firstVisible = false;
        }
        if (n >= 0 && n <= strings.size() - 1) {//加个保护；防止越界

            String s = strings.get(n);//得到被选中的文字
            if (onSelectListener!=null){
                onSelectListener.onSelected(s,n);
            }
            /**
             * 得到被选中文字 绘制时所需要的宽高
             */
            selectedPaint.getTextBounds(s, 0, s.length(), rect);
            //3从矩形区域中读出文本内容的宽高
            int centerTextWidth = rect.width();
            centerTextHeight = rect.height();
            canvas.drawText(strings.get(n), getWidth() / 2 - centerTextWidth / 2 + anOffset, getHeight() / 2 + centerTextHeight / 2, selectedPaint);//绘制被选中文字，注意点是y坐标

            for (int i = 0; i < strings.size(); i++) {//遍历strings，把每个地方都绘制出来，
                if (n > 0 && n < strings.size() - 1) {//这里主要是因为strings数据源的文字长度不一样，为了让被选中两边文字距离中心宽度一样，我们取得左右两个文字长度的平均值
                    textPaint.getTextBounds(strings.get(n - 1), 0, strings.get(n - 1).length(), rect);
                    int width1 = rect.width();
                    textPaint.getTextBounds(strings.get(n + 1), 0, strings.get(n + 1).length(), rect);
                    int width2 = rect.width();
                    textWidth = (width1 + width2) / 2;
                }
                if (i == 0) {//得到高，高度是一样的，所以无所谓
                    textPaint.getTextBounds(strings.get(0), 0, strings.get(0).length(), rect);
                    textHeight = rect.height();
                }

                if (i != n)
                    canvas.drawText(strings.get(i), (i - n) * itemWidth + getWidth() / 2 - textWidth / 2 + anOffset, getHeight() / 2 + textHeight / 2, textPaint);//画出每组文字

            }



        }

    }

    /**
     * 改变中间可见文字的数目
     *
     * @param seeSizes 可见数
     */
    public void setSeeSize(int seeSizes) {
        if (seeSize > 0) {
            seeSize = seeSizes;
            invalidate();
        }

    }


    /**
     * 向左移动一个单元
     */
    public void setAnLeftOffset() {
        if (n < strings.size() - 1) {
            n = n + 1;
            invalidate();
        }

    }

    /**
     * 向右移动一个单元
     */
    public void setAnRightOffset() {
        if (n > 0) {
            n = n - 1;
            invalidate();
        }
    }

    /**
     * 设置个数据源
     *
     * @param strings 数据源String集合
     */
    public void setData(List<String> strings) {
        this.strings = strings;

        invalidate();
    }

    /**
     * 获得被选中的文本
     *
     * @return 被选中的文本
     */
    public String getSelectedString() {
        if (strings.size() != 0) {
            return strings.get(n);
        }
        return null;
    }


    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public interface OnSelectListener{
        void onSelected(String text, int index);
    }


    public void setSelectedPosition(int p){
        n =p;
        invalidate();
    }
}

