package com.ralf.pet_provider.http;

import java.io.Serializable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name BaseEntity
 * @email -
 * @date 2018/12/10 下午3:13
 **/
public class BaseEntity<T> implements Serializable {

    /**
     * code : 0
     * data : {"pages":1,"dynamicList":[{"id":2,"dynamicsPath":"http://192.168.1.139:8090/upload/image/petPic/d2.jpg","videoPrintscreen":"","talk":"璁颁綇璇ヨ浣忕殑锛屽繕璁拌蹇樿鐨勶紝鏀瑰彉鑳芥敼鍙樼殑锛屾帴鍙椾笉鑳芥敼鍙樼殑銆�","createTime":"1492136111","type":0,"high":600,"width":600},{"id":1,"dynamicsPath":"http://192.168.1.139:8090/upload/image/petPic/d1.jpg","videoPrintscreen":"","talk":"鍊樿嫢涓�鏃犳秷鎭紝濡傛矇鑸瑰悗闈欓潤鐨勬捣闈紝鍏跺疄涔熸槸闈欓潤鍦拌寰�","createTime":"1492136110","type":0,"high":300,"width":220}]}
     * message : 鎴愬姛
     */

    private Integer code;
    private T data;
    private String message;
    private boolean state = false;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", state=" + state +
                '}';
    }

    public boolean isSuccess() {
        return code == 0;
    }

}
