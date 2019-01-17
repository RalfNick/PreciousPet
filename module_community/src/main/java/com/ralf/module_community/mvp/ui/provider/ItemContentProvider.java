package com.ralf.module_community.mvp.ui.provider;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.device_util.ScreenUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.rockerhieu.emojicon.EmojiconTextView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemContentProvider
 * @email -
 * @date 2018/12/27 下午1:18
 **/
public class ItemContentProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultiItemType.TYPE_CONTENT;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_content_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        ImageView imageView = helper.getView(R.id.item_content_iv);
        EmojiconTextView textView = helper.getView(R.id.item_content_comment_tv);
//        ImageView waterMarkIv = helper.getView(R.id.video_watermark_image);

        DynamicEntity dynamicBean = data.getDynamicBean();
        setStateContent(textView, dynamicBean.getTalk());
        int width = dynamicBean.getWidth();
        int height = dynamicBean.getHigh();
        String imagePath = dynamicBean.getDynamicsPath();

        // 类型：1-视频  0-图片
        int type = dynamicBean.getType();
        if (type == 1) {
//            waterMarkIv.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            setImageContent(imageView, width, height, imagePath);
//            waterMarkIv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置图片
     *
     * @param imageView ImageView
     * @param width     宽
     * @param height    高
     * @param imagePath 图片路径
     */
    private void setImageContent(ImageView imageView, int width, int height, String imagePath) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.transToHeight(width, height));
        imageView.setLayoutParams(params);
        ImageLoader imageLoader = ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader();
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(imageView)
                .url(imagePath)
                .isCircle(false)
                .build();
        imageLoader.loadImage(mContext, imageConfig);
    }

    /**
     * 设置状态内容
     *
     * @param textView tv
     * @param content  内容
     */
    private void setStateContent(EmojiconTextView textView, String content) {
        if (StringUtils.isEmpty(content)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(content);
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        super.onClick(helper, data, position);
    }


}
