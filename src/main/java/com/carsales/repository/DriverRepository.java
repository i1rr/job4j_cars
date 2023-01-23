package com.carsales.repository;

import com.carsales.model.Driver;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class DriverRepository {
    private final CrudRepository crudRepository;

    public void create(Driver driver) {
        crudRepository.run(session -> session.persist(driver));
    }

    public void update(Driver driver) {
        crudRepository.run(session -> session.merge(driver));
    }

    public void delete(int driverId) {
        crudRepository.run("delete from Driver where id = :fId", Map.of("fId", driverId));
    }

    public Optional<Driver> findById(int id) {
        return crudRepository.optional("from Driver where id = :fId", Driver.class, Map.of("fId", id));
    }

    public List<Driver> findByName(String name) {
        return crudRepository.query("from Driver where name = :fName", Driver.class, Map.of("fName", name));
    }

    public List<Driver> findByLikeName(String key) {
        return crudRepository.query("from Driver where name like :fKey", Driver.class, Map.of("fKey", "%" + key + "%"));
    }

    public List<Driver> findAllOrderById() {
        return crudRepository.query("from Driver order by id asc", Driver.class);
    }
}
