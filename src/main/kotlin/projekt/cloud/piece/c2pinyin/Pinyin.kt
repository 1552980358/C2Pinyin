package projekt.cloud.piece.c2pinyin

import kotlin.experimental.and
import kotlin.experimental.or

val Char.pinyin get() = when {
    !isChinese -> toString()
    this == CHAR_12295 -> PINYIN_12295
    else -> PINYIN_TABLE[pinyinCode]
}

val Char.isChinese: Boolean get() =
    (this in MIN_VALUE .. MAX_VALUE && pinyinCode > 0) || this == CHAR_12295

private val Char.pinyinCode: Int get() {
    val offset = this - MIN_VALUE
    if (offset in 0 until PINYIN_CODE_1_OFFSET) {
        return decodeIndex(PINYIN_CODE_PADDING_1, PINYIN_CODE_1, offset)
    }
    if (offset in PINYIN_CODE_1_OFFSET until PINYIN_CODE_2_OFFSET) {
        return decodeIndex(PINYIN_CODE_PADDING_2, PINYIN_CODE_2, offset - PINYIN_CODE_1_OFFSET)
    }
    return decodeIndex(PINYIN_CODE_PADDING_3, PINYIN_CODE_3, offset - PINYIN_CODE_2_OFFSET)
}

private fun decodeIndex(paddings: ByteArray, indexes: ByteArray, offset: Int): Int {
    var index = (indexes[offset] and 0xFF.toByte()).toShort()
    if ((paddings[offset / 8] and BIT_MASKS[offset % 8].toByte()) != 0.toByte()) {
        index = index or PADDING_MASK.toShort()
    }
    return index.toInt()
}