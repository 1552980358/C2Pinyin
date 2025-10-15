package me.ks.chan.c2pinyin.dictionary

@ConsistentCopyVisibility
data class Dictionary
internal constructor(
    internal val recordList: MutableList<Record>
) {

    companion object Static {
        @JvmStatic
        fun builder() = DictionaryBuilder()
    }

    init {
        TODO("Dictionary placeholder class, will be implemented in the future.")
    }

}
