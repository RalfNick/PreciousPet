package com.ralf.module_mall.mvp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ralf.module_mall.R;
import com.ralf.module_mall.constant.KeyConstant;
import com.ralf.pet_provider.constant.PetConstant;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

/**
 * @author wanglixin
 */
public class SimpleTitleView extends RelativeLayout implements ITangramViewLifeCycle {

    private TextView mTitleText;
    private TextView mMoreText;

    public SimpleTitleView(Context context) {
        super(context);
        init();
    }

    public SimpleTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.mall_simple_title, this);
        this.setBackgroundColor(getResources().getColor(R.color.app_background));
        mTitleText = findViewById(R.id.shopping_simple_title_title);
        mMoreText = findViewById(R.id.mall_simple_title_more);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
    }

    /**
     * 对组件里的控件进行填充数据
     *
     * @param cell
     */
    @Override
    public void postBindView(BaseCell cell) {
        mTitleText.setText(cell.optStringParam(KeyConstant.KEY_TITLE));
        String more = cell.optStringParam(KeyConstant.KEY_MORE);
        if (PetConstant.EMPTY.equals(more)) {
            mMoreText.setVisibility(GONE);
        } else {
            mMoreText.setVisibility(VISIBLE);
            mMoreText.setText(getResources().getString(R.string.mall_more));
        }
        setOnClickListener(cell);
    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
