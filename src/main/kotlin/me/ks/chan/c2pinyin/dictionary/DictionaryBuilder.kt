package me.ks.chan.c2pinyin.dictionary

import java.io.File
import java.io.InputStream
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import me.ks.chan.c2pinyin.resources.pinyin.Pinyin

private sealed interface Source {

    fun load(): List<Record>

    data class Dict(private val dictionary: Dictionary): Source {
        override fun load(): List<Record> = dictionary.recordList
    }

    data class PinyinPair(private val pair: Pair<String, List<Pinyin>>): Source {
        override fun load(): List<Record> {
            val (text, pinyinList) = pair
            return listOf(
                Record(text = text, indexList = pinyinList.map(Pinyin::index))
            )
        }
    }

    data class PinyinPairList(private val list: List<Pair<String, List<Pinyin>>>): Source {
        override fun load(): List<Record> {
            return list.map { (text: String, pinyinList: List<Pinyin>) ->
                Record(text = text, indexList = pinyinList.map(Pinyin::index))
            }
        }
    }

    data class JsonString(private val string: String): Source {
        override fun load(): List<Record> {
            return Json.decodeFromString(string)
        }
    }

    data class JsonFile(private val file: File): Source {
        override fun load(): List<Record> {
            @OptIn(ExperimentalSerializationApi::class)
            return file.inputStream().use(Json::decodeFromStream)
        }
    }

    class JsonStream(private val inputStream: InputStream):
        Source,
        AutoCloseable by inputStream {

        override fun load(): List<Record> {
            @OptIn(ExperimentalSerializationApi::class)
            return inputStream.use(Json::decodeFromStream)
        }

    }

}

class DictionaryBuilder internal constructor() {

    private typealias Self = DictionaryBuilder

    private val sourceList = mutableListOf<Source>()

    @JvmSynthetic
    operator fun plusAssign(dictionary: Dictionary) {
        sourceList += Source.Dict(dictionary)
    }

    @JvmSynthetic
    operator fun plusAssign(pair: Pair<String, List<Pinyin>>) {
        sourceList += Source.PinyinPair(pair)
    }

    @JvmSynthetic
    operator fun plusAssign(list: List<Pair<String, List<Pinyin>>>) {
        sourceList += Source.PinyinPairList(list)
    }

    @JvmSynthetic
    operator fun plusAssign(map: Map<String, List<Pinyin>>) {
        this += map.toList()
    }

    @JvmSynthetic
    operator fun plusAssign(string: String) {
        sourceList += Source.JsonString(string)
    }

    @JvmSynthetic
    operator fun plusAssign(file: File) {
        sourceList += Source.JsonFile(file)
    }

    @Deprecated(
        level = DeprecationLevel.WARNING,
        message =
            "Adding java.io.InputStream as source will lead to the locking of targeted IO resource until DictionaryBuilder complete of building. " +
                "You are strongly encouraged to add json string or json file so that release the resource as quick as possible. \n" +
                """If you understand what issue you are facing, use `@Suppress("Deprecation")` to suppress this warning. """,
        replaceWith = ReplaceWith(
            imports = arrayOf("me.ks.chan.c2pinyin.dictionary.DictionaryBuilder"),
            expression = "plusAssign(string)",
        )
    )
    @JvmSynthetic
    operator fun plusAssign(inputStream: InputStream) {
        sourceList += Source.JsonStream(inputStream)
    }

    infix fun add(dictionary: Dictionary) = apply { this += dictionary }

    infix fun add(pair: Pair<String, List<Pinyin>>) = apply { this += pair }

    infix fun add(list: List<Pair<String, List<Pinyin>>>) = apply { this += list }

    infix fun add(map: Map<String, List<Pinyin>>) = apply { this += map }

    infix fun add(string: String): Self = apply { this += string }

    infix fun add(file: File): Self = apply { this += file }

    @Deprecated(
        level = DeprecationLevel.WARNING,
        message =
            "Adding java.io.InputStream as source will lead to the locking of targeted IO resource until DictionaryBuilder complete of building. " +
            "You are strongly encouraged to add json string or json file so that release the resource as quick as possible. \n" +
            """If you understand what issue you are facing, use `@Suppress("Deprecation")` in Kotlin or """ +
            """`@SuppressWarnings("Deprecation")` in Java to suppress this warning.""",
        replaceWith = ReplaceWith(
            imports = arrayOf("me.ks.chan.c2pinyin.dictionary.DictionaryBuilder"),
            expression = "add(string)",
        )
    )
    @Suppress("DEPRECATION")
    infix fun add(inputStream: InputStream) = apply { this += inputStream }

    fun interface BuilderBlock {
        operator fun invoke(builder: Self)
    }

    @OptIn(ExperimentalContracts::class)
    fun block(builderBlock: BuilderBlock): Self = apply {
        builderBlock(this)
    }

    fun build(): Dictionary = Dictionary(
        recordList =
            sourceList.flatMap(Source::load)    // 1. Load source flatting & flat all into 1-dimension list
                .distinctBy(Record::text)         // 2. Keep text appears first only, making smaller memory usage
    )

    @OptIn(ExperimentalContracts::class)
    @JvmSynthetic
    internal fun buildBlock(builder: DictionaryBuilder.() -> Unit): Dictionary {
        contract {
            callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
        }
        builder()
        return build()
    }

}

fun Dictionary(builder: DictionaryBuilder.() -> Unit = {}): Dictionary =
    DictionaryBuilder().buildBlock(builder)