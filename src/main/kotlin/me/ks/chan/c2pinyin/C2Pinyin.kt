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
     * Call to translate param [text] with [C2Pinyin.dictionary] returning [Translator] as result
     * @param text [String]
     * @return [Translator]
     **/
    fun translate(text: String, letterCasePair: LetterCase.Pair = this.letterCasePair): Translator

}

/**
 * Internal implementation class for [C2Pinyin]
 * @constructor [Dictionary], [LetterCasePair]
 **/
internal data class C2PinyinImpl(
    override val dictionary: Dictionary = Dictionary(),
    override var letterCasePair: LetterCase.Pair = LetterCase.Pair.Default,
): C2Pinyin {

    override fun translate(text: String, letterCasePair: LetterCase.Pair): Translator {
        return Translator(text, dictionary, letterCasePair)
    }

}