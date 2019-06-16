package com.ralf.module_mall.mvp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ralf.module_mall.R;
import com.ralf.module_mall.constant.KeyConstant;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

/**
 * @author wanglixin
 */
public class SimpleGudingImage extends RelativeLayout implements ITangramViewLifeCycle {

    private ImageView mImageView;
    private TextView mTextView;

    public SimpleGudingImage(Context context) {
        super(context);
        init();
    }

    public SimpleGudingImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleGudingImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.mall_simple_guding, this);
        mImageView = findViewById(R.id.mall_simple_guding_image);
        mTextView = findViewById(R.id.mall_simple_guding_count);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
    }

    @Override
    public void postBindView(BaseCell cell) {
        ImageLoaderHelper.loadImage(getContext(), mImageView,
                cell.optStringParam(KeyConstant.KEY_IMAGE_URL), false);
        int cartCount = cell.optIntParam(KeyConstant.KEY_CAR_COUNT);
        if (cartCount == 0) {
            mTextView.setVisibility(INVISIBLE);
        } else {
            mTextView.setVisibility(VISIBLE);
            mTextView.setText(String.valueOf(cartCount));
        }

        setOnClickListener(cell);
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
