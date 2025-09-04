package me.ks.chan.c2pinyin.resources.pinyin

import java.io.InputStream
import kotlin.jvm.Throws
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@Serializable
internal sealed class Pinyin {

    companion object Static {

        @Throws(IndexOutOfBoundsException::class, IllegalStateException::class)
        operator fun get(index: Int): Pinyin = PinyinList[index]

        @Throws(IllegalStateException::class)
        operator fun get(pinyin: Pinyin): Int? {
            return PinyinList.indexOfFirst { i -> i == pinyin }
                .takeIf { it > -1 }
        }

    }

    @Serializable
    @SerialName("single-vowel")
    data class SingleVowel(
        @SerialName("vowel")
        val vowel: PinyinVowel
    ): Pinyin() {

        override operator fun equals(other: Any?): Boolean {
            return other is SingleVowel && other.vowel == vowel
        }

        override fun hashCode(): Int = vowel.hashCode()

    }

    @Serializable
    @SerialName("initial-and-vowelled")
    data class InitialAndVowelled(
        @SerialName("initial")
        val initial: PinyinInitial,
        @SerialName("vowel")
        val vowel: PinyinVowel
    ): Pinyin() {

        override operator fun equals(other: Any?): Boolean {
            return other is InitialAndVowelled && other.initial == initial && other.vowel == vowel
        }

        override fun hashCode(): Int = 31 * initial.hashCode() + vowel.hashCode()

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