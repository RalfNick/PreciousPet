package com.ralf.module_community.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jess.arms.utils.StringUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.HistoryPraiseEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.ralf.pet_provider.widget.StrokeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HeatPraiseView
 * @email -
 * @date 2019/04/19 下午3:57
 **/
public class HeatPraiseView extends FrameLayout {

    @BindView(R2.id.heat_praise_round_ri)
    RoundedImageView mRoundRi;
    @BindView(R2.id.praise_num_stroke_tv)
    StrokeTextView mStrokeTv;
    @BindView(R2.id.heat_praise_play_iv)
    ImageView mPlayIv;

    private Context mContext;

    public HeatPraiseView(@NonNull Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public HeatPraiseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public HeatPraiseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public HeatPraiseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr,
                          @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_heat_praise_view, this);
        ButterKnife.bind(this, view);
    }

    public Drawable getDrawable() {
        return mRoundRi.getDrawable();
    }

    public void setData(HeatPraiseEntity entity) {
        String imageUrl = entity.getDynamicsPath();
        String videoUrl = entity.getVideoPrintscreen();
        String numberText = String.valueOf(entity.getBePraiseTimes());
        int type = entity.getType();
        if (type == 0) {
            mPlayIv.setVisibility(GONE);
            ImageLoaderHelper.loadImage(mContext, mRoundRi, imageUrl, 4);
        } else {
            mPlayIv.setVisibility(VISIBLE);
            ImageLoaderHelper.loadImage(mContext, mRoundRi, videoUrl, 4);
        }
        if (StringUtils.isEmpty(numberText)) {
            mStrokeTv.setText("0");
        } else {
            mStrokeTv.setText(numberText);
        }
    }

    public void setZanNum(int praiseNum) {
        mStrokeTv.setText(String.valueOf(praiseNum));
    }

    public void setHistoryData(HistoryPraiseEntity.CurrentDayListBean data) {
        String imageUrl = data.getDynamicsPath();
        String videoUrl = data.getVideoPrintscreen();
        String numberText = String.valueOf(data.getBePraiseTimes());
        int type = data.getType();
        if (type == 0) {
            mPlayIv.setVisibility(GONE);
            ImageLoaderHelper.loadImage(mContext, mRoundRi, imageUrl, 0);
        } else {
            mPlayIv.setVisibility(VISIBLE);
            ImageLoaderHelper.loadImage(mContext, mRoundRi, videoUrl, 0);
        }
        if (StringUtils.isEmpty(numberText)) {
            mStrokeTv.setText("0");
        } else {
            mStrokeTv.setText(numberText);
        }
    }

}
