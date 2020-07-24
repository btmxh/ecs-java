package com.dah.ecsj;

import java.util.*;

public class FamilyManager {
    private Engine<?> engine;
    private final Map<Family, Collection<Entity>> families = new HashMap<>();

    public FamilyManager(Engine<?> engine) {
        this.engine = engine;
    }

    public Collection<Entity> register(Family family) {
        return families.computeIfAbsent(family, f -> {
            var list = new HashSet<Entity>();
            for (Entity entity : engine.entityManager.entities) {
                if(f.matches(entity)) {
                    list.add(entity);
                }
            }
            return list;
        });
    }

    public void addEntity(Entity entity) {
        for (Family family : families.keySet()) {
            if(family.matches(entity)) {
                families.get(family).add(entity);
            }
        }
    }

    public void removeEntity(Entity entity) {
        for (Family family : families.keySet()) {
            if(family.matches(entity)) {
                families.get(family).remove(entity);
            }
        }
    }

    public void updateEntity(Entity entity) {
        for(Family family : families.keySet()) {
            if (family.matches(entity)) {
                families.get(family).add(entity);
            } else {
                families.get(family).remove(entity);
            }
        }
    }
}
