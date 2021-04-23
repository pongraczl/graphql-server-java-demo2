package com.github.pongraczl.example.graphqlserverjavademo.service;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SocialService {

    private static List<Map<String, String>> humans = Arrays.asList(
            ImmutableMap.of("id", "human-1",
                    "name", "Bill the Brave",
                    "profession", "butcher"),
            ImmutableMap.of("id", "human-2",
                    "name", "Sarah Connor",
                    "profession", "")
    );

    public static List<Map<String, String>> getHumans() {
        return humans;
    }
}
