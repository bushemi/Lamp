package com.bushemi.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name = "POST")
public class Post {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PLACE_TIME")
    private LocalDateTime placeTime;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "PERSON_ID")
    private Person owner;

    @ManyToMany(fetch = FetchType.LAZY ,mappedBy = "likes")
    Collection<Person> postLikers;


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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person person) {
        this.owner = person;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(placeTime, post.placeTime) &&
                Objects.equals(owner, post.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeTime, owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", placeTime=").append(placeTime);
        sb.append(", person=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
