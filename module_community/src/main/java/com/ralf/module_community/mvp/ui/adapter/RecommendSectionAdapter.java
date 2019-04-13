package com.ralf.module_community.mvp.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.RecommendSectionEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendSectionAdapter
 * @email -
 * @date 2019/04/13 下午1:13
 **/
public class RecommendSectionAdapter extends BaseSectionQuickAdapter<RecommendSectionEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public RecommendSectionAdapter(int layoutResId, int sectionHeadResId, List<RecommendSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, RecommendSectionEntity item) {
        helper.setText(R.id.recommend_head_title_tv, item.header);
        helper.addOnClickListener(R.id.recommend_head_more_tv);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendSectionEntity item) {
        ImageView imageView = helper.getView(R.id.recommend_content_iv);
        ImageLoaderHelper.loadImage(mContext, imageView, item.t.getImgUrl(), 6);
    }
}
