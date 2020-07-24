package com.dah.ecsj;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EntityManager {

    protected final Set<Entity> entities = new HashSet<>();
    private final Engine<?> engine;

    public EntityManager(Engine<?> engine) {
        this.engine = engine;
    }

    public int size() {
        return entities.size();
    }

    public boolean contains(Entity entity) {
        return entities.contains(entity);
    }

    public void add(Entity entity) {
        entities.add(entity);
        engine.familyManager.addEntity(entity);
        entity.engine = engine;
    }

    public void remove(Entity entity) {
        boolean removed = entities.remove(entity);
        if(removed) {
            engine.familyManager.removeEntity(entity);
            entity.engine = null;
        }
    }

    public void removeAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            if(o instanceof Entity) {
                remove((Entity) o);
            }
        }
    }

    public void clear() {
        removeAll(entities);
    }
}
