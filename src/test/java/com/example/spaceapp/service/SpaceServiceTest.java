package com.example.spaceapp.service;

import com.example.spaceapp.entity.Master;
import com.example.spaceapp.entity.MasterPlanetPair;
import com.example.spaceapp.entity.Planet;
import com.example.spaceapp.entity.exception.MasterNotExistsException;
import com.example.spaceapp.entity.exception.PlanetAlreadyTakenException;
import com.example.spaceapp.entity.exception.PlanetNotExistsException;
import com.example.spaceapp.repository.MasterRepository;
import com.example.spaceapp.repository.PlanetRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpaceServiceTest {

    @MockBean
    private MasterRepository masterRepository;
    @MockBean
    private PlanetRepository planetRepository;

    @MockBean
    private SpaceService service;

    @Test
    public void addMasterWithValidFields() {
        service.addMaster(new Master("Master1", 100));
        verify(masterRepository).save(new Master("Master1", 100));
    }

    @Test
    public void assigningWithValidInputDataTest() throws MasterNotExistsException,
            PlanetNotExistsException, PlanetAlreadyTakenException {
        Master master = new Master();
        master.setName("Master1");
        master.setAge(100);
        Planet planet = new Planet();
        planet.setName("Earth");
        service.addMaster(master);
        service.addPlanet(planet);
        MasterPlanetPair pair = new MasterPlanetPair(master.getName(), planet.getName());
        masterRepository.findAll().forEach(p-> System.out.println(p.getName()));
        service.assignMasterToPlanet(pair);

        List<Planet> expectedPlanetsInOwning = new ArrayList<>();
        expectedPlanetsInOwning.add(planet);

        Assert.assertEquals(expectedPlanetsInOwning, service.getMaster(master.getId()).getPlanets());
    }

}
