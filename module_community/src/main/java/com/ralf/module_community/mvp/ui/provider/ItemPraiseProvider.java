package com.ralf.module_community.mvp.ui.provider;

import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.ui.view.HeadPortraitListView;
import com.ralf.module_community.mvp.ui.view.PraiseAnimView;
import com.ralf.pet_provider.router.RouterConfig;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemPraiseProvider
 * @email -
 * @date 2019/02/26 下午3:38
 **/
public class ItemPraiseProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    /**
     * 显示动画的Toast的偏移
     */
    private static final int OFFSET_X_TOAST = 40;
    private static final int OFFSET_Y_TOAST = 240;
    /**
     * 动画时长
     */
    private int mDuration;

    @Override
    public int viewType() {
        return MultiItemType.TYPE_PRAISE;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_praise_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        setPraiseView(helper, data.getDynamicBean());

        helper.addOnClickListener(R.id.item_praise_support_rb);
        helper.addOnClickListener(R.id.item_praise_gift_rb);
        helper.addOnClickListener(R.id.item_praise_comment_rb);
        helper.addOnClickListener(R.id.item_praise_share_rb);
    }

    /**
     * @param helper ViewHolder
     * @param entity 数据
     */
    private void setPraiseView(BaseViewHolder helper, DynamicEntity entity) {
        Integer ownPraise = entity.getOwnPraise();
        Integer bePraiseTimes = entity.getBePraiseTimes();
        // 点赞总人数
        TextView personNumTv = helper.getView(R.id.item_praise_person_num);
        helper.addOnClickListener(R.id.item_praise_person_num);
        if (bePraiseTimes < 1) {
            personNumTv.setVisibility(View.GONE);
        } else {
            personNumTv.setVisibility(View.VISIBLE);
            personNumTv.setText(String.valueOf(bePraiseTimes));
        }
        setHeadPortraitData(helper, entity);
        RadioButton supportButton = helper.getView(R.id.item_praise_support_rb);
        ImageView disSupportIv = helper.getView(R.id.item_praise_not_support_iv);
        supportButton.setChecked(ownPraise == 1);
        if (entity.getRefreshType() == DynamicEntity.RefreshType.REFRESH_STATE_PRAISE) {
            entity.setRefreshType(DynamicEntity.RefreshType.REFRESH_DEFAULT);
            if (ownPraise == 1) {
                disSupportIv.setVisibility(View.INVISIBLE);
                // 显示动画
                showToastAnim(entity);
            } else {
                disSupportIv.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .asGif()
                        .load(Constant.GIF_REMOVE_PRAISE)
                        .listener(new RequestListener<GifDrawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<GifDrawable> target, boolean isFirstResource) {
                                disSupportIv.setVisibility(View.INVISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GifDrawable resource, Object model,
                                                           Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                // 计算动画时长
                                if (mDuration == 0) {
                                    mDuration = getGifDuration(resource);
                                }
                                disSupportIv.postDelayed(() -> disSupportIv.setVisibility(View.GONE), mDuration);
                                return false;
                            }
                        })
                        .into(disSupportIv);
            }
        }
    }

    /**
     * 通过Toast显示动画
     *
     * @param entity 数据
     */
    private void showToastAnim(DynamicEntity entity) {
        ToastUtils.cancel();
        int[] locations = entity.getViewLocations();
        if (locations != null) {
            int x = locations[0] + SizeUtils.dp2px(OFFSET_X_TOAST);
            int y = locations[1] - SizeUtils.dp2px(OFFSET_Y_TOAST);
            ToastUtils.setGravity(Gravity.START | Gravity.TOP, x, y);
            PraiseAnimView animView = ToastUtils.showCustomLong(R.layout.layout_toast_praise_animation)
                    .findViewById(R.id.praise_anim_view);
            animView.startAnim();
        }
    }


    /**
     * 计算 gif 的时长
     *
     * @param resource GifDrawable
     * @return
     */
    private int getGifDuration(GifDrawable resource) {
        try {
            Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
            Field frameLoaderField = gifStateClass.getDeclaredField("frameLoader");
            frameLoaderField.setAccessible(true);
            Object frameLoader = frameLoaderField.get(resource.getConstantState());

            Class frameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
            Field gifDecoderField = frameLoaderClass.getDeclaredField("gifDecoder");
            gifDecoderField.setAccessible(true);
            GifDecoder gifDecoder = (GifDecoder) gifDecoderField.get(frameLoader);

            int duration = 0;
            for (int i = 0; i < resource.getFrameCount(); i++) {
                duration += gifDecoder.getDelay(i);
            }
            return duration;
        } catch (Exception e) {
            Logger.e("getGifDuration - " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 加载头像数据
     *
     * @param helper holder
     * @param entity 数据
     */
    private void setHeadPortraitData(BaseViewHolder helper, DynamicEntity entity) {
        List<PraiseEntity> praiseList = entity.getPraiseList();
        Map<Integer, String> headDataMap = new LinkedHashMap<>();
        if (praiseList != null && praiseList.size() > 0) {
            for (PraiseEntity bean : praiseList) {
                headDataMap.put(bean.getUserId(), bean.getHeadPortrait());
            }
        }
        HeadPortraitListView personView = helper.getView(R.id.item_praise_person_view);
        personView.setClickListener(new HeadPortraitListView.OnHeadPortraitClickListener() {
            @Override
            public void onClick(int id) {
                ARouter.getInstance()
                        .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_USER_ID, id)
                        .navigation();
            }

            @Override
            public void onMoreClick() {
            }
        });
        personView.setHeadPortraitData(headDataMap);
    }
}
