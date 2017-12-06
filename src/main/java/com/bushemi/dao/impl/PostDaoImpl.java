package com.bushemi.dao.impl;

import com.bushemi.converters.EntityDtoConverter;
import com.bushemi.dao.PostDao;
import com.bushemi.dao.entity.Person;
import com.bushemi.dao.entity.Post;
import com.bushemi.model.entity.PersonDto;
import com.bushemi.model.entity.PostDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by igor on 01.09.17.
 * useless comment
 */
@Repository
public class PostDaoImpl implements PostDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PostDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Long create(PostDto entity) {
        Post post = EntityDtoConverter.convert(entity);
        Session session = sessionFactory.getCurrentSession();
        session.merge(post.getOwner());
        session.save(post);
        return post.getId();
    }

    @Override
    public PostDto findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, id);
        return EntityDtoConverter.convert(post);
    }

    @Override
    public void delete(PostDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, entity.getId());
        session.delete(post);
    }

    @Override
    public PostDto update(PostDto entity) {
        Session session = sessionFactory.getCurrentSession();
        Post post = EntityDtoConverter.convert(entity);
        session.merge(post);
        return EntityDtoConverter.convert(post);
    }

    @Override
    public Collection<PostDto> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Post> posts;
        posts = session.createQuery("from com.bushemi.dao.entity.Post").list();
        return posts.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Collection<PostDto> findPersonsPosts(PersonDto person) {
        Session session = sessionFactory.getCurrentSession();
        Person entity = EntityDtoConverter.convert(person);
        session.merge(entity);
        return findPostsByOwnerId(entity.getId());
    }

    @Override
    public Collection<PersonDto> findPostLikers(PostDto post) {
        Post post1 = EntityDtoConverter.convert(post);
        Session session = sessionFactory.getCurrentSession();
        post1 = (Post) session.get(Post.class, post1.getId());
        return post1.getPostLikers().stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Collection<PostDto> findPostsByOwnerId(long id) {
        Session session = sessionFactory.getCurrentSession();
        List<Post> posts = session.createQuery("select p from com.bushemi.dao.entity.Post p where p.owner.id = :owner")
                .setParameter("owner", id)
                .list();

        return posts.stream().map(EntityDtoConverter::convert).collect(Collectors.toList());
    }

}
