package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.convert.pinyinRaw
import me.ks.chan.c2pinyin.dictionary.item.DictionaryChar
import me.ks.chan.c2pinyin.dictionary.item.DictionaryString

internal data class PinyinChar(
    val char: Char,
    var index: Int? = null,
) {
    val pinyin: String
        get() = index?.pinyinRaw ?: string

    private val string: String
        get() = char.toString()

    internal inline val unspecified: Boolean
        get() = index == null
}

data class C2String internal constructor(
    internal val string: String,
    internal val charList: List<PinyinChar> = string.map(::PinyinChar),
): List<PinyinChar> by charList {

    internal companion object {
         fun new(text: String) = C2String(text)
    }

    internal inline fun findStringIndex(
        dictionaryString: DictionaryString,
        block: (Int) -> Unit
    ) {
        if (string.length >= dictionaryString.size) {
            dictionaryString.string
                .toRegex()
                .findAll(string)
                .forEach { matchResult ->
                    matchResult.range
                        .takeIf { slice(it).all(PinyinChar::unspecified) }
                        ?.first
                        ?.let(block)
                }
        }
    }

    internal inline fun findCharIndex(
        dictionaryChar: DictionaryChar,
        block: (Int) -> Unit,
    ) {
        charList.forEachIndexed { index, item ->
            if (item.index == null && item.char == dictionaryChar.char) {
                block(index)
            }
        }
    }

    fun list(): List<String> {
        return charList.map(PinyinChar::pinyin)
    }

}