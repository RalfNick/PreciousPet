package com.ralf.module_db.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAssistantEntity
 * @email -
 * @date 2018/12/18 下午1:46
 **/
@Entity(nameInDb = "Pet_Assistant")
public class PetAssistantEntity {

    @Unique
    @Id(autoincrement = true)
    private long id;

    /**
     * qCType : 0
     * qCheadPortrait : https://tlpet.goodcool.cn:8444/fileserver/upload/image/userPic/357c7ff61f644ab6943d66ca8e44794f.jpg
     * qChxId : TL_16566
     * qCnickName : 牵宠小助手
     * qCuserId : 16566
     */

    @Property(nameInDb = "qc_type")
    private int qCType;

    @Property(nameInDb = "headportrait")
    private String qCheadPortrait;

    @Property(nameInDb = "qc_chxid")
    private String qChxId;

    @Property(nameInDb = "qc_name")
    private String qCnickName;

    @Property(nameInDb = "qc_userId")
    private int qCuserId;

    @Generated(hash = 454463711)
    public PetAssistantEntity(long id, int qCType, String qCheadPortrait, String qChxId, String qCnickName, int qCuserId) {
        this.id = id;
        this.qCType = qCType;
        this.qCheadPortrait = qCheadPortrait;
        this.qChxId = qChxId;
        this.qCnickName = qCnickName;
        this.qCuserId = qCuserId;
    }

    @Generated(hash = 1566405587)
    public PetAssistantEntity() {
    }

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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
