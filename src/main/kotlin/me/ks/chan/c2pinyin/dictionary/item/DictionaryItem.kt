package me.ks.chan.c2pinyin.dictionary.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private typealias KChar = Char
private typealias KString = String

private const val SERIAL_NAME_CHAR = "Char"
private const val SERIAL_NAME_CHAR_CHAR = "char"
private const val SERIAL_NAME_CHAR_PINYIN = "pinyin"
private const val SERIAL_NAME_STRING = "String"
private const val SERIAL_NAME_STRING_CHARS = "chars"

private const val JOIN_SEPARATOR = ""

/**
 * [DictionaryItem]
 * @access Public to all
 **/
@Serializable
sealed class DictionaryItem {

    /**
     * [DictionaryItem]
     * @access Internal to module
     * @return [KString]
     **/
    abstract val string: KString

    /**
     * [DictionaryItem.Char]
     * @access Public to all
     * @inherit [DictionaryItem]
     * @param char [KChar]
     * @param pinyin [Int]
     **/
    @Serializable
    @SerialName(SERIAL_NAME_CHAR)
    data class Char(
        @SerialName(SERIAL_NAME_CHAR_CHAR)
        val char: KChar,
        @SerialName(SERIAL_NAME_CHAR_PINYIN)
        val pinyin: Int
    ): DictionaryItem() {

        /**
         * [DictionaryItem.string]
         * @access Internal to module
         * @return [String]
         **/
        override val string: KString
            get() = char.toString()

    }

    /**
     * [DictionaryItem.String]
     * @access Public to all
     * @inherit [DictionaryItem]
     * @param charList [MutableList]-[DictionaryItem.Char]
     **/
    @Serializable
    @SerialName(SERIAL_NAME_STRING)
    data class String(
        @SerialName(SERIAL_NAME_STRING_CHARS)
        val charList: MutableList<Char>
    ): DictionaryItem(), MutableList<Char> by charList {

        /**
         * [DictionaryItem.string]
         * @access Internal to module
         * @return [String]
         **/
        override val string: KString
            get() = charList.joinToString(
                separator = JOIN_SEPARATOR,
                transform = DictionaryItem::string
            )

    }

}