package com.iken.Labo.controller;

import com.iken.Labo.model.Member;
import com.iken.Labo.model.Project;
import com.iken.Labo.model.Publication;
import com.iken.Labo.service.MemberService;
import com.iken.Labo.service.ProjectService;
import com.iken.Labo.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// DashboardController.java
@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PublicationService publicationService;


    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Project> ongoingProjects = projectService.getOngoingProjects();
        List<Member> members = memberService.getAllMembers();
        List<Publication> recentPublications = publicationService.getRecentPublications();
        List<Project> Project = projectService.getAllProjects();

        model.addAttribute("Project", Project);
        model.addAttribute("ongoingProjects", ongoingProjects);
        model.addAttribute("members", members);
        model.addAttribute("recentPublications", recentPublications);

        return "dashboards/dashboard";
    }


}



