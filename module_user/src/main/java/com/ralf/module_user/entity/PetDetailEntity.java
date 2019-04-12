package com.ralf.module_user.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetDetailEntity
 * @email -
 * @date 2019/04/11 上午10:48
 **/
public class PetDetailEntity<T> {

    /**
     * bePraisedTimes : 6
     * birthday : 1490837280
     * breed :  绉嬬敯鐘�
     * createTime : 1490837280
     * homeTime : 1490776398
     * pages : 1
     * petHeadPortrait : http://192.168.1.139:8090/upload/image/petPic/d2.jpg
     * petId : 2
     * petName : 鐜嬪摬
     * petSex : 1
     * recordList : [{"createTime":"1492136113","dynamicId":4,"dynamicsPath":"http://192.168.1.139:8090/upload/image/dynamicPic/d4.jpg","high":600,"petId":2,"talk":"鐗佃繃鐨勬墜铏藉凡鍒嗗紑浣嗕綘鍜岀骞翠竴鏍蜂細浣忓湪鎴戠殑蹇冮噷浣犲揩涔愬氨濂�","type":0,"videoPrintscreen":"","width":600},{"createTime":"1492136112","dynamicId":3,"dynamicsPath":"http://192.168.1.139:8090/upload/image/dynamicPic/d3.jpg","high":600,"petId":2,"talk":" 鎴戜滑灏辨槸杩欐牱鑻嶈�佺殑锛屼粠鏃跺厜鐨勪竴绔⒕杞埌鏃跺厜鐨勫彟涓�绔紝璇峰埆璇村啀瑙侊紝涓嶉渶瑕佸啀瑙�","type":0,"videoPrintscreen":"","width":600}]
     * userId : 2
     */

    private int bePraisedTimes;
    private String birthday;
    private String breed;
    private String createTime;
    private String homeTime;
    private int pages;
    private String petHeadPortrait;
    private int petId;
    private String petName;
    private int petSex;
    private int userId;
    private int status;
    private List<T> recordList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBePraisedTimes() {
        return bePraisedTimes;
    }

    public void setBePraisedTimes(int bePraisedTimes) {
        this.bePraisedTimes = bePraisedTimes;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHomeTime() {
        return homeTime;
    }

    public void setHomeTime(String homeTime) {
        this.homeTime = homeTime;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    @Override
    public String toString() {
        return "PetXiangQingEntity{" +
                "bePraisedTimes=" + bePraisedTimes +
                ", birthday='" + birthday + '\'' +
                ", breed='" + breed + '\'' +
                ", createTime='" + createTime + '\'' +
                ", homeTime='" + homeTime + '\'' +
                ", pages=" + pages +
                ", petHeadPortrait='" + petHeadPortrait + '\'' +
                ", petId=" + petId +
                ", petName='" + petName + '\'' +
                ", petSex=" + petSex +
                ", userId=" + userId +
                ", recordList=" + recordList +
                '}';
    }

}
