package com.project311.JM311.dao;

import com.project311.JM311.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void updateUser(int id, User newUser) {
        Query query = entityManager.createQuery("update User set email = :userEmail, name = :userName, surname = :userSurname where id = :userId");
        query.setParameter("userId", id);
        query.setParameter("userName", newUser.getName());
        query.setParameter("userSurname", newUser.getSurname());
        query.setParameter("userEmail", newUser.getEmail());
        query.executeUpdate();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id =:userID");
        query.setParameter("userID", id);
        query.executeUpdate();
    }

    @Override
    public void setUser(User user) {
        entityManager.persist(user);
    }
}
