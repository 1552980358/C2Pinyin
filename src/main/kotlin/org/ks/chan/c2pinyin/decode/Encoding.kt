package org.ks.chan.c2pinyin.decode

import org.ks.chan.c2pinyin.decode.encoding.EncodingBottom
import org.ks.chan.c2pinyin.decode.encoding.EncodingMiddle
import org.ks.chan.c2pinyin.decode.encoding.EncodingUpper
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

private const val OFFSET_PADDING_START = 0
private const val OFFSET_BASE = 19968
private const val OFFSET_ENCODING_PART_DIFFERENCE = 7000

internal abstract class Encoding {

    private companion object Unused

    protected abstract val indexesBase64: String
    protected abstract val paddingsBase64: String

    open val offsetPaddingStart: Int
        get() = OFFSET_PADDING_START

    @OptIn(ExperimentalEncodingApi::class)
    val indexes by lazy { Base64.decode(indexesBase64) }

    @OptIn(ExperimentalEncodingApi::class)
    val paddings by lazy { Base64.decode(paddingsBase64) }

    val offset: Int
        get() = OFFSET_BASE + offsetPaddingStart

    val offsetRange: IntRange
        get() = offset.run {
            this ..< this + OFFSET_ENCODING_PART_DIFFERENCE
        }

}

internal val Int.encoding: Encoding
    get() = when (this) {
        in EncodingUpper.offsetRange -> EncodingUpper
        in EncodingMiddle.offsetRange -> EncodingMiddle
        else -> EncodingBottom
    }