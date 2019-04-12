package com.ralf.module_user.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetRecordEntity
 * @email -
 * @date 2019/04/11 下午7:24
 **/
public class PetRecordEntity {

    /**
     * createTime : 1492136113
     * dynamicId : 4
     * dynamicsPath : http://192.168.1.139:8090/upload/image/dynamicPic/d4.jpg
     * high : 600
     * petId : 2
     * talk : 鐗佃繃鐨勬墜铏藉凡鍒嗗紑浣嗕綘鍜岀骞翠竴鏍蜂細浣忓湪鎴戠殑蹇冮噷浣犲揩涔愬氨濂�
     * type : 0
     * videoPrintscreen :
     * width : 600
     */

    private String createTime;
    private int dynamicId;
    private String dynamicsPath;
    private int high;
    private int petId;
    private String talk;
    private int type;
    private String videoPrintscreen;
    private int width;
    private String year;
    private String month;
    private String day;
    private boolean yearTag = false;
    private boolean monthTag = false;
    private boolean dayTag = false;

    /**
     * 标记字段，是否是第一个 item，显示【动态字样】
     */
    private boolean isFirstItem;
    /**
     * 是否是我的详情
     */
    private boolean isMyTag;

    public boolean getIsYearSame() {
        return yearTag;
    }

    public void setIsYearSame(boolean yearTag) {
        this.yearTag = yearTag;
    }

    public boolean getIsMonthSame() {
        return monthTag;
    }

    public void setIsMonthSame(boolean monthTag) {
        this.monthTag = monthTag;
    }

    public boolean getIsDaySame() {
        return dayTag;
    }

    public void setIsDaySame(boolean dayTag) {
        this.dayTag = dayTag;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
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

    public boolean isFirstItem() {
        return isFirstItem;
    }

    public void setFirstItem(boolean firstItem) {
        isFirstItem = firstItem;
    }

    public boolean isMyTag() {
        return isMyTag;
    }

    public void setMyTag(boolean myTag) {
        isMyTag = myTag;
    }

    @Override
    public String toString() {
        return "PetXiangQingRecordListEntity{" +
                "createTime='" + createTime + '\'' +
                ", dynamicId=" + dynamicId +
                ", dynamicsPath='" + dynamicsPath + '\'' +
                ", high=" + high +
                ", petId=" + petId +
                ", talk='" + talk + '\'' +
                ", type=" + type +
                ", videoPrintscreen='" + videoPrintscreen + '\'' +
                ", width=" + width +
                '}';
    }

}
