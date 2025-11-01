package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.resources.pinyin.PinyinInitial
import me.ks.chan.c2pinyin.resources.pinyin.PinyinVowel

/**
 * A sealed class indicating letter case for [me.ks.chan.c2pinyin.resources.pinyin.Pinyin]
 **/
sealed class LetterCase {

    /**
     * Data class for defining [LetterCase] for [me.ks.chan.c2pinyin.resources.pinyin.Pinyin] ([PinyinInitial] and [PinyinVowel] pair)
     * @constructor [LetterCase] (Default: [Uppercase]), [LetterCase] (Default: [Lowercase])
     **/
    data class Pair(
        val initial: LetterCase = Uppercase, val vowel: LetterCase = Lowercase
    ) {

        companion object {
            /**
             * default instance of [LetterCase.Pair]
             **/
            val Default by lazy(LetterCase::Pair)
        }

    }

    internal abstract operator fun invoke(initial: PinyinInitial): String
    internal abstract operator fun invoke(vowel: PinyinVowel): List<String>

    /**
     * Set all letters (chars) as uppercase (capital letter)
     **/
    data object Uppercase: LetterCase() {

        override fun invoke(initial: PinyinInitial): String {
            return initial.str.uppercase()
        }

        override fun invoke(vowel: PinyinVowel): List<String> {
            return vowel.strings.map(String::uppercase)
        }

    }

    /**
     * Set all letters (chars) as lowercase (small letter)
     **/
    data object Lowercase: LetterCase() {

        override fun invoke(initial: PinyinInitial): String {
            return initial.str.lowercase()
        }

        override fun invoke(vowel: PinyinVowel): List<String> {
            return vowel.strings.map(String::lowercase)
        }

    }

    /**
     * Set string capitalized, i.e., first letter as capital letter while remaining small letter
     **/
    data object Capitalize: LetterCase() {

        override fun invoke(initial: PinyinInitial): String {
            return initial.str.replaceFirstChar(Char::uppercase)
        }

        override fun invoke(vowel: PinyinVowel): List<String> {
            return vowel.strings.mapIndexed { i, str ->
                if (i > 0) { str.lowercase() } else { str.lowercase() }
            }
        }

    }

}