package com.dah.ecsj;

import com.dah.ecsj.utils.GrowableArray;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class Entity {
    private final GrowableArray<Component> components = new GrowableArray<>();
    private final BitSet compBits = new BitSet();

    public Component add(@NotNull Component component) {
        int id = component.getType().getId();
        compBits.set(id);
        return components.setAndReturn(id, component);
    }

    public Component remove(int id) {
        compBits.clear(id);
        return components.get(id);
    }

    public Component remove(@NotNull Component component) {
        return remove(component.getType().getId());
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
        set.or(set);
    }
}
