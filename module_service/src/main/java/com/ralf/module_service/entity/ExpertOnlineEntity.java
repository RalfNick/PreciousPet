package com.ralf.module_service.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineEntity
 * @email -
 * @date 2019/06/25 14:50
 **/
public class ExpertOnlineEntity {

    private int seekHelpPages;
    private int seekHelpTotal;
    private List<ExpertExcelListBean> expertExcelList;
    private List<ExpertsListBean> expertsList;
    private List<PetTypeListBean> petTypeList;
    private List<SeekHelpsListBean> seekHelpsList;

    public int getSeekHelpPages() {
        return seekHelpPages;
    }

    public void setSeekHelpPages(int seekHelpPages) {
        this.seekHelpPages = seekHelpPages;
    }

    public int getSeekHelpTotal() {
        return seekHelpTotal;
    }

    public void setSeekHelpTotal(int seekHelpTotal) {
        this.seekHelpTotal = seekHelpTotal;
    }

    public List<ExpertExcelListBean> getExpertExcelList() {
        return expertExcelList;
    }

    public void setExpertExcelList(List<ExpertExcelListBean> expertExcelList) {
        this.expertExcelList = expertExcelList;
    }

    public List<ExpertsListBean> getExpertsList() {
        return expertsList;
    }

    public void setExpertsList(List<ExpertsListBean> expertsList) {
        this.expertsList = expertsList;
    }

    public List<PetTypeListBean> getPetTypeList() {
        return petTypeList;
    }

    public void setPetTypeList(List<PetTypeListBean> petTypeList) {
        this.petTypeList = petTypeList;
    }

    public List<SeekHelpsListBean> getSeekHelpsList() {
        return seekHelpsList;
    }

    public void setSeekHelpsList(List<SeekHelpsListBean> seekHelpsList) {
        this.seekHelpsList = seekHelpsList;
    }

    public static class ExpertExcelListBean {
        /**
         * excelId : 1
         * excelName : 医疗
         */

        private int excelId;
        private String excelName;

        public int getExcelId() {
            return excelId;
        }

        public void setExcelId(int excelId) {
            this.excelId = excelId;
        }

        public String getExcelName() {
            return excelName;
        }

        public void setExcelName(String excelName) {
            this.excelName = excelName;
        }
    }

    public static class ExpertsListBean {
        /**
         * expertId : 1
         * headPortrait : http://192.168.1.139:8090/upload/image/userPic/dff115b6d39d40c8b62b145752eae812.jpg
         * type : 1
         * userId : 16451
         * userName : 西门庆
         */

        private int expertId;
        private String headPortrait;
        private int type;
        private int userId;
        private String userName;

        public int getExpertId() {
            return expertId;
        }

        public void setExpertId(int expertId) {
            this.expertId = expertId;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class PetTypeListBean {
        /**
         * petId : 0
         * typeName : 全部
         */

        private int petId;
        private String typeName;

        public int getPetId() {
            return petId;
        }

        public void setPetId(int petId) {
            this.petId = petId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

    public static class SeekHelpsListBean {
        /**
         * answerTotal : 0
         * createTime : 1419574725
         * headPortrait : http://192.168.1.139:8090/upload/image/userPic/12.jpg
         * isImg : 1
         * isNew : 1
         * isPlacedTop : 1
         * nickName : 浅梦沫汐
         * seekHelpId : 1
         * title : 拉稀
         * userId : 1
         */

        private int answerTotal;
        private String createTime;
        private String headPortrait;
        private int isImg;
        private int isNew;
        private int isPlacedTop;
        private String nickName;
        private int seekHelpId;
        private String title;
        private int userId;

        public int getAnswerTotal() {
            return answerTotal;
        }

        public void setAnswerTotal(int answerTotal) {
            this.answerTotal = answerTotal;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getIsImg() {
            return isImg;
        }

        public void setIsImg(int isImg) {
            this.isImg = isImg;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public int getIsPlacedTop() {
            return isPlacedTop;
        }

        public void setIsPlacedTop(int isPlacedTop) {
            this.isPlacedTop = isPlacedTop;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getSeekHelpId() {
            return seekHelpId;
        }

        public void setSeekHelpId(int seekHelpId) {
            this.seekHelpId = seekHelpId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

}
