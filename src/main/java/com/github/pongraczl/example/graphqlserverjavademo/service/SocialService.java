package com.github.pongraczl.example.graphqlserverjavademo.service;

import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SocialService {

    private final List<Creature> creatures = new ArrayList<>();

    /* Sample data initialization */
    {
        creatures.add(Creature.createNewHuman("Bad Guy", "hangman"));
        creatures.add(Creature.createNewHuman("Carl the Other", ""));
        creatures.add(Creature.createNewHuman("Sarah Connor", "waitress"));
    }


    public Creature getCreatureById(String id) {
        return creatures.stream()
                .filter(creature -> creature.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Creature> getEveryBody() {
        return creatures;
    }

    public List<Creature> getAllLiving() {
        return creatures
                .stream()
                .filter(creature -> creature.getLifeStatus() == Creature.LifeStatus.ALIVE)
                .collect(Collectors.toList());
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void updateCreature(Creature updatedCreature) {
        Creature creatureToUpdate = getCreatureById(updatedCreature.getId());
        if (creatureToUpdate != null) {
            creatures.remove(creatureToUpdate);
            creatures.add(updatedCreature);
        }
    }

}
