package com.yts.ytscleanarchitecture.extension

import android.content.Context
import androidx.core.content.edit

/**
 * SharedPreferences
 */

fun Context.getStringPreference(key: String): String {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getString(key, "")
        ?: ""
}

fun Context.setStringPreference(key: String, value: String) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putString(key, value)
        apply()
    }
}

fun Context.getIntegerPreference(key: String): Int {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).getInt(key, 0)
}

fun Context.setIntegerPreference(key: String, value: Int) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putInt(key, value)
        apply()
    }
}

fun Context.getBooleanPreference(key: String, default: Boolean = false): Boolean {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)?.getBoolean(
        key,
        default
    )
        ?: false
}

fun Context.setBooleanPreference(key: String, value: Boolean) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putBoolean(key, value)
        apply()
    }
}


fun Context.getLongPreference(key: String): Long {
    return this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        ?.getLong(key, 0L) ?: 0L
}

fun Context.setLongPreference(key: String, value: Long) {
    this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE).edit {
        putLong(key, value)
        apply()
    }
}


