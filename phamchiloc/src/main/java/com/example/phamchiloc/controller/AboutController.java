package com.example.phamchiloc.controller;

import com.example.phamchiloc.content.SiteContent;
import com.example.phamchiloc.content.SiteContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    private final SiteContentService siteContentService;

    public AboutController(SiteContentService siteContentService) {
        this.siteContentService = siteContentService;
    }

    @GetMapping("/about")
    public String about(Model model) {
        SiteContent c = siteContentService.getContent();

        model.addAttribute("tagline", c.getTagline());
        model.addAttribute("roleLine", c.getRoleLine());
        model.addAttribute("name", c.getName());
        model.addAttribute("dob", c.getDob());
        model.addAttribute("gender", c.getGender());
        model.addAttribute("hometown", c.getHometown());
        model.addAttribute("university", c.getUniversity());
        model.addAttribute("major", c.getMajor());
        model.addAttribute("course", c.getCourse());
        model.addAttribute("email", c.getEmail());
        model.addAttribute("github", c.getGithub());
        model.addAttribute("facebook", c.getFacebook());
        model.addAttribute("goal", c.getGoal());
        model.addAttribute("hobbies", c.getHobbies());
        model.addAttribute("projects", c.getProjects());

        return "about";
    }
}
