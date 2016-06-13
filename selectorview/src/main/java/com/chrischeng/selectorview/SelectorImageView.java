package com.chrischeng.selectorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SelectorImageView extends ImageView {

    private int mFilterColor;

    public SelectorImageView(Context context) {
        this(context, null);
    }

    public SelectorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public void setFilterColor(int color) {
        mFilterColor = color;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);

        Drawable drawable = getDrawable() == null ? getBackground() : getDrawable();
        if (drawable != null) {
            if (pressed)
                drawable.setColorFilter(mFilterColor, PorterDuff.Mode.MULTIPLY);
            else
                drawable.clearColorFilter();
        }
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SelectorImageView);
        mFilterColor = a.getColor(R.styleable.SelectorImageView_siv_filter_color, getResources().getColor(R.color.default_filter));
        a.recycle();
    }
}
