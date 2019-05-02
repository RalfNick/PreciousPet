package com.ralf.module_community.entity.result;

import com.google.gson.annotations.SerializedName;
import com.ralf.module_community.entity.ChannelTypeEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AllChannelResultEntity
 * @email -
 * @date 2019/04/27 上午11:53
 **/
public class AllChannelResultEntity {

    @SerializedName("userId")
    private int userId;

    @SerializedName("myChannels")
    private List<ChannelTypeEntity> myChannels;

    @SerializedName("otherChannel")
    private List<ChannelTypeEntity> otherChannel;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ChannelTypeEntity> getMyChannels() {
        return myChannels;
    }

    public void setMyChannels(List<ChannelTypeEntity> myChannels) {
        this.myChannels = myChannels;
    }

    public List<ChannelTypeEntity> getOtherChannel() {
        return otherChannel;
    }

    public void setOtherChannel(List<ChannelTypeEntity> otherChannel) {
        this.otherChannel = otherChannel;
    }

    @Override
    public String toString() {
        return "AllChannelResultEntity{" +
                "userId=" + userId +
                ", myChannels=" + myChannels +
                ", otherChannel=" + otherChannel +
                '}';
    }
}
