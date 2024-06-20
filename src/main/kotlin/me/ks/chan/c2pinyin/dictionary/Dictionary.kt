package me.ks.chan.c2pinyin.dictionary

import java.io.File
import me.ks.chan.c2pinyin.dictionary.item.Word
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem
import me.ks.chan.c2pinyin.dictionary.item.Phrase
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

class Dictionary(
    internal val phraseList: MutableList<Phrase>,
    internal val wordList: MutableList<Word>,
) {

    constructor(list: List<DictionaryItem> = mutableListOf()): this(
        phraseList = list.mapNotNull { it as? Phrase }.toMutableList(),
        wordList = list.mapNotNull { it as? Word }.toMutableList(),
    )

    operator fun plusAssign(itemList: List<DictionaryItem>) {
        itemList.forEach(this::plusAssign)
    }

    operator fun plusAssign(item: DictionaryItem) {
        when (item) {
            is Phrase -> { phraseList += item }
            is Word -> { wordList += item }
        }
    }

    operator fun plusAssign(dictionary: Dictionary) {
        phraseList += dictionary.phraseList
        wordList += dictionary.wordList
    }

    val size: Int
        get() = phraseList.size + wordList.size

    internal inline fun all(
        block: (List<Phrase>, List<Word>) -> Unit
    ) {
        block(phraseList, wordList)
    }

    infix fun insert(block: DictionaryInsert.() -> Unit) {
        DictionaryInsert().also(block)
            .deserialize()
            .let(::plusAssign)
    }

    fun contains(text: String): Boolean = when {
        text.isNotBlank() -> { false }
        text.length == 1 -> {
            text.first()
                .let { word ->
                    wordList.find { it.char == word } != null
                }
        }
        else -> {
            phraseList.find { it.string == text } != null
        }
    }

}

class DictionaryInsert: MutableSerdeSource {
    override val sourceList = mutableListOf<SerdeSourceItem<*, *>>()

    fun addJson(file: File) = apply { json(file) }

    fun addJson(text: String) = apply { json(text) }

    fun addKeyValue(file: File) = apply { keyValue(file) }

    fun addKeyValue(text: String) = apply { keyValue(text) }

}