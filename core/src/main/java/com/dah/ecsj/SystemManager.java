package com.dah.ecsj;

import java.util.HashMap;
import java.util.Map;

public final class SystemManager<RD> {
    @SuppressWarnings("rawtypes")
    protected final Map<Class<? extends EntitySystem>, EntitySystem<RD>> systems = new HashMap<>();

    public void add(EntitySystem<RD> system) {
        systems.put(system.getKeyClass(), system);
    }

    public void remove(@SuppressWarnings("rawtypes") Class<? extends EntitySystem> clazz) {
        systems.remove(clazz);
    }
}
