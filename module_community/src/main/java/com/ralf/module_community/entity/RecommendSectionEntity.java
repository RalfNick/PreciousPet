package com.ralf.module_community.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendSectionEntity
 * @email -
 * @date 2019/04/13 下午1:14
 **/
public class RecommendSectionEntity extends SectionEntity<RecommendEntity.InnerBean> {

    private boolean isMore;

    public RecommendSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public RecommendSectionEntity(RecommendEntity.InnerBean innerBean) {
        super(innerBean);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
