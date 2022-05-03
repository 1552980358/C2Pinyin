package projekt.cloud.piece.c2.pinyin

import projekt.cloud.piece.c2.pinyin.util.ConstantValue.EMPTY_STR
import projekt.cloud.piece.c2.pinyin.util.PinyinUtil.pinyinArrayList
import projekt.cloud.piece.c2.pinyin.util.PinyinUtil.pinyinStr

object C2Pinyin {

    @JvmStatic
    val Char.pinyin get() = pinyinStr

    @JvmStatic
    val String.pinyin get() = pinyinArrayList.joinToString(separator = EMPTY_STR)

    @JvmStatic
    fun String.convertToPinyin(separator: String = EMPTY_STR) =
        pinyinArrayList.joinToString(separator = EMPTY_STR)

}