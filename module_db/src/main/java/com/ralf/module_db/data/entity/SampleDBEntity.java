package com.ralf.module_db.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author KaraShok(zhangyaozhong)
 * @name SampleDBEntity
 * DESCRIPTION
 * @email zhangyaozhong@icourt.cc
 * @date 2018/05/15/下午4:57
 */

@Entity
public class SampleDBEntity {

    @Id(autoincrement = true)
    private long id;

    @Unique
    private String entityId;

    private String entityJson;

    @Generated(hash = 1099150035)
    public SampleDBEntity(long id, String entityId, String entityJson) {
        this.id = id;
        this.entityId = entityId;
        this.entityJson = entityJson;
    }

    @Generated(hash = 1957180818)
    public SampleDBEntity() {
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
}
