package com.github.xiangjuzou.pacman.yaegerExtensions;

public interface DistanceWatcher {
    default void onDistanceReached() {
    }

    double getDistanceThreshold();
}