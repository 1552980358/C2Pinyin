package org.ks.chan.c2pinyin

internal val GlobalInstance by lazy { C2Pinyin() }

/**
 * [Char.pinyin]
 * @return [String]
 *
 *
 * Convert [Char] into [String] pinyin with configurations from [GlobalInstance]
 **/
val Char.pinyin: String
    get() = GlobalInstance.char(this)

/**
 * [Char.pinyin]
 * @param letterCase [LetterCase]
 * @return [String]
 *
 * Convert [Char] into [String] pinyin with configurations from [GlobalInstance]
 **/
fun Char.pinyin(letterCase: LetterCase = GlobalInstance.letterCase): String {
    return GlobalInstance.char(this, letterCase = letterCase)
}

/**
 * [String.pinyin]
 * @return [String][List]
 *
 * Convert [String] into [List] of [String] pinyin with configurations from [GlobalInstance]
 **/
val String.pinyin: List<String>
    get() = GlobalInstance.string(this)

/**
 * [String.pinyin]
 * @param letterCase [LetterCase]
 * @return [String][List]
 *
 * Convert [String] into [List] of [String] pinyin with configurations from [GlobalInstance]
 **/
fun String.pinyin(letterCase: LetterCase = GlobalInstance.letterCase): List<String> {
    return GlobalInstance.string(this, letterCase = letterCase)
}
