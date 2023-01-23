package com.carsales.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    @OneToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "vehicle_engine_id_fkey"))
    private Engine engine;
    @ManyToOne
    @JoinTable(name = "vehicle_history", joinColumns = {
            @JoinColumn(name = "vehicle_id", foreignKey = @ForeignKey(name = "vehicle_driver_vehicle_id_fkey"))},
            inverseJoinColumns = {
            @JoinColumn(name = "driver_id", foreignKey = @ForeignKey(name = "vehicle_driver_driver_id_fkey"))})
    private Driver driver;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "vehicle_history", joinColumns = {
            @JoinColumn(name = "vehicle_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Driver> drivers = new HashSet<>();
}

