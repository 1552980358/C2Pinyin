package projekt.cloud.piece.c2.pinyin.util

import projekt.cloud.piece.c2.pinyin.coding.CodingA.CODING_INDEX_A
import projekt.cloud.piece.c2.pinyin.coding.CodingA.CODING_PADDING_A
import projekt.cloud.piece.c2.pinyin.coding.CodingB.CODING_INDEX_B
import projekt.cloud.piece.c2.pinyin.coding.CodingB.CODING_PADDING_B
import projekt.cloud.piece.c2.pinyin.coding.CodingC.CODING_INDEX_C
import projekt.cloud.piece.c2.pinyin.coding.CodingC.CODING_PADDING_C
import projekt.cloud.piece.c2.pinyin.dictionary.Dictionary
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.BIT_MASKS
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CHAR_12295
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_OFFSET_A
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_OFFSET_B
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_MAX
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_MIN
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.EMPTY_STR
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.INDEX_NOT_FOUND
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.PADDING_MASK
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.PINYIN
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.PINYIN_12295
import kotlin.experimental.and
import kotlin.experimental.or

object PinyinUtil {

    internal val dictionary = Dictionary()
    
    @Volatile
    internal var lowercaseEnabled = false
        set(value) {
            if (camelCaseEnabled && value) {
                camelCaseEnabled = false
            }
            field = value
        }
    
    @Volatile
    internal var camelCaseEnabled = false
        set(value) {
            if (lowercaseEnabled && value) {
                lowercaseEnabled = false
            }
            field = value
        }

    internal val Char.pinyinStr get() = when {
        lowercaseEnabled -> lowercase
        camelCaseEnabled -> camelcase
        else -> asPinyin
    }

    internal val String.pinyinArrayList get() = ArrayList<String>().also { resultArrayList ->
        repeat(length) { resultArrayList.add(EMPTY_STR) }
        var searchIndex: Int
        if (dictionary.isNotEmpty()) {
            dictionary.forEach { dictionaryItem ->
                searchIndex = indexOf(dictionaryItem.text)
                if (searchIndex != INDEX_NOT_FOUND) {
                    dictionaryItem.pinyin.forEachIndexed { index, pinyin ->
                        resultArrayList[searchIndex + index] = pinyin
                    }
                }
            }
        }
        resultArrayList.forEachIndexed { index, pinyin ->
            resultArrayList[index] = when {
                pinyin.isBlank() -> this[index].pinyinStr
                else -> pinyin.cases
            }
        }
    }
    
    private val Char.asPinyin get() = when {
        !isChinese -> toString()
        this == CHAR_12295 -> PINYIN_12295
        else -> PINYIN[code]
    }
    
    private val Char.isChinese get() =
        (isInCodingRange && validCode) || this == CHAR_12295

    private val Char.isInCodingRange get() =
        this in CODING_MIN .. CODING_MAX

    private val Char.validCode get() = code > 0

    private val Char.code get() = when (val offset = this - CODING_MIN) {
        in 0 until CODING_OFFSET_A ->
            decodeIndex(CODING_INDEX_A, CODING_PADDING_A, offset)
        in CODING_OFFSET_A until CODING_OFFSET_B ->
            decodeIndex(CODING_INDEX_B, CODING_PADDING_B, offset - CODING_OFFSET_A)
        else ->
            decodeIndex(CODING_INDEX_C, CODING_PADDING_C, offset - CODING_OFFSET_B)
    }

    private fun decodeIndex(codingIndexes: ByteArray, codingPaddings: ByteArray, offset: Int): Int {
        var index = codingIndexes[offset].toShort() and 0xFF.toShort()
        if ((codingPaddings[(offset / 8)].toInt() and BIT_MASKS[offset % 8]) != 0) {
            index = index or PADDING_MASK.toShort()
        }
        return index.toInt()
    }
    
    private val Char.lowercase get() = asPinyin.run { lowercase() }
    
    private val Char.camelcase get() = asPinyin.camelcase
    
    private val String.camelcase get() = first() + substring(1).lowercase()
    
    private val String.cases get() = when {
        lowercaseEnabled -> lowercase()
        camelCaseEnabled -> camelcase
        else -> this
    }
    
}