package com.ralf.module_news.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.utils.device_util.ScreenUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.ralf.pet_provider.widget.player.SimpleCoverVideo;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsRecommendAdapter
 * @email -
 * @date 2019/05/16 17:34
 **/
public class NewsVideoAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

    private static final String TAG = NewsVideoAdapter.class.getSimpleName();

    public NewsVideoAdapter(int layoutResId, @Nullable List<NewsEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        SimpleCoverVideo video = helper.getView(R.id.item_news_detail_video);
        helper.setVisible(R.id.item_news_video_watermark_iv,true);
        helper.setText(R.id.item_news_detail_tv,item.getTitle())
                .setText(R.id.item_news_author_tv,item.getAuthor())
                .setText(R.id.item_news_preview_num_tv,String.valueOf(item.getViewTimes()));
        GSYVideoOptionBuilder videoOptionBuilder = new GSYVideoOptionBuilder();
        ImageView imageView = new ImageView(mContext);
        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoaderHelper.loadImage(mContext, imageView,item.getImg(),false);
        videoOptionBuilder.setIsTouchWiget(false)
                .setUrl(item.getVideoUrl())
                .setThumbImageView(imageView)
                .setCacheWithPlay(false)
                .setLockLand(true)
                .setNeedShowWifiTip(true)
                .setLooping(false)
                .setPlayTag(TAG)
                .build(video);
        // 隐藏 title
        video.getTitleTextView().setVisibility(View.GONE);
        // 设置返回键
        video.getBackButton().setVisibility(View.GONE);
        // 设置全屏按键功能
        video.getFullscreenButton().setVisibility(View.GONE);
    }
}
