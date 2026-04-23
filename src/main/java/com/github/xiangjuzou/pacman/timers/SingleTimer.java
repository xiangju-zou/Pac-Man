package com.github.xiangjuzou.pacman.timers;

import com.github.hanyaeger.api.Timer;

public class SingleTimer extends Timer {
    private final TimerCallback callback;
    private final int id;

    public SingleTimer(int id, long intervalInMs, TimerCallback callback) {
        super(intervalInMs);

        this.id = id;
        this.callback = callback;
    }

    @Override
    public void onAnimationUpdate(long timestamp) {
        System.out.println(("TIJD voor " + id));
        callback.onTimeReached(id);
        remove();
    }
}
