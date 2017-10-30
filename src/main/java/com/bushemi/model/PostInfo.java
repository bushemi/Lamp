package com.bushemi.model;

import com.bushemi.model.entity.PostDto;

import java.time.LocalDateTime;

/**
 * Created by igor on 24.10.17.
 * @Version 1.0
 * Utility class for jsp
 */
public class PostInfo {
    private long id;
    private String title;
    private String content;
    private LocalDateTime placeTime;
    private String ownerNickname;
    private int countLikes = 0;

    public PostInfo() {
    }

    public PostInfo(PostDto post){
        this(post.getId(), post.getTitle(),
                post.getContent(), post.getPlaceTime(),
                post.getOwner().getNickname());
    }

    public PostInfo(PostDto post, int countLikes){
        this(post);
        this.countLikes = countLikes;
    }

    public PostInfo(long id, String title, String content, LocalDateTime placeTime, String ownerNickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.placeTime = placeTime;
        this.ownerNickname = ownerNickname;
    }

    public PostInfo(long id, String title, String content, LocalDateTime placeTime, String ownerNickname, int countLikes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.placeTime = placeTime;
        this.ownerNickname = ownerNickname;
        this.countLikes = countLikes;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



    public LocalDateTime getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(LocalDateTime placeTime) {
        this.placeTime = placeTime;
    }

    public String getOwnerNickname() {
        return ownerNickname;
    }

    public void setOwnerNickname(String ownerNickname) {
        this.ownerNickname = ownerNickname;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostInfo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", placeTime=").append(placeTime);
        sb.append(", ownerNickname='").append(ownerNickname).append('\'');
        sb.append(", countLikes=").append(countLikes);
        sb.append('}');
        return sb.toString();
    }
}
