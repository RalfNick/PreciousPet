package com.ralf.module_community.mvp.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.mvp.ui.view.FeaturedHeaderView;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedAdapter
 * @email -
 * @date 2018/12/20 下午5:53
 **/
public class FeaturedAdapter extends BaseMultiItemQuickAdapter<AdapterMultiItemEntity, BaseViewHolder> {

    public FeaturedAdapter(List<AdapterMultiItemEntity> data) {
        super(data);
        addItemType(MultiItemType.TYPE_HEAD, R.layout.item_featured_header_layout);
        addItemType(MultiItemType.TYPE_CONTENT, R.layout.item_featured_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdapterMultiItemEntity item) {

        switch (item.getItemType()) {

            case MultiItemType.TYPE_HEAD:
                FeaturedHeaderView headerView = helper.getView(R.id.item_header_view);
                headerView.setData(item.getDynamicBean());
                break;

            case MultiItemType.TYPE_CONTENT:
                TextView view = helper.getView(R.id.feather_item_title);
                view.setText(item.toString());
                break;

            default:
                break;
        }
    }
}
