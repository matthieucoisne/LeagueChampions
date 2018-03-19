package com.leaguechampions.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public class Resource<T> {
    @NonNull public final Status status;
    @Nullable public final T data;
    @Nullable public final String message;
    @StringRes public final int stringId;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.stringId = 0;
    }

    private Resource(@NonNull Status status, @Nullable T data, @StringRes int stringId) {
        this.status = status;
        this.data = data;
        this.message = null;
        this.stringId = stringId;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> error(@StringRes int stringId, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, stringId);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}
