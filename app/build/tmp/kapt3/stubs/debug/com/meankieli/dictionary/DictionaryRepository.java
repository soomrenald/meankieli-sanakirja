package com.meankieli.dictionary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007J\b\u0010\u0011\u001a\u00020\fH\u0002J.\u0010\u0012\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\tH\u0002J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\tH\u0002J\u0016\u0010\u0017\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\tH\u0002J\u0016\u0010\u0018\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\tH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010 \u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\fH\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/meankieli/dictionary/DictionaryRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "metadata", "", "", "words", "", "Lcom/meankieli/dictionary/WordElement;", "addEntry", "", "meankieli", "swedish", "pos", "user", "createBackup", "getExamples", "Lkotlin/Pair;", "tags", "Lcom/meankieli/dictionary/TagElement;", "getNotes", "getPosTag", "getSwedishTranslation", "loadDictionary", "parseLeftElement", "Lcom/meankieli/dictionary/LeftElement;", "parser", "Lorg/xmlpull/v1/XmlPullParser;", "parseRightElement", "Lcom/meankieli/dictionary/RightElement;", "parseWordElement", "saveToXml", "searchWord", "Lcom/meankieli/dictionary/data/SearchResult;", "word", "app_debug"})
public final class DictionaryRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.meankieli.dictionary.WordElement> words;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.String> metadata = null;
    
    public DictionaryRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void loadDictionary() {
    }
    
    private final com.meankieli.dictionary.WordElement parseWordElement(org.xmlpull.v1.XmlPullParser parser) {
        return null;
    }
    
    private final com.meankieli.dictionary.LeftElement parseLeftElement(org.xmlpull.v1.XmlPullParser parser) {
        return null;
    }
    
    private final com.meankieli.dictionary.RightElement parseRightElement(org.xmlpull.v1.XmlPullParser parser) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meankieli.dictionary.data.SearchResult searchWord(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return null;
    }
    
    private final java.lang.String getPosTag(java.util.List<com.meankieli.dictionary.TagElement> tags) {
        return null;
    }
    
    private final java.lang.String getNotes(java.util.List<com.meankieli.dictionary.TagElement> tags) {
        return null;
    }
    
    private final java.lang.String getSwedishTranslation(java.util.List<com.meankieli.dictionary.TagElement> tags) {
        return null;
    }
    
    private final kotlin.Pair<java.util.List<java.lang.String>, java.util.List<java.lang.String>> getExamples(java.util.List<com.meankieli.dictionary.TagElement> tags) {
        return null;
    }
    
    public final void addEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String meankieli, @org.jetbrains.annotations.NotNull()
    java.lang.String swedish, @org.jetbrains.annotations.NotNull()
    java.lang.String pos, @org.jetbrains.annotations.NotNull()
    java.lang.String user) {
    }
    
    private final void createBackup() {
    }
    
    private final void saveToXml() {
    }
}