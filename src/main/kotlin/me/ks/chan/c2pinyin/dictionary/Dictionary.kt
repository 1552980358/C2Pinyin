package me.ks.chan.c2pinyin.dictionary

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

}

private inline val List<Record>.distinctRecordText: List<Record>
    get() = distinctBy(Record::text)
