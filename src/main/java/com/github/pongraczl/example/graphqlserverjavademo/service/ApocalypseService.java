package com.github.pongraczl.example.graphqlserverjavademo.service;

import com.github.pongraczl.example.graphqlserverjavademo.service.model.CombatResult;
import com.github.pongraczl.example.graphqlserverjavademo.service.model.Creature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
     *
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

    /**
     * @param killerId creatureId of the killer
     * @param victimId creatureId of the victim
     * @return list of the killer and the victim
     */
    public CombatResult kill(String killerId, String victimId) {
        Creature killer = socialService.getCreatureById(killerId);
        Creature victim = socialService.getCreatureById(victimId);

        if (checkIfAllCreaturesExist(killer, victim)
                && checkIfCreatureIsAbleToKill(killer)
                && checkIfCreatureIsAbleToBeKilled(victim)) {

            simplyKillHumanIfKillerIsHuman(killer, victim);
            makeZombieReallyDeadIfKillerIsHuman(killer, victim);
            transformHumanIntoZombieIfKillerIsZombie(killer, victim);
        }

        return new CombatResult(killer, victim);
    }

    private boolean checkIfAllCreaturesExist(Creature... creatures) {
        return Arrays.stream(creatures)
                .noneMatch(Objects::isNull);
    }

    private boolean checkIfCreatureIsAbleToKill(Creature creature) {
        return creature.getLifeStatus() != REALLY_DEAD;
    }

    private boolean checkIfCreatureIsAbleToBeKilled(Creature creature) {
        return creature.getLifeStatus() != REALLY_DEAD;
    }

    private void simplyKillHumanIfKillerIsHuman(Creature killer, Creature victim) {
        if (killer.getLifeStatus() == ALIVE
                && victim.getLifeStatus() == ALIVE) {
            victim.kill();
        }
    }

    private void makeZombieReallyDeadIfKillerIsHuman(Creature killer, Creature victim) {
        if (killer.getLifeStatus() == ALIVE
                && victim.getLifeStatus() == LIVING_DEAD) {
            victim.kill();
        }
    }

    private void transformHumanIntoZombieIfKillerIsZombie(Creature killer, Creature victim) {
        if (killer.getLifeStatus() == LIVING_DEAD
                && victim.getLifeStatus() == ALIVE) {
            victim.transformIntoZombieIfAlive();
        }
    }
}
