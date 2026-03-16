package com.example.phamchiloc.controller;

import com.example.phamchiloc.content.SiteContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkillsController {

    private final SiteContentService siteContentService;

    public SkillsController(SiteContentService siteContentService) {
        this.siteContentService = siteContentService;
    }

    @GetMapping("/skills")
    public String skills(Model model) {
        var c = siteContentService.getContent();
        model.addAttribute("name", c.getName());
        model.addAttribute("tagline", c.getTagline());
        model.addAttribute("skills", c.getSkills());
        return "skills";
    }
}
