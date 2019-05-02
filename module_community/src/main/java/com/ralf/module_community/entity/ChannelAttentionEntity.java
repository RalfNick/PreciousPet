package com.ralf.module_community.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelAttentionEntity
 * @email -
 * @date 2019/04/30 17:56
 **/
public class ChannelAttentionEntity {

    /**
     * relType : 2
     * status : 0
     */

    @SerializedName("relType")
    private int relType;
    @SerializedName("status")
    private int status;

    public int getRelType() {
        return relType;
    }

    public void setRelType(int relType) {
        this.relType = relType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FocusOrLikeEntity{" +
                "relType=" + relType +
                ", status=" + status +
                '}';
    }

}
