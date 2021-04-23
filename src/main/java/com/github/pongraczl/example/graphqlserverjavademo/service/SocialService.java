package com.github.pongraczl.example.graphqlserverjavademo.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SocialService {

    private List<Map<String, String>> humans = Arrays.asList(
            ImmutableMap.of("id", "human-1",
                    "name", "Carl the Other",
                    "profession", ""),
            ImmutableMap.of("id", "human-2",
                    "name", "Sarah Connor",
                    "profession", "waitress")
    );

    public List<Map<String, String>> getHumans() {
        return humans;
    }
}
