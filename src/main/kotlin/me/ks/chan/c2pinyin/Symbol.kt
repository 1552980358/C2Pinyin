package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.resources.pinyin.Pinyin

private const val Symbol_Blank = ""
private const val Symbol_Space = " "
private const val Symbol_Hyphen = "-"
private const val Symbol_Underscore = "_"

/**
 * A sealed class indicating separator for joining [Pinyin] strings
 **/
sealed class Symbol {

    /**
     * @param prefix [Symbol] (Default: [Blank]) Put [str] before each [Pinyin]
     * @param separator [Symbol] (Default: [Underscore]) Put [str] after [Pinyin.initial] and before [Pinyin.vowel]
     * @param vowels [Symbol] (Default: [Underscore]) Put [str] between each [vowels] string element
     * @param postfix [Symbol] (Default: [Blank]) Put [str] after each [Pinyin]
     * @constructor [Symbol], [Symbol], [Symbol], [Symbol]
     **/
    data class PinyinString(
        val prefix: Symbol = Blank,
        val separator: Symbol = Hyphen,
        val vowels: Symbol = Underscore,
        val postfix: Symbol = Blank,
    ) {

        companion object {
            /**
             * default instance of [Symbol.PinyinString]
             **/
            @JvmStatic
            val Default by lazy(Symbol::PinyinString)
        }

    }

    /**
     * @param prefix [Symbol] (Default: [Blank]) Put [str] at the beginning of string
     * @param pinyin [Symbol] (Default: [Space]) Put [str] to end of each [Pinyin] right after [PinyinString.postfix]
     * @param postfix [Symbol] (Default: [Blank]) Put [str] at the end of string
     * @constructor [Symbol], [Symbol], [Symbol], [Symbol]
     **/
    data class JoinPinyinStrings(
        val prefix: Symbol = Blank, val pinyin: Symbol = Space, val postfix: Symbol = Blank,
    ) {

        companion object {
            /**
             * default instance of [Symbol.JoinPinyinStrings]
             **/
            @JvmStatic
            val Default by lazy(Symbol::JoinPinyinStrings)
        }

    }

    /**
     * The separator string instance
     **/
    internal abstract val str: String

    /**
     * Preset blank "" (nothing) separator
     **/
    data object Blank: Symbol() {
        override val str: String
            get() = Symbol_Blank
    }

    /**
     * Preset space " " separator
     **/
    data object Space: Symbol() {
        override val str: String
            get() = Symbol_Space
    }

    /**
     * Preset underscore "`-`" separator
     **/
    data object Hyphen: Symbol() {
        override val str: String
            get() = Symbol_Hyphen
    }

    /**
     * Preset underscore "`_`" separator
     **/
    data object Underscore: Symbol() {
        override val str: String
            get() = Symbol_Underscore
    }

    /**
     * Custom customized separator string
     * @param str [String] Custom separator string
     * @constructor [String]
     **/
    data class Custom(override val str: String): Symbol()

    /**
     * Return [str] so that [StringBuilder] can [StringBuilder.append] symbol [str] into output [String] directly
     * @return [String]
     **/
    override fun toString(): String = str

}