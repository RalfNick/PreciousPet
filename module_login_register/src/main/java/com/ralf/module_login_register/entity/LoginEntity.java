package com.ralf.module_login_register.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LoginEntity
 * @email -
 * @date 2018/12/13 上午9:59
 **/
public class LoginEntity {

    /**
     * hxUserName : TL_16462
     * nickName : 傻妞
     * petList : [{"headPortrait":"https://tlpet.goodcool.cn:8444/fileserver/upload/image/petPic/bd11f080f58a4693acd55d10f3b77d11.jpg","petId":104,"petName":"小宁","sex":0},{"headPortrait":"https://tlpet.goodcool.cn:8444/fileserver/upload/image/petPic/339474b3ffcd48a5b496c99c2a6991a7.jpg","petId":179,"petName":"小畅","sex":1},{"headPortrait":"https://tlpet.goodcool.cn:8444/fileserver/upload/image/petPic/609099d4c66f4512aff07b21502ee3d9.jpg","petId":180,"petName":"猫猫","sex":1}]
     * qCList : [{"qCType":0,"qCheadPortrait":"https://tlpet.goodcool.cn:8444/fileserver/upload/image/userPic/357c7ff61f644ab6943d66ca8e44794f.jpg","qChxId":"TL_16566","qCnickName":"牵宠小助手","qCuserId":16566}]
     * token : a1ded47cffdaefa470fa571f85898d7d8498b713
     * userHeadPortrait : https://tlpet.goodcool.cn:8444/fileserver/upload/image/userPic/4b3ae8b11cff4a3bac90171dc134a26d.jpg
     * userId : 16462
     * userSex : 1
     */

    private String hxUserName;
    private String nickName;
    private String token;
    private String userHeadPortrait;
    private int userId;
    private int userSex;
    private List<PetListBean> petList;
    private List<QCListBean> qCList;
    private boolean masterSelect = true;

    public boolean getMasterSelect() {
        return masterSelect;
    }

    public void setMasterSelect(boolean masterSelect) {
        this.masterSelect = masterSelect;
    }

    public String getHxUserName() {
        return hxUserName;
    }

    public void setHxUserName(String hxUserName) {
        this.hxUserName = hxUserName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserHeadPortrait() {
        return userHeadPortrait;
    }

    public void setUserHeadPortrait(String userHeadPortrait) {
        this.userHeadPortrait = userHeadPortrait;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public List<PetListBean> getPetList() {
        return petList;
    }

    public void setPetList(List<PetListBean> petList) {
        this.petList = petList;
    }

    public List<QCListBean> getQCList() {
        return qCList;
    }

    public void setQCList(List<QCListBean> qCList) {
        this.qCList = qCList;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "hxUserName='" + hxUserName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", token='" + token + '\'' +
                ", userHeadPortrait='" + userHeadPortrait + '\'' +
                ", userId=" + userId +
                ", userSex=" + userSex +
                ", petList=" + petList +
                ", qCList=" + qCList +
                ", masterSelect=" + masterSelect +
                '}';
    }

    public static class PetListBean {
        /**
         * headPortrait : https://tlpet.goodcool.cn:8444/fileserver/upload/image/petPic/bd11f080f58a4693acd55d10f3b77d11.jpg
         * petId : 104
         * petName : 小宁
         * sex : 0
         */

        private String headPortrait;
        private int petId;
        private String petName;
        private int sex;
        private boolean petSelect = false;

        public boolean getPetSelect() {
            return petSelect;
        }

        public void setPetSelect(boolean petSelect) {
            this.petSelect = petSelect;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "PetListBean{" +
                    "headPortrait='" + headPortrait + '\'' +
                    ", petId=" + petId +
                    ", petName='" + petName + '\'' +
                    ", sex=" + sex +
                    ", petSelect=" + petSelect +
                    '}';
        }
    }

    public static class QCListBean {
        /**
         * qCType : 0
         * qCheadPortrait : https://tlpet.goodcool.cn:8444/fileserver/upload/image/userPic/357c7ff61f644ab6943d66ca8e44794f.jpg
         * qChxId : TL_16566
         * qCnickName : 牵宠小助手
         * qCuserId : 16566
         */

        private int qCType;
        private String qCheadPortrait;
        private String qChxId;
        private String qCnickName;
        private int qCuserId;

        public int getQCType() {
            return qCType;
        }

        public void setQCType(int qCType) {
            this.qCType = qCType;
        }

        public String getQCheadPortrait() {
            return qCheadPortrait;
        }

        public void setQCheadPortrait(String qCheadPortrait) {
            this.qCheadPortrait = qCheadPortrait;
        }

        public String getQChxId() {
            return qChxId;
        }

        public void setQChxId(String qChxId) {
            this.qChxId = qChxId;
        }

        public String getQCnickName() {
            return qCnickName;
        }

        public void setQCnickName(String qCnickName) {
            this.qCnickName = qCnickName;
        }

        public int getQCuserId() {
            return qCuserId;
        }

        public void setQCuserId(int qCuserId) {
            this.qCuserId = qCuserId;
        }

        @Override
        public String toString() {
            return "QCListBean{" +
                    "qCType=" + qCType +
                    ", qCheadPortrait='" + qCheadPortrait + '\'' +
                    ", qChxId='" + qChxId + '\'' +
                    ", qCnickName='" + qCnickName + '\'' +
                    ", qCuserId=" + qCuserId +
                    '}';
        }
    }

}
