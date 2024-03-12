package me.ks.chan.c2pinyin.convert

import me.ks.chan.c2pinyin.convert.encoding.Encoding
import me.ks.chan.c2pinyin.convert.encoding.encoding
import me.ks.chan.c2pinyin.convert.encoding.encodingRange

/**
 * [ENCODING_SPECIAL_ZERO]
 * @access Private to file
 * @field [Int]
 **/
private const val ENCODING_SPECIAL_ZERO = 12295
/**
 * [PINYIN_INDEX_SPECIAL_ZERO]
 * @access Private to file
 * @field [Int]
 **/
private const val PINYIN_INDEX_SPECIAL_ZERO = 176

/**
 * [Char.asIndex]
 * @access Internal to module
 * @extend [Char]
 * @return [Int]
 **/
internal val Char.asIndex: Int?
    get() = when (val code = code) {
        in encodingRange -> { code.asIndex }
        ENCODING_SPECIAL_ZERO -> { PINYIN_INDEX_SPECIAL_ZERO }
        else -> { null }
    }

/**
 * [PADDING_MASK]
 * @access Private to file
 * @field [Int]
 **/
private const val PADDING_MASK = 256

/**
 * [Int.asIndex]
 * @access Private to file
 * @extend [Int]
 * @return [Int]
 **/
private val Int.asIndex: Int
    get() = encoding.index(this)

/**
 * [Encoding.indexOf]
 * @access Private to file
 * @param code [Int]
 * @param charOffset [Int]
 * @param index [Int]
 * @return [Int]
 **/
private fun Encoding.index(
    code: Int,
    charOffset: Int = code - offset,
    index: Int = indexOf(charOffset)
): Int {
    return when {
        requirePadded(charOffset) -> { index or PADDING_MASK
        }
        else -> { index }
    }
}

/**
 * [INDEX_MAX]
 * @access Private to file
 * @field [Int]
 **/
private const val INDEX_MAX = 0xFF

/**
 * [Encoding.indexOf]
 * @access Private to file
 * @param charOffset [Int]
 * @return [Int]
 **/
private fun Encoding.indexOf(charOffset: Int): Int =
    indexes[charOffset].toInt() and INDEX_MAX

/**
 * [Encoding.requirePadded]
 * @access Private to file
 * @param charOffset [Int]
 * @return [Boolean]
 *
 * Check if index should be padded
 **/
private fun Encoding.requirePadded(charOffset: Int): Boolean =
    (offsetPadding(charOffset) and charOffset.bitmask) != 0

/**
 * [PADDING_MAX]
 * @access Private to file
 * @field [Int]
 **/
private const val PADDING_MAX = 8

/**
 * [Encoding.offsetPadding]
 * @access Private to file
 * @extend [Encoding]
 * @param charOffset [Int]
 * @return [Int]
 **/
private fun Encoding.offsetPadding(charOffset: Int): Int =
    paddings[charOffset / PADDING_MAX].toInt()

/**
 * [Int.bitmask]
 * @access Private to file
 * @extend [Int]
 * @return [Int]
 **/
private val Int.bitmask: Int
    get() = 1 shl (this % PADDING_MAX)