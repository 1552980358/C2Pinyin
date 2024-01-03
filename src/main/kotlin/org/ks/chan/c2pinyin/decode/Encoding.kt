package org.ks.chan.c2pinyin.decode

import org.ks.chan.c2pinyin.decode.encoding.EncodingBottom
import org.ks.chan.c2pinyin.decode.encoding.EncodingMiddle
import org.ks.chan.c2pinyin.decode.encoding.EncodingUpper
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

private const val OFFSET_PADDING_START = 0
private const val OFFSET_BASE = 19968
private const val OFFSET_ENCODING_PART_DIFFERENCE = 7000

/**
 * [Encoding]
 * @access Internal
 **/
internal abstract class Encoding {

    private companion object Unused

    /**
     * [Encoding.indexesBase64]
     * @access Protected
     * @return [String]
     *
     * Base64 encoded [ByteArray] of [Encoding.indexes]
     **/
    protected abstract val indexesBase64: String

    /**
     * [Encoding.paddingsBase64]
     * @access Protected
     * @return [String]
     *
     * Base64 encoded [ByteArray] of [Encoding.paddings]
     **/
    protected abstract val paddingsBase64: String

    /**
     * [Encoding.offsetPaddingStart]
     * @return [Int]
     *
     * Start padding for [Encoding.offset] of implementation of [Encoding]
     **/
    open val offsetPaddingStart: Int
        get() = OFFSET_PADDING_START

    /**
     * [Encoding.indexes]
     * @return [ByteArray]
     **/
    val indexes by lazy {
        @OptIn(ExperimentalEncodingApi::class)
        Base64.decode(indexesBase64)
    }

    /**
     * [Encoding.paddings]
     * @return [ByteArray]
     **/
    val paddings by lazy {
        @OptIn(ExperimentalEncodingApi::class)
        Base64.decode(paddingsBase64)
    }

    /**
     * [Encoding.offset]
     * @return [Int]
     **/
    val offset: Int
        get() = OFFSET_BASE + offsetPaddingStart

    /**
     * [Encoding.offsetRange]
     * @return [IntRange]
     **/
    val offsetRange: IntRange
        get() = offset.run {
            this ..< this + OFFSET_ENCODING_PART_DIFFERENCE
        }

}

/**
 * [Int.encoding]
 * @access Internal
 * @return [Encoding]
 *
 * Return matching [Encoding] implementation for [Int]
 **/
internal val Int.encoding: Encoding
    get() = when (this) {
        in EncodingUpper.offsetRange -> EncodingUpper
        in EncodingMiddle.offsetRange -> EncodingMiddle
        else -> EncodingBottom
    }