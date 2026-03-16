package com.example.phamchiloc.controller;

import com.example.phamchiloc.content.SiteContent;
import com.example.phamchiloc.content.SiteContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SiteContentService siteContentService;

    public HomeController(SiteContentService siteContentService) {
        this.siteContentService = siteContentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        SiteContent c = siteContentService.getContent();
        model.addAttribute("name", c.getName());
        model.addAttribute("tagline", c.getTagline());
        model.addAttribute("roleLine", c.getRoleLine());
        model.addAttribute("university", c.getUniversity());
        model.addAttribute("major", c.getMajor());
        model.addAttribute("github", c.getGithub());
        model.addAttribute("facebook", c.getFacebook());
        model.addAttribute("email", c.getEmail());
        model.addAttribute("projects", c.getProjects());
        model.addAttribute("skills", c.getSkills());
        return "index";
    }
}
