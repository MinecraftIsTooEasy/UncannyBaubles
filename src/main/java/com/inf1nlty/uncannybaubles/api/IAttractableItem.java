package com.inf1nlty.uncannybaubles.api;

public interface IAttractableItem {

    static IAttractableItem of(Object item) {
        return (IAttractableItem) item;
    }

    void ub$setAttractedByMagnet();
}
