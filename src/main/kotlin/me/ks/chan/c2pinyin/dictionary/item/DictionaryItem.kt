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
     * @param charItems [Array]-[DictionaryItem.Char]
     **/
    @Serializable
    @SerialName(SERIAL_NAME_STRING)
    data class String(
        @SerialName(SERIAL_NAME_STRING_CHARS)
        val charItems: Array<Char>
    ): DictionaryItem() {

        /**
         * [DictionaryItem.string]
         * @access Internal to module
         * @return [String]
         **/
        override val string: KString
            get() = charItems.joinToString(
                separator = JOIN_SEPARATOR,
                transform = Char::string
            )

        /**
         * [Any.equals]
         * @access Public to all
         * @param other [Any]
         * @return [Boolean]
         **/
        override fun equals(other: Any?): Boolean {
            if (other !is String) {
                return false
            }
            return charItems.contentEquals(other.charItems)
        }

        /**
         * [Any.hashCode]
         * @access Public to all
         * @return [Int]
         **/
        override fun hashCode(): Int =
            charItems.contentHashCode()

    }

}