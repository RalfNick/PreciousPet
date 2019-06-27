package com.ralf.module_service.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.mvp.ui.provider.ItemCommentProvider;
import com.ralf.module_service.mvp.ui.provider.ItemExpertProvider;
import com.ralf.module_service.mvp.ui.provider.ItemHeaderProvider;
import com.ralf.module_service.mvp.ui.provider.ItemOptionProvider;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineAdapter
 * @email -
 * @date 2019/06/19 20:25
 **/
public class ExpertOnlineAdapter extends MultipleItemRvAdapter<ExpertOnlineMultiEntity, BaseViewHolder> {

    public ExpertOnlineAdapter(@Nullable List<ExpertOnlineMultiEntity> data) {
        super(data);
        finishInitialize();
    }

    @Override
    protected int getViewType(ExpertOnlineMultiEntity expertOnlineMultiEntity) {
        return expertOnlineMultiEntity.getItemType();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new ItemHeaderProvider());
        mProviderDelegate.registerProvider(new ItemOptionProvider());
        mProviderDelegate.registerProvider(new ItemExpertProvider());
        mProviderDelegate.registerProvider(new ItemCommentProvider());
    }
}
