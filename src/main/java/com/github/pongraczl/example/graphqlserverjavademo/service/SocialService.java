package com.github.pongraczl.example.graphqlserverjavademo.service;

import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.HumanInput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialService {

    private final List<Creature> creatures = new ArrayList<>();

    /* Sample data initialization */ {
        addHuman("Bad Guy", "hangman");
        addHuman("Carl the Other", "");
        addHuman("Sarah Connor", "waitress");
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

    public Creature addCreature(Creature creature) {
        creatures.add(creature);
        return creature;
    }

    public Creature addHuman(String name, String profession) {
        return addCreature(Creature.createNewHuman(name, profession));
    }

    public List<Creature> addHumans(List<HumanInput> humansToAdd) {
        return humansToAdd.stream()
                .map(human -> addHuman(human.getName(), human.getProfession()))
                .collect(Collectors.toList());
    }

    public void updateCreature(Creature updatedCreature) {
        Creature creatureToUpdate = getCreatureById(updatedCreature.getId());
        if (creatureToUpdate != null) {
            creatures.remove(creatureToUpdate);
            creatures.add(updatedCreature);
        }
    }

}
