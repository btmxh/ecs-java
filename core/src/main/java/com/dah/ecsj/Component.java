package com.dah.ecsj;

public class Component {

    protected int getTypeID(Engine<?> engine) {
        return engine.typeIndexer.next(getClass());
    }

    protected final ComponentType getType(Engine<?> engine) {
        return new ComponentType(getTypeID(engine));
    }

}
