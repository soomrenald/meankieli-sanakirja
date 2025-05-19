package com.meankieli.dictionary;

@com.tickaroo.tikxml.annotation.Xml(name = "w")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\'\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lcom/meankieli/dictionary/WordElement;", "", "value", "", "left", "Lcom/meankieli/dictionary/LeftElement;", "right", "Lcom/meankieli/dictionary/RightElement;", "(Ljava/lang/String;Lcom/meankieli/dictionary/LeftElement;Lcom/meankieli/dictionary/RightElement;)V", "getLeft", "()Lcom/meankieli/dictionary/LeftElement;", "setLeft", "(Lcom/meankieli/dictionary/LeftElement;)V", "getRight", "()Lcom/meankieli/dictionary/RightElement;", "setRight", "(Lcom/meankieli/dictionary/RightElement;)V", "getValue", "()Ljava/lang/String;", "setValue", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class WordElement {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String value;
    @org.jetbrains.annotations.Nullable()
    private com.meankieli.dictionary.LeftElement left;
    @org.jetbrains.annotations.Nullable()
    private com.meankieli.dictionary.RightElement right;
    
    public WordElement(@com.tickaroo.tikxml.annotation.PropertyElement(name = "v")
    @org.jetbrains.annotations.NotNull()
    java.lang.String value, @com.tickaroo.tikxml.annotation.Element(name = "l")
    @org.jetbrains.annotations.Nullable()
    com.meankieli.dictionary.LeftElement left, @com.tickaroo.tikxml.annotation.Element(name = "r")
    @org.jetbrains.annotations.Nullable()
    com.meankieli.dictionary.RightElement right) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getValue() {
        return null;
    }
    
    public final void setValue(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.meankieli.dictionary.LeftElement getLeft() {
        return null;
    }
    
    public final void setLeft(@org.jetbrains.annotations.Nullable()
    com.meankieli.dictionary.LeftElement p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.meankieli.dictionary.RightElement getRight() {
        return null;
    }
    
    public final void setRight(@org.jetbrains.annotations.Nullable()
    com.meankieli.dictionary.RightElement p0) {
    }
    
    public WordElement() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.meankieli.dictionary.LeftElement component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.meankieli.dictionary.RightElement component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meankieli.dictionary.WordElement copy(@com.tickaroo.tikxml.annotation.PropertyElement(name = "v")
    @org.jetbrains.annotations.NotNull()
    java.lang.String value, @com.tickaroo.tikxml.annotation.Element(name = "l")
    @org.jetbrains.annotations.Nullable()
    com.meankieli.dictionary.LeftElement left, @com.tickaroo.tikxml.annotation.Element(name = "r")
    @org.jetbrains.annotations.Nullable()
    com.meankieli.dictionary.RightElement right) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}