package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.mvp.ui.provider.ItemContentProvider;
import com.ralf.module_community.mvp.ui.provider.ItemFooterProvider;
import com.ralf.module_community.mvp.ui.provider.ItemHeaderProvider;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedAdapter
 * @email -
 * @date 2018/12/20 下午5:53
 **/
public class FeaturedAdapter extends MultipleItemRvAdapter<AdapterMultiItemEntity, BaseViewHolder> {

    public FeaturedAdapter(@Nullable List<AdapterMultiItemEntity> data) {
        super(data);
        finishInitialize();
    }

    @Override
    protected int getViewType(AdapterMultiItemEntity entity) {
        return entity.getItemType();
    }

    @Override
    public void registerItemProvider() {

        mProviderDelegate.registerProvider(new ItemHeaderProvider());
        mProviderDelegate.registerProvider(new ItemContentProvider());
        mProviderDelegate.registerProvider(new ItemFooterProvider());
    }
}
