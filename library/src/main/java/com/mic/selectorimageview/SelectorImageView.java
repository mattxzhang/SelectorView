package com.mic.selectorimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;

public class SelectorImageView extends ImageView {

    private static final int DEFAULT_FILTER_COLOR = Color.GRAY;

    private int mFilterColor;
    private boolean mIsOutside;
    private boolean mIsLongClick;
    private LongPressedRunnable mLongPressedRunnable;

    public SelectorImageView(Context context) {
        this(context, null);
    }

    public SelectorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLongPressedRunnable = new LongPressedRunnable();
        initAttrs(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                setFilter();
                postDelayed(mLongPressedRunnable, ViewConfiguration.getLongPressTimeout());
                break;
            case MotionEvent.ACTION_MOVE:
                checkInBound(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                performClickEvent();
                resetStatus();
                mIsOutside = false;
                mIsLongClick = false;
                break;
        }

        return true;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectorImageView);
        mFilterColor = a.getColor(R.styleable.SelectorImageView_filterColor, DEFAULT_FILTER_COLOR);
        a.recycle();
    }

    private void setFilter() {
        Drawable drawable = getDrawable() == null ? getBackground() : getDrawable();
        if (drawable != null)
            drawable.setColorFilter(mFilterColor, PorterDuff.Mode.MULTIPLY);
    }

    private void clearFilter() {
        Drawable drawable = getDrawable() == null ? getBackground() : getDrawable();
        if (drawable != null)
            drawable.clearColorFilter();
    }

    private void checkInBound(float x, float y) {
        if (x < 0 || x > getWidth() || y < 0 || y > getHeight()) {
            mIsOutside = true;
            resetStatus();
        }
    }

    private void resetStatus() {
        clearFilter();
        removeCallbacks(mLongPressedRunnable);
    }

    private void performClickEvent() {
        if (isClickable() && !mIsOutside && !mIsLongClick)
            performClick();
    }

    private class LongPressedRunnable implements Runnable {

        @Override
        public void run() {
            if (isLongClickable()) {
                mIsLongClick = true;
                performLongClick();
                resetStatus();
            }
        }
    }
}
