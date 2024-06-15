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
 * [DictionaryChar]
 * @access Public to all
 * @inherit [DictionaryItem]
 * @param char [Char]
 * @param pinyin [Int]
 **/
@Serializable
@SerialName("Char")
data class DictionaryChar(
    @SerialName("char")
    val char: Char,
    @SerialName("pinyin")
    val pinyin: Int
): DictionaryItem() {

    companion object Construct {
        fun new(charPinyinPair: Pair<Char, Int>): DictionaryChar {
            val (char, pinyin) = charPinyinPair
            return DictionaryChar(char, pinyin)
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
 * [DictionaryString]
 * @access Public to all
 * @inherit [DictionaryItem]
 * @param charList [MutableList]-[DictionaryChar]
 **/
@Serializable
@SerialName("String")
data class DictionaryString(
    @SerialName("chars")
    val charList: List<DictionaryChar>
): DictionaryItem(), List<DictionaryChar> by charList {

    companion object Construct {
        fun new(charList: List<DictionaryChar>): DictionaryString {
            return DictionaryString(charList)
        }
    }

    /**
     * [DictionaryItem.string]
     * @access Internal to module
     * @return [String]
     **/
    override val string: String by lazy {
        charList.joinToString(
            separator = "",
            transform = DictionaryItem::string
        )
    }

}