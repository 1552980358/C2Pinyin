package me.ks.chan.c2pinyin

import kotlin.test.Test
import kotlin.test.assertEquals
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem

class C2PinyinItemTest {

    @Test
    fun testCharItem() {
        val pinyinCharList = ("这个银行到底行不行？").let(C2String::new)

        val charItem = DictionaryItem.Char('行', 348)

        assertEquals(
            listOf(3, 6, 8),
            pinyinCharList.indexListOf(charItem),
        )

        pinyinCharList[3].index = 113
        assertEquals(
            listOf(6, 8),
            pinyinCharList.indexListOf(charItem),
        )
    }

    @Test
    fun testStringItem() {
        val pinyinCharList = ("行走在行人上看到有几家银行").let(C2String::new)

        val stringItem = DictionaryItem.String(
            listOf(
                DictionaryItem.Char('银', 362),
                DictionaryItem.Char('行', 113)
            )
        )

        assertEquals(
            listOf(11),
            pinyinCharList.indexListOf(stringItem)
        )
    }

}