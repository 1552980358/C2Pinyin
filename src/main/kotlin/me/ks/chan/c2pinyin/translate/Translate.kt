package me.ks.chan.c2pinyin.translate

import me.ks.chan.c2pinyin.mapping.Binary

private const val ZeroCircle_UTF8Code = 0x3007
private const val ZeroCircle_PinyinIndex = 176

private const val Padding_Max = 0x8
private const val Padding_Mask = 0x100
private const val Padding_NoMask = 0x0
private const val Index_Max = 0xFF

internal val Char.pinyinIndex: Int?
    get() = when (val code = code) {
        ZeroCircle_UTF8Code -> { ZeroCircle_PinyinIndex }
        in Binary -> {
            Binary[code].let { binary ->
                val offset = code + -binary

                /**
                 * Check if index require being padded
                 *
                 * binary\[offset\]: See [Binary.get]
                 * binary % offset: See [Binary.rem]
                 **/
                binary[offset] or (if (binary % offset) { Padding_Mask } else { Padding_NoMask })
            }
        }
        else -> { null }
    }

/**
 * Get pinyin index from [Binary.indexes]
 **/
private operator fun Binary.get(offset: Int): Int {
    return indexes[offset].toInt() and Index_Max
}

/**
 * Check require padded from [Binary.paddings]
 **/
private operator fun Binary.rem(offset: Int): Boolean {
    return paddings[offset / Padding_Max].toInt() and (1 shl (offset % Padding_Max)) != 0
}