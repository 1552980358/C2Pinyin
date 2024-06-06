package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem

data class PinyinString(
    val string: String,
    val charList: List<PinyinChar> = string.map(::PinyinChar)
): List<PinyinChar> by charList

data class PinyinChar(
    val char: Char,
    var index: Int? = null,
)

internal fun PinyinString.indexListOf(charItem: DictionaryItem.Char): List<Int> {
    return charList.mapIndexedNotNull { index, item ->
        index.takeIf {
            item.index == null && item.char == charItem.char
        }
    }
}

internal fun PinyinString.indexListOf(
    stringItem: DictionaryItem.String,
): List<Int> = when {
    string.length < stringItem.charList.size -> {
        emptyList()
    }
    else -> {
        stringItem.string
            .toRegex()
            .findAll(string)
            .toMutableList()
            .mapNotNull { matchResult ->
                matchResult.range
                    .takeIf {
                        subList(it.first, it.last)
                            .all { range -> range.index == null }
                    }
                    ?.start
            }
    }
}