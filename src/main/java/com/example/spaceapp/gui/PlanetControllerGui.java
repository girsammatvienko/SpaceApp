package com.example.spaceapp.gui;

import com.example.spaceapp.entity.Planet;
import com.example.spaceapp.entity.exception.PlanetNotExistsException;
import com.example.spaceapp.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gui/planet")
public class PlanetControllerGui {
    private SpaceService service;

    @Autowired
    public PlanetControllerGui(SpaceService service) {
        this.service = service;
    }

    @RequestMapping("/getAll")
    public String getPlanets(Model model) {
        model.addAttribute("planets", service.getAllPlanets());
        return "planets/show-planets";
    }

    @RequestMapping("/add")
    public String addPlanet(Model model) {
        model.addAttribute("planet", new Planet());
        return "planets/add-planet";
    }

    @RequestMapping("/create")
    public String createPlanet(@ModelAttribute("planet") Planet planet) {
        service.addPlanet(planet);
        return "redirect:/gui/planet/getAll";
    }

    @RequestMapping("{id}")
    public String getPlanet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("planet", service.getPlanet(id));
        return "planets/show-planet";
    }

    @RequestMapping("/destroy/{id}")
    public String destroyPlanet(@PathVariable("id") Long id) throws PlanetNotExistsException {
        service.destroyPlanetByItsId(id);
        return "redirect:/gui/planet/getAll";
    }
}
