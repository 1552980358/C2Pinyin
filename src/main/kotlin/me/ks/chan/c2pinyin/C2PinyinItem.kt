package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem

internal data class PinyinChar(
    val char: Char,
    var index: Int? = null,
)

data class C2String internal constructor(
    internal val string: String,
    internal val charList: List<PinyinChar> = string.map(::PinyinChar)
): List<PinyinChar> by charList {

    internal companion object {
         fun new(text: String) = C2String(text)
    }

    internal fun indexListOf(
        charItem: DictionaryItem.Char
    ): List<Int> = charList.mapIndexedNotNull { index, item ->
        index.takeIf { item.index == null && item.char == charItem.char }
    }

    internal fun indexListOf(
        stringItem: DictionaryItem.String,
    ): List<Int> = when {
        string.length < stringItem.charList.size -> { emptyList() }
        else -> {
            stringItem.string.toRegex()
                .findAll(string)
                .mapNotNull { matchResult ->
                    matchResult.range.takeIf { range ->
                        charList.subList(range.first, range.last)
                            .all { char -> char.index == null }
                    }?.start
                }
                .toList()
        }
    }

}