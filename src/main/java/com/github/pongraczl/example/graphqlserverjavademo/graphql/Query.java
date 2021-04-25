package com.github.pongraczl.example.graphqlserverjavademo.graphql;

import com.github.pongraczl.example.graphqlserverjavademo.service.ApocalypseService;
import com.github.pongraczl.example.graphqlserverjavademo.service.SocialService;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private SocialService socialService;
    @Autowired
    private ApocalypseService apocalypseService;

    public Creature getSomebodyById(String id) {
        return socialService.getCreatureById(id);
    }

    public List<Creature> getAllHumans() {
        return socialService.getAllLiving();
    }

    public List<Creature> getAllZombies() {
        return apocalypseService.getZombies();
    }

    public List<Creature> getEveryBody() {
        return socialService.getEveryBody();
    }
}
