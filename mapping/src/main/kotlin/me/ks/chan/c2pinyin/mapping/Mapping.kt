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

private sealed class Binary(val segment: Segment) {

    data object One: Binary(Segment.One)
    data object Two: Binary(Segment.Two)
    data object Three: Binary(Segment.Three)

    private companion object Static {

        inline val String.resourcesStream: InputStream?
            get() = Static::class.java.getResourceAsStream(this)

        fun buildPath(variant: Variant, segment: Segment): String =
            // mapping/index/1.bin
            "${Root}${File.pathSeparatorChar}${variant.name}${File.pathSeparatorChar}${segment.name}${FileExt}"

    }

    val indexes by lazy { buildPath(variant = Variant.Index, segment = segment).resourcesStream }

    val paddings by lazy { buildPath(variant = Variant.Padding, segment = segment).resourcesStream }

}