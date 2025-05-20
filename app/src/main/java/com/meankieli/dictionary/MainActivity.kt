package com.meankieli.dictionary

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.meankieli.dictionary.data.SearchResult
import com.meankieli.dictionary.ui.DictionaryScreen
import com.meankieli.dictionary.ui.theme.MeankieliDictionaryTheme

class MainActivity : ComponentActivity() {
    private lateinit var dictionaryRepository: DictionaryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        
        try {
            Log.d("MainActivity", "Attempting to initialize DictionaryRepository...")
            dictionaryRepository = DictionaryRepository(applicationContext)
            Log.d("MainActivity", "DictionaryRepository initialized successfully")
        } catch (e: Exception) {
            Log.e("MainActivity", "Error initializing DictionaryRepository", e)
        }

        setContent {
            Log.d("MainActivity", "Setting content")
            MeankieliDictionaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var searchResult by remember { mutableStateOf<SearchResult?>(null) }

                    DictionaryScreen(
                        searchResult = searchResult,
                        onSearch = { query ->
                            if (query.isNotBlank()) {
                                try {
                                    Log.d("MainActivity", "Searching for: $query")
                                    searchResult = dictionaryRepository.searchWord(query)
                                    Log.d("MainActivity", "Search completed for: $query, found ${searchResult?.exactMatches?.size ?: 0} exact matches and ${searchResult?.partialMatches?.size ?: 0} partial matches")
                                } catch (e: Exception) {
                                    Log.e("MainActivity", "Error searching for: $query", e)
                                }
                            } else {
                                searchResult = null
                            }
                        }
                    )
                }
            }
        }
    }
} 