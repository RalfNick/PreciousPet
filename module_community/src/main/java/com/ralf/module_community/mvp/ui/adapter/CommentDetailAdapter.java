package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.mvp.ui.provider.ItemContentProvider;
import com.ralf.module_community.mvp.ui.provider.ItemDetailCommentProvider;
import com.ralf.module_community.mvp.ui.provider.ItemHeaderProvider;
import com.ralf.module_community.mvp.ui.provider.ItemPraiseProvider;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentDetailAdapter
 * @email -
 * @date 2019/03/01 下午7:49
 **/
public class CommentDetailAdapter extends MultipleItemRvAdapter<AdapterMultiItemEntity, BaseViewHolder> {

    public CommentDetailAdapter(@Nullable List<AdapterMultiItemEntity> data) {
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
        mProviderDelegate.registerProvider(new ItemPraiseProvider());
        mProviderDelegate.registerProvider(new ItemDetailCommentProvider());
    }
}
