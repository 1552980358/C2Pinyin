package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.dictionary.Dictionary
import me.ks.chan.c2pinyin.translate.Translator

/**
 * Quick constructor method for [C2Pinyin]
 * @param builderBlock {[C2PinyinBuilder]->[Unit]}
 **/
@JvmSynthetic
fun C2Pinyin(builderBlock: C2PinyinBuilder.() -> Unit): C2Pinyin =
    C2PinyinBuilder().buildBlock(builderBlock)

/**
 * Global configuration interface. Call [C2Pinyin].* directly change global configuration, but
 * for Java user, use [C2Pinyin.Global].* to change global configuration.
 **/
interface C2Pinyin {

    /**
     * Global instance of [C2Pinyin]. The only entry for Java user.
     **/
    companion object Global: C2Pinyin by C2PinyinImpl() {

        /**
         * A builder class for [C2Pinyin] instance in Java, call `[C2Pinyin] { /*...*/ }` in Kotlin.
         *
         * Call [C2PinyinBuilder.build] to finalize building and get instance
         *
         * @return [C2PinyinBuilder]
         **/
        @JvmStatic
        fun builder(): C2PinyinBuilder = C2PinyinBuilder()

    }

    /**
     * Independent [Dictionary] instance for each [C2Pinyin] instance
     **/
    val dictionary: Dictionary

    /**
     * Independent [LetterCase.Pair] instance for each [C2Pinyin] instance
     **/
    var letterCasePair: LetterCase.Pair

    /**
     * Independent [Symbol.PinyinString] instance for each [C2Pinyin] instance
     **/
    var pinyinStringSymbol: Symbol.PinyinString

    /**
     * Independent [Symbol.JoinPinyinStrings] instance for each [C2Pinyin] instance
     **/
    var joinPinyinStringsSymbol: Symbol.JoinPinyinStrings

    /**
     * Call to translate param [text] with [C2Pinyin.dictionary] returning [Translator] as result
     * @param text [String]
     * @param letterCasePair [LetterCase.Pair]
     * @param pinyinStringSymbol [Symbol.PinyinString]
     * @param joinPinyinStringsSymbol [Symbol.JoinPinyinStrings]
     * @return [Translator]
     **/
    fun translate(
        text: String,
        letterCasePair: LetterCase.Pair = this.letterCasePair,
        pinyinStringSymbol: Symbol.PinyinString = this.pinyinStringSymbol,
        joinPinyinStringsSymbol: Symbol.JoinPinyinStrings = this.joinPinyinStringsSymbol
    ): Translator

}

/**
 * Internal implementation class for [C2Pinyin]
 * @constructor [Dictionary], [LetterCase.Pair]
 **/
internal data class C2PinyinImpl(
    override val dictionary: Dictionary = Dictionary(),
    override var letterCasePair: LetterCase.Pair = LetterCase.Pair.Default,
    override var pinyinStringSymbol: Symbol.PinyinString = Symbol.PinyinString.Default,
    override var joinPinyinStringsSymbol: Symbol.JoinPinyinStrings = Symbol.JoinPinyinStrings.Default,
): C2Pinyin {

    /**
     * @see C2Pinyin.translate
     **/
    override fun translate(
        text: String,
        letterCasePair: LetterCase.Pair,
        pinyinStringSymbol: Symbol.PinyinString,
        joinPinyinStringsSymbol: Symbol.JoinPinyinStrings
    ): Translator {
        return Translator(text, dictionary, letterCasePair, pinyinStringSymbol, joinPinyinStringsSymbol)
    }

}