package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * 关注结果实体类
 * @name AttentionEntity
 * @email -
 * @date 2019/02/25 上午8:57
 **/
public class AttentionEntity {

    /**
     * status : 0
     * toUserId : 6
     */

    private int status;
    private int toUserId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return "AttentionEntity{" +
                "status=" + status +
                ", toUserId=" + toUserId +
                '}';
    }
}
