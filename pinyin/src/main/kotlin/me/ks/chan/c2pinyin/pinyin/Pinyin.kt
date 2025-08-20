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

@Serializable
enum class PinyinInitial(val initial: String) {

    @SerialName("b")
    B("b"),

    @SerialName("p")
    P("p"),

    @SerialName("m")
    M("m"),

    @SerialName("f")
    F("f"),

    @SerialName("d")
    D("d"),

    @SerialName("t")
    T("t"),

    @SerialName("n")
    N("n"),

    @SerialName("l")
    L("l"),

    @SerialName("g")
    G("g"),

    @SerialName("k")
    K("k"),

    @SerialName("h")
    H("h"),

    @SerialName("j")
    J("j"),

    @SerialName("q")
    Q("q"),

    @SerialName("x")
    X("x"),

    @SerialName("zh")
    ZH("zh"),

    @SerialName("ch")
    CH("ch"),

    @SerialName("sh")
    SH("sh"),

    @SerialName("r")
    R("r"),

    @SerialName("z")
    Z("z"),

    @SerialName("c")
    C("c"),

    @SerialName("s")
    S("s"),

    @SerialName("y")
    Y("y"),

    @SerialName("w")
    W("w")

}

@Serializable
enum class PinyinVowel(val vowels: Array<String>) {

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
    Eng(arrayOf("")),

    @SerialName("i")
    I(arrayOf("")),

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