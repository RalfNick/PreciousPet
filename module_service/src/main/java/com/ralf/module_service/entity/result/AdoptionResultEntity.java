package com.ralf.module_service.entity.result;

import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.module_service.entity.PetPairEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AdoptionResultEntity
 * @email -
 * @date 2019/06/29 17:41
 **/
public class AdoptionResultEntity {

    /**
     * listPetAdopt : [{"adoptRequest":"押一付三","age":1409263968,"breedTypeName":"哈士奇","createTime":"1499342343","distance":"49.2km","id":10,"sex":1,"videoPrintscreen":"http://192.168.1.139:8090/upload/video/petAdopt/videoPic/4e8d3a4f752f483d9c8f74f5947a66e1.jpg"},{"adoptRequest":"押一付三","age":1409263968,"breedTypeName":"哈士奇","createTime":"1499342806","distance":"49.6km","id":11,"sex":1,"videoPrintscreen":"http://192.168.1.139:8090/upload/video/petAdopt/videoPic/4e8d3a4f752f483d9c8f74f5947a66e1.jpg"}]
     * pages : 1
     */

    private int pages;
    private List<AdoptionEntity> listPetAdopt;
    private List<PetPairEntity> listPetPair;


    public List<PetPairEntity> getListPetPair() {
        return listPetPair;
    }

    public void setListPetPair(List<PetPairEntity> listPetPair) {
        this.listPetPair = listPetPair;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<AdoptionEntity> getListPetAdopt() {
        return listPetAdopt;
    }

    public void setListPetAdopt(List<AdoptionEntity> listPetAdopt) {
        this.listPetAdopt = listPetAdopt;
    }

}
