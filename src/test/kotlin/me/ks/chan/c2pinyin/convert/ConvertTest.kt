package me.ks.chan.c2pinyin.convert

import kotlin.test.Test
import kotlin.test.assertEquals

class ConvertTest {

    @Test
    fun testConvert() {
        // Halo => Halo
        assertEquals(null, 'H'.asIndex?.pinyinRaw)
        assertEquals(null, 'a'.asIndex?.pinyinRaw)
        assertEquals(null, 'l'.asIndex?.pinyinRaw)
        assertEquals(null, 'o'.asIndex?.pinyinRaw)

        // 你好世界 ==> ni hao shi jie
        assertEquals("ni", '你'.asIndex!!.pinyinRaw)
        assertEquals("hao", '好'.asIndex!!.pinyinRaw)
        assertEquals("shi", '世'.asIndex!!.pinyinRaw)
        assertEquals("jie", '界'.asIndex!!.pinyinRaw)
    }

}