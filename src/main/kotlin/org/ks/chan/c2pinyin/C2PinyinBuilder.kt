package org.ks.chan.c2pinyin

import org.ks.chan.c2pinyin.dictionary.Dictionary

/**
 * [C2Pinyin]
 * @param letterCase [LetterCase]
 * @param dictionary [Dictionary]
 * @param builderBlock [kotlin.jvm.functions.Function1]
 * @return [C2Pinyin]
 *
 * A DSL style wrapper function of [C2PinyinBuilder]
 **/
@JvmOverloads
fun C2Pinyin(
    letterCase: LetterCase = LetterCase.Camel,
    dictionary: Dictionary? = null,
    builderBlock: C2PinyinBuilder.() -> Unit = {}
): C2Pinyin {
    return C2PinyinBuilder(letterCase, dictionary, builderBlock)
        .build()
}

/**
 * [C2PinyinBuilder]
 * @param letterCase [LetterCase]
 * @param dictionary [Dictionary]
 * @param builderBlock [kotlin.jvm.functions.Function1]
 **/
class C2PinyinBuilder(
    override var letterCase: LetterCase,
    dictionary: Dictionary?,
    private val builderBlock: C2PinyinBuilder.() -> Unit,
): C2PinyinSkeleton {

    /**
     * [C2PinyinBuilder._dictionary]
     * @access Private
     *
     * A nullable [Dictionary] instance holder
     **/
    private var _dictionary: Dictionary? = dictionary
        @Synchronized set

    /**
     * [C2PinyinBuilder.dictionary]
     *
     * A backing property for [C2PinyinBuilder._dictionary]
     **/
    override var dictionary: Dictionary
        /**
         * [C2PinyinBuilder.dictionary].[kotlin.reflect.KMutableProperty.getter]
         *
         * A getter for [C2PinyinBuilder.dictionary], which occupies singleton
         **/
        get() = _dictionary ?: synchronized(this) {
            _dictionary ?: Dictionary().apply(::_dictionary::set)
        }
        /**
         * [C2PinyinBuilder.dictionary].[kotlin.reflect.KMutableProperty.setter]
         *
         * A setter for [C2PinyinBuilder.dictionary], which occupies singleton
         **/
        @Synchronized
        set(value) {
            _dictionary = value
        }

    /**
     * [C2PinyinBuilder.build]
     * @return [C2Pinyin]
     *
     * Invoke [C2PinyinBuilder.builderBlock] and return [C2Pinyin] instance
     **/
    fun build(): C2Pinyin {
        builderBlock.invoke(this)
        return C2Pinyin(letterCase, dictionary)
    }

}