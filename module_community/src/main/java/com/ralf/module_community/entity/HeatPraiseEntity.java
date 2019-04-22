package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HeatPraiseEntity
 * @email -
 * @date 2019/04/19 下午4:15
 **/
public class HeatPraiseEntity {

    /**
     * bePraiseTimes : 9
     * createTime : 1492099200
     * dynamicId : 9
     * dynamicsPath : http://192.168.1.139:8090/upload/image/petPic/d9.jpg
     * imgHigh : 600
     * imgWidth : 600
     * type : 0
     * videoPrintscreen : http://192.168.1.139:8090
     */

    private int bePraiseTimes;
    private String createTime;
    private int dynamicId;
    private String dynamicsPath;
    private int imgHigh;
    private int imgWidth;
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

    public int getImgHigh() {
        return imgHigh;
    }

    public void setImgHigh(int imgHigh) {
        this.imgHigh = imgHigh;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
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
