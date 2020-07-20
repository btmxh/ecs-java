package com.dah.ecsj;

import java.util.BitSet;

public interface Family {

    boolean matches(Entity entity);

    class BitFamilyBuilder {
        private BitSet all = new BitSet();
        private BitSet one = new BitSet();
        private BitSet exclude = new BitSet();

        public void all(int idx) {
            all.set(idx);
        }

        public void all(ComponentType type) {
            all.set(type.getId());
        }

        public void one(int idx) {
            one.set(idx);
        }

        public void one(ComponentType type) {
            one.set(type.getId());
        }

        public void exclude(int idx) {
            exclude.set(idx);
        }

        public void exclude(ComponentType type) {
            exclude.set(type.getId());
        }

        public BitFamily get() {
            if(all.isEmpty())       all = null;
            if(one.isEmpty())       one = null;
            if(exclude.isEmpty())   exclude = null;
            return new BitFamily(all, one, exclude);
        }
    }

    class BitFamily implements Family {

        private final BitSet all, one, exclude;

        private BitFamily(BitSet all, BitSet one, BitSet exclude) {
            this.all = all;
            this.one = one;
            this.exclude = exclude;
        }

        @Override
        public boolean matches(Entity entity) {
            var compBits = new BitSet();
            if(all != null) {
                entity.getCompBitsCopy(compBits);
                compBits.and(all);
                if (!compBits.equals(all)) return false;
            }

            if(one != null) {
                entity.getCompBitsCopy(compBits);
                if(!compBits.intersects(one))    return false;
            }

            if(exclude != null) {
                entity.getCompBitsCopy(compBits);
                if(exclude.intersects(compBits))    return false;
            }

            return true;
        }
    }
}
