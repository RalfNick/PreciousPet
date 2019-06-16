package com.ralf.module_mall.mvp.ui.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.tmall.wireless.tangram.MVHelper;
import com.tmall.wireless.tangram.dataparser.concrete.Card;
import com.tmall.wireless.tangram.dataparser.concrete.Style;
import com.tmall.wireless.tangram.structure.style.ColumnStyle;

import org.json.JSONObject;

public class OnePlusNExCard extends Card {

    private static final float[] EMPTY_WEIGHTS = new float[0];

    @Override
    public void parseWith(@NonNull JSONObject data, @NonNull MVHelper resolver) {
        super.parseWith(data, resolver);
    }

    @Override
    protected void parseStyle(JSONObject data) {
        style = new ColumnStyle();
        if (data != null) {
            style.parseWith(data);
        }
    }


    @Nullable
    @Override
    public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper) {
        OnePlusNLayoutHelperEx layoutHelper;
        if (oldHelper instanceof OnePlusNLayoutHelperEx) {
            layoutHelper = (OnePlusNLayoutHelperEx) oldHelper;
        } else {
            layoutHelper = new OnePlusNLayoutHelperEx();
        }

        layoutHelper.setItemCount(mCells.size());

        if (style instanceof ColumnStyle) {
            ColumnStyle columnStyle = (ColumnStyle) style;
            if (columnStyle.cols != null && columnStyle.cols.length > 0)
                layoutHelper.setColWeights(columnStyle.cols);
            else
                layoutHelper.setColWeights(EMPTY_WEIGHTS);

            if (!Float.isNaN(style.aspectRatio)) {
                layoutHelper.setAspectRatio(style.aspectRatio);
            }

            if (columnStyle.rows != null && columnStyle.rows.length > 0) {
                layoutHelper.setRowWeight(columnStyle.rows[0]);
            }

            layoutHelper.setBgColor(columnStyle.bgColor);
            layoutHelper.setMargin(style.margin[Style.MARGIN_LEFT_INDEX], style.margin[Style.MARGIN_TOP_INDEX],
                    style.margin[Style.MARGIN_RIGHT_INDEX], style.margin[Style.MARGIN_BOTTOM_INDEX]);
            layoutHelper.setPadding(style.padding[Style.MARGIN_LEFT_INDEX], style.padding[Style.MARGIN_TOP_INDEX],
                    style.padding[Style.MARGIN_RIGHT_INDEX], style.padding[Style.MARGIN_BOTTOM_INDEX]);
        }


        return layoutHelper;
    }
}
