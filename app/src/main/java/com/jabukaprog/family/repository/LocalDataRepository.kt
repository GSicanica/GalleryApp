package com.jabukaprog.family.repository

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataRepository(
    private val sharedPreferences: SharedPreferences
) {

    suspend fun setData(stringUriSet: Set<String>) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit()
                .putStringSet(PREF_STRING_URI, stringUriSet)
                .commit()
        }
    }


    fun getStringsOrNull(): MutableSet<String>? {
        return if (sharedPreferences.contains(PREF_STRING_URI)) {
            sharedPreferences.getStringSet(PREF_STRING_URI, setOfNotNull(""))
        }
        else mutableSetOf("")
    }

    suspend fun clearData() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit()
                .remove(PREF_STRING_URI)
                .commit()
        }
    }


    companion object {
        const val PREF_STRING_URI = "uri"
    }
}