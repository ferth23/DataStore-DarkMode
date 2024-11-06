package com.example.datastore_darkmode

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDarkMode ( private val context: Context ) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore( "DarkMode" )
        val DARK_MODE = booleanPreferencesKey( "dark_mode" )
    }

    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preference ->
            preference [ DARK_MODE ] ?: false
        }

    suspend fun saveDarkMode ( isDarkMode: Boolean ) {
        context.dataStore.edit { preference ->
            preference [ DARK_MODE ] = isDarkMode
        }
    }
}