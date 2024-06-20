package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.convert.pinyinRaw
import me.ks.chan.c2pinyin.dictionary.item.Word
import me.ks.chan.c2pinyin.dictionary.item.Phrase

internal data class PinyinChar(
    val char: Char,
    var pinyin: Int? = null,
) {
    val pinyinStr: String
        get() = pinyin?.pinyinRaw ?: string

    private val string: String
        get() = char.toString()

    internal inline val unspecified: Boolean
        get() = pinyin == null
}

data class C2String internal constructor(
    internal val text: String,
    internal val charList: List<PinyinChar> = text.map(::PinyinChar),
): List<PinyinChar> by charList {

    internal companion object Construct {
         fun new(text: String) = C2String(text)
    }

    internal inline fun findPhaseIndex(
        dictionaryString: Phrase,
        block: (Int) -> Unit
    ) {
        if (text.length >= dictionaryString.size) {
            dictionaryString.string
                .toRegex()
                .findAll(text)
                .forEach { matchResult ->
                    matchResult.range
                        .takeIf { slice(it).all(PinyinChar::unspecified) }
                        ?.first
                        ?.let(block)
                }
        }
    }

    internal inline fun findWordIndex(
        word: Word,
        block: (Int) -> Unit,
    ) {
        charList.forEachIndexed { index, item ->
            if (item.pinyin == null && item.char == word.char) {
                block(index)
            }
        }
    }

    internal inline fun allUnspecified(block: (PinyinChar) -> Unit) {
        forEach { pinyinChar ->
            if (pinyinChar.unspecified) {
                block(pinyinChar)
            }
        }
    }

    fun list(): List<String> {
        return charList.map(PinyinChar::pinyinStr)
    }

}