package com.dah.ecsj;

public class Component {

    private Engine<?> engine;

    protected Component(Engine<?> engine) {
        this.engine = engine;
    }

    protected final ComponentType getTypeFromClass(Class<? extends Component> clazz) {
        return new ComponentType(engine, clazz);
    }

    protected ComponentType getType() {
        return getTypeFromClass(getClass());
    }

}
