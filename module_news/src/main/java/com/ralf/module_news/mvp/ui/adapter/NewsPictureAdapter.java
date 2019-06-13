package com.ralf.module_news.mvp.ui.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_news.R;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsRecommendAdapter
 * @email -
 * @date 2019/05/16 17:34
 **/
public class NewsPictureAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

    private static final String TAG = NewsPictureAdapter.class.getSimpleName();

    public NewsPictureAdapter(int layoutResId, @Nullable List<NewsEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        helper.setText(R.id.item_news_image_title_tv, item.getTitle());
        helper.setText(R.id.item_news_image_author_tv, item.getAuthor());
        helper.setText(R.id.item_news_image_number_tv, String.valueOf(item.getViewTimes()));
        List<NewsEntity.PicListBean> picList = item.getPicList();
        setImageUrl(helper, R.id.item_news_image_first_iv, picList.get(0).getPicUrl());
        setImageUrl(helper, R.id.item_news_image_second_iv, picList.get(1).getPicUrl());
        setImageUrl(helper, R.id.item_news_image_third_iv, picList.get(2).getPicUrl());
    }

    private void setImageUrl(BaseViewHolder helper, @IdRes int imageId, String picUrl) {
        ImageLoaderHelper.loadImage(mContext,helper.getView(imageId),picUrl,false);
    }
}
