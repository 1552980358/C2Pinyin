package org.ks.chan.c2pinyin

import org.ks.chan.c2pinyin.decode.INDEX_INVALID
import org.ks.chan.c2pinyin.decode.PinyinTable
import org.ks.chan.c2pinyin.decode.tableIndex
import org.ks.chan.c2pinyin.decode.validChinese

internal fun String.decodeToPinyinList(
    letterCase: LetterCase,
    dictionary: Dictionary,
    indexArray: IntArray = IntArray(length) { INDEX_INVALID }
): List<String> {
    // Find and set value as dictionary
    if (dictionary.isNotEmpty()) {
        dictionary.forEach { line, pinyin ->
            validate(line = line, indexArray = indexArray) { startIndex ->
                pinyin.forEachIndexed { index, tableIndex ->
                    indexArray[startIndex + index] = tableIndex
                }
            }
        }
    }
    // Fill all `-1`ed element
    forEachIndexed { index, char ->
        if (indexArray[index] == INDEX_INVALID && char.validChinese) {
            indexArray[index] = char.tableIndex
        }
    }
    // Handle convert into pinyin, or copy that char as string
    return indexArray.mapIndexed { index, tableIndex ->
        when (tableIndex) {
            INDEX_INVALID -> { this[index].toString() }
            else -> { PinyinTable[tableIndex] }
        }
    }.map(letterCase::handleCase)
}

private fun String.validate(
    line: String,
    indexArray: IntArray,
    // Make it reusable
    startIndex: Int = indexOf(line),
    validAction: (Int) -> Unit,
) {
    // Do a valid check
    if (validateCheck(line = line, indexArray = indexArray, startIndex = startIndex)) {
        validAction(startIndex)
    }
}

private fun validateCheck(
    line: String, indexArray: IntArray, startIndex: Int,
): Boolean {
    return indexArray.copyOfRange(startIndex, startIndex + line.length)
        .all { it == INDEX_INVALID }
}