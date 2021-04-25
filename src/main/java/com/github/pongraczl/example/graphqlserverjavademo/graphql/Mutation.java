package com.github.pongraczl.example.graphqlserverjavademo.graphql;

import com.github.pongraczl.example.graphqlserverjavademo.service.ApocalypseService;
import com.github.pongraczl.example.graphqlserverjavademo.service.SocialService;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.CombatResult;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.HumanInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Mutation implements GraphQLMutationResolver {

    public static final String ZOMBIE_APOCALYPSE_HAS_BEGUN = "Zombie Apocalypse has begun!";
    public static final String NEEDLESS_TO_START_ZOMBIE_APOCALYPSE = "Needless to start Zombie Apocalypse. It's already here!";

    @Autowired
    private SocialService socialService;
    @Autowired
    private ApocalypseService apocalypseService;

    public String startZombieApocalypse() {
        boolean isApocalypseActivatedNow = apocalypseService.startZombieApocalypse();
        return isApocalypseActivatedNow ? ZOMBIE_APOCALYPSE_HAS_BEGUN : NEEDLESS_TO_START_ZOMBIE_APOCALYPSE;
    }

    public List<Creature> addHumans(List<HumanInput> humanInput) {
        return socialService.addHumans(humanInput);
    }

    public List<Creature> addZombies(int count) {
        return apocalypseService.addZombies(count);
    }

    public List<Creature> kill(String killerId, String victimId) {
        CombatResult combatResult = apocalypseService.kill(killerId, victimId);
        return listOfNonNullCreatures(combatResult.getKiller(), combatResult.getVictim());
    }

    private List<Creature> listOfNonNullCreatures(Creature... items) {
        return Arrays.stream(items)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
