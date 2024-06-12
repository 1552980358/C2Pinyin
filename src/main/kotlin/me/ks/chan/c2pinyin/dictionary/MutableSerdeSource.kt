package me.ks.chan.c2pinyin.dictionary

import java.io.File
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem
import me.ks.chan.c2pinyin.dictionary.item.serde.KeyValuePairSerde
import me.ks.chan.c2pinyin.dictionary.item.serde.KotlinxSerde
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

context(MutableSerdeSource) fun File.asJson() = json(this)
context(MutableSerdeSource) fun String.asJson() = json(this)
context(MutableSerdeSource) fun File.asKeyValue() = keyValue(this)
context(MutableSerdeSource) fun String.asKeyValue() = keyValue(this)

internal interface MutableSerdeSource {

    val sourceList: MutableList<SerdeSourceItem<*, *>>

    fun json(file: File) {
        sourceList += KotlinxSerde.fileSource(file)
    }

    fun json(text: String) {
        sourceList += KotlinxSerde.stringSource(text)
    }

    fun keyValue(file: File) {
        sourceList += KeyValuePairSerde.fileSource(file)
    }

    fun keyValue(text: String) {
        sourceList += KeyValuePairSerde.stringSource(text)
    }

    fun deserialize(): List<DictionaryItem> =
        sourceList.flatMap(transform = SerdeSourceItem<*, *>::deserialize)

}