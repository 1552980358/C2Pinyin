package me.ks.chan.c2pinyin.dictionary.item.serde

import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem

class SerdeSourceItem<Source, Output>(
    private val source: Source,
    private val serde: AbstractSerde<Output>,
    private val factory: (Source) -> Output,
) {

    fun deserialize(): List<DictionaryItem> {
        return serde.deserialize(
            factory.invoke(source)
        )
    }

}