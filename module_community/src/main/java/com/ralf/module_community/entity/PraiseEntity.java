package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseEntity
 * @email -
 * @date 2018/12/21 下午12:32
 **/
public class PraiseEntity {

    /**
     * userId : 2
     * headPortrait : http://192.168.1.139:8090/upload/image/userPic/2.jpg
     */
    private Integer userId;
    private String headPortrait;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "PraiseListEntity{" +
                "userId=" + userId +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}
