package com.bushemi.model;

import com.bushemi.model.entity.MessageDto;

import java.time.LocalDateTime;

/**
 * Created by igor on 24.10.17.
 * @Version 1.0
 * Utility class for jsp
 */
public class MessageInfo {
    private long id;
    private String content;
    private LocalDateTime timeSent;
    private String nicknamePersonFrom;
    private String nicknamePersonTo;

    public MessageInfo() {
    }

    public MessageInfo(long id, String content, LocalDateTime timeSent,
                       String nicknamePersonFrom, String nicknamePersonTo) {
        this.id = id;
        this.content = content;
        this.timeSent = timeSent;
        this.nicknamePersonFrom = nicknamePersonFrom;
        this.nicknamePersonTo = nicknamePersonTo;
    }
    public MessageInfo(MessageDto message) {
        this(message.getId(), message.getContent(), message.getTimeSent(),
                message.getPersonFrom().getNickname(), message.getPersonTo().getNickname());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public String getNicknamePersonFrom() {
        return nicknamePersonFrom;
    }

    public void setNicknamePersonFrom(String nicknamePersonFrom) {
        this.nicknamePersonFrom = nicknamePersonFrom;
    }

    public String getNicknamePersonTo() {
        return nicknamePersonTo;
    }

    public void setNicknamePersonTo(String nicknamePersonTo) {
        this.nicknamePersonTo = nicknamePersonTo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageInfo{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", timeSent=").append(timeSent);
        sb.append(", nicknamePersonFrom='").append(nicknamePersonFrom).append('\'');
        sb.append(", nicknamePersonTo='").append(nicknamePersonTo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
