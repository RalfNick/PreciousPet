package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.mvp.ui.provider.ItemContentProvider;
import com.ralf.module_community.mvp.ui.provider.ItemFooterProvider;
import com.ralf.module_community.mvp.ui.provider.ItemHeaderProvider;
import com.ralf.module_community.mvp.ui.view.FeaturedHeaderView;

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

//    public FeaturedAdapter(List<AdapterMultiItemEntity> data) {
//        super(data);
//        addItemType(MultiItemType.TYPE_HEAD, R.layout.item_featured_header_layout);
//        addItemType(MultiItemType.TYPE_CONTENT, R.layout.item_featured_layout);
//    }

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

//    @Override
//    protected void convert(BaseViewHolder helper, AdapterMultiItemEntity item) {
//
//        switch (item.getItemType()) {
//
//            case MultiItemType.TYPE_HEAD:
//                FeaturedHeaderView headerView = helper.getView(R.id.item_header_view);
//                headerView.setData(item.getDynamicBean(),helper);
//                break;
//
//            case MultiItemType.TYPE_CONTENT:
//                TextView view = helper.getView(R.id.feather_item_title);
//                view.setText(item.toString());
//                break;
//
//            default:
//                break;
//        }
//    }
}
