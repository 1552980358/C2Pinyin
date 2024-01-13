import org.ks.chan.c2pinyin.C2Pinyin.Global.Global
import org.ks.chan.c2pinyin.dictionary.Dictionary
import org.ks.chan.c2pinyin.dictionary.Word
import org.ks.chan.c2pinyin.pinyin
import kotlin.test.Test
import kotlin.test.assertEquals

class DictionaryTest {

    @Test
    fun singleWord() {
        val char = '行'
        val originPinyin = "Xing"
        assertEquals(originPinyin, char.pinyin)
        val newPinyin = "Hang"
        Global.dictionary += Word(char, newPinyin)
        // Output: `Hang`
        assertEquals(newPinyin, char.pinyin)
    }

    @Test
    fun jumpWord() {
        val char = '拼'
        val originPinyin = "Pin"
        assertEquals(char.pinyin, originPinyin)
        // Empty 2nd parameter -> jump conversion
        Global.dictionary += Word(char)
        // Output: `拼`
        assertEquals(char.toString(), char.pinyin)
    }

    @Test
    fun phase() {
        /**
         * It is strongly recommended to store all mapping in a file,
         * and a file considers as a dictionary, which
         * a file can be imported into a [Dictionary] instance,
         * so that you can have multiple dictionary for different usage
         *
         * Format:
         * 1) Each line place only one pair
         * 2) Use `-` to separate each pinyin
         * 3) Each word should have corresponding pinyin String
         * 4) Use `!` to mark that word as jumping
         * 5) Single word or phase is supported at the same time in single dictionary file
         * 6) Space char(s) around will be trimmed
         **/
        // Consider it is a file content
        // val dictionaryString = File("path").readText()
        val dictionaryString = """
            行 = Xing
            银行 = Yin-Hang
            跳 = !
            跳过 = Tiao-Guo
        """.trimIndent()
        // Directly put File instance
        // val dictionary = Dictionary(File("Path"))
        // Or read it into line list
        val dictionary = Dictionary(dictionaryString.lines())
        // Replace dictionary
        Global.dictionary = dictionary
        // Or create your own conversion instance
        // val c2Pinyin = C2Pinyin(dictionary)

        assertEquals("Xing", '行'.pinyin)
        assertEquals("Yin, Hang", "银行".pinyin.joinToString())
        // Return same char
        assertEquals("跳", '跳'.pinyin)
        // Return converted
        assertEquals("Tiao, Guo", "跳过".pinyin.joinToString())
    }

}