package me.ks.chan.c2pinyin.dictionary.item.serde

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import me.ks.chan.c2pinyin.dictionary.item.Word
import me.ks.chan.c2pinyin.dictionary.item.Phrase

class KotlinxSerdeTest {

    @Test
    fun testDeserialize() {

        val list = listOf(
            KotlinxSerde.fileSource(
                "src/test/resources/kotlinx-serde-test-deserialize.json"
                    .let(::File)
            ),
            KotlinxSerde.stringSource(
                """
                    [
                        {
                            "type": "word",
                            "char": "行",
                            "pinyin": 362
                        },
                        {
                            "type": "phrase",
                            "words": [
                                {
                                    "char": "银",
                                    "pinyin": 348
                                },
                                {
                                    "char": "行",
                                    "pinyin": 113
                                }
                            ]
                        }
                    ]
                """.trimIndent()
            ),
        )

        val deserialized = list.flatMap(SerdeSourceItem<*, *>::deserialize)

        val ni = '你'
        val niPinyin = 216
        val hao = '好'
        val haoPinyin = 114
        val niHao = "你好"

        val first = deserialized.first() as Word
        assertEquals(ni, first.char)
        assertEquals(niPinyin, first.pinyin)

        val second = deserialized[1] as Phrase

        val secondNi = second.words[0]
        assertEquals(ni, secondNi.char)
        assertEquals(niPinyin, secondNi.pinyin)

        val secondHao = second.words[1]
        assertEquals(hao, secondHao.char)
        assertEquals(haoPinyin, secondHao.pinyin)

        assertEquals(niHao, second.string)

        val yin = '银'
        val hang = '行'
        val xingPinyin = 362
        val yinPinyin = 348
        val hangPinyin = 113
        val yinHang = "银行"

        val third = deserialized[2] as Word
        assertEquals(hang, third.char)
        assertEquals(xingPinyin, third.pinyin)

        val forth = deserialized[3] as Phrase

        val forthYin = forth.words[0]
        assertEquals(yin, forthYin.char)
        assertEquals(yinPinyin, forthYin.pinyin)

        val forthHang = forth.words[1]
        assertEquals(hang, forthHang.char)
        assertEquals(hangPinyin, forthHang.pinyin)

        assertEquals(yinHang, forth.string)

    }

}