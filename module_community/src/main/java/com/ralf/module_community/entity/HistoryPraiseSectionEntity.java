package com.ralf.module_community.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseSectionEntity
 * @email -
 * @date 2019/04/22 上午11:02
 **/
public class HistoryPraiseSectionEntity extends SectionEntity<HeatPraiseEntity> {

    private boolean isMore;
    private String dateTime;

    public HistoryPraiseSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public HistoryPraiseSectionEntity(HeatPraiseEntity heatPraiseEntity) {
        super(heatPraiseEntity);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
