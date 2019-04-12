package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseEntity
 * @email -
 * @date 2018/12/21 下午12:32
 **/
public class PraiseEntity {

    /**
     * sex : 0
     * userId : 9
     * petList : []
     * headPortrait :
     * petTotal : 0
     * province :
     * praiseId : 6
     * city :
     * nickName : asdasd
     */

    private int sex;
    private int userId;
    private String headPortrait;
    private int petTotal;
    private String province;
    private int praiseId;
    private String city;
    private String nickName;
    private List<PetBean> petList;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public int getPetTotal() {
        return petTotal;
    }

    public void setPetTotal(int petTotal) {
        this.petTotal = petTotal;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(int praiseId) {
        this.praiseId = praiseId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<PetBean> getPetList() {
        return petList;
    }

    public void setPetList(List<PetBean> petList) {
        this.petList = petList;
    }

    public static class PetBean {
        /**
         * petHeadPortrait : http://192.168.1.139:8090/upload/image/petPic/d6.jpg
         * petId : 21
         */

        private String petHeadPortrait;
        private int petId;

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

        @Override
        public String toString() {
            return "PetListBean{" +
                    "petHeadPortrait='" + petHeadPortrait + '\'' +
                    ", petId=" + petId +
                    '}';
        }
    }
}
