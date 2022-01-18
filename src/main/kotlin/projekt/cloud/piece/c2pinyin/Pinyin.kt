package projekt.cloud.piece.c2pinyin

import kotlin.experimental.and
import kotlin.experimental.or

@Suppress("unused")
class Pinyin private constructor() {
    companion object {

        @Suppress("unused")
        @JvmStatic
        fun getPinyin(c: Char) = c.pinyin
    
        @Suppress("unused")
        @JvmStatic
        fun getPinyin(s: String) = s.pinyin

        @Suppress("unused")
        @JvmStatic
        fun getPinyin(s: String, divider: String) = s.pinyin(divider)
    
        @Suppress("unused")
        @JvmStatic
        fun getPinyin(s: String, divider: Char) = s.pinyin(divider)
    
        @Suppress("unused")
        @JvmStatic
        fun isChinese(c: Char) = c.isChinese

    }
    
    init { throw IllegalAccessException() }
    
}

val Char.pinyin get() = when {
    !isChinese -> toString()
    this == CHAR_12295 -> PINYIN_12295
    else -> PINYIN_TABLE[pinyinCode]
}

@Suppress("unused")
val String.pinyin: String get() = when {
    isEmpty() -> EMPTY_STR
    else -> StringBuilder().also { stringBuilder -> forEach { stringBuilder.append(it.pinyin) } }.toString()
}

fun String.pinyin(divider: String): String {
    if (isEmpty()) {
        return EMPTY_STR
    }
    if (length == 1) {
        return first().pinyin
    }
    return StringBuilder(first().pinyin).also {
        for (i in 1 until length) {
            it.append(divider).append(this[i].pinyin)
        }
    }.toString()
}

@Suppress("unused")
fun String.pinyin(divider: Char = ' ') = pinyin(divider.toString())

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
    var index = indexes[offset].toShort() and 0xFF.toShort()
    if ((paddings[(offset / 8)].toInt() and BIT_MASKS[offset % 8]) != 0) {
        index = index or PADDING_MASK.toShort()
    }
    return index.toInt()
}