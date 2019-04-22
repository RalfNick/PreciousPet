package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseEntity
 * @email -
 * @date 2019/04/19 下午4:21
 **/
public class HistoryPraiseEntity {

    /**
     * currentDayList : [{"bePraiseTimes":59,"createTime":"1492136112","dynamicId":3,"dynamicsPath":"http://192.168.1.139:8090/upload/image/petPic/d3.jpg","type":0,"videoPrintscreen":"http://192.168.1.139:8090"}]
     * currentDayTotal : 4
     * dateTime : 1492012800
     */

    private int currentDayTotal;
    private String dateTime;
    private List<CurrentDayListBean> currentDayList;

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

    public List<CurrentDayListBean> getCurrentDayList() {
        return currentDayList;
    }

    public void setCurrentDayList(List<CurrentDayListBean> currentDayList) {
        this.currentDayList = currentDayList;
    }

    public static class CurrentDayListBean {
        /**
         * bePraiseTimes : 59
         * createTime : 1492136112
         * dynamicId : 3
         * dynamicsPath : http://192.168.1.139:8090/upload/image/petPic/d3.jpg
         * type : 0
         * videoPrintscreen : http://192.168.1.139:8090
         */

        private int bePraiseTimes;
        private String createTime;
        private int dynamicId;
        private String dynamicsPath;
        private int type;
        private String videoPrintscreen;

        public int getBePraiseTimes() {
            return bePraiseTimes;
        }

        public void setBePraiseTimes(int bePraiseTimes) {
            this.bePraiseTimes = bePraiseTimes;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDynamicId() {
            return dynamicId;
        }

        public void setDynamicId(int dynamicId) {
            this.dynamicId = dynamicId;
        }

        public String getDynamicsPath() {
            return dynamicsPath;
        }

        public void setDynamicsPath(String dynamicsPath) {
            this.dynamicsPath = dynamicsPath;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVideoPrintscreen() {
            return videoPrintscreen;
        }

        public void setVideoPrintscreen(String videoPrintscreen) {
            this.videoPrintscreen = videoPrintscreen;
        }
    }

}
