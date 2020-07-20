package com.dah.ecsj.systems;

import com.dah.ecsj.Engine;
import com.dah.ecsj.Entity;
import com.dah.ecsj.EntitySystem;
import com.dah.ecsj.Family;

import java.util.List;

public class FamilySystem<RD> extends EntitySystem<RD> {
    private final Family family;
    private List<Entity> entities;

    public FamilySystem(Family family) {
        this.family = family;
    }

    public FamilySystem(int priority, Family family) {
        super(priority);
        this.family = family;
    }

    public void addToEngine(Engine<RD> engine) {
        super.addToEngine(engine);
        entities = engine.familyManager.register(family);
    }

    @Override
    public void removeFromEngine(Engine<RD> engine) {
        super.removeFromEngine(engine);
        entities = null;
    }

    @Override
    public void run(RD runData) {
        for (Entity entity : entities) {
            run(entity, runData);
        }
    }

    protected void run(Entity entity, RD runData) {
    }
}
