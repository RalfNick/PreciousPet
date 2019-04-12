package com.ralf.module_community.entity.result;

import com.ralf.module_community.entity.PetInfoEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetListResultEntity
 * @email -
 * @date 2019/04/09 上午8:25
 **/
public class PetListResultEntity {

    /**
     * nickName : 鎸侀厭鍔濇枩闃�
     * petList : [{"age":"0","breed":"涓崕鐢板洯鐚�","headPortrait":"http://192.168.1.139:8090/upload/image/petPic/d4.jpg","petId":4,"petName":"鐜嬪摬","sex":0}]
     */

    private String nickName;
    private List<PetInfoEntity> petList;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<PetInfoEntity> getPetList() {
        return petList;
    }

    public void setPetList(List<PetInfoEntity> petList) {
        this.petList = petList;
    }
}
