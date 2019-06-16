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
public class SimpleContentView extends RelativeLayout implements ITangramViewLifeCycle {

    private ImageView mImage;
    private TextView mTitle;
    private TextView mNowPrice;
    private TextView mOldPrice;

    public SimpleContentView(Context context) {
        super(context);
        init();
    }

    public SimpleContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.mall_simple_content, this);
        mImage = findViewById(R.id.shopping_simple_content_image);
        mTitle = findViewById(R.id.shopping_simple_content_title);
        mNowPrice = findViewById(R.id.shopping_simple_content_nowPrice);
        mOldPrice = findViewById(R.id.shopping_simple_content_oldPrice);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
    }

    @Override
    public void postBindView(BaseCell cell) {
        ImageLoaderHelper.loadImage(getContext(),mImage,cell.optStringParam(KeyConstant.KEY_IMAGE_URL),false);
        mTitle.setText(cell.optStringParam(KeyConstant.KEY_TITLE));
        mNowPrice.setText(cell.optStringParam(KeyConstant.KEY_PRICE_NOW));
        mOldPrice.setText(cell.optStringParam(KeyConstant.KEY_PRICE_OLD));
        setOnClickListener(cell);
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
