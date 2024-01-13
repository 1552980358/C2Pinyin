package org.ks.chan.c2pinyin.dictionary

import org.ks.chan.c2pinyin.camelcase
import org.ks.chan.c2pinyin.decode.INDEX_JUMP
import org.ks.chan.c2pinyin.decode.PinyinTable

/**
 * [Word]
 * @access Internal
 * @param char [Char]
 * @param tableIndex [Int]
 **/
data class Word internal constructor(
    /**
     * [Word.char]
     **/
    val char: Char,
    /**
     * [Word.tableIndex]
     **/
    val tableIndex: Int
) {

    /**
     * [Word]
     * @param char [Char]
     * @param pinyin [String]
     **/
    @Suppress("Unused")
    constructor(char: Char, pinyin: String): this(
        char, PinyinTable.indexOf(pinyin.camelcase)
    )

    /**
     * [Word]
     * @param char [Char]
     **/
    @Suppress("Unused")
    constructor(char: Char): this(char, INDEX_JUMP)

    /**
     * [Word.string]
     *
     * Convert [Word.char] into [String]
     **/
    val string: String
        get() = char.toString()

}