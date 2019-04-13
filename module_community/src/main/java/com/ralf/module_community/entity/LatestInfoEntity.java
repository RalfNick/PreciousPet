package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoEntity
 * @email -
 * @date 2019/04/13 下午4:45
 **/
public class LatestInfoEntity {

    /**
     * createTime : 1494578912
     * dynamicId : 354
     * dynamicsPath : http://192.168.1.139:8090/upload/image/dynamicPic/fe26f1e8872c43f084fe42937851db57.jpg
     * high : 497
     * type : 0
     * videoPrintscreen :
     * width : 793
     */

    private String createTime;
    private int dynamicId;
    private String dynamicsPath;
    private int high;
    private int type;
    private String videoPrintscreen;
    private int width;

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

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "LatestInfoEntity{" +
                "createTime='" + createTime + '\'' +
                ", dynamicId=" + dynamicId +
                ", dynamicsPath='" + dynamicsPath + '\'' +
                ", high=" + high +
                ", type=" + type +
                ", videoPrintscreen='" + videoPrintscreen + '\'' +
                ", width=" + width +
                '}';
    }
}
