package me.ks.chan.c2pinyin.dictionary.item.serde

import java.io.File
import me.ks.chan.c2pinyin.convert.pinyinIndex
import me.ks.chan.c2pinyin.dictionary.item.Word
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem
import me.ks.chan.c2pinyin.dictionary.item.Phrase
import me.ks.chan.c2pinyin.dictionary.item.serde.source.FileSerdeSource
import me.ks.chan.c2pinyin.dictionary.item.serde.source.StringSerdeSource

private const val RegexString = "^([\\u4e00-\\u9fa5]+ *)=((([a-zA-Z]+|-),)*([a-zA-Z]+|-))\$"

object KeyValuePairSerde: AbstractSerde<String>,
                          FileSerdeSource<String>,
                          StringSerdeSource<String> {

    override fun serialize(list: List<DictionaryItem>): String {
        throw IllegalAccessException(
            "KeyValuePairSerde only allows deserialization. " +
                "Use KotlinxSerde or your own implementation instead."
        )
    }

    override fun deserialize(serialized: String): List<DictionaryItem> {
        val regex = RegexString.toRegex()
        return serialized.lines()
            .mapNotNull { line -> findTextPinyinListPair(line, regex) }
            .mapNotNull(::asDictionaryItem)
    }

    override fun handleSource(file: File): String =
        file.readText()

    override fun handleSource(string: String): String = string

}

private const val PinyinSeparator = ','
private fun findTextPinyinListPair(line: String, regex: Regex): Pair<String, List<String>>? {
    return regex.find(line)
        ?.destructured
        ?.let { (text, pinyin) ->
            Pair(text, pinyin.split(PinyinSeparator).map(String::trim).filter(String::isNotBlank))
                .takeIf { (text, pinyinList) -> text.isNotBlank() && text.length == pinyinList.size }
        }
}

private const val Skip = "-"
private fun asDictionaryItem(textPinyinListPair: Pair<String, List<String>>): DictionaryItem? {
    val (text, pinyinList) = textPinyinListPair

    val indexList = pinyinList.map { pinyin ->
        when (pinyin) {
            Skip -> { 0 }
            else -> {
                pinyin.pinyinIndex.takeIf { it > 0 }
                    ?: return null
            }
        }
    }

    return when (text.length) {
        1 -> { Word(text.first(), indexList.first()) }
        else -> {
            text.toCharArray()
                .zip(indexList)
                .map(Word::new)
                .toMutableList()
                .let(Phrase::new)
        }
    }
}