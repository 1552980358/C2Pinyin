package me.ks.chan.c2pinyin.resources.mapping

import java.io.File
import java.io.InputStream

private const val Root = "mapping"

private sealed class Segment(
    val name: String, val utf8Offset: Int, val utf8End: Int
) {
    data object One: Segment("1", 0x4E00, 0x6958)
    data object Two: Segment("2", 0x6958, 0x84B0)
    data object Three: Segment("3", 0x84B0, 0x9FA6)

    operator fun contains(utf8Code: Int): Boolean {
        return utf8Code in (utf8Offset ..< utf8End)
    }
}

private sealed class Variant(val name: String) {
    data object Index: Variant("index")
    data object Padding: Variant("padding")
}

private const val FileExt = ".bin"

private inline val PathSeparator: Char
    get() = File.pathSeparatorChar

internal sealed class Mapping(private val segment: Segment) {

    data object One: Mapping(Segment.One)
    data object Two: Mapping(Segment.Two)
    data object Three: Mapping(Segment.Three)

    companion object Static {

        private inline val String.stream: InputStream
            get() = runCatching(Static::class.java::getResourceAsStream)
                .mapCatching { inputStream -> inputStream!! }
                .onFailure { exception ->
                    throw IllegalArgumentException("Error reading resource $this from stream", exception)
                }
                .getOrThrow()

        private inline val InputStream.byteArray: ByteArray
            get() = runCatching { use(InputStream::readBytes) }
                .onFailure { exception ->
                    throw IllegalArgumentException("Error reading resource $this from stream", exception)
                }
                .getOrThrow()

        // mapping/<VARIANT: index|padding>/<SEGMENT: 1|2|3>.bin
        private operator fun Segment.invoke(variant: Variant): String =
            "${Root}${PathSeparator}${variant.name}${PathSeparator}${this.name}${FileExt}"

        operator fun contains(utf8Code: Int): Boolean {
            return utf8Code in One || utf8Code in Two || utf8Code in Three
        }

        operator fun get(utf8Code: Int): Mapping =
            when (utf8Code) {
                in One -> { One }
                in Two -> { Two }
                in Three -> { Three }
                else -> { throw IllegalAccessException("""UTF-8 code "$utf8Code": Out of ZH UTF-8 range""") }
            }

    }

    val indexes by lazy { segment(Variant.Index).stream.byteArray }

    val paddings by lazy { segment(Variant.Padding).stream.byteArray }

    operator fun contains(utf8Code: Int) = utf8Code in segment

    operator fun unaryMinus() = -segment.utf8Offset

}