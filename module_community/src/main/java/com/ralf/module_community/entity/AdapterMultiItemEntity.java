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
    private DynamicEntity mDynamicBean;

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

    public DynamicEntity getDynamicBean() {
        return mDynamicBean;
    }

    public void setDynamicBean(DynamicEntity dynamicBean) {
        mDynamicBean = dynamicBean;
    }
}
