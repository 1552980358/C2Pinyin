package me.ks.chan.c2pinyin.dictionary.item.serde

import me.ks.chan.c2pinyin.dictionary.item.DictionaryItem

interface AbstractSerde<S> {

    fun serialize(list: List<DictionaryItem>): S

    fun deserialize(serialized: S): List<DictionaryItem>

}