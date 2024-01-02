package org.ks.chan.c2pinyin.decode

internal const val INDEX_INVALID = -1
private const val INDEX_SPECIAL_ZERO = 176

internal val Char.tableIndex: Int
    get() = when {
        validChinese -> when (val code = code) {
            ENCODING_SPECIAL_ZERO -> INDEX_SPECIAL_ZERO
            else -> code.pinyinIndex()
        }
        else -> INDEX_INVALID
    }

private fun Int.pinyinIndex(
    encoding: Encoding = this.encoding,
    charOffset: Int = this - encoding.offset,
    indexRaw: Int = encoding.calculateIndex(charOffset)
): Int {
    return when {
        encoding.checkPaddingCorrection(charOffset) -> {
            indexRaw or PADDING_MASK
        }
        else -> indexRaw
    }
}

private const val INDEX_MAX = 0xFF
private const val PADDING_MAX = 0xF

private fun Encoding.calculateIndex(charOffset: Int): Int {
    return indexes[charOffset].toInt() and INDEX_MAX
}

private const val PADDING_MASK = 256

private fun Encoding.padding(charOffset: Int): Int {
    return paddings[charOffset / PADDING_MAX].toInt()
}

private val bitMasks: IntArray
    get() = intArrayOf(1, 2, 4, 8, 16, 32, 64, 128)
private val Int.bitMask: Int
    get() = bitMasks[this % PADDING_MAX]

private fun Encoding.checkPaddingCorrection(charOffset: Int): Boolean {
    return (padding(charOffset) and charOffset.bitMask) != 0
}