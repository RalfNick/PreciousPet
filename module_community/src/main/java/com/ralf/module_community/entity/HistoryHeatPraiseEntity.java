package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryHeatPraiseEntity
 * @email -
 * @date 2019/04/22 上午11:16
 **/
public class HistoryHeatPraiseEntity {

    /**
     * currentDayList : [{"bePraiseTimes":59,"createTime":"1492136112","dynamicId":3,"dynamicsPath":"http://192.168.1.139:8090/upload/image/petPic/d3.jpg","type":0,"videoPrintscreen":"http://192.168.1.139:8090"}]
     * currentDayTotal : 4
     * dateTime : 1492012800
     */

    private int currentDayTotal;
    private String dateTime;
    private List<HeatPraiseEntity> currentDayList;

    public int getCurrentDayTotal() {
        return currentDayTotal;
    }

    public void setCurrentDayTotal(int currentDayTotal) {
        this.currentDayTotal = currentDayTotal;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<HeatPraiseEntity> getCurrentDayList() {
        return currentDayList;
    }

    public void setCurrentDayList(List<HeatPraiseEntity> currentDayList) {
        this.currentDayList = currentDayList;
    }
}
