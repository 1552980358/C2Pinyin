package projekt.cloud.piece.c2.pinyin.util

import projekt.cloud.piece.c2.pinyin.dictionary.DictionaryItem
import projekt.cloud.piece.c2.pinyin.util.DictionaryUtil.plus
import java.io.File
import java.io.InputStream

/**
 * [DictionaryUtil]
 *
 * Constants
 *  [REGEX_NEW_LINE]
 *  [REGEX_DICTIONARY_ITEM]
 *  [DIVIDER_TEXT_PINYIN]
 *  [DIVIDER_PINYIN]
 *
 * Methods
 *  [asDictionaryItems]
 *  [dumpLines]
 *  [dumpLine]
 *  [isDictionaryItem]
 *  
 **/
object DictionaryUtil {
    
    /**
     * [REGEX_NEW_LINE]
     **/
    private const val REGEX_NEW_LINE = "\\r?\\n"
    
    /**
     * [REGEX_DICTIONARY_ITEM]
     **/
    private const val REGEX_DICTIONARY_ITEM =
        "[\\u4e00-\\u9fa5]+=((A[IO]?|OU?|E[INR]?|ANG?|NG|[BMP](A[IO]?|[AEI]NG?|EI|IE?|IA[NO]|O|U)|POU|ME|M[IO]U" +
            "|[FW](A|[AE]NG?|EI|O|U)|FOU|WAI|[DT](A[IO]?|AN|E|[AEIO]NG|IE?|IA[NO]|OU|U[INO]?|UAN)|DEI|DIU|[NL](" +
            "A[IO]?|EI?|[EIO]NG|I[EU]?|I?ANG?|IAO|IN|OU|U[EO]?|VE?|UAN)|NEN|LIA|LUN|[GHK](A[IO]?|[AE]NG?|E|ONG|" +
            "OU|U[AINO]?|UAI|UANG?)|[GH]EI|[JQX](I(AO?|ANG?|E|NG?|ONG|U)?|U[EN]?|UAN)|([CSZ]H?|R)([AE]NG?|AO|E|" +
            "I|OU|U[INO]?|UAN)|[CSZ](AI?|ONG)|[CSZ]H(AI?|UAI|UANG)|ZEI|[SZ]HUA|([CZ]H|R)ONG|Y(AO?|[AI]NG?|E|I|O" +
            "NG|OU|U[EN]?|UAN))')+(A[IO]?|OU?|E[INR]?|ANG?|NG|[BMP](A[IO]?|[AEI]NG?|EI|IE?|IA[NO]|O|U)|POU|ME|M" +
            "[IO]U|[FW](A|[AE]NG?|EI|O|U)|FOU|WAI|[DT](A[IO]?|AN|E|[AEIO]NG|IE?|IA[NO]|OU|U[INO]?|UAN)|DEI|DIU|" +
            "[NL](A[IO]?|EI?|[EIO]NG|I[EU]?|I?ANG?|IAO|IN|OU|U[EO]?|VE?|UAN)|NEN|LIA|LUN|[GHK](A[IO]?|[AE]NG?|E" +
            "|ONG|OU|U[AINO]?|UAI|UANG?)|[GH]EI|[JQX](I(AO?|ANG?|E|NG?|ONG|U)?|U[EN]?|UAN)|([CSZ]H?|R)([AE]NG?|" +
            "AO|E|I|OU|U[INO]?|UAN)|[CSZ](AI?|ONG)|[CSZ]H(AI?|UAI|UANG)|ZEI|[SZ]HUA|([CZ]H|R)ONG|Y(AO?|[AI]NG?|" +
            "E|I|ONG|OU|U[EN]?|UAN))"
    
    /**
     * [DIVIDER_TEXT_PINYIN]
     **/
    private const val DIVIDER_TEXT_PINYIN = '='
    
    /**
     * [DIVIDER_PINYIN]
     **/
    private const val DIVIDER_PINYIN = '\''
    
    /**
     * [String.asDictionaryItems]
     **/
    @JvmStatic
    val String.asDictionaryItems get() = split(REGEX_NEW_LINE.toRegex()).dumpLines
    
    /**
     * [File.asDictionaryItems]
     **/
    @JvmStatic
    val File.asDictionaryItems get() = readLines().dumpLines
    
    /**
     * [InputStream.asDictionaryItems]
     **/
    @JvmStatic
    val InputStream.asDictionaryItems get() = bufferedReader().readLines().dumpLines
    
    /**
     * [List]<[String]>.[dumpLines]
     **/
    private val List<String>.dumpLines get() = ArrayList<DictionaryItem>().also {
        forEach { line ->
            if (line.isDictionaryItem) {
                line.dumpLine?.let { dictionaryItem -> it.add(dictionaryItem) }
            }
        }
    }
    
    /**
     * [String.dumpLine]
     **/
    private val String.dumpLine: DictionaryItem? get() {
        val equalIndex = indexOf(DIVIDER_TEXT_PINYIN)
        val text = substring(0, equalIndex)
        val pinyin = substring(equalIndex + 1).uppercase().split(DIVIDER_PINYIN)
        return when {
            text.length != pinyin.size -> null
            else -> DictionaryItem(text, pinyin)
        }
    }
    
    /**
     * [String.isDictionaryItem]
     **/
    private val String.isDictionaryItem get() =
        uppercase().matches(REGEX_DICTIONARY_ITEM.toRegex())
    
    /**
     * [Pair]<[String], [List]<[String]>>.[plus]
     * @param dictionaryItem [DictionaryItem]
     **/
    @JvmStatic
    operator fun Pair<String, List<String>>.plus(dictionaryItem: DictionaryItem) =
        DictionaryItem(this) + dictionaryItem
    
    /**
     * [Pair]<[String], [List]<[String]>>.[plus]
     * @param pair [Pair]<[String], [List]<[String]>>
     **/
    @JvmStatic
    operator fun Pair<String, List<String>>.plus(pair: Pair<String, List<String>>) =
        DictionaryItem(this) + pair
    
    /**
     * [MutableList]<[DictionaryItem]>.[plus]
     * @param dictionaryItem [DictionaryItem]
     **/
    @JvmStatic
    operator fun MutableList<DictionaryItem>.plus(dictionaryItem: DictionaryItem) = apply {
        add(dictionaryItem)
    }
    
    /**
     * [MutableList]<[DictionaryItem]>.[plus]
     * @param pair [Pair]<[String], [List]<[String]>>
     **/
    @JvmStatic
    operator fun MutableList<DictionaryItem>.plus(pair: Pair<String, List<String>>) = apply() {
        add(DictionaryItem(pair))
    }

}