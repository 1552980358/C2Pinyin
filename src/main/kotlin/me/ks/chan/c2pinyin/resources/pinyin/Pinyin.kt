package me.ks.chan.c2pinyin.resources.pinyin

import java.io.InputStream
import kotlin.jvm.Throws
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@ConsistentCopyVisibility
@Serializable
data class Pinyin internal constructor(
    @SerialName("initial")
    val initial: PinyinInitial = PinyinInitial.None,
    @SerialName("vowel")
    val vowel: PinyinVowel
) {

    val index: Int by lazy { PinyinList.indexOfFirst(this::equals) }

    companion object Static {

        @Throws(IndexOutOfBoundsException::class, IllegalStateException::class)
        operator fun get(index: Int): Pinyin = PinyinList[index]

    }

    override operator fun equals(other: Any?): Boolean {
        return other is Pinyin && other.vowel == vowel && other.initial == initial
    }

}

private val PinyinList: List<Pinyin> by lazy { PinyinJsonFileStream.asPinyinList }

private const val PinyinJsonFile = "pinyin.json"
private inline val PinyinJsonFileStream: InputStream
    @Throws(IllegalStateException::class)
    get() = PinyinJsonFile.runCatching(Pinyin::class.java::getResourceAsStream)
        .mapCatching { inputStream -> inputStream!! }
        .onFailure { exception ->
            throw IllegalStateException("Error while loading resource $PinyinJsonFile", exception)
        }
        .getOrThrow()

private inline val InputStream.asPinyinList: List<Pinyin>
    @OptIn(ExperimentalSerializationApi::class)
    @Throws(IllegalStateException::class)
    get() = runCatching<_, List<Pinyin>> { use(Json::decodeFromStream) }
        .onFailure { exception ->
            throw IllegalStateException("Error while reading resource $PinyinJsonFile", exception)
        }
        .getOrThrow()