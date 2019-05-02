package com.ralf.module_community.entity.eventbus;

import com.ralf.module_community.entity.CommentEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RefreshCommentEntity
 * @email -
 * @date 2019/03/24 下午10:36
 **/
public class RefreshCommentEntity {

    /**
     * 刷新评论，在列表中的位置
     */
    private int position;
    private CommentEntity mEntity;

    public RefreshCommentEntity(int position, CommentEntity entity) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CommentEntity getEntity() {
        return mEntity;
    }

    public void setEntity(CommentEntity entity) {
        mEntity = entity;
    }
}
