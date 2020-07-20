package com.dah.ecsj;

import org.jetbrains.annotations.NotNull;

public class EntitySystem<RD> implements Comparable<EntitySystem<RD>>{

    private final int priority;
    protected Engine<RD> engine;

    public EntitySystem() {
        this(0);
    }

    public EntitySystem(int priority) {
        this.priority = priority;
    }

    public void run(RD runData) {

    }

    @SuppressWarnings("rawtypes")
    protected Class<? extends EntitySystem> getKeyClass() {
        return getClass();
    }

    @Override
    public final int compareTo(@NotNull EntitySystem<RD> o) {
        return Integer.compare(priority, o.priority);
    }

    protected void addToEngine(Engine<RD> engine) {
        engine.systemManager.add(this);
        this.engine = engine;
    }

    protected void removeFromEngine(Engine<RD> engine) {
        engine.systemManager.remove(getKeyClass());
        this.engine = null;
    }
}
