package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ListRefreshEntity
 * @email -
 * @date 2019/02/21 下午10:32
 **/
public class ListRefreshEntity {

    private RefreshType mRefreshType;

    public ListRefreshEntity(RefreshType refreshType) {
        mRefreshType = refreshType;
    }

    public enum RefreshType {
        /**
         * 刷新评论，刷新点赞
         */
        REFRESH_COMMENT, REFRESH_PRAISE
    }

    public RefreshType getRefreshType() {
        return mRefreshType;
    }
}
