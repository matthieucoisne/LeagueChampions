package com.leaguechampions.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static Object getField(Object object, String fieldName) {
        try {
            Field field = getDeclaredField(object.getClass(), fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setField(Object object, String fieldName, Object value) {
        try {
            Field field = getDeclaredField(object.getClass(), fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recursive call
     */
    private static Field getDeclaredField(Class clazz, String fieldName) {
        Field field = null;
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            field = getDeclaredField(clazz.getSuperclass(), fieldName);
        }
        if (field == null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception ignored) {}
        }
        return field;
    }
}
