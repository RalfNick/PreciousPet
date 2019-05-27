package com.ralf.module_news.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
public class NewsRecommendAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

    public NewsRecommendAdapter(int layoutResId, @Nullable List<NewsEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        helper.setText(R.id.item_news_article_author_tv, item.getAuthor())
                .setText(R.id.item_news_article_title_tv, item.getTitle())
                .setText(R.id.item_news_read_num_tv, String.valueOf(item.getViewTimes()));
        helper.setVisible(R.id.item_news_article_top_tv, item.getTop() == 1);
        TextView selectedView = helper.getView(R.id.item_news_article_selected_tv);
        selectedView.setVisibility(item.getFeatured() == 1 ? View.VISIBLE : View.GONE);
        ImageView imageView = helper.getView(R.id.item_news_article_iv);
        ImageLoaderHelper.loadImage(mContext, imageView, item.getImg(), false);
    }
}
