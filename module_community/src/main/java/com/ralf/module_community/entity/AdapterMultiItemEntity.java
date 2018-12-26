package com.ralf.module_community.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AdapterMultiItemEntity
 * @email -
 * @date 2018/12/25 上午10:00
 **/
public class AdapterMultiItemEntity implements MultiItemEntity {

    private int mItemType;
    private List<BannerEntity> mBannerEntityList;
    private FeaturedEntity.DynamicListBean mDynamicBean;

    public AdapterMultiItemEntity(int itemType) {
        mItemType = itemType;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public List<BannerEntity> getBannerEntityList() {
        return mBannerEntityList;
    }

    public void setBannerEntityList(List<BannerEntity> bannerEntityList) {
        mBannerEntityList = bannerEntityList;
    }

    public FeaturedEntity.DynamicListBean getDynamicBean() {
        return mDynamicBean;
    }

    public void setDynamicBean(FeaturedEntity.DynamicListBean dynamicBean) {
        mDynamicBean = dynamicBean;
    }
}
