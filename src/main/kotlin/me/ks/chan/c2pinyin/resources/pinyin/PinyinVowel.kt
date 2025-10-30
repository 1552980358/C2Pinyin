package me.ks.chan.c2pinyin.resources.pinyin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val Vowel_A = "a"
private const val Vowel_AI = "ai"
private const val Vowel_An = "an"
private const val Vowel_Ang = "ang"
private const val Vowel_AO = "ao"
private const val Vowel_EI = "ei"
private const val Vowel_En = "en"
private const val Vowel_Eng = "eng"
private const val Vowel_I = "i"
private const val Vowel_IAn = "ian"
private const val Vowel_IAO = "iao"
private const val Vowel_IE = "ie"
private const val Vowel_In = "in"
private const val Vowel_Ing = "ing"
private const val Vowel_O = "o"
private const val Vowel_U = "u"
private const val Vowel_E = "e"
private const val Vowel_Ong = "ong"
private const val Vowel_OU = "ou"
private const val Vowel_UAI = "uai"
private const val Vowel_UAn = "uan"
@Suppress("SpellCheckingInspection")
private const val Vowel_UAng = "uang"
private const val Vowel_UI = "ui"
private const val Vowel_Un = "un"
private const val Vowel_UO = "uo"
private const val Vowel_IA = "ia"
private const val Vowel_IU = "iu"
private const val Vowel_Er = "er"
private const val Vowel_EHat = "e^"
private const val Vowel_UA = "ua"
@Suppress("SpellCheckingInspection")
private const val Vowel_IAng = "iang"
@Suppress("SpellCheckingInspection")
private const val Vowel_IOng = "iong"
private const val Vowel_UE = "ue"
private const val Vowel_V = "v"
private const val Vowel_VE = "ve"
private const val Vowel_M = "m"
private const val Vowel_Ng = "ng"

@Serializable
enum class PinyinVowel(@Suppress("unused") internal val strings: Array<String>) {

    @Suppress("unused")
    @SerialName(Vowel_A)
    A(arrayOf(Vowel_A)),

    @Suppress("unused")
    @SerialName(Vowel_AI)
    AI(arrayOf(Vowel_A, Vowel_I)),

    @Suppress("unused")
    @SerialName(Vowel_An)
    An(arrayOf(Vowel_An)),

    @Suppress("unused")
    @SerialName(Vowel_Ang)
    Ang(arrayOf(Vowel_Ang)),

    @Suppress("unused")
    @SerialName(Vowel_AO)
    AO(arrayOf(Vowel_A, Vowel_O)),

    @Suppress("unused")
    @SerialName(Vowel_EI)
    EI(arrayOf(Vowel_E, Vowel_I)),

    @Suppress("unused")
    @SerialName(Vowel_En)
    En(arrayOf(Vowel_En)),

    @Suppress("unused")
    @SerialName(Vowel_Eng)
    Eng(arrayOf(Vowel_Eng)),

    @Suppress("unused")
    @SerialName(Vowel_I)
    I(arrayOf(Vowel_I)),

    @Suppress("unused")
    @SerialName(Vowel_IAn)
    IAn(arrayOf(Vowel_I, Vowel_An)),

    @Suppress("unused")
    @SerialName(Vowel_IAO)
    IAO(arrayOf(Vowel_I, Vowel_A, Vowel_O)),

    @Suppress("unused")
    @SerialName(Vowel_IE)
    IE(arrayOf(Vowel_I, Vowel_E)),

    @Suppress("unused")
    @SerialName(Vowel_In)
    In(arrayOf(Vowel_In)),

    @Suppress("unused")
    @SerialName(Vowel_Ing)
    Ing(arrayOf(Vowel_Ing)),

    @Suppress("unused")
    @SerialName(Vowel_O)
    O(arrayOf(Vowel_O)),

    @Suppress("unused")
    @SerialName(Vowel_U)
    U(arrayOf(Vowel_U)),

    @Suppress("unused")
    @SerialName(Vowel_E)
    E(arrayOf(Vowel_E)),

    @Suppress("unused")
    @SerialName(Vowel_Ong)
    Ong(arrayOf(Vowel_Ong)),

    @Suppress("unused")
    @SerialName(Vowel_OU)
    OU(arrayOf(Vowel_O, Vowel_U)),

    @Suppress("unused")
    @SerialName(Vowel_UAI)
    UAI(arrayOf(Vowel_U, Vowel_A, Vowel_I)),

    @Suppress("unused")
    @SerialName(Vowel_UAn)
    UAn(arrayOf(Vowel_U, Vowel_An)),

    @Suppress("unused")
    @SerialName(Vowel_UAng)
    UAng(arrayOf(Vowel_U, Vowel_Ang)),

    @Suppress("unused")
    @SerialName(Vowel_UI)
    UI(arrayOf(Vowel_U, Vowel_I)),

    @Suppress("unused")
    @SerialName(Vowel_Un)
    Un(arrayOf(Vowel_Un)),

    @Suppress("unused")
    @SerialName(Vowel_UO)
    UO(arrayOf(Vowel_UO, Vowel_O)),

    @Suppress("unused")
    @SerialName(Vowel_IA)
    IA(arrayOf(Vowel_I, Vowel_A)),

    @Suppress("unused")
    @SerialName(Vowel_IU)
    IU(arrayOf(Vowel_I, Vowel_U)),

    @Suppress("unused")
    @SerialName(Vowel_Er)
    Er(arrayOf(Vowel_Er)),

    @Suppress("unused")
    @SerialName(Vowel_EHat)
    EHat(arrayOf(Vowel_EHat)),

    @Suppress("unused")
    @SerialName(Vowel_UA)
    UA(arrayOf(Vowel_U, Vowel_A)),

    @Suppress("unused")
    @SerialName(Vowel_IAng)
    IAng(arrayOf(Vowel_I, Vowel_Ang)),

    @Suppress("unused")
    @SerialName(Vowel_IOng)
    IOng(arrayOf(Vowel_I, Vowel_Ong)),

    @Suppress("unused")
    @SerialName(Vowel_UE)
    UE(arrayOf(Vowel_U, Vowel_E)),

    @Suppress("unused")
    @SerialName(Vowel_V)
    V(arrayOf(Vowel_V)),

    @Suppress("unused")
    @SerialName(Vowel_VE)
    VE(arrayOf(Vowel_V, Vowel_E)),

    @Suppress("unused")
    @SerialName(Vowel_M)
    M(arrayOf(Vowel_M)),

    @Suppress("unused")
    @SerialName(Vowel_Ng)
    Ng(arrayOf(Vowel_Ng))

}