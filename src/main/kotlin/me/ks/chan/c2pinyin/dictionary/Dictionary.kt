package me.ks.chan.c2pinyin.dictionary

data class Dictionary(
    private val recordList: MutableList<Record>
) {

    companion object Static {
        @JvmStatic
        fun builder() = DictionaryBuilder()
    }

    init {
        TODO("Dictionary placeholder class, will be implemented in the future.")
    }

}
