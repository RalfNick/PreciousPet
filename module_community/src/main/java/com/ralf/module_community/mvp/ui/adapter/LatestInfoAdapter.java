package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.LatestInfoEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoAdapter
 * @email -
 * @date 2019/04/13 下午4:45
 **/
public class LatestInfoAdapter extends BaseQuickAdapter<LatestInfoEntity,BaseViewHolder> {

    public LatestInfoAdapter(int layoutResId, @Nullable List<LatestInfoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LatestInfoEntity item) {
        helper.getView(R.id.item_latest_image_play_iv)
                .setVisibility(item.getType() == 1 ? View.VISIBLE : View.GONE);
        ImageView imageView = helper.getView(R.id.item_latest_image_iv);
        ImageLoaderHelper.loadImage(mContext,imageView,item.getDynamicsPath(),6);
    }
}
