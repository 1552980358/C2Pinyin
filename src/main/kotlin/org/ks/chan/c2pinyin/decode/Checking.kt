package org.ks.chan.c2pinyin.decode

internal val Char.validChinese: Boolean
    get() = code.run { validEncoding || this == ENCODING_SPECIAL_ZERO }

private const val ENCODING_START = 19968
private const val ENCODING_END = 40869
private val Int.validEncoding: Boolean
    get() = this in ENCODING_START .. ENCODING_END

internal const val ENCODING_SPECIAL_ZERO = 12295