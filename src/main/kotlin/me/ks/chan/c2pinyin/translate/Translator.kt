package me.ks.chan.c2pinyin.translate

import me.ks.chan.c2pinyin.LetterCase
import me.ks.chan.c2pinyin.Symbol
import me.ks.chan.c2pinyin.dictionary.Dictionary
import me.ks.chan.c2pinyin.dictionary.Record

/**
 * Result translator class for translating Chinese characters into Chinese-Pinyin paired list
 * @param text [String]
 * @param dictionary [Dictionary]
 * @param letterCasePair [LetterCase.Pair]
 * @param pinyinStringSymbol [Symbol.PinyinString]
 * @param joinPinyinStringsSymbol [Symbol.JoinPinyinStrings]
 * @constructor [String], [Dictionary], [LetterCase.Pair], [Symbol.PinyinString], [Symbol.JoinPinyinStrings]
 **/
open class Translator(
    text: String,
    dictionary: Dictionary,
    @Suppress("unused")
    private val letterCasePair: LetterCase.Pair,                    // TODO: Placeholder global value
    @Suppress("unused")
    private val pinyinStringSymbol: Symbol.PinyinString,            // TODO: Placeholder global value
    @Suppress("unused")
    private val joinPinyinStringsSymbol: Symbol.JoinPinyinStrings,  // TODO: Placeholder global value
) {

    private val charStateList: List<CharState>

    init {
        if (text.isEmpty()) {
            throw IllegalArgumentException("Text must not be empty")
        }

        charStateList = text(dictionary)
        if (!charStateList.none(CharState::isAccessed)) {
            throw IllegalStateException("Unexpected unaccessed translated state")
        }
    }

}

/**
 * Create a [MutableList] by mapping all characters in [String] into [CharState.Raw]
 * @param this [String]
 * @return [MutableList]<[CharState]>
 **/
private inline val String.RawCharStateList: MutableList<CharState>
    get() = mapTo(ArrayList(length), CharState::Raw)

/**
 * Placeholder space [Char] for marking accessed characters during translation
 **/
private const val Char_AccessedPlaceholder = ' '

/**
 * Translate [String] into [MutableList]<[CharState]> by using (1) [Dictionary] instance [dictionary] matching and (2) [Char.pinyinIndex] checking
 * @param this [String]
 * @return [MutableList]<[CharState]>
 **/
private operator fun String.invoke(dictionary: Dictionary): MutableList<CharState> =
    RawCharStateList.apply {
        dictionary.translate()
        translate()
    }

/**
 * Browse [this] ([Dictionary]) instance to match added [Record] and replace [CharState.Raw] into [CharState.Translated]
 * @param str [String]
 * @param charStateList [MutableList]<[CharState]>
 * @param this [Dictionary]
 **/
context(str: String, charStateList: MutableList<CharState>)
private fun Dictionary.translate() = StringBuilder(str).let { stringBuilder ->
    var text = str

    recordList.forEach { record ->
        if (text.length >= record.length) {
            record.text.toRegex().findAll(text)
                .map(MatchResult::range)
                .takeIf(Sequence<IntRange>::any)
                ?.onEach { range ->
                    // l: IntRange `forEachIndexed` element index
                    // i: IntRange content element
                    range.forEachIndexed { l: Int, i: Int ->
                        // Set all matched text into placeholder prevent re-matched
                        stringBuilder[i] = Char_AccessedPlaceholder
                        // Set char list targeted element as translated
                        val (char, index) = record[l]
                        charStateList[i] = CharState.Translated(char, index)
                    }
                }
                // Update [str] to latest text, if any
                ?.also { text = stringBuilder.toString() }
        }
    }
}

/**
 * Map non-[Accessed] elements into [CharState.Translated] with checking using contract function [CharState.isAccessed]
 * @param this [MutableList]<[CharState]>
 **/
private fun MutableList<CharState>.translate() {
    /**
     * The [MutableList.replaceAll] will trigger [MutableListIterator.set] on all elements by [Iterator] even
     * there is a false replacement condition
     **/
    // replaceAll { charState ->
    //     when {
    //         !charState.isAccessed -> {
    //             when (val index = charState.char.pinyinIndex) {
    //                 null -> { CharState.Unchanged(charState.char) }
    //                 else -> { CharState.Translated(charState.char, index) }
    //             }
    //         }
    //         else -> { charState }
    //     }
    // }
    listIterator().let { iterator ->
        iterator.forEach { charState ->
            if (!charState.isAccessed) {
                iterator.set(
                    when (val index = charState.char.pinyinIndex) {
                        null -> { CharState.Unchanged(charState.char) }
                        else -> { CharState.Translated(charState.char, index) }
                    }
                )
            }
        }
    }
}