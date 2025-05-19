package com.meankieli.dictionary.data

data class DictionaryEntry(
    val source: String,
    val target: String,
    val pos: String,
    val meankieliExamples: List<String>,
    val swedishExamples: List<String>,
    val notes: String?
)

data class SearchResult(
    val exactMatches: List<DictionaryEntry>,
    val partialMatches: List<DictionaryEntry>
) 