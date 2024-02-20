package org.ks.chan.c2pinyin

import org.ks.chan.c2pinyin.dictionary.Dictionary

internal interface C2PinyinSkeleton {

    var letterCase: LetterCase

    var dictionary: Dictionary

    fun dictionary(block: Dictionary.() -> Unit) {
        dictionary.apply(block)
    }

}