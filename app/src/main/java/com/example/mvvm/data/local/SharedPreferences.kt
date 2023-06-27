package com.example.mvvm.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences constructor(context: Context) {
    companion object {
        private const val Name = "Name"
    }

    private val sp: SharedPreferences = context.getSharedPreferences("mvvm", Context.MODE_PRIVATE)

    fun clear() = sp.edit().clear().apply()

    /***
     * Sample
     */
    /** Set */
    fun setName(name: String) = sp.edit().putString(Name, name).apply()

    /** Get */
    fun getName(): String = sp.getString(Name,"") ?: ""
}