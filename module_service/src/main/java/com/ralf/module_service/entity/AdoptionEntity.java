package com.ralf.module_service.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AdoptionEntity
 * @email -
 * @date 2019/06/29 17:12
 **/
public class AdoptionEntity {

    /**
     * adoptRequest : 押一付三
     * age : 1409263968
     * breedTypeName : 哈士奇
     * createTime : 1499342343
     * distance : 49.2km
     * id : 10
     * sex : 1
     * videoPrintscreen : http://192.168.1.139:8090/upload/video/petAdopt/videoPic/4e8d3a4f752f483d9c8f74f5947a66e1.jpg
     */

    private String adoptRequest;
    private String age;
    private String breedTypeName;
    private String createTime;
    private String distance;
    private int id;
    private int sex;
    private String videoPrintscreen;

    public String getAdoptRequest() {
        return adoptRequest;
    }

    public void setAdoptRequest(String adoptRequest) {
        this.adoptRequest = adoptRequest;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getVideoPrintscreen() {
        return videoPrintscreen;
    }

    public void setVideoPrintscreen(String videoPrintscreen) {
        this.videoPrintscreen = videoPrintscreen;
    }

}
