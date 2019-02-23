package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * 点赞，送礼，评论等用户交互后的结果实体类
 * @name FeedbackEntity
 * @email -
 * @date 2019/02/16 上午10:28
 **/
public class FeedbackEntity {

    /**
     * 1 - 点赞状态，0 - 取消点赞
     */
    public static final int STATE_PRAISE = 1;
    /**
     * id : 67
     */
    private int ownPraise;
    private int isBusy;
    private int isWish = 0;
    private int dynamicId;
    private String headPortrait;
    private String videoPath;
    private String videoPrintscreenPath;
    private String name;
    private String sign;
    private String downloadUrl;
    private String version;
    private String petHeadPortrait;
    /**
     * addAward : {"type":1,"value":3}
     * ownPraise : 1
     */

    private String addAward;

    public String getAddAward() {
        return addAward;
    }

    public void setAddAward(String addAward) {
        this.addAward = addAward;
    }

    public String getPetHeadPortrait() {
        return petHeadPortrait;
    }

    public void setPetHeadPortrait(String petHeadPortrait) {
        this.petHeadPortrait = petHeadPortrait;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoPrintscreenPath() {
        return videoPrintscreenPath;
    }

    public void setVideoPrintscreenPath(String videoPrintscreenPath) {
        this.videoPrintscreenPath = videoPrintscreenPath;
    }

    public int getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public int getIsWish() {
        return isWish;
    }

    public void setIsWish(int isWish) {
        this.isWish = isWish;
    }

    public int getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(int isBusy) {
        this.isBusy = isBusy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    public int getOwnPraise() {
        return ownPraise;
    }

    public void setOwnPraise(int ownPraise) {
        this.ownPraise = ownPraise;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "ownPraise=" + ownPraise +
                ", isBusy=" + isBusy +
                ", isWish=" + isWish +
                ", dynamicId=" + dynamicId +
                ", headPortrait='" + headPortrait + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", videoPrintscreenPath='" + videoPrintscreenPath + '\'' +
                ", name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", version='" + version + '\'' +
                ", petHeadPortrait='" + petHeadPortrait + '\'' +
                ", addAward='" + addAward + '\'' +
                ", userId=" + userId +
                ", id='" + id + '\'' +
                '}';
    }
}
