package me.ks.chan.c2pinyin.pinyin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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