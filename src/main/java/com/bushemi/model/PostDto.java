package com.bushemi.model;

import java.time.LocalDateTime;
import java.util.Objects;


public class PostDto {

    private long id = -1;
    private String title;
    private String content;
    private LocalDateTime placeTime;
    private PersonDto owner;

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

    public PersonDto getOwner() {
        return owner;
    }

    public void setOwner(PersonDto person) {
        this.owner = person;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        PostDto postDto = (PostDto) o;
        return id == postDto.id &&
                Objects.equals(title, postDto.title) &&
                Objects.equals(content, postDto.content) &&
                Objects.equals(placeTime, postDto.placeTime) &&
                Objects.equals(owner, postDto.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, placeTime, owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", placeTime=").append(placeTime);
        sb.append(", owner=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
