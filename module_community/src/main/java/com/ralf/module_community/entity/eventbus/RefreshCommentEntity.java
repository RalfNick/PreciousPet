package com.ralf.module_community.entity.eventbus;

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
    int position;

    public RefreshCommentEntity(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
