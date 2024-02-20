package org.ks.chan.c2pinyin

import org.ks.chan.c2pinyin.decode.decodeToPinyin
import org.ks.chan.c2pinyin.decode.decodeToPinyinList
import org.ks.chan.c2pinyin.dictionary.Dictionary
import org.ks.chan.c2pinyin.dictionary.Phase
import org.ks.chan.c2pinyin.dictionary.Word

class C2Pinyin(
    letterCase: LetterCase,
    dictionary: Dictionary,
): C2PinyinSkeleton {

    /**
     * [C2Pinyin.letterCase]
     * @see [C2Pinyin]
     *
     * A [LetterCase] instance deciding conversion pinyin string format
     **/
    private var _letterCase: LetterCase = letterCase
    override var letterCase: LetterCase
        get() = _letterCase
        set(value) {
            if (_letterCase != value) {
                _letterCase = value
            }
        }

    /**
     * [C2Pinyin.dictionary]
     * @access Private
     * @see [C2Pinyin]
     *
     * A [Dictionary] instance holding mapping data
     **/
    private var _dictionary: Dictionary = dictionary
    @Suppress("MemberVisibilityCanBePrivate")
    override var dictionary: Dictionary
        get() = _dictionary
        set(value) {
            if (_dictionary != value) {
                _dictionary = value
            }
        }

    companion object Global {

        /**
         * [C2Pinyin.Global.Global]
         *
         * A JVM static getter for [GlobalInstance] of [C2Pinyin]
         * [GlobalInstance] is a lazy field, will only be created when [C2Pinyin.Global] is called
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
        @JvmName("ofChar")
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
        @JvmName("ofString")
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
        return char.decodeToPinyin(letterCase = letterCase, dictionary = dictionary)
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
     * @param word [Word]
     *
     * Add [word] into [C2Pinyin.dictionary]
     **/
    operator fun plusAssign(word: Word) {
        dictionary += word
    }

    /**
     * [C2Pinyin.plusAssign]
     * @param phase [Phase]
     *
     * Add [phase] into [C2Pinyin.dictionary]
     **/
    operator fun plusAssign(phase: Phase) {
        dictionary += phase
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

}