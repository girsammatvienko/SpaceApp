package com.example.spaceapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "masters")
@Data
@NoArgsConstructor
public class Master {
    public Master(String name, int age, List<Planet> planets) {
        this.name = name;
        this.age = age;
        this.planets = planets;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "master")
    private List<Planet> planets;

    public Master(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }
}
