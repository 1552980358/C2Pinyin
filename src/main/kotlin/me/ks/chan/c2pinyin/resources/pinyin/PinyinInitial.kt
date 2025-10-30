package me.ks.chan.c2pinyin.resources.pinyin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val Initial_None = ""
private const val Initial_B = "b"
private const val Initial_P = "p"
private const val Initial_M = "m"
private const val Initial_F = "f"
private const val Initial_D = "d"
private const val Initial_T = "t"
private const val Initial_N = "n"
private const val Initial_L = "l"
private const val Initial_G = "g"
private const val Initial_K = "k"
private const val Initial_H = "h"
private const val Initial_J = "j"
private const val Initial_Q = "q"
private const val Initial_X = "x"
private const val Initial_ZH = "zh"
private const val Initial_CH = "ch"
private const val Initial_SH = "sh"
private const val Initial_R = "r"
private const val Initial_Z = "z"
private const val Initial_C = "c"
private const val Initial_S = "s"
private const val Initial_Y = "y"
private const val Initial_W = "w"

@Serializable
enum class PinyinInitial(@Suppress("unused") internal val str: String) {

    @SerialName(Initial_None)
    None(Initial_None),

    @Suppress("unused")
    @SerialName(Initial_B)
    B(Initial_B),

    @Suppress("unused")
    @SerialName(Initial_P)
    P(Initial_P),

    @Suppress("unused")
    @SerialName(Initial_M)
    M(Initial_M),

    @Suppress("unused")
    @SerialName(Initial_F)
    F(Initial_F),

    @Suppress("unused")
    @SerialName(Initial_D)
    D(Initial_D),

    @Suppress("unused")
    @SerialName(Initial_T)
    T(Initial_T),

    @Suppress("unused")
    @SerialName(Initial_N)
    N(Initial_N),

    @Suppress("unused")
    @SerialName(Initial_L)
    L(Initial_L),

    @Suppress("unused")
    @SerialName(Initial_G)
    G(Initial_G),

    @Suppress("unused")
    @SerialName(Initial_K)
    K(Initial_K),

    @Suppress("unused")
    @SerialName(Initial_H)
    H(Initial_H),

    @Suppress("unused")
    @SerialName(Initial_J)
    J(Initial_J),

    @Suppress("unused")
    @SerialName(Initial_Q)
    Q(Initial_Q),

    @Suppress("unused")
    @SerialName(Initial_X)
    X(Initial_X),

    @Suppress("unused")
    @SerialName(Initial_ZH)
    ZH(Initial_ZH),

    @Suppress("unused")
    @SerialName(Initial_CH)
    CH(Initial_CH),

    @Suppress("unused")
    @SerialName(Initial_SH)
    SH(Initial_SH),

    @Suppress("unused")
    @SerialName(Initial_R)
    R(Initial_R),

    @Suppress("unused")
    @SerialName(Initial_Z)
    Z(Initial_Z),

    @Suppress("unused")
    @SerialName(Initial_C)
    C(Initial_C),

    @Suppress("unused")
    @SerialName(Initial_S)
    S(Initial_S),

    @Suppress("unused")
    @SerialName(Initial_Y)
    Y(Initial_Y),

    @Suppress("unused")
    @SerialName(Initial_W)
    W(Initial_W)

}