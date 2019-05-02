package com.ralf.module_community.mvp.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.AllChannelSectionEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AllChannelAdapter
 * @email -
 * @date 2019/04/27 上午11:48
 **/
public class AllChannelAdapter extends BaseSectionQuickAdapter<AllChannelSectionEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public AllChannelAdapter(int layoutResId, int sectionHeadResId, List<AllChannelSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AllChannelSectionEntity item) {
        helper.setText(R.id.head_all_channel_my_title_tv,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllChannelSectionEntity item) {
        helper.setText(R.id.item_all_channel_title_tv, item.t.getChannelTitle())
                .setText(R.id.item_header_all_channel_content_tv,
                        String.format(Constant.CHANNEL_NUMBER_STATE, item.t.getNewNumber()));
        ImageView channelImg = helper.getView(R.id.item_all_channel_img_iv);
        ImageLoaderHelper.loadImage(mContext,channelImg,item.t.getChannelUrl(),4);
    }
}
