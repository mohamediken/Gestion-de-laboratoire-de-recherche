package com.iken.Labo.controller;


import com.iken.Labo.model.Project;
import com.iken.Labo.model.Publication;
import com.iken.Labo.repository.ProjectRepository;
import com.iken.Labo.repository.PublicationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Controller
@RequestMapping("/publications")
public class PublicationController {

    private final PublicationRepository publicationRepository;
    private final ProjectRepository projectRepository;

    public PublicationController(PublicationRepository publicationRepository, ProjectRepository projectRepository) {
        this.publicationRepository = publicationRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/list")
    public String listPublications(Model model) {

        List<Publication> publications = publicationRepository.findAll();
        model.addAttribute("publications", publications);
        return "Publication/list-pub";
    }

    @GetMapping("/add")
    public String showAddPublicationForm(ModelMap model) {
        model.addAttribute("publications", new Publication());
        model.addAttribute("projects", projectRepository.findAll());
        return "Publication/add-pub";

    }
    /*
    @PostMapping("/add")
    public String addPublication(@ModelAttribute("publication") Publication publication) {
        publicationRepository.save(publication);
        return "redirect:/admin/publications/list";
    }
*/
    @PostMapping("/add")
    public String addPublication(@ModelAttribute("publication") Publication publication) {
        Long projectId = publication.getProject().getId();
        Project project = projectRepository.findById(projectId).orElse(null); // Récupérer le projet par son ID
        publication.setProject(project); // Associer le projet à la publication
        publicationRepository.save(publication);
        return "redirect:/publications/list";
    }

}
