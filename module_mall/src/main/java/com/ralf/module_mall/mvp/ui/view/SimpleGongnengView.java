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
public class SimpleGongnengView extends RelativeLayout implements ITangramViewLifeCycle {

    private ImageView mImage;
    private TextView mName;

    public SimpleGongnengView(Context context) {
        super(context);
        init();
    }

    public SimpleGongnengView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleGongnengView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.mall_simple_gongneng, this);
        mImage = findViewById(R.id.mall_simple_gongneng_image);
        mName = findViewById(R.id.mall_simple_gongneng_name);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
    }

    @Override
    public void postBindView(BaseCell cell) {
        ImageLoaderHelper.loadImage(getContext(),mImage,cell.optStringParam(KeyConstant.KEY_IMAGE_URL),false);
        mName.setText(cell.optStringParam(KeyConstant.KEY_TITLE));
        setOnClickListener(cell);
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
