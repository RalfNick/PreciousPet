package com.ralf.module_community.mvp.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ralf.module_community.R;
import com.ralf.pet_provider.util.BitmapUtil;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseAnimView
 * @email -
 * @date 2019/02/23 下午1:38
 **/
public class PraiseAnimView extends RelativeLayout {

    private Paint mPaint;
    protected PointF pointFStart, pointFEnd, pointFFirst, pointFSecond;
    private Bitmap mBitmap;

    public PraiseAnimView(Context context) {
        super(context);
        init();
    }

    public PraiseAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PraiseAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(getResources().getDrawable(R.mipmap.bg_praise_anim, null));
        initPaint();
        initPoint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.praise_anim_pic);
        int height = Double.valueOf(bitmap.getHeight() * 1.5).intValue();
        int width = Double.valueOf(bitmap.getWidth() * 1.5).intValue();
        mBitmap = BitmapUtil.zoomImg(bitmap, width, height);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private void initPoint() {
        pointFStart = new PointF();
        pointFFirst = new PointF();
        pointFSecond = new PointF();
        pointFEnd = new PointF();
    }

    private void getPoint() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        pointFStart.x = measuredWidth / 2;
        pointFStart.y = measuredHeight - mBitmap.getHeight() * 3 / 2;
        pointFEnd.y = 10;
        pointFEnd.x = measuredWidth / 3;

        pointFFirst.x = 10;
        pointFFirst.y = measuredHeight / 2;
        pointFSecond.x = measuredWidth / 3;
        pointFSecond.y = measuredHeight / 2;
    }


    private void addImageView() {
        ImageView imageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
        imageView.setImageBitmap(mBitmap);
        addView(imageView, params);
        initAnim(imageView);
    }

    private void initAnim(final ImageView view) {
        AnimatorSet animSet = new AnimatorSet();

        ValueAnimator beiAnim = ValueAnimator.ofObject(new MyTypeEvaluator(pointFFirst, pointFSecond), pointFStart, pointFEnd);
        beiAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF value = (PointF) animation.getAnimatedValue();
                view.setX(value.x - view.getWidth() / 2);
                view.setY(value.y + view.getHeight() / 2);
            }
        });

        PropertyValuesHolder pl = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f, 1f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f, 1f);
        scaleAnim = ObjectAnimator.ofPropertyValuesHolder(view, pl, p2).setDuration(500);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 3f, 0);


        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
//                MyAnimLayout.this.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                PraiseAnimView.this.removeView(view);
//                MyAnimLayout.this.setVisibility(INVISIBLE);
            }
        });
        animSet.setDuration(3000);
        animSet.play(beiAnim).with(alpha);
        animSet.start();

    }

    private ObjectAnimator scaleAnim;

    public void startAnim() {
        addImageView();
        scaleAnim.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getPoint();
    }

    class MyTypeEvaluator implements TypeEvaluator<PointF> {

        private PointF pointFFirst, pointFSecond;

        public MyTypeEvaluator(PointF start, PointF end) {
            this.pointFFirst = start;
            this.pointFSecond = end;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            PointF result = new PointF();
            float left = 1 - fraction;
            result.x = (float) (startValue.x * Math.pow(left, 3) + 3 * pointFFirst.x * Math.pow(left, 2) * fraction + 3 * pointFSecond.x * Math.pow(fraction, 2) * left + endValue.x * Math.pow(fraction, 3));
            result.y = (float) (startValue.y * Math.pow(left, 3) + 3 * pointFFirst.y * Math.pow(left, 2) * fraction + 3 * pointFSecond.y * Math.pow(fraction, 2) * left + endValue.y * Math.pow(fraction, 3));
            return result;
        }
    }

}
