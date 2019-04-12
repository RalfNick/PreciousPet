package com.ralf.module_community.mvp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.ralf.module_community.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Ralf(wanglixin)
 * 评论点赞的人的头像自定义 View
 * @name HeadPortraitListView
 * @email -
 * @date 2019/01/01 下午10:14
 **/
public class HeadPortraitListView extends LinearLayout {

    /**
     * 默认的间隔和分割线宽度,最大数目，默认的单行数量
     */
    public static final int DEFAULT_SPACING = 8;
    public static final int DEFAULT_MAX_NUM = 10;
    public static final int DEFAULT_SINGLE_MAX_NUM = 10;
    public static final int DEFAULT_PIC_SIZE = 20;

    /**
     * 方向，垂直和水平方向的间隔,是否多行
     */
    private int mVerticalSpace;
    private int mHorizontalSpace;
    private int mGravity;
    private boolean isMultiLine;
    private int mSingleLineNum;
    private int mMaxNum;
    private int mPicSize;
    private boolean isCircle;

    /**
     * 计算的宽度和高度
     */
    private int mWidth, mHeight;
    /**
     * ImageView 总数
     */
    private int mTotalNum;
    /**
     * 行数
     */
    private int mLineNum;

    /**
     * 控件集合
     */
    private List<ImageView> mImageViews = new ArrayList<>();

    private OnHeadPortraitClickListener mClickListener;

    private LinearLayout.LayoutParams mLayoutParams;

    private ImageLoader mImageLoader;

    public HeadPortraitListView(Context context) {
        this(context, null);
    }

    public HeadPortraitListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadPortraitListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    /**
     * 初始化视图
     *
     * @param context      context
     * @param attrs        属性
     * @param defStyleAttr style
     */
    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.HeadPortraitListView, defStyleAttr, 0);
        mHorizontalSpace = (int) typedArray.getDimension(R.styleable.HeadPortraitListView_horizontalSpace, DEFAULT_SPACING);
        mVerticalSpace = (int) typedArray.getDimension(R.styleable.HeadPortraitListView_verticalSpace, DEFAULT_SPACING);
        isMultiLine = typedArray.getBoolean(R.styleable.HeadPortraitListView_multiLine, false);
        mMaxNum = typedArray.getInt(R.styleable.HeadPortraitListView_maxNum, DEFAULT_MAX_NUM);
        mSingleLineNum = typedArray.getInt(R.styleable.HeadPortraitListView_lineNum, DEFAULT_SINGLE_MAX_NUM);
        mPicSize = (int) typedArray.getDimension(R.styleable.HeadPortraitListView_picSize, DEFAULT_PIC_SIZE);
        isCircle = typedArray.getBoolean(R.styleable.HeadPortraitListView_circle, true);
        mLayoutParams = new LinearLayout.LayoutParams(mPicSize, mPicSize);
        typedArray.recycle();

        mImageLoader = ArmsUtils.obtainAppComponentFromContext(context).imageLoader();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 宽度测量
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 内部 Padding 的尺寸
        int widthUsed = getPaddingLeft() + getPaddingRight();
        int heightUsed = getPaddingTop() + getPaddingBottom();

        // 计算宽需要的 ImageView 的数目
        int calPicNum;
        if (isMultiLine) {
            // 最后一行
            int lastLineNum = mTotalNum % mSingleLineNum;
            mLineNum = lastLineNum == 0 ? mTotalNum / mSingleLineNum : mTotalNum / mSingleLineNum + 1;
            calPicNum = mLineNum > 1 ? mSingleLineNum : lastLineNum;
        } else {
            calPicNum = mTotalNum > mSingleLineNum ? mSingleLineNum : mTotalNum;
            mLineNum = 1;
        }

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT
                && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mWidth = calPicNum * mPicSize + (calPicNum - 1) * mHorizontalSpace;
            mHeight = mLineNum * mPicSize + (mLineNum - 1) * mVerticalSpace;
            setMeasuredDimension(mWidth + widthUsed, mHeight + heightUsed);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mWidth = calPicNum * mPicSize + (calPicNum - 1) * mHorizontalSpace;
            setMeasuredDimension(mWidth, measureHeight);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mHeight = mLineNum * mPicSize + (mLineNum - 1) * mVerticalSpace;
            setMeasuredDimension(measureWidth, mHeight + heightUsed);
        } else {
            setMeasuredDimension(measureWidth, measureHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int top = getPaddingTop();
        if (isMultiLine) {
            for (int i = 0; i < mLineNum; i++) {
                top = i * (mPicSize + mVerticalSpace) + top;
                if (i == mLineNum - 1 && mTotalNum % mSingleLineNum != 0) {
                    layoutSingleLine(top, mTotalNum % mSingleLineNum, i);
                } else {
                    layoutSingleLine(top, mSingleLineNum, i);
                }
            }
        } else {
            layoutSingleLine(top, mTotalNum, 0);
        }
    }

    /**
     * 计算单行情况下布局
     *
     * @param top     top坐标
     * @param lineNum 第几行（从0开始，单行是为0）
     */
    private void layoutSingleLine(int top, int length, int lineNum) {
        int left;
        if (mGravity == Gravity.END) {
            left = mWidth - getPaddingEnd() - mPicSize;
            for (int i = 0; i < length; i++) {
                ImageView child = mImageViews.get(lineNum * mSingleLineNum + i);
                child.layout(left, top, left + mPicSize, top + mPicSize);
                left = left - mPicSize - mHorizontalSpace;
            }
        } else {
            left = getPaddingLeft();
            for (int i = 0; i < length; i++) {
                ImageView child = mImageViews.get(lineNum * mSingleLineNum + i);
                child.layout(left, top, mPicSize + left, top + mPicSize);
                left = left + mPicSize + mHorizontalSpace;
            }
        }
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
        // 只管左右方向 Start or End
        switch (gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case Gravity.START:
                break;
            case Gravity.END:
                mGravity = Gravity.END;
                break;
            default:
                mGravity = Gravity.START;
                break;
        }
    }

    public void setHeadPortraitData(Map<Integer, String> userInfoMap) {
        if (calculateTotalNum(userInfoMap)) {
            mImageViews.clear();
            removeAllViews();
            return;
        }
        loadImageViews(userInfoMap);
    }

    private void loadImageViews(Map<Integer, String> userInfoMap) {
        mImageViews.clear();
        removeAllViews();
        int count = 0;
        for (Integer userId : userInfoMap.keySet()) {
            if (count == mTotalNum) {
                break;
            }
            addImageViewToParent(userInfoMap.get(userId), userId);
            count++;
        }
    }

    private boolean calculateTotalNum(Map<Integer, String> userInfoMap) {
        if (userInfoMap == null || userInfoMap.isEmpty()) {
            mTotalNum = 0;
            return true;
        }
        if (isMultiLine) {
            mTotalNum = userInfoMap.size() > mMaxNum ? mMaxNum : userInfoMap.size();
        } else {
            mTotalNum = userInfoMap.size() > mSingleLineNum ? mSingleLineNum : userInfoMap.size();
        }
        return false;
    }

    /**
     * 是否有更多数据
     *
     * @param userInfoMap 数据
     * @param resId       更多图标
     */
    public void setHeadPortraitData(Map<Integer, String> userInfoMap, int resId) {
        if (userInfoMap == null || userInfoMap.isEmpty()) {
            return;
        }
        if (calculateTotalNum(userInfoMap)) {
            mImageViews.clear();
            removeAllViews();
            return;
        }
        mTotalNum += 1;
        loadImageViews(userInfoMap);
        ImageView view = new ImageView(getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(mLayoutParams);
        // 加载图片
        view.setImageResource(resId);
        mImageViews.add(mImageViews.size(), view);
        Collections.reverse(mImageViews);
        this.addView(view);
        view.setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.onMoreClick();
            }
        });

    }

    /**
     * @param url    图片 url
     * @param userId 用户 id
     */
    private void addImageViewToParent(String url, int userId) {
        ImageView view = new ImageView(getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(mLayoutParams);
        // 加载图片
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(view)
                .url(url)
                .isCircle(isCircle)
                .build();
        mImageLoader.loadImage(getContext(), imageConfig);
        mImageViews.add(view);
        this.addView(view);
        view.setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.onClick(userId);
            }
        });

    }

    public void setVerticalSpace(int verticalSpace) {
        mVerticalSpace = verticalSpace;
        requestLayout();
    }

    public void setHorizontalSpace(int horizontalSpace) {
        mHorizontalSpace = horizontalSpace;
        requestLayout();
    }

    public void setClickListener(OnHeadPortraitClickListener clickListener) {
        mClickListener = clickListener;
    }

    /**
     * 头像点击事件回调接口
     */
    public interface OnHeadPortraitClickListener {

        /**
         * 点赞人员头像点击事件
         *
         * @param id id
         */
        void onClick(int id);

        /**
         * 点击更多
         */
        void onMoreClick();
    }

}
