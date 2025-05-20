package com.meankieli.dictionary.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meankieli.dictionary.data.DictionaryEntry
import com.meankieli.dictionary.data.SearchResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DictionaryScreen(
    searchResult: SearchResult?,
    onSearch: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val searchDelay = 500L // 500ms delay

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newQuery -> 
                searchQuery = newQuery
                coroutineScope.launch {
                    delay(searchDelay)
                    if (newQuery == searchQuery) { // Only search if the query hasn't changed during the delay
                        onSearch(newQuery)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Sök svenska eller meänkieli...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { 
                        searchQuery = ""
                        onSearch("")
                    }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear Search")
                    }
                }
            }
        )

        // Results
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            searchResult?.let { result ->
                if (result.exactMatches.isNotEmpty()) {
                    item {
                        Text(
                            "exakt matchning (${result.exactMatches.size})",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    items(result.exactMatches) { entry ->
                        DictionaryEntryCard(entry)
                    }
                }

                if (result.partialMatches.isNotEmpty()) {
                    item {
                        Text(
                            "Delmatch (${result.partialMatches.size})",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    items(result.partialMatches) { entry ->
                        DictionaryEntryCard(entry)
                    }
                }

                if (result.exactMatches.isEmpty() && result.partialMatches.isEmpty()) {
                    item {
                        Text(
                            "No matches found",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DictionaryEntryCard(entry: DictionaryEntry) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "${entry.source} (${entry.pos}): ${entry.target}",
                style = MaterialTheme.typography.h6
            )

            if (entry.meankieliExamples.isNotEmpty() || entry.swedishExamples.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Examples:", style = MaterialTheme.typography.subtitle1)
                
                entry.meankieliExamples.zip(entry.swedishExamples).forEach { (me, se) ->
                    Text("Meänkieli: $me")
                    Text("Swedish: $se")
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            entry.notes?.let { notes ->
                Spacer(modifier = Modifier.height(8.dp))
                Text("Notes: $notes", style = MaterialTheme.typography.body2)
            }
        }
    }
} 