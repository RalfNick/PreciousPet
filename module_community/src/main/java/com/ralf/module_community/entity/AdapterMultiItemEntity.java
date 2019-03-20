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
    private DynamicEntity mDynamicBean;
    private CommentEntity mCommentEntity;
    private List<PraiseEntity> mPraiseEntityList;
    /**
     * 当前评论人的用户 id
     */
    private int mUserId;

    public AdapterMultiItemEntity(int itemType) {
        mItemType = itemType;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public DynamicEntity getDynamicBean() {
        return mDynamicBean;
    }

    public void setDynamicBean(DynamicEntity dynamicBean) {
        mDynamicBean = dynamicBean;
    }

    public CommentEntity getCommentEntity() {
        return mCommentEntity;
    }

    public void setCommentEntity(CommentEntity commentEntity) {
        mCommentEntity = commentEntity;
    }

    public List<PraiseEntity> getPraiseEntityList() {
        return mPraiseEntityList;
    }

    public void setPraiseEntityList(List<PraiseEntity> praiseEntityList) {
        mPraiseEntityList = praiseEntityList;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }
}
