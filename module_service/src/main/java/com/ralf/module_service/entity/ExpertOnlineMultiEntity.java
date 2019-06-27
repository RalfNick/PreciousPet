package com.ralf.module_service.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineMultiEntity
 * @email -
 * @date 2019/06/19 20:26
 **/
public class ExpertOnlineMultiEntity implements MultiItemEntity {

    private int mItemType;
    private ExpertOnlineEntity mData;
    private ExpertOnlineEntity.SeekHelpsListBean mSeekHelpsListBean;
    private static String mMedicalType;
    private static String mPetType;

    public ExpertOnlineMultiEntity(int itemType) {
        mItemType = itemType;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public ExpertOnlineEntity getData() {
        return mData;
    }

    public void setData(ExpertOnlineEntity data) {
        mData = data;
    }

    public ExpertOnlineEntity.SeekHelpsListBean getSeekHelpsListBean() {
        return mSeekHelpsListBean;
    }

    public void setSeekHelpsListBean(ExpertOnlineEntity.SeekHelpsListBean seekHelpsListBean) {
        mSeekHelpsListBean = seekHelpsListBean;
    }

    public String getMedicalType() {
        return mMedicalType;
    }

    public void setMedicalType(String medicalType) {
        mMedicalType = medicalType;
    }

    public String getPetType() {
        return mPetType;
    }

    public void setPetType(String petType) {
        mPetType = petType;
    }
}
