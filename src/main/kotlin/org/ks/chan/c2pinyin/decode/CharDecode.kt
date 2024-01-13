package org.ks.chan.c2pinyin.decode

import org.ks.chan.c2pinyin.LetterCase
import org.ks.chan.c2pinyin.dictionary.Dictionary

internal fun Char.decodeToPinyinTableIndex(dictionary: Dictionary): Int {
    return dictionary.findWord(this)?.tableIndex ?: tableIndex
}

internal fun Char.decodeToPinyin(letterCase: LetterCase, dictionary: Dictionary): String {
    return decodeToPinyinTableIndex(dictionary)
        .let(PinyinTable::get)
        .let(letterCase::handleCase)
}