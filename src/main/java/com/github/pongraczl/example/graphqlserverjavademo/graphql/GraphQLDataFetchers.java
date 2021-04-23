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

    public static final String ZOMBIE_APOCALYPSE_HAS_BEGUN = "Zombie Apocalypse has begun!";
    public static final String NEEDLESS_TO_START_ZOMBIE_APOCALYPSE = "Needless to start Zombie Apocalypse. It's already here!";
    @Autowired
    private SocialService socialService;
    @Autowired
    private ApocalypseService apocalypseService;

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

    public DataFetcher<String> startZombieApocalypseDataFetcher() {
        return dataFetchingEnvironment -> {
            boolean isApocalypseActivatedNow = apocalypseService.startZombieApocalypse();
            return isApocalypseActivatedNow ? ZOMBIE_APOCALYPSE_HAS_BEGUN : NEEDLESS_TO_START_ZOMBIE_APOCALYPSE;
        };
    }
}
