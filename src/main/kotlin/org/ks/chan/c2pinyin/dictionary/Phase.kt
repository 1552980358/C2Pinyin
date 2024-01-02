package org.ks.chan.c2pinyin.dictionary

private const val SEPARATOR = ""

/**
 * [Phase]
 * @param words [Word][Array]
 **/
class Phase(private val words: Array<Word>) {

    constructor(wordList: List<Word>): this(wordList.toTypedArray())

    /**
     * [Phase.text]
     * @return [String]
     *
     * Get text of [Word]
     **/
    val text: String
        get() = words.joinToString(
            separator = SEPARATOR,
            transform = Word::string
        )

    /**
     * [Phase.forEachIndexed]
     * @param action
     *
     * Loop through all elements in [Phase.words] with index
     **/
    fun forEachIndexed(action: (Int, Word) -> Unit) =
        words.forEachIndexed(action)

}