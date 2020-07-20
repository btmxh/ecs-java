package com.dah.ecsj.systems;

import com.dah.ecsj.EntitySystem;

import java.util.function.ToDoubleFunction;

public class IntervalSystem<RD> extends EntitySystem<RD> {
    private double accumulator;
    private final double interval;
    private final ToDoubleFunction<RD> deltaTimeGetter;

    public IntervalSystem(double interval, ToDoubleFunction<RD> deltaTimeGetter) {
        this.interval = interval;
        this.deltaTimeGetter = deltaTimeGetter;
    }

    public IntervalSystem(int priority, double interval, ToDoubleFunction<RD> deltaTimeGetter) {
        super(priority);
        this.interval = interval;
        this.deltaTimeGetter = deltaTimeGetter;
    }

    @Override
    public void run(RD runData) {
        accumulator += deltaTimeGetter.applyAsDouble(runData);

        while(accumulator >= interval) {
            accumulator -= interval;
            runInterval(runData);
        }
    }

    protected void runInterval(RD runData) {
    }
}
