package com.ralf.module_db.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author KaraShok(zhangyaozhong)
 * @name ContractDBEntity
 * DESCRIPTION
 * @email zhangyaozhong@icourt.cc
 * @date 2018/05/15/下午5:11
 */

@Entity
public class ContractDBEntity {

    @Id(autoincrement = true)
    private long id;

    @Unique
    private String entityId;

    private String entityJson;

    @Generated(hash = 198171527)
    public ContractDBEntity(long id, String entityId, String entityJson) {
        this.id = id;
        this.entityId = entityId;
        this.entityJson = entityJson;
    }

    @Generated(hash = 1386263427)
    public ContractDBEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityJson() {
        return this.entityJson;
    }

    public void setEntityJson(String entityJson) {
        this.entityJson = entityJson;
    }

    @Override
    public String toString() {
        return "ContractDBEntity{" +
                "id=" + id +
                ", entityId='" + entityId + '\'' +
                ", entityJson='" + entityJson + '\'' +
                '}';
    }
}
