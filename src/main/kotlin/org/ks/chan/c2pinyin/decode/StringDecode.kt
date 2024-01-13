package org.ks.chan.c2pinyin.decode

import org.ks.chan.c2pinyin.LetterCase
import org.ks.chan.c2pinyin.dictionary.Dictionary

/**
 * [String.decodeToPinyinList]
 * @access Internal
 * @param letterCase [LetterCase]
 * @param dictionary [Dictionary]
 * @param indexArray [IntArray]
 * @return [String][List]
 *
 * Responsible for decode [String] into pinyin [List] of [String]
 **/
internal fun String.decodeToPinyinList(
    letterCase: LetterCase,
    dictionary: Dictionary,
    indexArray: IntArray = IntArray(length) { INDEX_INVALID }
): List<String> {
    // Loop through each phase
    dictionary.allPhases { phase ->
        // Check if any phase exists in string
        validate(phase = phase.text, indexArray = indexArray) { startIndex ->
            // Replace all index
            phase.forEachIndexed { index, word ->
                indexArray[startIndex + index] = word.tableIndex
            }
        }
    }

    // Find if word is in dictionary
    forEachIndexed { index, char ->
        if (indexArray[index] == INDEX_INVALID) {
            indexArray[index] = dictionary.findWord(char)?.tableIndex ?: char.tableIndex
        }
    }

    // Handle convert into pinyin, or copy that char as string
    return indexArray.mapIndexed { index, tableIndex ->
        when (tableIndex) {
            INDEX_INVALID, INDEX_JUMP -> { this[index].toString() }
            else -> { PinyinTable[tableIndex].let(letterCase::handleCase) }
        }
    }
}

/**
 * [String.validate]
 * @access Private
 * @param phase [String]
 * @param indexArray [IntArray]
 * @param startIndex [Int]
 * @param validAction callback action when [validateCheck] returns true
 *
 * Check if ranged [startIndex] to [String.length] of [phase] in [indexArray] is [INDEX_INVALID]
 **/
private fun String.validate(
    phase: String,
    indexArray: IntArray,
    // Make it reusable
    startIndex: Int = indexOf(phase),
    validAction: (Int) -> Unit,
) {
    // Do a valid check
    if (validateCheck(phase, indexArray, startIndex)) {
        validAction(startIndex)
    }
}

/**
 * [validateCheck]
 * @access Private
 * @param phase [String]
 * @param indexArray [IntArray]
 * @param startIndex
 *
 * Actual implementation of checking of [String.validate]
 **/
private fun validateCheck(
    phase: String, indexArray: IntArray, startIndex: Int,
): Boolean {
    // Ensure all are `-1`
    return indexArray.copyOfRange(startIndex, startIndex + phase.length)
        .all { it == INDEX_INVALID }
}