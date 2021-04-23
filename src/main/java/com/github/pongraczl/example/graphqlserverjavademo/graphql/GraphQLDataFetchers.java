package com.github.pongraczl.example.graphqlserverjavademo.graphql;

import com.github.pongraczl.example.graphqlserverjavademo.service.ApocalypseService;
import com.github.pongraczl.example.graphqlserverjavademo.service.SocialService;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    @Autowired
    private SocialService socialService;

    public DataFetcher<Creature> getSomebodyByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return socialService.getCreatureById(id);
        };
    }

    public DataFetcher<List<Creature>> getAllHumans() {
        return dataFetchingEnvironment -> {
            return socialService.getAllLiving();
        };
    }

    public DataFetcher<List<Creature>> getAllCreatures() {
        return dataFetchingEnvironment -> {
            return socialService.getEveryBody();
        };
    }
}
