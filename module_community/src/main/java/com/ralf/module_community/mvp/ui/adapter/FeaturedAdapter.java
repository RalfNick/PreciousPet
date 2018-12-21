package com.ralf.module_community.mvp.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.FeaturedEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedAdapter
 * @email -
 * @date 2018/12/20 下午5:53
 **/
public class FeaturedAdapter extends BaseQuickAdapter<FeaturedEntity, BaseViewHolder> {

    public FeaturedAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeaturedEntity item) {

        TextView view = helper.getView(R.id.feather_item_title);
        view.setText(item.toString());
    }
}
