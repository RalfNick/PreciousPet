package com.ralf.module_service.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetPairEntity
 * @email -
 * @date 2019/06/29 17:43
 **/
public class PetPairEntity {

    /**
     * age : 1490837280
     * breedTypeName : 中华田园猫
     * createTime : 1499533968
     * distance : 49.6km
     * id : 5
     * pairRequest : 两岁的弟弟还是个小处男，女盆友在哪里
     * petHeadPortrait : http://192.168.1.139:8090/upload/image/petPic/d9.jpg
     * petName : 王哲
     * sex : 1
     */

    private int age;
    private String breedTypeName;
    private String createTime;
    private String distance;
    private int id;
    private String pairRequest;
    private String petHeadPortrait;
    private String petName;
    private int sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreedTypeName() {
        return breedTypeName;
    }

    public void setBreedTypeName(String breedTypeName) {
        this.breedTypeName = breedTypeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPairRequest() {
        return pairRequest;
    }

    public void setPairRequest(String pairRequest) {
        this.pairRequest = pairRequest;
    }

    public String getPetHeadPortrait() {
        return petHeadPortrait;
    }

    public void setPetHeadPortrait(String petHeadPortrait) {
        this.petHeadPortrait = petHeadPortrait;
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

}
