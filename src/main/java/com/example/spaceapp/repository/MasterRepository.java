package com.example.spaceapp.repository;

import com.example.spaceapp.entity.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface MasterRepository extends CrudRepository<Master, Long> {
    boolean existsByName(String name);

    Master findByName(String name);

    List<Master> findAllByPlanets_Empty();


}
