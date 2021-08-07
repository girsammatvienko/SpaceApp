package com.example.spaceapp.api;

import com.example.spaceapp.entity.Planet;
import com.example.spaceapp.entity.exception.PlanetNotExistsException;
import com.example.spaceapp.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planet")
public class PlanetControllerApi {
    private SpaceService service;

    @Autowired
    public PlanetControllerApi(SpaceService service) {
        this.service = service;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Planet addPlanet(@RequestBody Planet planet) {
        service.addPlanet(planet);
        return planet;
    }

    @PostMapping(value = "/destroy")
    public Planet destroyPlanet(@RequestBody String name) throws PlanetNotExistsException {
        Planet destroyedPlanet = service.destroyPlanetByItsName(name);
        return destroyedPlanet;
    }
}
