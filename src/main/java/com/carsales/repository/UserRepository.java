package com.carsales.repository;

import com.carsales.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    public User create(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return user;
    }

    public void update(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void delete(int userId) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<User> findAllOrderById() {
        var session = sf.openSession();
        session.beginTransaction();
        var result = session.createQuery("from User order by id", User.class).list();
        session.getTransaction().commit();
        session.close();

        return result;
    }

    public Optional<User> findById(int id) {
        var session = sf.openSession();
        session.beginTransaction();
        var result = session.createQuery("from User where id = :fId", User.class)
                .setParameter("fId", id).uniqueResultOptional();
        session.close();

        return result;
    }

    public List<User> findByLikeLogin(String key) {
        var session = sf.openSession();
        session.beginTransaction();
        var result = session.createQuery("from User where login like :fKey", User.class)
                .setParameter("fKey", key).list();
        session.close();

        return result;
    }

    public Optional<User> findByLogin(String login) {
        var session = sf.openSession();
        session.beginTransaction();
        var result = session.createQuery("from User where login = :fLogin", User.class)
                .setParameter("fLogin", login).uniqueResultOptional();
        session.close();

        return result;
    }
}