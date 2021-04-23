package com.github.pongraczl.example.graphqlserverjavademo.service;

import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature.LifeStatus.*;

@Service
public class ApocalypseService {

    @Autowired
    private SocialService socialService;

    public boolean isZombieApocalypseActive() {
        return !getZombies().isEmpty();
    }

    /**
     * Starts Zombie Apocalypse by creating a zombie if none exists
     * @return if starting was necessary (not already active)
     */
    public boolean startZombieApocalypse() {
        boolean doesZombieApocalypseNeedToBeStarted = !isZombieApocalypseActive();
        if (doesZombieApocalypseNeedToBeStarted) {
            addZombies(1);
        }
        return doesZombieApocalypseNeedToBeStarted;
    }

    public List<Creature> addZombies(int count) {
        List<Creature> createdCreatures = new ArrayList<>();
        Stream.generate(Creature::createNewZombie)
                .limit(count)
                .peek(createdCreatures::add)
                .forEach(socialService::addCreature);
        return createdCreatures;
    }

    public List<Creature> getZombies() {
        return socialService.getEveryBody()
                .stream()
                .filter(creature -> creature.getLifeStatus() == Creature.LifeStatus.LIVING_DEAD)
                .collect(Collectors.toList());
    }

}
