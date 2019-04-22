package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseEntity
 * @email -
 * @date 2019/04/15 上午11:48
 **/
public class FriendPraiseEntity {

    /**
     * createTime : 1492136125
     * dynamicId : 16
     * dynamicsPath : http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4
     * high : 150
     * nickName :
     * owner : 1
     * petHeadPortrait : http://192.168.1.95:8090/upload/image/petPic/h8.jpg
     * petId : 16
     * petName : 澶ф瘺
     * petSex : 0
     * type : 1
     * userHeadPortrait :
     * videoPrintscreen : http://192.168.1.95:8090/upload/video/videoPic/1490776077081video.jpg
     * width : 150
     */
    private int bePraisedTimes;
    private int bePraiseTimes;
    private int createTime;
    private int dynamicId;
    private String dynamicsPath;
    private int high;
    private String nickName;
    private int owner;
    private String petHeadPortrait;
    private int petId;
    private String petName;
    private int petSex;
    private int type;
    private String userHeadPortrait;
    private String videoPrintscreen;
    private int width;
    private String friendName;
    private int praiseState;
    private int ownPraise;
    private int userId;

    public int getBePraiseTimes() {
        return bePraiseTimes;
    }

    public void setBePraiseTimes(int bePraiseTimes) {
        this.bePraiseTimes = bePraiseTimes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getOwnPraise() {
        return ownPraise;
    }

    public void setOwnPraise(int ownPraise) {
        this.ownPraise = ownPraise;
    }

    public int getPraiseState() {
        return praiseState;
    }

    public void setPraiseState(int praiseState) {
        this.praiseState = praiseState;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }


    public int getBePraisedTimes() {
        return bePraisedTimes;
    }

    public void setBePraisedTimes(int bePraisedTimes) {
        this.bePraisedTimes = bePraisedTimes;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getPetHeadPortrait() {
        return petHeadPortrait;
    }

    public void setPetHeadPortrait(String petHeadPortrait) {
        this.petHeadPortrait = petHeadPortrait;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetSex() {
        return petSex;
    }

    public void setPetSex(int petSex) {
        this.petSex = petSex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserHeadPortrait() {
        return userHeadPortrait;
    }

    public void setUserHeadPortrait(String userHeadPortrait) {
        this.userHeadPortrait = userHeadPortrait;
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
}
