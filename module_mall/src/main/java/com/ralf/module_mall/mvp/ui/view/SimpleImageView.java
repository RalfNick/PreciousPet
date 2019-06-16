package com.ralf.module_mall.mvp.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.ralf.module_mall.constant.KeyConstant;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.CellRender;
import com.tmall.wireless.tangram.util.ImageUtils;
import com.tmall.wireless.tangram.util.Utils;

/**
 * @author wanglixin
 */
public class SimpleImageView extends RatioImageView {

    public SimpleImageView(Context context) {
        super(context);
        setScaleType(ScaleType.FIT_XY);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.FIT_XY);
    }

    public SimpleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setScaleType(ScaleType.FIT_XY);
    }

    @CellRender
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);

    }

    /**
     * 对组件里的控件进行填充数据
     *
     * @param cell
     */
    @CellRender
    public void postBindView(BaseCell cell) {
        String imgUrl = cell.optStringParam(KeyConstant.KEY_IMAGE_URL);
        float ratioFromUrl = Utils.getImageRatio(imgUrl);
        setRatio(ratioFromUrl);
        if (cell.style != null) {
            if (!Float.isNaN(cell.style.aspectRatio)) {
                setRatio(cell.style.aspectRatio, RatioImageView.PRIORITY_HIGH);
            }
        }
        ImageUtils.doLoadImageUrl(this, imgUrl);
        setOnClickListener(cell);
    }

    @CellRender
    public void postUnBindView(BaseCell cell) {

    }

}
