package org.ks.chan.c2pinyin.decode

import org.ks.chan.c2pinyin.LetterCase
import org.ks.chan.c2pinyin.dictionary.Dictionary

internal fun Char.decodeToPinyinTableIndex(dictionary: Dictionary): Int {
    return dictionary.findWord(this)?.tableIndex ?: tableIndex
}

internal fun Char.decodeToPinyin(letterCase: LetterCase, dictionary: Dictionary): String {
    return when (val tableIndex = decodeToPinyinTableIndex(dictionary)) {
        INDEX_JUMP -> toString()
        else -> PinyinTable[tableIndex].let(letterCase::handleCase)
    }
}