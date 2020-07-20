package com.dah.ecsj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyManager {
    private Engine<?> engine;
    private final Map<Family, List<Entity>> families = new HashMap<>();

    public FamilyManager(Engine<?> engine) {
        this.engine = engine;
    }

    public List<Entity> register(Family family) {
        return families.computeIfAbsent(family, f -> {
            var list = new ArrayList<Entity>();
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
}
