package com.leaguechampions.utils;

import android.support.test.espresso.IdlingResource;

import com.leaguechampions.core.Const;

public class ElapsedTimeIdlingResource implements IdlingResource {

    private final long startTime;
    private final long waitingTime;
    private ResourceCallback resourceCallback;

    public ElapsedTimeIdlingResource() {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = Const.BEHAVIOR_DELAY_MILLIS;
    }

    public ElapsedTimeIdlingResource(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitingTime;
    }

    @Override
    public String getName() {
        return "ElapsedTimeIdlingResource: " + waitingTime + startTime;
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitingTime);
        if (idle) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
