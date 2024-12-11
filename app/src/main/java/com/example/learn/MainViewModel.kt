package com.example.learn

import android.content.Context.MODE_PRIVATE
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api
import com.example.learn.data.models.NewWord
import com.example.learn.data.models.WordList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.learn.data.models.Result
import com.example.learn.data.models.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class MainViewModel(private val dataStore: DataStore<Preferences>): ViewModel() {
    val id: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.ID]
    }

    fun ensureUserId() {
        viewModelScope.launch {
            // Read userId from DataStore
            val existingUserId = id.firstOrNull()

            // If userId is null, fetch from API
            if (existingUserId == null) {
                try {
                    val fetchedUserId = api.getUserId()
                    saveUserId(fetchedUserId)
                } catch (e: Exception) {
                    // Handle error (e.g., network issue)
                    e.printStackTrace()
                }
            }
        }
    }

    private fun saveUserId(userId: Int) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.ID] = userId
            }
        }
    }

    private val userId = 1
    private val _userWordLists = MutableStateFlow<List<WordList>?>(null)
    val userWordLists: StateFlow<List<WordList>?> = _userWordLists

    private val _wordLists = MutableStateFlow<List<WordList>?>(null)
    val wordLists: StateFlow<List<WordList>?> = _wordLists

    private val _words = MutableStateFlow<List<NewWord>?>(null)
    val words: StateFlow<List<NewWord>?> = _words

    private val _status = MutableStateFlow<Boolean?>(null)
    val status: StateFlow<Boolean?> = _status

    private val _quizData = MutableStateFlow<List<NewWord>?>(null)
    val quizData: StateFlow<List<NewWord>?> = _quizData
    var results = mutableListOf<Result>()

    fun fetchUserWordLists() {
        viewModelScope.launch {
            try {
                val response = api.getUserWordLists(userId)
                _userWordLists.value = response
            } catch (e: Exception) {
                //
            }
        }
    }

    fun fetchWords(listId: Int) {
        viewModelScope.launch {
            try {
                val response = api.getWordList(listId)
                _words.value = response
            } catch (e: Exception) {
                //
            }
        }
    }

    fun fetchWordLists() {
        viewModelScope.launch {
            try {
                val response = api.getWordLists()
                _wordLists.value = response
            } catch (e: Exception) {
                //
            }
        }
    }

    fun addWordList(listId: Int) {
        viewModelScope.launch {
            try {
                val response = api.addUserWordList(userId, listId)
                _status.value = response
            } catch (e: Exception) {
                //
            }
        }
    }

    fun fetchQuizData() {
        viewModelScope.launch {
            try {
                val response = api.getUserLearnList(userId)
                _quizData.value = response.shuffled()
            } catch (e: Exception) {
                //
            }
        }
    }

    fun sendResults() {
        viewModelScope.launch {
            try {
                val body = Results(data=results)
                val response = api.uploadResults(userId, body)
                _status.value = response
            } catch (e: Exception) {
                //
            }
        }
    }
}