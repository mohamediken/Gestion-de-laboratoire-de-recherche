package com.iken.Labo.controller;


import com.iken.Labo.model.Project;
import com.iken.Labo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;



@Controller
@RequestMapping("/projects") // L'URL pour la gestion des projets par l'administrateur

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public String listProjects(org.springframework.ui.Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "project/list-project"; // Vue Thymeleaf appropriée pour la liste des projets
    }



    @GetMapping("/add")
    public String showAddProjectForm(Project project) {
        return "project/add-project"; // Page Thymeleaf correspondante pour le formulaire d'ajout de projet
    }

    @PostMapping("/add")
    public String addProject(Project project) {
        // Ajouter un projet à la base de données
        projectService.createProject(project);
        return "redirect:/projects/list";
    }

    @GetMapping("/edit/{projectId}")
    public String editProjectForm(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        return "project/edit-project"; // Page Thymeleaf correspondante pour le formulaire de modification de projet
    }

    @PostMapping("/edit/{projectId}")
    public String editProject(@PathVariable Long projectId, @ModelAttribute Project updatedProject) {
        // Mettez à jour les informations du projet et redirigez vers la page de détails
        Project existingProject = projectService.getProjectById(projectId);
        if (existingProject != null) {
            existingProject.setTitle(updatedProject.getTitle());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setStartDate(updatedProject.getStartDate());
            existingProject.setEndDate(updatedProject.getEndDate());
            // ... d'autres attributs

            projectService.updateProject(existingProject);
        }
        return "redirect:/projects/list";
    }


    @GetMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
        // Supprimer un projet par ID
        projectService.deleteProject(projectId);
        return "redirect:/projects/list";
    }








}
