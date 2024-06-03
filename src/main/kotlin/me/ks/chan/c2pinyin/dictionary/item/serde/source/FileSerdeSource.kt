package me.ks.chan.c2pinyin.dictionary.item.serde.source

import java.io.File
import me.ks.chan.c2pinyin.dictionary.item.serde.AbstractSerde
import me.ks.chan.c2pinyin.dictionary.item.serde.SerdeSourceItem

interface FileSerdeSource<T> {

    fun fileSource(file: File): SerdeSourceItem<File, T> {
        @Suppress("UNCHECKED_CAST")
        return SerdeSourceItem(
            file,
            this as AbstractSerde<T>,
            ::handleSource
        )
    }

    fun handleSource(file: File): T

}