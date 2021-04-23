package com.github.pongraczl.example.graphqlserverjavademo.service.model;

public class CombatResult {

    private Creature killer;
    private Creature victim;

    public CombatResult(Creature killer, Creature victim) {
        this.killer = killer;
        this.victim = victim;
    }

    public Creature getKiller() {
        return killer;
    }

    public void setKiller(Creature killer) {
        this.killer = killer;
    }

    public Creature getVictim() {
        return victim;
    }

    public void setVictim(Creature victim) {
        this.victim = victim;
    }

}
