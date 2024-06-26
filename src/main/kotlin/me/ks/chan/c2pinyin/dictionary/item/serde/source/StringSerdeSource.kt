package me.ks.chan.c2pinyin.dictionary.item.serde.source

import me.ks.chan.c2pinyin.dictionary.item.serde.AbstractSerde
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

interface StringSerdeSource<T> {

    fun stringSource(string: String): SerdeSourceItem<String, T> {
        @Suppress("UNCHECKED_CAST")
        return SerdeSourceItem(
            string,
            this as AbstractSerde<T>,
            ::handleSource
        )
    }

    fun handleSource(string: String): T

}