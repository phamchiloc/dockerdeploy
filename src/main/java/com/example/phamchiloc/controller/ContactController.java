package com.example.phamchiloc.controller;

import com.example.phamchiloc.content.SiteContent;
import com.example.phamchiloc.content.SiteContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    private final SiteContentService siteContentService;

    public ContactController(SiteContentService siteContentService) {
        this.siteContentService = siteContentService;
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        SiteContent c = siteContentService.getContent();
        model.addAttribute("tagline", c.getTagline());
        model.addAttribute("name", c.getName());
        model.addAttribute("email", c.getEmail());
        model.addAttribute("phone", c.getPhone());
        model.addAttribute("address", c.getAddress());
        model.addAttribute("github", c.getGithub());
        model.addAttribute("facebook", c.getFacebook());
        model.addAttribute("university", c.getUniversity());
        model.addAttribute("major", c.getMajor() + " - " + c.getCourse());
        return "contact";
    }
}
