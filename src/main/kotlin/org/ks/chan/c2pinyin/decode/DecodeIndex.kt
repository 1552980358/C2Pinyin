package org.ks.chan.c2pinyin.decode

/**
 * [INDEX_INVALID]
 * @access Internal
 *
 * Indicating invalid / unknown index
 **/
internal const val INDEX_INVALID = -1

/**
 * [INDEX_JUMP]
 * @access Internal
 *
 * Indicating this index should be skipped
 **/
internal const val INDEX_JUMP = -2

/**
 * [INDEX_SPECIAL_ZERO]
 *
 * A index of pinyin table of a special zero with code [ENCODING_SPECIAL_ZERO]
 **/
private const val INDEX_SPECIAL_ZERO = 176

/**
 * [Char.tableIndex]
 * @access Internal
 * @return [Int], or [INDEX_INVALID]
 *
 * Convert [Char] into index of pinyin table; or
 * return [INDEX_INVALID] if not a valid Chinese character
 **/
internal val Char.tableIndex: Int
    get() = when {
        validChinese -> when (val code = code) {
            ENCODING_SPECIAL_ZERO -> INDEX_SPECIAL_ZERO
            else -> code.pinyinIndex()
        }
        else -> INDEX_INVALID
    }

/**
 * [Int.pinyinIndex]
 * @access Private
 * @param encoding [Encoding]
 * @param offset [Int]
 * @param indexRaw [Int]
 * @return [Int]
 *
 * Convert [Char.code] into index of pinyin table
 **/
private fun Int.pinyinIndex(
    encoding: Encoding = this.encoding,
    offset: Int = this - encoding.offset,
    indexRaw: Int = encoding.calculateIndex(offset)
): Int {
    return when {
        encoding.checkPaddingCorrection(offset) -> {
            indexRaw or PADDING_MASK
        }
        else -> indexRaw
    }
}

/**
 * [INDEX_MAX]
 * @access Private
 *
 * Maximum index of pinyin table
 **/
private const val INDEX_MAX = 0xFF

/**
 * [PADDING_MAX]
 * @access Private
 *
 * Maximum padding of pinyin table
 **/
private const val PADDING_MAX = 8

/**
 * [Encoding.calculateIndex]
 * @access Private
 * @param charOffset [Int]
 * @return [Int]
 *
 * Calculate index of pinyin table
 **/
private fun Encoding.calculateIndex(charOffset: Int): Int {
    return indexes[charOffset].toInt() and INDEX_MAX
}

/**
 * [PADDING_MASK]
 * @access Private
 *
 * Mask for padding of pinyin table
 **/
private const val PADDING_MASK = 256

/**
 * [Encoding.padding]
 * @access Private
 * @param charOffset [Int]
 * @return [Int]
 *
 * Calculate index for [Encoding.paddings] and return padding
 **/
private fun Encoding.padding(charOffset: Int): Int {
    return paddings[charOffset / PADDING_MAX].toInt()
}

/**
 * [Int.bitMask]
 * @access Private
 * @return [Int]
 *
 * Calculate bit mask
 **/
private val Int.bitMask: Int
    get() = 1 shl (this % PADDING_MAX)

/**
 * [Encoding.checkPaddingCorrection]
 * @access Private
 * @param charOffset [Int]
 * @return [Boolean]
 *
 * Check if index should be padded
 **/
private fun Encoding.checkPaddingCorrection(charOffset: Int): Boolean {
    return (padding(charOffset) and charOffset.bitMask) != 0
}