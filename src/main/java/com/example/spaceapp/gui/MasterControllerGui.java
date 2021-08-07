package com.example.spaceapp.gui;

import com.example.spaceapp.entity.Master;
import com.example.spaceapp.entity.MasterPlanetPair;
import com.example.spaceapp.entity.exception.MasterNotExistsException;
import com.example.spaceapp.entity.exception.PlanetAlreadyTakenException;
import com.example.spaceapp.entity.exception.PlanetNotExistsException;
import com.example.spaceapp.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gui/master")
public class MasterControllerGui {

    private SpaceService service;

    @Autowired
    public MasterControllerGui(SpaceService service) {
        this.service = service;
    }

    @RequestMapping("/add")
    public String addMaster(Model model) {
        model.addAttribute("master", new Master());
        return "masters/add-master";
    }

    @RequestMapping("/create")
    public String createMaster(@ModelAttribute("master") Master master) {
        service.addMaster(master);
        return "redirect:/gui/master/getAll";
    }

    @RequestMapping("/getAll")
    public String getMasters(Model model) {
        model.addAttribute("masters", service.getAllMasters());
        return "masters/show-masters";
    }

    @RequestMapping("/{id}")
    public String getMaster(@PathVariable("id") Long id, Model model) {
        model.addAttribute("master", service.getMaster(id));
        return "masters/show-master";
    }

    @RequestMapping("assign")
    public String assignMasterToPlanet(Model model) {
        model.addAttribute("pair", new MasterPlanetPair());
        return "masters/assign-master";
    }

    @RequestMapping("/confirmAssigning")
    public String confirmAssigning(@ModelAttribute("pair") MasterPlanetPair pair) throws MasterNotExistsException, PlanetNotExistsException, PlanetAlreadyTakenException {
        service.assignMasterToPlanet(pair);
        return "redirect:/gui/master/getAll";
    }

    @RequestMapping("/getSlackers")
    public String getSlackers(Model model) {
        model.addAttribute("slackers", service.getSlackers());
        return "masters/show-slackers";
    }

    @GetMapping("/getYoungest")
    public String getTenYoungestMasters(Model model) {
        model.addAttribute("tenYoungestMasters", service.getTenYoungestMasters());
        return "masters/show-youngest";
    }
}
