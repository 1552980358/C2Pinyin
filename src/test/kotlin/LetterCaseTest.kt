import org.ks.chan.c2pinyin.C2Pinyin
import org.ks.chan.c2pinyin.C2Pinyin.Global.Global
import org.ks.chan.c2pinyin.LetterCase
import org.ks.chan.c2pinyin.pinyin
import kotlin.test.Test
import kotlin.test.assertEquals

class LetterCaseTest {

    @Test
    fun camelcaseTest() {
        // By default, camelcase is set in settings, which is no need for custom configuration
        // So this line is no needed
        Global.letterCase = LetterCase.Camel
        // Or individual instance
        val c2Pinyin = C2Pinyin(letterCase = LetterCase.Camel)

        val niChinese = '你'
        val niPinyin = "Ni"

        // Using `.pinyin` will use configuration set in `C2Pinyin.Global`
        assertEquals(niPinyin, niChinese.pinyin)
        // Or single time letter case setting
        assertEquals(niPinyin, niChinese.pinyin(letterCase = LetterCase.Camel))
        // Or use custom `C2Pinyin` instance
        // Custom instance should call `.char()` instead of `.pinyin()`
        assertEquals(niPinyin, c2Pinyin.char(niChinese))

        // String
        val niHaoChinese = "你好"
        val niHaoPinyin = "Ni, Hao"
        // Using `.pinyin` will use configuration set in `C2Pinyin.Global`
        assertEquals(niHaoPinyin, niHaoChinese.pinyin.joinToString())
        // Or single time letter case setting
        assertEquals(niHaoPinyin, niHaoChinese.pinyin().joinToString())
        // Or use custom `C2Pinyin` instance
        // Custom instance should call `.string()` instead of `.pinyin()`
        assertEquals(niHaoPinyin, c2Pinyin.string(niHaoChinese).joinToString())
    }

    @Test
    fun uppercaseTest() {
        // Set to global
        Global.letterCase = LetterCase.Upper
        // Or individual instance
        val c2Pinyin = C2Pinyin(letterCase = LetterCase.Upper)

        // Char
        val niChinese = '你'
        val niPinyin = "NI"
        assertEquals(niPinyin, niChinese.pinyin)
        assertEquals(niPinyin, c2Pinyin.char(niChinese))

        // String
        val niHaoChinese = "你好"
        val niHaoPinyin = "NI, HAO"
        assertEquals(niHaoPinyin, niHaoChinese.pinyin.joinToString())
        assertEquals(niHaoPinyin, c2Pinyin.string(niHaoChinese).joinToString())
    }

    @Test
    fun lowercaseTest() {
        // Set to global
        Global.letterCase = LetterCase.Lower
        // Or individual instance
        val c2Pinyin = C2Pinyin(letterCase = LetterCase.Lower)

        // Char
        val niChinese = '你'
        val niPinyin = "ni"
        assertEquals(niPinyin, niChinese.pinyin)
        assertEquals(niPinyin, c2Pinyin.char(niChinese))

        // String
        val niHaoChinese = "你好"
        val niHaoPinyin = "ni, hao"
        assertEquals(niHaoPinyin, niHaoChinese.pinyin.joinToString())
        assertEquals(niHaoPinyin, c2Pinyin.string(niHaoChinese).joinToString())
    }

}
