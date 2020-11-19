package com.story.creator.picker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.story.creator.picker.R;

import static com.story.creator.picker.constant.Constant.TAG;

public class PaletteView extends View {
    private int count = 0;
    private int width, height;
    private float internal;
    private Paint paint;
    private int[] colors;

    private Rect mRect = new Rect();

    public PaletteView(Context context) {
        super(context);
        initCustom();
    }

    public PaletteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        initCustom();
    }

    public PaletteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initCustom();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PaletteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
        initCustom();
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray palette = context.obtainStyledAttributes(attrs, R.styleable.palette_attr);
        count = palette.getInteger(R.styleable.palette_attr_count, 0);
        palette.recycle();
    }

    private void initCustom() {
        paint = new Paint();
        paint.setAntiAlias(false);
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setColor(int[] colors) {
        count = colors.length;
        this.colors = colors;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (width == 0 || height == 0) {
            width = getWidth();
            height = getHeight();

            Log.d(TAG, "init width: " + width + " height: " + height);
        }
        if (count >= 1) {
            internal = (float) (getWidth() / (count * 1.0));

            for (int i = 0; i < count; i++) {
                if (paint == null) {
                    paint = new Paint();
                    paint.setAntiAlias(false);
                }
                paint.setColor(colors[i]);
                paint.setAlpha(255);
                mRect.set((int) (i * internal), 0, (int) ((i + 1) * internal), height);
                //canvas.drawLine(i * internal, 0, (i + 1) * internal, height, paint);
                canvas.drawRect(mRect, paint);
            }
        }
    }
}
