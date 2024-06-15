package me.ks.chan.c2pinyin.dictionary.item.serde

import kotlin.test.Test
import kotlin.test.assertEquals
import me.ks.chan.c2pinyin.dictionary.item.DictionaryChar
import me.ks.chan.c2pinyin.dictionary.item.DictionaryString

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

        val first = deserialized[0] as DictionaryChar
        assertEquals(348, first.pinyin)

        val second = deserialized[1] as DictionaryString
        assertEquals(362, second.charList[0].pinyin)
        assertEquals(113, second.charList[1].pinyin)
    }

}