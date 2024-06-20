package me.ks.chan.c2pinyin.dictionary.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [DictionaryItem]
 * @access Public to all
 **/
@Serializable
sealed class DictionaryItem {
    /**
     * [DictionaryItem]
     * @access Internal to module
     * @return [String]
     **/
    abstract val string: String
}

/**
 * [Word]
 * @access Public to all
 * @inherit [DictionaryItem]
 * @param char [Char]
 * @param pinyin [Int]
 **/
@Serializable
@SerialName("word")
data class Word(
    @SerialName("char")
    val char: Char,
    @SerialName("pinyin")
    val pinyin: Int
): DictionaryItem() {

    companion object Construct {
        fun new(charPinyinPair: Pair<Char, Int>): Word {
            val (char, pinyin) = charPinyinPair
            return Word(char, pinyin)
        }
    }

    /**
     * [DictionaryItem.string]
     * @access Internal to module
     * @return [String]
     **/
    override val string: String
        get() = char.toString()

}

/**
 * [Phrase]
 * @access Public to all
 * @inherit [DictionaryItem]
 * @param words [MutableList]-[Word]
 **/
@Serializable
@SerialName("phrase")
data class Phrase(
    @SerialName("words")
    val words: List<Word>
): DictionaryItem(), List<Word> by words {

    companion object Construct {
        fun new(charList: List<Word>): Phrase {
            return Phrase(charList)
        }
    }

    /**
     * [DictionaryItem.string]
     * @access Internal to module
     * @return [String]
     **/
    override val string: String by lazy {
        words.joinToString(
            separator = "",
            transform = DictionaryItem::string
        )
    }

}