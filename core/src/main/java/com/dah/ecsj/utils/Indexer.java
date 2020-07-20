package com.dah.ecsj.utils;

import java.util.HashMap;
import java.util.Map;

public class Indexer<T> {
    private int id = 0;
    private final Map<T, Integer> map = new HashMap<>();

    public int next(T t) {
        Integer id = map.get(t);
        if(id == null) {
            this.id++;
            map.put(t, this.id);
            return this.id;
        } else return id;
    }
}
