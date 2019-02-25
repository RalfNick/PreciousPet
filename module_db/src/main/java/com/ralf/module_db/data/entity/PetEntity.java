package com.ralf.module_db.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetEntity
 * @email -
 * @date 2018/12/13 下午5:33
 **/
@Entity(nameInDb = "Pet")
public class PetEntity {

    @Unique
    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "head_portrait")
    private String headPortrait;

    private int petId;

    @Property(nameInDb = "name")
    private String petName;

    private int sex;

    @Property(nameInDb = "pet_select")
    private boolean petSelect;

    @Generated(hash = 2027225065)
    public PetEntity() {
    }

    @Generated(hash = 228638810)
    public PetEntity(long id, String headPortrait, int petId, String petName,
                     int sex, boolean petSelect) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.petId = petId;
        this.petName = petName;
        this.sex = sex;
        this.petSelect = petSelect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isPetSelect() {
        return petSelect;
    }

    public void setPetSelect(boolean petSelect) {
        this.petSelect = petSelect;
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", headPortrait='" + headPortrait + '\'' +
                ", petId=" + petId +
                ", petName='" + petName + '\'' +
                ", sex=" + sex +
                ", petSelect=" + petSelect +
                '}';
    }

    public boolean getPetSelect() {
        return this.petSelect;
    }
}
