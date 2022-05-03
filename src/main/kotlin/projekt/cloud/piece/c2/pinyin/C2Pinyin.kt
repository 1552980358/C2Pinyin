package projekt.cloud.piece.c2.pinyin

import projekt.cloud.piece.c2.pinyin.util.ConstantValue.EMPTY_STR
import projekt.cloud.piece.c2.pinyin.util.PinyinUtil.pinyinArrayList
import projekt.cloud.piece.c2.pinyin.util.PinyinUtil.pinyinStr

/**
 * Object [C2Pinyin]
 * Methods
 *  [Char.pinyin]
 *  [String.pinyin]
 *  [String.convertToPinyin]
 **/
object C2Pinyin {

    /**
     * [Char.pinyin] convert [Char] into pinyin string
     * For Kotlin:
     *   import projekt.cloud.piece.c2.pinyin.C2Pinyin.pinyin
     *   ...
     *   val charZhong = '中'
     *   val pinyinZhong = charZhong.pinyin // -> [String] "ZHONG"
     * For Java:
     *   import static projekt.cloud.piece.c2.pinyin.C2Pinyin.getPinyin;
     *   ...
     *   char charZhong = '中'
     *   String pinyinZhong = getPinyin(charZhong)  // -> [String] "ZHONG"
     *
     *   // Or
     *   import static projekt.cloud.piece.c2.pinyin.C2Pinyin;
     *   ...
     *   char charZhong = '中'
     *   String pinyinZhong = C2Pinyin.getPinyin(charZhong)  // -> [String] "ZHONG"
     **/
    @JvmStatic
    val Char.pinyin get() = pinyinStr

    /**
     * [String.pinyin] convert [String] into pinyin string
     *
     * For Kotlin:
     *   import projekt.cloud.piece.c2.pinyin.C2Pinyin.pinyin
     *   ...
     *   val charZhongWen = "中文"
     *   val pinyinZhong = charZhongWen.pinyin // -> [String] "ZHONGWEN"
     *
     * For Java:
     *   import static projekt.cloud.piece.c2.pinyin.C2Pinyin.getPinyin;
     *   ...
     *   String charZhongWen = "中文"
     *   String pinyinZhong = getPinyin(charZhongWen)  // -> [String] "ZHONGWEN"
     *
     *   // Or
     *   import static projekt.cloud.piece.c2.pinyin.C2Pinyin;
     *   ...
     *   String charZhongWen = "中文"
     *   String pinyinZhong = C2Pinyin.getPinyin(charZhongWen)  // -> [String] "ZHONGWEN"
     **/
    @JvmStatic
    val String.pinyin get() = pinyinArrayList.joinToString(separator = EMPTY_STR)

    /**
     * [String.convertToPinyin] convert [String] into pinyin string with [separator] between each
     * @param separator
     *
     * For Kotlin:
     *   import projekt.cloud.piece.c2.pinyin.C2Pinyin.convertToPinyin
     *   ...
     *   val charZhongWen = "中文"
     *   val pinyinZhong = charZhongWen.convertToPinyin(" ") // -> [String] "ZHONG WEN"
     *
     * For Java:
     *   import static projekt.cloud.piece.c2.pinyin.C2Pinyin.convertToPinyin;
     *   ...
     *   String charZhongWen = "中文"
     *   String pinyinZhong = convertToPinyin(charZhongWen, " ")  // -> [String] "ZHONG WEN"
     *
     *   // Or
     *   import static projekt.cloud.piece.c2.pinyin.C2Pinyin;
     *   ...
     *   String charZhongWen = "中文"
     *   String pinyinZhong = C2Pinyin.convertToPinyin(charZhongWen, " ")  // -> [String] "ZHONG WEN"
     **/
    @JvmStatic
    fun String.convertToPinyin(separator: String = EMPTY_STR) =
        pinyinArrayList.joinToString(separator = separator)

}