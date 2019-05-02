package com.ralf.module_community.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseSectionEntity
 * @email -
 * @date 2019/04/22 上午11:02
 **/
public class AllChannelSectionEntity extends SectionEntity<ChannelTypeEntity> {

    private boolean isMore;

    public AllChannelSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public AllChannelSectionEntity(ChannelTypeEntity channelTypeEntity) {
        super(channelTypeEntity);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
