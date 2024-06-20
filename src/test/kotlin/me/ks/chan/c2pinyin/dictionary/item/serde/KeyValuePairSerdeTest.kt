package me.ks.chan.c2pinyin.dictionary.item.serde

import kotlin.test.Test
import kotlin.test.assertEquals
import me.ks.chan.c2pinyin.dictionary.item.Word
import me.ks.chan.c2pinyin.dictionary.item.Phrase

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

        val first = deserialized[0] as Word
        assertEquals(348, first.pinyin)

        val second = deserialized[1] as Phrase
        assertEquals(362, second.words[0].pinyin)
        assertEquals(113, second.words[1].pinyin)
    }

}