package com.example.spaceapp.api;

import com.example.spaceapp.entity.Master;
import com.example.spaceapp.entity.MasterPlanetPair;
import com.example.spaceapp.entity.exception.MasterNotExistsException;
import com.example.spaceapp.entity.exception.PlanetAlreadyTakenException;
import com.example.spaceapp.entity.exception.PlanetNotExistsException;
import com.example.spaceapp.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/master")
public class MasterControllerApi {

    private SpaceService service;

    @Autowired
    public MasterControllerApi(SpaceService service) {
        this.service = service;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Master addMaster(@RequestBody Master master) {
        service.addMaster(master);
        return master;
    }

    @PostMapping(value = "/assign", consumes = "application/json")
    public void assignMasterToPlanet(@RequestBody MasterPlanetPair pair) throws MasterNotExistsException, PlanetNotExistsException, PlanetAlreadyTakenException {
        service.assignMasterToPlanet(pair);
    }

    @GetMapping("/youngest")
    public List<Master> getTenYoungestMasters(Model model) {
        return service.getTenYoungestMasters();
    }

}
