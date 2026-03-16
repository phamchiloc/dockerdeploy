package com.example.phamchiloc.content;

public class Skill {
    private String name;
    private int level;
    private String desc;

    public Skill() {
    }

    public Skill(String name, int level, String desc) {
        this.name = name;
        this.level = level;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
