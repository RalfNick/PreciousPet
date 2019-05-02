package com.ralf.pet_provider.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jess.arms.glide.GlideRoundTransform;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.SizeUtils;
import com.ralf.pet_provider.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ImageLoaderHelper
 * @email -
 * @date 2019/04/11 上午8:31
 **/
public final class ImageLoaderHelper {

    /**
     * 图片加载辅助类
     *
     * @param context   上下文
     * @param imageView 图片控件
     * @param url       图片地址
     * @param isCircle  是否是圆形
     */
    public static void loadImage(Context context, ImageView imageView, String url, boolean isCircle) {
        ImageLoader mImageLoader = ArmsUtils.obtainAppComponentFromContext(context).imageLoader();
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(imageView)
                .url(url)
                .isCircle(isCircle)
                .build();
        mImageLoader.loadImage(context, imageConfig);
    }

    /**
     * 图片加载辅助类，圆角
     *
     * @param context   上下文
     * @param imageView 图片控件
     * @param url       图片地址
     * @param radius    圆角
     */
    public static void loadImage(Context context, ImageView imageView, String url, int radius) {
        ImageLoader mImageLoader = ArmsUtils.obtainAppComponentFromContext(context).imageLoader();
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(imageView)
                .url(url)
                .transformation(new GlideRoundTransform(radius))
                .build();
        mImageLoader.loadImage(context, imageConfig);
    }
}
