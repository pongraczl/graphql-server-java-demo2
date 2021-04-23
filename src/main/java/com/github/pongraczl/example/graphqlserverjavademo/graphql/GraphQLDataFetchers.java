package com.github.pongraczl.example.graphqlserverjavademo.graphql;

import com.github.pongraczl.example.graphqlserverjavademo.service.ApocalypseService;
import com.github.pongraczl.example.graphqlserverjavademo.service.SocialService;
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

    public DataFetcher getHumanByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String humanId = dataFetchingEnvironment.getArgument("id");
            return socialService.getHumans()
                    .stream()
                    .filter(human -> human.get("id").equals(humanId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
