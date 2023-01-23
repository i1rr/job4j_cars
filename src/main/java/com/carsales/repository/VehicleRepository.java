package com.carsales.repository;

import com.carsales.model.Vehicle;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class VehicleRepository {
    public final CrudRepository crudRepository;

    public void create(Vehicle vehicle) {
        crudRepository.run(session -> session.persist(vehicle));
    }

    public void update(Vehicle vehicle) {
        crudRepository.run(session -> session.merge(vehicle));
    }

    public void delete(int vehicleId) {
        crudRepository.run("delete from Vehicle where id = :fId", Map.of("fId", vehicleId));
    }

    public Optional<Vehicle> findById(int id) {
        return crudRepository.optional("from Vehicle where id = :fId", Vehicle.class, Map.of("fId", id));
    }

    public List<Vehicle> findByName(String name) {
        return crudRepository.query("from Vehicle where name = :fName", Vehicle.class, Map.of("fName", name));
    }

    public List<Vehicle> findByLikeName(String key) {
        return crudRepository.query("from Vehicle where name like :fKey", Vehicle.class, Map.of("fKey", "%" + key + "%"));
    }

    public List<Vehicle> findAllOrderById() {
        return crudRepository.query("from Vehicle order by id asc", Vehicle.class);
    }
}
