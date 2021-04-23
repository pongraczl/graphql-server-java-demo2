package com.github.pongraczl.example.graphqlserverjavademo.service.model;

public class HumanInput {

    private String name;
    private String profession;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public HumanInput(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }
}
