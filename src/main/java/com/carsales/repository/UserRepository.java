package com.carsales.repository;

import com.carsales.model.User;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final CrudRepository crudRepository;

    public User create(User user) {
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    public void delete(int userId) {
        crudRepository.run("delete from User where id = :fId", Map.of("fId", userId));
    }

    public List<User> findAllOrderById() {
        return crudRepository.query("from User order by id asc", User.class);
    }

    public Optional<User> findById(int id) {
        return crudRepository.optional("from User where id = :fId", User.class, Map.of("fId", id));
    }

    public List<User> findByLikeLogin(String key) {
        return crudRepository.query("from User where login like :fKey", User.class, Map.of("fKey", "%" + key + "%"));
    }

    public Optional<User> findByLogin(String login) {
        return crudRepository.optional("from User where login = :fLogin", User.class, Map.of("fLogin", login));
    }
}