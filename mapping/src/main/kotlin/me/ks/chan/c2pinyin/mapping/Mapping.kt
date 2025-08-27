package me.ks.chan.c2pinyin.mapping

import java.io.File
import java.io.InputStream

private const val Root = "mapping"

private sealed class Segment(val name: String, val utf8Offset: Int, val utf8End: Int) {
    data object One: Segment("1", 0x4E00, 0x6958)
    data object Two: Segment("2", 0x6958, 0x84B0)
    data object Three: Segment("3", 0x84B0, 0x9FA6)

    val range = utf8Offset until utf8End
}

private sealed class Variant(val name: String) {
    data object Index: Variant("index")
    data object Padding: Variant("padding")
}

private const val FileExt = ".bin"

private inline val PathSeparator: Char
    get() = File.pathSeparatorChar

sealed class Binary(private val segment: Segment) {

    internal data object One: Binary(Segment.One)
    internal data object Two: Binary(Segment.Two)
    internal data object Three: Binary(Segment.Three)

    private companion object Static {

        inline val String.stream: InputStream
            get() = runCatching(Static::class.java::getResourceAsStream)
                .mapCatching { inputStream -> inputStream!! }
                .onFailure { exception ->
                    throw IllegalArgumentException("Error reading resource $this from stream", exception)
                }
                .getOrThrow()

        inline val InputStream.byteArray: ByteArray
            get() = runCatching { use(InputStream::readBytes) }
                .onFailure { exception ->
                    throw IllegalArgumentException("Error reading resource $this from stream", exception)
                }
                .getOrThrow()

        // mapping/<VARIANT: index|padding>/<SEGMENT: 1|2|3>.bin
        operator fun Segment.invoke(variant: Variant): String =
            "${Root}${PathSeparator}${variant.name}${PathSeparator}${this.name}${FileExt}"

    }

    val indexes by lazy { segment(Variant.Index).stream.byteArray }

    val paddings by lazy { buildPath(variant = Variant.Padding, segment = segment).resourcesStream }
    val paddings by lazy { segment(Variant.Padding).stream.byteArray }


}