package me.ks.chan.c2pinyin.dictionary

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStream
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream

@ConsistentCopyVisibility
data class Dictionary
internal constructor(
    internal var recordList: List<Record>
) {

    companion object Static {
        @JvmStatic
        fun builder() = DictionaryBuilder()
    }

    @JvmSynthetic
    operator fun plusAssign(record: Record) {
        recordList = (recordList + record).distinctRecordText
    }

    @JvmSynthetic
    operator fun plusAssign(otherRecordList: List<Record>) {
        recordList = (recordList + otherRecordList).distinctRecordText
    }

    @JvmSynthetic
    operator fun plusAssign(dictionary: Dictionary) {
        recordList = (recordList + dictionary.recordList).distinctRecordText
    }

    infix fun add(record: Record) = apply { this += record }

    infix fun add(otherRecordList: List<Record>) = apply { this += otherRecordList }

    infix fun add(dictionary: Dictionary) = apply { this += dictionary }

    /**
     * [Dictionary.jsonString]
     * @return [kotlin.String] Output json string instance
     **/
    fun jsonString(): String {
        return Json.encodeToString(recordList)
    }

    /**
     * [Dictionary.toFile]
     * @param target [java.io.File] Write to targeted [java.io.File] instance
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.SecurityException
     **/
    @Throws(FileNotFoundException::class, IOException::class, SecurityException::class)
    fun toFile(target: File) {
        target.writeText(jsonString())
    }

    /**
     * [Dictionary.outputStream]
     * @param target [java.io.OutputStream] Write to targeted [java.io.OutputStream] instance [target]
     * @throws java.io.IOException
     *
     * You should call [java.lang.AutoCloseable.close] the [target] by your self!
     * Strongly recommended to use kotlin extension method [java.lang.AutoCloseable.use] cooperated with method
     * referencing to [Dictionary.outputStream]. See following example
     * ```kotlin
     * val dictionary = Dictionary { /*...*/ }
     *     .also { File("dictionary.json").outputStream().use(it::outputStream) }
     * ```
     **/
    @Throws(IOException::class)
    fun outputStream(target: OutputStream) {
        @OptIn(ExperimentalSerializationApi::class)
        Json.encodeToStream(recordList, target)
    }

}

private inline val List<Record>.distinctRecordText: List<Record>
    get() = distinctBy(Record::text)
