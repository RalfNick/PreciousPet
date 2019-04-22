package com.ralf.module_community.mvp.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.HistoryPraiseSectionEntity;
import com.ralf.module_community.widget.HeatPraiseView;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DayHeatPraiseAdapter
 * @email -
 * @date 2019/04/19 下午4:48
 **/
public class HistoryPraiseSectionAdapter extends BaseSectionQuickAdapter<HistoryPraiseSectionEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public HistoryPraiseSectionAdapter(int layoutResId, int sectionHeadResId, List<HistoryPraiseSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryPraiseSectionEntity item) {
        HeatPraiseView heatPraiseView = helper.getView(R.id.item_history_heat_praise_hv);
        heatPraiseView.setData(item.t);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, HistoryPraiseSectionEntity item) {
        helper.getView(R.id.history_praise_head_more_tv)
                .setVisibility(item.isMore() ? View.VISIBLE : View.GONE);
        helper.setText(R.id.history_praise_head_tv, item.header);
        helper.addOnClickListener(R.id.history_praise_head_more_tv);
    }
}
