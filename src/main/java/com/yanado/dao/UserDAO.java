package com.yanado.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dto.User;

@Service
public class UserDAO {

    @PersistenceContext
    public EntityManager em;

    @Transactional
    public int createUser(User user) {
        try {
            em.persist(user);
        } catch (DataAccessException e) {
            return 0;
        }

        return 1;
    }

    @Transactional
    public int updateUser(User user) {
        try {
            em.merge(user);
        } catch (DataAccessException e) {
            return 0;
        }

        return 1;
    }

    @Transactional
    public int deleteUser(String userId) {
        try {
            User user = em.find(User.class, userId);

            if (user == null)
                return 0;

            em.remove(user);
        } catch (DataAccessException e) {
            return 0;
        }

        return 1;
    }

    public User getUserByUserId(String userId) throws DataAccessException {
        User result;
        TypedQuery<User> query;
        try {
            query = em.createNamedQuery("getUserByUserId", User.class);
            query.setParameter("id", userId);
            result = (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

        return result;
    }

    public User getUserByUserName(String userName) throws DataAccessException {
        User result;
        TypedQuery<User> query;
        try {
            query = em.createNamedQuery("getUserByUserName", User.class);
            query.setParameter("userName", userName);
            result = (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

        return result;
    }

    public int login(String userId, String password) throws DataAccessException {
        User result;
        TypedQuery<User> query;
        try {
            query = em.createNamedQuery("login", User.class);
            query.setParameter("id", userId);
            query.setParameter("password", password);

            result = (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return 0;
        }

        if (result == null)
            return 0;

        return 1;
    }

}
