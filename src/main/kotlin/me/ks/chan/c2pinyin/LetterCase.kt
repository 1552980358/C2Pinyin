package me.ks.chan.c2pinyin

import me.ks.chan.c2pinyin.resources.pinyin.Pinyin
import me.ks.chan.c2pinyin.resources.pinyin.PinyinInitial
import me.ks.chan.c2pinyin.resources.pinyin.PinyinVowel

data class LetterCases(
    val initial: LetterCase = LetterCase.Uppercase,
    val vowel: LetterCase = LetterCase.Lowercase,
) {

    companion object {
        val Default by lazy(::LetterCases)
    }

    internal operator fun invoke(pinyin: Pinyin): Pair<String, List<String>> {
        val (pinyinInitial, pinyinVowel) = pinyin
        return initial(pinyinInitial) to vowel(pinyinVowel)
    }

}

sealed class LetterCase {

    internal abstract operator fun invoke(initial: PinyinInitial): String
    internal abstract operator fun invoke(vowel: PinyinVowel): List<String>

    data object Uppercase: LetterCase() {

        override fun invoke(initial: PinyinInitial): String {
            return initial.str.uppercase()
        }

        override fun invoke(vowel: PinyinVowel): List<String> {
            return vowel.strings.map(String::uppercase)
        }

    }

    data object Lowercase: LetterCase() {

        override fun invoke(initial: PinyinInitial): String {
            return initial.str.lowercase()
        }

        override fun invoke(vowel: PinyinVowel): List<String> {
            return vowel.strings.map(String::lowercase)
        }

    }

    data object Capitalize: LetterCase() {

        override fun invoke(initial: PinyinInitial): String {
            return initial.str.replaceFirstChar(Char::uppercase)
        }

        override fun invoke(vowel: PinyinVowel): List<String> {
            return vowel.strings.mapIndexed { i, str -> if (i > 0) str else str.uppercase() }
        }

    }

}