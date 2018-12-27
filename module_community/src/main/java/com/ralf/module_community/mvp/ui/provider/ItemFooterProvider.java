package com.ralf.module_community.mvp.ui.provider;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemContentProvider
 * @email -
 * @date 2018/12/27 下午1:18
 **/
public class ItemFooterProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultiItemType.TYPE_FOOTER;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {

    }
}
