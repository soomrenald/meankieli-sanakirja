package com.meankieli.dictionary

import android.content.Context
import com.meankieli.dictionary.data.DictionaryEntry
import com.meankieli.dictionary.data.SearchResult
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log

@Xml(name = "w")
data class WordElement(
    @PropertyElement(name = "v")
    var value: String = "",
    
    @Element(name = "l")
    var left: LeftElement? = null,
    
    @Element(name = "r")
    var right: RightElement? = null
)

@Xml(name = "l")
data class LeftElement(
    @PropertyElement
    var text: String = "",
    
    @Element(name = "s")
    var tags: List<TagElement> = emptyList()
)

@Xml(name = "r")
data class RightElement(
    @Element(name = "s")
    var tags: List<TagElement> = emptyList()
)

@Xml(name = "s")
data class TagElement(
    @PropertyElement(name = "n")
    var name: String = ""
)

class DictionaryRepository(private val context: Context) {
    private var words: List<WordElement> = emptyList()
    private val metadata = mapOf(
        "s" to "noun",
        "a" to "adjective",
        "adv" to "adverb",
        "v" to "verb",
        "en" to "name",
        "pos" to "postposition",
        "pron" to "pronoun",
        "num" to "numeral",
        "konj" to "conjunction",
        "ij" to "interjection",
        "prep" to "preposition"
    )

    init {
        loadDictionary()
    }

    private fun loadDictionary() {
        try {
            Log.d("DictionaryRepository", "Starting to load dictionary...")
            val inputStream = context.assets.open("fit-swe-lr-trie.xml")
            Log.d("DictionaryRepository", "Successfully opened XML file")
            
            val parser = XmlPullParserFactory.newInstance().newPullParser()
            parser.setInput(inputStream, "UTF-8")
            Log.d("DictionaryRepository", "Parser initialized")
            
            val wordsList = mutableListOf<WordElement>()
            var eventType = parser.eventType
            var wordCount = 0
            
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.name == "w") {
                    val word = parseWordElement(parser)
                    wordsList.add(word)
                    wordCount++
                    if (wordCount % 1000 == 0) {
                        Log.d("DictionaryRepository", "Parsed $wordCount words so far...")
                    }
                }
                eventType = parser.next()
            }
            
            words = wordsList
            Log.d("DictionaryRepository", "Successfully loaded ${words.size} words from XML")
        } catch (e: Exception) {
            Log.e("DictionaryRepository", "Error loading dictionary", e)
            e.printStackTrace()
        }
    }

    private fun parseWordElement(parser: XmlPullParser): WordElement {
        val word = WordElement()
        word.value = parser.getAttributeValue(null, "v")
        
        var eventType = parser.next()
        while (eventType != XmlPullParser.END_TAG || parser.name != "w") {
            if (eventType == XmlPullParser.START_TAG) {
                when (parser.name) {
                    "l" -> word.left = parseLeftElement(parser)
                    "r" -> word.right = parseRightElement(parser)
                }
            }
            eventType = parser.next()
        }
        
        return word
    }

    private fun parseLeftElement(parser: XmlPullParser): LeftElement {
        val left = LeftElement()
        
        // Check if the next event is text content
        var eventType = parser.next()
        if (eventType == XmlPullParser.TEXT) {
            left.text = parser.text
            eventType = parser.next()
        }
        
        val tags = mutableListOf<TagElement>()
        
        while (eventType != XmlPullParser.END_TAG || parser.name != "l") {
            if (eventType == XmlPullParser.START_TAG && parser.name == "s") {
                val tag = TagElement()
                tag.name = parser.getAttributeValue(null, "n")
                tags.add(tag)
                // Skip to the next event since this is an empty tag
                eventType = parser.next()
            } else {
                eventType = parser.next()
            }
        }
        
        left.tags = tags
        return left
    }

    private fun parseRightElement(parser: XmlPullParser): RightElement {
        val right = RightElement()
        val tags = mutableListOf<TagElement>()
        
        var eventType = parser.next()
        while (eventType != XmlPullParser.END_TAG || parser.name != "r") {
            if (eventType == XmlPullParser.START_TAG && parser.name == "s") {
                val tag = TagElement()
                tag.name = parser.getAttributeValue(null, "n")
                tags.add(tag)
                // Skip to the next event since this is an empty tag
                eventType = parser.next()
            } else {
                eventType = parser.next()
            }
        }
        
        right.tags = tags
        return right
    }

    fun searchWord(word: String): SearchResult {
        val searchWords = word.lowercase().split(" ").filter { it.isNotEmpty() }
        val searchPhrase = searchWords.joinToString(" ").trim()
        if (searchWords.isEmpty()) {
            return SearchResult(emptyList(), emptyList())
        }

        val exactMatches = mutableListOf<DictionaryEntry>()
        val partialMatches = mutableListOf<DictionaryEntry>()
        val seenEntries = mutableSetOf<String>() // Track unique entries

        for (wordElem in words) {
            val source = wordElem.value.lowercase()
            val left = wordElem.left
            val right = wordElem.right

            if (left != null && right != null) {
                val meankieli = left.text
                val pos = getPosTag(left.tags)
                val notes = getNotes(left.tags)
                val swedish = getSwedishTranslation(right.tags)
                val examples = getExamples(right.tags)

                if (meankieli.isNotEmpty() && swedish.isNotEmpty()) {
                    // Create entries for both directions
                    val meankieliEntry = DictionaryEntry(
                        source = meankieli,
                        target = swedish,
                        pos = pos,
                        meankieliExamples = examples.first,
                        swedishExamples = examples.second,
                        notes = notes
                    )

                    val swedishEntry = DictionaryEntry(
                        source = swedish,
                        target = meankieli,
                        pos = pos,
                        meankieliExamples = examples.first,
                        swedishExamples = examples.second,
                        notes = notes
                    )

                    // --- Exact match logic ---
                    // Meänkieli: exact match if the whole search phrase matches the word
                    if (meankieli.lowercase().trim() == searchPhrase) {
                        val entryKey = "${meankieliEntry.source}-${meankieliEntry.target}"
                        if (entryKey !in seenEntries) {
                            exactMatches.add(meankieliEntry)
                            seenEntries.add(entryKey)
                        }
                    }

                    // Swedish: exact match if any part matches the whole search phrase
                    val swedishParts = swedish.split(*arrayOf(",", ".", "/", ";", "|", "•", "·"))
                        .map { it.trim().lowercase() }
                        .filter { it.isNotEmpty() }
                    if (swedishParts.any { it == searchPhrase }) {
                        val entryKey = "${swedishEntry.source}-${swedishEntry.target}"
                        if (entryKey !in seenEntries) {
                            exactMatches.add(swedishEntry)
                            seenEntries.add(entryKey)
                        }
                    }

                    // --- Partial match logic (unchanged) ---
                    // Meänkieli: any search word is a substring
                    if (searchWords.any { it in source }) {
                        val entryKey = "${meankieliEntry.source}-${meankieliEntry.target}"
                        if (entryKey !in seenEntries) {
                            partialMatches.add(meankieliEntry)
                            seenEntries.add(entryKey)
                        }
                    }
                    // Swedish: any search word is a substring of any part
                    if (searchWords.any { word -> swedishParts.any { it.contains(word) } }) {
                        val entryKey = "${swedishEntry.source}-${swedishEntry.target}"
                        if (entryKey !in seenEntries) {
                            partialMatches.add(swedishEntry)
                            seenEntries.add(entryKey)
                        }
                    }
                }
            }
        }

        return SearchResult(exactMatches, partialMatches)
    }

    private fun getPosTag(tags: List<TagElement>): String {
        return tags.find { it.name in metadata.keys }?.let { metadata[it.name] } ?: ""
    }

    private fun getNotes(tags: List<TagElement>): String? {
        return tags.find { it.name.startsWith("note") }?.name
    }

    private fun getSwedishTranslation(tags: List<TagElement>): String {
        return tags.find { it.name.startsWith("t:") }?.name?.substring(2) ?: ""
    }

    private fun getExamples(tags: List<TagElement>): Pair<List<String>, List<String>> {
        val meankieliExamples = mutableListOf<String>()
        val swedishExamples = mutableListOf<String>()

        for (tag in tags) {
            when {
                tag.name.startsWith("exS:") -> meankieliExamples.add(tag.name.substring(4))
                tag.name.startsWith("exT:") -> swedishExamples.add(tag.name.substring(4))
            }
        }

        return Pair(meankieliExamples, swedishExamples)
    }

    fun addEntry(meankieli: String, swedish: String, pos: String, user: String) {
        // Create backup before modifying
        createBackup()
        
        // Create new word element
        val newWord = WordElement(
            value = meankieli.lowercase(),
            left = LeftElement(
                text = meankieli,
                tags = listOf(
                    TagElement(name = pos),
                    TagElement(name = "note:Added by $user")
                )
            ),
            right = RightElement(
                tags = listOf(
                    TagElement(name = "t:$swedish")
                )
            )
        )
        
        // Add to in-memory list
        words = words + newWord
        
        // Save to XML file
        saveToXml()
    }
    
    private fun createBackup() {
        try {
            val backupDir = File(context.filesDir, "backup").apply { mkdirs() }
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val backupFile = File(backupDir, "dictionary_$timestamp.xml")
            
            context.assets.open("fit-swe-lr-trie.xml").use { input ->
                backupFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun saveToXml() {
        try {
            val outputFile = File(context.filesDir, "fit-swe-lr-trie.xml")
            outputFile.outputStream().use { output ->
                val writer = output.writer()
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                writer.write("<dictionary>\n")
                
                words.forEach { word ->
                    writer.write("  <w v=\"${word.value}\">\n")
                    
                    word.left?.let { left ->
                        writer.write("    <l>${left.text}</l>\n")
                        left.tags.forEach { tag ->
                            writer.write("      <s n=\"${tag.name}\"/>\n")
                        }
                    }
                    
                    word.right?.let { right ->
                        writer.write("    <r>\n")
                        right.tags.forEach { tag ->
                            writer.write("      <s n=\"${tag.name}\"/>\n")
                        }
                        writer.write("    </r>\n")
                    }
                    
                    writer.write("  </w>\n")
                }
                
                writer.write("</dictionary>")
                writer.flush()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
} 