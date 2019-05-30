package com.ralf.module_news.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

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
public class NewsDetailAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

    public NewsDetailAdapter(int layoutResId, @Nullable List<NewsEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        helper.setText(R.id.item_news_article_author_tv, item.getAuthor())
                .setText(R.id.item_news_article_title_tv, item.getTitle())
                .setText(R.id.item_news_read_num_tv, String.valueOf(item.getViewTimes()));
        ImageView imageView = helper.getView(R.id.item_news_article_iv);
        ImageLoaderHelper.loadImage(mContext, imageView, item.getImg(), false);
    }
}
