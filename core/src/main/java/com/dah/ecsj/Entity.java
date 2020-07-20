package com.dah.ecsj;

import com.dah.ecsj.utils.GrowableArray;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Entity {
    private final GrowableArray<Component> components = new GrowableArray<>();
    private final BitSet compBits = new BitSet();

    public Component add(@NotNull Component component) {
        int id = component.getType().getId();
        compBits.set(id);
        return components.setAndReturn(id, component);
    }

    public Component remove(@NotNull Component component) {
        int id = component.getType().getId();
        compBits.clear(id);
        return components.remove(id);
    }

    public Component remove(@NotNull ComponentType type) {
        int id = type.getId();
        compBits.clear(id);
        return components.remove(id);
    }

    public void clear() {
        components.clear();
    }

    public boolean has(@NotNull ComponentType type) {
        return compBits.get(type.getId());
    }

    public BitSet getCompBits() {
        return Configuration.SAFE_ACTIONS? (BitSet) compBits.clone() : compBits;
    }

    public BitSet getCompBitsCopy() {
        return (BitSet) compBits.clone();
    }

    public void getCompBitsCopy(BitSet set) {
        set.clear();
        set.or(set);
    }
}
