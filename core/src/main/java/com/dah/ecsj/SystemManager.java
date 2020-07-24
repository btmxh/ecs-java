package com.dah.ecsj;

import java.util.HashMap;
import java.util.Map;

public final class SystemManager<RD> {
    @SuppressWarnings("rawtypes")
    protected final Map<Class<? extends EntitySystem>, EntitySystem<RD>> systems = new HashMap<>();

    private final Engine<RD> engine;

    public SystemManager(Engine<RD> engine) {
        this.engine = engine;
    }

    public void add(EntitySystem<RD> system) {
        system.addToEngine(engine);
    }

    public void remove(@SuppressWarnings("rawtypes") Class<? extends EntitySystem> clazz) {
        EntitySystem<RD> system = systems.get(clazz);
        if(system != null)  system.removeFromEngine(engine);
    }
}
