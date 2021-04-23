package com.github.pongraczl.example.graphqlserverjavademo.service.model;

public class Creature {

    private static final String ORIGINALLY_ZOMBIE_ID_PREFIX = "Z";
    private static final String ORIGINALLY_HUMAN_ID_PREFIX = "C";
    private static int creatureCounter = 0;

    private String id;
    private String name;
    private String profession;
    private LifeStatus lifeStatus;

    public void transformIntoZombieIfAlive() {
        if (lifeStatus == LifeStatus.ALIVE) {
            lifeStatus = LifeStatus.LIVING_DEAD;
        }
    }

    public void kill() {
        lifeStatus = LifeStatus.REALLY_DEAD;
    }

    public Creature(){
    }

    private Creature(String id, boolean isZombie) {
        this.id = id;
        lifeStatus = isZombie ? LifeStatus.LIVING_DEAD : LifeStatus.ALIVE;
        creatureCounter++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public LifeStatus getLifeStatus() {
        return lifeStatus;
    }

    public static Creature createNewZombie() {
        return new Creature(ORIGINALLY_ZOMBIE_ID_PREFIX + creatureCounter, true);
    }

    public static Creature createNewHuman(String name, String profession) {
        Creature creature = new Creature(ORIGINALLY_HUMAN_ID_PREFIX + creatureCounter, false);
        creature.name = name;
        creature.profession = profession;
        return creature;
    }

    public enum LifeStatus {
        ALIVE, LIVING_DEAD, REALLY_DEAD
    }

}
