package com.yijian.staff.mvp.main.work.face;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yijian.staff.R;

import java.util.ArrayList;
import java.util.List;

import io.fotoapparat.facedetector.Rectangle;

public class MyRectanglesView extends View {

    private final List<Rect> rectangles = new ArrayList<>();
    private final Paint strokePaint = new Paint();
    private final Paint strokeLinePaint = new Paint();
    private int strokePaintWidth = 1;
    private int strokeLinePaintWidth = 1;



    public MyRectanglesView(Context context) {
        super(context);
    }

    public MyRectanglesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        applyAttributes(context, attrs);
    }

    public MyRectanglesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        applyAttributes(context, attrs);
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RectanglesView);

        try {
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setColor(
                    attributes.getColor(io.fotoapparat.facedetector.R.styleable.RectanglesView_rectanglesColor, Color.BLUE)
            );
            strokePaintWidth = attributes.getDimensionPixelSize(io.fotoapparat.facedetector.R.styleable.RectanglesView_rectanglesStrokeWidth, 1);
            strokePaint.setStrokeWidth(strokePaintWidth);
            strokeLinePaint.setStyle(Paint.Style.FILL);
            strokeLinePaint.setColor(Color.WHITE);
            strokeLinePaint.setAntiAlias(true);
            strokeLinePaintWidth = strokePaintWidth+5;
            strokeLinePaint.setStrokeWidth(strokeLinePaintWidth);
        } finally {
            attributes.recycle();
        }
    }

    /**
     * Updates rectangles which will be drawn.
     *
     * @param rectangles rectangles to draw.
     */
    public void setRectangles(@NonNull List<Rectangle> rectangles) {
        ensureMainThread();

        this.rectangles.clear();

        for (Rectangle rectangle : rectangles) {
            final int left = (int) (rectangle.getX() * getWidth());
            final int top = (int) (rectangle.getY() * getHeight());
            final int right = left + (int) (rectangle.getWidth() * getWidth());
            final int bottom = top + (int) (rectangle.getHeight() * getHeight());

            this.rectangles.add(
                    new Rect(left, top, right, bottom)
            );
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Rect rectangle : rectangles) {
            canvas.drawRect(rectangle, strokePaint);

            canvas.drawLine(rectangle.left-strokePaintWidth-2,rectangle.top-strokePaintWidth,rectangle.left+(rectangle.right-rectangle.left)/3,rectangle.top-strokePaintWidth,strokeLinePaint); //左上(横)
            canvas.drawLine(rectangle.left-strokePaintWidth,rectangle.top-strokePaintWidth-2,rectangle.left-strokePaintWidth,rectangle.top+(rectangle.bottom-rectangle.top)/3,strokeLinePaint);//左上（竖）

            canvas.drawLine(rectangle.right+strokePaintWidth+2,rectangle.top-strokePaintWidth,rectangle.right - (rectangle.right-rectangle.left)/3,rectangle.top-strokePaintWidth,strokeLinePaint); //右上（横）
            canvas.drawLine(rectangle.right+strokePaintWidth,rectangle.top-strokePaintWidth-2, rectangle.right+strokePaintWidth,rectangle.top+(rectangle.bottom-rectangle.top)/3,strokeLinePaint); //右上 （竖）

            canvas.drawLine(rectangle.left-strokePaintWidth-2,rectangle.bottom+strokePaintWidth,rectangle.left+(rectangle.right-rectangle.left)/3,rectangle.bottom+strokePaintWidth,strokeLinePaint); //左下（横）
            canvas.drawLine(rectangle.left-strokePaintWidth,rectangle.bottom+strokePaintWidth+2,rectangle.left-strokePaintWidth,rectangle.bottom-(rectangle.bottom-rectangle.top)/3,strokeLinePaint); //左下（竖）

            canvas.drawLine(rectangle.right+strokePaintWidth+2,rectangle.bottom+strokePaintWidth,rectangle.right - (rectangle.right-rectangle.left)/3,rectangle.bottom+strokePaintWidth,strokeLinePaint); //右下（横）
            canvas.drawLine(rectangle.right+strokePaintWidth,rectangle.bottom+strokePaintWidth+2,rectangle.right+strokePaintWidth,rectangle.bottom-(rectangle.bottom-rectangle.top)/3,strokeLinePaint); //右下（竖）
        }
    }

    private void ensureMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalThreadStateException("This method must be called from the main thread");
        }
    }

}
