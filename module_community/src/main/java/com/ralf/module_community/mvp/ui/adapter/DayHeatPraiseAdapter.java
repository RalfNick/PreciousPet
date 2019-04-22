package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.entity.HeatPraiseEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DayHeatPraiseAdapter
 * @email -
 * @date 2019/04/19 下午4:48
 **/
public class DayHeatPraiseAdapter extends BaseQuickAdapter<HeatPraiseEntity,BaseViewHolder> {

    public DayHeatPraiseAdapter(int layoutResId, @Nullable List<HeatPraiseEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HeatPraiseEntity item) {

    }
}
