package me.ks.chan.c2pinyin.resources.pinyin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PinyinVowel(internal val vowels: Array<String>) {

    @SerialName("a")
    A(arrayOf("a")),

    @SerialName("ai")
    AI(arrayOf("a", "i")),

    @SerialName("an")
    An(arrayOf("an")),

    @SerialName("ang")
    Ang(arrayOf("ang")),

    @SerialName("ao")
    AO(arrayOf("a", "o")),

    @SerialName("ei")
    EI(arrayOf("e", "i")),

    @SerialName("en")
    En(arrayOf("en")),

    @SerialName("eng")
    Eng(arrayOf("eng")),

    @SerialName("i")
    I(arrayOf("i")),

    @SerialName("ian")
    IAn(arrayOf("i", "an")),

    @SerialName("iao")
    IAO(arrayOf("i", "a", "o")),

    @SerialName("ie")
    IE(arrayOf("i", "e")),

    @SerialName("in")
    In(arrayOf("in")),

    @SerialName("ing")
    Ing(arrayOf("ing")),

    @SerialName("o")
    O(arrayOf("o")),

    @SerialName("u")
    U(arrayOf("u")),

    @SerialName("e")
    E(arrayOf("e")),

    @SerialName("ong")
    Ong(arrayOf("ong")),

    @SerialName("ou")
    OU(arrayOf("o", "u")),

    @SerialName("uai")
    UAI(arrayOf("u", "a", "i")),

    @SerialName("uan")
    UAn(arrayOf("u", "an")),

    @SerialName("uang")
    UAng(arrayOf("u", "ang")),

    @SerialName("ui")
    UI(arrayOf("u", "i")),

    @SerialName("un")
    Un(arrayOf("un")),

    @SerialName("uo")
    UO(arrayOf("u", "o")),

    @SerialName("ia")
    IA(arrayOf("i", "a")),

    @SerialName("iu")
    IU(arrayOf("i", "u")),

    @SerialName("er")
    Er(arrayOf("er")),

    @SerialName("e^")
    EHat(arrayOf("e^")),

    @SerialName("ua")
    UA(arrayOf("u", "a")),

    @SerialName("iang")
    IAng(arrayOf("i", "ang")),

    @SerialName("iong")
    IOng(arrayOf("i", "ong")),

    @SerialName("ue")
    UE(arrayOf("u", "e")),

    @SerialName("v")
    V(arrayOf("v")),

    @SerialName("ve")
    VE(arrayOf("v", "e")),

    @SerialName("m")
    M(arrayOf("m")),

    @SerialName("ng")
    Ng(arrayOf("ng"))

}