package me.ks.chan.c2pinyin.pinyin

import java.io.InputStream
import kotlin.jvm.Throws
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@Serializable
sealed class Pinyin {

    @Serializable
    @SerialName("single-vowel")
    data class SingleVowel(
        @SerialName("vowel")
        val vowel: PinyinVowel
    ): Pinyin()

    @Serializable
    @SerialName("initial-and-vowelled")
    data class InitialAndVowelled(
        @SerialName("initial")
        val initial: PinyinInitial,
        @SerialName("vowel")
        val vowel: PinyinVowel
    ): Pinyin()

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