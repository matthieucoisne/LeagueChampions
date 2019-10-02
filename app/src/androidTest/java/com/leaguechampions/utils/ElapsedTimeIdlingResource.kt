package com.leaguechampions.utils

import androidx.test.espresso.IdlingResource

import com.leaguechampions.core.data.local.Const

class ElapsedTimeIdlingResource(private val waitingTime: Long = com.leaguechampions.core.data.local.Const.BEHAVIOR_DELAY_MILLIS) : IdlingResource {

    private val startTime: Long = System.currentTimeMillis()
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    override fun getName(): String {
        return "ElapsedTimeIdlingResource:" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }
}
