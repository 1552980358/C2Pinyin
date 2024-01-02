package org.ks.chan.c2pinyin

import org.ks.chan.c2pinyin.decode.PinyinTable
import java.io.File
import java.io.FileNotFoundException

@Suppress("Unused")
val File.asDictionary
    get() = Dictionary.fromFile(this)

class Dictionary(
    private val map: HashMap<String, IntArray> = HashMap()
): MutableMap<String, IntArray> by map {

    companion object Factory {

        @Throws(FileNotFoundException::class, SecurityException::class)
        @JvmStatic
        fun fromFile(file: File) = file.useLines { fromLines(it) }

        @JvmStatic
        fun fromLines(sequence: Sequence<String>) = fromLines(sequence.toList())

        @Suppress("Unused")
        @JvmStatic
        fun fromLines(array: Array<String>) = fromLines(array.asList())

        @JvmStatic
        fun fromLines(lines: List<String>): Dictionary {
            return Dictionary(lines.mapNotNull(transform = ::decodeLine))
        }

    }

    constructor(pairList: List<Pair<String, IntArray>>): this() {
        this += pairList
    }

    operator fun plusAssign(line: String) {
        decodeLine(line = line)?.let {
            this += it
        }
    }

}

private const val KEY_VALUE_SEPARATOR = '='
private const val INDEX_NOT_FOUND = -1
private const val PINYIN_SEPARATOR = '-'
private fun decodeLine(
    line: String,
    keyValueSeparatorIndex: Int = line.indexOf(KEY_VALUE_SEPARATOR)
): Pair<String, IntArray>? {
    if (keyValueSeparatorIndex == INDEX_NOT_FOUND) {
        return null
    }

    // Map into index of PinyinTable
    val pinyinList = line.substring(keyValueSeparatorIndex + 1)
        .trim()
        .split(PINYIN_SEPARATOR)
        .map { PinyinTable.indexOf(it.camelcase) }
    // Check all valid index
    if (!pinyinList.all { it != INDEX_NOT_FOUND }) {
        return null
    }

    // Chinese char
    val key = line.substring(0, keyValueSeparatorIndex)
        .trim()

    // Validate if number of Chinese char equals number of pinyin in list
    return when {
        key.length != pinyinList.size -> null
        else -> key to pinyinList.toIntArray()
    }
}

internal val String.camelcase: String
    get() = when {
        length <= 1 -> uppercase()
        else -> first().uppercase() + substring(1).lowercase()
    }