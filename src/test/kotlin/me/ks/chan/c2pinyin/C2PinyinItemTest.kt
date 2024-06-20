package me.ks.chan.c2pinyin

import kotlin.test.Test
import kotlin.test.assertEquals
import me.ks.chan.c2pinyin.dictionary.item.Word
import me.ks.chan.c2pinyin.dictionary.item.Phrase

class C2PinyinItemTest {

    @Test
    fun testCharItem() {
        val pinyinCharList = ("这个银行到底行不行？").let(C2String::new)

        val word = Word('行', 348)

        val indexList = mutableListOf<Int>()
        pinyinCharList.findWordIndex(
            word = word,
            block = indexList::plusAssign,
        )
        assertEquals(listOf(3, 6, 8), indexList)

        indexList.clear()
        pinyinCharList[3].pinyin = 113
        pinyinCharList.findWordIndex(
            word = word,
            block = indexList::plusAssign,
        )
        assertEquals(listOf(6, 8), indexList)
    }

    @Test
    fun testStringItem() {
        val pinyinCharList = ("行走在行人上看到有几家银行").let(C2String::new)

        val dictionaryString = Phrase(
            listOf(
                Word('银', 362),
                Word('行', 113)
            )
        )

        val indexList = mutableListOf<Int>()
        pinyinCharList.findPhaseIndex(
            dictionaryString = dictionaryString,
            block = indexList::plusAssign,
        )
        assertEquals(listOf(11), indexList)
    }

}