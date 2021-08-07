package com.example.spaceapp.service;

import com.example.spaceapp.entity.Master;
import com.example.spaceapp.entity.MasterPlanetPair;
import com.example.spaceapp.entity.Planet;
import com.example.spaceapp.entity.exception.MasterNotExistsException;
import com.example.spaceapp.entity.exception.PlanetAlreadyTakenException;
import com.example.spaceapp.entity.exception.PlanetNotExistsException;
import com.example.spaceapp.repository.MasterRepository;
import com.example.spaceapp.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@org.springframework.stereotype.Service
public class SpaceService {
    private MasterRepository masterRepository;
    private PlanetRepository planetRepository;

    @Autowired
    public SpaceService(MasterRepository masterRepository, PlanetRepository planetRepository) {
        this.masterRepository = masterRepository;
        this.planetRepository = planetRepository;
    }

    public void addMaster(Master master) {
        masterRepository.save(master);
    }

    public void addPlanet(Planet planet) {
        planetRepository.save(planet);
    }

    public void assignMasterToPlanet(MasterPlanetPair pair) throws MasterNotExistsException, PlanetNotExistsException, PlanetAlreadyTakenException {
        String masterName = pair.getMasterName();
        String planetName = pair.getPlanetName();
        if(!masterRepository.existsByName(masterName)) throw new MasterNotExistsException();
        if(!planetRepository.existsByName(planetName)) throw new PlanetNotExistsException();

        Master master = masterRepository.findByName(masterName);
        Planet planet = planetRepository.findByName(planetName);

        if(planet.getMaster() != null) throw new PlanetAlreadyTakenException();

        planet.setMaster(master);
        master.addPlanet(planet);

        planetRepository.save(planet);
        masterRepository.save(master);
    }

    public Planet destroyPlanetByItsName(String name) throws PlanetNotExistsException {
        if(!planetRepository.existsByName(name)) throw new PlanetNotExistsException();
        Planet planet = planetRepository.findByName(name);
        planetRepository.delete(planet);
        return planet;
    }

    public Planet destroyPlanetByItsId(Long id) throws PlanetNotExistsException {
        if(!planetRepository.existsById(id)) throw new PlanetNotExistsException();
        Planet planet = planetRepository.findById(id).get();
        planetRepository.delete(planet);
        return planet;
    }

    public List<Master> getSlackers() {
        return masterRepository.findAllByPlanets_Empty();
    }

    public List<Master> getTenYoungestMasters() {
        List<Master> masters = (List<Master>) masterRepository.findAll();

        masters.stream()
                .sorted(new Comparator<Master>() {
                    @Override
                    public int compare(Master o1, Master o2) {
                        return o1.getAge() - o2.getAge();
                    }
                });

        int range = masters.size() < 10? masters.size(): 10;
        return masters.subList(0, range);
    }

    public List<Master> getAllMasters() {
        return (List<Master>) masterRepository.findAll();
    }

    public List<Planet> getAllPlanets() { return (List<Planet>) planetRepository.findAll(); }

    public Master getMaster(Long id) {
        return masterRepository.findById(id).get();
    }

    public Planet getPlanet(Long id) { return planetRepository.findById(id).get(); }




}
