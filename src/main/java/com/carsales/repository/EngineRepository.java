package com.carsales.repository;

import com.carsales.model.Engine;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;

    public void create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }

    public void update(Engine engine) {
        crudRepository.run(session -> session.merge(engine));
    }

    public void delete(int engineId) {
        crudRepository.run("delete from Engine where id = :fId", Map.of("fId", engineId));
    }

    public Optional<Engine> findById(int id) {
        return crudRepository.optional("from Engine where id = :fId", Engine.class, Map.of("fId", id));
    }

    public List<Engine> findByName(String name) {
        return crudRepository.query("from Engine where name = :fName", Engine.class, Map.of("fName", name));
    }

    public List<Engine> findAllOrderById() {
        return crudRepository.query("from Engine order by id asc", Engine.class);
    }

    public List<Engine> findByLikeName(String key) {
        return crudRepository.query("from Engine where name like :fKey", Engine.class, Map.of("fKey", "%" + key + "%"));
    }
}
