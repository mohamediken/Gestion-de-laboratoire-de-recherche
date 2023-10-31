package com.iken.Labo.controller;

import com.iken.Labo.model.Ressource;
import com.iken.Labo.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/ressources")
public class RessourceController {

    @Autowired
    private RessourceService ressourceService;

    @GetMapping("/list")
    public String listRessources(Model model) {
        model.addAttribute("ressources", ressourceService.getAllRessources());
        return "Res/list-res";
    }

    @GetMapping("/add")
    public String showAddRessourceForm(Ressource ressource) {
        return "Res/add-res";
    }

    @PostMapping("/add")
    public String addRessource(Ressource ressource) {
        // Ajouter une ressource à la base de données
        ressourceService.createRessource(ressource);
        return "redirect:/ressources/list";
    }
    @GetMapping("/delete/{ressourceId}")
    public String deleteRessource(@PathVariable Long ressourceId) {
        // Supprimer une ressource par ID
        ressourceService.deleteRessource(ressourceId);
        return "redirect:/ressources/list";
    }

    @GetMapping("/edit/{ressourceId}")
    public String editRessourceForm(@PathVariable Long ressourceId, Model model) {
        Ressource ressource = ressourceService.getRessourceById(ressourceId);
        model.addAttribute("ressource", ressource);
        return "Res/edite-res";
    }

    @PostMapping("/edit/{ressourceId}")
    public String editRessource(@PathVariable Long ressourceId, @ModelAttribute Ressource updatedRessource) {
        Ressource existingRessource = ressourceService.getRessourceById(ressourceId);

        if (existingRessource != null) {
            existingRessource.setName(updatedRessource.getName());
            existingRessource.setDescription(updatedRessource.getDescription());
            existingRessource.setAvailable(updatedRessource.isAvailable()); // Si "available" est un booléen
            ressourceService.updateRessource(existingRessource);
        }
        return "redirect:/ressources/list";
    }

}
