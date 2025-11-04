package me.ks.chan.c2pinyin

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import me.ks.chan.c2pinyin.dictionary.Dictionary
import me.ks.chan.c2pinyin.dictionary.DictionaryBuilder

/**
 * Do not call [C2PinyinBuilder] constructor directly, call [C2Pinyin] or [C2Pinyin.builder] to create instance instead
 * @constructor [Dictionary], [LetterCase.Pair]
 **/
@ConsistentCopyVisibility
data class C2PinyinBuilder
internal constructor(
    @set:JvmSynthetic
    @get:JvmSynthetic
    var dictionary: Dictionary? = null,
    var letterCasePair: LetterCase.Pair = LetterCase.Pair.Default,
    var pinyinStringSymbol: Symbol.PinyinString = Symbol.PinyinString.Default,
    var joinPinyinStringsSymbol: Symbol.JoinPinyinStrings = Symbol.JoinPinyinStrings.Default
) {

    private typealias Self = C2PinyinBuilder

    /**
     * Functional Interface, also Single Abstract Method (SAM), for [C2PinyinBuilder] specially for Java user
     **/
    fun interface BuilderBlock {
        operator fun invoke(self: Self)
    }

    /**
     * Method for using [BuilderBlock] for Java user
     * @param builderBlock [BuilderBlock]
     * @return [C2PinyinBuilder]
     **/
    fun block(builderBlock: BuilderBlock): Self =
        apply(builderBlock::invoke)

    /**
     * Set [dictionary]
     * @return [C2PinyinBuilder]
     **/
    fun setDictionary(setDictionary: Dictionary?): Self = apply {
        dictionary = setDictionary
    }

    /**
     * Kotlin builder lambda for building [Dictionary] instance using [DictionaryBuilder]
     * @param _ [C2PinyinBuilder]
     * @param block {[DictionaryBuilder]->[Unit]} Method block of [DictionaryBuilder]
     **/
    @JvmSynthetic
    context(_: C2PinyinBuilder)
    fun dictionary(block: DictionaryBuilder.() -> Unit) {
        dictionary = DictionaryBuilder().buildBlock(block)
    }

    /**
     * Set [letterCasePair]
     * @param setLetterCasePair [LetterCase.Pair]
     * @return [C2PinyinBuilder]
     **/
    fun setLetterCasePair(setLetterCasePair: LetterCase.Pair): Self = apply {
        letterCasePair = setLetterCasePair
    }

    /**
     * Set [pinyinStringSymbol]
     * @param setPinyinStringSymbol [Symbol.PinyinString]
     * @return [C2PinyinBuilder]
     **/
    fun setPinyinStringSymbol(setPinyinStringSymbol: Symbol.PinyinString): Self = apply {
        pinyinStringSymbol = setPinyinStringSymbol
    }

    /**
     * Set [joinPinyinStringsSymbol]
     * @param setJoinPinyinStringsSymbol [Symbol.JoinPinyinStrings]
     * @return [C2PinyinBuilder]
     **/
    fun setJoinPinyinStringsSymbol(setJoinPinyinStringsSymbol: Symbol.JoinPinyinStrings): Self = apply {
        joinPinyinStringsSymbol = setJoinPinyinStringsSymbol
    }

    /**
     * Complete building and return [C2Pinyin] instance
     * @return [C2Pinyin]
     **/
    fun build(): C2Pinyin = C2PinyinImpl(
        dictionary = dictionary ?: Dictionary()
    )

    /**
     * Internal fast call lambda method for Kotlin method [C2Pinyin]
     **/
    @JvmSynthetic
    internal fun buildBlock(builderBlock: C2PinyinBuilder.() -> Unit): C2Pinyin {
        @OptIn(ExperimentalContracts::class)
        contract { callsInPlace(builderBlock, InvocationKind.EXACTLY_ONCE) }
        builderBlock()
        return C2PinyinImpl(
            dictionary = dictionary ?: Dictionary(),
            letterCasePair = letterCasePair,
            pinyinStringSymbol = pinyinStringSymbol,
            joinPinyinStringsSymbol = joinPinyinStringsSymbol
        )
    }

}