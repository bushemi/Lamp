package com.bushemi.dao.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "POST_LIKES")
public class PostLikes {


    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person liker;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    public Person getLiker() {
        return liker;
    }

    public void setLiker(Person liker) {
        this.liker = liker;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        PostLikes postLikes = (PostLikes) o;
        return Objects.equals(liker, postLikes.liker) &&
                Objects.equals(post, postLikes.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(liker, post);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostLikes{");
        sb.append("liker=").append(liker);
        sb.append(", post=").append(post);
        sb.append('}');
        return sb.toString();
    }
}
