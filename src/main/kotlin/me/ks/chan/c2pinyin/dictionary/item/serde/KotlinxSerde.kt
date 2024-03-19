package me.ks.chan.c2pinyin.dictionary.item.serde

import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem
import me.ks.chan.c2pinyin.dictionary.item.serde.source.FileSerdeSource
import me.ks.chan.c2pinyin.dictionary.item.serde.source.StringSerdeSource

object KotlinxSerde: AbstractSerde<String>,
                     FileSerdeSource<String>,
                     StringSerdeSource<String> {

    override fun serialize(list: List<DictionaryItem>): String {
        return Json.encodeToString(list)
    }

    override fun deserialize(serialized: String): List<DictionaryItem> {
        return Json.decodeFromString(serialized)
    }

    override fun handleSource(file: File): String =
        file.readText()

    override fun handleSource(string: String): String = string

}