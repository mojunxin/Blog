package org.blog.model;

import java.util.List;
import java.util.UUID;

public class Whisper {
    private String id;
    private String userId;
    private String username;
    private String createDate;
    private String pic;
    private String classCode;
    private String content;
    private String isShow;
    private String clickNum;
    private String msgNum;

    private List<Message> messages;

    public Whisper() {
    }

    public String getId() {
        return id == null?String.valueOf(UUID.randomUUID()):id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getClickNum() {
        return clickNum;
    }

    public void setClickNum(String clickNum) {
        this.clickNum = clickNum;
    }

    public String getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(String msgNum) {
        this.msgNum = msgNum;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Whisper{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", createDate='" + createDate + '\'' +
                ", pic='" + pic + '\'' +
                ", classCode='" + classCode + '\'' +
                ", content='" + content + '\'' +
                ", isShow='" + isShow + '\'' +
                ", clickNum='" + clickNum + '\'' +
                ", msgNum='" + msgNum + '\'' +
                ", messages=" + messages +
                '}';
    }
}
