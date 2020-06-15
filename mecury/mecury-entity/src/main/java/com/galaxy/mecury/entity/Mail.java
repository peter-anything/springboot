package com.galaxy.mecury.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Auther: peter
 * @Date: 2020/6/14 23:28
 * @Description:
 */
public class Mail {
    private String to;

    private String title;

    private String content;

    private String msgId;

    public Mail() {

    }

    public Mail(String to, String title, String content, String msgId) {
        this.to = to;
        this.title = title;
        this.content = content;
        this.msgId = msgId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "to='" + to + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
