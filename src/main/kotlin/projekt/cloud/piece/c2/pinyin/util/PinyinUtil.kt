package projekt.cloud.piece.c2.pinyin.util

import projekt.cloud.piece.c2.pinyin.coding.CodingA.CODING_INDEX_A
import projekt.cloud.piece.c2.pinyin.coding.CodingA.CODING_PADDING_A
import projekt.cloud.piece.c2.pinyin.coding.CodingB.CODING_INDEX_B
import projekt.cloud.piece.c2.pinyin.coding.CodingB.CODING_PADDING_B
import projekt.cloud.piece.c2.pinyin.coding.CodingC.CODING_INDEX_C
import projekt.cloud.piece.c2.pinyin.coding.CodingC.CODING_PADDING_C
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.BIT_MASKS
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CHAR_12295
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_OFFSET_A
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_OFFSET_B
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_MAX
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.CODING_MIN
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.PADDING_MASK
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.PINYIN
import projekt.cloud.piece.c2.pinyin.util.ConstantValue.PINYIN_12295
import kotlin.experimental.and
import kotlin.experimental.or

object PinyinUtil {

    internal val Char.pinyinStr get() = when {
        !isChinese -> toString()
        this == CHAR_12295 -> PINYIN_12295
        else -> PINYIN[code]
    }

    internal val String.pinyinArrayList get() = ArrayList<String>().also { arrayList ->
        forEach { arrayList.add(it.pinyinStr) }
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

}