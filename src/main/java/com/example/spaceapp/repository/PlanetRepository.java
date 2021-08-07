package com.example.spaceapp.repository;

import com.example.spaceapp.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("planetRepository")
public interface PlanetRepository extends CrudRepository<Planet, Long> {
    boolean existsByName(String name);

    Planet findByName(String name);

}
