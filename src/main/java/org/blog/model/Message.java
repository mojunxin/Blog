package org.blog.model;

import java.util.UUID;

public class Message {
    private String id;
    private String createDate;
    private String userId;
    private String username;
    private String whisperId;
    private String paperId;
    private String classCode;
    private String pic;
    private String content;
    private String num;

    public Message() {
    }

    public String getId() {
        return id == null?String.valueOf(UUID.randomUUID()):id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getWhisperId() {
        return whisperId;
    }

    public void setWhisperId(String whisperId) {
        this.whisperId = whisperId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
