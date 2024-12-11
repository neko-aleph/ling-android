package com.example.learn

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

class App : Application() {
    companion object {
        val Context.dataStore by preferencesDataStore(name = "settings")
    }
}