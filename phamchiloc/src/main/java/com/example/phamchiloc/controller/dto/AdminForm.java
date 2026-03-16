package com.example.phamchiloc.controller.dto;

import com.example.phamchiloc.content.Skill;
import com.example.phamchiloc.content.SiteContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminForm {
    private String name;
    private String tagline;
    private String roleLine;

    private String dob;
    private String gender;
    private String hometown;
    private String university;
    private String major;
    private String course;

    private String email;
    private String phone;
    private String address;

    private String github;
    private String facebook;

    private String goal;

    private String hobbiesText;
    private String projectsText;
    private String skillsText;

    public AdminForm() {
    }

    public static AdminForm from(SiteContent c) {
        AdminForm f = new AdminForm();
        f.setName(c.getName());
        f.setTagline(c.getTagline());
        f.setRoleLine(c.getRoleLine());

        f.setDob(c.getDob());
        f.setGender(c.getGender());
        f.setHometown(c.getHometown());
        f.setUniversity(c.getUniversity());
        f.setMajor(c.getMajor());
        f.setCourse(c.getCourse());

        f.setEmail(c.getEmail());
        f.setPhone(c.getPhone());
        f.setAddress(c.getAddress());

        f.setGithub(c.getGithub());
        f.setFacebook(c.getFacebook());

        f.setGoal(c.getGoal());

        f.setHobbiesText(String.join("\n", c.getHobbies() == null ? List.of() : c.getHobbies()));
        f.setProjectsText(String.join("\n", c.getProjects() == null ? List.of() : c.getProjects()));

        StringBuilder skillsBuilder = new StringBuilder();
        if (c.getSkills() != null) {
            for (Skill s : c.getSkills()) {
                if (skillsBuilder.length() > 0) {
                    skillsBuilder.append("\n");
                }
                skillsBuilder
                        .append(nullToEmpty(s.getName()))
                        .append("|")
                        .append(s.getLevel())
                        .append("|")
                        .append(nullToEmpty(s.getDesc()));
            }
        }
        f.setSkillsText(skillsBuilder.toString());
        return f;
    }

    public SiteContent toSiteContent() {
        SiteContent c = new SiteContent();

        c.setName(trimToNull(name));
        c.setTagline(trimToNull(tagline));
        c.setRoleLine(trimToNull(roleLine));

        c.setDob(trimToNull(dob));
        c.setGender(trimToNull(gender));
        c.setHometown(trimToNull(hometown));
        c.setUniversity(trimToNull(university));
        c.setMajor(trimToNull(major));
        c.setCourse(trimToNull(course));

        c.setEmail(trimToNull(email));
        c.setPhone(trimToNull(phone));
        c.setAddress(trimToNull(address));

        c.setGithub(trimToNull(github));
        c.setFacebook(trimToNull(facebook));

        c.setGoal(trimToNull(goal));

        c.setHobbies(splitLines(hobbiesText));
        c.setProjects(splitLines(projectsText));
        c.setSkills(parseSkills(skillsText));

        return c;
    }

    private static List<String> splitLines(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return Arrays.stream(text.split("\\R"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private static List<Skill> parseSkills(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }

        List<Skill> result = new ArrayList<>();
        String[] lines = text.split("\\R");
        for (String raw : lines) {
            String line = raw == null ? "" : raw.trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", 3);
            if (parts.length < 3) {
                continue;
            }

            String skillName = parts[0].trim();
            String levelText = parts[1].trim();
            String desc = parts[2].trim();

            int level = 0;
            try {
                level = Integer.parseInt(levelText);
            } catch (NumberFormatException ignored) {
            }

            result.add(new Skill(skillName, level, desc));
        }

        return result;
    }

    private static String trimToNull(String s) {
        if (s == null) {
            return null;
        }
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getRoleLine() {
        return roleLine;
    }

    public void setRoleLine(String roleLine) {
        this.roleLine = roleLine;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getHobbiesText() {
        return hobbiesText;
    }

    public void setHobbiesText(String hobbiesText) {
        this.hobbiesText = hobbiesText;
    }

    public String getProjectsText() {
        return projectsText;
    }

    public void setProjectsText(String projectsText) {
        this.projectsText = projectsText;
    }

    public String getSkillsText() {
        return skillsText;
    }

    public void setSkillsText(String skillsText) {
        this.skillsText = skillsText;
    }
}
