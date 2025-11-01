package me.ks.chan.c2pinyin.translate

import me.ks.chan.c2pinyin.LetterCase
import me.ks.chan.c2pinyin.dictionary.Dictionary

open class Translator(
    text: String,
    dictionary: Dictionary,
    private val letterCasePair: LetterCase.Pair, // TODO: Placeholder global value
) {

    private val charStateList: List<CharState>

    init {
        if (text.isEmpty()) {
            throw IllegalArgumentException("Text must not be empty")
        }

        charStateList = text.asRawCharStateMutableList
            .apply { dictionary.browseRecordList(text) }
            .apply(MutableList<CharState>::translate)
        if (!charStateList.none(CharState::isAccessed)) {
            throw IllegalStateException("Unexpected unaccessed translated state")
        }
    }

}

private inline val String.asRawCharStateMutableList: MutableList<CharState>
    get() = mapTo(ArrayList(length), CharState::Raw)

/**
 * Map non-[Accessed] elements into [CharState.Translated] with checking using contract function [CharState.isAccessed]
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

private const val Char_AccessedPlaceholder = ' '

context(charList: MutableList<CharState>)
private fun Dictionary.browseRecordList(text: String) {
    val stringBuilder = StringBuilder(text)
    var str = text

    recordList.forEach { record ->
        if (str.length >= record.length) {
            record.text.toRegex().findAll(str)
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
                        charList[i] = CharState.Translated(char, index)
                    }
                }
                // Update [str] to latest text, if any
                ?.also { str = stringBuilder.toString() }
        }
    }
}