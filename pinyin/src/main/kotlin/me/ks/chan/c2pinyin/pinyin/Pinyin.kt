package me.ks.chan.c2pinyin.pinyin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Pinyin {

    @Serializable
    @SerialName("single-vowel")
    data class SingleVowel(
        @SerialName("vowel")
        val vowel: PinyinVowel
    ): Pinyin()

    @Serializable
    @SerialName("initial-and-vowelled")
    data class InitialAndVowelled(
        @SerialName("initial")
        val initial: PinyinInitial,
        @SerialName("vowel")
        val vowel: PinyinVowel
    ): Pinyin()

}

