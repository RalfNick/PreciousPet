package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.ChannelTypeEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelHeaderAdapter
 * @email -
 * @date 2019/04/24 上午10:28
 **/
public class ChannelHeaderAdapter extends BaseQuickAdapter<ChannelTypeEntity, BaseViewHolder> {

    public ChannelHeaderAdapter(int layoutResId, @Nullable List<ChannelTypeEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelTypeEntity item) {
        helper.setText(R.id.item_header_channel_title_tv, item.getChannelTitle())
                .setText(R.id.item_header_channel_content_tv,
                        String.format(Constant.CHANNEL_NUMBER_STATE, item.getNewNumber()));
        ImageView channelImg = helper.getView(R.id.item_header_channel_type_iv);
        ImageLoaderHelper.loadImage(mContext,channelImg,item.getChannelUrl(),4);
    }
}
