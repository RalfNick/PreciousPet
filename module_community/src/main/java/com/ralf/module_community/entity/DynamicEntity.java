package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DynamicEntity
 * @email -
 * @date 2019/01/05 下午12:54
 **/
public class DynamicEntity {

    private String videoPrintscreen;

    private Integer width;

    private Integer commentTotal;

    private Integer userId;

    private String petHeadPortrait;

    private Integer attentionStatus;

    private Integer praiseTimes;

    private String province;

    private String nickName;

    private String talk;

    private Integer ownPraise;

    private String createTime;

    private String petName;

    private String city;

    private Integer high;

    private Integer type;

    private String dynamicsPath;

    private Integer owner;

    private Integer category;

    private String breed;

    private Integer petId;

    private Integer bePraiseTimes;

    private String country;

    private Integer petSex;

    private Integer dynamicsType;

    private Integer dynamicId;

    private String userHeadPortrait;

    private RefreshType mRefreshType;

    private String state;
    private List<CommentEntity> commentList;
    private List<PraiseEntity> praiseList;

    private int[] viewLocations;

    public int[] getViewLocations() {
        return viewLocations;
    }

    public void setViewLocations(int[] viewLocations) {
        this.viewLocations = viewLocations;
    }

    public String getVideoPrintscreen() {
        return videoPrintscreen;
    }

    public void setVideoPrintscreen(String videoPrintscreen) {
        this.videoPrintscreen = videoPrintscreen;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public RefreshType getRefreshType() {
        return mRefreshType;
    }

    public void setRefreshType(RefreshType refreshType) {
        mRefreshType = refreshType;
    }

    public Integer getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(Integer commentTotal) {
        this.commentTotal = commentTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPetHeadPortrait() {
        return petHeadPortrait;
    }

    public void setPetHeadPortrait(String petHeadPortrait) {
        this.petHeadPortrait = petHeadPortrait;
    }

    public Integer getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(Integer attentionStatus) {
        this.attentionStatus = attentionStatus;
    }

    public Integer getPraiseTimes() {
        return praiseTimes;
    }

    public void setPraiseTimes(Integer praiseTimes) {
        this.praiseTimes = praiseTimes;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public Integer getOwnPraise() {
        return ownPraise;
    }

    public void setOwnPraise(Integer ownPraise) {
        this.ownPraise = ownPraise;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDynamicsPath() {
        return dynamicsPath;
    }

    public void setDynamicsPath(String dynamicsPath) {
        this.dynamicsPath = dynamicsPath;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getBePraiseTimes() {
        return bePraiseTimes;
    }

    public void setBePraiseTimes(Integer bePraiseTimes) {
        this.bePraiseTimes = bePraiseTimes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPetSex() {
        return petSex;
    }

    public void setPetSex(Integer petSex) {
        this.petSex = petSex;
    }

    public Integer getDynamicsType() {
        return dynamicsType;
    }

    public void setDynamicsType(Integer dynamicsType) {
        this.dynamicsType = dynamicsType;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getUserHeadPortrait() {
        return userHeadPortrait;
    }

    public void setUserHeadPortrait(String userHeadPortrait) {
        this.userHeadPortrait = userHeadPortrait;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<CommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentEntity> commentList) {
        this.commentList = commentList;
    }

    public List<PraiseEntity> getPraiseList() {
        return praiseList;
    }

    public void setPraiseList(List<PraiseEntity> praiseList) {
        this.praiseList = praiseList;
    }

    @Override
    public String toString() {
        return "DynamicEntity{" +
                "videoPrintscreen='" + videoPrintscreen + '\'' +
                ", width=" + width +
                ", commentTotal=" + commentTotal +
                ", userId=" + userId +
                ", petHeadPortrait='" + petHeadPortrait + '\'' +
                ", attentionStatus=" + attentionStatus +
                ", praiseTimes=" + praiseTimes +
                ", province='" + province + '\'' +
                ", nickName='" + nickName + '\'' +
                ", talk='" + talk + '\'' +
                ", ownPraise=" + ownPraise +
                ", createTime='" + createTime + '\'' +
                ", petName='" + petName + '\'' +
                ", city='" + city + '\'' +
                ", high=" + high +
                ", type=" + type +
                ", dynamicsPath='" + dynamicsPath + '\'' +
                ", owner=" + owner +
                ", category=" + category +
                ", breed='" + breed + '\'' +
                ", petId=" + petId +
                ", bePraiseTimes=" + bePraiseTimes +
                ", country='" + country + '\'' +
                ", petSex=" + petSex +
                ", dynamicsType=" + dynamicsType +
                ", dynamicId=" + dynamicId +
                ", userHeadPortrait='" + userHeadPortrait + '\'' +
                ", state='" + state + '\'' +
                ", commentList=" + commentList +
                ", praiseList=" + praiseList +
                '}';
    }

    public enum RefreshType {
        /**
         * 刷新类型,关注状态，点赞，评论，默认
         */
        REFRESH_STATE_ATTENTION, REFRESH_STATE_PRAISE, REFRESH_COMMENT, REFRESH_DEFAULT
    }
}
