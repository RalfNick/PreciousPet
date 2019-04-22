package com.ralf.pet_provider.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ralf.pet_provider.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name StrokeTextView
 * @email -
 * @date 2019/04/19 下午1:50
 **/
public class StrokeTextView extends android.support.v7.widget.AppCompatTextView {

    private TextView mOutlineTextView;
    /**
     * 描边颜色和宽度
     */
    private float mWidth;
    private int mColor;

    public StrokeTextView(Context context) {
        this(context,null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mOutlineTextView = new TextView(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.StrokeTextView, defStyle, 0);
        mWidth = typedArray.getInteger(R.styleable.StrokeTextView_strokeTextWidth, 3);
        mColor = typedArray.getColor(R.styleable.StrokeTextView_strokeTextColor, Color.BLACK);
        init();
        typedArray.recycle();
    }

    public void init() {
        TextPaint paint = mOutlineTextView.getPaint();
        paint.setStrokeWidth(mWidth);
        paint.setStyle(Paint.Style.STROKE);
        mOutlineTextView.setTextColor(mColor);
        mOutlineTextView.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        mOutlineTextView.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置轮廓文字
        CharSequence outlineText = mOutlineTextView.getText();
        if (outlineText == null || !outlineText.equals(this.getText())) {
            mOutlineTextView.setText(getText());
            postInvalidate();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mOutlineTextView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mOutlineTextView.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mOutlineTextView.draw(canvas);
        super.onDraw(canvas);
    }
}
