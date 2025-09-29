package me.ks.chan.c2pinyin.dictionary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ConsistentCopyVisibility
@Serializable
data class Record internal constructor(
    @SerialName("text")
    val text: String,
    @SerialName("indexes")
    val indexList: List<Int>,
) {

    init {
        if (text.isBlank() || text.length != indexList.size) {
            throw IllegalArgumentException("Record text must have the same length as indexes: $text")
        }
    }

}