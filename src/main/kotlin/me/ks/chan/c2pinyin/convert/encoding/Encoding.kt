package me.ks.chan.c2pinyin.convert.encoding

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * [ENCODING_START]
 * @access Private to file
 * @field [Int]
 **/
private const val ENCODING_START = 19968

/**
 * [ENCODING_END]
 * @access Private to file
 * @field [Int]
 **/
private const val ENCODING_END = 40869

/**
 * [Encoding]
 * @access Internal to module
 **/
internal sealed class Encoding {

    protected companion object {
        /**
         * [Encoding.OFFSET_DIFF]
         * @access Protected to child
         * @field [Int]
         **/
        const val OFFSET_DIFF = 7000
    }

    /**
     * [Encoding.indexesBase64]
     * @access Protected to child
     * @return [String]
     **/
    protected abstract val indexesBase64: String
    /**
     * [Encoding.indexes]
     * @access Internal to module
     * @field [ByteArray]
     **/
    @OptIn(ExperimentalEncodingApi::class)
    val indexes by lazy { Base64.decode(indexesBase64) }

    /**
     * [Encoding.paddingsBase64]
     * @access Protected to child
     * @return [String]
     **/
    protected abstract val paddingsBase64: String
    /**
     * [Encoding.paddings]
     * @access Internal to module
     * @field [ByteArray]
     **/
    @OptIn(ExperimentalEncodingApi::class)
    val paddings by lazy { Base64.decode(paddingsBase64) }

    /**
     * [Encoding.offsetPadding]
     * @access Protected to child
     * @return [Int]
     **/
    protected open val offsetPadding: Int
        get() = 0

    /**
     * [Encoding.offset]
     * @access Internal to module
     * @return [Int]
     **/
    internal val offset: Int
        get() = ENCODING_START + offsetPadding

    /**
     * [Encoding.offsetRange]
     * @access Internal to module
     * @return [IntRange]
     **/
    val offsetRange: IntRange
        get() = offset.run { this ..< this + OFFSET_DIFF }

}

/**
 * [Int.encoding]
 * @access Internal to module
 * @extend [Int]
 * @return [Encoding]
 **/
internal val Int.encoding: Encoding
    get() = when (this) {
        in EncodingUpper.offsetRange -> EncodingUpper
        in EncodingMiddle.offsetRange -> EncodingMiddle
        else -> EncodingBottom
    }

/**
 * [encodingRange]
 * @access Internal to module
 * @return [IntRange]
 **/
internal val encodingRange: IntRange
    get() = ENCODING_START .. ENCODING_END