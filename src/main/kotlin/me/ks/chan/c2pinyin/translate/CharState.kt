package me.ks.chan.c2pinyin.translate

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import me.ks.chan.c2pinyin.resources.pinyin.Pinyin

internal interface Accessed

sealed class CharState {

    abstract val char: Char

    data class Raw(override val char: Char): CharState()

    @ConsistentCopyVisibility
    data class Translated internal constructor(
        override val char: Char,
        val index: Int
    ): CharState(), Accessed {

        val pinyin: Pinyin by lazy { Pinyin[index] }

    }

    data class Unchanged(override val char: Char): CharState(), Accessed

    @OptIn(ExperimentalContracts::class)
    internal fun isAccessed(): Boolean {
        contract {
            returns(true) implies (this@CharState is Accessed)
        }
        return (this is Accessed)
    }

}