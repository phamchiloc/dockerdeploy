package com.example.phamchiloc.controller;

import com.example.phamchiloc.content.SiteContent;
import com.example.phamchiloc.content.SiteContentService;
import com.example.phamchiloc.controller.dto.AdminForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final SiteContentService siteContentService;

    public AdminController(SiteContentService siteContentService) {
        this.siteContentService = siteContentService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        SiteContent c = siteContentService.getContent();
        model.addAttribute("form", AdminForm.from(c));
        return "admin";
    }

    @PostMapping("/admin")
    public String save(@ModelAttribute("form") AdminForm form, Model model) {
        SiteContent content = form.toSiteContent();
        siteContentService.save(content);

        model.addAttribute("saved", true);
        model.addAttribute("form", AdminForm.from(content));
        return "admin";
    }
}
