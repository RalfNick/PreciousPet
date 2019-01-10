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
public class ItemContentProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultiItemType.TYPE_CONTENT;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_content_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        TextView view = helper.getView(R.id.feather_item_title);
        view.setText(data.getDynamicBean().getNickName());
    }

    @Override
    public void onClick(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        super.onClick(helper, data, position);
    }
}
