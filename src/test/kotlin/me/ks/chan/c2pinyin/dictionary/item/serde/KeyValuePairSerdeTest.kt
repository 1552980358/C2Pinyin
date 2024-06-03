package me.ks.chan.c2pinyin.dictionary.item.serde

import kotlin.test.Test
import kotlin.test.assertEquals
import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem

class KeyValuePairSerdeTest {

    @Test
    fun testDeserialize() {
        val sourceList = listOf(
            KeyValuePairSerde.stringSource(
                """
                |行=xing
                |银行=yin,hang
                """.trimMargin()
            )
        )
        val deserialized = sourceList.flatMap(SerdeSourceItem<*, *>::deserialize)

        val first = deserialized[0] as DictionaryItem.Char
        assertEquals(348, first.pinyin)

        val second = deserialized[1] as DictionaryItem.String
        assertEquals(362, second.charList[0].pinyin)
        assertEquals(113, second.charList[1].pinyin)
    }

}