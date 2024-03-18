package me.ks.chan.c2pinyin.dictionary.item

private typealias KChar = Char
private typealias KString = String

private const val JOIN_SEPARATOR = ""

/**
 * [DictionaryItem]
 * @access Public to all
 **/
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
    data class Char(val char: KChar, val pinyin: Int): DictionaryItem() {

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
    data class String(val charItems: Array<Char>): DictionaryItem() {

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