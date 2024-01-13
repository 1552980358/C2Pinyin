package org.ks.chan.c2pinyin.decode

import org.ks.chan.c2pinyin.LetterCase
import org.ks.chan.c2pinyin.dictionary.Dictionary

/**
 * [Char.decodeToPinyinTableIndex]
 * @access Internal
 * @param dictionary [Dictionary]
 * @return [Int]
 *
 * Convert [Char] into index of pinyin table
 **/
internal fun Char.decodeToPinyinTableIndex(dictionary: Dictionary): Int {
    return dictionary.findWord(this)?.tableIndex ?: tableIndex
}

/**
 * [Char.decodeToPinyin]
 * @access Internal
 * @param letterCase [LetterCase]
 * @param dictionary [Dictionary]
 * @return [String]
 *
 * Convert [Char] into pinyin [String]
 **/
internal fun Char.decodeToPinyin(letterCase: LetterCase, dictionary: Dictionary): String {
    return when (val tableIndex = decodeToPinyinTableIndex(dictionary)) {
        INDEX_JUMP -> toString()
        else -> PinyinTable[tableIndex].let(letterCase::handleCase)
    }
}