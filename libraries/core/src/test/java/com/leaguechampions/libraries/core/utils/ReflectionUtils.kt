package com.leaguechampions.libraries.core.utils

import java.lang.reflect.Field

object ReflectionUtils {

    fun getField(clazz: Any, fieldName: String): Any {
        val field = getDeclaredField(clazz.javaClass, fieldName)
        field!!.isAccessible = true
        return field.get(clazz)
    }

    fun setField(clazz: Any, fieldName: String, value: Any) {
        val field = getDeclaredField(clazz.javaClass, fieldName)
        field!!.isAccessible = true
        field.set(clazz, value)
    }

    /**
     * Recursive call
     */
    private fun getDeclaredField(clazz: Class<*>, fieldName: String): Field? {
        var field: Field? = null
        val superClass = clazz.superclass
        if (superClass != null) {
            field = getDeclaredField(superClass, fieldName)
        }
        if (field == null) {
            try {
                field = clazz.getDeclaredField(fieldName)
            } catch (ignored: Exception) {}
        }
        return field
    }
}
