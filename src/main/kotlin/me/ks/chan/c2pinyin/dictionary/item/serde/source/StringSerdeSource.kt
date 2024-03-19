package me.ks.chan.c2pinyin.dictionary.item.serde.source

import me.ks.chan.c2pinyin.dictionary.item.serde.AbstractSerde
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

interface StringSerdeSource<T> {

    fun stringSource(string: String): SerdeSourceItem<String, T> {
        return SerdeSourceItem(
            string,
            @Suppress("UNCHECKED_CAST")
            this as AbstractSerde<T>,
            ::handleSource
        )
    }

    fun handleSource(string: String): T

}