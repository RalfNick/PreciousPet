package com.ralf.module_db.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MessageRemindEntity
 * @email -
 * @date 2018/12/14 上午10:55
 **/
@Entity
public class MessageRemindEntity {

    /**
     * comment : 1
     * informationPush : 0
     * notification : 0
     * notifyDisplayMessages : 0
     * praise : 0
     * preventTrouble : 1
     * privateChat : 0
     * shake : 0
     * sound : 0
     * systemNotifications : 0
     */

    private int comment;
    private int informationPush;
    private int notification;
    private int notifyDisplayMessages;
    private int praise;
    private int preventTrouble;
    private int privateChat;
    private int shake;
    private int sound;
    private int systemNotifications;

    @Generated(hash = 1888502008)
    public MessageRemindEntity(int comment, int informationPush, int notification,
            int notifyDisplayMessages, int praise, int preventTrouble,
            int privateChat, int shake, int sound, int systemNotifications) {
        this.comment = comment;
        this.informationPush = informationPush;
        this.notification = notification;
        this.notifyDisplayMessages = notifyDisplayMessages;
        this.praise = praise;
        this.preventTrouble = preventTrouble;
        this.privateChat = privateChat;
        this.shake = shake;
        this.sound = sound;
        this.systemNotifications = systemNotifications;
    }

    @Generated(hash = 844091205)
    public MessageRemindEntity() {
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getInformationPush() {
        return informationPush;
    }

    public void setInformationPush(int informationPush) {
        this.informationPush = informationPush;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public int getNotifyDisplayMessages() {
        return notifyDisplayMessages;
    }

    public void setNotifyDisplayMessages(int notifyDisplayMessages) {
        this.notifyDisplayMessages = notifyDisplayMessages;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getPreventTrouble() {
        return preventTrouble;
    }

    public void setPreventTrouble(int preventTrouble) {
        this.preventTrouble = preventTrouble;
    }

    public int getPrivateChat() {
        return privateChat;
    }

    public void setPrivateChat(int privateChat) {
        this.privateChat = privateChat;
    }

    public int getShake() {
        return shake;
    }

    public void setShake(int shake) {
        this.shake = shake;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public int getSystemNotifications() {
        return systemNotifications;
    }

    public void setSystemNotifications(int systemNotifications) {
        this.systemNotifications = systemNotifications;
    }

    @Override
    public String toString() {
        return "MessageRemindEntity{" +
                "comment=" + comment +
                ", informationPush=" + informationPush +
                ", notification=" + notification +
                ", notifyDisplayMessages=" + notifyDisplayMessages +
                ", praise=" + praise +
                ", preventTrouble=" + preventTrouble +
                ", privateChat=" + privateChat +
                ", shake=" + shake +
                ", sound=" + sound +
                ", systemNotifications=" + systemNotifications +
                '}';
    }
}
