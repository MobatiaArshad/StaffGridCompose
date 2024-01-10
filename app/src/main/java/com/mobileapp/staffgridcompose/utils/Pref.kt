package com.a71cities.example.paypayexchange.utils

import android.content.Context
import android.content.SharedPreferences

object Pref {
    private const val NAME = "STAFF_X_PREF"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private const val LAST_API_TIME = "LAST_API_TIME"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun clear() {
        preferences.edit {
            it.clear()
            it.apply()
        }
    }

    var lastApiTime: String
        get() = preferences.getString(LAST_API_TIME, "")!!
        set(value) = preferences.edit {
            it.putString(LAST_API_TIME, value)
        }
}