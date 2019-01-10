package com.ralf.module_community.mvp.ui.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.mvp.ui.view.FeaturedHeaderView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemHeaderProvider
 * @email -
 * @date 2018/12/27 下午1:14
 **/
public class ItemHeaderProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultiItemType.TYPE_HEADER;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_header_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {

        FeaturedHeaderView headerView = helper.getView(R.id.item_header_view);
        headerView.setData(data.getDynamicBean(), helper);
    }
}
