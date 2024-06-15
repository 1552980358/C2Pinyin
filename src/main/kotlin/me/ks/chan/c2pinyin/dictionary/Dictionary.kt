package me.ks.chan.c2pinyin.dictionary

import java.io.File
import me.ks.chan.c2pinyin.dictionary.item.DictionaryChar
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem
import me.ks.chan.c2pinyin.dictionary.item.DictionaryString
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

class Dictionary(
    internal val stringList: MutableList<DictionaryString>,
    internal val charList: MutableList<DictionaryChar>,
) {

    constructor(list: List<DictionaryItem> = mutableListOf()): this(
        stringList = list.mapNotNull { it as? DictionaryString }.toMutableList(),
        charList = list.mapNotNull { it as? DictionaryChar }.toMutableList(),
    )

    operator fun plusAssign(itemList: List<DictionaryItem>) {
        itemList.forEach(this::plusAssign)
    }

    operator fun plusAssign(item: DictionaryItem) {
        when (item) {
            is DictionaryString -> { stringList += item }
            is DictionaryChar -> { charList += item }
        }
    }

    operator fun plusAssign(dictionary: Dictionary) {
        stringList += dictionary.stringList
        charList += dictionary.charList
    }

    val size: Int
        get() = stringList.size + charList.size

    internal inline fun all(block: (List<DictionaryString>, List<DictionaryChar>) -> Unit) {
        block(stringList, charList)
    }

    infix fun insert(block: DictionaryInsert.() -> Unit) {
        DictionaryInsert().also(block)
            .deserialize()
            .let(::plusAssign)
    }

    fun contains(string: String): Boolean {
        return when {
            string.isNotBlank() -> { false }
            string.length == 1 -> {
                val char = string.first()
                charList.find { it.char == char } != null
            }
            else -> {
                stringList.find { it.string == string } != null
            }
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