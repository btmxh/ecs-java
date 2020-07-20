package com.dah.ecsj;

import com.dah.ecsj.utils.Indexer;

import java.lang.reflect.InvocationTargetException;

public class Engine<RD> {
    protected final Indexer<Class<? extends Component>> typeIndexer = new Indexer<>();
    public final EntityManager entityManager = new EntityManager(this);
    public final SystemManager<RD> systemManager = new SystemManager<>();
    public final FamilyManager familyManager = new FamilyManager(this);

    public <C> C newComponent(Class<? extends C> clazz) {
        try {
            return clazz.getConstructor(Engine.class).newInstance(this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Create a new component by reflection failed", e);
        }
    }

    public void run(RD runData) {
        for (@SuppressWarnings("rawtypes") Class<? extends EntitySystem> key : systemManager.systems.keySet()) {
            systemManager.systems.get(key).run(runData);
        }
    }

    public void add(EntitySystem<RD> system) {
        system.addToEngine(this);
    }

    public void remove(EntitySystem<RD> system) {
        system.removeFromEngine(this);
    }

}
