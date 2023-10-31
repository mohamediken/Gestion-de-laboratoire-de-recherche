package com.iken.Labo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {



    @GetMapping("/login")
    public String maPage(Model model) {
        model.addAttribute("message", "Bienvenue sur ma page !");
        return "dashboards/login";
    }

    @GetMapping("/notAuthorized")
    public String accessDenied() {
        return "dashboards/notAuthorized"; // This should match the name of your HTML file without the file extension
    }
}
