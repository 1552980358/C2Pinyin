package me.ks.chan.c2pinyin.dictionary

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStream
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream

/**
 * @param recordList [List]<[Record]>
 * @constructor [List]<[Record]>
 **/
@ConsistentCopyVisibility
data class Dictionary
internal constructor(
    internal var recordList: List<Record>
) {

    companion object Static {
        /**
         * Create a [DictionaryBuilder] instance
         * @return [DictionaryBuilder]
         */
        @JvmStatic
        fun builder() = DictionaryBuilder()
    }

    /**
     * For Kotlin user only: Add [record] to current [Dictionary] instance
     * @param record [Record]
     **/
    @JvmSynthetic
    operator fun plusAssign(record: Record) {
        recordList = (recordList + record).distinctRecordText
    }

    /**
     * For Kotlin user only: Add [addRecordList] to current [Dictionary] instance
     * @param addRecordList [List]<[Record]>
     **/
    @JvmSynthetic
    operator fun plusAssign(addRecordList: List<Record>) {
        recordList = (recordList + addRecordList).distinctRecordText
    }

    /**
     * For Kotlin user only: Add [recordList] owned by [dictionary] to current [Dictionary] instance
     * @param recordList [Dictionary]
     **/
    @JvmSynthetic
    operator fun plusAssign(dictionary: Dictionary) {
        recordList = (recordList + dictionary.recordList).distinctRecordText
    }

    /**
     * Add [record] to current [Dictionary] instance for Java user.
     * Use `+=` operator ([plusAssign]) for Kotlin DSL user.
     * @param record [Record]
     **/
    infix fun add(record: Record) = apply { this += record }

    /**
     * Add [recordList] to current [Dictionary] instance for Java user.
     * Use `+=` operator ([plusAssign]) for Kotlin DSL user.
     * @param recordList [Dictionary]
     **/
    infix fun add(recordList: List<Record>) = apply { this += recordList }

    /**
     * Add [recordList] owned by [Dictionary] instance to current [Dictionary] instance for Java user.
     * Use `+=` operator ([plusAssign]) for Kotlin DSL user.
     * @param dictionary [Dictionary]
     **/
    infix fun add(dictionary: Dictionary) = apply { this += dictionary }

    /**
     * Get serialized json string of current [Dictionary] instance
     * @return [kotlin.String] Output json string instance
     **/
    fun jsonString(): String {
        return Json.encodeToString(recordList)
    }

    /**
     * Save current [Dictionary] instance to targeted [java.io.File] instance
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
     * You should call [java.lang.AutoCloseable.close] the [target] by your self!
     *
     * Strongly recommended to use kotlin extension method [java.lang.AutoCloseable.use] cooperated with method
     * referencing to [Dictionary.outputStream]. See following example
     * ```kotlin
     * val dictionary = Dictionary { /*...*/ }
     *     .also { File("dictionary.json").outputStream().use(it::outputStream) }
     * ```
     * @param target [java.io.OutputStream] Write to targeted [java.io.OutputStream] instance [target]
     * @throws java.io.IOException
     **/
    @Throws(IOException::class)
    fun outputStream(target: OutputStream) {
        @OptIn(ExperimentalSerializationApi::class)
        Json.encodeToStream(recordList, target)
    }

}

/**
 * @param this [List]<[Record]> Distinct [this] by [Record.text]
 * @return [List]<[Record]>
 **/
private inline val List<Record>.distinctRecordText: List<Record>
    get() = distinctBy(Record::text)
