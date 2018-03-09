package com.leaguechampions.data.repository

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.annotation.StringRes


class Resource<T> {
    val status: Status
    @Nullable val data: T
    @Nullable val message: String?
    @StringRes val stringId: Int

    private constructor(@NonNull status: Status, @Nullable data: T, @Nullable message: String) {
        this.status = status
        this.data = data
        this.message = message
        this.stringId = 0
    }

    private constructor(@NonNull status: Status, @Nullable data: T, @StringRes stringId: Int) {
        this.status = status
        this.data = data
        this.message = null
        this.stringId = stringId
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(message: String, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> error(@StringRes stringId: Int, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, stringId)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }
    }
}
