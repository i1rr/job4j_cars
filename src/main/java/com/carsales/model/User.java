package com.carsales.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "auto_user")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String login;
    private String password;
}