package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetInfoEntity
 * @email -
 * @date 2019/04/09 上午8:04
 **/
public class PetInfoEntity {

    /**
     * age : 0
     * breed : 涓崕鐢板洯鐚�
     * headPortrait : http://192.168.1.139:8090/upload/image/petPic/d4.jpg
     * petId : 4
     * petName : 鐜嬪摬
     * sex : 0
     */

    private String age;
    private String breed;
    private String headPortrait;
    private int petId;
    private String petName;
    private int sex;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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

}
