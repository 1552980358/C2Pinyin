package me.ks.chan.c2pinyin.dictionary

import java.io.File
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

inline fun Dictionary(
    builder: DictionaryBuilder.() -> Unit
): Dictionary {
    return DictionaryBuilder().also(builder)
        .build()
}

open class DictionaryBuilder: MutableSerdeSource {

    final override val sourceList = mutableListOf<SerdeSourceItem<*, *>>()

    fun addJson(file: File) = apply { json(file) }

    fun addJson(text: String) = apply { json(text) }

    fun addKeyValue(file: File) = apply { keyValue(file) }

    fun addKeyValue(text: String) = apply { keyValue(text) }

    fun build(): Dictionary = deserialize().let(::Dictionary)

}