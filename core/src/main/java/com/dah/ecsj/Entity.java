package com.dah.ecsj;

import com.dah.ecsj.utils.GrowableArray;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;
import java.util.Objects;

public class Entity {
    protected Engine<?> engine;
    private final GrowableArray<Component> components = new GrowableArray<>();
    private final BitSet compBits = new BitSet();

    public Component add(@NotNull Component component) {
        int id = component.getTypeID(engine);
        compBits.set(id);
        var old = components.setAndReturnOld(id, component);
        engine.familyManager.updateEntity(this);
        return old;
    }

    public Component remove(int id) {
        compBits.clear(id);
        var old = components.remove(id);
        engine.familyManager.updateEntity(this);
        return old;
    }

    public Component remove(@NotNull Component component) {
        int id = component.getTypeID(engine);
        Component comp = components.get(id);
        if(Objects.equals(comp, component)) {
            return remove(id);
        } else {
            return null;
        }
    }

    public Component remove(@NotNull ComponentType type) {
        return remove(type.getId());
    }

    public Component get(int id) {
        return components.get(id);
    }

    public Component get(@NotNull ComponentType type) {
        return get(type.getId());
    }

    @SuppressWarnings("unchecked")
    public <C extends Component> C get(Class<C> clazz) {
        return (C) get(engine.typeIndexer.next(clazz));
    }

    public void clear() {
        components.clear();
    }

    public boolean has(@NotNull ComponentType type) {
        return has(type.getId());
    }

    public boolean has(int id) {
        return compBits.get(id);
    }

    public BitSet getCompBits() {
        return Configuration.SAFE_ACTIONS? (BitSet) compBits.clone() : compBits;
    }

    public BitSet getCompBitsCopy() {
        return (BitSet) compBits.clone();
    }

    public void getCompBitsCopy(BitSet set) {
        set.clear();
        set.or(compBits);
    }
}
