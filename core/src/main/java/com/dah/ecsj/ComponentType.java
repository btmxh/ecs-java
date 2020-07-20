package com.dah.ecsj;

import com.dah.ecsj.utils.Indexer;

import java.util.HashMap;

public class ComponentType {

    private final int id;

    public ComponentType(Indexer<Class<? extends Component>> indexer, Class<? extends Component> clazz) {
        this.id = indexer.next(clazz);
    }

    public ComponentType(Engine<?> engine, Class<? extends Component> clazz) {
        this(engine.typeIndexer, clazz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentType that = (ComponentType) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }
}
