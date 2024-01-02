package org.ks.chan.c2pinyin

import org.ks.chan.c2pinyin.decode.INDEX_INVALID
import org.ks.chan.c2pinyin.decode.PinyinTable
import org.ks.chan.c2pinyin.decode.tableIndex

class C2Pinyin(
    dictionary: Dictionary = Dictionary(),
    letterCase: LetterCase = LetterCase.Camel,
) {

    /**
     * [C2Pinyin.dictionary]
     * @see [C2Pinyin]
     *
     * A [Dictionary] instance holding mapping data
     **/
    private var _dictionary: Dictionary = dictionary
    @Suppress("MemberVisibilityCanBePrivate")
    var dictionary: Dictionary
        get() = _dictionary
        set(value) {
            if (_dictionary != value) {
                _dictionary = value
            }
        }

    /**
     * [C2Pinyin.letterCase]
     * @see [C2Pinyin]
     *
     * A [LetterCase] instance deciding conversion pinyin string format
     **/
    private var _letterCase: LetterCase = letterCase
    var letterCase: LetterCase
        get() = _letterCase
        set(value) {
            if (_letterCase != value) {
                _letterCase = value
            }
        }

    companion object Global {

        /**
         * [C2Pinyin.Global.Global]
         *
         * A JVM static getter for [GlobalInstance] of [C2Pinyin]
         **/
        @JvmStatic
        val Global: C2Pinyin
            get() = GlobalInstance

        /**
         * [C2Pinyin.Global.char]
         * @param char [Char]
         * @param letterCase [LetterCase] letter case, default is set from [GlobalInstance]
         * @return [String]
         *
         * Convert [char] into [String] pinyin with configurations from [GlobalInstance]
         **/
        @JvmOverloads
        @JvmStatic
        fun char(char: Char, letterCase: LetterCase = Global.letterCase): String =
            char.pinyin(letterCase = letterCase)

        /**
         * [C2Pinyin.Global.string]
         * @param string [String]
         * @param letterCase [LetterCase]
         * @return [String][List]
         *
         * Convert [string] into [List] of [String] pinyin with configurations from [GlobalInstance]
         **/
        @JvmOverloads
        @JvmStatic
        fun string(string: String, letterCase: LetterCase = Global.letterCase): List<String> =
            string.pinyin(letterCase = letterCase)

    }

    /**
     * [C2Pinyin.char]
     * @param char [Char]
     * @param letterCase [LetterCase]
     * @return [String]
     *
     * Convert [string] into [String] pinyin
     **/
    fun char(char: Char, letterCase: LetterCase = _letterCase): String {
        return when (val tableIndex = char.tableIndex) {
            INDEX_INVALID -> { char.toString() }
            else -> { PinyinTable[tableIndex] }
        }.let(letterCase::handleCase)
    }

    /**
     * [C2Pinyin.string]
     * @param string [String]
     * @param letterCase [LetterCase]
     * @return [String][List]
     *
     * Convert [string] into [List] of [String] pinyin
     **/
    fun string(string: String, letterCase: LetterCase = _letterCase): List<String> =
        string.decodeToPinyinList(letterCase = letterCase, dictionary = dictionary)

    /**
     * [C2Pinyin.plusAssign]
     * @param dictionary [Dictionary]
     *
     * Add values from [dictionary] into [C2Pinyin._dictionary]
     **/
    operator fun plusAssign(dictionary: Dictionary) {
        _dictionary += dictionary
    }

    /**
     * [C2Pinyin.minusAssign]
     * @param key [String]
     *
     * Remove key from [C2Pinyin._dictionary]
     **/
    operator fun minusAssign(key: String) {
        dictionary -= key
    }

    /**
     * [C2Pinyin.minusAssign]
     * @param dictionary [Dictionary]
     *
     * Remove all inside [dictionary] from [C2Pinyin._dictionary]
     **/
    operator fun minusAssign(dictionary: Dictionary) {
        dictionary.forEach { key, _ ->
            this -= key
        }
    }

}